package thescope.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="tblCleaningSchedule")
public class CleaningSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long PKcleaningSchedule;
	
	
}
