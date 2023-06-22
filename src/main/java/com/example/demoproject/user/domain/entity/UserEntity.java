package com.example.demoproject.user.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("user_info")
public class UserEntity {
  @Id
  @Column("id")
  Integer id;
  @Column("name")
  String name;
  @Column("surname")
  String surname;
  @Column("adress")
  String adress;
  @Column("email")
  String email;
}
