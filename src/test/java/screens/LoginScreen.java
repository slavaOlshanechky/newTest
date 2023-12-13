package screens;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static helper.MobileDeviceHelper.sendKeys;

public class LoginScreen {
    private SelenideElement loginButton = $(MobileBy.id("Login"));
    private SelenideElement continueButton = $(MobileBy.id("Continue"));
//    private String registeredPhone = "+972522245678";
    private SelenideElement phoneField = $(MobileBy.id("phone"));
    private SelenideElement notificationsPopUp = $(MobileBy.xpath("/hierarchy/android.widget.FrameLayout"));
    private SelenideElement locationPermitPopUp = $(MobileBy.id("com.android.permissioncontroller:id/grant_dialog"));
    private SelenideElement allowNotificationButton = $(MobileBy.id("com.android.permissioncontroller:id/permission_allow_button"));
    private SelenideElement whileUsingAppButton = $(MobileBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"));

//    private By loginButton = By.id("Login");
//    private By continueButton = By.id("Continue");
//    private By phoneField = By.id("phone");
//    private By notificationsPopUp = By.xpath("/hierarchy/android.widget.FrameLayout");
//    private By locationPermitPopUp = By.id("com.android.permissioncontroller:id/grant_dialog");
//    private By allowNotificationButton = By.id("com.android.permissioncontroller:id/permission_allow_button");
//    private By whileUsingAppButton = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");





    @Step("Close Notifications PopUp")
    public LoginScreen closeNotificationPopUp() {
        if (notificationsPopUp.exists()) {
            allowNotificationButton.click();
        }
        return this;
    }

    @Step("Open phone number field")
    public LoginScreen openPhoneField() {
        phoneField.click();
        return this;
    }

    @Step("Type phone number")
    public LoginScreen typePhoneNumber(String registeredPhone) {
        sendKeys(phoneField, registeredPhone);
        return this;
    }

    @Step("Login with phone number")
    public void login() {
        loginButton.click();

    }
}
