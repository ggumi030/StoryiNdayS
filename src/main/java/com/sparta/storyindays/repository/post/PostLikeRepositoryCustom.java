package com.sparta.storyindays.repository.post;

import com.sparta.storyindays.entity.Post;
import com.sparta.storyindays.entity.User;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepositoryCustom {
    Long getPostLikeCount(Post post);
    List<Post> getPostILike(User user, int page, int size);
}
