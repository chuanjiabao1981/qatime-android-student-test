package test;

import org.junit.Test;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.text.DecimalFormat;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import main.ConstantValue;
import model.WithdrawCashRecordBean;
import util.HttpRequest;
import util.JsonUtils;

/**
 * @author Tianhaoranly
 * @date 2016/10/24 10:59
 * @Description:
 */
public class MyWithdrawCashRecordTest extends MyWalletAndRechargeTest {
    /**
     * QTA-58 提现记录测试
     *
     * @throws MalformedURLException
     * @throws InterruptedException
     */
    @Test
    public void testConsumptionRecordFund() throws MalformedURLException, InterruptedException {
        testMyWallet();
        AndroidElement recharge_record = driver.findElementById("withdraw_record");//消费记录
        recharge_record.click();
        Time(2);
        String result = HttpRequest.sendGet("http://testing.qatime.cn/api/v1/payment/users/" + ConstantValue.userId + "/withdraws?page=1");
        WithdrawCashRecordBean bean = JsonUtils.objectFromJson(result, WithdrawCashRecordBean   .class);
        AndroidElement list = driver.findElementById("android:id/list");//list

        if (bean.getData().size() > 0) {
            MobileElement mode = driver.findElementsById("mode").get(0);
            MobileElement time = driver.findElementsById("time").get(0);
            MobileElement money_amount = driver.findElementsById("money_amount").get(0);
            MobileElement status = driver.findElementsById("status").get(0);
            MobileElement id = driver.findElementsById("id").get(0);


            DecimalFormat df = new DecimalFormat("#.00");
            //测试第一条数据显示
            Assert.assertEquals(mode.getText(), getPayType(bean.getData().get(0).getPay_type()));
            Assert.assertEquals(time.getText(), bean.getData().get(0).getCreated_at());
            Assert.assertEquals(id.getText(), bean.getData().get(0).getTransaction_no());

            String amount = df.format(Double.valueOf(bean.getData().get(0).getAmount()));
            if (amount.startsWith(".")) {
                amount = "0" + amount;
            }
            Assert.assertEquals(money_amount.getText(), "-￥" + amount);
            Assert.assertEquals(status.getText(),getStatus(bean.getData().get(0).getStatus()));
        }


    }

    private String getPayType(String pay_type) {
        switch (pay_type) {
            case "bank":
                return "银行卡";
            case "alipay":
                return "支付宝";
        }
        return "银行卡";
    }

    private String getStatus(String status) {
        switch (status) {
            case "init":
                return "审核中";
            case "allowed":
                return "审核通过";
            case "refused":
                return "审核失败";
            case "cancel  ":
                return "已取消";
            default:
                return "已取消";
        }
    }

}
