package com.sparta.logistics.hub.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.sparta.logistics.hub.domain.model.Hub;
import com.sparta.logistics.hub.domain.model.QHub;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface HubRepository extends JpaRepository<Hub, String>,
        QuerydslPredicateExecutor<Hub>,
        QuerydslBinderCustomizer<QHub> {


    @Override
    default void customize(QuerydslBindings bindings, @NotNull QHub qHub) {
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
