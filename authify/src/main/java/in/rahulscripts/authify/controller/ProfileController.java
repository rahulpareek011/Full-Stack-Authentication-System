package in.rahulscripts.authify.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.rahulscripts.authify.io.ProfileRequest;
import in.rahulscripts.authify.io.ProfileResponse;
import in.rahulscripts.authify.service.EmailService;
import in.rahulscripts.authify.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class ProfileController {
	
	private final ProfileService profileService;
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ProfileResponse register(@Valid @RequestBody ProfileRequest request) {
		ProfileResponse response = profileService.createProfile(request);
		return response;
	}
	
	@GetMapping("/profile") //but if user email not find in securitycontext then it should throw exception so creating global exception handler(inside config CustomAuthenticationEntryPoint)
	public ProfileResponse getProfile(@CurrentSecurityContext(expression = "authentication?.name") String email) {
		return profileService.getProfile(email);
	}
}
