package in.rahulscripts.authify.io;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileRequest {
	
	@NotBlank(message="Name should not be empty")
	private String name;
	@Email(message="Enter a valid email")
	@NotNull(message="Email should be not null")
	private String email;
	@Size(min = 6,message="Password must be atleast 6 characters")
	private String password;
}
