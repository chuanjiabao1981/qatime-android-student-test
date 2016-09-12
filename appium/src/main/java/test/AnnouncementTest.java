package test;


import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import main.ConstantValue;
import model.Announcements;
import model.TutorialClassBean;
import util.JsonUtils;
import util.StringUtils;
import util.UrlUtils;

/**
 * @author lungtify
 * @date 2016/9/7 20:51
 * @Description: 群组公告/成员列表
 */
public class AnnouncementTest extends BaseTest {
    private Announcements data;
    private TutorialClassBean temp;

    /**
     * 转到直播页
     */
    private void toOnline() throws InterruptedException {
        Map<String, String> map = new HashMap<>();
        map.put("page", String.valueOf(1));
        map.put("per_page", "10");
        map.put("status", "teaching");
        String result1 = request.sendGet(UrlUtils.getUrl(UrlUtils.urlMyRemedialClass + ConstantValue.userId + "/courses", map));
        temp = JsonUtils.objectFromJson(result1, TutorialClassBean.class);

        //转到fragment4
        AndroidElement tab4 = driver.findElementById("tab_text4");
        tab4.click();

        Assert.assertEquals(".activity.MainActivity", driver.currentActivity());

        //已开课
        AndroidElement calssed = driver.findElementById("calssed");
        calssed.click();
        Assert.assertEquals(".activity.PersonalMyTutorshipActivity", driver.currentActivity());
        Time(3);
        List<AndroidElement> list = driver.findElementsById("video");
        //我的辅导班 点击item的position
        clicckItem(list, 1);
    }

    private void clicckItem(List<AndroidElement> list, int position) throws InterruptedException {
        if (list.size() > position) {
            for (int i = 0; i < list.size(); i++) {
                if (i == position) {
                    list.get(position).click();
                    break;
                }
            }
            String result2 = request.sendGet("http://testing.qatime.cn/api/v1/live_studio/courses/" + temp.getData().get(position).getId() + "/realtime");
            data = JsonUtils.objectFromJson(result2, Announcements.class);
            //直播页面
            Time(2);
        } else {
            list.get(list.size() - 1).click();
            String result2 = request.sendGet("http://testing.qatime.cn/api/v1/live_studio/courses/" + temp.getData().get(list.size() - 1).getId() + "/realtime");
            data = JsonUtils.objectFromJson(result2, Announcements.class);
            //直播页面
            Time(2);
        }
    }

    /**
     * QTA-29群组公告
     */
    @Test
    public void testAnnouncement() throws MalformedURLException, InterruptedException {
        setUp();
        toOnline();
        Assert.assertEquals(driver.currentActivity(), ".activity.NEVideoPlayerActivity");

        List<AndroidElement> list = driver.findElementsById("time");

        Assert.assertEquals(data.getData().getAnnouncements().size(), list.size());

        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(StringUtils.isNullOrBlanK(data.getData().getAnnouncements().get(i).getEdit_at()) ? "2016-00-00 00:00:00" : data.getData().getAnnouncements().get(i).getEdit_at(), list.get(i).getText());
            Assert.assertEquals(StringUtils.isNullOrBlanK(data.getData().getAnnouncements().get(i).getAnnouncement()) ? "本节课需要大家提前预习内容，以免手忙脚乱听不懂！" : data.getData().getAnnouncements().get(i).getAnnouncement(), driver.findElementsById("describe").get(i).getText());
        }
        println("群组公告测试完成");
    }

    /**
     * QTA-28成员列表
     */
    @Test
    public void testMember() throws MalformedURLException, InterruptedException {
        setUp();
        toOnline();
        Assert.assertEquals(driver.currentActivity(), ".activity.NEVideoPlayerActivity");
        //转到第四页
        driver.findElementById("tab_text4").click();
        Time(2);

        List<AndroidElement> list = driver.findElementsById("image");

        Assert.assertEquals(data.getData().getMembers().size(), list.size());

        for (Announcements.DataBean.MembersBean item : data.getData().getMembers()) {
            if (StringUtils.isNullOrBlanK(item.getName())) {
                item.setFirstLetter("");
            } else {
                item.setFirstLetter(StringUtils.getPYIndexStr(item.getName().substring(0, 1)));
            }
        }

        Collections.sort(data.getData().getMembers(), new Comparator<Announcements.DataBean.MembersBean>() {
            @Override
            public int compare(Announcements.DataBean.MembersBean lhs, Announcements.DataBean.MembersBean rhs) {
                return lhs.getFirstLetter().compareTo(rhs.getFirstLetter());
            }
        });
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(StringUtils.isNullOrBlanK(data.getData().getMembers().get(i).getName()) ? "名字" : data.getData().getMembers().get(i).getName(), driver.findElementById("listview").findElementsById("name").get(i).getText());
        }

        println("成员列表测试完成");
    }
}
