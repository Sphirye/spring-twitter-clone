package com.sphiryecode.twitterdb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProfilePhoto {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Media media;

    private Boolean isActive;

}
