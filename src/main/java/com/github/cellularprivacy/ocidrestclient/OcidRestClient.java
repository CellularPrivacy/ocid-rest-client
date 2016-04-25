package com.github.cellularprivacy.ocidrestclient;

import com.github.cellularprivacy.ocidrestclient.client.CellClient;
import com.github.cellularprivacy.ocidrestclient.client.MeasureClient;
import com.github.cellularprivacy.ocidrestclient.service.CellService;
import com.github.cellularprivacy.ocidrestclient.service.MeasureService;
import com.github.cellularprivacy.ocidrestclient.service.OcidService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.ref.WeakReference;

public class OcidRestClient {

    private final String apiKey;
    private final Retrofit retrofit;

    public OcidRestClient(String apiKey) {
        this(new Retrofit.Builder(), apiKey);
    }

    public OcidRestClient(OkHttpClient okHttpClient, String apiKey) {
        this(new Retrofit.Builder().client(okHttpClient), apiKey);
    }

    public OcidRestClient(Retrofit.Builder builder, String apiKey) {
        retrofit = builder.baseUrl("http://www.opencellid.org/")
                .addConverterFactory(new OcidConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    WeakReference<CellClient> cellClient;

    public CellClient getCellClient() {
        if (cellClient == null || cellClient.get() == null) {
            cellClient = new WeakReference<>(new CellClient(this, retrofit.create(CellService.class)));
        }

        return cellClient.get();
    }

    WeakReference<MeasureClient> measureClient;

    public MeasureClient getMeasureClient() {
        if (measureClient == null || measureClient.get() == null) {
            measureClient = new WeakReference<>(new MeasureClient(this, retrofit.create(MeasureService.class)));
        }

        return measureClient.get();
    }

    public OcidService getOcidService() {
        return retrofit.create(OcidService.class);
    }
}
