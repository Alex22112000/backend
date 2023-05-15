package infrastructure.jpa.product;

import java.util.ArrayList;
import java.util.List;
import application.product.IProductRepository;
import application.product.Product;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ProductRepository implements IProductRepository {

    @PersistenceContext(unitName = "shopProducts_PersistenceUnit")
    private EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        String query = "select p from EProduct p";
        List<EProduct> eProducts = entityManager.createQuery(query, EProduct.class).getResultList();
        List<Product> products = new ArrayList<>();
        for (EProduct eProduct : eProducts) {
            Product product = new Product();
            product.setImg(eProduct.getImg());
            product.setCost(eProduct.getCost());
            product.setCount(eProduct.getCount());
            product.setName(eProduct.getName());
            products.add(product);
        }
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        boolean status = true;
        try {
            EProduct eProduct = new EProduct();
            eProduct.setName(product.getName());
            eProduct.setImg(product.getImg());
            eProduct.setCost(product.getCost());
            eProduct.setCount(product.getCount());
            entityManager.persist(eProduct);
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        }
        return status;
    }

    @Override
    public boolean deleteProduct(String name) {
        String query = "delete from EProduct p where p.name=:name";
        boolean status = true;
        try {
            entityManager.createQuery(query, EProduct.class)
                    .setParameter("name", name).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        }
        return status;
    }
    
}
