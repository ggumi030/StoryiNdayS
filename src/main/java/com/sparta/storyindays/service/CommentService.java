package com.sparta.storyindays.service;

import com.sparta.storyindays.dto.comment.CommentCreateReqDto;
import com.sparta.storyindays.dto.comment.CommentResDto;
import com.sparta.storyindays.dto.comment.CommentUpdateReqDto;
import com.sparta.storyindays.entity.Comment;
import com.sparta.storyindays.entity.Post;
import com.sparta.storyindays.entity.User;
import com.sparta.storyindays.enums.user.Auth;
import com.sparta.storyindays.repository.comment.CommentLikeRepository;
import com.sparta.storyindays.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final CommentLikeRepository commentLikeRepository;
    private final MessageSource messageSource;

    @Transactional
    public CommentResDto createComment(long postId, CommentCreateReqDto reqDto, User user) {

        Post post = postService.findById(postId);
        Comment comment = commentRepository.save(new Comment(reqDto.getComment(), post, user));
        return new CommentResDto(comment.getId(), comment.getUser().getUsername(), comment.getComment(), comment.getCreatedAt());
    }

    public CommentResDto getComment(long postId, long commentId) {

        Post post = postService.findById(postId);
        Comment comment = findComment(commentId);
        int commentLikeCount = commentLikeRepository.getCommentLikeCount(comment);

        return new CommentResDto(comment.getId(), comment.getUser().getUsername(), comment.getComment(), comment.getCreatedAt(),commentLikeCount);
    }

    public List<CommentResDto> getAllComment(long postId) {

        postService.findById(postId);
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        List<CommentResDto> commentResDtos = new ArrayList<>();
        for (Comment comment : comments) {
            commentResDtos.add(new CommentResDto(comment.getId(), comment.getUser().getUsername(), comment.getComment(), comment.getCreatedAt()));
        }
        return commentResDtos;
    }

    public List<CommentResDto> getLikeComment(User user, int page, int size) {
        List<Comment> comments = commentLikeRepository.getCommentILike(user,page,size);

        return comments
            .stream()
            .map(comment-> new CommentResDto(comment.getId(),comment.getUser().getUsername(),comment.getComment(),comment.getCreatedAt()))
            .toList();
    }

    @Transactional
    public CommentResDto updateComment(long postId, long commentId, CommentUpdateReqDto reqDto, User user) {

        postService.findById(postId);
        Comment comment = findComment(commentId);
        String loginUsername = user.getUsername();
        String commentUsername = comment.getUser().getUsername();

        if (!loginUsername.equals(commentUsername)) {
            throw new IllegalArgumentException(messageSource.getMessage(
                    "author.and.user.mismatch",
                    null,
                    "User Mismatch",
                    Locale.getDefault()
            ));
        }

        comment.updateComment(reqDto.getComment());

        return new CommentResDto(comment.getId(), comment.getUser().getUsername(), comment.getComment(), comment.getCreatedAt());
    }

    @Transactional
    public void deleteComment(long postId, long commentId, User user) {

        postService.findById(postId);
        Comment comment = findComment(commentId);
        String loginUsername = user.getUsername();
        String commentUsername = comment.getUser().getUsername();

        if (!loginUsername.equals(commentUsername)) {
            throw new IllegalArgumentException(messageSource.getMessage(
                    "author.and.user.mismatch",
                    null,
                    "User Mismatch",
                    Locale.getDefault()
            ));
        }
        commentRepository.delete(comment);
    }

    @Transactional
    public CommentResDto updateCommentAdmin(long postId, long commentId, CommentUpdateReqDto reqDto, User user) {
        postService.findById(postId);
        Comment comment = findComment(commentId);
        Auth loginAuth = user.getAuth();

        if (!loginAuth.equals(Auth.ADMIN)) {
            throw new IllegalArgumentException(messageSource.getMessage(
                    "only.admin.can.edit",
                    null,
                    "Only Admin Can Edit",
                    Locale.getDefault()
            ));
        }

        comment.updateComment(reqDto.getComment());

        return new CommentResDto(comment.getId(), comment.getUser().getUsername(), comment.getComment(), comment.getCreatedAt());
    }

    @Transactional
    public void deleteCommentAdmin(long postId, long commentId, User user) {

        postService.findById(postId);
        Comment comment = findComment(commentId);
        Auth loginAuth = user.getAuth();

        if (!loginAuth.equals(Auth.ADMIN)) {
            throw new IllegalArgumentException(messageSource.getMessage(
                    "only.admin.can.delete",
                    null,
                    "Only Admin Can Delete",
                    Locale.getDefault()
            ));
        }
        commentRepository.delete(comment);
    }

    public Comment findComment(long commentId) {

        return commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException(messageSource.getMessage(
                        "not.found.commentid",
                        null,
                        "Not Found CommentId",
                        Locale.getDefault()
                )));
    }
}
