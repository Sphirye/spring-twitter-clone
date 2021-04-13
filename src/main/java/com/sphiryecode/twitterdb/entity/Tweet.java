package com.sphiryecode.twitterdb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Tweet implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    @JsonIgnore
    @OneToMany(mappedBy = "repliedTweet")
    private List<Tweet> replies = new ArrayList<>();

    @ManyToOne
    private Tweet repliedTweet;

    @ManyToOne
    private User tweetUser;

}
