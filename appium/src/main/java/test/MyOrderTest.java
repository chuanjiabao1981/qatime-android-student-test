package test;

import org.junit.Test;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import main.ClassName;
import model.MyPaidOrderBean;
import util.HttpRequest;
import util.JsonUtils;

/**
 * Created by lenovo on 2016/9/7.
 */
public class MyOrderTest extends BaseTest {
    public void toMyOrder() throws MalformedURLException, InterruptedException {
        setUp();
        //转到fragment4
        AndroidElement tab4 = driver.findElementById("tab_text4");
        tab4.click();
        Time(2);
//        paying
        //到代付款
        AndroidElement paying = driver.findElementById("paying");
        paying.click();
        Time(2);
    }

    /**
     * 待付款测试
     */
    @Test
    public void testMyOrder1() throws MalformedURLException, InterruptedException {
        toMyOrder();
        String result = HttpRequest.sendGet("http://testing.qatime.cn/api/v1/payment/orders?cate=unpaid&page=1&per_page=10");
        MyPaidOrderBean bean = JsonUtils.objectFromJson(result, MyPaidOrderBean.class);
        AndroidElement list = driver.findElementById("android:id/list");

        List<MobileElement> item = list.findElementsByClassName(ClassName.ImageView);
        //每页最多五个
        
        Assert.assertEquals(bean.data.size() > 5 ? 5 : bean.data.size(), item.size());
//        classname
//        grade
//        subject
//        teacher
//        progress
//        price
//        cancel_order
//        pay

        if (bean.data.size() > 0) {
            MobileElement classname = driver.findElementsById("classname").get(0);
            MobileElement grade = driver.findElementsById("grade").get(0);
            MobileElement subject = driver.findElementsById("subject").get(0);
            MobileElement teacher = driver.findElementsById("teacher").get(0);
            MobileElement progress = driver.findElementsById("progress").get(0);
            MobileElement cancel = driver.findElementsById("cancel_order").get(0);
            MobileElement pay = driver.findElementsById("pay").get(0);
            MobileElement price = driver.findElementsById("price").get(0);


            DecimalFormat df = new DecimalFormat("#.00");
            //测试第一条数据显示
            Assert.assertEquals(classname.getText(), bean.data.get(0).product.name);
            Assert.assertEquals(grade.getText(), bean.data.get(0).product.grade);
            Assert.assertEquals(subject.getText(), bean.data.get(0).product.subject);
            Assert.assertEquals(teacher.getText(), bean.data.get(0).product.teacher_name);
            Assert.assertEquals(progress.getText(), bean.data.get(0).product.completed_lesson_count + "/" + bean.data.get(0).product.preset_lesson_count);
            String s = df.format(bean.data.get(0).product.price);
            Assert.assertEquals(price.getText(), "￥" + (s.startsWith(".") ? "0" + s : s));
            cancel.click();
            //        android:id/button1  确定取消按钮
            AndroidElement button1 = driver.findElementById("android:id/button1");
            button1.click();
        }
//如果有2条以上订单再测试下数据显示
        if (bean.data.size() > 1) {
            result = HttpRequest.sendGet("http://testing.qatime.cn/api/v1/payment/orders?cate=unpaid&page=1&per_page=10");
            bean = JsonUtils.objectFromJson(result, MyPaidOrderBean.class);
            Time(2);
            item = list.findElementsByClassName(ClassName.ImageView);
            Assert.assertEquals(bean.data.size(), item.size());
        }
    }

    /**
     * 已经付款测试
     */
    @Test
    public void testMyOrder2() throws MalformedURLException, InterruptedException {
        toMyOrder();
        toMyOrder();
        //跳到已付款   没有已付款信息
        AndroidElement tab2 = driver.findElementById("tab_text2");
        tab2.click();
        Time(2);
        String result = HttpRequest.sendGet("http://testing.qatime.cn/api/v1/payment/orders?cate=canceled&page=1&per_page=10");
        MyPaidOrderBean bean = JsonUtils.objectFromJson(result, MyPaidOrderBean.class);
        AndroidElement list = driver.findElementById("android:id/list");

        List<MobileElement> item = list.findElementsByClassName(ClassName.ImageView);
        //每页5个
        Assert.assertEquals(bean.data.size() > 5 ? 5 : bean.data.size(), item.size());
//        classname
//        grade
//        subject
//        teacher
//        progress
//        price
//        cancel_order
//        pay

        if (bean.data.size() > 0) {
            MobileElement classname = driver.findElementsById("classname").get(0);
            MobileElement grade = driver.findElementsById("grade").get(0);
            MobileElement subject = driver.findElementsById("subject").get(0);
            MobileElement teacher = driver.findElementsById("teacher").get(0);
            MobileElement progress = driver.findElementsById("progress").get(0);
            MobileElement price = driver.findElementsById("price").get(0);


            DecimalFormat df = new DecimalFormat("#.00");
            //测试第一条数据显示
            Assert.assertEquals(classname.getText(), bean.data.get(0).product.name);
            Assert.assertEquals(grade.getText(), bean.data.get(0).product.grade);
            Assert.assertEquals(subject.getText(), bean.data.get(0).product.subject);
            Assert.assertEquals(teacher.getText(), bean.data.get(0).product.teacher_name);
            Assert.assertEquals(progress.getText(), bean.data.get(0).product.completed_lesson_count + "/" + bean.data.get(0).product.preset_lesson_count);
            String s = df.format(bean.data.get(0).product.price);
            Assert.assertEquals(price.getText(), "￥" + (s.startsWith(".") ? "0" + s : s));
            //        android:id/button1  确定取消按钮
        }
    }

    /**
     * 已取消付款测试
     */
    @Test
    public void testMyOrder3() throws MalformedURLException, InterruptedException {
        toMyOrder();
        //跳到已取消
        AndroidElement tab3 = driver.findElementById("tab_text3");
        tab3.click();
        Time(2);
        String result = HttpRequest.sendGet("http://testing.qatime.cn/api/v1/payment/orders?cate=canceled&page=1&per_page=10");
        MyPaidOrderBean bean = JsonUtils.objectFromJson(result, MyPaidOrderBean.class);
        AndroidElement list = driver.findElementById("android:id/list");

        List<MobileElement> item = list.findElementsByClassName(ClassName.ImageView);
        //每页5个
        Assert.assertEquals(bean.data.size() > 5 ? 5 : bean.data.size(), item.size());
//        classname
//        grade
//        subject
//        teacher
//        progress
//        price
//        cancel_order
//        pay

        if (bean.data.size() > 0) {
            MobileElement classname = driver.findElementsById("classname").get(0);
            MobileElement grade = driver.findElementsById("grade").get(0);
            MobileElement subject = driver.findElementsById("subject").get(0);
            MobileElement teacher = driver.findElementsById("teacher").get(0);
            MobileElement progress = driver.findElementsById("progress").get(0);
            MobileElement price = driver.findElementsById("price").get(0);


            DecimalFormat df = new DecimalFormat("#.00");
            //测试第一条数据显示
            Assert.assertEquals(classname.getText(), bean.data.get(0).product.name);
            Assert.assertEquals(grade.getText(), bean.data.get(0).product.grade);
            Assert.assertEquals(subject.getText(), bean.data.get(0).product.subject);
            Assert.assertEquals(teacher.getText(), bean.data.get(0).product.teacher_name);
            Assert.assertEquals(progress.getText(), bean.data.get(0).product.completed_lesson_count + "/" + bean.data.get(0).product.preset_lesson_count);
            String s = df.format(bean.data.get(0).product.price);
            Assert.assertEquals(price.getText(), "￥" + (s.startsWith(".") ? "0" + s : s));
            //        android:id/button1  确定取消按钮
        }
    }
}
