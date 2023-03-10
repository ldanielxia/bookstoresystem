package com.team15.bookstoreapp.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team15.bookstoreapp.exception.BaseException;
import com.team15.bookstoreapp.exception.ResultBo;
import com.team15.commonmybatis.model.BookFilesModelAndView;
import com.team15.bookstoreapp.model.BookModelAndView;
import com.team15.bookstoreapp.model.CodeEntity;
import com.team15.bookstoreapp.model.SearchBase;
import com.team15.commonmybatis.model.*;
import com.team15.commonmybatis.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    BookimageService bookimageService;
    @Autowired
    UsersService usersService;
    @Autowired
    BookcommunicateService bookcommunicateService;
    @Autowired
    BookfavoritesService bookfavoritesService;
    @Autowired
    OrdersService ordersService;

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


    @RequestMapping(value="favoritebook",produces = "application/json;charset=utf-8", method= RequestMethod.POST)
    public @ResponseBody
    ResultBo favoriteBook(@RequestBody Users users) {
        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        List<BookExtend> list;
        try {
            QueryWrapper<Book> queryWrapper =new QueryWrapper<>();
            queryWrapper.eq("book.user_ID",users.getId());
            list= bookService.favoriteBook(queryWrapper);
            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, list);
            resultBo.setRecordsTotal(list.size());
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }

    @RequestMapping(value="bookdetail/{id}",produces = "application/json;charset=utf-8", method= RequestMethod.GET)
    public @ResponseBody
    ResultBo bookDetail(@PathVariable int id) {
        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        try {
            Book book =bookService.getById(id);
            BookModelAndView bookModelAndView=new BookModelAndView();
            if(book!=null){
                bookModelAndView.setBook(book);
                QueryWrapper<Bookimage> queryWrapper=new QueryWrapper<>();
                queryWrapper.lambda().eq(Bookimage::getBookId,book.getId());
                List<Bookimage> bookimages= bookimageService.list(queryWrapper);
                bookModelAndView.setBookimageList(bookimages);
                Users users=usersService.getById(book.getUserId());
                bookModelAndView.setUser(users);
                List<BookcommunicateExtend> bookcommunicateExtends=bookcommunicateService.bookcommunicateByBookID(id);
                bookModelAndView.setBookcommunicateExtends(bookcommunicateExtends);
                QueryWrapper<Bookfavorites>  queryWrapper1=new QueryWrapper<>();
                queryWrapper1.lambda().eq(Bookfavorites::getBookId,book.getId());
                queryWrapper1.lambda().eq(Bookfavorites::getUserId,users.getId());
                Bookfavorites bookfavorites=bookfavoritesService.getOne(queryWrapper1);
                bookModelAndView.setBookfavorites(bookfavorites);
            }
            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, bookModelAndView);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }

    //search the book by category's id
    @RequestMapping(value="booklistbycategory/{id}",produces = "application/json;charset=utf-8", method= RequestMethod.GET)
    public @ResponseBody
    ResultBo bookListByCategory(@PathVariable int id) {

        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        List<BookExtend> list;
        try {

            QueryWrapper<Book> queryWrapper =new QueryWrapper<>();
            queryWrapper.lambda().eq(Book::getCategoryId,id);

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


    @RequestMapping(value="myusebook",produces = "application/json;charset=utf-8", method= RequestMethod.POST)
    public @ResponseBody
    ResultBo myusebook(@RequestBody Users users) {
        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        List<BookExtend> list;
        try {
            QueryWrapper<Book> queryWrapper =new QueryWrapper<>();
            queryWrapper.eq("book.user_ID",users.getId());
            list= bookService.myUsedBook(queryWrapper);
            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, list);
            resultBo.setRecordsTotal(list.size());
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }

    @RequestMapping(value="updateUsebook",produces = "application/json;charset=utf-8", method= RequestMethod.POST)
    public @ResponseBody
    ResultBo updateUsebook(@RequestBody Book book) {
        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        List<BookExtend> list;
        try {
            if(book.getId()+1!=0) {
                bookService.updateById(book);
            }else{
                book.setId(null);
                LocalDateTime dateTime = LocalDateTime.now();
                book.setCeateDT(dateTime);
                bookService.save(book);
            }
            restResult = new BaseException(CodeEntity.UPDATE_EXCEPTION.code, CodeEntity.UPDATE_EXCEPTION.message);
            resultBo = new ResultBo(restResult, new ArrayList<>());

        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }

    @RequestMapping(value="newusebookwithimg",produces = "application/json;charset=utf-8", method= RequestMethod.POST)
    public @ResponseBody
    ResultBo newUsebook(@RequestParam("data") String data, @RequestParam("fileList") MultipartFile[] fileList, HttpServletRequest request) {
        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        try {
              Book book =  JSON.parseObject(data,Book.class);

            BookFilesModelAndView bookModelAndView=new BookFilesModelAndView();
            bookModelAndView.setBook(book);
            List<MultipartFile> list = new ArrayList<>();
            if (fileList.length > 0) {
                for (MultipartFile file : fileList) {
                    list.add(file);
                }
            }
            bookModelAndView.setFileList(list);
            bookService.addBook(bookModelAndView);

            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, new ArrayList<>());

        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }
    @RequestMapping(value="updateusebookwithimg",produces = "application/json;charset=utf-8", method= RequestMethod.POST)
    public @ResponseBody
    ResultBo updateUsebookWithImg(@RequestParam("data") String data, @RequestParam("fileList") MultipartFile[] fileList,@RequestParam("pictureIds") Integer[] pictureIds, HttpServletRequest request) {
        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        try {
            Book book =  JSON.parseObject(data,Book.class);

            BookFilesModelAndView bookModelAndView=new BookFilesModelAndView();
            bookModelAndView.setBook(book);
            List<MultipartFile> list = new ArrayList<>();
            if (fileList.length > 0) {
                for (MultipartFile file : fileList) {
                    list.add(file);
                }
            }
            bookModelAndView.setFileList(list);
            bookModelAndView.setPictureIds(pictureIds);

            bookService.addBook(bookModelAndView);

            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, new ArrayList<>());

        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }

    @RequestMapping(value="updateusebook",produces = "application/json;charset=utf-8", method= RequestMethod.POST)
    public @ResponseBody
    ResultBo updateUsebook(@RequestParam("data") String data, HttpServletRequest request) {
        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        try {
            Book book =  JSON.parseObject(data,Book.class);

           bookService.updateById(book);

            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, new ArrayList<>());

        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }


    @RequestMapping(value="orderbook",produces = "application/json;charset=utf-8", method= RequestMethod.POST)
    public @ResponseBody
    ResultBo myOrdersBook(@RequestBody Users users) {
        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        List<BookExtend> list;
        try {
            QueryWrapper<Book> queryWrapper =new QueryWrapper<>();
            queryWrapper.eq("orders.buyer_ID",users.getId());
//            queryWrapper.eq("book.isSaled","1");
            list= bookService.myOrdersBook(queryWrapper);
            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, list);
            resultBo.setRecordsTotal(list.size());
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }

    @RequestMapping(value="salebook",produces = "application/json;charset=utf-8", method= RequestMethod.POST)
    public @ResponseBody
    ResultBo salebook(@RequestBody Users users) {
        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        List<BookExtend> list;
        try {
            QueryWrapper<Book> queryWrapper =new QueryWrapper<>();
            queryWrapper.eq("orders.seller_ID",users.getId());
//            queryWrapper.eq("book.isSaled","1");
            list= bookService.myOrdersBook(queryWrapper);
            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, list);
            resultBo.setRecordsTotal(list.size());
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }

    @RequestMapping(value="bookorderdetail/{id}",produces = "application/json;charset=utf-8", method= RequestMethod.GET)
    public @ResponseBody
    ResultBo bookOrderDetail(@PathVariable int id) {
        ResultBo resultBo ;
        BaseException restResult=new BaseException(CodeEntity.FALSE_EXCEPTION.code, CodeEntity.FALSE_EXCEPTION.message);
        try {
            Book book =bookService.getById(id);
            BookModelAndView bookModelAndView=new BookModelAndView();
            if(book!=null){
                bookModelAndView.setBook(book);
                QueryWrapper<Bookimage> queryWrapper=new QueryWrapper<>();
                queryWrapper.lambda().eq(Bookimage::getBookId,book.getId());
                List<Bookimage> bookimages= bookimageService.list(queryWrapper);
                bookModelAndView.setBookimageList(bookimages);
                Users users=usersService.getById(book.getUserId());
                bookModelAndView.setUser(users);
                List<BookcommunicateExtend> bookcommunicateExtends=bookcommunicateService.bookcommunicateByBookID(id);
                bookModelAndView.setBookcommunicateExtends(bookcommunicateExtends);
                QueryWrapper<Bookfavorites>  queryWrapper1=new QueryWrapper<>();
                queryWrapper1.lambda().eq(Bookfavorites::getBookId,book.getId());
                queryWrapper1.lambda().eq(Bookfavorites::getUserId,users.getId());
                Bookfavorites bookfavorites=bookfavoritesService.getOne(queryWrapper1);
                bookModelAndView.setBookfavorites(bookfavorites);
                //order
                QueryWrapper<Orders> queryWrapper2=new QueryWrapper<>();
                queryWrapper2.eq("book_Id",book.getId());
                Orders orders=ordersService.getOne(queryWrapper2);
                if(orders!=null)
                    bookModelAndView.setOrders(orders);
            }
            restResult = new BaseException(CodeEntity.SUCCESS_EXCEPTION.code, CodeEntity.SUCCESS_EXCEPTION.message);
            resultBo = new ResultBo(restResult, bookModelAndView);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultBo = new ResultBo(restResult, new ArrayList<>());
        }
        return resultBo;
    }
}
