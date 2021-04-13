package com.sphiryecode.twitterdb.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Resource {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    public enum Type {
        PROFILE_PHOTO, TWEET_MEDIA
    }

    @OneToOne
    private File file;

}
