package test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.net.MalformedURLException;

import io.appium.java_client.android.AndroidElement;
import main.ClassName;
import main.ConstantValue;
import model.TutorialClassBean;
import util.JsonUtils;

/**
 * Created by lenovo on 2016/8/30.
 */
public class SystemSetingTest extends BaseTest {

    /**
     * 进入系统设置页面
     *
     * @throws InterruptedException
     * @throws MalformedURLException
     */


    public void toSystemSetting() throws MalformedURLException, InterruptedException {
        setUp();
        String result = request.sendGet("http://testing.qatime.cn/api/v1/live_studio/students/" + ConstantValue.userId + "/courses?status=teaching&page=1&per_page=10");
        TutorialClassBean data = JsonUtils.objectFromJson(result, TutorialClassBean.class);
        //转到fragment4
        AndroidElement tab4 = driver.findElementById("tab_text4");
        tab4.click();
        Time(2);
        //转到安全管理
        AndroidElement setting = driver.findElementById("setting");
        setting.click();
        Time(2);
    }

    @Test
    public void testSystemSettingPage() throws MalformedURLException, InterruptedException {
        toSystemSetting();

        AndroidElement learning = driver.findElementById("learning_process");
        AndroidElement notify = driver.findElementById("notify_setting");
        AndroidElement update = driver.findElementById("check_update");
        AndroidElement clean = driver.findElementById("clean_cache");
        AndroidElement about = driver.findElementById("about");


        update.click();
        Time(2);
        AndroidElement download = driver.findElementById("download");

        download.click();

    }

    @Test
    public void testNotifySettingPage() throws MalformedURLException, InterruptedException {
        toSystemSetting();

        AndroidElement notify = driver.findElementById("notify_setting");

        notify.click();
        Time(2);

        AndroidElement message = driver.findElementById("notify_message");
        AndroidElement classes = driver.findElementById("notify_classes");


//        AndroidElement back = driver.findElementsByClassName(ClassName.ImageView).get(0);
//        back.click();
//        Time(2);
    }

    @Test
    public void testMessageSettingPage() throws MalformedURLException, InterruptedException {
        testNotifySettingPage();

        AndroidElement notify = driver.findElementById("notify_message");
        notify.click();
        Time(2);

        AndroidElement cbVoice = driver.findElementById("cb_voice");
        AndroidElement cbShake = driver.findElementById("cb_shake");

        String checked1 = cbVoice.getAttribute("checked");
        String checked2 = cbShake.getAttribute("checked");

        AndroidElement voice = driver.findElementById("voice");
        AndroidElement shake = driver.findElementById("shake");
        voice.click();
        shake.click();
        Time(2);


        Assert.assertNotEquals(checked1, cbVoice.getAttribute("checked"));
        Assert.assertNotEquals(checked2, cbShake.getAttribute("checked"));


        AndroidElement back = driver.findElementsByClassName(ClassName.ImageView).get(0);
        back.click();

        Time(2);
        notify.click();
        Time(2);

        Assert.assertNotEquals(checked1, cbVoice.getAttribute("checked"));
        Assert.assertNotEquals(checked2, cbShake.getAttribute("checked"));
    }

    @Test
    public void testCourseSettingPage() throws MalformedURLException, InterruptedException {
        testNotifySettingPage();

        AndroidElement classes = driver.findElementById("notify_classes");
        classes.click();
        Time(2);

        AndroidElement cb1 = driver.findElementById("cb_1");
        AndroidElement cb2 = driver.findElementById("cb_2");
        AndroidElement sms = driver.findElementById("sms");
        AndroidElement sys = driver.findElementById("sys");
        AndroidElement sHours = driver.findElementById("spinner_hours");
        AndroidElement sMinute = driver.findElementById("spinner_minute");


        sHours.click();
        Time(2);
//        List<MobileElement> sHoursList = sHours.findElementsByClassName(ClassName.TextView);
        driver.scrollToExact("10小时");
        AndroidElement tenH = driver.findElement(By.name("10小时"));
        tenH.click();

        sMinute.click();
        Time(2);
//        List<MobileElement> sMinuteList = sMinute.findElementsByClassName(ClassName.TextView);
        driver.scrollToExact("10分钟");
        AndroidElement tenM = driver.findElement(By.name("10分钟"));
        tenM.click();
//        sHoursList.get(10).click();
//        sMinuteList.get(10).click();

        Assert.assertEquals(sHours.findElementsByClassName(ClassName.TextView).get(0).getText(), "10小时");
        Assert.assertEquals(sMinute.findElementsByClassName(ClassName.TextView).get(0).getText(), "10分钟");


        String b1 = cb1.getAttribute("checked");
        String b2 = cb2.getAttribute("checked");
        String b3 = sms.getAttribute("checked");
        String b4 = sys.getAttribute("checked");

        cb1.click();
        cb2.click();
        sys.click();
        sms.click();

        Time(2);

        Assert.assertNotEquals(cb1.getAttribute("checked"), b1);
        Assert.assertNotEquals(cb2.getAttribute("checked"), b2);
        Assert.assertNotEquals(sms.getAttribute("checked"), b3);
        Assert.assertNotEquals(sys.getAttribute("checked"), b4);

        AndroidElement back = driver.findElementsByClassName(ClassName.ImageView).get(0);
        back.click();

        Time(2);
        classes.click();
        Time(2);

        Assert.assertEquals(sHours.findElementsByClassName(ClassName.TextView).get(0).getText(), "10小时");
        Assert.assertEquals(sMinute.findElementsByClassName(ClassName.TextView).get(0).getText(), "10分钟");
        Assert.assertNotEquals(cb1.getAttribute("checked"), b1);
        Assert.assertNotEquals(cb2.getAttribute("checked"), b2);
        Assert.assertNotEquals(sms.getAttribute("checked"), b3);
        Assert.assertNotEquals(sys.getAttribute("checked"), b4);
    }

    @Test
    public void testAboutUs() throws MalformedURLException, InterruptedException {
        toSystemSetting();

        AndroidElement about = driver.findElementById("about");
        about.click();
        Time(2);

        AndroidElement callPhone = driver.findElementById("call_phone");
        AndroidElement phone1 = driver.findElementById("phone");
        String phone = phone1.getText();
        Assert.assertEquals(phone,"0353-2135828");
        callPhone.click();
        Time(2);
        //拨打电话
        AndroidElement button = driver.findElementById("android:id/button2");
        button.click();
        Time(2);
    }

    @Test
    public void exitLogin() throws MalformedURLException, InterruptedException {
        toSystemSetting();

        AndroidElement exit = driver.findElementById("exit");
        exit.click();
        Time(2);

        login();
        onHome();

    }
}
