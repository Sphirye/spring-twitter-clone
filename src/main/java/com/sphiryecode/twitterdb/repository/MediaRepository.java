package com.sphiryecode.twitterdb.repository;

import com.sphiryecode.twitterdb.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
