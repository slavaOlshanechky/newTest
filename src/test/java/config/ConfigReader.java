package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigReader {

    //reader for mobileDevice.properties
    public static final MobileDeviceConfig mobileDeviceConfig= ConfigFactory.create(MobileDeviceConfig.class,System.getProperties());

    //reader for test.properties
    public static final TestConfig testConfig =ConfigFactory.create(TestConfig.class, System.getProperties());
}
