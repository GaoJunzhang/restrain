package com.restrain.service;

import com.restrain.dao.CommentRepository;
import com.restrain.model.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comments saveComment(Long ownerUserId, Long targetUserId, String content, Long parentId, Short parentType){

        Comments comment = new Comments();
        comment.setOwnerUserId(ownerUserId);
        comment.setTargetUserId(targetUserId);
        comment.setContent(content);
        comment.setCreaterTime(new Timestamp(System.currentTimeMillis()));
        comment.setParentId(parentId);
        comment.setParentType(parentType);
        return commentRepository.save(comment);
    }

    public int sumComment(Long activityId){
        return commentRepository.countByActivityId(activityId);
    }

    public List<Comments> comments(Long activityId){
        return commentRepository.findByActivityId(activityId);
    }
}
