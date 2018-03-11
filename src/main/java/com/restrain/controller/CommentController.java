package com.restrain.controller;

import com.google.common.collect.ImmutableMap;
import com.restrain.bean.CommentsBean;
import com.restrain.model.Comments;
import com.restrain.model.WxUsers;
import com.restrain.service.CommentService;
import com.restrain.service.WxUserService;
import com.restrain.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private WxUserService wxUserService;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "密一下中添加评论", notes = "添加评论")
    @RequestMapping(value = "saveComment", method = RequestMethod.POST, produces = "application/json")
    public Map<String,Object> saveComment(String sessionId,Long signId, String targetUserId, String content, Long parentId) {
        Object wxSessionObj = redisUtil.get(sessionId);
        if(null == wxSessionObj){
            return rtnParam(40008, null);
        }
        String wxSessionStr = (String)wxSessionObj;
        String openid = wxSessionStr.split("#")[1];
        Comments comment = commentService.saveComment(openid, targetUserId, content, parentId, signId);
        if (comment != null) {
            CommentsBean commentsBean = new CommentsBean();
            commentsBean.inject(comment);
            commentsBean.setMsg("success");
            return rtnParam(0, commentsBean);
        }
        return null;
    }

    @ApiOperation(value = "获取评论总数", notes = "根据活动id获取总评论数量")
    @RequestMapping(value = "sunmComment", method = RequestMethod.GET, produces = "application/json")
    public int sunmComment(Long signId) {
        return commentService.sumComment(signId);
    }

    @ApiOperation(value = "密一下中评论", notes = "根据signId获取评论总数List")
    @GetMapping(value = "comments")
    public Map<String,Object> comments(String sessionId,@RequestParam(required = true, value = "signId") Long signId) {
        Object wxSessionObj = redisUtil.get(sessionId);
        if(null == wxSessionObj){
            return rtnParam(40008, null);
        }
       /* String wxSessionStr = (String)wxSessionObj;
        String openid = wxSessionStr.split("#")[1];*/
        List<Comments> comments = commentService.comments(signId);
        List<CommentsBean> commentsBeans = new ArrayList<CommentsBean>();
        String[] idslong = new String[comments.size()];
        if (comments.size()>0){
            for (int i = 0;i<comments.size();i++){
                idslong[i] = comments.get(i).getOwnerUserId();
            }
        }
        List<WxUsers> wxUsers = wxUserService.wxUsersListByIdIn(idslong);
        for (Comments comment : comments) {
            CommentsBean commentsBean = new CommentsBean();
            commentsBean.inject(comment);
            for (int n=0;n<wxUsers.size();n++){
                if (!StringUtils.isEmpty(comment.getTargetUserId())&&comment.getTargetUserId().equals(wxUsers.get(n).getWxno())){
                    commentsBean.setTargetUserName(wxUsers.get(n).getWxName());
                }
                if (!StringUtils.isEmpty(comment.getOwnerUserId())&&comment.getOwnerUserId().equals(wxUsers.get(n).getWxno())){
                    commentsBean.setOwnerUserName(wxUsers.get(n).getWxName());
                }
                commentsBean.setLogo(wxUsers.get(n).getImg());
            }
            commentsBeans.add(commentsBean);
        }
        return rtnParam(0, ImmutableMap.of("comments",commentsBeans));
    }
}
