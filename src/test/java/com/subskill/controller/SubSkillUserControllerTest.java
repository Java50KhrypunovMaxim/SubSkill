package com.subskill.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.subskill.config.ObjectMapperConfig;
import com.subskill.enums.Status;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.subskill.dto.UserDto;
import com.subskill.enums.Roles;
import com.subskill.service.UserService;

import java.util.List;

@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@Import(ObjectMapperConfig.class)
@SpringBootTest(classes = {UsersController.class})
class SubSkillUserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    private static final String USERNAME1 = "MAX";
    private static final String USERNAME2 = "Artur";

    private static final String NICKNAME1 = "Rambo";
    private static final String NICKNAME2 = "Leo";
    private static final String NICKNAME3 = "Monkey";

    private static final String EMAIL1 = "user1@telran.com";
    private static final String EMAIL2 = "name1@tel-ran.co.il";
    private static final String EMAIL3 = "Max@gmail.com";

    private static final String PASSWORD1 = "Telran321";
    private static final String PASSWORD3 = "Telran234";

    private static final String IMAGEURLl = "https://example.com/image1.jpg";
    private static final String IMAGEURL2 = "https://example.com/image2.jpg";

    UserDto userDto1 = new UserDto(USERNAME1, EMAIL1, PASSWORD1, Status.ONLINE, "", Roles.USER);
    UserDto userDtoUpdated = new UserDto(USERNAME1, EMAIL1, PASSWORD1, Status.ONLINE, "", Roles.USER);
    UserDto changePasswordUserDto2 = new UserDto(USERNAME2, EMAIL2, PASSWORD3, Status.ONLINE, "", Roles.USER);



    @Test
    void testUpdateUser() throws Exception {
//        when(userService.updateUser(userDtoUpdated)).thenReturn(userDtoUpdated);
        String jsonUserDtoUpdated = mapper.writeValueAsString(userDtoUpdated);
        String actualJSON = mockMvc.perform(put("/api/v1/users/update/" + userDtoUpdated.email()).contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUserDtoUpdated)).andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        assertEquals(jsonUserDtoUpdated, actualJSON);
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(EMAIL1);
        mockMvc.perform(delete("/api/v1/users/delete/" + EMAIL1)
                .param("email", EMAIL1)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void testChangePassword() throws Exception {
//        when(userService.changePassword(EMAIL2, PASSWORD3)).thenReturn(changePasswordUserDto2);
//        String jsonChangePasswordDto = mapper.writeValueAsString(changePasswordUserDto2);
//        String actualJSON = mockMvc.perform(put("/api/v1/users/password/" + EMAIL2)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonChangePasswordDto))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        assertEquals(jsonChangePasswordDto, actualJSON);
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<UserDto> userList = userService.allUsers();
        when(userService.allUsers()).thenReturn(userList);
        String jsonUserList = mapper.writeValueAsString(userList);
        String actualJSON = mockMvc.perform(get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals(jsonUserList, actualJSON);
    }

}




