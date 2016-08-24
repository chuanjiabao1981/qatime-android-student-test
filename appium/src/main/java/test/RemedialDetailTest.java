package test;

import java.net.MalformedURLException;

import org.openqa.selenium.NoSuchElementException;

import java.net.MalformedURLException;
import java.util.List;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import util.HttpRequest;

/**
 * @author luntify
 * @date 2016/8/23 11:07
 * @Description 辅导班详情测试
 */
public class RemedialDetailTest extends LoginTest {
    @Override
    protected void testStart() throws InterruptedException, MalformedURLException {
        try {

            Time(5);
            List<AndroidElement> root = driver.findElementsById("grid");
            if (root.size() > 0) {
                root.get(0).click();
            }
            HttpRequest request = new HttpRequest();
            request.sendGet("http://testing.qatime.cn/api/v1/live_studio/courses/5");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}
