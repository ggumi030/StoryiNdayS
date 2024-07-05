package com.sparta.storyindays.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.storyindays.config.QueryDslConfiguration;
import com.sparta.storyindays.dto.user.FollowerTop10ProfileResDto;
import com.sparta.storyindays.entity.Post;
import com.sparta.storyindays.entity.QCommentLike;
import com.sparta.storyindays.entity.QFollow;
import com.sparta.storyindays.entity.QPostLike;
import com.sparta.storyindays.entity.QUser;
import java.util.List;
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

    @Test
    @DisplayName("팔로워 수 Top10 회원 프로필 조회 QueryDSL 테스트")
    public void getFollowerTop10Profile(){
        //given
        QFollow follow = QFollow.follow;
        QFollow subFollow = new QFollow("subFollow");
        QUser user = QUser.user;

        JPQLQuery<Long> subQuery = JPAExpressions
            .select(subFollow.count())
            .from(subFollow)
            .where(subFollow.followeeUser.id.eq(user.id)
                .and(subFollow.isFollow.eq(true)));

        //when
        List<FollowerTop10ProfileResDto> followerTop10ProfileResDtos = jpaQueryFactory
            .select(
                Projections.constructor(FollowerTop10ProfileResDto.class,
                    user.username,
                    user.name,
                    user.introduction,
                    ExpressionUtils.as(subQuery,"follower")
                )
            )
            .from(user)
            .leftJoin(follow).on(user.id.eq(follow.followeeUser.id))
            .groupBy(user.username, user.name, user.introduction)
            .orderBy(Expressions.asNumber(subQuery).desc())
            .limit(10)
            .fetch();

        //then
        assertEquals(followerTop10ProfileResDtos.get(0).getFollower(),5);
        assertEquals(followerTop10ProfileResDtos.get(1).getFollower(),4);
        assertEquals(followerTop10ProfileResDtos.get(2).getFollower(),3);
        assertEquals(followerTop10ProfileResDtos.get(3).getFollower(),2);
        assertEquals(followerTop10ProfileResDtos.get(4).getFollower(),1);

    }
}
