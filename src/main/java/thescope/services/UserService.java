package thescope.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	private  PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
		this.passwordEncoder =  new BCryptPasswordEncoder();
	}

	@Autowired
	private EntityManager em;

	// Global credentials for processing
	private String userName;
	private String secret;
	private String name;
	private String firstname;
	private String userRole;
	long roleID;

	public List<User> list() {
		return userRepository.findAll();
	}

	public List<UserRole> userRoles() {
		return userRoleRepository.findAll();
	}

	public User findUserByUsername(String userName)
	{
		return userRepository.findUserByUserName(userName);
	}

	// Used for searching users by admin
	public List<User> findUsers(String userName, String name, String firstName, String roleName)
	{
		List<User> userList = new ArrayList();
		for (User i:userRepository.findAll())
		{
			// Search with rolename and username
			if(roleName!="" && i.getUserRole().toString().equalsIgnoreCase(roleName) && i.getUserName().toString().equalsIgnoreCase(userName) && name=="" && firstName=="")
			{
				userList.add(i);
			}
			// Search with rolename and username and name
			else if(roleName!="" && i.getUserRole().toString().equalsIgnoreCase(roleName) && i.getUserName().toString().equalsIgnoreCase(userName) && i.getName().toString().equalsIgnoreCase(name)  && firstName=="")
			{
				userList.add(i);
			}
			// Search with rolename and username and firstname
			else if(roleName!="" && i.getUserRole().toString().equalsIgnoreCase(roleName) && i.getUserName().toString().equalsIgnoreCase(userName) && i.getFirstName().toString().equalsIgnoreCase(firstname)  && name=="")
			{
				userList.add(i);
			}
			// Search with rolename and name
			else if(roleName!="" && i.getUserRole().toString().equalsIgnoreCase(roleName) && i.getName().toString().equalsIgnoreCase(name) && userName=="" && firstName=="")
			{
				userList.add(i);
			}
			// Search with rolename and firstname
			else if(roleName!="" && i.getUserRole().toString().equalsIgnoreCase(roleName) && i.getFirstName().toString().equalsIgnoreCase(firstName) && userName=="" && name=="")
			{
				userList.add(i);
			}
			// Search with rolename and firstname and name
			else if(roleName!="" && i.getUserRole().toString().equalsIgnoreCase(roleName) && i.getFirstName().toString().equalsIgnoreCase(firstName) && i.getName().toString().equalsIgnoreCase(name) && userName=="")
			{
				userList.add(i);
			}
			// Search with rolename and firstname and name and username
			else if(roleName!="" && i.getUserRole().toString().equalsIgnoreCase(roleName) && i.getFirstName().toString().equalsIgnoreCase(firstName) && i.getName().toString().equalsIgnoreCase(name) && i.getUserName().toString().equalsIgnoreCase(userName))
			{
				userList.add(i);
				System.out.println("test");
			}
			// Search with rolename without username and without name and without firstname
			else if(roleName!="" &&  i.getUserRole().toString().equalsIgnoreCase(roleName) && userName=="" && name=="" && firstName=="")
			{
				userList.add(i);
			}
			else
			{
				// Search without rolename
				if(roleName=="")
				{
					// Search with username and name and firstname without rolename
					if(i.getUserName().toString().equalsIgnoreCase(userName) && i.getName().toString().equalsIgnoreCase(name) && i.getFirstName().toString().equalsIgnoreCase(firstName)) 
					{
						userList.add(i);
					}
					// Search with username and name without firstname and without rolename
					if(i.getUserName().toString().equalsIgnoreCase(userName) && i.getName().toString().equalsIgnoreCase(name) && firstName=="") 
					{
						userList.add(i);
					}
					// Search with username and firstname without name and without rolename
					else if(i.getUserName().toString().equalsIgnoreCase(userName) && i.getFirstName().toString().equalsIgnoreCase(firstName) && name=="")
					{
						userList.add(i);
					}
					// Search with firstname without username and without name and without role
					else if(i.getFirstName().toString().equalsIgnoreCase(firstName) && userName=="" && name=="")
					{
						userList.add(i);
					}
					// Search with name without username and without firstname and without role
					else if(i.getName().toString().equalsIgnoreCase(name) && userName=="" && firstName=="")
					{
						userList.add(i);
					}
					// Search with name and firstname without username and without role
					else if(i.getName().toString().equalsIgnoreCase(name) && i.getFirstName().toString().equalsIgnoreCase(firstName) && userName=="")
					{
						userList.add(i);
						System.out.println("test");
					}
					// Search with username without name and without firstname and without role
					else if(i.getUserName().toString().equalsIgnoreCase(userName) && name=="" && firstName=="")
					{
						userList.add(i);
					}
				}
			}
		}
		return userList;
	}

	public long findbyRoleName(String roleName)
	{
		for (UserRole i:userRoleRepository.findAll())
		{
			if(i.getRoleName().equals(roleName))
			{
				return i.getPKuserRole();
			}
		}
		return 0;
	}

	public boolean userExist(String username)
	{
		for (User i:userRepository.findAll())
		{
			if(i.getUserName().equals(username))
			{
				return true;
			}
		}
		return false;
	}

	public void createUser(String userName, String password, String name, String firstname, String address, String postalcode, String town, long role) {
		User user = new User();
		user.setUserName(userName);
		String encodedPassword = this.passwordEncoder.encode(password);
		user.setSecret(encodedPassword);
		user.setName(name);
		user.setFirstName(firstname);
		user.setAddress(address);
		user.setPostalCode(postalcode);
		user.setTown(town);
		user.setUserRole(userRoleRepository.findById(role).get());
		userRepository.findAll().add(user);
		userRepository.save(user);
	}

	public void updateAccount(String userName, String name, String firstname, String address, String postalcode, String town) {
		User user = this.findUserByUsername(userName);
		user.setName(name);
		user.setFirstName(firstname);
		user.setAddress(address);
		user.setPostalCode(postalcode);
		user.setTown(town);
		this.name=name;
		this.firstname=firstname;
		userRepository.save(user);
	}

	public void updateUser(String userName, String name, String firstname, String address, String postalcode, String town, String password, String userRole) {
		User user = this.findUserByUsername(userName);
		user.setName(name);
		user.setFirstName(firstname);
		user.setAddress(address);
		user.setPostalCode(postalcode);
		user.setTown(town);
		user.setUserName(userName);
		if(!password.equals(""))
		{
			String encodedPassword = this.passwordEncoder.encode(password);
			user.setSecret(encodedPassword);
		}
		user.setUserRole(userRoleRepository.findById(findbyRoleName(userRole)).get());
		userRepository.save(user);

	}

	public void deleteUser(String userName)
	{
		User user = this.findUserByUsername(userName);
		userRepository.delete(user);
	}

	public void updatePassword(String userName, String password) {
		User user = this.findUserByUsername(userName);
		String encodedPassword = this.passwordEncoder.encode(password);
		user.setSecret(encodedPassword);
		userRepository.save(user);
	}

	// Check roles used by GlobalControllerAdvice
	public boolean isStaff()
	{
		if(roleID==1 || roleID==2 || roleID==3 || roleID==4)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean isCustomer()
	{
		if(roleID==5) // 5 = Customer
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isAdmin()
	{
		if(roleID==4) // 4 = Admin
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isCleaning()
	{
		if(roleID==3) // 3 = Cleaning staff
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isDesk()
	{
		if(roleID==2) // 2 = Desk
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isShop()
	{
		if(roleID==1) // 1 = Shop
		{
			return true;
		}
		else
		{
			return false;
		}
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

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public long getRoleID() {
		return roleID;
	}

	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}

}

