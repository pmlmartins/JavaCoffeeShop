package nl.brianvermeer.workshop.coffee.repository;

import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import nl.brianvermeer.workshop.coffee.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SearchRepository {

  @Autowired
  EntityManager em;

  @Autowired
  DataSource dataSource;

  public List<Product> searchProduct(String input) {
    var lowerInput = input.toLowerCase(Locale.ROOT);
    String query = "Select * from Product where lower(description) like ?1  OR lower(product_name) like ?2";

    var q = em.createNativeQuery(query, Product.class);
    q.setParameter(1, "%" + lowerInput + "%");
    q.setParameter(2, "%" + lowerInput + "%");
    return q.getResultList();
  }

}
