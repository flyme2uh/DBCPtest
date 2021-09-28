package com.bylz.dbcptest.service;

import cn.beecp.BeeDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bylz.dbcptest.mapper.UserMapper;
import com.bylz.dbcptest.vo.Qcode_user;
import com.mysql.jdbc.TimeUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: TestService
 * @Description: A serviceclass for testing connection pool
 * @Author chenzhipeng
 * @Date 2021/9/18
 * @Version 1.0
 */
@Service
public class TestService {
    @Autowired
    private UserMapper userMapper;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    private Lock lock = new ReentrantLock();

    @DS("beeCP")
    public List<Qcode_user> beeCPRead() {
        DynamicRoutingDataSource drds = (DynamicRoutingDataSource) dataSource;
        BeeDataSource beeDataSource = (BeeDataSource) drds.determineDataSource();
        beeDataSource.setMaxActive(100);
        List<Qcode_user> qcode_users = selectList();
        try {
            beeDataSource.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qcode_users;
    }

    @DS("dbcp2")
    public List<Qcode_user> dbcp2Read() {
        DynamicRoutingDataSource drds = (DynamicRoutingDataSource) dataSource;
        BasicDataSource basicDataSource = (BasicDataSource) drds.determineDataSource();
        basicDataSource.setMaxTotal(100);
        List<Qcode_user> qcode_users = selectList();
        try {
            basicDataSource.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qcode_users;
    }

    @DS("tomcat")
    public List<Qcode_user> tomcatRead() {
        DynamicRoutingDataSource drds = (DynamicRoutingDataSource) dataSource;
        org.apache.tomcat.jdbc.pool.DataSource tomcatDS = (org.apache.tomcat.jdbc.pool.DataSource) drds.determineDataSource();
        tomcatDS.setMaxActive(100);
        List<Qcode_user> qcode_users = selectList();
        try {
            tomcatDS.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qcode_users;
    }

    @DS("druid")
    public List<Qcode_user> druidRead() {
        DynamicRoutingDataSource drds = (DynamicRoutingDataSource) dataSource;
        DruidDataSource druidDataSource = (DruidDataSource) drds.determineDataSource();
        druidDataSource.setMaxActive(100);
        /*druidDataSource.setRemoveAbandoned(true);
        druidDataSource.setRemoveAbandonedTimeout(10);
        druidDataSource.setLogAbandoned(true);*/
        List<Qcode_user> qcode_users = selectList();
        try {
            druidDataSource.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qcode_users;
    }

    @DS("hikariCP")
    public List<Qcode_user> hikariRead() {
        DynamicRoutingDataSource drds = (DynamicRoutingDataSource) dataSource;
        HikariDataSource hikariDataSource = (HikariDataSource) drds.determineDataSource();
        hikariDataSource.setMaximumPoolSize(100);
        List<Qcode_user> qcode_users = selectList();
        try {
            hikariDataSource.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qcode_users;
    }

    private Connection printConnection() {
        try {
            Connection connection = dataSource.getConnection();
            System.out.println(Thread.currentThread().getName() + " get connection " + connection);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Qcode_user> selectList() {
        return userMapper.selectList(
                new QueryWrapper<>()
        );
    }
}
