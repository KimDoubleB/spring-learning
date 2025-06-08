package com.version.api.controller.parameter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/parameter")
public class ParameterVersionController {

	@GetMapping(value = "/versions", params = "version=1")
	public ResponseEntity<List<String>> getVersionsV1() {
		return ResponseEntity.ok(List.of("v1.1", "v1.2", "v1.3"));
	}

	@GetMapping(value = "/versions", params = "version=2")
	public ResponseEntity<List<String>> getVersionsV2() {
		return ResponseEntity.ok(List.of("v2.1", "v2.2", "v2.3"));
	}

}
