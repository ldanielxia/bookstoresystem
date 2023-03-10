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
 * @author lixia
 * @since 2023-01-30
 */
@Getter
@Setter
@TableName("bookimage")
public class Bookimage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("book_ID")
    private Integer bookId;

    @TableField("imageUrl")
    private String imageUrl;

    @TableField("fileName")
    private String fileName;

    @TableField("createDT")
    private LocalDateTime createDT;

    @TableField("isMain")
    private Integer isMain;


}
