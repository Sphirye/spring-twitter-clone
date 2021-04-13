package com.sphiryecode.twitterdb.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sphiryecode.twitterdb.service.tool.StorageSerialize;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class File {

    public enum Type {
        IMAGE, VIDEO
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonSerialize(using = StorageSerialize.class)
    private String url;

    private String name;
    private String baseName;
    private String extension;
    private String title;

    @Enumerated(EnumType.STRING)
    private Type type;

}
