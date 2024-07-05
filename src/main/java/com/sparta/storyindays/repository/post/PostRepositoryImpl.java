package com.sparta.storyindays.repository.post;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.storyindays.entity.Post;
import com.sparta.storyindays.entity.QPost;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> getPostWithPageAndSortCreatedAtDesc(long offset, int pageSize) {
        QPost post = QPost.post;

        OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(Order.DESC, post.createdAt);

        return jpaQueryFactory
            .selectFrom(post)
            .offset(offset)
            .limit(pageSize)
            .orderBy(orderSpecifier)
            .fetch();
    }
}
