package com.subskill.service;

import com.subskill.dto.CartDto;
import com.subskill.models.Cart;
import com.subskill.models.MicroSkill;
import com.subskill.repository.CartRepository;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.service.impl.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Sql(scripts = {"classpath:cart.sql"})


public class SubSkillCartServiceTest {

    @Mock
    private CartRepository cartRepository;
    @Autowired

    @Mock
    private MicroSkillRepository microSkillRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    private MicroSkill microSkill;
    private CartDto cartDto;
    private Cart cart;
    private static final String CART_SERVICE_TEST = "Cart Service Test: ";
    @BeforeEach
    void setUp() {
        microSkill = new MicroSkill();
        microSkill.setId(1L);
        microSkill.setName("Test MicroSkill");

        Set<MicroSkill> microSkills = new HashSet<>();
        microSkills.add(microSkill);

        cart = new Cart();
        cart.setId(1L);
        cart.setUserId(1L);
        cart.setListOfMicroSkills(microSkills);

        cartDto = new CartDto(
                1L,
                List.of() );
    }


    @Test
    @DisplayName(CART_SERVICE_TEST + "testAddMicroSkillToCart")
    void testAddMicroSkillToCart() {

        when(microSkillRepository.findById(anyLong())).thenReturn(Optional.of(microSkill));
        when(cartRepository.findByUserId(anyLong())).thenReturn(Optional.of(cart));

        CartDto result = cartService.addMicroSkillToCart(1L);

        assertEquals(cartDto, result);
        verify(cartRepository, times(1)).findByUserId(anyLong());
        verify(microSkillRepository, times(1)).findById(anyLong());
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    @DisplayName(CART_SERVICE_TEST + "DeleteMicroSkillFromCart")
    void testDeleteMicroSkillFromCart() {

        when(cartRepository.findCartByUserId(anyLong())).thenReturn(Optional.of(cart));
        cartService.deleteMicroSkillFromCart(1L);
        verify(cartRepository, times(1)).findCartByUserId(anyLong());
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    @DisplayName(CART_SERVICE_TEST + "AllMicroSkillsInCart")
    void testAllMicroSkillsInCart() {

        when(cartRepository.findAll()).thenReturn(List.of(cart));
        List<CartDto> result = cartService.allMicroSkillsInCart();
        assertEquals(List.of(cartDto), result);
        verify(cartRepository, times(1)).findAll();
    }
}
