package thescope.services;


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
	String userName;
	String secret;
	String name;
	String firstname;
	String userRole;
	long roleID;

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
			if(i.getUserName().equals(username))
			{
				return i;
			}
		}
		return null;
	}

	public long findRolID(String roleName)
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
		em.persist(user);
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
		em.persist(user);
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
		this.name=name;
		this.firstname=firstname;
		this.roleID=findRolID(userRole); // for session and showing role name directly
		user.setUserRole(userRoleRepository.findById(roleID).get());
		em.persist(user);

	}

	public void updatePassword(String userName, String password) {
		User user = em.find(User.class,this.findUserByUsername(userName).getPKuser());
		String encodedPassword = this.passwordEncoder.encode(password);
		user.setSecret(encodedPassword);
		em.persist(user);
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

