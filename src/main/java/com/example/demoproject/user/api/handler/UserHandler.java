package com.example.demoproject.user.api.handler;

import com.example.demoproject.user.model.request.UserAddRequest;
import com.example.demoproject.user.model.response.UserListResponse;
import com.example.demoproject.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class UserHandler {
  private final UserService userService;
  public Mono<ServerResponse> listUser(ServerRequest serverRequest){
    return userService.findUserList()
        .flatMap(userListResponse -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(userListResponse),UserListResponse.class));
  }
  public Mono<ServerResponse> addUser(ServerRequest serverRequest){
    return serverRequest.bodyToMono(UserAddRequest.class)
        .flatMap(userAddRequest -> userService.addUser(userAddRequest))
        .flatMap(body -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(body));
  }
  public Mono<ServerResponse> deleteUser(ServerRequest serverRequest){
    return Mono.just(serverRequest.pathVariable("email"))
        .flatMap(requestEmail -> userService.deleteUser(requestEmail))
        .then(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).build());
  }


}

