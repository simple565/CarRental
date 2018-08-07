package com.six.carrental.Entity;

/**
 * @author Lian
 * @create 2018/7/31
 * @Describe
 */
public class OrderEntity {

    /**
     * result : {"id":"24","user_id":"9","user_phone":"333","car_id":"31","order_date":"2018-07-31","order_time":"00:30","finish":"1"}
     * carinfo : {"id":"31","user_id":"9","driver_name":"BBC","brand":"BMW","img":null,"union":"AAA 111","free_date":"2018-07-30","start_time":"07:30","end_time":"19:30","publish_time":"2018-07-31 14:20:40","accept":"0","status":"1"}
     * status : 200
     */

    private ResultBean result;
    private CarinfoBean carinfo;
    private int status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public CarinfoBean getCarinfo() {
        return carinfo;
    }

    public void setCarinfo(CarinfoBean carinfo) {
        this.carinfo = carinfo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class ResultBean {
        /**
         * id : 24
         * user_id : 9
         * user_phone : 333
         * car_id : 31
         * order_date : 2018-07-31
         * order_time : 00:30
         * finish : 1
         */

        private String id;
        private String user_id;
        private String user_phone;
        private String car_id;
        private String order_date;
        private String order_time;
        private String finish;

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

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getCar_id() {
            return car_id;
        }

        public void setCar_id(String car_id) {
            this.car_id = car_id;
        }

        public String getOrder_date() {
            return order_date;
        }

        public void setOrder_date(String order_date) {
            this.order_date = order_date;
        }

        public String getOrder_time() {
            return order_time;
        }

        public void setOrder_time(String order_time) {
            this.order_time = order_time;
        }

        public String getFinish() {
            return finish;
        }

        public void setFinish(String finish) {
            this.finish = finish;
        }
    }

    public static class CarinfoBean {
        /**
         * id : 31
         * user_id : 9
         * driver_name : BBC
         * brand : BMW
         * img : null
         * union : AAA 111
         * free_date : 2018-07-30
         * start_time : 07:30
         * end_time : 19:30
         * publish_time : 2018-07-31 14:20:40
         * accept : 0
         * status : 1
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
    }
}
