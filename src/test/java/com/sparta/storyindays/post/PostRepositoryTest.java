package com.sparta.storyindays.post;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.storyindays.config.QueryDslConfiguration;
import com.sparta.storyindays.entity.Post;
import com.sparta.storyindays.entity.PostLike;
import com.sparta.storyindays.entity.QPostLike;
import com.sparta.storyindays.entity.QUser;
import com.sparta.storyindays.repository.post.PostRepository;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@Import(QueryDslConfiguration.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTest {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    @DisplayName("단건 게시글 조회시 좋아요 개수 적용 QueryDSL 테스트")
    public void getPostLikeCount_Ok(){
        //given
        QPostLike postLike1 = QPostLike.postLike1;

        //when
        Long count = jpaQueryFactory
            .select(postLike1.count())
            .from(postLike1)
            .where(postLike1.post.id.eq(1L))
            .where(postLike1.postLike.eq(true))
            .fetchFirst();

        //then
        assertEquals(count,1);
    }

    @Test
    @DisplayName("좋아요한 게시물만 조회해오기 QueryDSL 테스트")
    public void getPostILike_Ok() {
        //given
        QPostLike postLike1 = QPostLike.postLike1;
        QUser user = QUser.user;

        //when
        List<Post> posts = jpaQueryFactory
            .selectFrom(postLike1)
            .where(postLike1.user.id.eq(1L))
            .where(postLike1.postLike.eq(true))
            .fetch()
            .stream()
            .map(PostLike::getPost)
            .toList();

        //then
        assertEquals(posts.get(0).getId(),1L);
    }

}
