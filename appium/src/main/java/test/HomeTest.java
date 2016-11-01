package test;

import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.text.DecimalFormat;

import io.appium.java_client.android.AndroidElement;
import model.ClassRecommendBean;
import model.TeacherRecommendBean;
import util.JsonUtils;

/**
 * @author luntify
 * @date 2016/8/22 12:20
 * @Description 主页测试   mainactivity
 */
public class HomeTest extends BaseTest {
    DecimalFormat df = new DecimalFormat("#.00");

    /***
     * 主页
     */
    @Test
    public void testHome() throws InterruptedException, NoSuchElementException, MalformedURLException {
        setUp();
        String teacherResult = request.sendGet("http://testing.qatime.cn/api/v1/recommend/positions/" + "index_teacher_recommend" + "/items");
        TeacherRecommendBean teacherData = JsonUtils.objectFromJson(teacherResult, TeacherRecommendBean.class);
        String classResult = request.sendGet("http://testing.qatime.cn/api/v1/recommend/positions/" + "index_live_studio_course_recommend" + "/items");
        ClassRecommendBean classData = JsonUtils.objectFromJson(classResult, ClassRecommendBean.class);
        ClassRecommendBean.DataBean.LiveStudioCourseBean course = classData.getData().get(0).getLive_studio_course();

        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        driver.swipe(width / 2, height - 200, width / 2, 300, 500);

        Time(3);
        AndroidElement name = driver.findElementById("cn.qatime.player:id/teacher_text");
        AndroidElement title = driver.findElementById("cn.qatime.player:id/course_title");
        AndroidElement subject = driver.findElementById("cn.qatime.player:id/subject");
        AndroidElement count = driver.findElementById("cn.qatime.player:id/count");


        Assert.assertEquals(name.getText(), teacherData.getData().get(0).getTeacher().getName());
        Assert.assertEquals(subject.getText(), course.getSubject());
        Assert.assertEquals(title.getText(), course.getName());
        Assert.assertEquals(count.getText(), course.getBuy_tickets_count() + "人已购");

        title.click();
        Time(2);
        //点击跳转辅导班详情
        String currentActivity = driver.currentActivity();
        Assert.assertEquals(currentActivity, ".activity.RemedialClassDetailActivity");

        //点击跳转辅教师公开页
        back();
        Time(2);
        name.click();
        Time(2);
        currentActivity = driver.currentActivity();
        Assert.assertEquals(currentActivity, ".activity.TeacherDataActivity");

        back();
        Time(2);
        driver.swipe(width / 2,300, width / 2,  height - 200, 500);
        Time(3);
        AndroidElement refreshTeacher = driver.findElementById("cn.qatime.player:id/refresh_teacher");
        refreshTeacher.click();
        Time(2);
        //刷新后数据不相同
        Assert.assertNotEquals(name.getText(), teacherData.getData().get(0).getTeacher().getName());
        //点击全部跳转
        AndroidElement allClass = driver.findElementById("cn.qatime.player:id/all_class");
        allClass.click();
        Time(2);
        //辅导班筛选全部
        AndroidElement subjectText = driver.findElementById("cn.qatime.player:id/subject_text");
        Assert.assertEquals(subjectText.getText(), "全部");
        //返回首页
        AndroidElement tab = driver.findElementById("cn.qatime.player:id/tab_text1");
        tab.click();
        Time(2);
        //点击科目跳转
        AndroidElement homeSubject = driver.findElementById("cn.qatime.player:id/subject_text");
        homeSubject.click();
        Time(2);
        //辅导班筛选科目
        subjectText = driver.findElementById("cn.qatime.player:id/subject_text");
        Assert.assertEquals(subjectText.getText(), homeSubject.getText());
        System.out.println("首页测试完成");
    }

    private String getReason(String reason) {
        if ("latest".equals(reason)) {
            return "最新";
        } else if ("hottest".equals(reason)) {
            return "最热";
        }
        return "";
    }
}
