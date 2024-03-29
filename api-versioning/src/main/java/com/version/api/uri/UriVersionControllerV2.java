package com.version.api.uri;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/uri/v2")
public class UriVersionControllerV2 {

	@GetMapping("/versions")
	public ResponseEntity<List<String>> getVersions() {
		return ResponseEntity.ok(List.of("v2.1", "v2.2", "v2.3"));
	}

}
