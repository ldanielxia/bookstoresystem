package com.team15.commonmybatis.mapper;

import com.team15.commonmybatis.model.Bookcommunicate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team15.commonmybatis.model.BookcommunicateExtend;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

/**
 *
 * @author lixia
 * @since 2023-02-03
 */
@Mapper
public interface BookcommunicateMapper extends BaseMapper<Bookcommunicate> {
     List<BookcommunicateExtend> bookcommunicateByBookID(int ID);
}
