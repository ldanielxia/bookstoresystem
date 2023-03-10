package com.team15.commonmybatis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lixia
 * @since 2023-01-23
 */
@Getter
@Setter
@TableName("users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("email")
    private String email;

    @TableField("password")
    private String password;

    @TableField("studentID")
    private String studentID;

    @TableField("firstName")
    private String firstName;

    @TableField("lastName")
    private String lastName;

    @TableField("gender")
    private String gender;

    @TableField("dateOfBirth")
    private LocalDate dateOfBirth;

    @TableField("address")
    private String address;

    @TableField("phone")
    private String phone;

    @TableField("areaId")
    private String areaId;

    @TableField("area")
    private String area;

    @TableField("schoolId")
    private String schoolId;

    @TableField("school")
    private String school;

    @TableField("programeId")
    private String programeId;

    @TableField("programe")
    private String programe;



    @TableField("createDT")
    private LocalDateTime createDT;


    @TableField("isDeleted")
    private Integer isDeleted;
}
