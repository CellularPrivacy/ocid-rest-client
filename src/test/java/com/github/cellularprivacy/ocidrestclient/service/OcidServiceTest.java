package com.github.cellularprivacy.ocidrestclient.service;

import com.github.cellularprivacy.ocidrestclient.OcidRestClient;
import com.github.cellularprivacy.ocidrestclient.dto.Status;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import static org.junit.Assert.*;

public class OcidServiceTest {

    OcidRestClient ocidRestClient;

    @Before
    public void setUp() throws Exception {
        ocidRestClient = new OcidRestClient(new Retrofit.Builder(), "xxx");
    }

    @Test
    public void testGetStatus() throws Exception {
        Call<Status> status = ocidRestClient.getOcidService().getStatus();

        assertNotNull(status);

        Response<Status> response = status.execute();

        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertEquals(200, response.code());

        Status body = response.body();

        assertNotNull(body);
    }
}