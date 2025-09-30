# ERP Cloud (Mini-ERP)

Spring Boot + H2 (dev) backend for inventory/orders/invoices.
- Run backend: `.\mvnw.cmd spring-boot:run` (Windows) or `./mvnw spring-boot:run` (Mac/Linux)
- Health: `GET http://127.0.0.1:8080/api/health`
- Products: `GET http://127.0.0.1:8080/api/products`

Dev DB: in-memory H2 (no install). H2 console at `/h2-console` with JDBC URL `jdbc:h2:mem:erpdb`.