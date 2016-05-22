package com.github.cellularprivacy.ocidrestclient.client;

import com.github.cellularprivacy.ocidrestclient.OcidRestClient;
import com.github.cellularprivacy.ocidrestclient.dto.Count;
import com.github.cellularprivacy.ocidrestclient.dto.MeasurementList;
import com.github.cellularprivacy.ocidrestclient.dto.Status;
import com.github.cellularprivacy.ocidrestclient.service.MeasureService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

import java.io.File;
import java.util.Date;

public class MeasureClient extends Client<MeasureService> {

    public MeasureClient(OcidRestClient ocidRestClient, MeasureService service) {
        super(ocidRestClient, service);
    }

    public Call<String> uploadCsv(String csv) {
        return service.uploadCsv(
                ocidRestClient.getApiKey(),
                MultipartBody.Part.createFormData(
                        "datafile",
                        "data.csv",
                        RequestBody.create(
                                MediaType.parse("text/csv"),
                                csv
                        )
                )
        );
    }

    public Call<String> uploadCsv(File csvFile) {
        return service.uploadCsv(
                ocidRestClient.getApiKey(),
                MultipartBody.Part.createFormData(
                        "datafile",
                        csvFile.getName(),
                        RequestBody.create(
                                MediaType.parse("text/csv"),
                                csvFile
                        )
                )
        );
    }

    public Call<Status> uploadJson(String json) {
        return service.uploadJson(
                ocidRestClient.getApiKey(),
                MultipartBody.Part.createFormData(
                        "datafile",
                        "data.json",
                        RequestBody.create(
                                MediaType.parse("application/json"),
                                json
                        )
                )
        );
    }

    public Call<Status> uploadJson(File jsonFile) {
        return service.uploadJson(
                ocidRestClient.getApiKey(),
                MultipartBody.Part.createFormData(
                        "datafile",
                        jsonFile.getName(),
                        RequestBody.create(
                                MediaType.parse("application/json"),
                                jsonFile
                        )
                )
        );
    }

    public Call<Count> listSize() {
        return service.listSize(ocidRestClient.getApiKey());
    }

    public Call<MeasurementList> list() {
        return list(null, null);
    }

    public Call<MeasurementList> list(Integer limit, Integer offset) {
        return service.list(ocidRestClient.getApiKey(), limit, offset);
    }

    public Call<String> delete(String measureId) {
        return service.delete(ocidRestClient.getApiKey(), measureId);
    }

    public Call<String> add(
            double latitude,
            double longitude,
            Integer mobileCountryCode,
            Integer mobileNetworkCode,
            Integer locationAreaCode,
            Integer cellId,
            Integer signal,
            Date measured_at,
            Double rating,
            Double speed,
            Double direction,
            String act,
            Integer ta,
            Integer psc,
            Integer tac,
            Integer pci,
            Integer sid,
            Integer nid,
            Integer bid,
            String devn,
            Integer txp,
            Integer tsrf
    ) {
        return service.add(ocidRestClient.getApiKey(), latitude, longitude, mobileCountryCode, mobileNetworkCode, locationAreaCode, cellId, signal, measured_at, rating, speed, direction, act, ta, psc, tac, pci, sid, nid, bid, devn, txp, tsrf);
    }
}
