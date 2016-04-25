package com.github.cellularprivacy.ocidrestclient.client;

import com.github.cellularprivacy.ocidrestclient.OcidRestClient;
import com.github.cellularprivacy.ocidrestclient.dto.Count;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import static org.junit.Assert.*;

/**
 * Created by larsgrefer on 25.04.16.
 */
public class MeasureClientTest {

    MeasureClient measureClient;

    @Before
    public void setUp() throws Exception {
        measureClient = new OcidRestClient(new Retrofit.Builder(), "xxx").getMeasureClient();
    }

    @Test
    public void testListSize() throws Exception {
        Call<Count> countCall = measureClient.listSize();
        assertNotNull(countCall);

        Response<Count> countResponse = countCall.execute();
        assertNotNull(countResponse);

        assertEquals(403, countResponse.code());
    }

    @Test
    public void testList() throws Exception {

    }

    @Test
    public void testList1() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {
        Call<String> stringCall = measureClient.delete("foo");
        assertNotNull(stringCall);

        Response<String> stringResponse = stringCall.execute();
        assertNotNull(stringResponse);
    }

    @Test
    public void testAdd() throws Exception {

    }
}