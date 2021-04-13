package com.sphiryecode.twitterdb.service.tool;

import com.sphiryecode.twitterdb.config.exception.NotFoundException;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.PathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class FileTool {

    public MultipartFile getStoredMultipartFile(String path) throws IOException {

        String homeDirectory = System.getProperty("user.dir");

        File file = new PathResource(homeDirectory + path).getFile();

        if (!file.exists()) {
            throw new NotFoundException("File does not exist");
        }

        FileInputStream input = new FileInputStream(file);

        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), null, IOUtils.toByteArray(input));

        return multipartFile;

    }

}
