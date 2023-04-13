package thescope.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thescope.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	User findUserByUserName (String userName);
}
