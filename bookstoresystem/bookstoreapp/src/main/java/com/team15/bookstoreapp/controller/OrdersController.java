package com.team15.bookstoreapp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team15.bookstoreapp.exception.BaseException;
import com.team15.bookstoreapp.exception.ResultBo;
import com.team15.bookstoreapp.model.CodeEntity;
import com.team15.commonmybatis.model.Book;
import com.team15.commonmybatis.model.Orders;
import com.team15.commonmybatis.service.BookService;
import com.team15.commonmybatis.service.OrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping(value="orderAPI")
public class OrdersController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    OrdersService ordersService;
    @Autowired
    BookService bookService;

    @RequestMapping(value="orders",produces = "application/json;charset=utf-8", method= RequestMethod.POST)
    public @ResponseBody
    @Transactional
    ResultBo add(@RequestBody Orders order) {
        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.INSERT_EXCEPTION.code, CodeEntity.INSERT_EXCEPTION.message);
        try {
            //check user if exits
            QueryWrapper<Orders> queryWrapper =new QueryWrapper<>();
            queryWrapper.lambda().eq(Orders::getBookId,order.getBookId());
            queryWrapper.lambda().eq(Orders::getSellerId,order.getBuyerId());
            Orders _tempOrder=ordersService.getOne(queryWrapper);
            if(_tempOrder==null) {
                LocalDateTime dateTime=LocalDateTime.now();
                order.setCreateDT(dateTime);
                QueryWrapper<Book> queryBook =new QueryWrapper<>();
                queryBook.lambda().eq(Book::getId,order.getBookId());

                Book book=bookService.getOne(queryBook);
                if(book!=null) {
                    book.setIsSaled(1);
                    bookService.updateById(book);
                    order.setIsAgree(0);
                    order.setIsEnd(0);
                    ordersService.save(order);
                    restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
                }

                resultBo = new ResultBo(restResult, new ArrayList<>());
            }else{
                resultBo = new ResultBo(restResult, new ArrayList<>());
            }
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }
}
