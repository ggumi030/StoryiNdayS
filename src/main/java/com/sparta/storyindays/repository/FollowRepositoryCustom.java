package com.sparta.storyindays.repository;

import com.sparta.storyindays.dto.user.FollowerTop10ProfileResDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepositoryCustom {
    List<FollowerTop10ProfileResDto> getFollowerTop10Profile();
}
