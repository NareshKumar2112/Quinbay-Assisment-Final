package com.blibli.inventory.Service.serviceInterface;

import com.blibli.inventory.DAO.Model.*;
import com.blibli.inventory.Dto.*;
import com.blibli.inventory.Exception.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import java.util.*;

public interface CategoryServiceInterface {

    public Category addCategory(Category category);

    public List<CategoryDto> getAllCategory();

    public Optional<Category> getCategoryById(long id)throws JsonMappingException, JsonProcessingException, CategoryNotFoundException;

    public String deleteCategory(long id);
}
