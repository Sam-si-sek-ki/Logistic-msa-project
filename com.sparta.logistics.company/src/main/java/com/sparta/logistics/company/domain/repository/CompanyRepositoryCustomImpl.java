package com.sparta.logistics.company.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.logistics.company.domain.model.Company;
import com.sparta.logistics.company.domain.model.QCompany;
import com.sparta.logistics.company.infrastructure.configuration.CompanySearchCondition;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
public class CompanyRepositoryCustomImpl implements CompanyRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public Page<Company> searchCompanies(CompanySearchCondition condition, Pageable pageable) {
    QCompany company = QCompany.company;

    BooleanBuilder builder = new BooleanBuilder();
    builder.and(company.isDeleted.eq(false));

    if (condition.getCompanyType() != null) {
      builder.and(company.companyType.eq(condition.getCompanyType()));
    }

    if (StringUtils.hasText(condition.getCompanyName())) {
      builder.and(company.companyName.contains(condition.getCompanyName()));
    }

    if (condition.getHubId() != null) {
      builder.and(company.hubId.eq(condition.getHubId()));
    }

    List<Company> content = queryFactory
        .selectFrom(company)
        .where(builder)
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    long total = Optional.ofNullable(queryFactory
        .select(company.count())
        .from(company)
        .where(builder)
            .fetchOne())
        .orElse(0L);

    return new PageImpl<>(content, pageable, total);
  }
}
