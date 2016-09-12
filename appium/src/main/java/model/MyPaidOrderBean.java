package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lenovo on 2016/9/7.
 */
public class MyPaidOrderBean {
    /**
     * status : 1
     * data : [{"id":"201609021104510174","status":"unpaid","prepay_id":"wx201609021104513dab226d5f0154110573","nonce_str":"WfP6gOyFcYDSX2bM","app_pay_params":{"appid":"wxf2dfbeb5f641ce40","partnerid":"1379576802","package":"Sign=WXPay","timestamp":"1473239277","prepayid":"wx201609021104513dab226d5f0154110573","noncestr":"WfP6gOyFcYDSX2bM","sign":"577BF6A41D1ADDB1A5036F567F9B91F2"},"pay_type":1,"pay_at":null,"created_at":"2016-09-02 11:04:51","product":{"id":2,"name":"数学提升班","subject":"生物","grade":"五年级","teacher_name":"魏玉花","price":300,"chat_team_id":"7670551","buy_tickets_count":682,"preset_lesson_count":10,"completed_lesson_count":0,"live_start_time":" ","live_end_time":" ","publicize":"http://testing.qatime.cn/imgs/no_img.png"}},{"id":"201609012053550551","status":"unpaid","prepay_id":"wx201609012053550a31f48af80450793197","nonce_str":"66vU526FocAVeXrC","app_pay_params":{"appid":"wxf2dfbeb5f641ce40","partnerid":"1379576802","package":"Sign=WXPay","timestamp":"1473239277","prepayid":"wx201609012053550a31f48af80450793197","noncestr":"66vU526FocAVeXrC","sign":"C0DB7D6EC508E624D046A6AEF6D18AAE"},"pay_type":1,"pay_at":null,"created_at":"2016-09-01 20:53:55","product":{"id":27,"name":"王小龙 800*500","subject":"数学","grade":"初一","teacher_name":"luke","price":4444,"chat_team_id":"7733636","buy_tickets_count":1,"preset_lesson_count":1,"completed_lesson_count":0,"live_start_time":"2016-08-31 3:00","live_end_time":"2016-08-31 3:30","publicize":"http://qatime-testing.oss-cn-beijing.aliyuncs.com/courses/publicize/list_41364c5b954a58bb2b0379ee672d71ed.png"}}]
     */

    public int status;
    /**
     * id : 201609021104510174
     * status : unpaid
     * prepay_id : wx201609021104513dab226d5f0154110573
     * nonce_str : WfP6gOyFcYDSX2bM
     * app_pay_params : {"appid":"wxf2dfbeb5f641ce40","partnerid":"1379576802","package":"Sign=WXPay","timestamp":"1473239277","prepayid":"wx201609021104513dab226d5f0154110573","noncestr":"WfP6gOyFcYDSX2bM","sign":"577BF6A41D1ADDB1A5036F567F9B91F2"}
     * pay_type : 1
     * pay_at : null
     * created_at : 2016-09-02 11:04:51
     * product : {"id":2,"name":"数学提升班","subject":"生物","grade":"五年级","teacher_name":"魏玉花","price":300,"chat_team_id":"7670551","buy_tickets_count":682,"preset_lesson_count":10,"completed_lesson_count":0,"live_start_time":" ","live_end_time":" ","publicize":"http://testing.qatime.cn/imgs/no_img.png"}
     */

    public List<DataBean> data;

    public static class DataBean {
        public String id;
        public String status;
        public String prepay_id;
        public String nonce_str;
        /**
         * appid : wxf2dfbeb5f641ce40
         * partnerid : 1379576802
         * package : Sign=WXPay
         * timestamp : 1473239277
         * prepayid : wx201609021104513dab226d5f0154110573
         * noncestr : WfP6gOyFcYDSX2bM
         * sign : 577BF6A41D1ADDB1A5036F567F9B91F2
         */

        public AppPayParamsBean app_pay_params;
        public int pay_type;
        public Object pay_at;
        public String created_at;
        /**
         * id : 2
         * name : 数学提升班
         * subject : 生物
         * grade : 五年级
         * teacher_name : 魏玉花
         * price : 300
         * chat_team_id : 7670551
         * buy_tickets_count : 682
         * preset_lesson_count : 10
         * completed_lesson_count : 0
         * live_start_time :
         * live_end_time :
         * publicize : http://testing.qatime.cn/imgs/no_img.png
         */

        public ProductBean product;

        public static class AppPayParamsBean {
            public String appid;
            public String partnerid;
            @SerializedName("package")
            public String packageX;
            public String timestamp;
            public String prepayid;
            public String noncestr;
            public String sign;
        }

        public static class ProductBean {
            public int id;
            public String name;
            public String subject;
            public String grade;
            public String teacher_name;
            public int price;
            public String chat_team_id;
            public int buy_tickets_count;
            public int preset_lesson_count;
            public int completed_lesson_count;
            public String live_start_time;
            public String live_end_time;
            public String publicize;
        }
    }
}
