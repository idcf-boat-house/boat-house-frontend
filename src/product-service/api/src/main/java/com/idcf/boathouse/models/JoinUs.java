package com.idcf.boathouse.models;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author jaylee
 */
@Data
public class JoinUs {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String telephone;
    private String comment;
}
