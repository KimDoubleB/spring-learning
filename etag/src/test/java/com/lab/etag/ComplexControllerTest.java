package com.lab.etag;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lab.etag.dto.Request;
import com.lab.etag.dto.ResponseData;
import com.lab.etag.service.EtagService;
import com.lab.etag.utils.EtagUtils;

class ComplexControllerTest {

	private ComplexController complexController;
	private EtagService etagService;

	@BeforeEach
	void setUp() {
		etagService = mock(EtagService.class);
		complexController = new ComplexController(etagService);
	}

	@DisplayName("갖고 있던 Etag 값과 현재 데이터 Etag 값이 같으면, Save를 수행한다")
	@Test
	void executeSaveWhenNotOccurMidAirCollision() {
		// given
		var request = new Request("name123", 30);
		var expectedResponse = new ResponseData("name", 20);
		var etag = EtagUtils.generateETagHeaderValue(expectedResponse);

		when(etagService.getSomeData(request)).thenReturn(expectedResponse);
		when(etagService.saveSomeData(request)).thenReturn(expectedResponse);

		// when
		ResponseEntity<ResponseData> response = complexController.saveData(etag, request);

		// then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(expectedResponse);
		verify(etagService, timeout(1)).saveSomeData(request);
	}


	@DisplayName("If-Match header가 없으면, Save를 수행한다")
	@Test
	void executeSaveWhenIfMatchHeaderIsNull() {
		// given
		var request = new Request("name123", 30);
		var expectedResponse = new ResponseData("name", 20);
		when(etagService.saveSomeData(request)).thenReturn(expectedResponse);

		// when
		ResponseEntity<ResponseData> response = complexController.saveData(null, request);

		// then
		verify(etagService, timeout(1)).saveSomeData(request);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(expectedResponse);
	}


	@DisplayName("갖고 있던 Etag 값과 현재 데이터 Etag 값이 다르면(mid-air collision), Save를 수행하지 않고 412를 반환한다")
	@Test
	void return412WhenOccurMidAirCollision() {
		// given
		var request = new Request("name123", 30);
		var realResponse = new ResponseData("name123", 30);

		var expectedResponse = new ResponseData("name", 20);
		var etag = EtagUtils.generateETagHeaderValue(realResponse);

		when(etagService.getSomeData(request)).thenReturn(expectedResponse);
		when(etagService.saveSomeData(request)).thenReturn(expectedResponse);

		// when
		ResponseEntity<ResponseData> response = complexController.saveData(etag, request);

		// then
		verify(etagService, never()).saveSomeData(request);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.PRECONDITION_FAILED);
		assertThat(response.getBody()).isNull();
	}

}
