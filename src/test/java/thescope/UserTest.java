package thescope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import thescope.models.User;
import thescope.services.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserTest {

    @Autowired
    private UserService userService;

    @Test
    void findUserAndHasBookingTest() {
        User user = userService.findUserByUsername("customer@thescope.site");
        Boolean hasBooking = userService.hasBookings(user);
        assertTrue(hasBooking);
    }

    @Test
    void findUsersByUserName() {
        String userName = "admin@thescope.site";
        User user = userService.findUserByUsername(userName);
        System.out.println(userName);
        assertEquals("admin@thescope.site", user.getUserName());
    }

}
