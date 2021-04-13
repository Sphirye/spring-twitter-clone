package com.sphiryecode.twitterdb.service.tool;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class StorageTool {

    private static final String STORAGE_PATH = "/static/storage";

    public void save(MultipartFile multipartFile, String folder, String name) {
        try {
            if (multipartFile.isEmpty()) {
                throw new IllegalArgumentException("Error multipartFile is empty");
            }
            String originalFilename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            if (originalFilename.contains("..")) {
                throw new IllegalArgumentException("Cannot store file with relative path outside current directory " + originalFilename);
            }

            Path path = Paths.get(getRootPath() + STORAGE_PATH + (folder == null ? "" : folder));
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Path resolve = path.resolve(StringUtils.cleanPath(name != null ? name : multipartFile.getOriginalFilename()));
            Files.copy(multipartFile.getInputStream(), resolve, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getRootPath() {
        Path path = Paths.get("").toAbsolutePath();
        return path.toString().replace("\\", "/");
    }

}