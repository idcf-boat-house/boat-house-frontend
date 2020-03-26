package com.idcf.boathouse.mapper;

import com.idcf.boathouse.models.JoinUs;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class JoinUsMapperTest {

//    @Resource
//    private JoinUsMapper joinUsMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
//        List<JoinUs> userList = joinUsMapper.selectList(null);
//        userList.forEach(System.out::println);
    }
}