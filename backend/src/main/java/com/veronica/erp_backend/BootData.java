package com.veronica.erp_backend;

import com.veronica.erp_backend.product.Product;
import com.veronica.erp_backend.product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class BootData implements CommandLineRunner {
  private final ProductRepository repo;
  public BootData(ProductRepository repo){ this.repo = repo; }
  @Override public void run(String... args) {
    if (repo.count() == 0) {
      repo.save(newP("SKU-APPLE","Apple MacBook Air 13","999.00",10));
      repo.save(newP("SKU-MOUSE","Wireless Mouse","25.00",200));
      repo.save(newP("SKU-CHAIR","Ergo Office Chair","189.99",35));
    }
  }
  private Product newP(String sku,String name,String price,int qty){
    Product p=new Product(); p.setSku(sku); p.setName(name);
    p.setPrice(new BigDecimal(price)); p.setActive(true); p.setStockQty(qty); return p;
  }
}