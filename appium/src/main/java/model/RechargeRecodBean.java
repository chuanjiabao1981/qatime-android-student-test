package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Tianhaoranly
 * @date 2016/9/30 14:09
 * @Description:
 */
public class RechargeRecodBean {

    /**
     * status : 1
     * data : [{"id":"201609301400130951","amount":"11.0","pay_type":"weixin","status":"unpaid","source":"app","created_at":"2016-09-30T14:00:13.022+08:00","updated_at":"2016-09-30T14:00:13.022+08:00","pay_at":null,"prepay_id":"wx201609301400149d061fa2b00209342913","nonce_str":"BoiNuHXTfWHRSekH","app_pay_params":{"appid":"wxf2dfbeb5f641ce40","partnerid":"1379576802","package":"Sign=WXPay","timestamp":"1475215676","prepayid":"wx201609301400149d061fa2b00209342913","noncestr":"BoiNuHXTfWHRSekH","sign":"31EE9B2449DB7FB2582EC4986C77BD96"}},{"id":"201609301336290800","amount":"1000.0","pay_type":"weixin","status":"unpaid","source":"app","created_at":"2016-09-30T13:36:29.970+08:00","updated_at":"2016-09-30T13:36:29.970+08:00","pay_at":null,"prepay_id":"wx20160930133630461ae75f310092191799","nonce_str":"eZIgAuwesdGqoNrr","app_pay_params":{"appid":"wxf2dfbeb5f641ce40","partnerid":"1379576802","package":"Sign=WXPay","timestamp":"1475215676","prepayid":"wx20160930133630461ae75f310092191799","noncestr":"eZIgAuwesdGqoNrr","sign":"5D8EA2F97658B2AABEB8552AC9FD7814"}}]
     */

    private int status;
    /**
     * id : 201609301400130951
     * amount : 11.0
     * pay_type : weixin
     * status : unpaid
     * source : app
     * created_at : 2016-09-30T14:00:13.022+08:00
     * updated_at : 2016-09-30T14:00:13.022+08:00
     * pay_at : null
     * prepay_id : wx201609301400149d061fa2b00209342913
     * nonce_str : BoiNuHXTfWHRSekH
     * app_pay_params : {"appid":"wxf2dfbeb5f641ce40","partnerid":"1379576802","package":"Sign=WXPay","timestamp":"1475215676","prepayid":"wx201609301400149d061fa2b00209342913","noncestr":"BoiNuHXTfWHRSekH","sign":"31EE9B2449DB7FB2582EC4986C77BD96"}
     */

    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String amount;
        private String pay_type;
        private String status;
        private String source;
        private String created_at;
        private String updated_at;
        private Object pay_at;
        private String prepay_id;
        private String nonce_str;
        /**
         * appid : wxf2dfbeb5f641ce40
         * partnerid : 1379576802
         * package : Sign=WXPay
         * timestamp : 1475215676
         * prepayid : wx201609301400149d061fa2b00209342913
         * noncestr : BoiNuHXTfWHRSekH
         * sign : 31EE9B2449DB7FB2582EC4986C77BD96
         */

        private AppPayParamsBean app_pay_params;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public Object getPay_at() {
            return pay_at;
        }

        public void setPay_at(Object pay_at) {
            this.pay_at = pay_at;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public AppPayParamsBean getApp_pay_params() {
            return app_pay_params;
        }

        public void setApp_pay_params(AppPayParamsBean app_pay_params) {
            this.app_pay_params = app_pay_params;
        }

        public static class AppPayParamsBean {
            private String appid;
            private String partnerid;
            @SerializedName("package")
            private String packageX;
            private String timestamp;
            private String prepayid;
            private String noncestr;
            private String sign;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }
        }
    }
}
