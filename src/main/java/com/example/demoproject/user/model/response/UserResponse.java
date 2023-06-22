package com.example.demoproject.user.model.response;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
  String name;
  String surname;
  String adress;
  String email;
}
