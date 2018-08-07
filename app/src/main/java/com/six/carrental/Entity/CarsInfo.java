package com.six.carrental.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lian
 * @create 2018/7/23
 * @Describe 汽车实体类
 */
public class CarsInfo implements Serializable {

    /**
     * result : [{"id":"5","user_id":"2","brand":"玛莎拉蒂","img":null,"union":"闽D2417","start_time":"2017-111","end_time":"2017-111","publish_time":"2018-07-26 17:24:36","status":"0","user_message":{"name":"x","tel":"1","img":null}},{"id":"6","user_id":"2","brand":"玛莎拉蒂","img":null,"union":"闽D2417","start_time":"2017-111","end_time":"2017-111","publish_time":"2018-07-26 17:24:36","status":"0","user_message":{"name":"x","tel":"1","img":null}},{"id":"8","user_id":"2","brand":"玛莎拉蒂","img":null,"union":"闽D2417","start_time":"2017-111","end_time":"2017-111","publish_time":"2018-07-26 17:24:38","status":"0","user_message":{"name":"x","tel":"1","img":null}},{"id":"9","user_id":"2","brand":"玛莎拉蒂","img":null,"union":"闽D2417","start_time":"2017-111","end_time":"2017-111","publish_time":"2018-07-26 17:24:39","status":"0","user_message":{"name":"x","tel":"1","img":null}},{"id":"10","user_id":"2","brand":"玛莎拉蒂","img":null,"union":"闽D2417","start_time":"2017-111","end_time":"2017-111","publish_time":"2018-07-26 17:24:39","status":"0","user_message":{"name":"x","tel":"1","img":null}},{"id":"11","user_id":"2","brand":"玛莎拉蒂","img":null,"union":"闽D2417","start_time":"2017-111","end_time":"2017-111","publish_time":"2018-07-26 17:24:40","status":"0","user_message":{"name":"x","tel":"1","img":null}},{"id":"12","user_id":"2","brand":"玛莎拉蒂","img":null,"union":"闽D2417","start_time":"2017-111","end_time":"2017-111","publish_time":"2018-07-26 17:24:41","status":"0","user_message":{"name":"x","tel":"1","img":null}},{"id":"13","user_id":"2","brand":"玛莎拉蒂","img":null,"union":"闽D2417","start_time":"2017-111","end_time":"2017-111","publish_time":"2018-07-26 23:46:06","status":"0","user_message":{"name":"x","tel":"1","img":null}}]
     * length : 8
     * status : 200
     */

    private int length;
    private int status;
    private List <ResultBean> result;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List <ResultBean> getResult() {
        return result;
    }

    public void setResult(List <ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        /**
         * id : 5
         * user_id : 2
         * driver_name: 小明
         * brand : 玛莎拉蒂
         * img : null
         * union : 闽D2417
         * free_date:2018-01-01
         * start_time : 00:00
         * end_time : 00:00
         * publish_time : 2018-07-26 17:24:36
         * accept: 0
         * status : 0
         * user_message : {"name":"x","tel":"1","img":null}
         */

        private String id;
        private String user_id;
        private String driver_name;
        private String brand;
        private Object img;
        private String union;
        private String free_date;
        private String start_time;
        private String end_time;
        private String publish_time;
        private String accept;
        private String status;
        private UserMessageBean user_message;

        public ResultBean(String name, String union, String brand, String time) {

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getDriver_name() {
            return driver_name;
        }

        public void setDriver_name(String driver_name) {
            this.driver_name = driver_name;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public Object getImg() {
            return img;
        }

        public void setImg(Object img) {
            this.img = img;
        }

        public String getUnion() {
            return union;
        }

        public void setUnion(String union) {
            this.union = union;
        }


        public String getFree_date() {
            return free_date;
        }

        public void setFree_date(String free_date) {
            this.free_date = free_date;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getPublish_time() {
            return publish_time;
        }

        public void setPublish_time(String publish_time) {
            this.publish_time = publish_time;
        }

        public String getAccept() {
            return accept;
        }

        public void setAccept(String accept) {
            this.accept = accept;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public UserMessageBean getUser_message() {
            return user_message;
        }

        public void setUser_message(UserMessageBean user_message) {
            this.user_message = user_message;
        }

        public static class UserMessageBean {
            /**
             * name : x
             * tel : 1
             * img : null
             */

            private String name;
            private String tel;
            private Object img;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public Object getImg() {
                return img;
            }

            public void setImg(Object img) {
                this.img = img;
            }
        }
    }
}
