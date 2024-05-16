package com.subskill.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.subskill.config.ObjectMapperConfig;
import com.subskill.dto.ArticleDto;
import com.subskill.dto.AuthDto.LoginDto;
import com.subskill.dto.ProfessionDto;
import com.subskill.service.ProfessionService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(classes = {CartController.class})
@Import(ObjectMapperConfig.class)
@AutoConfigureMockMvc(addFilters = false)

class SubSkillProfessionControllerTest {

		@MockBean
	    ProfessionService professionService;

	    @Autowired
	    MockMvc mockMvc;

	    @Autowired
	    ObjectMapper mapper;
	    
	    private static String authToken;

	    @BeforeAll
	    public static void setup() {
	        LoginDto loginDto = new LoginDto("12345", "user@example.com");
	        authToken = Jwts.builder()
	                .setSubject(loginDto.email())
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 60L * 1000))
	                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
	                .compact();
	    }

	    @Test
	    void findAllProffessionTest() throws Exception {
	    	  List<ProfessionDto> professionList = Collections.emptyList();
	          when(professionService.findAllProfession()).thenReturn(professionList);
	          String actualJSON = mockMvc.perform(get("api/v1/profession/all")
	                          .contentType(MediaType.APPLICATION_JSON))
	                  .andExpect(status().isOk())
	                  .andReturn()
	                  .getResponse()
	                  .getContentAsString();
	          List<ArticleDto> actualProfessionList = mapper.readValue(actualJSON, new TypeReference<>() {
	          });
	          assertNotEquals(professionList, actualProfessionList);
	    }
	}


