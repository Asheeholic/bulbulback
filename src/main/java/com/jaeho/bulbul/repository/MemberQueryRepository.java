package com.jaeho.bulbul.repository;

import com.jaeho.bulbul.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import static com.jaeho.bulbul.entity.QMember.*;

@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory query;

    public MemberQueryRepository(JPAQueryFactory queryFactory){
        this.query = queryFactory;
    }


    /**
     * 멤버 정보 조회
     * @param memberId
     * @return Member
     */
    public Member findMemberByMemberId(String memberId){
        return query.selectFrom(member)
                .where(member.memberId.eq(memberId))
                .fetchOne();
    }

    /**
     * 멤버 정보 수정 (패스워드 제외)
     * @param memberId
     * @param nickname
     * @param phoneNumber
     * @param email
     * @return Long
     */
    public Long updateMemberInfo(String memberId, String nickname, String phoneNumber, String email){
        return query.update(member)
                .where(member.memberId.eq(memberId))
                .set(member.nickname, nickname)
                .set(member.phoneNumber, phoneNumber)
                .set(member.email, email)
                .execute();
    }

    /**
     * 멤버 패스워드 수정
     * @param memberId
     * @param password
     * @return Long
     */
    public Long updateMemberPassword(String memberId, String password){
        return query.update(member)
                .where(member.memberId.eq(memberId))
                .set(member.password, password)
                .execute();
    }

    /**
     * 멤버 패스워드 조회 (해도 되나..)
     * @param memberId
     * @return
     */
    public String findPasswordByMemberId(String memberId){
        return query.select(member.password)
                .from(member)
                .where(member.memberId.eq(memberId))
                .fetchOne();
    }

}
