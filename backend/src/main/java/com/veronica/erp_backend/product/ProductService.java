package com.veronica.erp_backend.product;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductRepository repo;
    public ProductService(ProductRepository repo){ this.repo = repo; }

    public List<Product> list(){ return repo.findAll(); }
    public Product get(Long id){ return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found: "+id)); }
    public Product create(Product p){
        repo.findBySkuIgnoreCase(p.getSku()).ifPresent(x -> { throw new RuntimeException("SKU exists: "+p.getSku()); });
        return repo.save(p);
    }
    public Product update(Long id, Product patch){
        Product e = get(id);
        e.setSku(patch.getSku()); e.setName(patch.getName());
        e.setPrice(patch.getPrice()); e.setActive(patch.getActive());
        e.setStockQty(patch.getStockQty());
        return repo.save(e);
    }
    public void delete(Long id){ repo.deleteById(id); }
}