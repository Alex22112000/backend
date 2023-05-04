package DBlayer.repositories;

import java.util.ArrayList;
import java.util.List;

import DBlayer.entities.EProduct;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.UserTransaction;
import model.dto.Product;
import model.interfaces.out.IProductRepository;

public class ProductRepository implements IProductRepository {

    @PersistenceUnit(unitName = "shopProducts_PersistenceUnit")
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @Resource
    UserTransaction userTransaction;

    @Override
    public List<Product> findAll() {
        entityManager = entityManagerFactory.createEntityManager();
        String query = "select p from EProduct p";
        List<EProduct> eProducts = entityManager.createQuery(query, EProduct.class).getResultList();
        List<Product> products = new ArrayList<>();
        for (EProduct eProduct : eProducts) {
            Product product = new Product();
            product.setProduct_id(eProduct.getId());
            product.setImg(eProduct.getImg());
            product.setProduct_cost(eProduct.getCost());
            product.setProduct_count(Integer.parseInt(eProduct.getCount()));
            product.setProduct_name(eProduct.getName());
            products.add(product);
        }
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        entityManager = entityManagerFactory.createEntityManager();
        boolean status = true;
        try {
            EProduct eProduct = new EProduct();
            eProduct.setName(product.getProduct_name());
            eProduct.setImg(product.getImg());
            eProduct.setCost(product.getProduct_cost());
            eProduct.setCount(String.valueOf(product.getProduct_count()));
            userTransaction.begin();
            entityManager.joinTransaction();
            entityManager.persist(eProduct);
            userTransaction.commit();
        } catch (Exception e) {
            System.out.println(e);
            status = false;
        }
        return status;
    }

    @Override
    public boolean deleteProduct(String name) {
        entityManager = entityManagerFactory.createEntityManager();
        String query = "delete from EProduct p where p.name=:name";
        boolean status = true;
        try {
            userTransaction.begin();
            entityManager.joinTransaction();
            entityManager.createQuery(query, EProduct.class)
                    .setParameter("name", name).executeUpdate();
            userTransaction.commit();
        } catch (Exception e) {
            System.out.println(e);
            status = false;
        }
        return status;
    }
    
}
