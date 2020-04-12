#### 订单服务
##### 管理后台查询订单详细接口

```text
## curl 请求
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/api/v1.0/orders/pending?index=1&size=20'

## 返回结果
[
  {
    "id": 31,
    "userId": 19,
    "orderId": "xxxxxx30",
    "createTime": "2020-03-15T15:18:21.000+0000",
    "updateTime": "2020-03-19T00:07:11.000+0000",
    "payType": 2,
    "totalAmount": 90.23,
    "additionalAmount": 10,
    "orderStatus": 2,
    "reason": null,
    "note": null,
    "itemsList": [
      {
        "id": 30,
        "orderId": "xxxxxx30",
        "foodId": 25,
        "foodName": "xxxxxx30---food_name---25",
        "foodPrice": 23,
        "foodSubTotal": 115,
        "foodNum": 5,
        "foodPicture": ""
      }
    ],
    "orderStatusDesc": "派送中",
    "orderTime": "2020-03-15 23:18:21",
    "updateTimeStr": "2020-03-19 08:07:11"
  },
 ....
]
```


##### 管理后台接单接口

```text
## curl 请求
curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json;charset=UTF-8' -d '[“xxxxxx38”]' 'http://localhost:8080/api/v1.0/orders/confirm'

## 返回响应
200 表示操作成功
400 表示操作失败,同时400的请求体内容为错误信息
```

##### 管理后台拒单接口

```text
## curl请求
curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json;charset=UTF-8' -d '{"order_id": "xxxxxx38", "reason": "订单无效"}' 'http://localhost:8080/api/v1.0/orders/refuse'

## 返回响应
200 表示操作成功
400 表示操作失败,400返回请求体内容为错误信息
```
