package test;

import org.junit.Test;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.text.DecimalFormat;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import main.ConstantValue;
import model.ConsumptionRecordBean;
import util.HttpRequest;
import util.JsonUtils;

/**
 * @author Tianhaoranly
 * @date 2016/10/11 10:59
 * @Description:
 */
public class MyConsumptionRecordTest extends MyWalletAndRechargeTest {
    /**
     * QTA-52  充值记录
     *
     * @throws MalformedURLException
     * @throws InterruptedException
     */
    @Test
    public void testConsumptionRecordFund() throws MalformedURLException, InterruptedException {
        testMyWallet();
        AndroidElement recharge_record = driver.findElementById("cn.qatime.player:id/consumption_record");//消费记录
        recharge_record.click();
        Time(2);

        String result = HttpRequest.sendGet("http://testing.qatime.cn/api/v1/payment/users/" + ConstantValue.userId + "/consumption_records?page=1");
        ConsumptionRecordBean bean = JsonUtils.objectFromJson(result, ConsumptionRecordBean.class);
        AndroidElement list = driver.findElementById("android:id/list");//list

        if (bean.getData().size() > 0) {
            MobileElement mode = driver.findElementsById("cn.qatime.player:id/mode").get(0);
            MobileElement time = driver.findElementsById("cn.qatime.player:id/time").get(0);
            MobileElement money_amount = driver.findElementsById("cn.qatime.player:id/money_amount").get(0);
            MobileElement type = driver.findElementsById("cn.qatime.player:id/type").get(0);


            DecimalFormat df = new DecimalFormat("#.00");
            //测试第一条数据显示
            Assert.assertEquals(mode.getText(), getChangeType(bean.getData().get(0).getChange_type()));
            Assert.assertEquals(time.getText(), bean.getData().get(0).getCreated_at());

            String amount = df.format(0 - Double.valueOf(bean.getData().get(0).getAmount()));
            if (amount.startsWith(".")) {
                amount = "0" + amount;
            }
            Assert.assertEquals(money_amount.getText(), "-￥" + amount);
            Assert.assertEquals(type.getText(), bean.getData().get(0).getTarget_type());
        }


    }

    private String getChangeType(String change_type) {
        switch (change_type) {
            case "weixin":
                return "微信支付";
            case "alipay":
                return "支付宝";
            case "account":
                return "余额支付";
        }
        return "余额支付";
    }

}
