package thescope.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import thescope.models.User;
import thescope.repositories.UserRepository;

@Service
@Transactional 
public class UserService {
	
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
	
	public void createUser(String username, String password, String name, String firstname, String address, String postalcode, String town, int role) {
		User user = new User();
		user.setUserID(username);
		user.setSecret(password);
		user.setName(name);
		user.setFirstName(firstname);
		user.setAddress(address);
		user.setPostalCode(postalcode);
		user.setTown(town);
		user.setRole(role);
		em.persist(user);
		userRepository.findAll().add(user);
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

