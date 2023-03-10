package com.team15.bookstoreapp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team15.bookstoreapp.exception.BaseException;
import com.team15.bookstoreapp.exception.ResultBo;
import com.team15.bookstoreapp.model.BookModelAndView;
import com.team15.bookstoreapp.model.CodeEntity;
import com.team15.commonmybatis.model.*;
import com.team15.commonmybatis.service.BookfavoritesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("bookfavoritesAPI")
public class BookfavoritesController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    BookfavoritesService bookfavoritesService;
    @RequestMapping(value="bookfavorites/{bookid}/{userid}",produces = "application/json;charset=utf-8", method= RequestMethod.GET)
    public @ResponseBody
    ResultBo bookfavorites(@PathVariable int bookid,@PathVariable  int userid) {
        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        try {
            QueryWrapper<Bookfavorites>  queryWrapper=new QueryWrapper<>();
            queryWrapper.lambda().eq(Bookfavorites::getBookId,bookid);
            queryWrapper.lambda().eq(Bookfavorites::getUserId,userid);
            Bookfavorites bookfavorites=bookfavoritesService.getOne(queryWrapper);
            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, bookfavorites);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }

    @RequestMapping(value="bookfavorites",produces = "application/json;charset=utf-8", method= RequestMethod.POST)
    public @ResponseBody
    ResultBo add(@RequestBody Bookfavorites bookfavorites) {
        ResultBo resultBo ;
        BaseException  restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        try {
            //search for bookfavorite if not exits
            QueryWrapper<Bookfavorites> queryWrapper=new QueryWrapper<>();
            queryWrapper.lambda().eq(Bookfavorites::getBookId,bookfavorites.getBookId());
            queryWrapper.lambda().eq(Bookfavorites::getUserId,bookfavorites.getUserId());
            LocalDateTime localDateTime = LocalDateTime.now();
            Bookfavorites favorites=bookfavoritesService.getOne(queryWrapper);
            if(favorites==null){
                bookfavorites.setCreateDT(localDateTime);
                bookfavoritesService.save(bookfavorites);
                restResult = new BaseException(CodeEntity.INSERT_EXCEPTION.code, CodeEntity.INSERT_EXCEPTION.message);
                resultBo = new ResultBo(restResult, new ArrayList<>());
            }else {
                restResult.setMsg("bookfavorites is exits");
                resultBo = new ResultBo(restResult, new ArrayList<>());
            }
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }

    @RequestMapping(value="bookfavorites",produces = "application/json;charset=utf-8", method= RequestMethod.DELETE)
    public @ResponseBody
    ResultBo delete(@RequestBody Bookfavorites bookfavorites) {
        ResultBo resultBo ;
        BaseException  restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        try {
            //search for bookfavorite if not exits
            QueryWrapper<Bookfavorites> queryWrapper=new QueryWrapper<>();
            queryWrapper.lambda().eq(Bookfavorites::getBookId,bookfavorites.getBookId());
            queryWrapper.lambda().eq(Bookfavorites::getUserId,bookfavorites.getUserId());

            Bookfavorites favorites=bookfavoritesService.getOne(queryWrapper);
            if(favorites!=null){
                bookfavoritesService.removeById(favorites.getId());
                restResult = new BaseException(CodeEntity.DELETE_EXCEPTION.code, CodeEntity.DELETE_EXCEPTION.message);
                resultBo = new ResultBo(restResult, new ArrayList<>());
            }else {
                restResult.setMsg("bookfavorites is exits");
                resultBo = new ResultBo(restResult, new ArrayList<>());
            }
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }
}
