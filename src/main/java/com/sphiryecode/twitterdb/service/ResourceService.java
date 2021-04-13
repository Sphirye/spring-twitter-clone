package com.sphiryecode.twitterdb.service;

import com.sphiryecode.twitterdb.config.exception.NotFoundException;
import com.sphiryecode.twitterdb.entity.File;
import com.sphiryecode.twitterdb.entity.Media;
import com.sphiryecode.twitterdb.entity.Resource;
import com.sphiryecode.twitterdb.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ResourceService {

    @Autowired ResourceRepository resourceRepository;
    @Autowired FileService fileService;

    public Resource create(String title, MultipartFile multipartFile) {
        if (title.isEmpty() && title.equals("")) {
            throw new IllegalArgumentException();
        }

        if (multipartFile == null) {
            throw new IllegalArgumentException();
        }

        Resource resource = new Resource();
        resource.setTitle(title);
        resource.setFile(
                fileService.create(multipartFile, Media.Type.TWEET_MEDIA, title)
        );

        return resourceRepository.save(resource);

    }

    public Resource findById(Long id) {
        if (!resourceRepository.existsById(id)) {
            throw new NotFoundException();
        }
        return resourceRepository.getOne(id);
    }

}
