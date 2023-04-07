package thescope;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.metamodel.Metamodel;
import thescope.models.Booking;
import thescope.models.ScheduleShow;
import thescope.models.Tarifs;
import thescope.models.TarifsList;
import thescope.repositories.TarifsRepository;
import thescope.services.BookingService;
import thescope.services.ScheduleShowService;
import thescope.services.TarifsListService;
import thescope.services.TarifsService;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TheScopeApplication{

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx=
		 SpringApplication.run(TheScopeApplication.class, args);

		 
	}

}
