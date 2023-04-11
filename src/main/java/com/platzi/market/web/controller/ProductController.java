package com.platzi.market.web.controller;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("/all")
    @ApiOperation("Obtiene todos los productos") // Se implementa para swagger
    @ApiResponse(code = 200, message = "OK") // Se implementa para swagger
    public ResponseEntity<List<Product>> getAll(){

        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }
        @GetMapping("/{id}")
        @ApiOperation("Devuelve producto por ID")
        @ApiResponses({             //Se implementa para swagger, como el metodo tiene dos posibles respuestas
                @ApiResponse(code = 200, message = "OK"),
                @ApiResponse(code = 400, message = "NOT FOUND")
        })
    public ResponseEntity<Product> getProduct(@PathVariable("id") int productId){

        return productService.getProduct(productId).
                map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@ApiParam(value = "Ingresar id del producto", required = true, example = "7") @PathVariable("categoryId") int categoryId){

        return productService.getByCategory(categoryId).
                map(products -> new ResponseEntity<>(products, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/save")
    public ResponseEntity <Product> save(@RequestBody Product product){

        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId){
            if(productService.delete(productId)){
                return new ResponseEntity(HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }

    }

}
