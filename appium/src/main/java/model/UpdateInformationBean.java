package model;

/**
 * Created by lenovo on 2016/9/6.
 */
public class UpdateInformationBean {

    /**
     * status : 1
     * data : {"title":"qatime-live-beta-0.1.1","sub_title":"答疑时间直播试用版","version":"0.1.1","category":"student_client","desc":"用于辅导班直播","description":"12313123","download_links":"http://qatime-production.oss-cn-beijing.aliyuncs.com/download/software/windows/qatime-live-release-0.1.1.zip","qr_code_url":"http://qatime-testing.oss-cn-beijing.aliyuncs.com/qrcode/c4af1faaa9abf20b40b4006180b6073b.png","enforce":false,"published_at":"2016-09-02T14:32:57.251+08:00"}
     */

    public int status;
    /**
     * title : qatime-live-beta-0.1.1
     * sub_title : 答疑时间直播试用版
     * version : 0.1.1
     * category : student_client
     * desc : 用于辅导班直播
     * description : 12313123
     * download_links : http://qatime-production.oss-cn-beijing.aliyuncs.com/download/software/windows/qatime-live-release-0.1.1.zip
     * qr_code_url : http://qatime-testing.oss-cn-beijing.aliyuncs.com/qrcode/c4af1faaa9abf20b40b4006180b6073b.png
     * enforce : false
     * published_at : 2016-09-02T14:32:57.251+08:00
     */

    public DataBean data;

    public static class DataBean {
        public String title;
        public String sub_title;
        public String version;
        public String category;
        public String desc;
        public String description;
        public String download_links;
        public String qr_code_url;
        public boolean enforce;
        public String published_at;
    }
}
