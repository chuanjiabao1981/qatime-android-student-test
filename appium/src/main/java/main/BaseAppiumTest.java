package main;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import util.HttpRequest;

/**
 * @author luntify
 * @date 2016/8/23 15:39
 * @Description
 */
public class BaseAppiumTest {
    public AppiumDriver<AndroidElement> driver;
    protected HttpRequest request = new HttpRequest();

    @Before
    public void testBegin() {
        try {
            File app = new File(ConstantValue.appPath, ConstantValue.appName);//apk路径
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", ConstantValue.deviceName);//手机机型名
            capabilities.setCapability("platformVersion", ConstantValue.platformVersion);//手机版本
            capabilities.setCapability("app", app.getAbsolutePath());//获取当前app包的路径
//            capabilities.setCapability("unicodeKeyboard","True");//实现中文输入
            capabilities.setCapability("resetKeyboard", "True");//输入结束隐藏键盘
            capabilities.setCapability("appPackage", ConstantValue.appPackage);//app包名
            capabilities.setCapability("noSign", "True");//避免重签名
            capabilities.setCapability("StartActivity", ConstantValue.appActivity);//测试起始类，一般都是引导页
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);//初始化
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 返回按钮
     *
     * @throws InterruptedException
     */
    public void back() throws InterruptedException {
        AndroidElement back = driver.findElementById("back");
        back.click();
        Time(2);
    }

    public void titleBack() throws InterruptedException {
        AndroidElement back = driver.findElementById("title_back");
        back.click();
        Time(2);
    }


    /**
     * 进程运行时间间隔
     *
     * @param time 秒
     * @throws InterruptedException
     */
    public void Time(int time) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
