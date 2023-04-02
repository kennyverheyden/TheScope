package thescope.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thescope.models.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long>{
	
}