package test;

import org.junit.Test;
import org.testng.Assert;

import java.net.MalformedURLException;

import io.appium.java_client.android.AndroidElement;
import main.ClassName;
import main.ConstantValue;
import model.PersonalInformationBean;
import model.TutorialClassBean;
import util.JsonUtils;

/**
 * Created by lenovo on 2016/8/30.
 */
public class SecurityManagerTest extends BaseTest {
    /**
     * 进入安全管理页面
     *
     * @throws InterruptedException
     * @throws MalformedURLException
     */


    public void toSecurityManager() throws MalformedURLException, InterruptedException {
        setUp();
        String result = request.sendGet("http://testing.qatime.cn/api/v1/live_studio/students/" + ConstantValue.userId + "/courses?status=teaching&page=1&per_page=10");
        TutorialClassBean data = JsonUtils.objectFromJson(result, TutorialClassBean.class);
        //转到fragment4
        AndroidElement tab4 = driver.findElementById("tab_text4");
        tab4.click();
        Time(2);
        //转到安全管理
        AndroidElement security = driver.findElementById("security");
        security.click();
        Time(2);
    }

    @Test
    public void testSecurityManagerPage() throws MalformedURLException, InterruptedException {
        toSecurityManager();

        AndroidElement phoneM = driver.findElementById("phone_number_m");
        AndroidElement phoneP = driver.findElementById("phone_number_p");
        AndroidElement email = driver.findElementById("email");

        String result = request.sendGet("http://testing.qatime.cn/api/v1/students/" + ConstantValue.userId + "/info");
        PersonalInformationBean bean = JsonUtils.objectFromJson(result, PersonalInformationBean.class);

        Assert.assertEquals(phoneM.getText(), (bean.getData().getLogin_mobile()) == null ? "未绑定" : bean.getData().getLogin_mobile());
        Assert.assertEquals(phoneP.getText(), (bean.getData().getParent_phone()) == null ? "未绑定" : bean.getData().getParent_phone());
        Assert.assertEquals(email.getText(), (bean.getData().getEmail()) == null ? "未绑定" : bean.getData().getEmail());

    }

    @Test
    public void testVerifyPhonePage() throws MalformedURLException, InterruptedException {
        toSecurityManager();
        //跳到验证手机页面
        AndroidElement bindPhone = driver.findElementById("bind_phone_number");
        bindPhone.click();
        Time(2);


        AndroidElement currentPhone = driver.findElementById("current_phone");
        AndroidElement code = driver.findElementById("code");
        AndroidElement getCode = driver.findElementById("text_getcode");
        AndroidElement next = driver.findElementById("button_next");


        String result = request.sendGet("http://testing.qatime.cn/api/v1/students/" + ConstantValue.userId + "/info");
        PersonalInformationBean bean = JsonUtils.objectFromJson(result, PersonalInformationBean.class);

        Assert.assertEquals(currentPhone.getText(), (bean.getData().getLogin_mobile()) == null ? "未绑定" : bean.getData().getLogin_mobile());

        getCode.click();
        Time(2);
        Assert.assertFalse(getCode.isEnabled());

    }

    @Test
    public void testParentPhone() throws MalformedURLException, InterruptedException {
        toSecurityManager();
        //跳到验证手机页面
        AndroidElement parentPhone = driver.findElementById("parent_phone_number");
        parentPhone.click();
        Time(2);


        AndroidElement currentParentPhone = driver.findElementById("current_parent_phone");
        AndroidElement getCode = driver.findElementById("text_getcode");
        AndroidElement newPhone = driver.findElementById("new_parent_phone");


        String result = request.sendGet("http://testing.qatime.cn/api/v1/students/" + ConstantValue.userId + "/info");
        PersonalInformationBean bean = JsonUtils.objectFromJson(result, PersonalInformationBean.class);

        Assert.assertEquals(currentParentPhone.getText(), (bean.getData().getParent_phone()) == null ? "未绑定" : bean.getData().getParent_phone());

        newPhone.sendKeys("15638780663");
        Time(2);
        getCode.click();
        Time(2);
        Assert.assertFalse(getCode.isEnabled());

    }

    @Test
    public void testChangePWD() throws MalformedURLException, InterruptedException {
        toSecurityManager();
        //跳到验证手机页面
        AndroidElement changePWD = driver.findElementById("change_password");
        changePWD.click();
        Time(2);


        AndroidElement forgetPwd = driver.findElementById("forget_password");


        forgetPwd.click();
        Time(2);

        String result = request.sendGet("http://testing.qatime.cn/api/v1/students/" + ConstantValue.userId + "/info");
        PersonalInformationBean bean = JsonUtils.objectFromJson(result, PersonalInformationBean.class);

        AndroidElement currentPhone = driver.findElementById("current_phone");
        AndroidElement newPass = driver.findElementById("new_pass");
        Assert.assertEquals(currentPhone.getText(), (bean.getData().getLogin_mobile()) == null ? "未绑定" : bean.getData().getLogin_mobile());

        AndroidElement back = driver.findElementsByClassName(ClassName.ImageView).get(0);
        back.click();
        Time(2);

        AndroidElement password = driver.findElementById("password");
        AndroidElement newPwd1 = driver.findElementById("new_password");
        AndroidElement newPwd2 = driver.findElementById("confirm_new_password");
        AndroidElement over = driver.findElementById("button_over");

        password.sendKeys("qqqqqq");
        Time(2);
        newPwd1.sendKeys("111111");
        Time(2);
        newPwd2.sendKeys("111111");
        Time(2);
        over.click();
        Time(2);


        //判断是否在登录页面

        AndroidElement register = driver.findElementById("register");
        AndroidElement login = driver.findElementById("login");

        //使用新密码登陆
        ConstantValue.password = "111111";

        login();
        onHome();
    }

    @Test
    public void testBindPhonePage() throws MalformedURLException, InterruptedException {
        toSecurityManager();
//自动跳不过去
//        AndroidElement currentPhone = driver.findElementById("current_phone");
//        AndroidElement code = driver.findElementById("code");
//        AndroidElement getCode = driver.findElementById("text_getcode");
//        AndroidElement next = driver.findElementById("button_next");
//
//        String result = request.sendGet("http://testing.qatime.cn/api/v1/students/" + ConstantValue.userId + "/info");
//        PersonalInformationBean bean = JsonUtils.objectFromJson(result, PersonalInformationBean.class);
//
//        Assert.assertEquals(currentPhone.getText(), bean.getData().getLogin_mobile());
//
//        getCode.click();
//        Time(2);
//        Assert.assertFalse(getCode.isEnabled());

    }


}
