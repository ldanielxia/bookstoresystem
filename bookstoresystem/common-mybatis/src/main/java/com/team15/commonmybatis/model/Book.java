package com.team15.commonmybatis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lixia
 * @since 2023-01-30
 */
@Getter
@Setter
@TableName("book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("ISBN")
    private String isbn;

    @TableField("bookTitle")
    private String bookTitle;

    @TableField("author")
    private String author;

    @TableField("editor")
    private String editor;

    @TableField("publishYear")
    private String publishYear;

    @TableField("category_ID")
    private Integer categoryId;

    @TableField("price")
    private BigDecimal price;

    @TableField("salePrice")
    private BigDecimal salePrice;

    @TableField("remark")
    private String remark;

    @TableField("readingNotes")
    private String readingNotes;

    @TableField("user_ID")
    private Integer userId;

    @TableField("ceateDT")
    private LocalDateTime ceateDT;

    @TableField("isSaled")
    private Integer isSaled;

    @TableField(exist=false)
    private String categoryName;

    @TableField(exist=false)
    private String categoryFatherId;

}
