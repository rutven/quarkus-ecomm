package name.legkodymov.ecom.model;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Order order;

    @Column(name = "product_id")
    private Long productId;

    private Double price;

    private Integer amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
