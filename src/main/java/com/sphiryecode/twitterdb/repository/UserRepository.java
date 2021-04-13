package com.sphiryecode.twitterdb.repository;

import com.sphiryecode.twitterdb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByTag(String tag);
    User findByTag(String tag);
}