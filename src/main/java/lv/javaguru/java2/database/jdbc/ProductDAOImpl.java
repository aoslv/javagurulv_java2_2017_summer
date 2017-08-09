package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ProductDAO;
import lv.javaguru.java2.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@Component
//@Qualifier("JDBC_PRODUCT_DAO")
public class ProductDAOImpl implements ProductDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static RowMapper<Product> rowMapper() {
        return (rs, rowNum) -> {
            Product p = new Product();
            p.setId(rs.getLong("id"));
            p.setTitle(rs.getString("title"));
            p.setDescription(rs.getString("description"));
            p.setCreatedAt(rs.getTimestamp("created_at"));
            p.setUpdatedAt(rs.getTimestamp("updated_at"));
            return p;
        };
    }


    @Override
    public Product save(Product product) throws DBException {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("products")
                .usingColumns("title", "description", "created_at", "updated_at")
                .usingGeneratedKeyColumns("id");

        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());

        Map parameters = new HashMap();
        parameters.put("title", product.getTitle());
        parameters.put("description", product.getDescription());
        parameters.put("created_at", product.getCreatedAt());
        parameters.put("updated_at", product.getUpdatedAt());

        Long id = insert.executeAndReturnKey(parameters).longValue();
        product.setId(id);


        return product;
    }

    @Override
    public Optional<Product> getById(Long id) throws DBException {
        List<Product> products = this.jdbcTemplate.query(
                "select * from PRODUCTS where id = ?",
                new Object[]{id},
                rowMapper());
        if (products.isEmpty()) {
            return Optional.empty();
        } else {
            if (products.size() == 1) {
                return Optional.ofNullable(products.get(0));
            } else {
                throw new IllegalStateException("More then one row returned!");
            }
        }
    }

    @Override
    public Optional<Product> getByTitle(String title) throws DBException {
        Product product = this.jdbcTemplate.queryForObject(
                "select * from PRODUCTS where title = ?",
                new Object[]{title},
                rowMapper());
        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> getAll() throws DBException {
        List<Product> products = this.jdbcTemplate.query(
                "select * from PRODUCTS",
                rowMapper());
        return products;
    }

    @Override
    public void delete(Product product) throws DBException {
        this.jdbcTemplate.update(
                "delete from products where id = ?",
                product.getId());
    }

}
