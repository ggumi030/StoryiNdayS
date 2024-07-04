package com.sparta.storyindays.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.storyindays.config.QueryDslConfiguration;
import com.sparta.storyindays.entity.Post;
import com.sparta.storyindays.entity.QCommentLike;
import com.sparta.storyindays.entity.QPostLike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@Import(QueryDslConfiguration.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class userProfileTest {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    @DisplayName("profile 조회 시 좋아요한 게시글과 댓글 개수 응답 QueryDSL 테스트")
    public void getPostAndCommentCountILike(){
        //given
        QPostLike postLike1 = QPostLike.postLike1;
        QCommentLike commentLike1 = QCommentLike.commentLike1;

        //when
        Long postCount = jpaQueryFactory
            .select(postLike1.count())
            .from(postLike1)
            .where(postLike1.user.id.eq(2L)
                .and(postLike1.postLike.eq(true)))
            .fetchFirst();

        Long commentCount = jpaQueryFactory
            .select(commentLike1.count())
            .from(commentLike1)
            .where(commentLike1.user.id.eq(2L)
                .and(commentLike1.commentLike.eq(true)))
            .fetchFirst();

        int intPostCount = postCount != null ? postCount.intValue() : 0;
        int intCommentCount = commentCount != null ? commentCount.intValue() : 0;

        //then
        assertEquals(intPostCount,3);
        assertEquals(intCommentCount,3);
    }
}
