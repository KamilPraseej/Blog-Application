package com.application.service;

import com.application.model.UserProfile;
import org.springframework.stereotype.Service;
import com.application.model.Blog;
import com.application.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Optional<Blog> getBlogById(long id) {
        return blogRepository.findById(id);
    }

    public Blog createBlog(Blog blog, UserProfile user) {

        blog.setUserProfile(user);
        return blogRepository.save(blog);
    }

    public Blog updateBlog(long id, Blog blog) {
        if (blogRepository.existsById(id)) {
            blog.setBlogId(id);
            return blogRepository.save(blog);
        } else {
            // Handle not found or return an error.
            return null;
        }
    }

    public List<Blog> getBlogsByUserProfile(UserProfile userProfile) {
        // Replace 'userProfile' and 'user' with the actual fields and relationships in your entity
        return blogRepository.findByUserProfile(userProfile);
    }


    public void deleteBlog(long id) {
        blogRepository.deleteById(id);
    }
}
