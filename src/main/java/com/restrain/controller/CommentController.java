package com.restrain.controller;

import com.restrain.bean.CommentBean;
import com.restrain.model.Comments;
import com.restrain.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "添加评论", notes = "添加评论")
    @RequestMapping(value = "saveComment", method = RequestMethod.POST, produces = "application/json")
    public CommentBean saveComment(Long ownerUserId, Long targetUserId, String content, Long parentId, Short parentType) {
        Comments comment = commentService.saveComment(ownerUserId, targetUserId, content, parentId, parentType);
        if (comment != null) {
            CommentBean commentBean = new CommentBean();
            commentBean.inject(comment);
            return commentBean;
        }
        return null;
    }

    @ApiOperation(value = "获取评论总数", notes = "根据活动id获取总评论数量")
    @RequestMapping(value = "sunmComment", method = RequestMethod.GET, produces = "application/json")
    public int sunmComment(Long activityId) {
        return commentService.sumComment(activityId);
    }

    @ApiOperation(value = "获取活动评论明细", notes = "根据activityId获取评论总数List")
    @GetMapping(value = "comments")
    public List<CommentBean> comments(@RequestParam(required = true, value = "activityId") Long activityId) {
        List<Comments> comments = commentService.comments(activityId);
        List<CommentBean> commentBeans = new ArrayList<CommentBean>();
        for (Comments comment : comments) {
            CommentBean commentBean = new CommentBean();
            commentBean.inject(comment);
            commentBeans.add(commentBean);
        }
        return commentBeans;
    }
}
