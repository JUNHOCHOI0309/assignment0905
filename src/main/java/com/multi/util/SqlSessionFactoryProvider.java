package com.multi.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class SqlSessionFactoryProvider {
    private static final SqlSessionFactory Factory;
    static {
        try(Reader reader = Resources.getResourceAsReader("mybatis-config.xml")){
            Factory = new SqlSessionFactoryBuilder().build(reader);
        }catch (Exception e){
            throw new RuntimeException("Failed to init SqlSessionFactory", e);
        }
    }
    private SqlSessionFactoryProvider(){}
    public static SqlSessionFactory getFactory(){
        return Factory;
    }
}
