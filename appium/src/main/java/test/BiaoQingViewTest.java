package test;

import org.junit.Test;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import main.ClassName;

/**
 * Created by lenovo on 2016/9/6.
 */
public class BiaoQingViewTest extends BaseTest {

    public void toMessagePage() throws MalformedURLException, InterruptedException {
        setUp();
        //转到fragment3
        AndroidElement tab3 = driver.findElementById("tab_text3");
        tab3.click();
        Time(2);

        AndroidElement list = driver.findElementById("android:id/list");
        list.findElementsByClassName(ClassName.RelativeLayout).get(0).click();


    }

    public void toPlayerPage() throws MalformedURLException, InterruptedException {
        setUp();
        //转到fragment4
        AndroidElement tab4 = driver.findElementById("tab_text4");
        tab4.click();
        Time(2);
        //转已经开课
        AndroidElement calssed = driver.findElementById("calssed");
        calssed.click();
        Time(2);
        //转到player
        List<AndroidElement> player = driver.findElementsById("video");
        player.get(0).click();
        Time(2);

        //转到聊天
        AndroidElement tabText2 = driver.findElementById("tab_text2");
        tabText2.click();
        Time(2);


    }

    @Test
    public void testMessageBiaoQingView() throws InterruptedException, MalformedURLException {
        toMessagePage();

        AndroidElement emoji = driver.findElementById("emoji");
        emoji.click();
        Time(2);
        AndroidElement grid = driver.findElementByClassName(ClassName.GridView);
        List<MobileElement> imageView = grid.findElementsByClassName(ClassName.ImageView);

        imageView.get(0).click();

        AndroidElement content = driver.findElementById("content");

        Assert.assertEquals(content.getText(), "[em_1]");

        imageView.get(0).click();

        Assert.assertEquals(content.getText(), "[em_1][em_1]");
        //删除
        imageView.get(27).click();

        Assert.assertEquals(content.getText(), "[em_1]");
    }

    @Test
    public void testPlayerBiaoQingView() throws InterruptedException, MalformedURLException {
        toPlayerPage();

        AndroidElement emoji = driver.findElementById("emoji");
        emoji.click();
        Time(2);
        AndroidElement grid = driver.findElementByClassName(ClassName.GridView);
        List<MobileElement> imageView = grid.findElementsByClassName(ClassName.ImageView);

        imageView.get(0).click();

        AndroidElement content = driver.findElementById("content");

        Assert.assertEquals(content.getText(), "[em_1]");

        imageView.get(0).click();

        Assert.assertEquals(content.getText(), "[em_1][em_1]");
        //删除
        imageView.get(27).click();

        Assert.assertEquals(content.getText(), "[em_1]");
    }
}
