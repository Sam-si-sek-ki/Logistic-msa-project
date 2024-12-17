package com.sparta.logistics.company.infrastructure.configuration;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuditorAware implements AuditorAware<String> {
  private final UserContextHolder userContextHolder;

  @Override
  public @NotNull Optional<String> getCurrentAuditor() {
    return Optional.of(userContextHolder.getCurrentAuditor());
  }
}
