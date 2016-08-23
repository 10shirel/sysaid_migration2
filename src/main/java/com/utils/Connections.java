package com.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


/**
 * Created by Shirel Azulay on 23/08/2016.
 */
public class Connections {

    //Source
    public static final String SOURCE_DRIVER_CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String SOURCE_URL = "jdbc:mysql://10.14.1.167:3306/ingles";
    public static final String SOURCE_USER_NAME = "root";
    public static final String SOURCE_PASSWORD = "Password1";

    //Target
    public static final String TARGET_DRIVER_CLASS_NAME = "oracle.jdbc.OracleDriver";
    public static final String TARGET_URL = "jdbc:oracle:thin:@//10.14.1.19:1521/XE";
    public static final String TARGET_USER_NAME = "sysaid2";
    public static final String TARGET_PASSWORD = "changeit";


    /**
     * Return source / target connections DB
     * @return
     */
    @NotNull
    public static DriverManagerDataSource[] getDBConnections() {
        DriverManagerDataSource sourceTargetConnections [] = new DriverManagerDataSource[2];

        //Source DB
        DriverManagerDataSource sourceDs = new DriverManagerDataSource();
        sourceDs.setDriverClassName(SOURCE_DRIVER_CLASS_NAME);
        sourceDs.setUrl(SOURCE_URL);
        sourceDs.setUsername(SOURCE_USER_NAME);
        sourceDs.setPassword(SOURCE_PASSWORD);


        //Target DB
        DriverManagerDataSource targetDs = new DriverManagerDataSource();
        targetDs.setDriverClassName(TARGET_DRIVER_CLASS_NAME);
        targetDs.setUrl(TARGET_URL);
        targetDs.setUsername(TARGET_USER_NAME);
        targetDs.setPassword(TARGET_PASSWORD);

        sourceTargetConnections[0] = sourceDs;
        sourceTargetConnections[1] = targetDs;

        return sourceTargetConnections;
    }
}
