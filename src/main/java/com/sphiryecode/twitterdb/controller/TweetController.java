package com.sphiryecode.twitterdb.controller;

import com.sphiryecode.twitterdb.entity.Tweet;
import com.sphiryecode.twitterdb.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TweetController {

    @Autowired TweetService tweetService;

    @GetMapping("/public/tweets")
    public ResponseEntity<List<Tweet>> getTweets() {
        return ResponseEntity.status(HttpStatus.OK).body(tweetService.findAll());
    }

    @GetMapping("/public/tweets/{id}")
    public ResponseEntity<Tweet> getTweet(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(tweetService.findById(id));
    }

    @PostMapping("/api/tweets")
    public ResponseEntity<Tweet> create(
            @RequestParam (required = false) Long repliedTweetId,
            @RequestParam String body
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tweetService.create(repliedTweetId, body));
    }

    @DeleteMapping("/api/tweets/{id}")
    public ResponseEntity<Tweet> delete(@PathVariable Long id) {
        tweetService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /*@GetMapping(value = {"/public/pathtesting", "/public/pathtesting/{path}"})
    public String bruh(@PathVariable(required = false) String path) {
        if (path == null) {
            System.out.println("Path is empty");
        }
        return path;
    }*/

}
