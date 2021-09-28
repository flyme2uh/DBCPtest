package com.bylz.dbcptest.controller;

import com.bylz.dbcptest.service.TestService;
import com.bylz.dbcptest.vo.Qcode_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: TestController
 * @Description:
 * @Author chenzhipeng
 * @Date 2021/9/18
 * @Version 1.0
 */

@RestController
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping("/beecp")
    @ResponseBody
    public List<Qcode_user> beeCP() {
        return testService.beeCPRead();
    }

    @GetMapping("/dbcp2")
    @ResponseBody
    public List<Qcode_user> dbcp2() {
        return testService.dbcp2Read();
    }

    @GetMapping("/tomcat")
    @ResponseBody
    public List<Qcode_user> tomcat() {
        return testService.tomcatRead();
    }

    @GetMapping("/druid")
    @ResponseBody
    public List<Qcode_user> druid() {
        return testService.druidRead();
    }

    @GetMapping("/hikariCP")
    @ResponseBody
    public List<Qcode_user> hikariCP() {
        return testService.hikariRead();
    }
}
