package thescope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import thescope.models.User;
import thescope.services.UserService;

import org.junit.jupiter.api.Assertions;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;
 
    @Test
    public void findUserAndHasBookingTest() {
        User user=userService.findUserByUsername("customer@thescope.site");
        Boolean hasBooking = userService.hasBookings(user);
        Assertions.assertTrue(hasBooking);
    }

    @Test
    public void findUsersByUserName() {
        String userName = "admin@thescope.site";
        User user = userService.findUserByUsername(userName);
        System.out.println(userName);
        Assertions.assertEquals("admin@thescope.site", user.getUserName());
    }

}
