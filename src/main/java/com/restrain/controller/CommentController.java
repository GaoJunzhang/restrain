package com.restrain.controller;

import com.restrain.bean.CommentsBean;
import com.restrain.model.Comments;
import com.restrain.model.WxUsers;
import com.restrain.service.CommentService;
import com.restrain.service.WxUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private WxUserService wxUserService;

    @ApiOperation(value = "添加评论", notes = "添加评论")
    @RequestMapping(value = "saveComment", method = RequestMethod.POST, produces = "application/json")
    public CommentsBean saveComment(String ownerUserId, String targetUserId, String content, Long parentId, Short parentType) {
        Comments comment = commentService.saveComment(ownerUserId, targetUserId, content, parentId, parentType);
        if (comment != null) {
            CommentsBean commentsBean = new CommentsBean();
            commentsBean.inject(comment);
            commentsBean.setMsg("success");
            return commentsBean;
        }
        return null;
    }

    @ApiOperation(value = "获取评论总数", notes = "根据活动id获取总评论数量")
    @RequestMapping(value = "sunmComment", method = RequestMethod.GET, produces = "application/json")
    public int sunmComment(Long signId) {
        return commentService.sumComment(signId);
    }

    @ApiOperation(value = "获取活动评论明细", notes = "根据activityId获取评论总数List")
    @GetMapping(value = "comments")
    public List<CommentsBean> comments(@RequestParam(required = true, value = "signId") Long signId) {
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
                if (!StringUtils.isEmpty(comment.getTargetUserId())&&comment.getTargetUserId().equals(wxUsers.get(n).getId())){
                    commentsBean.setTargetUserName(wxUsers.get(n).getWxName());
                }
                if (!StringUtils.isEmpty(comment.getOwnerUserId())&&comment.getOwnerUserId().equals(wxUsers.get(n).getId())){
                    commentsBean.setOwnerUserName(wxUsers.get(n).getWxName());
                }
            }
            commentsBeans.add(commentsBean);
        }
        return commentsBeans;
    }
}
