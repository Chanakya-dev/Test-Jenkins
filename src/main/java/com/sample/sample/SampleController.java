package com.sample.sample;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sample")
public class SampleController {
	@GetMapping("/greet/{username}")
	public ResponseEntity<String> greet(
	        @PathVariable("username") String username) {

	    return ResponseEntity.ok(String.format("Hello %s", username));
	}
}
