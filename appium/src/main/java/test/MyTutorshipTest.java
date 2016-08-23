package test;

import java.net.MalformedURLException;
import java.util.List;

import io.appium.java_client.TouchAction;
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
        totalship();
//直播
        List<AndroidElement> list = driver.findElementsById("list");
        if (list.size() > 0) {
            list.get(0).findElementById("video").click();
            //直播页面
            Time(1);
            AndroidElement videoPlayer = driver.findElementById("video_player");//点击空屏幕
            videoPlayer.click();
            int width = driver.manage().window().getSize().width;
            int height = driver.manage().window().getSize().height;
            new TouchAction(driver).press(videoPlayer, width / 2, height - 100).release();

            Time(1);
            AndroidElement play = driver.findElementById("play");//播放暂停
            play.click();
            Time(2);
            play.click();
            Time(2);

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
            Time(3);
//
//            int width = driver.manage().window().getSize().width;
//            int height = driver.manage().window().getSize().height;//详情页内部上下滑动 高度待定
            driver.swipe(height - width - 5, height / 5, width - 5, height / 2, 200);
            Time(2);
            driver.swipe(height - width - 5, height / 5, width - 5, height / 2, 200);
            Time(2);
            driver.swipe(height - width - 5, height / 2, width - 5, height / 5, 200);
            Time(2);
            driver.swipe(height - width - 5, height / 2, width - 5, height / 5, 200);
            Time(2);

            AndroidElement notice = driver.findElementById("tab_text1");//公告
            notice.click();
            Time(3);
            driver.swipe(width / 2, height - 10, width / 2, height / 2, 500);//公告上拉刷新
            Time(3);
            AndroidElement chat = driver.findElementById("tab_text2");//聊天
            chat.click();
            Time(3);
            AndroidElement menber = driver.findElementById("tab_text4");//成员列表
            menber.click();
            Time(3);
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
