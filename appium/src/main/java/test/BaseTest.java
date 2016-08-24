package test;


import org.openqa.selenium.NoSuchElementException;

import java.net.MalformedURLException;
import java.util.List;

import io.appium.java_client.android.AndroidElement;
import main.BaseAppiumTest;

public class BaseTest extends BaseAppiumTest {

    /**
     * 登录并转到主页
     * 若要单独测试登录,需将@Test加上
     */
//    @Test
    public void setUp() throws InterruptedException, MalformedURLException {
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
            login.click();
            Time(2);

//            edit.get(0).sendKeys("15638780663");
//            login.click();
//            Time(2);
//
//            pass.get(0).sendKeys("12");
//            login.click();
//            Time(2);
//
//            edit.get(0).sendKeys("15638780663");
//            pass.get(0).sendKeys("123456");
//            login.click();
//            Time(2);


            edit.get(0).sendKeys("15638780663");
            pass.get(0).sendKeys("111111");
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
}