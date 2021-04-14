package com.sphiryecode.twitterdb.service;

import com.sphiryecode.twitterdb.config.exception.NotFoundException;
import com.sphiryecode.twitterdb.entity.Tweet;
import com.sphiryecode.twitterdb.repository.TweetRepository;
import com.sphiryecode.twitterdb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TweetService {

    @Autowired TweetRepository tweetRepository;
    @Autowired UserRepository userRepository;

    public void init() {
        if (tweetRepository.findAll().isEmpty()) {
            Tweet tweet = new Tweet();
            tweet.setBody("Tweet 1");
            tweet.setTweetUser(userRepository.findByTag("TagExample1"));
            tweetRepository.save(tweet);

            tweet = new Tweet();
            tweet.setBody("Tweet 2");
            tweet.setTweetUser(userRepository.findByTag("TagExample2"));
            tweetRepository.save(tweet);

            tweet = new Tweet();
            tweet.setBody("Tweet 3 that aparently is a tweet 1 reply, looks great, dont you think? In deserunt exercitation mollit dolore pariatur labore exercitation sit reprehenderit magna reprehenderit anim do non ut amet duis in in culpa enim esse quis cons");
            tweet.setTweetUser(userRepository.findByTag("TagExample2"));
            tweet.setRepliedTweet(findById(1L));
            tweetRepository.save(tweet);
        }
    }

    public Tweet create(Long repliedTweetId, String body) {

        Tweet tweet = new Tweet();

        if (repliedTweetId != null) {
            if (!tweetRepository.existsById(repliedTweetId)) {
                throw new NotFoundException("Tweet does not exist");
            }
            tweet.setRepliedTweet(findById(repliedTweetId));
        }

        if (body.isEmpty() && body.equals("")) {
            throw new IllegalArgumentException();
        }

        tweet.setBody(body);
        return tweetRepository.save(tweet);

    }

    public void delete (Long id) {
        if (!tweetRepository.existsById(id)) {
            throw new NotFoundException("Tweet does not exist");
        }
        tweetRepository.deleteById(id);
    }

    public List<Tweet> findAll() {
        return tweetRepository.findAll();
    }

    public Tweet findById(Long id) {
        if (!tweetRepository.existsById(id)) {
            throw new NotFoundException("Tweet does not exist");
        }
        return tweetRepository.getOne(id);
    }

}
