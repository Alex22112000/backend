package model.interfaces.in;

import java.util.List;

import model.dto.Product;
import model.interfaces.out.IProductRepository;

public interface IModelProduct {

    public void setRepository(IProductRepository productRepository);

    boolean addNewProduct(String name, String cost, String count, String img);

    boolean delProduct(String name);

    List<Product> getFullCatalog();
}
