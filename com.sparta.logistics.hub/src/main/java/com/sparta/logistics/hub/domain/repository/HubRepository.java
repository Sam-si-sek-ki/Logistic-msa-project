package com.sparta.logistics.hub.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.sparta.logistics.hub.domain.model.Hub;
import com.sparta.logistics.hub.domain.model.QHub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.*;

public interface HubRepository extends JpaRepository<Hub, UUID>,
        QuerydslPredicateExecutor<Hub>,
        QuerydslBinderCustomizer<QHub> {


    @Override
    default void customize(QuerydslBindings bindings, QHub qHub) {
        bindings.bind(String.class).all((StringPath path, Collection<? extends String> values) -> {
            List<String> valueList = new ArrayList<>(values.stream().map(String::trim).toList());
            if (valueList.isEmpty()) {
                return Optional.empty();
            }
            BooleanBuilder bb = new BooleanBuilder();
            for (String str : valueList) {
                bb.or(path.containsIgnoreCase(str));
            }
            return Optional.of(bb);
        });
    }
}
