package com.team15.commonmybatis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lixia
 * @since 2023-02-05
 */
@Getter
@Setter
@TableName("bookfavorites")
public class Bookfavorites implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("Book_ID")
    private Integer bookId;

    @TableField("User_ID")
    private Integer userId;

    @TableField("createDT")
    private LocalDateTime createDT;


}
