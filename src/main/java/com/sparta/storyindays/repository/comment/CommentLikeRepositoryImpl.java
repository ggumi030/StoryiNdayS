package com.sparta.storyindays.repository.comment;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.storyindays.entity.Comment;
import com.sparta.storyindays.entity.QComment;
import com.sparta.storyindays.entity.QCommentLike;
import com.sparta.storyindays.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentLikeRepositoryImpl implements CommentLikeRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public int getCommentLikeCount(Comment comment){
        QCommentLike commentLike = QCommentLike.commentLike1;

        Long count = jpaQueryFactory
            .select(commentLike.count())
            .from(commentLike)
            .where(commentLike.comment.eq(comment))
            .where(commentLike.commentLike.eq(true))
            .fetchFirst();

        return count != null ? count.intValue() : 0;
    }

    @Override
    public List<Comment> getCommentILike(User user, int page, int size){
        QComment comment1 = QComment.comment1;
        QCommentLike commentLike1 = QCommentLike.commentLike1;

        PageRequest pageRequest = PageRequest.of(page, size);
        OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(Order.DESC,comment1.createdAt);

        return jpaQueryFactory
            .selectFrom(comment1)
            .leftJoin(commentLike1).on(comment1.id.eq(commentLike1.comment.id))
            .where(commentLike1.user.eq(user)
                .and(commentLike1.commentLike.eq(true)))
            .offset(pageRequest.getOffset())
            .limit(pageRequest.getPageSize())
            .orderBy(orderSpecifier)
            .fetch();

    }

    @Override
    public int getCommentCountILike(User user){
        QCommentLike commentLike1 = QCommentLike.commentLike1;

        Long count = jpaQueryFactory
            .select(commentLike1.count())
            .from(commentLike1)
            .where(commentLike1.user.eq(user)
                .and(commentLike1.commentLike.eq(true)))
            .fetchFirst();

        return count != null ? count.intValue() : 0;
    }
}
