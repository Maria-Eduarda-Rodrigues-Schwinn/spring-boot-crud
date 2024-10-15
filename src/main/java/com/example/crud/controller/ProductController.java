package com.example.crud.controller;

import com.example.crud.domain.product.Product;
import com.example.crud.domain.product.ProductDTO;
import com.example.crud.domain.product.ProductRepository;
import com.example.crud.domain.product.ProductRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {

        var allProducts = repository.findAllByActiveTrue();

        var productDTOs = allProducts.stream()
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getPrice_in_cents(),
                        product.isActive()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(productDTOs);
    }

    @PostMapping
    public ResponseEntity<Void> registerProduct(@RequestBody @Valid ProductRequest data) {

        Product newProduct = new Product(data);

        repository.save(newProduct);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody @Valid ProductRequest data) {

        Optional<Product> optionalProduct = repository.findById(data.id());

        if (optionalProduct.isPresent()) {

            Product product = optionalProduct.get();

            product.setName(data.name());
            product.setPrice_in_cents(data.price_in_cents());

            ProductDTO productDTO = new ProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getPrice_in_cents(),
                    product.isActive()
            );

            return ResponseEntity.ok(productDTO);
        }

        return ResponseEntity.notFound().build();

    }
}
