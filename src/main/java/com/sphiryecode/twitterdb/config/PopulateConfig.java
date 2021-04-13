package com.sphiryecode.twitterdb.config;

import com.sphiryecode.twitterdb.service.TweetService;
import com.sphiryecode.twitterdb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class PopulateConfig {

    @Autowired UserService userService;
    @Autowired TweetService tweetService;

    @PostConstruct
    public void populateDatabase() {
        userService.init();
        tweetService.init();
    }
}