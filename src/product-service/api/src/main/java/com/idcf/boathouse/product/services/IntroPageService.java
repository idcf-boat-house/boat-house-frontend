package com.idcf.boathouse.product.services;

import com.idcf.boathouse.product.mapper.IntroPageMapper;
import com.idcf.boathouse.product.models.IntroPage;
import com.idcf.boathouse.product.models.IntroPageFront;
import com.idcf.boathouse.product.models.IntroPageValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IntroPageService {
    @Autowired
    private IntroPageMapper introPageMapper;

    /**
     * 插入或更新船坞故事
     * @param introPageFront
     */
    public void inserIntroPage(IntroPageFront introPageFront){

       List<IntroPage> lstIntroPage= introPageMapper.findIntroPage(introPageFront.page_id);
       if(lstIntroPage!=null&&lstIntroPage.size()>0){
           IntroPage introPage=lstIntroPage.get(0);
           introPage.setPageTitle(introPageFront.page_title);
           introPage.setPageApiUrl(introPageFront.page_api_url);
           if(introPageFront.page_values!=null) {
               introPage.setImage(introPageFront.page_values.image);
               introPage.setText(introPageFront.page_values.text);
           }
           introPage.setDeleted(false);
           introPage.setUpdateTime(new Date());
           introPageMapper.updateById(introPage);
       }
       else {
           IntroPage introPage=new IntroPage();
           introPage.setPageId(introPageFront.page_id);
           introPage.setPageTitle(introPageFront.page_title);
           introPage.setPageApiUrl(introPageFront.page_api_url);
           if(introPageFront.page_values!=null) {
               introPage.setImage(introPageFront.page_values.image);
               introPage.setText(introPageFront.page_values.text);
           }
           introPage.setDeleted(false);
           introPage.setUpdateTime(new Date());
           introPageMapper.insert(introPage);
       }
    }

    /**
     * 删除船坞故事
     * @param page_id
     */
    public void deleteIntroPage(String page_id) throws Exception{
        List<IntroPage> lstIntroPage= introPageMapper.findIntroPage(page_id);
        if(lstIntroPage!=null&&lstIntroPage.size()>0){
            IntroPage introPage=lstIntroPage.get(0);
            introPage.setDeleted(true);
            introPageMapper.updateById(introPage);
        }
        else {
            throw new Exception("未能根据page_id"+page_id+"找到对应的故事数据");
        }
    }

    public void updateIntroPage(IntroPageFront introPageFront) throws Exception{

        List<IntroPage> lstIntroPage= introPageMapper.findIntroPage(introPageFront.page_id);
        if(lstIntroPage!=null&&lstIntroPage.size()>0){
            IntroPage introPage=lstIntroPage.get(0);
            introPage.setPageTitle(introPageFront.page_title);
            introPage.setPageApiUrl(introPageFront.page_api_url);
            if(introPageFront.page_values!=null) {
                introPage.setImage(introPageFront.page_values.image);
                introPage.setText(introPageFront.page_values.text);
            }
            introPage.setDeleted(false);
            introPage.setUpdateTime(new Date());
            introPageMapper.updateById(introPage);
        }
        else {
            throw new Exception("未能根据page_id"+introPageFront.page_id+"找到对应的故事数据");
        }
    }

    /**
     * 获得船坞故事数据
     * @param page_id
     * @return
     */
    public IntroPageFront getIntroPage(String page_id){
        List<IntroPage> lstIntroPage= introPageMapper.findIntroPage(page_id);
        if(lstIntroPage!=null&&lstIntroPage.size()>0&&!lstIntroPage.get(0).isDeleted()) {
            IntroPage introPage = lstIntroPage.get(0);
            IntroPageFront introPageFront=new IntroPageFront();
            introPageFront.page_id=introPage.getPageId();
            introPageFront.page_title=introPage.getPageTitle();
            introPageFront.page_api_url=introPage.getPageApiUrl();
            introPageFront.page_values=new IntroPageValues();
            introPageFront.page_values.text=introPage.getText();
            introPageFront.page_values.image=introPage.getImage();
            return introPageFront;
        }
        else {
            return null;
        }
    }

}
