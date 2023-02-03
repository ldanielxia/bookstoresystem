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
 * @since 2023-01-30
 */
@Getter
@Setter
@TableName("category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("categoryName")
    private String categoryName;

    @TableField("categoryDescribe")
    private String categoryDescribe;

    @TableField("layer")
    private Integer layer;

    @TableField("sortNumber")
    private Integer sortNumber;

    @TableField("createDT")
    private LocalDateTime createDT;

    @TableField("fatherID")
    private Integer fatherID;


}
