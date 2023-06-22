package com.example.demoproject.user.model.response;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class UserListResponse {
  List<UserResponse> userResponseList;
}
