package com.zest.technical.controller;

import com.zest.technical.service.ProductService;
import com.zest.technical.service.ItemService;
import com.zest.technical.model.Product;
import com.zest.technical.model.Item;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Product APIs", description = "APIs for managing products")
@SecurityRequirement(name = "Bearer Authentication")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ItemService itemService;



    //for a sample i have added api versioning for this specifuc api alone
    //** header , param apiversioning can be done according the bussines case
    @Operation(summary = "Get all products", description = "Fetches all products.  +version is not a manadotry  + done it asyncially  ")
    @GetMapping()
    public List<Product> getAllProducts(@RequestParam(required = false) String version) {
        if(version!=null){
            return productService.getAllProducts();
        }
        else { return productService.getAllProducts(); }
    }

    @Operation(summary = "Get product by ID", description = "Fetches a product by its ID.")
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @Operation(summary = "Create a new product", description = "Creates a new product. Requires 'ADMIN' role.")
    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @Operation(summary = "Update a product", description = "Updates the details of a product by its ID. Requires 'ADMIN' role.")
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @Operation(summary = "Delete a product", description = "Deletes a product by its ID. Requires 'ADMIN' role.")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @Operation(summary = "Get items by product ID", description = "Fetches all items associated with a specific product.")
    @GetMapping("/{id}/items")
    public List<Item> getItemsByProductId(@PathVariable Long id) {
        return itemService.getItemsByProductId(id);
    }
}
