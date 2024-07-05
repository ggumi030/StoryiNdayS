package com.sparta.storyindays.repository.comment;

import com.sparta.storyindays.entity.Comment;
import com.sparta.storyindays.entity.User;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepositoryCustom {
    int getCommentLikeCount(Comment comment);
    List<Comment> getCommentILike(User user,int page, int size);
    int getCommentCountILike(User user);
}
