//package com.sparta.storyindays.post;
//
//import com.sparta.storyindays.entity.Comment;
//import com.sparta.storyindays.entity.Post;
//import com.sparta.storyindays.entity.User;
//import com.sparta.storyindays.repository.comment.CommentRepository;
//import com.sparta.storyindays.repository.post.PostLikeRepository;
//import com.sparta.storyindays.service.PostService;
//import java.time.LocalDateTime;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.context.ActiveProfiles;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ActiveProfiles("test")
//@ExtendWith(MockitoExtension.class)
//class PostServiceTest {
//
//    @Mock
//    PostLikeRepository postLikeRepository;
//
//    @Mock
//    CommentRepository commentRepository;
//
//    @InjectMocks
//    PostService postService;
//
//    @Mock
//    Post post;
//
//    @Mock
//    Comment comment;
//
//    @Mock
//    User user;
//
//    @Test
//    @DisplayName("게시글 작성")
//    void writePost() {
//    }
//
//
//    @Nested
//    @DisplayName("게시글 조회")
//    class Test1 {
//        @Test
//        @DisplayName("게시글 조회 전체")
//        void getAllPost() {
//        }
//
//        @Test
//        @DisplayName("특정 유저의 게시글 조회")
//        void getUserPost() {
//        }
//
//        @Test
//        @DisplayName("게시글 단건 조회 with 좋아요 개수")
//        void getPost_ok_with_postLikeCount() {
//            //given
//            List<Comment> comments = Collections.emptyList();
//            when(postService.findById(any(Long.class))).thenReturn(post);
//            when(commentRepository.findAllByPostId(any(Long.class))).thenReturn(comments);
//            when(comment.getId()).thenReturn(1L);
//            when(comment.getUser()).thenReturn(user);
//            when(user.getUsername()).thenReturn("ggumi");
//            when(comment.getComment()).thenReturn("comment");
//            when(comment.getCreatedAt()).thenReturn(LocalDateTime.now());
//
//            //when
//
//
//            //then
//        }
//
//        @Test
//        @DisplayName("좋아요 한 게시글 조회")
//        void getLikePost_ok() {
//            //given
//
//            //when
//
//            //then
//        }
//    }
//
//    @Test
//    void updatePost() {
//    }
//
//    @Test
//    void deletePost() {
//    }
//
//    @Test
//    void findById() {
//    }
//
//    @Test
//    void getPageable() {
//    }
//
//    @Test
//    void validUserCheckAndGetUser() {
//    }
//}