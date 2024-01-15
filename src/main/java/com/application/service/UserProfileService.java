package com.application.service;

import com.application.model.UserProfile;
import com.application.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    /**
     * Method for fetch all userProfile details from database through repository
     * @return
     */
    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }

    /**
     * Method for fetch userProfile details using id from database through repository
     * @param id
     * @return
     */
    public UserProfile getUserProfileById(long id) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(id);
        return userProfile.orElse(null);
    }

    public UserProfile getUserProfileByEmail(String email) {
        Optional<UserProfile> userProfile = Optional.ofNullable(userProfileRepository.findByEmailId(email));
        return userProfile.orElse(null);
    }

    /**
     * Method for add userProfile details from database through repository
     * @param userProfile
     * @return
     */
    public UserProfile createNewUser(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    public UserProfile updateUserDetails(long id, UserProfile userProfile) {
        if (userProfileRepository.existsById(id)) {
            userProfile.setUserId(id);
            return userProfileRepository.save(userProfile);
        } else {
            return null;
        }
    }

    /**
     * Method for delete userProfile details from database through repository
     * @param id
     * @return
     */
    public boolean deleteUserProfile(long id) {
        if (userProfileRepository.existsById(id)) {
            userProfileRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
