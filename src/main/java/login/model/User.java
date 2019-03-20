package login.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class User {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;
    private String username;
    private String password;

    @ManyToOne
    private Role role;

    public User() {}

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(User user) {
    	this.username = user.getUsername();
        this.password = user.getPassword();
        this.userId = user.getId();
	}

	@Override
    public String toString() {
        return String.format(
                "User[id=%d, username='%s', password='%s', role='%s']",
                userId, username, password, role.getRole());
    }

	public Long getId() {
		return userId;
	}

	public void setId(Long id) {
		this.userId = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	

	

	
	
    
    
}
