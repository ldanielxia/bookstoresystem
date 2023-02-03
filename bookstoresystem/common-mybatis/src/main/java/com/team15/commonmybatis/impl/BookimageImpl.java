package com.team15.commonmybatis.impl;

import com.team15.commonmybatis.model.Bookimage;
import com.team15.commonmybatis.mapper.BookimageMapper;
import com.team15.commonmybatis.service.BookimageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author lixia
 * @since 2023-01-26
 */
@Service
public class BookimageImpl extends ServiceImpl<BookimageMapper, Bookimage> implements BookimageService {

}
