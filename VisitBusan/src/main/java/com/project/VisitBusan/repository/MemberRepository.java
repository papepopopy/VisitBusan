package com.project.VisitBusan.repository;

import com.project.VisitBusan.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
<<<<<<< HEAD
    Member findByEmail(String email);

    @EntityGraph(attributePaths = "profileImage")
    @Query("select b from Member b where b.userId = :userId")
    Optional<Member> findByUserId(@Param("userId") String userId);
=======

    @EntityGraph(attributePaths = "roleSet")
    Member findByEmail(String email);
>>>>>>> 96e56902718561b200ab9ad54d209b423b20b8d1
}



/*

JpaRepository에서 지원하는 메서드

<S extends T> save(S entity)    : 엔티티 저장 및 수정
void delete(t entity)           : 엔티티 삭제
count()                         : 엔티티 총 개수 반환
Iterable<T> finaAll(0)          : 모든 엔티티 조회

 */