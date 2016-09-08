package test;


import org.junit.Assert;
import org.openqa.jetty.util.StringUtil;
import org.openqa.selenium.NoSuchElementException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import io.appium.java_client.android.AndroidElement;
import main.BaseAppiumTest;
import main.ConstantValue;
import util.StringUtils;

public class BaseTest extends BaseAppiumTest {

    /**
     * 登录并转到主页
     * 若要单独测试登录,需将@Test加上
     */
//    @Test
    public void setUp() throws InterruptedException, MalformedURLException {
        Time(3);
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
            AndroidElement register = driver.findElementById("register");
            AndroidElement login_error = driver.findElementById("login_error");
            Assert.assertTrue(login.isDisplayed());
            Assert.assertTrue(register.isDisplayed());
            Assert.assertTrue(login_error.isDisplayed());
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


            edit.get(0).sendKeys(ConstantValue.phone);
            pass.get(0).sendKeys(ConstantValue.password);
            login.click();
            Time(2);
            onHome();
        } catch (NoSuchElementException e) {
//            e.printStackTrace();
            System.out.println("roll to slide");
            try {
                Slide();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void println(Object object) {
        if (StringUtils.isNullOrBlanK(object)) {
            System.out.println("测试完成");
            return;
        }
        System.out.println(object);
    }

    /**
     * 系统按键
     * 电话键
     * <p>
     * KEYCODE_CALL 拨号键 5
     * KEYCODE_ENDCALL 挂机键 6
     * KEYCODE_HOME 按键Home 3
     * KEYCODE_MENU 菜单键 82
     * KEYCODE_BACK 返回键 4
     * KEYCODE_SEARCH 搜索键 84
     * KEYCODE_CAMERA 拍照键 27
     * KEYCODE_FOCUS 拍照对焦键 80
     * KEYCODE_POWER 电源键 26
     * KEYCODE_NOTIFICATION 通知键 83
     * KEYCODE_MUTE 话筒静音键 91
     * KEYCODE_VOLUME_MUTE 扬声器静音键 164
     * KEYCODE_VOLUME_UP 音量增加键 24
     * KEYCODE_VOLUME_DOWN 音量减小键 25
     * <p>
     * 控制键
     * <p>
     * KEYCODE_ENTER 回车键 66
     * KEYCODE_ESCAPE ESC键 111
     * KEYCODE_DPAD_CENTER 导航键 确定键 23
     * KEYCODE_DPAD_UP 导航键 向上 19
     * KEYCODE_DPAD_DOWN 导航键 向下 20
     * KEYCODE_DPAD_LEFT 导航键 向左 21
     * KEYCODE_DPAD_RIGHT 导航键 向右 22
     * KEYCODE_MOVE_HOME 光标移动到开始键 122
     * KEYCODE_MOVE_END 光标移动到末尾键 123
     * KEYCODE_PAGE_UP 向上翻页键 92
     * KEYCODE_PAGE_DOWN 向下翻页键 93
     * KEYCODE_DEL 退格键 67
     * KEYCODE_FORWARD_DEL 删除键 112
     * KEYCODE_INSERT 插入键 124
     * KEYCODE_TAB Tab键 61
     * KEYCODE_NUM_LOCK 小键盘锁 143
     * KEYCODE_CAPS_LOCK 大写锁定键 115
     * KEYCODE_BREAK Break/Pause键 121
     * KEYCODE_SCROLL_LOCK 滚动锁定键 116
     * KEYCODE_ZOOM_IN 放大键 168
     * KEYCODE_ZOOM_OUT 缩小键 169
     */
    public void xitongkey(int num) throws InterruptedException, MalformedURLException {
        String cmdstr = "adb shell input keyevent " + num;
        try {
            Runtime.getRuntime().exec(cmdstr).waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread.sleep(10000);
    }
}
