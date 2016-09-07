package test;

import org.junit.Test;
import org.testng.Assert;

import java.net.MalformedURLException;

import io.appium.java_client.android.AndroidElement;
import model.UpdateInformationBean;
import util.JsonUtils;

/**
 * Created by lenovo on 2016/9/6.
 */
public class UpdateTest extends BaseTest {

    private UpdateInformationBean bean;
    //判断是否打开过dialog,在点击x号时赋值为true，默认false，false状态下可以
    private boolean status;

    @Test
    public void testStartDialog() throws InterruptedException, MalformedURLException {
        Time(3);

        String s = request.sendGet("http://testing.qatime.cn/api/v1/system/check_update?category=student_client&page=1&platform=android");
        bean = JsonUtils.objectFromJson(s, UpdateInformationBean.class);
        if (bean.data == null) {
            onHome();
        } else {
//            cn.qatime.player:id/new_version
//            cn.qatime.player:id/desc
//            cn.qatime.player:id/download
//            cn.qatime.player:id/text_x
            testShowDialog();


            AndroidElement download = driver.findElementById("download");
            AndroidElement textX = driver.findElementById("text_x");
            if (!status) {
                if (bean.data.enforce) {
                    textX.click();
                    status = true;
                    Time(2);
                    //强制更新，判断是否在showdialog
                    testShowDialog();
                } else {
                    textX.click();
                    Time(2);
                    //非强制更新，判断是否在主页
                    onHome();
                }
            } else {
                //走else说明是第二次进入
                //点击下载,会暂时进入主页
                download.click();
                Time(2);
                onHome();
            }
        }
    }

    @Test
    public void testFragment4() throws InterruptedException, MalformedURLException {
        String s = request.sendGet("http://testing.qatime.cn/api/v1/system/check_update?category=student_client&page=1&platform=android");
        bean = JsonUtils.objectFromJson(s, UpdateInformationBean.class);
        testStartDialog();

        AndroidElement tab4 = driver.findElementById("tab_text4");
        tab4.click();
        Time(2);
        AndroidElement newVersion = driver.findElementById("new_version");
        if (bean.data == null) {
            //没有新版本提示
            Assert.assertEquals("", newVersion.getText());
        } else {
            //没有新版本提示
            //cn.qatime.player:id/new_version
            Assert.assertEquals("新版本", newVersion.getText());
        }
    }

    @Test
    public void testSystemSettingUpdate() throws InterruptedException, MalformedURLException {
        String s = request.sendGet("http://testing.qatime.cn/api/v1/system/check_update?category=student_client&page=1&platform=android");
        bean = JsonUtils.objectFromJson(s, UpdateInformationBean.class);

        testFragment4();
        Time(3);

        AndroidElement setting = driver.findElementById("cn.qatime.player:id/setting");
        setting.click();
        Time(2);

        AndroidElement checkUpdate = driver.findElementById("cn.qatime.player:id/check_update");
        checkUpdate.click();
        Time(2);

        if (bean.data == null) {
            //没有新版本

        } else {
            //没有新版本提示
            //cn.qatime.player:id/new_version
            Time(2);
            testShowDialog();

            //能进入app说明没有强制更新的版本
//            点击任意位置或者x更新对话框消失
            AndroidElement textX = driver.findElementById("text_x");
            textX.click();
            Time(2);
            //判断在系统设置页面
            driver.findElementById("cn.qatime.player:id/check_update");
        }
    }


    private void testShowDialog() throws InterruptedException, MalformedURLException {
        AndroidElement newVersion = driver.findElementById("new_version");
        AndroidElement desc = driver.findElementById("desc");
        AndroidElement download = driver.findElementById("download");
        AndroidElement textX = driver.findElementById("text_x");

        Assert.assertEquals("V" + bean.data.version, newVersion.getText());
        Assert.assertEquals(bean.data.description, desc.getText());


    }
}
