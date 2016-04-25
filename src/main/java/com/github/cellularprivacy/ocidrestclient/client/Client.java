package com.github.cellularprivacy.ocidrestclient.client;

import com.github.cellularprivacy.ocidrestclient.OcidRestClient;

abstract class Client<S> {
    protected OcidRestClient ocidRestClient;
    protected S service;

    public Client(OcidRestClient ocidRestClient, S service) {
        this.ocidRestClient = ocidRestClient;
        this.service = service;
    }
}
