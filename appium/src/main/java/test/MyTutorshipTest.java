package test;

import java.net.MalformedURLException;

import io.appium.java_client.android.AndroidElement;
import main.AppiumTest;

/**
 * @author luntify
 * @date 2016/8/23 13:26
 * @Description 我的辅导班测试
 */
public class MyTutorshipTest extends AppiumTest {
    @Override
    protected void testStart() throws InterruptedException, MalformedURLException {
        //转到fragment4
        AndroidElement tab4 = driver.findElementById("tab_text4");
        tab4.click();

        //已开课
        AndroidElement calssed = driver.findElementById("calssed");
        calssed.click();
        Time(3);

        
    }
}
