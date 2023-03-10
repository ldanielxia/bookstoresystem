package com.team15.commonmybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.team15.commonmybatis.model.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team15.commonmybatis.model.BookExtend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lixia
 * @since 2023-01-26
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {

    List<BookExtend> top10List();
    List<BookExtend> searchBook(@Param(Constants.WRAPPER) Wrapper wrapper);
    List<BookExtend> favoriteBook(@Param(Constants.WRAPPER) Wrapper wrapper);
    //my used book
    List<BookExtend> myUsedBook(@Param(Constants.WRAPPER) Wrapper wrapper);

    List<BookExtend> myOrdersBook(@Param(Constants.WRAPPER) Wrapper wrapper);
}
