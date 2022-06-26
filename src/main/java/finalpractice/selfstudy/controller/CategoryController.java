package finalpractice.selfstudy.controller;

import finalpractice.selfstudy.dto.CategoryGetDto;
import finalpractice.selfstudy.dto.CategoryPostDto;
import finalpractice.selfstudy.entity.Category;
import finalpractice.selfstudy.service.CategoryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    public String joinCategory(@RequestBody CategoryPostDto categoryPostDto){

        Category category = new Category();
        category.setName(categoryPostDto.getName());

        // todo : null 처리 필요. 현재는 입력된 카테고리 id가 db에 없을 경우 null, 있을 경우 정상 등
        //Optional<Category> findCategory = categoryService.findById(categoryPostDto.getParentCategoryId());
        //findCategory.ifPresent(category::setParent);

        Long parentCategoryId = categoryPostDto.getParentCategoryId();
        categoryService.joinCategory(category, parentCategoryId);

        return "ok";
    }

    // 자기참조, 딸린 item까지 모두 리턴
    @GetMapping("/category")
    public List<CategoryGetDto> findALlCategory() {

        List<CategoryGetDto> categoryGetDtos = new ArrayList<>();
        List<Category> categories = categoryService.findAllCategories();

        categories.stream().forEach(x -> categoryGetDtos.add(new CategoryGetDto(
                x.getName(), x.getItem(), x.getParent(), x.getChild()
        )));


        // System.out.println("categories = " + categoryGetDtos.get(0).getParent().getName());
        return categoryGetDtos;
    }
}
