package com.team15.bookstoreapp.controller;

import com.team15.bookstoreapp.exception.BaseException;
import com.team15.bookstoreapp.exception.ResultBo;
import com.team15.bookstoreapp.model.CodeEntity;
import com.team15.commonmybatis.model.Bookcommunicate;
import com.team15.commonmybatis.model.BookcommunicateExtend;
import com.team15.commonmybatis.model.Users;
import com.team15.commonmybatis.service.BookcommunicateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixia
 * @since 2023-02-03
 */
@RestController
@RequestMapping("commonmybatisAPI")
public class BookcommunicateController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    BookcommunicateService bookcommunicateService;

    @RequestMapping(value="bookcommunicate",produces = "application/json;charset=utf-8", method= RequestMethod.POST)
    public @ResponseBody
    ResultBo add(@RequestBody Bookcommunicate bookcommunicate) {
        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.INSERT_EXCEPTION.code, CodeEntity.INSERT_EXCEPTION.message);
        try {

            bookcommunicateService.save(bookcommunicate);
            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }


    @RequestMapping(value="bookcommunicateByBookID/{ID}",produces = "application/json;charset=utf-8", method= RequestMethod.GET)
    public @ResponseBody
    ResultBo bookcommunicateByBookID(@PathVariable int ID) {
        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.INSERT_EXCEPTION.code, CodeEntity.INSERT_EXCEPTION.message);
        try {

            List<BookcommunicateExtend> list=bookcommunicateService.bookcommunicateByBookID(ID);
            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, list);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }
}
