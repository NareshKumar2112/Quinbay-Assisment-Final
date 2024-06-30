package com.blibli.inventory.Service.ServiceImplementation;

import com.blibli.inventory.DAO.Model.*;
import com.blibli.inventory.DAO.Repository.*;
import com.blibli.inventory.Dto.*;
import com.blibli.inventory.Exception.*;
import com.blibli.inventory.Service.serviceInterface.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cache.annotation.*;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CategoryService implements CategoryServiceInterface {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StringRedisTemplate redisStringTemplate;


    public Category addCategory(Category category) {
        Category category1=categoryRepository.save(category);
        return category1;
    }

    public List<CategoryDto> getAllCategory()
    {
        List<Category> categoryList=categoryRepository.findAll();
        List<CategoryDto> categoryDtosList=new ArrayList<>();
        for(int i=0;i<categoryList.size();i++)
        {
            CategoryDto categoryDto=new CategoryDto();
            categoryDto.setName(categoryList.get(i).getName());
            categoryDto.setProductList(categoryList.get(i).getProductList());
            categoryDtosList.add(categoryDto);
        }
        return categoryDtosList;
    }

    public Optional<Category> getCategoryById(long id)throws JsonMappingException, JsonProcessingException, CategoryNotFoundException {
        String key = "category::" + id;
        System.out.println("ss");
        String cacheValue = redisStringTemplate.opsForValue().get(key);
        System.out.println(cacheValue);
        if (cacheValue != null) {
            Category category = new Category();
            System.out.println("\nFetching from Cache Memory...\n");
            try {
                category = objectMapper.readValue(cacheValue, new TypeReference<Category>() {
                });
                return Optional.ofNullable(category);
            } catch (Exception e) {
                return null;
            }
        } else {
            try {
                System.out.println("database");
                Optional<Category> category1 = categoryRepository.findById(id);
                if(category1!=null) {

                    String serializedString = objectMapper.writeValueAsString(category1.get());
                    System.out.println(serializedString);
                    redisStringTemplate.opsForValue().set(key, serializedString);
                    return category1;
                }
                else
                {
                    throw new CategoryNotFoundException("category is not present");
                }
            } catch (Exception e) {
                return null;
            }
        }
    }

    @Cacheable(value = "category")
    public String getCategoryUsingId(long id)
    {
        String key="category::"+id;
        System.out.println("ss");
        String cacheValue = redisStringTemplate.opsForValue().get(key);
        System.out.println("cscs");
        if(cacheValue != null)
        {
            Category category=new Category();
            System.out.println("\nFetching from Cache Memory...\n");
            try {
                category = objectMapper.readValue(cacheValue, new TypeReference<Category>() {
                });
                return category.toString();
            }
            catch(Exception e)
            {
                return null;
            }
        }
        else {
            try {
                System.out.println("database");
                Optional<Category> category1 = categoryRepository.findById(id);
                String serializedString = objectMapper.writeValueAsString(category1.get());
                System.out.println(serializedString);
                redisStringTemplate.opsForValue().set(key, serializedString);
                return category1.toString();
            } catch (Exception e) {
                return null;
            }
        }
    }

    @CacheEvict(value = "category",key="#id")
    public String deleteCategory(long id) {
        categoryRepository.deleteById(id);
        return "deleted successfully";
    }
}
