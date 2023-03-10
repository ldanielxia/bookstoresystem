package com.team15.commonmybatis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lixia
 * @since 2023-02-18
 */
@Getter
@Setter
@TableName("orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("book_ID")
    private Integer bookId;

    @TableField("remark")
    private String remark;

    @TableField("seller_ID")
    private Integer sellerId;

    @TableField("buyer_ID")
    private Integer buyerId;

    @TableField("transDate")
     private LocalDateTime transDate;

    @TableField("contactEmail")
    private String contactEmail;

    @TableField("meetingAddress")
    private String meetingAddress;

    @TableField("createDT")
    private LocalDateTime createDT;

    @TableField("rating")
    private String rating;

    @TableField("isEnd")
    private Integer isEnd;

    @TableField("isAgree")
    private Integer isAgree;


}
