package com.project.VisitBusan.repository;

import com.project.VisitBusan.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @EntityGraph(attributePaths = "roleSet")
    Member findByEmail(String email);
}
