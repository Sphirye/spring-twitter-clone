package com.sphiryecode.twitterdb.service;

import com.sphiryecode.twitterdb.config.exception.NotFoundException;
import com.sphiryecode.twitterdb.entity.Media;
import com.sphiryecode.twitterdb.entity.ProfilePhoto;
import com.sphiryecode.twitterdb.entity.User;
import com.sphiryecode.twitterdb.repository.ProfilePhotoRepository;
import com.sphiryecode.twitterdb.repository.UserRepository;
import com.sphiryecode.twitterdb.service.tool.FileTool;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired ProfilePhotoRepository profilePhotoRepository;
    @Autowired UserRepository userRepository;
    @Autowired MediaService mediaService;
    @Autowired FileTool fileToolname;

    @SneakyThrows
    public void init() {
        if (userRepository.findAll().isEmpty()) {

            User user = new User();
            user.setTag("TagExample1");
            user.setUsername("UsernameExample1");
            user.setBio("Bio Example 1");
            userRepository.save(user);

            mediaService.setProfilePhoto(
                    "TagExample1",
                    fileToolname.getStoredMultipartFile("/static/default users/PFP1.png"),
                    "XD"
            );

            mediaService.setProfilePhoto(
                    "TagExample1",
                    fileToolname.getStoredMultipartFile("/static/default users/PFP1.png"),
                    "XD"
            );

            mediaService.setProfilePhoto(
                    "TagExample1",
                    fileToolname.getStoredMultipartFile("/static/default users/PFP1.png"),
                    "XD"
            );

            user = new User();
            user.setTag("TagExample2");
            user.setUsername("UsernameExample2");
            user.setBio("Bio Example 2");
            userRepository.save(user);

            mediaService.setProfilePhoto(
                    "TagExample2",
                    fileToolname.getStoredMultipartFile("/static/default users/PFP2.png"),
                    "XD"
            );

        }
    }

    public User create(String tag, String username, String bio) {

        if (tag.isEmpty() && tag.equals("")) {
            throw new IllegalArgumentException();
        }

        if (username.isEmpty() && username.equals("")) {
            throw new IllegalArgumentException();
        }

        User user = new User();

        user.setTag(tag);
        user.setUsername(username);
        user.setBio(bio);

        return userRepository.save(user);

    }

    public void delete (Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User does not exist");
        }
        userRepository.deleteById(id);
    }

    public User findByTag (String tag) {
        return userRepository.findByTag(tag);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
