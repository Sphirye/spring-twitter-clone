package com.sphiryecode.twitterdb.service;

import com.sphiryecode.twitterdb.config.exception.NotFoundException;
import com.sphiryecode.twitterdb.entity.Media;
import com.sphiryecode.twitterdb.entity.ProfilePhoto;
import com.sphiryecode.twitterdb.repository.ProfilePhotoRepository;
import com.sphiryecode.twitterdb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProfilePhotoService {

    @Autowired UserRepository userRepository;
    @Autowired ProfilePhotoRepository profilePhotoRepository;

    public ProfilePhoto create(String userTag, Media media) {

        ProfilePhoto profilePhoto = new ProfilePhoto();
        profilePhoto.setMedia(media);
        profilePhoto.setUser(userRepository.findByTag(userTag));
        profilePhoto.setIsActive(activate(userTag));

        return profilePhotoRepository.save(profilePhoto);
    }

    public boolean activate(String userTag) {

        for(ProfilePhoto pfp : findAllByUser(userTag)) {
            pfp.setIsActive(false);
        }

        return true;
    }

    public List<ProfilePhoto> findAllByUser(String userTag) {

        if (!userRepository.existsByTag(userTag)) {
            throw new NotFoundException("User does not exist.");
        }

        return profilePhotoRepository.findByUser(userRepository.findByTag(userTag));
    }

    public ProfilePhoto findActiveByUser(String userTag) {

        ProfilePhoto active = null;

        for(ProfilePhoto profilePhoto : findAllByUser(userTag)) {
            if (profilePhoto.getIsActive()) {
                active = profilePhoto;
                break;
            }
        }

        if (active != null) { return active; }
        else {
            throw new NotFoundException("Active profile photo not found.");
        }
    }

}
