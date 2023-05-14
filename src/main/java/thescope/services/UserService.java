package thescope.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import thescope.exceptions.EntityNotFoundException;
import thescope.models.Booking;
import thescope.models.User;
import thescope.models.UserRole;
import thescope.processors.UserDetailsImpl;
import thescope.repositories.UserRepository;
import thescope.repositories.UserRoleRepository;

@Service
@Transactional 
public class UserService implements UserDetailsService{

	@Autowired
	private  UserRepository userRepository;
	@Autowired
	private  UserRoleRepository userRoleRepository;
	@Autowired UserDetailsImpl userDetails;

	private  PasswordEncoder passwordEncoder;

	@Autowired
	BookingService bookingService; // Cannot delete a user when he has one or more bookings

	public UserService() {
		this.passwordEncoder =  new BCryptPasswordEncoder();
	}

	// Global credentials for processing
	private String userName;
	private boolean secret;
	private String name;
	private String firstname;
	private String userRole;
	long roleID;

	// Part of Spring security
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUserName(username);
		if(user==null) {
			throw new UsernameNotFoundException(username);
		}
		else
		{
			userDetails.setUser(user);
		}
		return userDetails;
	}

	public List<User> list() {
		return userRepository.findAll();
	}
	public User findUserById(long id) {
		Optional<User> entity = userRepository.findById(id);
		return unwrapUser(entity, id);
	}

	public List<UserRole> userRoles() {
		return userRoleRepository.findAll();
	}

	public User findUserByUsername(String userName)
	{
		return userRepository.findUserByUserName(userName);
	}

	public boolean hasBookings(User user)
	{
		List<Booking> bookings = bookingService.findByUser(user);
		if(bookings.size()==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	// Used for searching users by admin
	public List<User> findUsers(String userName, String name, String firstName, String roleName)
	{
		List<User> userList = new ArrayList<>();
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
			else if(roleName!="" && i.getUserRole().toString().equalsIgnoreCase(roleName) && i.getUserName().toString().equalsIgnoreCase(userName) && i.getFirstName().toString().equalsIgnoreCase(firstName)  && name=="")
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
		User user = userRepository.findUserByUserName(username);
		if(user==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public User createUser(String userName, String password, String name, String firstname, String address, String postalcode, String town, long role) {
		Optional<UserRole> entity = userRoleRepository.findById(role);
		User user = new User();
		user.setUserName(userName);
		String encodedPassword = this.passwordEncoder.encode(password);
		user.setSecret(encodedPassword);
		user.setName(name);
		user.setFirstName(firstname);
		user.setAddress(address);
		user.setPostalCode(postalcode);
		user.setTown(town);
		user.setUserRole(unwrapUserRole(entity, role));
		userRepository.findAll().add(user);
		userRepository.save(user);
		return user;
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
		Optional<UserRole> entity = userRoleRepository.findById(findbyRoleName(userRole));
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
		user.setUserRole(unwrapUserRole(entity, findbyRoleName(userRole)));
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

	public static User unwrapUser(Optional<User> entity, Long id) {
		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new EntityNotFoundException(id, User.class);
		}
	}

	public static UserRole unwrapUserRole(Optional<UserRole> entity, Long id) {
		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new EntityNotFoundException(id, UserRole.class);
		}
	}

}

