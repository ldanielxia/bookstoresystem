package com.team15.bookstoreapp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team15.bookstoreapp.exception.BaseException;
import com.team15.bookstoreapp.exception.ResultBo;
import com.team15.bookstoreapp.model.CodeEntity;
import com.team15.bookstoreapp.model.SearchBase;
import com.team15.commonmybatis.model.Book;
import com.team15.commonmybatis.model.BookExtend;
import com.team15.commonmybatis.model.Category;
import com.team15.commonmybatis.service.BookService;
import com.team15.commonmybatis.service.BookimageService;
import com.team15.commonmybatis.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lixia
 * @since 2023-01-30
 */
@RestController
@RequestMapping("bookAPI")
public class BookController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    BookService bookService;
    @RequestMapping(value="booktop10list",produces = "application/json;charset=utf-8", method= RequestMethod.GET)
    public @ResponseBody
    ResultBo bookTop10List() {

        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        List<BookExtend> list;
        try {

            list= bookService.top10List();
            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, list);
            resultBo.setRecordsTotal(list.size());
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }

    @RequestMapping(value="search",produces = "application/json;charset=utf-8", method= RequestMethod.POST)
    public @ResponseBody
    ResultBo searchBook(@RequestBody SearchBase searchBase) {
       String keyword=searchBase.keyword;
        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        List<BookExtend> list;
        try {

            QueryWrapper<Book> queryWrapper =new QueryWrapper<>();
            queryWrapper.lambda().like(Book::getBookTitle,keyword);
            queryWrapper.or().lambda().like(Book::getAuthor,keyword);
            list= bookService.searchBook(queryWrapper);
            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, list);
            resultBo.setRecordsTotal(list.size());
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }
}
