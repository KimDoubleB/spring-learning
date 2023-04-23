package com.oauth0.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
		if (principal != null) {
			model.addAttribute("profile", principal.getClaims());
			log.info("Profile: {}", principal.getClaims());
		}
		return "index";
	}

}
