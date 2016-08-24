package test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import model.RemedialClassDetailBean;
import util.JsonUtils;

/**
 * @author luntify
 * @date 2016/8/23 13:26
 */
public class MyTutorshipTest extends BaseTest {

    /**
     * QTA-47我的辅导班测试
     */
    @Test
    public void testMyTutorship() throws Exception {
        setUp();
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
     */
    @Test
    public void testNEVideoPlayer() throws Exception {
        setUp();
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

        String result = request.sendGet("http://testing.qatime.cn/api/v1/live_studio/courses/5");

        RemedialClassDetailBean data = JsonUtils.objectFromJson(result, RemedialClassDetailBean.class);

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
        Assert.assertEquals("课程名称：" + data.getData().getName(), name.get(0).getText());//辅导班名称
        List<AndroidElement> teacher = driver.findElementsById("teacher");
        Assert.assertEquals("授课教师：" + data.getData().getTeacher().getName(), teacher.get(0).getText());//授课老师

        driver.swipe(width / 2, height - 100, width / 2, height - 700, 500);
        Time(3);
        AndroidElement teacherName = driver.findElementById("name");
        Assert.assertEquals("老师姓名：" + data.getData().getTeacher().getName(), teacherName.getText());//教师详情-教师姓名

        driver.swipe(width / 2, height - 100, width / 2, height - 700, 500);
        Time(3);
//        List<AndroidElement> list = driver.findElementsById("list");
//        Assert.assertEquals(data.getData().getLessons().size(), list.size());

        driver.swipe(width / 2, height - 700, width / 2, height - 100, 500);
        Time(3);
        driver.swipe(width / 2, height - 700, width / 2, height - 100, 500);
        Time(3);

        AndroidElement notice = driver.findElementById("tab_text1");//公告
        notice.click();
        Time(3);
        AndroidElement chat = driver.findElementById("tab_text2");//聊天
        chat.click();
        Time(3);
        AndroidElement member = driver.findElementById("tab_text4");//成员列表
        member.click();
        Time(3);
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
