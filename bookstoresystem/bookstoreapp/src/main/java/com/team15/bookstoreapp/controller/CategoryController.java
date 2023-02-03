package com.team15.bookstoreapp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team15.bookstoreapp.exception.BaseException;
import com.team15.bookstoreapp.exception.ResultBo;
import com.team15.bookstoreapp.model.CodeEntity;
import com.team15.commonmybatis.model.Category;
import com.team15.commonmybatis.model.Users;
import com.team15.commonmybatis.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixia
 * @since 2023-01-30
 */
@RestController
@RequestMapping("categoryAPI")

public class CategoryController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CategoryService categoryService;
    @RequestMapping(value="categories",produces = "application/json;charset=utf-8", method= RequestMethod.GET)
    public @ResponseBody
    ResultBo categories() {

        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        List<Category> list;
        try {

            list= categoryService.list();
            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, list);
            resultBo.setRecordsTotal(list.size());
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }

    @RequestMapping(value="categories/{id}",produces = "application/json;charset=utf-8", method= RequestMethod.GET)
    public @ResponseBody
    ResultBo categoriesByID(@PathVariable int id) {
        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        List<Category> list;
        try {
            QueryWrapper<Category> queryWrapper =new QueryWrapper<>();
            queryWrapper.lambda().eq(Category::getFatherID,id);
            list= categoryService.list(queryWrapper);
            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, list);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }
}
