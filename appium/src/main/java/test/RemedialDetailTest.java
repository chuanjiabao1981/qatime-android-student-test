package test;

import org.openqa.selenium.NoSuchElementException;

import java.net.MalformedURLException;
import java.util.List;

import io.appium.java_client.android.AndroidElement;
import util.HttpRequest;

/**
 * @author luntify
 * @date 2016/8/23 11:07
 * @Description 辅导班详情测试
 */
public class RemedialDetailTest extends LoginTest {
    @Override
    protected void testStart() throws InterruptedException, MalformedURLException {
        try {

            Time(5);
            List<AndroidElement> root = driver.findElementsById("grid");
            System.out.print(root.size());
            if (root.size() > 0) {
                List<AndroidElement> f1 = driver.findElementsById(root.get(0).getId());
                f1.get(0).click();
            }
            HttpRequest request = new HttpRequest();
            String result = request.sendGet("http://testing.qatime.cn/api/v1/live_studio/courses/5");


//                AndroidElement image = driver.findElementsById("image").get(0);
//            AndroidElement name = driver.findElementsById("name").get(0);
//            AndroidElement price = driver.findElementsById("price").get(0);
//            AndroidElement audition = driver.findElementsById("cn.qatime.player:id/audition").get(0);
//            AndroidElement pay = driver.findElementsById("cn.qatime.player:id/pay").get(0);
//                AndroidElement title = driver.findElementsById("title").get(0);

//                AndroidElement student_number = driver.findElementsById("student_number").get(0);
//                AndroidElement id_stickynavlayout_indicator = driver.findElementsById("id_stickynavlayout_indicator").get(0);
//                AndroidElement id_stickynavlayout_viewpager = driver.findElementsById("id_stickynavlayout_viewpager").get(0);

//            RemedialClassDetailBean data = JsonUtils.objectFromJson(result, RemedialClassDetailBean.class);

//            Assert.assertEquals(data.getData().getName(), name.getText());
//                Assert.assertEquals(data.getData().getName(), title.getText());
//                Assert.assertEquals("" + data.getData().getPrice(), price.getText());
//                Assert.assertEquals("" + data.getData().getBuy_tickets_count(), student_number.getText());
//                Assert.assertFalse(audition.isEnabled());
//                Assert.assertTrue(pay.isEnabled());


        } catch (NoSuchElementException e) {

            e.printStackTrace();
        }
    }
}
