package com.idcf.boathouse;

import org.hamcrest.CustomMatcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * date:2020/4/17 14:04
 * author:xiaokunliu
 * desc: junit4 test case for JdbcUtilsTest
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration()
//@ActiveProfiles("dev")
@SpringBootTest
public class JdbcUtilsTest {

    private JdbcUtils jdbcUtils;

    @Before
    public void setUp() {
        jdbcUtils = new JdbcUtils();
        Assert.assertNotNull(jdbcUtils);
        jdbcUtils.getConnection();
        Assert.assertNotNull(jdbcUtils.getConnection());
    }

    @After
    public void tearDown() throws Exception {
        Connection connection = jdbcUtils.releaseConn();
        Assert.assertTrue(connection.isClosed());
    }

    @Test
    public void testFindModeResult() throws Exception {
        String sql = "select * from FoodCategory where Name=?";
        List<Object> params = new ArrayList<>();
        params.add("菜品11");
        List<Map<String, Object>> list = jdbcUtils.findModeResult(sql, params);
        Assert.assertNotNull(list);
        Assert.assertThat(list, new CustomMatcher<List<Map<String, Object>>>("存在菜品名称不匹配") {
            @Override
            public boolean matches(Object actual) {
                List<Map<String, Object>> result = (List<Map<String, Object>>) actual;
                for (Map<String, Object> map : result) {
                    if (!"菜品11".equals(map.get("Name"))) {
                        return false;
                    }
                }
                return true;
            }
        });
    }
}