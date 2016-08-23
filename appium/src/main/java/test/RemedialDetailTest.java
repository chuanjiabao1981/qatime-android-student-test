package test;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;

import java.net.MalformedURLException;
import java.util.List;

import io.appium.java_client.android.AndroidElement;
import main.AppiumTest;

/**
 * @author luntify
 * @date 2016/8/23 11:07
 * @Description  辅导班详情测试
 */
public class RemedialDetailTest extends AppiumTest {
    @Override
    protected void testStart() throws InterruptedException, MalformedURLException {
        try {
            Time(5);
            List<AndroidElement> root = driver.findElementsById("grid");
            if (root.size() > 0) {
                root.get(0).click();
            }

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}
