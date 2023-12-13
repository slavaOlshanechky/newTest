package tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import screens.LoginScreen;

import static helper.Constants.REGISTERED_PHONE;

public class LoginTest extends BaseTest{
    private static LoginScreen loginScreen;

//    @BeforeSuite
//    public void setUp() throws IOException {
//        createDriver();
//    }

    @BeforeAll
    public static void init() {
        loginScreen = new LoginScreen();
    }

    @Test
    @Description("Login screen test")
    public void testLogin(){
        loginScreen.closeNotificationPopUp()
                //.isCursorInField()
                .openPhoneField()
                .typePhoneNumber(REGISTERED_PHONE)
                .login();
    }

}
