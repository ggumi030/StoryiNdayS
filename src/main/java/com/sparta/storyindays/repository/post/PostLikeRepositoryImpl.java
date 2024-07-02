package com.sparta.storyindays.repository.post;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.storyindays.entity.Post;
import com.sparta.storyindays.entity.PostLike;
import com.sparta.storyindays.entity.QPost;
import com.sparta.storyindays.entity.QPostLike;
import com.sparta.storyindays.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostLikeRepositoryImpl implements PostLikeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long getPostLikeCount(Post post) {
        QPostLike postLike1 = QPostLike.postLike1;

        return jpaQueryFactory
            .select(postLike1.count())
            .from(postLike1)
            .where(postLike1.post.eq(post))
            .where(postLike1.postLike.eq(true))
            .fetchFirst();
    }

    @Override
    public List<Post> getPostILike(User user) {
        QPostLike postLike1 = QPostLike.postLike1;

        return jpaQueryFactory
            .selectFrom(postLike1)
            .where(postLike1.user.eq(user))
            .where(postLike1.postLike.eq(true))
            .fetch()
            .stream()
            .map(PostLike::getPost)
            .toList();
    }

}
