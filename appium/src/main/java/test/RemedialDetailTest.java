package test;

import org.junit.Test;
import org.testng.Assert;

import java.io.IOException;
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
import util.JsonUtils;

/**
 * @author luntify
 * @date 2016/8/23 11:07
 * @Description 辅导班详情测试
 */
public class RemedialDetailTest extends BaseTest {

    private RemedialClassDetailBean.Data data;
    DecimalFormat df = new DecimalFormat("#.00");

    /**
     * 进入详情页
     *
     * @param id 进入的条目
     * @throws InterruptedException
     * @throws MalformedURLException
     */


    public void toRemedialDetail(int id) throws InterruptedException, MalformedURLException {
        setUp();


        List<AndroidElement> grid = driver.findElementsById("grid");
        //找到gridview条目中的元素

        String result1 = request.sendGet("http://testing.qatime.cn/api/v1/live_studio/courses/");
        RemedialClassBean remedialClassBean = JsonUtils.objectFromJson(result1, RemedialClassBean.class);

        String result2 = request.sendGet("http://testing.qatime.cn/api/v1/live_studio/courses/" + remedialClassBean.getData().get(id).getId());
        RemedialClassDetailBean remedialClassDetailBean = JsonUtils.objectFromJson(result2, RemedialClassDetailBean.class);

        data = remedialClassDetailBean.getData();

        Time(5);
        List<MobileElement> images = grid.get(0).findElementsById("image");
        images.get(id).click();
        Time(5);

    }

    /**
     * 判断辅导班详情页的信息显示
     *
     * @throws InterruptedException
     */
    @Test
    public void testRemedialDetailDesc() throws InterruptedException, MalformedURLException {

        int testiId = 0;

        toRemedialDetail(testiId);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        driver.swipe(width / 2, height - 100, width / 2, height - 800, 500);
        driver.swipe(width / 2, height - 800, width / 2, height - 100, 500);
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


        Assert.assertEquals(data.getName(), name.getText());
        Assert.assertEquals(data.getName(), title.getText());

        DecimalFormat df = new DecimalFormat("#.00");
        String price_str = df.format(data.getPrice());
        if (price_str.startsWith(".")) {
            price_str = "0" + price_str;
        }

        Assert.assertEquals("￥" + price_str, price.getText());
        Assert.assertEquals("报名人数 " + data.getBuy_tickets_count(), student_number.getText());
        Assert.assertEquals(data.getIs_tasting(), !audition.isEnabled());
        Assert.assertEquals(data.getIs_bought(), !pay.isEnabled());
    }


    /**
     * 加入试听测试
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testAudition() throws IOException, InterruptedException {
        int testiId = 5;

        toRemedialDetail(testiId);


        AndroidElement audition = driver.findElementsById("audition").get(0);
        Assert.assertEquals(data.getIs_tasting(), !audition.isEnabled());

        if (audition.isEnabled()) {
            boolean b = audition.isEnabled();
            System.out.println(b);
            audition.click();
//            driver.sendKeyEvent();
            Time(5);

//            testAudition();
            Assert.assertEquals(audition.isEnabled(), !b);

            AndroidElement back = driver.findElementsByClassName(ClassName.ImageView).get(0);
            back.click();
            testAudition();
        }

    }

    /**
     * 信息详情测试
     *
     * @throws InterruptedException
     */
    @Test
    public void testIndicator1() throws InterruptedException, MalformedURLException {
        int testiId = 0;

        toRemedialDetail(testiId);

        Time(2);

        AndroidElement id_stickynavlayout_indicator = driver.findElementsById("id_stickynavlayout_indicator").get(0);
        List<MobileElement> textviews = id_stickynavlayout_indicator.findElementsByClassName(ClassName.TextView);
        textviews.get(0).click();

        AndroidElement describe = driver.findElementsById("describe").get(0);
        AndroidElement classstarttime = driver.findElementsById("class_start_time").get(0);
        AndroidElement subject = driver.findElementsById("subject").get(0);
        AndroidElement grade = driver.findElementsById("grade").get(0);
        AndroidElement status = driver.findElementsById("status").get(0);
        AndroidElement classendtime = driver.findElementsById("class_end_time").get(0);
        AndroidElement teacher = driver.findElementsById("teacher").get(0);
        AndroidElement totalclass = driver.findElementsById("total_class").get(0);
        AndroidElement remainclass = driver.findElementsById("remain_class").get(0);

        Assert.assertEquals(data.getDescription(), describe.getText());
        Assert.assertEquals("开课时间：" + data.getLive_start_time(), classstarttime.getText());
        Assert.assertEquals("科目类型：" + data.getSubject(), subject.getText());
        Assert.assertEquals("年级类型：" + data.getGrade(), grade.getText());
        Assert.assertEquals(getStatus1(data.getStatus()), status.getText());
        Assert.assertEquals("结课时间：" + data.getLive_end_time(), classendtime.getText());
        Assert.assertEquals("授课教师：" + data.getTeacher_name(), teacher.getText());
        Assert.assertEquals("课时总数：" + data.getPreset_lesson_count(), totalclass.getText());
        Assert.assertEquals("剩余课时：" + (data.getPreset_lesson_count() - data.getCompleted_lesson_count()), remainclass.getText());
    }

    /**
     * 教师详情
     *
     * @throws InterruptedException
     */
    @Test
    public void testIndicator2() throws InterruptedException, MalformedURLException {
        int testiId = 0;

        toRemedialDetail(testiId);


        Time(2);
        AndroidElement id_stickynavlayout_indicator = driver.findElementsById("id_stickynavlayout_indicator").get(0);
        List<MobileElement> textviews = id_stickynavlayout_indicator.findElementsByClassName(ClassName.TextView);
        textviews.get(1).click();

        AndroidElement name = driver.findElementsById("name").get(1);
        AndroidElement teaching_years = driver.findElementsById("teaching_years").get(0);
        AndroidElement subject = driver.findElementsById("subject").get(0);


        Assert.assertEquals("老师姓名：" + data.getTeacher().getName(), name.getText());
        Assert.assertEquals(getTeacherYears(data.getTeacher().getTeaching_years()), teaching_years.getText());
        Assert.assertEquals("所授科目：" + data.getTeacher().getSubject(), subject.getText());

    }

    /**
     * 课程列表
     *
     * @throws InterruptedException
     */
    @Test
    public void testIndicator3() throws InterruptedException, MalformedURLException {
        int testiId = 0;

        toRemedialDetail(testiId);


        Time(2);
        AndroidElement id_stickynavlayout_indicator = driver.findElementsById("id_stickynavlayout_indicator").get(0);
        List<MobileElement> textviews = id_stickynavlayout_indicator.findElementsByClassName(ClassName.TextView);
        textviews.get(2).click();

        AndroidElement listview = driver.findElementByClassName(ClassName.ListView);
        List<MobileElement> class_date = listview.findElementsById("class_date");
        List<MobileElement> live_time = listview.findElementsById("live_time");
        List<MobileElement> status = listview.findElementsById("status");
        List<MobileElement> name = listview.findElementsById("name");

        List<RemedialClassDetailBean.Lessons> lessons = data.getLessons();
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
     * 购买测试
     */
    @Test
    public void testPay() throws MalformedURLException, InterruptedException {
        toRemedialDetail(3);
        AndroidElement pay = driver.findElementById("pay");
        Assert.assertEquals(data.getIs_bought(), !pay.isEnabled());

        if (pay.isEnabled()) {
            Time(2);
            pay.click();
            Time(3);

            AndroidElement name = driver.findElementById("name");
            AndroidElement project = driver.findElementById("project");
            AndroidElement grade = driver.findElementById("grade");
            AndroidElement classnumber = driver.findElementById("class_number");
            AndroidElement teacher = driver.findElementById("teacher");
            AndroidElement payprice = driver.findElementById("pay_price");

            Assert.assertEquals(data.getName(), name.getText());
            Assert.assertEquals("科目类型：" + data.getSubject(), project.getText());
            Assert.assertEquals("年级类型：" + data.getGrade(), grade.getText());
            Assert.assertEquals("课时总数：" + String.valueOf(data.getPreset_lesson_count()), classnumber.getText());
            Assert.assertEquals("授课教师：" + data.getTeacher_name(), teacher.getText());

            String price = df.format(data.getPrice());
            if (price.startsWith(".")) {
                price = "0" + price;
            }
            Assert.assertEquals(" " + price + " ", payprice.getText());

            AndroidElement alipay = driver.findElementById("alipay");
            alipay.click();

            Time(2);

            AndroidElement orderPay = driver.findElementById("pay");
            orderPay.click();

            Time(15);
            AndroidElement orderPrice = driver.findElementById("price");
            Assert.assertEquals("应付金额：￥" + price, orderPrice.getText());

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
        if (status.equals("preview")) {

            return "当前状态：招生中";
        } else if (status.equals("teaching")) {

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
