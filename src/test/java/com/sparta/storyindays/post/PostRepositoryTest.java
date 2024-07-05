package com.sparta.storyindays.post;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.storyindays.config.QueryDslConfiguration;
import com.sparta.storyindays.entity.Post;
import com.sparta.storyindays.entity.QPost;
import com.sparta.storyindays.entity.QPostLike;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@Import(QueryDslConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTest {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    @DisplayName("단건 게시글 조회시 좋아요 개수 적용 QueryDSL 테스트")
    public void getPostLikeCount_Ok() {
        //given
        QPostLike postLike1 = QPostLike.postLike1;

        //when
        Long count = jpaQueryFactory
            .select(postLike1.count())
            .from(postLike1)
            .where(postLike1.post.id.eq(1L))
            .where(postLike1.postLike.eq(true))
            .fetchFirst();

        int intCount = count != null ? count.intValue() : 0;

        //then
        assertEquals(intCount, 1);
    }

    @Test
    @DisplayName("좋아요한 게시물만 페이징 조회해오기 QueryDSL 테스트")
    public void getPostILike_Ok() {
        //given
        QPostLike postLike1 = QPostLike.postLike1;
        QPost post = QPost.post;

        PageRequest pageRequest = PageRequest.of(0, 5);
        OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(Order.DESC, post.createdAt);

        //when
        List<Post> posts = jpaQueryFactory
            .selectFrom(post)
            .leftJoin(postLike1).on(post.id.eq(postLike1.post.id))
            .where(postLike1.user.id.eq(2L)
                .and(postLike1.postLike.eq(true)))
            .offset(pageRequest.getOffset())
            .limit(pageRequest.getPageSize())
            .orderBy(orderSpecifier)
            .fetch();

        //then
        assertEquals(posts.get(0).getId(), 3L);
        assertEquals(posts.get(1).getId(), 2L);
        assertEquals(posts.get(2).getId(), 1L);
    }

}
