package com.example.errors.web;

import com.example.errors.model.ApiErrorDTO;
import com.example.errors.model.ProductDTO;
import com.example.errors.model.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/product")
public class ProductRestController {

    private ProductDTO getProductDTOById(Long id) {
        return null;
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id) {
        ProductDTO productDTO = getProductDTOById(id);

        // uncomment for controller, based exception handling

        if (productDTO == null) {
            throw new ProductNotFoundException(id);
        }

        return ResponseEntity.ok(productDTO);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ProductNotFoundException.class})
    public @ResponseBody ApiErrorDTO handleRESTErrors(ProductNotFoundException e) {
        return new ApiErrorDTO(e.getId(),
                "Product was not found");
    }
}



