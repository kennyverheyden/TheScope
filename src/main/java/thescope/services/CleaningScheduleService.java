package thescope.services;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thescope.repositories.BookingRepository;

import java.util.List;

@Service
@Transactional
public class CleaningScheduleService {

    @Autowired
    private BookingRepository bookingRepository;

}
