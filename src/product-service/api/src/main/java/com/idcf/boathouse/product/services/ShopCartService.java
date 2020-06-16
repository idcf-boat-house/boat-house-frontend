package com.idcf.boathouse.product.services;

import com.idcf.boathouse.product.dao.BaseDao;

import com.idcf.boathouse.product.models.ShopCartPost;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yan.wang
 */
@Service
public class ShopCartService {

    public void insertOrUpdateShopCart(ShopCartPost shopCartPost){

        List<Object> params = new ArrayList<Object>();
        String sql = "";
        params.add(shopCartPost.userid+"_"+shopCartPost.foodid);
        params.add(shopCartPost.userid);
        params.add(shopCartPost.foodid);
        params.add(shopCartPost.num);
        params.add(shopCartPost.comment);
        params.add(shopCartPost.num);

        sql = "insert into shop_cart (id, userid, foodid,num, comment) values (?, ?, ?, ?, ?) on duplicate key update num = num + ?" ;
        BaseDao.exec(sql, params);
    }

    public void reduceFoodNum(Integer userId,Integer foodId,Integer num){
        List<Object> params = new ArrayList<Object>();
        String sql = "";
        params.add(num);
        params.add(userId);
        params.add(foodId);

        sql = "update shop_cart set num = num - ? where userid = ? and foodid = ?" ;
        BaseDao.exec(sql, params);

        List<Object> params1 = new ArrayList<Object>();
        params1.add(userId);
        params1.add(foodId);

        sql = "";
        sql="delete from shop_cart where userid = ? and foodId = ? and num<=0";
        BaseDao.exec(sql, params1);
    }

    public void DeleteFoodFromShopCart(Integer userId,Integer foodId){
        String sql = "delete from shop_cart where userid = ? and foodId = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(userId);
        params.add(foodId);
        BaseDao.exec(sql, params);
    }

    public void ClearShopCart(Integer userId){
        String sql = "delete from shop_cart where userid = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(userId);
        BaseDao.exec(sql, params);
    }

    public List<Map<String, Object>> listFoodsInCart(Integer userId){
        StringBuffer sql = new StringBuffer("select t.*,t1.Name, t1.Price from shop_cart t, Food t1 where t.foodid = t1.id");
        if(userId != null){
            sql.append(" and userId = ");
            sql.append(userId.toString());
        }else{
            sql.append(" and userId = -1");
        }
        return BaseDao.exec(sql.toString());
    }

}
