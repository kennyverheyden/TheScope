package thescope.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thescope.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
}
