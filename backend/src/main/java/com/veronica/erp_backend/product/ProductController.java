package main.java.com.veronica.erp_backend.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    // quick probe: should return "products ok"
    @GetMapping("/api/products/ping")
    public String ping() {
        return "products ok";
    }

    // simple demo list so we know the mapping works
    @GetMapping("/api/products")
    public List<Map<String, Object>> demo() {
        return List.of(
                Map.of("sku", "DEMO-1", "name", "Demo Item", "price", 1.23, "active", true, "stockQty", 5)
        );
    }
}