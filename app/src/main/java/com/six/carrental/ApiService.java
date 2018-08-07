package com.six.carrental;

import com.six.carrental.Entity.CarsInfo;
import com.six.carrental.Entity.OrderEntity;
import com.six.carrental.Entity.ReservationEntity;
import com.six.carrental.Entity.ResultEntity;
import com.six.carrental.Entity.UserInfo;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableError;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 接口集合
 */
public interface ApiService {

    String BASE_URL = "https://www.supertimor.cn/java_car/index.php/index/";


    @FormUrlEncoded
    @POST("login")
    Observable <UserInfo> userLogin(@FieldMap Map <String, String> userInfo);

    @FormUrlEncoded
    @POST("enroll")
    Observable <UserInfo> getId(@FieldMap Map <String, String> userInfo);

    @FormUrlEncoded
    @POST("updateUserinfo")
    Observable <ResultEntity> updateUserinfo(@FieldMap Map <String, String> userInfo);


    @FormUrlEncoded
    @POST("getSelfMessage")
    Observable <ResultEntity> getSelfMessage(@Field("user_id") String userID);

    @POST("selectCanCar")
    Observable <CarsInfo> getCanCar();

    @FormUrlEncoded
    @POST("orderCar")
    Observable <ResultEntity> orderCar(@FieldMap Map <String, String> map);

    @FormUrlEncoded
    @POST("cancelOrder")
    Observable <ResultEntity> cancelOrder(@Field("order_id") String car_id);

    @FormUrlEncoded
    @POST("getOrderByCarId")
    Observable <OrderEntity> getOrder(@Field("car_id") String car_id);

    @FormUrlEncoded
    @POST("finishOrder")
    Observable <ResultEntity> finishOrder(@Field("order_id") String orderID);

    @FormUrlEncoded
    @POST("selectHistoryOrder")
    Observable <ReservationEntity> getHistoryOrder(@Field("user_id") String userID);


    @FormUrlEncoded
    @POST("selectPublish")
    Observable <CarsInfo> getPublished(@Field("user_id") String userID);

    @FormUrlEncoded
    @POST("publishMessage")
    Observable <ResultEntity> publishMessage(@FieldMap Map <String, String> message);

    @FormUrlEncoded
    @POST("acceptOrder")
    Observable <ResultEntity> acceptOrder(@Field("order_id") String carID);

    @FormUrlEncoded
    @POST("cancelPublishMes")
    Observable <ResultEntity> cancelPublishMes(@Field("car_id") String carID);


}
