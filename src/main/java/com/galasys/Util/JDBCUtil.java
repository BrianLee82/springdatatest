package com.galasys.Util;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;
/*
* JDBC工具类
* */
public class JDBCUtil {


    public static Connection getConnection() throws Exception
    {

//        String url ="jdbc:mysql:///springdata";
//        String user="root";
//        String password ="Root@666";
//        String driverClass = "com.mysql.jdbc.Driver";

        //InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
        InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");

        Properties properties = new Properties();
        properties.load(inputStream);

        String url =properties.getProperty("jdbc.url");
        String user=properties.getProperty("jdbc.user");
        String password =properties.getProperty("jdbc.password");
        String driverClass = properties.getProperty("jdbc.driverClass");


        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(url,user,password);

        return connection;
    }


    /*
    * 释放db资源
    * */
    public static void release(ResultSet resultSet, Statement statement,
                               Connection connection){

        if(resultSet !=null){
            try{
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        if(statement!=null){
            try {
                statement.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        if(connection!=null){
            try {
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }




    }

}
