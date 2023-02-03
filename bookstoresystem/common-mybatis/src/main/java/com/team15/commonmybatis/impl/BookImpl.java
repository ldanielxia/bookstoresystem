package com.team15.commonmybatis.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team15.commonmybatis.model.Book;
import com.team15.commonmybatis.mapper.BookMapper;
import com.team15.commonmybatis.model.BookExtend;
import com.team15.commonmybatis.model.Users;
import com.team15.commonmybatis.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lixia
 * @since 2023-01-26
 */
@Service
public class BookImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Autowired
    BookMapper bookMapper;
    @Override
    public List<BookExtend> top10List() {
        List<BookExtend> list  = bookMapper.top10List();
        return list;
    }

    @Override
    public List<BookExtend> searchBook(Wrapper wrapper) {
        return bookMapper.searchBook(wrapper);
    }
}
