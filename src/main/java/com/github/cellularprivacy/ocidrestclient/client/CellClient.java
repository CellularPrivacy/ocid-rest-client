package com.github.cellularprivacy.ocidrestclient.client;

import com.github.cellularprivacy.ocidrestclient.OcidRestClient;
import com.github.cellularprivacy.ocidrestclient.dto.*;
import com.github.cellularprivacy.ocidrestclient.service.CellService;
import retrofit2.Call;

public class CellClient extends Client<CellService> {

    public CellClient(OcidRestClient ocidRestClient, CellService service) {
        super(ocidRestClient, service);
    }

    public Call<CellInfo> get(
            int mobileCountryCode,
            int mobileNetworkCode,
            int locationAreaCode,
            int cellId,
            String radio
    ) {
        return service.get(ocidRestClient.getApiKey(), mobileCountryCode, mobileNetworkCode, locationAreaCode, cellId, radio);
    }

    public Call<Count> getMeasuresSize(
            int mobileCountryCode,
            int mobileNetworkCode,
            int locationAreaCode,
            int cellId,
            String radio
    ) {
        return service.getMeasuresSize(ocidRestClient.getApiKey(), mobileCountryCode, mobileNetworkCode, locationAreaCode, cellId, radio);
    }

    public Call<MeasurementInfo> getMeasures(
            int mobileCountryCode,
            int mobileNetworkCode,
            int locationAreaCode,
            int cellId,
            String radio
    ) {
        return getMeasures(mobileCountryCode, mobileNetworkCode, locationAreaCode, cellId, radio, null, null);
    }

    public Call<MeasurementInfo> getMeasures(
            int mobileCountryCode,
            int mobileNetworkCode,
            int locationAreaCode,
            int cellId,
            String radio,
            Integer limit,
            Integer offset
    ) {
        return service.getMeasures(ocidRestClient.getApiKey(), mobileCountryCode, mobileNetworkCode, locationAreaCode, cellId, radio, limit, offset);
    }

    public Call<Count> getInAreaSize(
            BoundingBox boundingBox,
            Integer mobileCountryCode,
            Integer mobileNetworkCode,
            Integer locationAreaCode,
            String radio
    ) {
        return service.getInAreaSize(ocidRestClient.getApiKey(), boundingBox, mobileCountryCode, mobileNetworkCode, locationAreaCode, radio);
    }

    public Call<CellInAreaInfo> getInArea(
            BoundingBox boundingBox,
            Integer mobileCountryCode,
            Integer mobileNetworkCode,
            Integer locationAreaCode,
            String radio
    ) {
        return getInArea(boundingBox, mobileCountryCode, mobileNetworkCode, locationAreaCode, radio, null, null);
    }

    public Call<CellInAreaInfo> getInArea(
            BoundingBox boundingBox,
            Integer mobileCountryCode,
            Integer mobileNetworkCode,
            Integer locationAreaCode,
            String radio,
            Integer limit,
            Integer offset
    ) {
        return service.getInArea(ocidRestClient.getApiKey(), boundingBox, mobileCountryCode, mobileNetworkCode, locationAreaCode, radio, limit, offset);
    }
}
