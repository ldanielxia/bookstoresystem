package com.team15.commonmybatis.impl;

import com.team15.commonmybatis.model.Category;
import com.team15.commonmybatis.mapper.CategoryMapper;
import com.team15.commonmybatis.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author lixia
 * @since 2023-01-30
 */
@Service
public class CategoryImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
