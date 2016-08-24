package test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;

import java.net.MalformedURLException;
import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import main.AppiumTest;

/**
 * @author luntify
 * @date 2016/8/23 13:26
 */
public class MyTutorshipTest extends AppiumTest {
    @Override
    protected void testStart() throws InterruptedException, MalformedURLException {
        //转到fragment4
//        AndroidElement tab4 = driver.findElementById("tab_text4");
//        tab4.click();
//
//        //已开课
//        AndroidElement calssed = driver.findElementById("calssed");
//        calssed.click();
//        Time(3);
//        //课程表tab点击
////        totalship();
//        //直播
//
    }

    /**
     * 我的辅导班测试QTA-47
     */
    @Test
    public void testMyTutorship() throws Exception {
        test();
        //转到fragment4
        AndroidElement tab4 = driver.findElementById("tab_text4");
        tab4.click();
        Time(2);

        //已开课
        AndroidElement calssed = driver.findElementById("calssed");
        calssed.click();
        Time(3);
        //课程表tab点击
        totalship();
    }

    /**
     * qta-48直播测试
     *
     * @throws Exception
     */
    @Test
    public void testNEVideoPlayer() throws Exception {
        test();
        //转到fragment4
        AndroidElement tab4 = driver.findElementById("tab_text4");
        tab4.click();

        //已开课
        AndroidElement calssed = driver.findElementById("calssed");
        calssed.click();
        Time(3);
        List<AndroidElement> list = driver.findElementsById("list");
        if (list.size() > 0) {
            list.get(0).findElementById("video").click();
            //直播页面
            Time(1);
            try {
                videoClick();
            } catch (NoSuchElementException e) {
                System.out.print("error-------------------------------------");
                e.printStackTrace();
            }
        }
    }

    private void videoClick() throws InterruptedException, NoSuchElementException {
        AndroidElement videoPlayer = driver.findElementById("video_player");//点击空屏幕

        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;

        Time(2);
        MobileElement play = driver.findElementById("play");//播放暂停
        play.click();
        Time(2);
        play.click();
        Time(2);
        videoPlayer.click();

        AndroidElement zoom = driver.findElementById("zoom");//全屏
        zoom.click();
        Time(2);

        AndroidElement playerexit = driver.findElementById("player_exit");//全屏返回
        playerexit.click();
        Time(2);
        zoom.click();//二次全屏
        Time(2);
        playerexit.click();
        Time(2);

        AndroidElement livedetails = driver.findElementById("tab_text3");//直播详情
        livedetails.click();
        Time(15);


        //直播详情测试
        List<AndroidElement> name = driver.findElementsById("name");
//        Assert.assertEquals("课程名称：", name.get(0).getText());//辅导班名称
        List<AndroidElement> teacher = driver.findElementsById("teacher");
//        Assert.assertEquals("授课教师：", teacher.get(0).getText());//授课老师
//        Assert.assertEquals("课程名称：", name.get(1).getText());//教师详情-教师姓名

        List<AndroidElement> list = driver.findElementsById("list");
//        Assert.assertEquals(0, list.size());

//        AndroidElement notice = driver.findElementById("tab_text1");//公告
//        notice.click();
//        Time(3);
//        AndroidElement chat = driver.findElementById("tab_text2");//聊天
//        chat.click();
//        Time(3);
//        AndroidElement member = driver.findElementById("tab_text4");//成员列表
//        member.click();
//        Time(3);
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
