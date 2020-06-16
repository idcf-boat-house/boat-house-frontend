package com.idcf.boathouse.product.services;

import com.idcf.boathouse.product.models.IntroPageFront;
import com.idcf.boathouse.product.models.IntroPageValues;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;


@RunWith(SpringJUnit4ClassRunner.class)
@Profile("dev")
@SpringBootTest
public class IntroPageServiceTest {

    @Autowired
    private IntroPageService introPageService;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return null;
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void inserIntroPage() throws Exception {
        IntroPageFront introPageFront=new IntroPageFront();
        introPageFront.page_api_url="http://www.baidu.com";
        introPageFront.page_title="网页Title";
        introPageFront.page_id="test";
        introPageFront.page_values=new IntroPageValues();
        introPageFront.page_values.text="test";
        introPageFront.page_values.image="http://www.taobao.com";
        introPageService.inserIntroPage(introPageFront);
    }

    // 单元测试通过
    @Test
    public void deleteIntroPage() throws Exception {
        introPageService.deleteIntroPage("test");
    }

    @Test
    public void updateIntroPage() throws Exception{
        IntroPageFront introPageFront=new IntroPageFront();
        introPageFront.page_api_url="http://www.baidu.com";
        introPageFront.page_title="网页Title1";
        introPageFront.page_id="test";
        introPageFront.page_values=new IntroPageValues();
        introPageFront.page_values.text="test1";
        introPageFront.page_values.image="http://www.taobao.com1";
        introPageService.updateIntroPage(introPageFront);
    }

    @Test
    public void getIntroPage() {
        IntroPageFront introPageFront = introPageService.getIntroPage("test");
        Assert.assertEquals("test", introPageFront.page_id);
    }


}