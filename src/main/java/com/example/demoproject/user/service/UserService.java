package com.example.demoproject.user.service;

import com.example.demoproject.user.model.request.UserAddRequest;
import com.example.demoproject.user.model.response.UserListResponse;
import reactor.core.publisher.Mono;

public interface UserService {

  Mono<UserListResponse> findUserList();
  Mono<String> addUser(UserAddRequest userAddRequest);

  Mono<Void> deleteUser(String email);
}
