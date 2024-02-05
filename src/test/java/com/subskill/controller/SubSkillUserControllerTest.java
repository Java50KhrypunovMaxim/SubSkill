package com.subskill.controller;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import com.subskill.dto.UserDto;
import com.subskill.enums.Roles;
import com.subskill.service.UserService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UsersController.class)
class SubSkillUserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    private static final String USERNAME1 = "MAX";
    private static final String USERNAME2 = "Artur";
    private static final String USERNAME3 = "David";

    private static final String NICKNAME1 = "Rambo";
    private static final String NICKNAME2 = "Leo";
    private static final String NICKNAME3 = "Monkey";

    private static final String EMAIL1 = "user1@telran.com";
    private static final String EMAIL2 = "name1@tel-ran.co.il";
    private static final String EMAIL4 = "Max@gmail.com";

    private static final String PASSWORD1 = "Telran321";
    private static final String PASSWORD2 = "Tel567";
    private static final String PASSWORD3 = "Telran234";

    private static final String IMAGEURLl = "https://example.com/image1.jpg";
    private static final String IMAGEURL2 = "https://example.com/image2.jpg";
    private static final String IMAGEURL3 = "https://example.com/image3.jpg";

    UserDto userDto1 = new UserDto(USERNAME1, PASSWORD1, EMAIL4, NICKNAME1, true, IMAGEURLl, Roles.USER);
    UserDto userDto2 = new UserDto(USERNAME2, PASSWORD2, EMAIL2, NICKNAME2, true, IMAGEURL2, Roles.USER);
    UserDto userDtoUpdated = new UserDto(USERNAME1, PASSWORD1, EMAIL1, NICKNAME3, true, IMAGEURLl, Roles.ADMIN);
    UserDto changePasswordUserDto2 = new UserDto(USERNAME2, PASSWORD3, EMAIL2, NICKNAME2, true, IMAGEURL2, Roles.USER);

    @Test
    void testRegisterUser() throws Exception {
        when(userService.registerUser(userDto1)).thenReturn(userDto1);
        String jsonUserDto = mapper.writeValueAsString(userDto1);
        String actualJSON = mockMvc.perform(post("http://localhost:8080/api/v1/users").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUserDto)).andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        assertEquals(jsonUserDto, actualJSON);

    }

    @Test
    void testUpdateUser() throws Exception {
        when(userService.updateUser(userDtoUpdated)).thenReturn(userDtoUpdated);
        String jsonUserDtoUpdated = mapper.writeValueAsString(userDtoUpdated);
        String actualJSON = mockMvc.perform(put("http://localhost:8080/api/v1/users/update/" + userDtoUpdated.email()).contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUserDtoUpdated)).andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        assertEquals(jsonUserDtoUpdated, actualJSON);
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(EMAIL1);
        mockMvc.perform(delete("http://localhost:8080/api/v1/users/delete/" + EMAIL1)
                .param("email", EMAIL1)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void testChangePassword() throws Exception {
        when(userService.changePassword(EMAIL2, PASSWORD3)).thenReturn(changePasswordUserDto2);
        String jsonChangePassworduserDto = mapper.writeValueAsString(changePasswordUserDto2);
        String actualJSON = mockMvc.perform(put("http://localhost:8080/api/v1/users/password/" + EMAIL2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonChangePassworduserDto))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(jsonChangePassworduserDto, actualJSON);
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<String> userList = Arrays.asList(EMAIL2, EMAIL1);
        when(userService.allUsers()).thenReturn(userList);
        String jsonUserList = mapper.writeValueAsString(userList);
        String actualJSON = mockMvc.perform(get("http://localhost:8080/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(jsonUserList, actualJSON);
    }

}
		

	

