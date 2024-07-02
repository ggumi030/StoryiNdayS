package com.sparta.storyindays.repository.comment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.storyindays.entity.Comment;
import com.sparta.storyindays.entity.QCommentLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentLikeRepositoryImpl implements CommentLikeRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long getCommentLikeCount(Comment comment){
        QCommentLike commentLike = QCommentLike.commentLike1;

        return jpaQueryFactory
            .select(commentLike.count())
            .from(commentLike)
            .where(commentLike.comment.eq(comment))
            .where(commentLike.commentLike.eq(true))
            .fetchFirst();
    }
}
