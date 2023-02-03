package com.team15.bookstoreapp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team15.bookstoreapp.exception.BaseException;
import com.team15.bookstoreapp.exception.ResultBo;
import com.team15.bookstoreapp.model.CodeEntity;
import com.team15.bookstoreapp.tools.secrect.MD5Util;
import com.team15.commonmybatis.model.Users;
import com.team15.commonmybatis.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="usersAPI")
public class UsersController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
     UsersService usersService;


    @RequestMapping(value="users",produces = "application/json;charset=utf-8", method= RequestMethod.GET)
    public @ResponseBody
    ResultBo users() {

        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        List<Users> list;
        try {
            QueryWrapper<Users> queryWrapper =new QueryWrapper<>();
            queryWrapper.eq("isDeleted","0");
            list= usersService.list(queryWrapper);
            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, list);
            resultBo.setRecordsTotal(list.size());
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }

    @RequestMapping(value="users",produces = "application/json;charset=utf-8", method= RequestMethod.POST)
    public @ResponseBody
    ResultBo add(@RequestBody Users users) {
        ResultBo resultBo ;
        BaseException  restResult=new BaseException(CodeEntity.INSERT_EXCEPTION.code, CodeEntity.INSERT_EXCEPTION.message);
        try {
            users.setIsDeleted(0);
            usersService.save(users);
            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }

    @RequestMapping(value="users",produces = "application/json;charset=utf-8", method= RequestMethod.PUT)
    public @ResponseBody
    ResultBo update(@RequestBody Users sysUsr) {
        ResultBo resultBo ;
        BaseException   restResult=new BaseException(CodeEntity.UPDATE_EXCEPTION.code, CodeEntity.UPDATE_EXCEPTION.message);
        try {
            usersService.updateById(sysUsr);
            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }


    @RequestMapping(value="users/{id}",produces = "application/json;charset=utf-8", method= RequestMethod.DELETE)
    public @ResponseBody
    ResultBo deleteByID(@PathVariable int id) {
        ResultBo resultBo ;
        BaseException  restResult=new BaseException(CodeEntity.DELETE_EXCEPTION.code, CodeEntity.DELETE_EXCEPTION.message);
        Users obj;
        try {
            obj=usersService.getById(id);
            obj.setIsDeleted(1);
            usersService.updateById(obj);
            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }


    @RequestMapping(value="usr/login",produces = "application/json;charset=utf-8", method= RequestMethod.POST)
    public @ResponseBody
    ResultBo checkUser(@RequestBody  Users users) {
        ResultBo resultBo ;
        BaseException  restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        try{

            String newPwd= MD5Util.stringMD5(users.getPassword());
            users.setPassword(newPwd);
            Users _tempPerson=usersService.exitsUser(users);
            if(_tempPerson!=null){

                restResult=new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
                resultBo = new ResultBo(restResult, _tempPerson);
            }else{
                resultBo = new ResultBo(restResult, new ArrayList<>());
            }
        }catch (Exception ex){
            logger.debug(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }

    @RequestMapping(value="resetPwd",produces = "application/json;charset=utf-8", method= RequestMethod.PUT)
    public @ResponseBody
    ResultBo resetPwd(@RequestBody  Users users) {
        ResultBo resultBo ;
        BaseException  restResult=new BaseException(CodeEntity.DELETE_EXCEPTION.code, CodeEntity.DELETE_EXCEPTION.message);
        resultBo = new ResultBo(restResult, new ArrayList<>());
        try {
            if(!(users.getEmail().trim().equals("")&&users.getPassword().trim().equals("")) ){

                users.setPassword(MD5Util.stringMD5(users.getPassword()));
              boolean flag=usersService.updatePassword(users);

                if(flag){
                    restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
                    resultBo = new ResultBo(restResult, new ArrayList<>());
             }
            }
        }catch (Exception ex){
            logger.error(ex.getMessage());
            restResult.setMsg(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }

}
