package com.example.reddit_rep.service;

import com.example.reddit_rep.domain.Comment;
import com.example.reddit_rep.domain.SubComment;
import com.example.reddit_rep.repo.CommentRepository;
import com.example.reddit_rep.repo.SubCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final SubCommentRepository subCommentRepository;

    public CommentService(CommentRepository commentRepository, SubCommentRepository subCommentRepository) {
        this.subCommentRepository = subCommentRepository;
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAllComment() {
        return commentRepository.findAll();
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<SubComment> findAllSubComment() {
        return subCommentRepository.findAll();
    }

    public SubComment saveSubComment(SubComment subComment) {
        return subCommentRepository.save(subComment);
    }

    public void deleteSubComment(Long subCommentId, Long commentId) {

        Optional<SubComment> subCommentOptional = subCommentRepository.findById(subCommentId);
        SubComment subComment = subCommentOptional.orElse(null);

        Optional<Comment> comment = commentRepository.findById(commentId);
        assert comment.orElse(null) != null;
        comment.orElse(null).removeChildren(subComment);

        subCommentRepository.deleteById(subCommentId);
    }
}
