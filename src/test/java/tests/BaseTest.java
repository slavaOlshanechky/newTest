package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helper.RunHelper;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import listeners.AllureListener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static helper.Constants.SCREENSHOT_TO_SAVE_FOLDER;
import static helper.RunHelper.runHelper;
import static io.qameta.allure.Allure.step;

@ExtendWith(AllureListener.class)
public class BaseTest {
//    public static void main(String[] args) {
//        runHelper();
//    }
    @BeforeAll
    public static void setup() {
        //add logging for allure's report with steps
        runHelper();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.reportsFolder = SCREENSHOT_TO_SAVE_FOLDER;

        //Android driver initialization
//      Configuration.browser = runHelper().getDriverClass().getName();
 //     Configuration.remote = runHelper().getDriverClass().getName();
//        Configuration.startMaximized = false;
//        Configuration.browserSize = null;
//        Configuration.timeout = 10000;
        String os = System.getProperty("os.name").toLowerCase();
        String command;

        //kill all node processes to ensure a clean environment for appium
        System.out.println("Killing all previous node processes...");
        if (os.contains("win")) {
            command = "taskkill /F /IM node.exe";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            command = "killall node";
        } else {
            throw new UnsupportedOperationException("Unsupported operating system: " + os);
        }

        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Reinitializing ADB server to ensure no stuck ports");
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("adb", "kill-server");
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("adb", "start-server");
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @BeforeEach
    public void startDriver() {
        step("Open app", (Allure.ThrowableRunnableVoid) Selenide::open);
    }


    @AfterEach
    public void afterEach() {
        step("Close App", Selenide::closeWebDriver);
    }
}
