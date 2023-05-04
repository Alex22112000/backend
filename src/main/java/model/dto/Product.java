package model.dto;

public class Product {
    private Integer product_id;

    private Integer product_cost;

    private String product_name;

    private Integer product_count;

    private String img;

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getProduct_cost() {
        return product_cost;
    }

    public void setProduct_cost(Integer product_cost) {
        this.product_cost = product_cost;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getProduct_count() {
        return product_count;
    }

    public void setProduct_count(Integer product_count) {
        this.product_count = product_count;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
