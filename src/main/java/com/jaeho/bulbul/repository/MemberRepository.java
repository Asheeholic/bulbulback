package com.jaeho.bulbul.repository;

import com.jaeho.bulbul.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
