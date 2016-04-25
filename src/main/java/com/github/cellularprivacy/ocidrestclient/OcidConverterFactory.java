package com.github.cellularprivacy.ocidrestclient;

import com.github.cellularprivacy.ocidrestclient.dto.BoundingBox;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Special {@link retrofit2.Converter.Factory} for the ocid api
 */
public class OcidConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (String.class.equals(type)) {
            return new ResponseBodyStringConverter();
        }

        return super.responseBodyConverter(type, annotations, retrofit);
    }

    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (BoundingBox.class.equals(type))
            return new BoundingBoxStringConverter();

        return super.stringConverter(type, annotations, retrofit);
    }

    private static class ResponseBodyStringConverter implements Converter<ResponseBody, String> {
        @Override
        public String convert(ResponseBody value) throws IOException {
            return value.string();
        }
    }

    private class BoundingBoxStringConverter implements Converter<BoundingBox, String> {

        @Override
        public String convert(BoundingBox value) throws IOException {
            return value.toString();
        }
    }
}
