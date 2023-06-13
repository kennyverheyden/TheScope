package thescope.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import thescope.exceptions.EntityNotFoundException;
import thescope.models.Movie;
import thescope.models.ScheduleShow;
import thescope.models.TheaterRoom;
import thescope.repositories.ScheduleShowRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScheduleShowService {


	@Autowired
	private  ScheduleShowRepository scheduleShowRepository;

	public ScheduleShowService() {}

	public ScheduleShow findScheduleShowById(long id) {
		Optional<ScheduleShow> entity = scheduleShowRepository.findById(id);
		return unwrapScheduleShow(entity, id);
	}
	public void addScheduleShow(ScheduleShow scheduleShow) {
		scheduleShowRepository.save(scheduleShow);
	}

	public List<ScheduleShow> findAll()
	{
		return scheduleShowRepository.findAll();
	}

	public List<ScheduleShow> findByLocation(String room)
	{
		List <ScheduleShow> schedules = scheduleShowRepository.findAll();
		List <ScheduleShow> foundSchedules = new ArrayList();
		for(ScheduleShow schedule:schedules)
		{
			if(schedule.getTheaterRoom().getLocation().equalsIgnoreCase(room))
			{
				foundSchedules.add(schedule);
			}
		}
		return foundSchedules;
	}

	public void deleteScheduleShowById(Long id) {
		scheduleShowRepository.deleteById(id);
	}

	public static ScheduleShow unwrapScheduleShow(Optional<ScheduleShow> entity, Long id) {
		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new EntityNotFoundException(id, ScheduleShow.class);
		}
	}

	// User can not delete a room when assigned to a schedule
	public boolean roomHasSchedule(TheaterRoom room)
	{
		List <ScheduleShow> schedules = scheduleShowRepository.findAll();
		for(ScheduleShow schedule:schedules)
		{
			if(schedule.getTheaterRoom().getPKtheaterRoom()==room.getPKtheaterRoom())
			{
				return true;
			}
		}
		return false;
	}

	// User can not delete a movie when assigned to a schedule
	public boolean movieHasSchedule(Movie movie)
	{
		List <ScheduleShow> schedules = scheduleShowRepository.findAll();
		for(ScheduleShow schedule:schedules)
		{
			if(schedule.getMovie().getPKmovie()==movie.getPKmovie())
			{
				return true;
			}
		}
		return false;
	}

	// Check if room is occupied on a specific time
	public boolean checkIfRoomIsOccupied(Long roomID, String strDate, String strTime)
	{
		List<ScheduleShow> schedules = this.findAll();
		Time time = Time.valueOf(strTime);
		Date date = Date.valueOf(strDate);
		
		Long threeHours=10800000L; // 3 hours
		Long timeL = time.getTime(); // Convert time to 1000ms
		for(ScheduleShow schedule:schedules)
		{
			Time scheduleTime = Time.valueOf(schedule.getTime());
			long c = time.getTime() - scheduleTime.getTime(); // Calculate difference in 1000ms
			if(schedule.getTheaterRoom().getPKtheaterRoom()==roomID && schedule.getDate().equals(strDate) && c<threeHours && c>-threeHours) // Check date and 3 hours before and after
			{
				{
					return true;
				}
			}
		}
		return false;
	}

}
