package com.blibli.inventory.Controller;

import com.blibli.inventory.DAO.Model.*;
import com.blibli.inventory.Dto.*;

import com.blibli.inventory.Exception.*;
import com.blibli.inventory.Service.ServiceImplementation.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Category addCategory(@RequestBody Category category)
    {
        return categoryService.addCategory(category);
    }
    @GetMapping("/get")
    public List<CategoryDto> getAllCategory()
    {
        return categoryService.getAllCategory();
    }
    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable long id)
    {
        return categoryService.deleteCategory(id);
    }
    @GetMapping("/get/{id}")
    public CategoryDto getCategoryById(@PathVariable long id)throws JsonMappingException, JsonProcessingException, CategoryNotFoundException
    {
        Optional<Category> categoryOptional =categoryService.getCategoryById(id) ;
        if(categoryOptional!=null) {
            Category category=categoryOptional.get();
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setProductList(category.getProductList());
            categoryDto.setName(category.getName());
            return categoryDto;
        }
        else
        {
           throw new CategoryNotFoundException("category not found");
       
        }
    }
}
