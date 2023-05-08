package hh.ohjelmistoprojekti.kysely.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity(name="users")
@JsonIgnoreProperties(value = { "passwordHash" , "email", "role", "passwordConfirmation"})
public class User {

	@Id
	@Column(name = "username", nullable = false, unique = true)
	@Size(min = 1, max = 20)
    private String username;
	
    @Column(name = "password", nullable = false)
    @Size(min = 5, max = 100)
    private String passwordHash;
    
    @Column(name = "email", nullable = false)
    @Email
    private String email;
    
    @Column(name = "role", nullable = false)
    private String role;
    
    @Transient
    private String passwordConfirmation;
	
	public User() {
	}

	public User(String username, String passwordHash, String email, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.email = email;
		this.role = role;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
    public String getpasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
