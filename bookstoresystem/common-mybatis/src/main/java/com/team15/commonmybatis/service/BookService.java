package com.team15.commonmybatis.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.team15.commonmybatis.model.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team15.commonmybatis.model.BookExtend;
import com.team15.commonmybatis.model.BookFilesModelAndView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lixia
 * @since 2023-01-26
 */
public interface BookService extends IService<Book> {
    List<BookExtend> top10List();
    List<BookExtend> searchBook(@Param(Constants.WRAPPER) Wrapper wrapper);
    List<BookExtend> favoriteBook(@Param(Constants.WRAPPER) Wrapper wrapper);
    List<BookExtend> myUsedBook(@Param(Constants.WRAPPER) Wrapper wrapper);
    List<BookExtend> myOrdersBook(@Param(Constants.WRAPPER) Wrapper wrapper);
    void addBook(BookFilesModelAndView bookFilesModelAndView);
}
