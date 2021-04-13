package com.sphiryecode.twitterdb.repository;

import com.sphiryecode.twitterdb.entity.ProfilePhoto;
import com.sphiryecode.twitterdb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfilePhotoRepository extends JpaRepository<ProfilePhoto, Long> {

    List<ProfilePhoto> findByUser(User user);

}