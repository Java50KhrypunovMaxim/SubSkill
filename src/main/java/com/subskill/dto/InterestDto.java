package com.subskill.dto;


import com.subskill.models.User;

import java.util.List;

public record InterestDto(
         String name,
         List<User> userList
){
}
