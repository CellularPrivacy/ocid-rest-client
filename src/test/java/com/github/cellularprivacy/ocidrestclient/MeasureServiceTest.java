package com.github.cellularprivacy.ocidrestclient;

import com.github.cellularprivacy.ocidrestclient.service.MeasureService;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Created by larsgrefer on 20.04.16.
 */
public class MeasureServiceTest {

    @org.junit.Test
    public void testUploadCsv() throws Exception {
        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://example.com/")
                .client( new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build())
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        if (type == String.class)
                            return new Converter<ResponseBody, String>() {
                                @Override
                                public String convert(ResponseBody value) throws IOException {
                                    try {
                                        return value.string();
                                    } finally {
                                        value.close();
                                    }
                                }
                            };
                        return super.responseBodyConverter(type, annotations, retrofit);
                    }

                    @Override
                    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
                        if(type == String.class)
                            return new Converter<String, RequestBody>() {
                                @Override
                                public RequestBody convert(String value) throws IOException {
                                    return RequestBody.create(MediaType.parse("text/plain"), value);
                                }
                            };
                        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
                    }

                    @Override
                    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        return super.stringConverter(type, annotations, retrofit);
                    }
                })
                .build();

        MeasureService measureService = build.create(MeasureService.class);

        Call<String> responseBodyCall = measureService.uploadCsv(
                "hallo",
                MultipartBody.Part.createFormData(
                        "datafile",
                        "test.csv",
                        RequestBody.create(
                                MediaType.parse("string/csv"), "Hallo Welt"
                        )
                )
        );

        responseBodyCall.execute();
    }
}