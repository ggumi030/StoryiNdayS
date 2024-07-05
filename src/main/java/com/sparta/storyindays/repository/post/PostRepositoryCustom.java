package com.sparta.storyindays.repository.post;

import com.sparta.storyindays.entity.Post;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositoryCustom {
    List<Post> getPostWithPageAndSortCreatedAtDesc(long offset, int pageSize);
}
