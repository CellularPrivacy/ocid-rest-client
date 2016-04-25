package com.github.cellularprivacy.ocidrestclient.service;

import com.github.cellularprivacy.ocidrestclient.dto.Count;
import com.github.cellularprivacy.ocidrestclient.dto.MeasurementList;
import com.github.cellularprivacy.ocidrestclient.dto.Status;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Date;

public interface MeasureService {

    /**
     * @param apiKey            API key assigned to the user submitting the measurement
     * @param latitude          Latitude
     * @param longitude         Longitude
     * @param mobileCountryCode Mobile country code
     * @param mobileNetworkCode Mobile network code or system identifier
     * @param locationAreaCode  Local area code, tracking area code or network id
     * @param cellId            Cell tower id or base station id
     * @param signal            Signal level: either in dBm or as defined in TS 27.007 8.5; both is accepted.
     * @param measured_at       When the measurement was measured.
     * @param rating            GPS quality/accuracy information (metres)
     * @param speed             Speed when creating the measurement, in metres/second
     * @param direction         Heading direction when creating the measurement (0=north, 90=east)
     * @param act               Network access type; currently supported: 1xRTT, CDMA, eHRPD, IS95A, IS95B, EVDO_0, EVDO_A, EVDO_B, UMTS, HSPA+, HSDPA, HSUPA, HSPA, LTE, EDGE, GPRS, GSM
     * @param ta                Timing advance; only for GSM and LTE
     * @param psc               Primary scrambling code; only for UMTS
     * @param tac               Tracking area code; only for LTE
     * @param pci               Physical cell Id; only for LTE
     * @param sid               System identifier; only for CDMA
     * @param nid               Network id; only for CDMA
     * @param bid               Base station id; only for CDMA
     * @param devn              Device name as concatenated strings with the manufacturer and the model name; max 50 characters
     * @param txp               TX power in dBm
     * @param tsrf              Temperature in the RF module; in degrees Celsius; only with <txp> parameter
     * @return Upon successful insert, HTTP 200 is returned with the string "Your measurement has been inserted."
     */
    @POST("measure/add")
    Call<String> add(
            @Field("key") String apiKey,
            @Field("lat") double latitude,
            @Field("lon") double longitude,
            @Field("mcc") Integer mobileCountryCode,
            @Field("mnc") Integer mobileNetworkCode,
            @Field("lac") Integer locationAreaCode,
            @Field("cellid") Integer cellId,
            @Field("signal") Integer signal,
            @Field("measured_at") Date measured_at,
            @Field("rating") Double rating,
            @Field("speed") Double speed,
            @Field("direction") Double direction,
            @Field("act") String act,
            @Field("ta") Integer ta,
            @Field("psc") Integer psc,
            @Field("tac") Integer tac,
            @Field("pci") Integer pci,
            @Field("sid") Integer sid,
            @Field("nid") Integer nid,
            @Field("bid") Integer bid,
            @Field("devn") String devn,
            @Field("txp") Integer txp,
            @Field("tsrf") Integer tsrf
    );

    /**
     * Column names recognised:
     * mcc, mnc, lac, cellid, lat, lon, signal, measured_at, rating, speed, direction, act, ta, psc, tac, pci, sid, nid, bid
     * <p/>
     * Maximum file size:
     * 2 MB; upload multiple files if you want to upload more data.
     *
     * @param apiKey  the user's API key.
     * @param csvFile
     * @return Upon successful insert, HTTP 200 is returned with the string "0,OK".
     * @see <a href="http://wiki.opencellid.org/wiki/API#Uploading_measurements_from_a_CSV_file">http://wiki.opencellid.org/wiki/API#Uploading_measurements_from_a_CSV_file</a>
     */
    @POST("measure/uploadCsv")
    @Multipart
    Call<String> uploadCsv(
            @Part("key") String apiKey,
            @Part MultipartBody.Part csvFile
    );

    /**
     * Field names recognised:
     * mcc, mnc, lac, cellid, lat, lon, signal, measured_at, rating, speed, direction, act, ta, psc, tac, pci, sid, nid, bid
     * <p/>
     * Maximum file size:
     * 2 MB; upload multiple files if you want to upload more data.
     *
     * @param apiKey   the user's API key.
     * @param jsonFile JSON file (send in datafile parameter)
     * @return Upon successful insert, HTTP 200 is returned with application/json content type and following json document as a content: {"code":0,"status":"OK"}
     * @see <a href="http://wiki.opencellid.org/wiki/API#Uploading_measurements_from_a_JSON_file">http://wiki.opencellid.org/wiki/API#Uploading_measurements_from_a_JSON_file</a>
     */
    @POST("measure/uploadJson")
    @Multipart
    Call<Status> uploadJson(
            @Part("key") String apiKey,
            @Part MultipartBody.Part jsonFile
    );

    /**
     * @param apiKey  the user's API key
     * @param clfFile CLF version 3.0 file (to be sent in datafile parameter)
     *                Columns are semicolon separated and have the following strict order:
     *                mcc+mnc, lac, cellid, rnc, lat, lon, ratio, data, rfu
     *                <p/>
     *                There is no header in CLF3 files. Therefore the columns must be provided in the order given above and all values must be there.
     *                <p/>
     *                You can add comments to CLF3 files.
     *                Comments in CLF3 start with // and extend to the end of the physical line.
     *                <p/>
     *                Maximum file size:
     *                2 MB; upload multiple files if you want to upload more data.
     * @return On successful insert return HTTP 200 with the string "0,OK".
     * @see <a href="http://wiki.opencellid.org/wiki/API#Uploading_measurements_from_CLF3_file">http://wiki.opencellid.org/wiki/API#Uploading_measurements_from_CLF3_file</a>
     */
    @POST("measure/uploadClf")
    @Multipart
    Call<String> uploadClf(
            @Part("key") String apiKey,
            @Part MultipartBody.Part clfFile
    );

    /**
     * @param apiKey    API key assigned to the user
     * @param measureId ID of the measure to delete
     * @return Upon successful deletion, HTTP 200 is returned with the string "Measurement with id=<id> has been deleted."
     */
    @POST("measure/delete")
    @FormUrlEncoded
    Call<String> delete(
            @Field("key") String apiKey,
            @Field("id") String measureId
    );

    /**
     * Listing a user's measurements
     *
     * @param apiKey API key assigned to the user
     * @param limit  A number defining maximum size of the returned list.
     *               Default and maximum value is 1000.
     * @param offset The field says to skip that many measurements before beginning to return measurements.
     *               Default is 0.
     * @return Upon successful request, HTTP 200 is returned, oldest entries first
     */
    @GET("measure/list?format=json")
    Call<MeasurementList> list(
            @Query("key") String apiKey,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset
    );

    /**
     * Getting the number of measurements from a user
     *
     * @param apiKey API key assigned to the user
     * @return Upon successful request, HTTP 200 is returned
     */
    @GET("measure/listSize?format=json")
    Call<Count> listSize(
            @Query("key") String apiKey
    );
}
