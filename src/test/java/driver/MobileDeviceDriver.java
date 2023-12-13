package driver;

import config.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

//public class MobileDeviceDriver implements WebDriverProvider {
public class MobileDeviceDriver {
//    static CountDownLatch startSignal;
//    static List<String> devices = new ArrayList<>();
//    static ExecutorService executor;
//    static ExecutorService AppiumServerExecutor;
    //    static List<String> AppiumPorts;
//    static List<String> UiAutomatorPorts;
//    static Integer appiumPort;
//    static String remotePath;
public static AndroidDriver<MobileElement> driver;
    public static WebDriverWait wait;
    public static DesiredCapabilities desiredCapabilities;

   // protected static AndroidDriver driver;
    public static final String DEVICE_NAME = ConfigReader.mobileDeviceConfig.deviceName();
    public static final String PLATFORM_NAME = ConfigReader.mobileDeviceConfig.platformName();
    //    public static String APP_PACKAGE = ConfigReader.mobileDeviceConfig.appPackage();
//    public static String APP_ACTIVITY = ConfigReader.mobileDeviceConfig.appActivity();
    public static String UDID = ConfigReader.mobileDeviceConfig.udid();
    public static String AUTOMATION_NAME = ConfigReader.mobileDeviceConfig.automationName();
    //  public static final String APP = ConfigReader.mobileDeviceConfig.app();
    public static final String URL = ConfigReader.mobileDeviceConfig.remoteURL();

//    public MobileDeviceDriver(HttpCommandExecutor executor, Capabilities capabilities) {
//        super(executor, capabilities);
//    }

    public static URL getUrl() {
        try {
            return new URL(URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }

//    private String getAbsolutePath(String filePath) {
//        File file = new File(filePath);
//        assertTrue(file.exists(), filePath + " not found");
//
//        return file.getAbsolutePath();
//    }

    // @Nonnull
//    @Override
//    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
    // initPackageAndActivity();
//        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
//        desiredCapabilities.setCapability("deviceName", DEVICE_NAME);
//        desiredCapabilities.setCapability("platformName", PLATFORM_NAME);
//        desiredCapabilities.setCapability("udid", UDID);
//        desiredCapabilities.setCapability("automationName", AUTOMATION_NAME);
//
////        desiredCapabilities.setCapability("appPackage", APP_PACKAGE);
////        desiredCapabilities.setCapability("appActivity", APP_ACTIVITY);
//        //desiredCapabilities.setCapability("app", getAbsolutePath(APP));
//        driver = new AndroidDriver<>(getUrl(), desiredCapabilities);
//        return driver;
//    }
//    public static WebDriver startAppium() throws InterruptedException {
    @Nonnull
    public static void createDriver() {
        ////////////
      desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        desiredCapabilities.setCapability("deviceName", DEVICE_NAME);
        desiredCapabilities.setCapability("platformName", PLATFORM_NAME);
        desiredCapabilities.setCapability("udid", UDID);
        desiredCapabilities.setCapability("automationName", AUTOMATION_NAME);
        //////////////
        System.out.println("Setting up Appium server for device: " + UDID);

       // AppiumDriver driver = null;
      //  AppiumDriverLocalService service = null;
        //remotePath = "/wd/hub";
        try {
           // AppiumServiceBuilder builder = new AppiumServiceBuilder().withTimeout(Duration.ofMinutes(1));
//            desiredCapabilities.setCapability("systemPort", UiAutomatorPorts.get(deviceNumber));
//            System.out.println("Device: " + deviceNumber + ", UiAutomator port: " + UiAutomatorPorts.get(deviceNumber));
//            builder.withIPAddress("127.0.0.1");
//            builder.usingPort(appiumPort);
            // System.out.println("Device: " + deviceNumber + ", Appium port: " + AppiumPorts.get(deviceNumber));
//            service = AppiumDriverLocalService.buildService(builder);
//            service.start();
            Thread.sleep(2000);
           // driver = new AndroidDriver(new URL("http://127.0.0.1:" + appiumPort + remotePath), desiredCapabilities);
            AppiumDriver  driver = new AndroidDriver(getUrl(), desiredCapabilities);
            driver = new AndroidDriver<MobileElement>(getUrl(), desiredCapabilities);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.hideKeyboard();
            wait = new WebDriverWait(driver, 15);
            //System.out.println("Device: " + devices.get(deviceNumber) + ", Appium port: " + appiumPort);
           // AppiumDriver finalDriver = driver;


//            executor.submit(() -> {
//                try {
//                    helperFunction(finalDriver, deviceNumber);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            });
           // startSignal.countDown();

        } catch (Exception e) {
            System.out.println("Failed to start appium server, trying the next port...");
            e.printStackTrace();
         //   Thread.sleep(1000);
        }
    }

//    public static void main(String[] args) throws InterruptedException {
//
////   @Nonnull
////   @Override
////   public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
//        String os = System.getProperty("os.name").toLowerCase();
//        String command;
//
//        //kill all node processes to ensure a clean environment for appium
//        System.out.println("Killing all previous node processes...");
//        if (os.contains("win")) {
//            command = "taskkill /F /IM node.exe";
//        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
//            command = "killall node";
//        } else {
//            throw new UnsupportedOperationException("Unsupported operating system: " + os);
//        }
//
//        try {
//            Process process = Runtime.getRuntime().exec(command);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//            process.waitFor();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Reinitializing ADB server to ensure no stuck ports");
//        try {
//            ProcessBuilder processBuilder = new ProcessBuilder("adb", "kill-server");
//            Process process = processBuilder.start();
//            process.waitFor();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            ProcessBuilder processBuilder = new ProcessBuilder("adb", "start-server");
//            Process process = processBuilder.start();
//            process.waitFor();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//
////        System.out.println("Getting connected devices and free TCP ports for Appium and UiAutomator...");
////        devices = getConnectedDevices();
////        AppiumPorts = getFreePortsForAppium();
////        UiAutomatorPorts = getFreePortsForUIAutomator();
////        System.out.println("Connected devices: " + devices);
////        System.out.println("These are the chosen Appium ports: " + AppiumPorts);
////        System.out.println("These are the chosen UiAutomator ports: " + UiAutomatorPorts);
//
//       // devices.forEach(device -> {
////            try {
////                ProcessBuilder processBuilder = new ProcessBuilder("adb", "-s", UDID, "uninstall ", "io.appium.uiautomator2.server.test");
////                Process process = processBuilder.start();
////                process.waitFor();
////            } catch (IOException | InterruptedException e) {
////                e.printStackTrace();
////            }
////        //});
////        System.out.println("Uninstalled io.appium.uiautomator2.server.test from all devices, this frees the ports used in previous runs for the UiAutomator");
//
////        startSignal = new CountDownLatch(devices.size());
////        executor = Executors.newFixedThreadPool(devices.size());
////        AppiumServerExecutor = Executors.newFixedThreadPool(devices.size());
////        deviceNumber = new AtomicInteger(0);
////        for (String device : devices) {
////            System.out.println("Starting device: " + device);
////       try {
////           Thread.sleep(5000);
////       } catch (InterruptedException e) {
////           throw new RuntimeException(e);
////       }
//       //           AppiumServerExecutor.submit(() -> {
//                try {
//                    System.out.println("Device is: " + UDID);
//                    createDriver();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
////            });
//            //important, devices need some delay between initializing
////       try {
////           Thread.sleep(5000);
////       } catch (InterruptedException e) {
////           throw new RuntimeException(e);
////       }
////        }
//   }
public static void main(String[] args) {
    createDriver();
}

}
