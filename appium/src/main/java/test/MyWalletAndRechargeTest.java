package test;

import org.junit.Test;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import main.ConstantValue;
import model.RechargeOrderBean;
import model.RechargeRecodBean;
import model.TutorialClassBean;
import model.WalletCashBean;
import util.HttpRequest;
import util.JsonUtils;
import util.UrlUtils;

/**
 * @author Tianhaoranly
 * @date 2016/9/29 20:31
 * @Description:
 */
public class MyWalletAndRechargeTest extends BaseTest {
    /**
     * 跳转到钱包页面
     */
    private void toMyWalletActivity() throws InterruptedException, MalformedURLException {
        setUp();
        String result = request.sendGet("http://testing.qatime.cn/api/v1/live_studio/students/" + ConstantValue.userId + "/courses?status=teaching&page=1&per_page=10");
        TutorialClassBean data = JsonUtils.objectFromJson(result, TutorialClassBean.class);
        //转到fragment4
        AndroidElement tab4 = driver.findElementById("tab_text4");
        tab4.click();
        Time(2);
        //转到安全管理
        AndroidElement myWallet = driver.findElementById("cn.qatime.player:id/my_wallet");
        myWallet.click();
        Time(2);
    }

    /**
     * QTA-52 我的钱包测试
     */
    @Test
    public void testMyWallet() throws MalformedURLException, InterruptedException {
        toMyWalletActivity();
        Assert.assertEquals(".activity.PersonalMyWalletActivity", driver.currentActivity());//判断是不是MyWalletActivity
        String s = request.sendGet(UrlUtils.urlPayment + ConstantValue.userId + "/cash");
        WalletCashBean bean = JsonUtils.objectFromJson(s, WalletCashBean.class);
        DecimalFormat df = new DecimalFormat("#.00");
        String price = df.format(Double.valueOf(bean.getData().getBalance()));
        if (price.startsWith(".")) {
            price = "0" + price;
        }
        AndroidElement balance = driver.findElementById("cn.qatime.player:id/balance");//钱包余额
        Assert.assertEquals(balance.getText().toString(), price);

        price = df.format(Double.valueOf(bean.getData().getTotal_expenditure()));

        AndroidElement consumption = driver.findElementById("cn.qatime.player:id/consumption");//累计消费

        if (price.startsWith(".")) {
            price = "0" + price;
        }
        Assert.assertEquals(consumption.getText().toString(), price);
    }

    /**
     * 充值测试
     *
     * @throws MalformedURLException
     * @throws InterruptedException
     */
    @Test
    public void testRecharge() throws MalformedURLException, InterruptedException {
        testMyWallet();
        AndroidElement recharge = driver.findElementById("cn.qatime.player:id/recharge");
        recharge.click();
        Time(2);


        Assert.assertEquals(".activity.RechargeActivity", driver.currentActivity());//RechargeActivity
        AndroidElement num = driver.findElementById("cn.qatime.player:id/recharge_num");//edittext
        AndroidElement weixin = driver.findElementById("cn.qatime.player:id/wechat_pay");//钱包余额
        num.sendKeys("1000.00");//限制只能有2位小数

        String pay_type = weixin.isSelected() ? "weixin" : "alipay";

        Map<String, String> map = new HashMap<>();
        map.put("amount", num.getText().toString());
        map.put("pay_type", pay_type);
        String s = request.sendPost(UrlUtils.urlPayment + ConstantValue.userId + "/recharges", UrlUtils.getUrl("", map));
        RechargeOrderBean bean = JsonUtils.objectFromJson(s, RechargeOrderBean.class);

        AndroidElement recharge_now = driver.findElementById("cn.qatime.player:id/recharge_now");//立即充值按钮
        recharge_now.click();
        Time(5);


        Assert.assertEquals(".activity.RechargeConfirmActivity", driver.currentActivity());//RechargeConfirmActivity支付确认页面


        AndroidElement id = driver.findElementById("cn.qatime.player:id/id");//订单id
        Assert.assertEquals(bean.getData().getId(), id.getText().toString());

        AndroidElement time = driver.findElementById("cn.qatime.player:id/time");//订单时间
        Assert.assertEquals(bean.getData().getCreated_at(), time.getText().toString());

        AndroidElement mode = driver.findElementById("cn.qatime.player:id/mode");//支付方式
        Assert.assertEquals(getPayType(bean.getData().getPay_type()), mode.getText().toString());

        DecimalFormat df = new DecimalFormat("#.00");
        String price = df.format(Double.valueOf(bean.getData().getAmount()));
        if (price.startsWith(".")) {
            price = "0" + price;
        }
        price = "￥" + price;
        AndroidElement amount = driver.findElementById("cn.qatime.player:id/amount");//金额
        Assert.assertEquals(price, amount.getText().toString());


    }

    /**
     * QTA-52  充值记录
      * @throws MalformedURLException
     * @throws InterruptedException
     */
    @Test
    public void testReCordFund() throws MalformedURLException, InterruptedException {
        testMyWallet();
        AndroidElement recharge_record = driver.findElementById("cn.qatime.player:id/recharge_record");//充值记录
        recharge_record.click();
        Time(2);
        String result = HttpRequest.sendGet("http://testing.qatime.cn/api/v1/payment/users/" + ConstantValue.userId + "/recharges?page=1&end_date=14752150462350&start_date=0");
        RechargeRecodBean bean = JsonUtils.objectFromJson(result, RechargeRecodBean.class);
        AndroidElement list = driver.findElementById("android:id/list");//list

        if (bean.getData().size() > 0) {
            MobileElement id = driver.findElementsById("cn.qatime.player:id/id").get(0);
            MobileElement mode = driver.findElementsById("cn.qatime.player:id/mode").get(0);
            MobileElement time = driver.findElementsById("cn.qatime.player:id/time").get(0);
            MobileElement money_amount = driver.findElementsById("cn.qatime.player:id/money_amount").get(0);
            MobileElement status = driver.findElementsById("cn.qatime.player:id/status").get(0);


            DecimalFormat df = new DecimalFormat("#.00");
            //测试第一条数据显示
            Assert.assertEquals(id.getText(), bean.getData().get(0).getId());
            Assert.assertEquals(mode.getText(), getPayType(bean.getData().get(0).getPay_type()));
            Assert.assertEquals(time.getText(), bean.getData().get(0).getCreated_at());

            String amount = df.format(Double.valueOf(bean.getData().get(0).getAmount()));
            if (amount.startsWith(".")) {
                amount = "0" + amount;
            }
            Assert.assertEquals(money_amount.getText(),"+￥"+ amount);
            Assert.assertEquals(status.getText(), getStatus(bean.getData().get(0).getStatus()));
        }


    }

    private String getStatus(String status) {
        switch (status) {
            case "unpaid":
                return "未支付";
            case "alipay":
                return "已支付";
            case "canceled":
                return "订单取消";
        }
        return "未支付";
    }

    private String getPayType(String pay_type) {
        switch (pay_type) {
            case "weixin":
                return "微信支付";
            case "alipay":
                return "支付宝";
            case "offline":
                return "线下支付";
        }
        return "未支付";
    }
}
