package com.sparta.logistics.hub.domain.repository;

import com.sparta.logistics.hub.domain.model.Hub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface HubRepository extends JpaRepository<Hub, String>, QuerydslPredicateExecutor<Hub> {

}
