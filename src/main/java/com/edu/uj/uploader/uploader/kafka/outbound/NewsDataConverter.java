package com.edu.uj.uploader.uploader.kafka.outbound;

import com.edu.uj.uploader.uploader.rest.model.CommentRequest;
import com.edu.uj.uploader.uploader.rest.model.PostRequest;
import com.edu.uj.uploader.uploader.rest.model.ThreadRequest;
import com.edu.uj.uploader.uploader.rest.model.VoteRequest;

import java.util.HashMap;
import java.util.Map;

public class NewsDataConverter {

    public Map<String, Object> convert(ThreadRequest newsThread) {
        Map<String, Object> threadProperties = new HashMap<>();
        if (null != newsThread) {
            threadProperties.put("name", newsThread.getName());
            threadProperties.put("description", newsThread.getDescription());
            threadProperties.put("username", newsThread.getUsername());
        }
        return threadProperties;
    }

    public Map<String, Object> convert(PostRequest newsPost) {
        Map<String, Object> postProperties = new HashMap<>();
        if (null != newsPost) {
            postProperties.put("postName", newsPost.getPostName());
            postProperties.put("threadName", newsPost.getThreadName());
            postProperties.put("url", newsPost.getUrl());
            postProperties.put("fileCompressed", newsPost.getFileCompressed());
            postProperties.put("username", newsPost.getUsername());
            postProperties.put("description", newsPost.getDescription());
        }
        return postProperties;
    }

    public Map<String, Object> convert(CommentRequest newsComment) {
        Map<String, Object> commentProperties = new HashMap<>();
        if (null != newsComment) {
            commentProperties.put("text", newsComment.getText());
            commentProperties.put("postName", newsComment.getPostName());
            commentProperties.put("username", newsComment.getUsername());
            commentProperties.put("duration", newsComment.getDuration());
        }
        return commentProperties;
    }

    public Map<String, Object> convert(VoteRequest newsVote) {
        Map<String, Object> voteProperties = new HashMap<>();
        if (null != newsVote) {
            voteProperties.put("voteType", newsVote.getVoteType());
            voteProperties.put("username", newsVote.getUsername());
            voteProperties.put("postName", newsVote.getPostName());
        }
        return voteProperties;
    }
}
