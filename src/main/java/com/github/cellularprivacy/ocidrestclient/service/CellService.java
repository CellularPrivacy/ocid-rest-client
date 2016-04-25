package com.github.cellularprivacy.ocidrestclient.service;

import com.github.cellularprivacy.ocidrestclient.dto.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface CellService {

    /**
     * @param apiKey            API key assigned to the user
     * @param mobileCountryCode Mobile country code
     * @param mobileNetworkCode Mobile network code or system identifier
     * @param locationAreaCode  Local area code, tracking area code or network id
     * @param cellId            Cell id or base station id
     * @param radio             You can specifiy GSM, UMTS, LTE or CDMA as the radio of returned cell. Otherwise first matched cell will be returned.
     * @return
     */
    @POST("cell/get?format=json")
    @FormUrlEncoded
    Call<CellInfo> get(
            @Field("key") String apiKey,
            @Field("mcc") int mobileCountryCode,
            @Field("mnc") int mobileNetworkCode,
            @Field("lac") int locationAreaCode,
            @Field("cellid") int cellId,
            @Field("radio") String radio
    );

    @POST("cell/getMeasures?format=json")
    @FormUrlEncoded
    Call<MeasurementInfo> getMeasures(
            @Field("key") String apiKey,
            @Field("mcc") int mobileCountryCode,
            @Field("mnc") int mobileNetworkCode,
            @Field("lac") int locationAreaCode,
            @Field("cellid") int cellId,
            @Field("radio") String radio,
            @Field("limit") Integer limit,
            @Field("offset") Integer offset
    );

    /**
     * Getting the number of all measures used to compute the position of a cell
     *
     * @param apiKey API key assigned to the user
     * @param mobileCountryCode Mobile country code
     * @param mobileNetworkCode Mobile network code or system identifier
     * @param locationAreaCode Local area code, tracking area code or network id
     * @param cellId Cell id or base station id
     * @param radio You can specifiy GSM, UMTS, LTE or CDMA as the radio of returned data. Otherwise first matched cell will be returned with its measurements.
     * @return Upon successful request, HTTP 200 is returned
     */
    @POST("cell/getMeasuresSize?format=json")
    @FormUrlEncoded
    Call<Count> getMeasuresSize(
            @Field("key") String apiKey,
            @Field("mcc") int mobileCountryCode,
            @Field("mnc") int mobileNetworkCode,
            @Field("lac") int locationAreaCode,
            @Field("cellid") int cellId,
            @Field("radio") String radio
    );

    /**
     * Getting the list of cells in a specified area
     *
     * @param apiKey API key assigned to the user
     * @param boundingBox
     * @param mobileCountryCode Mobile country code; If you want to restrict the result to a specific country
     * @param mobileNetworkCode Mobile network code or system identifier; If you want to restrict the result
     * @param locationAreaCode Local area code, tracking area code or network id; If you want to restrict the result
     * @param radio You can specifiy GSM, UMTS, LTE or CDMA as the radio of returned cells. Otherwise cells with any radios will be returned.
     * @param limit A number defining maximum size of the returned list. Default and maximum value is 1000.
     * @param offset The field says to skip that many cells before beginning to return cells. Default is 0.
     * @return Upon successful request, HTTP 200 is returned
     */
    @GET("cell/getInArea?format=json")
    Call<CellInAreaInfo> getInArea(
            @Query("key") String apiKey,
            @Query("BBOX") BoundingBox boundingBox,
            @Query("mcc") Integer mobileCountryCode,
            @Query("mnc") Integer mobileNetworkCode,
            @Query("lac") Integer locationAreaCode,
            @Query("radio") String radio,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );

    /**
     *
     * @param apiKey API key assigned to the user
     * @param boundingBox
     * @param mobileCountryCode Mobile country code; If you want to restrict the result to a specific country
     * @param mobileNetworkCode Mobile network code or system identifier; If you want to restrict the result
     * @param locationAreaCode Local area code, tracking area code or network id; If you want to restrict the result
     * @param radio You can specifiy GSM, UMTS, LTE or CDMA as the radio of returned data. Otherwise any cells will be returned.
     * @return Upon successful request, HTTP 200 is returned
     */
    @GET("cell/getInAreaSize?format=json")
    Call<Count> getInAreaSize(
            @Query("key") String apiKey,
            @Query("BBOX") BoundingBox boundingBox,
            @Query("mcc") Integer mobileCountryCode,
            @Query("mnc") Integer mobileNetworkCode,
            @Query("lac") Integer locationAreaCode,
            @Query("radio") String radio
    );
}
