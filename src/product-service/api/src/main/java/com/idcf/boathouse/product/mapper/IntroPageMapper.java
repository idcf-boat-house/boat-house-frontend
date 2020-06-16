package com.idcf.boathouse.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idcf.boathouse.product.models.IntroPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IntroPageMapper extends BaseMapper<IntroPage> {
    @Select("select * from intropage where page_id= #{pageId}")
    List<IntroPage> findIntroPage(@Param("pageId") String pageId);
}
