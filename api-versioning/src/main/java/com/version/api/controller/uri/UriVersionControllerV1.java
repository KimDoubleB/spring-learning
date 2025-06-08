package com.version.api.controller.uri;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/uri/v1")
public class UriVersionControllerV1 {

	@GetMapping("/versions")
	public ResponseEntity<List<String>> getVersions() {
		return ResponseEntity.ok(List.of("v1.1", "v1.2", "v1.3"));
	}

}
