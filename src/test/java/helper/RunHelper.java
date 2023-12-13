package helper;

import config.ConfigReader;
import driver.MobileDeviceDriver;

public class RunHelper {
    public static RunHelper runHelper() {
        return new RunHelper();
    }

    public Class<?> getDriverClass() {
        String deviceHost = ConfigReader.testConfig.deviceHost();

        switch (deviceHost) {
            case "browserstack":
                //    return BrowserstackMobileDriver.class; //BrowserStack logic
            case "selenoid":
                //   return SelenoidMobileDriver.class; //Selenoid and Multithreading logic
            case "emulator":
               // return EmulatorDriver.class; //emulator session logic
            case "real":
                    return MobileDeviceDriver.class;// real mobile device
            default:
                throw new RuntimeException("There are no params for deviceHost: " +
                        "browserstack/selenoid/emulator/real");
        }
    }
}
