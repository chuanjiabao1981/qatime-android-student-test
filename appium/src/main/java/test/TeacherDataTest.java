package test;

import org.junit.Test;
import org.testng.Assert;

import java.net.MalformedURLException;

import io.appium.java_client.android.AndroidElement;
import model.TeacherDataBean;
import model.TeacherRecommendBean;
import util.JsonUtils;

/**
 * @author Tianhaoranly
 * @date 2016/11/1 14:24
 * @Description:
 */
public class TeacherDataTest extends BaseTest {
    public void toTeacherDataPage() throws MalformedURLException, InterruptedException {
        setUp();
        AndroidElement teacherItem = driver.findElementsById("teacher_text").get(0);
        teacherItem.click();
        Time(2);
    }

    @Test
    public void testTeacherDataPage() throws MalformedURLException, InterruptedException {
        toTeacherDataPage();
        String result = request.sendGet("http://testing.qatime.cn/api/v1/recommend/positions/" + "index_teacher_recommend" + "/items");
        TeacherRecommendBean data = JsonUtils.objectFromJson(result, TeacherRecommendBean.class);
        int teacherId = data.getData().get(0).getTeacher().getId();
        String teacherResult = request.sendGet("http://testing.qatime.cn/api/v1/teachers/" + teacherId + "/profile");
        TeacherDataBean.DataBean teacherData = JsonUtils.objectFromJson(teacherResult, TeacherDataBean.class).getData();
        TeacherDataBean.DataBean.Course course = teacherData.getCourses().get(0);//第一条数据

        AndroidElement name = driver.findElementById("name");
        AndroidElement teachAge = driver.findElementById("teach_age");
        AndroidElement school = driver.findElementById("school");
        AndroidElement describe = driver.findElementById("describe");
        AndroidElement title = driver.findElementById("course_title");
        AndroidElement subject = driver.findElementById("subject");
        AndroidElement count = driver.findElementById("count");

        Assert.assertEquals(name.getText(), teacherData.getName());
        Assert.assertEquals(teachAge.getText(), getTeachingYear(teacherData.getTeaching_years()));
        Assert.assertEquals(school.getText(), teacherData.getSchool());
        Assert.assertEquals(describe.getText(), teacherData.getDesc());
        Assert.assertEquals(subject.getText(), course.getSubject());
        Assert.assertEquals(title.getText(), course.getName());
        Assert.assertEquals(count.getText(), course.getBuy_tickets_count() + "人已购买");

        title.click();
        Time(2);
//点击跳转辅导班详情
        String currentActivity = driver.currentActivity();
        Assert.assertEquals(currentActivity,".activity.RemedialClassDetailActivity");

    }

    private String getSex(String gender) {
        if ("male".equals(gender)) {
            return "♂";
        } else if ("female".equals(gender)) {
            return "♀";
        }
        return "";
    }

    private String getTeachingYear(String teaching_years) {
        switch (teaching_years) {
            case "within_three_years":
                return "3年以内";
            case "within_ten_years":
                return "3-10年";
            case "within_twenty_years":
                return "10-20年";
        }
        return "";
    }
}
