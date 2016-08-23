package test;

import org.openqa.selenium.NoSuchElementException;

import java.net.MalformedURLException;
import java.util.List;

import io.appium.java_client.android.AndroidElement;
import main.AppiumTest;

/**
 * @author luntify
 * @date 2016/8/22 12:20
 * @Description 主页测试   mainactivity
 */
public class HomeTest extends AppiumTest {
    @Override
    protected void testStart() throws InterruptedException, MalformedURLException {

    }

    @Override
    public void onHome() throws InterruptedException, NoSuchElementException, MalformedURLException {
        super.onHome();

        List<AndroidElement> lastnews = driver.findElementsById("tab_text1");//最新
        List<AndroidElement> whole = driver.findElementsById("tab_text2");//全部

        //最新下滑刷新
        Time(3);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        driver.swipe(width / 2, height - 10, width / 2, height / 2, 500);
        Time(3);
        //全部下滑刷新
        whole.get(0).click();//切换至全部页面
        driver.swipe(width / 2, height - 10, width / 2, height / 2, 500);
        Time(3);
        lastnews.get(0).click();

    }
}
