package com.team15.commonmybatis.impl;

import com.team15.commonmybatis.model.Orders;
import com.team15.commonmybatis.mapper.OrdersMapper;
import com.team15.commonmybatis.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author lixia
 * @since 2023-02-18
 */
@Service
public class OrdersImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}
