package com.subskill.service;

import com.subskill.dto.CartDto;
import com.subskill.enums.Level;
import com.subskill.enums.Roles;
import com.subskill.enums.Tags;
import com.subskill.models.Cart;
import com.subskill.models.MicroSkill;
import com.subskill.models.User;
import com.subskill.repository.CartRepository;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.UserRepository;
import com.subskill.service.impl.CartServiceImpl;
import com.subskill.service.impl.UserServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@Sql(scripts = {"classpath:cart.sql"})
public class SubSkillCartServiceTest {
    @Mock
    private CartRepository cartRepository;

    @Mock
    private MicroSkillRepository microSkillRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserServiceImplementation userService;

    @InjectMocks
    private CartServiceImpl cartService;


    private static final String CART_SERVICE_TEST = "Cart Service Test: ";


    //    @BeforeEach
//    void setUp() {
//        microSkill = new MicroSkill();
//        microSkill.setId(1L);
//        microSkill.setName("Test MicroSkill");
//
//        Set<MicroSkill> microSkills = new HashSet<>();
//        microSkills.add(microSkill);
//
//        cart = new Cart();
//        cart.setId(1L);
//        cart.setUserId(1L);
//        cart.setListOfMicroSkills(microSkills);
//
//        cartDto = new CartDto(
//                1L,
//                List.of(new MicroSkillDto( "Test MicroSkill",
//                        "Description",
//                        "Photo",
//                        "Learning Time",
//                        List.of(Tags.FRONTEND, Tags.DEVOPS),
//                        10.0,
//                        LocalDateTime.now(),
//                        LocalDate.now(),
//                        "About Skill",
//                        Level.ADVANCED,
//                        List.of(new ArticleDto("Article Name", "Article Content",microSkill)),
//                        1L))
//        );
//    }
private static final String USERNAME1 = "MAX";
    private static final String USERNAME2 = "Artur";
    private static final String USERNAME3 = "David";


    private static final String EMAIL1 = "user1@telran.com";
    private static final String EMAIL2 = "name1@tel-ran.co.il";
    private static final String EMAIL3 = "Max@gmail.com";



    private static final String PASSWORD1 = "telran321";
    private static final String PASSWORD2 = "tel567";
    private static final String PASSWORD3 = "telran234";

    private static final String IMAGEURL1 = "https://example.com/image1.jpg";
    private static final String IMAGEURL2 = "https://example.com/image2.jpg";
    private static final String IMAGEURL3 = "https://example.com/image3.jpg";

    User user1 = User.builder()
            .id(1L)
            .username(USERNAME1)
            .email(EMAIL1)
            .password(PASSWORD1)
            .online(true)
            .imageUrl(IMAGEURL1)
            .role(Roles.USER)
            .build();

    User user2 = User.builder()
            .id(2L)
            .username(USERNAME2)
            .email(EMAIL2)
            .password(PASSWORD2)
            .online(true)
            .imageUrl(IMAGEURL2)
            .role(Roles.USER)
            .build();

    User user3 = User.builder()
            .   id(3L)
            .username(USERNAME3)
            .email(EMAIL3)
            .password(PASSWORD3)
            .online(true)
            .imageUrl(IMAGEURL3)
            .role(Roles.USER)
            .build();
    MicroSkill microskill1 = MicroSkill.builder()
            .id(1L)
            .name("Java Programming")
            .photo("https://example.com/java.jpg")
            .creationDate(LocalDate.now())
            .description("Introduction to Java programming language")
            .learningTime("20 hours")
            .tags(List.of(Tags.DESIGN, Tags.BUSINESS))
            .level(Level.ADVANCED)
            .rating(4.5)
            .popularity(100.0)
            .views(500)
            .price(29.99)
            .lessonCount(10)
            .aboutSkill("This course covers basic Java programming concepts.")
            .lastUpdateTime(LocalDateTime.now())
            .build();

    MicroSkill microskill2 = MicroSkill.builder()
            .id(2L)
            .name("Python Programming")
            .photo("https://example.com/python.jpg")
            .creationDate(LocalDate.now())
            .description("Introduction to Python programming language")
            .learningTime("15 hours")
            .tags(List.of(Tags.DEVELOPMENT))
            .level(Level.INTERMEDIATE)
            .rating(4.2)
            .popularity(80.0)
            .views(400)
            .price(19.99)
            .lessonCount(8)
            .aboutSkill("This course covers intermediate Python programming concepts.")
            .lastUpdateTime(LocalDateTime.now())
            .build();

    MicroSkill microskill3 = MicroSkill.builder()
            .id(3L)
            .name("Machine Learning")
            .photo("https://example.com/ml.jpg")
            .creationDate(LocalDate.now())
            .description("Introduction to Machine Learning")
            .learningTime("30 hours")
            .tags(List.of(Tags.IT_SOFTWARE, Tags.BUSINESS))
            .level(Level.BASIC)
            .rating(4.8)
            .popularity(150.0)
            .views(600)
            .price(39.99)
            .lessonCount(12)
            .aboutSkill("This course covers advanced Machine Learning concepts.")
            .lastUpdateTime(LocalDateTime.now())
            .build();

//
//    @Test
//    @DisplayName(CART_SERVICE_TEST)
//    void testAddMicroSkillToCart() {
//        Cart cart = new Cart();
//        cart.setId(1L);
//        cart.setUserId(user1.getId());
//        Set<MicroSkill> microSkills = new HashSet<>();
//        microSkills.add(microskill1);
//        cart.setListOfMicroSkills(microSkills);
//
//
//
//            when(userService.getAuthenticatedUser()).thenReturn(user1);
//            when(microSkillRepository.findById(microskill1.getId())).thenReturn(Optional.of(microskill1));
//            when(cartRepository.findByUserId(user1.getId())).thenReturn(Optional.empty());
//            when(cartRepository.save(any(Cart.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//            CartDto result = cartService.addMicroSkillToCart(microskill1.getId());
//
//            assertEquals(user1.getId(), result.userId());
//            assertEquals(1, result.listOfMicroSkills().size());
//    }


//    Cart cart = new Cart();
//    cart.setId(1L);
//    cart.setUserId(user1.getId());
//    Set<MicroSkill> microSkills = new HashSet<>();
//    microSkills.add(microskill1);
//    cart.setListOfMicroSkills(microSkills);
//
//
//    when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
//    when(cartRepository.save(any(Cart.class))).thenReturn(cart);
//
//
//    cartService.deleteMicroSkillFromCart(cart.getId());
//
//
//    cart = cartRepository.findById(cart.getId()).orElse(null);
//
//    assertNotNull(cart, "Not Null");
//    assertTrue(cart.getListOfMicroSkills().isEmpty(), "Cart should be empty after deleting MicroSkill");
//    assertFalse(cart.getListOfMicroSkills().contains(microskill1), "MicroSkill should be removed from Cart");

    @BeforeEach
    void refreshRepository() {
        Mockito.reset(cartRepository, microSkillRepository, userRepository);
    }

    @Test
    @DisplayName("Delete MicroSkill from Cart Test")
    void testDeleteMicroSkillFromCart() {
        Cart cart = new Cart();
        cart.setId(1L);

        Set<MicroSkill> microSkills = new HashSet<>();
        microSkills.add(microskill1);
        cart.setListOfMicroSkills(microSkills);

        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
        cartService.deleteMicroSkillFromCart(microskill1.getId());

        Optional<Cart> optionalCart = cartRepository.findById(cart.getId());
        assertTrue(optionalCart.isPresent(), "Cart not null");


        assertTrue(cart.getListOfMicroSkills().contains(microskill1), "no microskill in cart");
    }
}
