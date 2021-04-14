package com.sphiryecode.twitterdb.service;

import com.sphiryecode.twitterdb.entity.File;
import com.sphiryecode.twitterdb.entity.Media;
import com.sphiryecode.twitterdb.repository.FileRepository;
import com.sphiryecode.twitterdb.service.tool.StorageTool;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Service to create/save multipart files in the database (normally as media entities)
@Service
@Transactional
public class FileService {

    @Autowired FileRepository fileRepository;
    @Autowired StorageTool storageTool;

    public File create(MultipartFile multipartFile, Media.Type type, String title) {
        File file = createEntity(multipartFile, type, title);
        saveFile(multipartFile, file);
        return file;
    }

    private void saveFile(MultipartFile multipartFile, File file) {
        storageTool.save(multipartFile, getFolderFromType(file.getType()), file.getName());
    }

    private File createEntity(MultipartFile multipartFile, Media.Type type, String title) {

        if (!FileService.isValidFile(multipartFile) || type == null) {
            throw new IllegalArgumentException();
        }

        String originalFilename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String name = FilenameUtils.getBaseName(originalFilename);
        String extension = FilenameUtils.getExtension(originalFilename);

        File file = new File();

        fileRepository.save(file);

        long millis = System.currentTimeMillis();
        String ext = extension != null && !extension.trim().isEmpty() ? "." + extension : "";

        file.setBaseName(name);
        file.setExtension(extension);
        file.setName(file.getId() + "F" + millis + ext);

        if ((type.toString() == "PROFILE_PHOTO")) {
            if (!isSupportedFormat(extension, Stream.of("png", "jpg", "jpeg").collect(Collectors.toList()))) {
                throw new IllegalArgumentException("File does not match with context");
            }
        }

        if ((type.toString() == "TWEET_MEDIA")) {
            if (!isSupportedFormat(extension, Stream.of("png", "jpg", "jpeg", "mp4").collect(Collectors.toList()))) {
                throw new IllegalArgumentException("File does not match with context");
            }
        }

        file.setType(File.Type.IMAGE);
        String folder = getFolderFromType(file.getType());
        System.out.println(folder);
        file.setUrl(folder + java.io.File.separator + file.getName());

        return file;

    }

    private static String getFolderFromType(File.Type type) {
        if (type == null) return "";
        return "/" + type.toString().toLowerCase();
    }

    public static boolean isValidFile(MultipartFile file) {
        if (file == null || file.isEmpty() || StringUtils.cleanPath(file.getOriginalFilename()).contains("..")) {
            return false;
        }
        return true;
    }

    public static boolean isSupportedFormat(String extension, List<String> supportedFormats) {

        Boolean isSupportedFormat = false;

        for (int i = 0; i < supportedFormats.size(); i++) {
            if (extension.equals(supportedFormats.get(i))) {
                isSupportedFormat = true;
            }
        }

        return isSupportedFormat;
    }

}
