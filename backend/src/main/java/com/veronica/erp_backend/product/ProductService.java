package main.java.com.veronica.erp_backend.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> list() {
        return repo.findAll();
    }

    public Product get(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    public Product create(Product p) {
        repo.findBySkuIgnoreCase(p.getSku()).ifPresent(x -> {
            throw new RuntimeException("SKU already exists: " + p.getSku());
        });
        return repo.save(p);
    }

    public Product update(Long id, Product patch) {
        Product existing = get(id);
        existing.setSku(patch.getSku());
        existing.setName(patch.getName());
        existing.setPrice(patch.getPrice());
        existing.setActive(patch.getActive());
        existing.setStockQty(patch.getStockQty());
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}