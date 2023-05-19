package com.jaeho.bulbul.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory query;

    public MemberQueryRepository(JPAQueryFactory queryFactory){
        this.query = queryFactory;
    }



}
