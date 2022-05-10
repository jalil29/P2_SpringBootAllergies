package dev.springallergies.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTests {
    @Test
    public void canCreateUser() {
        User user = new User();
        Assertions.assertNotNull(user);
    }
}
