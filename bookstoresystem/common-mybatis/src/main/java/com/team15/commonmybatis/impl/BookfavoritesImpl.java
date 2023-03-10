package com.team15.commonmybatis.impl;

import com.team15.commonmybatis.model.Bookfavorites;
import com.team15.commonmybatis.mapper.BookfavoritesMapper;
import com.team15.commonmybatis.service.BookfavoritesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author lixia
 * @since 2023-02-05
 */
@Service
public class BookfavoritesImpl extends ServiceImpl<BookfavoritesMapper, Bookfavorites> implements BookfavoritesService {

}
