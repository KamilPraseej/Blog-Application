package com.application.controller;

import com.application.model.Blog;
import com.application.model.UserProfile;
import com.application.service.BlogService;
import com.application.service.EmailService;
import com.application.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserProfileService userProfileService;


    @GetMapping("/all-blogs/{email:.+}")
    public List<Blog> getAllBlogs(@PathVariable String email) {
        UserProfile user = userProfileService.getUserProfileByEmail(email);

        if (user != null) {
            return blogService.getBlogsByUserProfile(user);
        } else {
            return Collections.emptyList();
        }
    }

    @PostMapping("/create-blog/{email:.+}")
    public Blog createBlog(@PathVariable String email,@RequestBody Blog blog) {
        UserProfile user = userProfileService.getUserProfileByEmail(email);
        Blog savedBlog = blogService.createBlog(blog, user);
        return savedBlog;
    }

    @PostMapping("/helpdesk/{email:.+}")
    public ResponseEntity<?> submitHelpDeskMessage(@PathVariable String email, @RequestBody Map<String, String> request) {
        String message = request.get("message");
        UserProfile user = userProfileService.getUserProfileByEmail(email);
        emailService.sendSuggestionEmail(user, message);
        return ResponseEntity.ok("Message received successfully");
    }
}
