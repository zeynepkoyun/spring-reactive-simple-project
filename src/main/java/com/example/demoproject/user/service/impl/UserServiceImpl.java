package com.example.demoproject.user.service.impl;

import com.example.demoproject.user.domain.entity.UserEntity;
import com.example.demoproject.user.domain.repository.UserRepository;
import com.example.demoproject.user.model.request.UserAddRequest;
import com.example.demoproject.user.model.response.UserListResponse;
import com.example.demoproject.user.model.response.UserResponse;
import com.example.demoproject.user.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Mono<UserListResponse> findUserList() {
    return userRepository.findAll()
        .map(userEntity -> UserResponse.builder()
            .name(userEntity.getName())
            .surname(userEntity.getSurname())
            .adress(userEntity.getAdress())
            .tel_number(userEntity.getTelNumber())
            .build())
        .collectList()
        .map(userResponses -> UserListResponse.builder().userResponseList(userResponses).build());
  }

  @Override
  public Mono<String> addUser(UserAddRequest userAddRequest) {
    return userRepository.findUserEntityByEmail(userAddRequest.getEmail())
        .hasElement()
        .flatMap(hasElement -> {
          if (hasElement) {
            return Mono.error(new Exception("Email already exist in database"));
          } else {
            return Mono.just(UserEntity.builder()
                    .name(userAddRequest.getName())
                    .surname(userAddRequest.getSurname())
                    .adress(userAddRequest.getAdress())
                    .telNumber(userAddRequest.getTelNumber())
                    .email(userAddRequest.getEmail())
                    .build())
                .flatMap(userEntity -> userRepository.save(userEntity))
                .then(Mono.just("Added new User"));
          }
        });
  }

  @Override
  public Mono<Void> deleteUser(String email) {
    return userRepository.findUserEntityByEmail(email)
        .switchIfEmpty(Mono.error(new Exception("Email not exists in database")))
        .flatMap(integer -> userRepository.deleteById(integer));
  }
}
