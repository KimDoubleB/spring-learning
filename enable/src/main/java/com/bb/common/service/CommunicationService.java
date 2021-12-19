package com.bb.common.service;

import com.bb.common.Payload;
import org.springframework.stereotype.Service;

@Service
public class CommunicationService {

    private final Payload payload;

    public CommunicationService(Payload payload) {
        this.payload = payload;
    }

}
