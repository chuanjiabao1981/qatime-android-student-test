package test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;

import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.List;

import io.appium.java_client.android.AndroidElement;
import model.RemedialClassBean;
import util.JsonUtils;
import util.StringUtils;

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
        String result = request.sendGet("http://testing.qatime.cn/api/v1/live_studio/courses?page=1&per_page=10");
        RemedialClassBean data = JsonUtils.objectFromJson(result, RemedialClassBean.class);
        List<AndroidElement> lastnews = driver.findElementsById("tab_text1");//最新
        List<AndroidElement> whole = driver.findElementsById("tab_text2");//全部
        List<AndroidElement> grid = driver.findElementsById("grid");//列表

        Time(4);
        Assert.assertEquals("最新", lastnews.get(0).getText());
        Assert.assertEquals("全部", whole.get(0).getText());
        for (int i = 0; i < grid.size(); i++) {
            Assert.assertEquals(StringUtils.isNullOrBlanK(data.getData().get(i).getName()) ? "高级辅导班" : data.getData().get(i).getName(), grid.get(i).findElementById("name").getText());
            Assert.assertEquals(StringUtils.isNullOrBlanK(data.getData().get(i).getSubject()) ? "科目" : data.getData().get(i).getSubject(), grid.get(i).findElementById("subject").getText());
            Assert.assertEquals(StringUtils.isNullOrBlanK(data.getData().get(i).getGrade()) ? "年级" : data.getData().get(i).getGrade(), grid.get(i).findElementById("grade").getText());
            Assert.assertEquals(StringUtils.isNullOrBlanK(data.getData().get(i).getTeacher_name()) ? "老师" : data.getData().get(i).getTeacher_name(), grid.get(i).findElementById("teacher").getText());
            String price = df.format(data.getData().get(i).getPrice());
            if (price.startsWith(".")) {
                price = "0" + price;
            }
            Assert.assertEquals(StringUtils.isNullOrBlanK(data.getData().get(i).getPrice()) ? "价格" : "￥" + price, grid.get(i).findElementById("price").getText());
            Assert.assertEquals(StringUtils.isNullOrBlanK(String.valueOf(data.getData().get(i).getBuy_tickets_count())) ? "人数" : String.valueOf(data.getData().get(i).getBuy_tickets_count()), grid.get(i).findElementById("student_number").getText());
        }

        //最新下滑刷新
        Time(3);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        driver.swipe(width / 2, height - 200, width / 2, 300, 500);
        driver.swipe(width / 2, height - 200, width / 2, 300, 500);
        driver.swipe(width / 2, height - 200, width / 2, 300, 500);
        Time(3);
        driver.swipe(width / 2, height - 200, width / 2, 300, 500);
        Time(5);
        //全部下滑刷新
        whole.get(0).click();//切换至全部页面
        driver.swipe(width / 2, height - 200, width / 2, 300, 500);
        Time(3);
        lastnews.get(0).click();
        System.out.println("首页测试完成");
    }
}
