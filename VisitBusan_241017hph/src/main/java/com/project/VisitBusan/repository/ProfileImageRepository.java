package com.project.VisitBusan.repository;

import com.project.VisitBusan.entity.Member;
import com.project.VisitBusan.entity.ProfileImage;
//import io.swagger.v3.oas.annotations.ExternalDocumentation;
import lombok.Builder;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.Optional;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {

    Optional<ProfileImage> findByMember(Member member);
}
