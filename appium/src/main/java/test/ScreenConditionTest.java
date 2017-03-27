package test;

import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.List;

import io.appium.java_client.android.AndroidElement;
import model.FilterCourseContentBean;
import util.JsonUtils;

/**
 * @author lungtify
 * @Time 2017/3/27 15:07
 * @Describe
 */

public class ScreenConditionTest extends BaseTest {
    @Test
    public void testScreenSubject() throws MalformedURLException, InterruptedException {
        String result = request.sendGet("http://testing.qatime.cn/api/v1/live_studio/courses/search?per_page=20&sort_by=published_at.asc&page=1&q[grade_eq]=%E9%AB%98%E4%B8%89");
        FilterCourseContentBean data = JsonUtils.objectFromJson(result, FilterCourseContentBean.class);
        setUp();
        AndroidElement tab2 = driver.findElementById("tab_text2");
        tab2.click();
        Time(2);

        //点击全部进入筛选页面
        driver.findElementByName("全部").click();

//        driver.findElementByName("最新").click();

        List<AndroidElement> names = driver.findElementsById("name");
        for (int i = 0; i < names.size(); i++) {
            Assert.assertEquals(data.getData().get(i).getName(), names.get(i).getText());
            Assert.assertEquals("￥" + data.getData().get(i).getPrice(), driver.findElementsById("price").get(i).getText());
            Assert.assertEquals(data.getData().get(i).getTeacher_name(), driver.findElementsById("teacher").get(i).getText());
        }
    }

}
