package com.sphiryecode.twitterdb.service;

import com.sphiryecode.twitterdb.config.exception.NotFoundException;
import com.sphiryecode.twitterdb.entity.File;
import com.sphiryecode.twitterdb.entity.Media;
import com.sphiryecode.twitterdb.entity.ProfilePhoto;
import com.sphiryecode.twitterdb.repository.MediaRepository;
import com.sphiryecode.twitterdb.repository.ProfilePhotoRepository;
import com.sphiryecode.twitterdb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//Service to ident multipart files with an appropriate context.
@Service
@Transactional
public class MediaService {

    @Autowired MediaRepository mediaRepository;
    @Autowired FileService fileService;
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    @Autowired ProfilePhotoRepository profilePhotoRepository;
    @Autowired ProfilePhotoService profilePhotoService;

    public Media setProfilePhoto(String userTag, MultipartFile multipartFile, String title) {

        if (!userRepository.existsByTag(userTag)) {
            throw new NotFoundException("User with '" + userTag + "' tag not founded");
        }

        //Guardar imagen
        Media media = new Media();
        media.setType(Media.Type.PROFILE_PHOTO);
        media.setTitle(title);
        media.setFile(
                fileService.create(multipartFile, Media.Type.PROFILE_PHOTO, title)
        );

        profilePhotoService.create(userTag, media);

        return mediaRepository.save(media);

    }

}
