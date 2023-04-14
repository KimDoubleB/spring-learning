package com.lab.etag;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.etag.dto.Request;
import com.lab.etag.dto.ResponseData;
import com.lab.etag.service.EtagService;
import com.lab.etag.utils.EtagUtils;

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
		var currentData = etagService.getSomeData(request);
		return ResponseEntity.status(HttpStatus.OK.value())
			.header(HttpHeaders.ETAG, EtagUtils.generateETagHeaderValue(currentData))
			.body(currentData);
	}

	@PostMapping("/midAirCollision")
	public ResponseEntity<ResponseData> saveData(@RequestHeader(HttpHeaders.IF_MATCH) String ifMatchHeaderValue,
	                                             @RequestBody Request request) {
		// check data is changed
		if (ifMatchHeaderValue != null) {
			var currentData = etagService.getSomeData(request);
			var currentEtag = EtagUtils.generateETagHeaderValue(currentData);
			if (!ifMatchHeaderValue.equals(currentEtag)) {
				log.info("Occurs mid-air collision - originalEtag {}, currentEtag {}", ifMatchHeaderValue, currentEtag);
				return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
			}
		}

		log.info("Save data: {}", request);
		var savedData = etagService.saveSomeData(request);
		return ResponseEntity.status(HttpStatus.OK.value())
			.header(HttpHeaders.ETAG, EtagUtils.generateETagHeaderValue(savedData))
			.body(savedData);
	}

}
