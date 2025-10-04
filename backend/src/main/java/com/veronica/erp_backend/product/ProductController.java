package com.veronica.erp_backend.product;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service){ this.service = service; }

    @GetMapping public List<Product> list(){ return service.list(); }

    @GetMapping("/{id}") public Product get(@PathVariable Long id){ return service.get(id); }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product p){
        Product saved = service.create(p);
        return ResponseEntity.created(URI.create("/api/products/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}") public Product update(@PathVariable Long id, @Valid @RequestBody Product p){
        return service.update(id, p);
    }

    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id); return ResponseEntity.noContent().build();
    }
}