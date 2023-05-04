package model;

import java.util.List;

import model.dto.Product;
import model.interfaces.in.IModelProduct;
import model.interfaces.out.IProductRepository;

public class ProductModel implements IModelProduct {

    IProductRepository productRepository;

    @Override
    public boolean addNewProduct(String name, String cost, String count, String img) {
        Product product = new Product();
        product.setImg(img);
        product.setProduct_name(name);
        product.setProduct_cost(Integer.parseInt(cost));
        product.setProduct_count(Integer.parseInt(count));
        return productRepository.addProduct(product);
    }

    @Override
    public boolean delProduct(String name) {
        return productRepository.deleteProduct(name);
    }

    @Override
    public List<Product> getFullCatalog() {
        return productRepository.findAll();
    }

    @Override
    public void setRepository(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
}
