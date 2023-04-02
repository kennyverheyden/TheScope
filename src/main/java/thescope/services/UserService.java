package thescope.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import thescope.models.User;
import thescope.models.UserRole;
import thescope.repositories.UserRepository;
import thescope.repositories.UserRoleRepository;

@Service
@Transactional 
public class UserService {

	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;

	@Autowired
	public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
	}

	@Autowired
	private EntityManager em;

	// Current user login details
	String userName;
	String secret;
	String name;
	String firstname;

	public List<User> list() {
		return userRepository.findAll();
	}

	public List<UserRole> userRoles() {
		return userRoleRepository.findAll();
	}


	public User findUserByUsername(String username)
	{
		for (User i:userRepository.findAll())
		{
			if(i.getUserID().equals(username))
			{
				return i;
			}
		}
		return null;
	}

	public boolean userExist(String username)
	{
		for (User i:userRepository.findAll())
		{
			if(i.getUserID().equals(username))
			{
				return true;
			}
		}
		return false;
	}

	public void createUser(String username, String password, String name, String firstname, String address, String postalcode, String town, long role) {
		User user = new User();
		user.setUserID(username);
		user.setSecret(password);
		user.setName(name);
		user.setFirstName(firstname);
		user.setAddress(address);
		user.setPostalCode(postalcode);
		user.setTown(town);
		user.setUserRole(userRoleRepository.findById(role).get());
		userRepository.findAll().add(user);
		em.persist(user);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
}

