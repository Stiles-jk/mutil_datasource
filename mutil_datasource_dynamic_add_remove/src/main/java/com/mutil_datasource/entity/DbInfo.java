package com.mutil_datasource.entity;

import lombok.Data;

/**
 * @auther Stiles-JKY
 * @date 2020/9/6-18:28
 */
@Data
public class DbInfo {
    private String ip;
    private String port;
    private String dbName;
    private String driveClassName;
    private String username;
    private String password;
}
