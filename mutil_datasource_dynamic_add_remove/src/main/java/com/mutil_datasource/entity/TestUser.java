package com.mutil_datasource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @auther Stiles-JKY
 * @date 2020/9/6-14:26
 */
@Data
@TableName("test_user")
public class TestUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;
    /** 姓名 */
    private String name;
    /** 手机号 */
    private String phone;
    /** 职称职别 */
    private String title;
    /** 邮箱 */
    private String email;
    /** 性别 */
    private String gender;
    /** 出生时间 */
    private Date dateOfBirth;
    /** 1:已删除,0:未删除 */
    private Integer deleted;
    /** 创建时间 */
    private Date sysCreateTime;
    /** 创建人 */
    private String sysCreateUser;
    /** 更新时间 */
    private Date sysUpdateTime;
    /** 更新人 */
    private String sysUpdateUser;
    /** 版本号 */
    private Long recordVersion;

    public TestUser() {
    }

}
