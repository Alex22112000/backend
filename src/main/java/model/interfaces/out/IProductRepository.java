package model.interfaces.out;

import java.util.List;

import model.dto.Product;

public interface IProductRepository {
    public List<Product> findAll();
    public boolean addProduct(Product product);
    public boolean deleteProduct(String name);
}
