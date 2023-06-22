package com.example.demoproject.user.api.router;

import com.example.demoproject.user.api.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class UserRouter {
  public static final class UserResourceConstant {
    public static final String USER_LIST_API_PATH = "/api/user/list";
    public static final String USER_ADD_API_PATH = "/api/user/add";
    public static final String USER_DELETE_API_PATH = "/api/user/delete/{email}";
  }

  @Bean
  public RouterFunction<ServerResponse> userRoutes(UserHandler userHandler){
    return RouterFunctions
        .route(GET(UserResourceConstant.USER_LIST_API_PATH),userHandler::listUser)
        .andRoute(POST(UserResourceConstant.USER_ADD_API_PATH),userHandler::addUser)
        .andRoute(DELETE(UserResourceConstant.USER_DELETE_API_PATH),userHandler::deleteUser);
  }
}
