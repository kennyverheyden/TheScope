package thescope.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import thescope.models.ShopList;

@Repository
public class ShopListRepository {

	//Database connection
	@Autowired
	private JdbcTemplate jdbc;

	public List<ShopList> selectShopArticles() {
		String sqlSelect = "SELECT artNO, description, stock, orderQTY, priceTAXexcl, priceTAXincl FROM tblShopList";

		RowMapper<ShopList> rowMapper = (r, i) -> {
			ShopList rowObject =
					new ShopList(r.getLong("artNO"),r.getString("description"),r.getInt("stock"),r.getInt("orderQTY"),r.getBigDecimal("priceTAXexcl"),r.getBigDecimal("priceTAXincl"));
			return rowObject;
		};
		return jdbc.query(sqlSelect, rowMapper);
	}


}
