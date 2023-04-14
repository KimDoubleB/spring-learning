package com.lab.etag.utils;

import org.springframework.util.DigestUtils;

import com.lab.etag.dto.ResponseData;

public class EtagUtils {

	private EtagUtils() {
	}

	public static String generateETagHeaderValue(ResponseData responseData) {
		return generateETagHeaderValue(responseData.toString());
	}

	/*
	ShallowEtagHeaderFilter 메서드 참조
	 */
	public static String generateETagHeaderValue(String input) {
		// length of W/ + " + 0 + 32bits md5 hash + "
		var builder = new StringBuilder(37);
		builder.append("\"0");
		DigestUtils.appendMd5DigestAsHex(input.getBytes(), builder);
		builder.append('"');
		return builder.toString();
	}

}
