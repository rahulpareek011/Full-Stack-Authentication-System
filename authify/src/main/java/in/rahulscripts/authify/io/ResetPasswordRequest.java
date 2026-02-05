package in.rahulscripts.authify.io;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest {
	
	//hiberante validators
	@NotBlank(message="New Password is required")
	private String newPassword;
	@NotBlank(message="OTP is required")
	private String otp;
	@NotBlank(message="Email is required")
	private String email;
}
