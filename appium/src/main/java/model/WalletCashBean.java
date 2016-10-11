package model;

/**
 * @author Tianhaoranly
 * @date 2016/9/29 21:29
 * @Description:
 */
public class WalletCashBean {

    /**
     * status : 1
     * data : {"id":70,"balance":"0.0","total_income":"0.0","total_expenditure":"0.0"}
     */

    private int status;
    /**
     * id : 70
     * balance : 0.0
     * total_income : 0.0
     * total_expenditure : 0.0
     */

    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private String balance;
        private String total_income;
        private String total_expenditure;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getTotal_income() {
            return total_income;
        }

        public void setTotal_income(String total_income) {
            this.total_income = total_income;
        }

        public String getTotal_expenditure() {
            return total_expenditure;
        }

        public void setTotal_expenditure(String total_expenditure) {
            this.total_expenditure = total_expenditure;
        }
    }
}
