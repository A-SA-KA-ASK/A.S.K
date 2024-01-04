package com.example.askBackend.InitDB;

import com.example.askBackend.Category.entity.Category;
import com.example.askBackend.Category.repository.CategoryRepository;
import com.example.askBackend.Member.entity.Auth;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryInitDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        //initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final CategoryRepository categoryRepository;

        public void dbInit1() {
            List<Auth> authType1 = List.of(Auth.USER, Auth.ADMIN);
            List<Auth> authType2 = List.of(Auth.ADMIN);
            //List<Auth> authType3 = List.of(Auth.USER);

            Category categoryEntity1 = Category.createCategory("이미지/권한 누구나", true, authType1);
            categoryRepository.save(categoryEntity1);

            Category categoryEntity2 = Category.createCategory("글/권한 누구나", false, authType1);
            categoryRepository.save(categoryEntity2);

            Category categoryEntity3 = Category.createCategory("이미지/권한 관리자만", true, authType2);
            categoryRepository.save(categoryEntity3);

            Category categoryEntity4 = Category.createCategory("글/권한 관리자만", false, authType2);
            categoryRepository.save(categoryEntity4);
        }
    }
}