package com.example.reddit_rep.web;

import com.example.reddit_rep.domain.Comment;
import com.example.reddit_rep.domain.Feature;
import com.example.reddit_rep.domain.SubComment;
import com.example.reddit_rep.domain.User;
import com.example.reddit_rep.service.CommentService;
import com.example.reddit_rep.service.FeatureService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/product/id/{productId}/feature/id/{featureId}/comment")
public class CommentController {

    private final CommentService commentService;
    private final FeatureService featureService;

    public CommentController(CommentService commentService, FeatureService featureService) {
        this.commentService = commentService;
        this.featureService = featureService;
    }

    @PostMapping("/")
    public String addComment(@AuthenticationPrincipal User user, @RequestParam(name = "content") String content,
                             @PathVariable Long featureId, @PathVariable Long productId) {
        Optional<Feature> featureOptional = featureService.findById(featureId);

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setFeature(featureOptional.orElse(null));
        comment.setCreatedDate(new Date());
        comment.setUser(user);

        comment = commentService.saveComment(comment);

        return "redirect:/product/id/" + productId + "/feature/id/" + featureId;
    }

    @PostMapping("/subcomment")
    public String addSubComment(@AuthenticationPrincipal User user, @PathVariable Long featureId, @PathVariable Long productId,
                                @RequestParam(name = "parent_id") Long parentId, @RequestParam(name = "content") String content) {

        Optional<Feature> featureOptional = featureService.findById(featureId);

        SubComment subComment = new SubComment();
        subComment.setContent(content);
        subComment.setCreatedDate(new Date());
        subComment.setUser(user);


        return "redirect:/product/id/" + productId + "/feature/id/" + featureId;
    }
}
