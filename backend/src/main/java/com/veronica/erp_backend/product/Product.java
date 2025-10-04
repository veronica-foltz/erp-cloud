package com.veronica.erp_backend.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(name = "uk_products_sku", columnNames = "sku"))
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(nullable = false, length = 64)
    private String sku;

    @NotBlank @Column(nullable = false, length = 200)
    private String name;

    @NotNull @DecimalMin("0.00")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @NotNull @Column(nullable = false)
    private Boolean active = true;

    @NotNull @Min(0) @Column(nullable = false, name = "stock_qty")
    private Integer stockQty = 0;

    // getters/setters
    public Long getId() { return id; } public void setId(Long id){ this.id=id; }
    public String getSku(){ return sku; } public void setSku(String sku){ this.sku=sku; }
    public String getName(){ return name; } public void setName(String name){ this.name=name; }
    public BigDecimal getPrice(){ return price; } public void setPrice(BigDecimal price){ this.price=price; }
    public Boolean getActive(){ return active; } public void setActive(Boolean active){ this.active=active; }
    public Integer getStockQty(){ return stockQty; } public void setStockQty(Integer stockQty){ this.stockQty=stockQty; }
}