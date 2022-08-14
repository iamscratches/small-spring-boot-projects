package com.iamscratches.ec.exportIn.web;

import com.iamscratches.ec.exportIn.domain.User;
import com.iamscratches.ec.exportIn.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SecurityControllerTest {
    @Autowired
    public UserService userService;

    Logger logger = LoggerFactory.getLogger(SecurityControllerTest.class);

    @Test
    public void signupTest(){
        Optional<User> user = userService.signup("dummyusername", "dummypassword",
                "dummyFirstName", "dummyLastName");
        assertNotEquals(user.get().getPassword(), "dummypassword");
        logger.debug(user.get().getPassword());
    }
}
