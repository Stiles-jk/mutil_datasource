package com.mutil_datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class MutilDatasourceDynamicAddRemoveApplication {

    public static void main(String[] args) {
        SpringApplication.run(MutilDatasourceDynamicAddRemoveApplication.class, args);
    }

}
