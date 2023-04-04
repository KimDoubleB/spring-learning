package com.lab.etag;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.context.request.ServletWebRequest;

public class EtagValidateTest {


	@Test
	void ifMatchHeaderEtagTest() {
		// given
		var etag = "TEST_ETAG";
		var request = new MockHttpServletRequest(HttpMethod.POST.toString(), "/");
		request.addHeader(HttpHeaders.IF_MATCH, etag);
		var response = new MockHttpServletResponse();
		var servletWebRequest = new ServletWebRequest(request, response);

		// when
		boolean checkNotModified = servletWebRequest.checkNotModified(etag);

		// then
		assertThat(checkNotModified).isTrue();
		assertThat(HttpStatus.valueOf(response.getStatus())).isEqualTo(HttpStatus.PRECONDITION_FAILED);
	}

}
