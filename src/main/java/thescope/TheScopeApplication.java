package thescope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import thescope.models.Booking;
import thescope.models.Tarifs;
import thescope.models.TarifsList;
import thescope.services.BookingService;
import thescope.services.TarifsListService;
import thescope.services.TarifsService;

@SpringBootApplication
public class TheScopeApplication{

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx=
		 SpringApplication.run(TheScopeApplication.class, args);

		 BookingService b= ctx.getBean(BookingService.class);
		 TarifsService s= ctx.getBean(TarifsService.class);
		
		System.out.println("hallo allemaal!!");
		System.out.println(s.findTarifsById(2).getPriceTaxIncl());
		
		TarifsListService t=ctx.getBean(TarifsListService.class);
		t.addTarifsList(new TarifsList(s.findTarifsById(2),b.findBookingById(2),4));
		
	}

}
