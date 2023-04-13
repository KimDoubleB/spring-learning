package com.lab.etag;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.etag.dto.Request;
import com.lab.etag.dto.ResponseData;
import com.lab.etag.service.EtagService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/complex")
@RequiredArgsConstructor
@Slf4j
public class ComplexController {

	private final EtagService etagService;

	@GetMapping
	public ResponseEntity<ResponseData> getData(@RequestBody Request request) {
		ResponseData currentData = etagService.getSomeData(request);
		return ResponseEntity.status(HttpStatus.OK.value())
			.header(HttpHeaders.ETAG, generateETagHeaderValue(currentData))
			.body(currentData);
	}

	@PostMapping("/midAirCollision")
	public ResponseEntity<ResponseData> saveData(@RequestHeader(HttpHeaders.IF_MATCH) String ifMatchHeaderValue,
	                                             @RequestBody Request request) {
		// check data is changed
		if (ifMatchHeaderValue != null) {
			ResponseData currentData = etagService.getSomeData(request);
			String currentEtag = generateETagHeaderValue(currentData);
			if (!ifMatchHeaderValue.equals(currentEtag)) {
				log.info("Occurs mid-air collision - originalEtag {}, currentEtag {}", ifMatchHeaderValue, currentEtag);
				return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
			}
		}

		log.info("Save data: {}", request);
		ResponseData savedData = etagService.saveSomeData(request);
		return ResponseEntity.status(HttpStatus.OK.value())
			.header(HttpHeaders.ETAG, generateETagHeaderValue(savedData))
			.body(savedData);
	}

	private String generateETagHeaderValue(ResponseData responseData) {
		return generateETagHeaderValue(responseData.toString());
	}

	/*
	ShallowEtagHeaderFilter 메서드 참조
	 */
	private String generateETagHeaderValue(String input) {
		// length of W/ + " + 0 + 32bits md5 hash + "
		StringBuilder builder = new StringBuilder(37);
		builder.append("\"0");
		DigestUtils.appendMd5DigestAsHex(input.getBytes(), builder);
		builder.append('"');
		return builder.toString();
	}
}
