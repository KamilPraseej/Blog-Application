package com.application.controller;

import com.application.model.UserProfile;
import com.application.service.UserProfileService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin("*")
@Controller
@RequestMapping("/api/v1/user")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @Getter
    @Autowired
    private HttpServletRequest request;


//    @GetMapping("/public/home")
//
//    public String loginForm(){
//        return "home";
//    }
    /**
     * Method used for view all users
     * Allowed to both admin and user
     * @return
     */
    @GetMapping("/view-users")
    public String showUser(Model model){
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        List<UserProfile> userProfile = userProfileService.getAllUsers();
        model.addAttribute("users",userProfile);
        return "view-users";
    }
    @GetMapping("/user/view-all-users")
    public ResponseEntity<List<UserProfile>> getAllUsers() {
        List<UserProfile> userProfiles = userProfileService.getAllUsers();
        return new ResponseEntity<>(userProfiles, HttpStatus.OK);
    }

    /**
     * method used for view userProfile using id
     * restricted to admin only
//     * @param id
     * @return
     */

    @GetMapping("/view-userProfile/{email:.+}")
    public ResponseEntity<UserProfile> getUserByEmail(@PathVariable String email) {
        UserProfile userProfile = userProfileService.getUserProfileByEmail(email);
        if (userProfile != null) {
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * method used for add new userProfile
     * restricted to admin only
     * @param userProfile
     * @return
     */
    @PostMapping("/add-userProfile")
    public ResponseEntity<String > createUserProfile(@RequestBody UserProfile userProfile) {
        UserProfile createdUser = userProfileService.createNewUser(userProfile);
        return ResponseEntity.ok("User created successfully");
    }

    /**
     * Method used for update userProfile details using id
     * restricted to admin only
     * @param id
     * @param userProfile
     * @return
     */
    @PutMapping("/update-userProfile/{id}")
    public ResponseEntity<String> updateUserProfile(@PathVariable long id, @RequestBody UserProfile userProfile) {
        UserProfile updatedUserProfile = userProfileService.updateUserDetails(id, userProfile);
        if (updatedUserProfile != null) {
            return ResponseEntity.ok("UserDetails updated successfully");
        } else {
            return ResponseEntity.ok("!Sorry User not existed with this Id");
        }
    }

    /**
     * Method used for delete userProfile details
     * restricted to admin only
     * @param id
     * @return
     */
    @DeleteMapping("/delete-userProfile/{id}")
    public ResponseEntity<String> deleteUserProfile(@PathVariable long id) {
        boolean deleted = userProfileService.deleteUserProfile(id);
        if (deleted) {
            return ResponseEntity.ok("UserProfile deleted successfully");
        } else {
            return ResponseEntity.ok("!Sorry UserProfile not existed with this Id");
        }
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    request.logout();
    Cookie[] list=request.getCookies();
     for (Cookie cookie:list){
         Cookie cookie1=new Cookie(cookie.getName(),null);
         cookie1.setMaxAge(0);
         cookie1.setPath("/");
         response.addCookie(cookie1);
     }
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
        response.setHeader("Expires", "0");
    request.getSession().invalidate();
    return new RedirectView("/app/api/public/home", true);
    }

}
