package com.sparta.storyindays.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.storyindays.dto.user.FollowerTop10ProfileResDto;
import com.sparta.storyindays.entity.QFollow;
import com.sparta.storyindays.entity.QUser;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryCustomImpl implements FollowRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<FollowerTop10ProfileResDto> getFollowerTop10Profile() {
        QFollow follow = QFollow.follow;
        QFollow subFollow = new QFollow("subFollow");
        QUser user = QUser.user;

        JPQLQuery<Long> subQuery = JPAExpressions
            .select(subFollow.count())
            .from(subFollow)
            .where(subFollow.followeeUser.id.eq(user.id)
                .and(subFollow.isFollow.eq(true)));

        return jpaQueryFactory
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
    }
}
