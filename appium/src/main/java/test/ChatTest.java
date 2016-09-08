package test;

import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.List;

import io.appium.java_client.android.AndroidElement;
import main.ClassName;

/**
 * @author lungtify
 * @date 2016/9/8 15:20
 * @Description: 聊天
 */
public class ChatTest extends BaseTest {

    public void toMessagePage() throws MalformedURLException, InterruptedException {
        setUp();
        //转到fragment3
        AndroidElement tab3 = driver.findElementById("tab_text3");
        tab3.click();
        Time(2);

        AndroidElement list = driver.findElementById("android:id/list");
        list.findElementsByClassName(ClassName.RelativeLayout).get(0).click();

    }

    /**
     * QTA-27聊天
     */
    @Test
    public void testChat() throws MalformedURLException, InterruptedException {
        toMessagePage();

        Assert.assertEquals(".activity.MessageActivity", driver.currentActivity());

        List<AndroidElement> content = driver.findElementsById("content");
        AndroidElement send = driver.findElementById("send");
        List<AndroidElement> first = driver.findElementsById("right");

        setMessage(content, "testing");
        send.click();
        Time(2);
        List<AndroidElement> second = driver.findElementsById("right");

        Assert.assertTrue(second.size() > first.size());

        setMessage(content, "testmessage");
        send.click();
        Time(2);
        List<AndroidElement> three = driver.findElementsById("right");

        Assert.assertTrue(three.size() > second.size());

    }

    private void setMessage(List<AndroidElement> content, String str) {
        content.get(0).sendKeys(str);
        Assert.assertEquals(str, content.get(0).getText());
    }
}
