package com.ssi.app.restutil;

/**
 * Created by user on 1/10/2018.
 */




import com.ssi.app.datamodel.Login.LoginRest;
import com.ssi.app.datamodel.SuratTugas.Order;
import com.ssi.app.datamodel.inputCITData.CITData;
import com.ssi.app.datamodel.inputCITData.CITDataResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by anupamchugh on 09/01/17.
 */

 public interface APIInterfacesRest {

 /*@FormUrlEncoded
 @POST("api/user/login")
 Call<Login> getLogin(@Field("username") String username, @Field("password") String password);
*/

 @Headers("Content-Type: application/json")
 @POST("secure/entry")
 Call<LoginRest> getLogin(@Body String body);


 @Headers("Content-Type: application/json")
 @POST("nomerst/getNomer")
 Call<Order> getOrder(@Body String body);

 @Headers("Content-Type: application/json")
 @POST("citdata/inputcitdata")
 Call<CITDataResult> sendCIT(@Body CITData body);


 /*@Multipart
 @POST("api/tbl_pegawai/add")
 Call<Pegawai> addData(
         @Part("nama") RequestBody nama,
         @Part("no_pegawai") RequestBody no_pegawai,
         @Part("foto\"; filename=\"image.jpeg\"") RequestBody foto

 );*/



/*
 @GET("api/tbl_tukar_point/all")
 Call<TukarPoint> getTukarPoint(@Query("filter") String filter, @Query("field") String field, @Query("start") String start, @Query("limit") String limit);

 @GET("api/tbl_motor_bekas/all")
 Call<MotorBekas> getMotorBekas(@Query("filter") String filter, @Query("field") String field, @Query("start") String start, @Query("limit") String limit);

 @GET("api/tbl_elektronik/all")
 Call<Elektronik> getElektronik(@Query("filter") String filter, @Query("field") String field, @Query("start") String start, @Query("limit") String limit);

 @GET("api/tbl_info_kamm/all")
 Call<InfoKamm> getInfoKamm(@Query("filter") String filter, @Query("field") String field, @Query("start") String start, @Query("limit") String limit);
*/



}

