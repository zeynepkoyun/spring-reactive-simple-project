package com.example.demoproject.user.domain.repository;

import com.example.demoproject.user.domain.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<UserEntity,Integer> {

  Mono<Integer> findUserEntityByEmail(String email);
}
