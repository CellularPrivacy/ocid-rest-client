package com.github.cellularprivacy.ocidrestclient.service;

import com.github.cellularprivacy.ocidrestclient.dto.Status;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @see <a href="http://wiki.opencellid.org/wiki/API">http://wiki.opencellid.org/wiki/API</a>
 */
@SuppressWarnings("unused")
public interface OcidService {

    /**
     * Check the online status of OpenCellID
     *
     * @return Upon successful request, HTTP 200 is returned with JSON in the following format {@code {"status":"ok"}}
     */
    @GET("ocid/status")
    Call<Status> getStatus();
}
