package com.sphiryecode.twitterdb.controller;

import com.sphiryecode.twitterdb.entity.Resource;
import com.sphiryecode.twitterdb.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Response;

//Obsolete code, please ignore
@RestController
public class ResourceController {

    @Autowired ResourceService resourceService;

    @PostMapping("/api/resources")
    public ResponseEntity<Resource> create(
            @RequestParam MultipartFile multipartFile,
            @RequestParam String title
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                resourceService.create(title, multipartFile)
        );
    }

    @GetMapping("public/resources/{id}")
    public ResponseEntity<Resource> getResource(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(resourceService.findById(id));
    }

}
