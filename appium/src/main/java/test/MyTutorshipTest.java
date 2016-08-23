package test;

import java.net.MalformedURLException;
import java.util.List;

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
        //课程表tab点击
//        totalship();

        List<AndroidElement> list = driver.findElementsById("list");
        if (list.size() > 0) {
            list.get(0).findElementById("video").click();
            //直播页面

        }
    }


    public void totalship() throws InterruptedException {
        AndroidElement tabText1 = driver.findElementById("tab_text1");
        tabText1.click();
        Time(3);
        AndroidElement tabText2 = driver.findElementById("tab_text2");
        tabText2.click();
        Time(3);
        AndroidElement tabText5 = driver.findElementById("tab_text5");
        tabText5.click();
        Time(3);
        AndroidElement tabText4 = driver.findElementById("tab_text4");
        tabText4.click();
        Time(3);
        AndroidElement tabText3 = driver.findElementById("tab_text3");
        tabText3.click();
        Time(3);
    }


}
