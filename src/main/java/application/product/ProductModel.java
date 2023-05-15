package application.product;

import java.util.List;

public class ProductModel implements IModelProduct {

    private IProductRepository productRepository;

    @Override
    public boolean addNewProduct(String name, Integer cost, Integer count, String img) {
        Product product = new Product();
        product.setImg(img);
        product.setName(name);
        product.setCost(cost);
        product.setCount(count);
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
