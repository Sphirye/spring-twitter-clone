package com.sphiryecode.twitterdb.controller;

import com.sphiryecode.twitterdb.entity.Media;
import com.sphiryecode.twitterdb.entity.ProfilePhoto;
import com.sphiryecode.twitterdb.service.MediaService;
import com.sphiryecode.twitterdb.service.ProfilePhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
public class MediaController {

    @Autowired MediaService mediaService;
    @Autowired ProfilePhotoService profilePhotoService;

    @GetMapping("/api/user/{userTag}/profile/photo/")
    public ResponseEntity<List<ProfilePhoto>> getProfilePhotos(@PathVariable String userTag) {
        return ResponseEntity.status(HttpStatus.OK).body(profilePhotoService.findAllByUser(userTag));
    }

    @GetMapping("/api/user/{userTag}/profile/photo/active")
    public ResponseEntity<ProfilePhoto> getActiveProfilePhoto(@PathVariable String userTag) {
        return ResponseEntity.status(HttpStatus.OK).body(profilePhotoService.findActiveByUser(userTag));
    }

    @PostMapping("/api/user/{userTag}/profile/photo")
    public ResponseEntity<Media> setProfilePhoto(
            @PathVariable String userTag,
            @RequestParam MultipartFile multipartFile,
            @RequestParam String title
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mediaService.setProfilePhoto(userTag, multipartFile, title));
    }
}