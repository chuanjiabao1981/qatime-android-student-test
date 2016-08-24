package test;

import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import main.ClassName;
import model.RemedialClassBean;
import model.RemedialClassDetailBean;
import util.HttpRequest;
import util.JsonUtils;

/**
 * @author luntify
 * @date 2016/8/23 11:07
 * @Description 辅导班详情测试
 */
public class RemedialDetailTest extends BaseTest {

    private RemedialClassDetailBean.Data bean;

    @Test
    public void testRemedialDetail() throws InterruptedException, MalformedURLException {
        try {
            Time(2);
            List<AndroidElement> grid = driver.findElementsById("grid");
            //找到gridview条目中的元素
            int i = 0;

            String result = request.sendGet("http://testing.qatime.cn/api/v1/live_studio/courses/");
            RemedialClassBean bean = JsonUtils.objectFromJson(result, RemedialClassBean.class);

            remedialDetailDesc(grid, i, bean.getData().get(i).getId());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断辅导班详情页的信息显示
     *
     * @param grid 首页-最新-gridview
     * @param item item id
     * @param id   辅导班详情id
     * @throws InterruptedException
     */
    private void remedialDetailDesc(List<AndroidElement> grid, int item, int id) throws InterruptedException {

        Time(5);
        List<MobileElement> images = grid.get(0).findElementsById("image");
        images.get(item).click();
        Time(5);
//            AndroidElement image = driver.findElementsById("image").get(0);

        //辅导班详情页面元素
        AndroidElement name = driver.findElementsById("name").get(0);
        AndroidElement price = driver.findElementsById("price").get(0);
        AndroidElement audition = driver.findElementsById("audition").get(0);
        AndroidElement pay = driver.findElementsById("pay").get(0);
        AndroidElement title = driver.findElementsById("title").get(0);
        AndroidElement student_number = driver.findElementsById("student_number").get(0);

        AndroidElement id_stickynavlayout_indicator = driver.findElementsById("id_stickynavlayout_indicator").get(0);

        List<MobileElement> textviews = id_stickynavlayout_indicator.findElementsByClassName(ClassName.TextView);


        String result = request.sendGet("http://testing.qatime.cn/api/v1/live_studio/courses/" + id);

        RemedialClassDetailBean data = JsonUtils.objectFromJson(result, RemedialClassDetailBean.class);
        bean = data.getData();


        Assert.assertEquals(data.getData().getName(), name.getText());
        Assert.assertEquals(data.getData().getName(), title.getText());

        DecimalFormat df = new DecimalFormat("#.00");
        String price_str = df.format(data.getData().getPrice());
        if (price_str.startsWith(".")) {
            price_str = "0" + price_str;
        }


        Assert.assertEquals("￥" + price_str, price.getText());
        Assert.assertEquals("报名人数 " + data.getData().getBuy_tickets_count(), student_number.getText());
        Assert.assertEquals(data.getData().getIs_tasting(), audition.isEnabled());
        Assert.assertEquals(data.getData().getIs_bought(), pay.isEnabled());


        textviews.get(0).click();
        testIndicator1();

        textviews.get(1).click();
        testIndicator2();

        textviews.get(2).click();
        testIndicator3();

    }

    /**
     * 信息详情测试
     *
     * @throws InterruptedException
     */
    @Test
    public void testIndicator1() throws InterruptedException {
        Time(2);
        AndroidElement describe = driver.findElementsById("describe").get(0);
        AndroidElement classstarttime = driver.findElementsById("class_start_time").get(0);
        AndroidElement subject = driver.findElementsById("subject").get(0);
        AndroidElement grade = driver.findElementsById("grade").get(0);
        AndroidElement status = driver.findElementsById("status").get(0);
        AndroidElement classendtime = driver.findElementsById("class_end_time").get(0);
        AndroidElement teacher = driver.findElementsById("teacher").get(0);
        AndroidElement totalclass = driver.findElementsById("total_class").get(0);
        AndroidElement remainclass = driver.findElementsById("remain_class").get(0);

        Assert.assertEquals(bean.getDescription(), describe.getText());
        Assert.assertEquals("开课时间：" + bean.getLive_start_time(), classstarttime.getText());
        Assert.assertEquals("科目类型：" + bean.getSubject(), subject.getText());
        Assert.assertEquals("年级类型：" + bean.getGrade(), grade.getText());
        Assert.assertEquals(getStatus1(bean.getStatus()), status.getText());
        Assert.assertEquals("结课时间：" + bean.getLive_end_time(), classendtime.getText());
        Assert.assertEquals("授课教师：" + bean.getTeacher_name(), teacher.getText());
        Assert.assertEquals("课时总数：" + bean.getPreset_lesson_count(), totalclass.getText());
        Assert.assertEquals("剩余课时：" + (bean.getPreset_lesson_count() - bean.getCompleted_lesson_count()), remainclass.getText());
    }

    /**
     * 教师详情
     *
     * @throws InterruptedException
     */
    private void testIndicator2() throws InterruptedException {
        Time(2);

        AndroidElement name = driver.findElementsById("name").get(1);
        AndroidElement teaching_years = driver.findElementsById("teaching_years").get(0);
        AndroidElement subject = driver.findElementsById("subject").get(0);


        Assert.assertEquals("老师姓名：" + bean.getTeacher().getName(), name.getText());
        Assert.assertEquals(getTeacherYears(bean.getTeacher().getTeaching_years()), teaching_years.getText());
        Assert.assertEquals("所授科目：" + bean.getTeacher().getSubject(), subject.getText());

    }

    /**
     * 课程列表
     *
     * @throws InterruptedException
     */
    private void testIndicator3() throws InterruptedException {
        Time(2);
        AndroidElement listview = driver.findElementByClassName(ClassName.ListView);
        List<MobileElement> class_date = listview.findElementsById("class_date");
        List<MobileElement> live_time = listview.findElementsById("live_time");
        List<MobileElement> status = listview.findElementsById("status");
        List<MobileElement> name = listview.findElementsById("name");

        List<RemedialClassDetailBean.Lessons> lessons = bean.getLessons();
        RemedialClassDetailBean.Lessons item = lessons.get(lessons.size() - 1);

        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Assert.assertEquals(format.format(parse.parse(item.getClass_date())), class_date.get(class_date.size() - 1).getText());
            Assert.assertEquals(item.getLive_time(), live_time.get(class_date.size() - 1).getText());
            Assert.assertEquals(getStatus2(item.getStatus()), status.get(class_date.size() - 1).getText());
            Assert.assertEquals(item.getName(), name.get(class_date.size() - 1).getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }



    /**
     * 直播状态
     *
     * @param status
     * @return
     */
    private String getStatus2(String status) {
        if (status.equals("teaching")) {//直播中
            return "直播中";
        } else if (status.equals("paused")) {
            return "直播中";
        } else if (status.equals("init")) {//未开始
            return "未开始";
        } else if (status.equals("ready")) {//待开课
            return "待开课";
        } else if (status.equals("paused_inner")) {//暂停中
            return "暂停中";
        } else {//已结束
            return "已结束";
        }
    }

    /**
     * 招生状态
     *
     * @param status
     * @return
     */
    private String getStatus1(String status) {
        if (bean.getStatus().equals("preview")) {

            return "当前状态：招生中";
        } else if (bean.getStatus().equals("teaching")) {

            return "当前状态：已开课";

        } else {

            return "当前状态：已结束";
        }
    }

    /**
     * 执教年龄
     *
     * @param years
     * @return
     */
    private String getTeacherYears(String years) {

        if (years.equals("within_three_years")) {
            return "执教年龄：3年以内";
        } else if (years.equals("within_ten_years")) {
            return "执教年龄：3-10年";
        } else if (years.equals("within_twenty_years")) {
            return "执教年龄：10-20年";
        } else {
            return "执教年龄：20年以上";
        }
    }
}
