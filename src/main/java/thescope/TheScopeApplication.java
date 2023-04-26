package thescope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, R2dbcAutoConfiguration.class }) // Ignore login screen from Spring Security
public class TheScopeApplication {


	public static void main(String[] args) {
		 SpringApplication.run(TheScopeApplication.class, args);


		 //ShopListService s= ctx.getBean(ShopListService.class);


		 
//		 ShopList item= new ShopList();
//		 item.setDescription("M&Ms pinda 200g");
//		 item.setCategory(ShopCategory.CANDY);
//		 item.setInStock(7);
//		 item.setOrderQuantity(20);
//		 item.setPriceTaxEx(2.14d);
//		 item.setPriceTaxIn(2.50d);
//		 s.AddShopList(item);
	}

	/**
	 * Deze klasse clean laten, het enige wat hier moet instaan is app.run(). Indien je wilt testen, doe dit via JUnit
	 * of integratietesten.
	 *
	 * Security configs moeten hier wel komen.
	 *
 	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
