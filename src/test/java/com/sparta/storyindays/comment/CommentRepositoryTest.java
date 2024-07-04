package com.sparta.storyindays.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.storyindays.config.QueryDslConfiguration;
import com.sparta.storyindays.entity.Comment;
import com.sparta.storyindays.entity.QComment;
import com.sparta.storyindays.entity.QCommentLike;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@Import(QueryDslConfiguration.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CommentRepositoryTest {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    @DisplayName("단건 댓글 조회시 좋아요 개수 적용 QueryDSL 테스트")
    public void getCommentLikeCount_Ok(){
        //given
        QCommentLike commentLike = QCommentLike.commentLike1;

        //when
        Long like = jpaQueryFactory
            .select(commentLike.count())
            .from(commentLike)
            .where(commentLike.comment.id.eq(1L))
            .where(commentLike.commentLike.eq(true))
            .fetchFirst();

        //then
        assertEquals(like,1L);
    }

    @Test
    public void getCommentILike_Ok(){
        //given
        QComment comment1 = QComment.comment1;
        QCommentLike commentLike1 = QCommentLike.commentLike1;

        PageRequest pageRequest = PageRequest.of(0, 5);
        OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(Order.DESC,comment1.createdAt);

        //when
         List<Comment> comments = jpaQueryFactory
            .selectFrom(comment1)
            .leftJoin(commentLike1).on(comment1.id.eq(commentLike1.comment.id))
            .where(commentLike1.user.id.eq(2L)
                .and(commentLike1.commentLike.eq(true)))
            .offset(pageRequest.getOffset())
            .limit(pageRequest.getPageSize())
            .orderBy(orderSpecifier)
            .fetch();

        //then
        assertEquals(comments.get(0).getId(),3L);
        assertEquals(comments.get(1).getId(),2L);
        assertEquals(comments.get(2).getId(),1L);
    }
}
