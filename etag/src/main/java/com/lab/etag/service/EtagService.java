package com.lab.etag.service;

import org.springframework.stereotype.Service;

import com.lab.etag.dto.Request;
import com.lab.etag.dto.ResponseData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EtagService {

	private static final String SALT = "salt";
	private static final int SALT_AGE = 123;

	public ResponseData getSomeData(Request request) {
		log.info("getSameData request: {}", request);
		return new ResponseData(request.name(), request.age());
	}

	public ResponseData getDifferentData(Request request) {
		log.info("getDifferentData request: {}", request);
		return new ResponseData(request.name() + SALT, request.age() + SALT_AGE);
	}

	public ResponseData saveSomeData(Request request) {
		log.info("saveSomeData request: {}", request);

		/*
		Some save logics
		 */

		return new ResponseData(request.name(), request.age());
	}

}
