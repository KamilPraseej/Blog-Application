package com.application.repository;

import com.application.model.Blog;
import com.application.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {
    public List<Blog> findByUserProfile(UserProfile userProfile);
}
