package main;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public abstract class AppiumTest {

    public AppiumDriver<AndroidElement> driver;

    /**
     * <p>
     * 每个新建的测试代码都要继承这个基类  就不用每次都写配置代码了
     */
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

    public void test() throws InterruptedException, MalformedURLException {
        Time(5);

        try { //首页测试
            onHome();//用于主页的测试

        } catch (NoSuchElementException e) {
            //捕获可能为空的异常  避免测试因为找不到控件崩溃
            login();
        }
    }

    /**
     * 主页
     */
    public void onHome() throws InterruptedException, NoSuchElementException, MalformedURLException {
        AndroidElement framentLayout = driver.findElementById("fragmentlayout");//此行用于标示是否是主页,不是就throw 异常,由起始页到登录页
        testStart();//用于到主页后的操作
    }


    /**
     * 引导页活动
     */
    public void Slide() throws InterruptedException, MalformedURLException {
        //引导页滑动设置
        try {
            int width = driver.manage().window().getSize().width;
            driver.swipe(width - 100, 100, width / 2, 100, 200);
            Time(1);
            //到登录页
            AndroidElement androidElement = driver.findElementByClassName("android.widget.ImageView");
            androidElement.click();
            login();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }


    /**
     * 登录界面测试
     */
    public void login() throws InterruptedException, MalformedURLException {
        try {
            Time(5);
            List<AndroidElement> edit = driver.findElementsById("name");
            List<AndroidElement> pass = driver.findElementsById("pass");
            //登陆按钮
            AndroidElement login = driver.findElementById("login");
//            login.click();
//            Time(2);
//
//            edit.get(0).sendKeys("15617685965");
//            login.click();
//            Time(2);
//
//            pass.get(0).sendKeys("12");
//            login.click();
//            Time(2);

            edit.get(0).sendKeys("15617685965");
            pass.get(0).sendKeys("123456");
            login.click();
            Time(2);
            onHome();

        } catch (NoSuchElementException e) {
            try {
                Slide();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
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

    /**
     * 所有override此方法的测试,都是从主页开始的
     * 可在此方法内查找主页的id  进行跳转
     */
    protected abstract void testStart() throws InterruptedException, MalformedURLException;


    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
