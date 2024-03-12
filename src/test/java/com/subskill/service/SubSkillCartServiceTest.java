package com.subskill.service;

import com.subskill.dto.CartDto;
import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import com.subskill.models.*;
import com.subskill.repository.CartRepository;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.service.impl.CartServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Log4j2
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class SubSkillCartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private MicroSkillRepository microSkillRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CartServiceImpl cartService;

    private static final String CART_SERVICE_TEST = "Cart Service Test: ";

    private MicroSkill microskill1;

    @BeforeEach
    void setUp() {
        microskill1 = MicroSkill.builder()
                .id(67L)
                .name("Java Basics")
                .photo("java.jpg")
                .creationDate(LocalDate.of(2022, 1, 1))
                .description("Introduction to Java programming language")
                .learningTime("2 weeks")
                .tags(List.of(Tags.DESIGN, Tags.BUSINESS))
                .level(Level.BASIC)
                .rating(4.5)
                .popularity(120.0)
                .views(1500)
                .price(19.99)
                .lessonCount(10)
                .aboutSkill("Learn the basics of Java programming language")
                .lastUpdateTime(LocalDateTime.of(2022, 1, 31, 10, 15))
                .build();
    }

    @Test
    @DisplayName(CART_SERVICE_TEST + "Add MicroSkill To Cart")
    void testAddMicroSkillToCart() {
        Cart cart = new Cart();
        cart.setId(10L);
        cart.setUserId(10L);
        Set<MicroSkill> microSkills = new HashSet<>();
        microSkills.add(microskill1);
        cart.setListOfMicroSkills(microSkills);

        when(microSkillRepository.findById(microskill1.getId())).thenReturn(Optional.of(microskill1));
        when(cartRepository.findByUserId(10L)).thenReturn(Optional.of(cart));

        CartDto result = cartService.addMicroSkillToCart(microskill1.getId(), 10L);

        assertEquals(10L, result.userId());
        assertEquals(2, result.listOfMicroSkills().size());
    }

    @Test
    @DisplayName(CART_SERVICE_TEST + "All MicroSkills In Cart")
    void testAllMicroSkillsInCart() {
        CartDto result = cartService.allMicroSkillsInCart(10L);
        assertNotEquals(0, result.listOfMicroSkills().size());
        assertEquals(1, result.listOfMicroSkills().size());
    }


}
