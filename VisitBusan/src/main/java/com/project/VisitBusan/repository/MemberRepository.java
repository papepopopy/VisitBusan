package com.project.VisitBusan.repository;

import com.project.VisitBusan.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    Optional<Member> findByUserId(String userId);
}
