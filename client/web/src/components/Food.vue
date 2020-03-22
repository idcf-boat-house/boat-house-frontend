<template>

  <!-- ======== @Region: #content ======== -->
  <div id="content" class="p-0">
    <!-- Call to action -->

    <div class="card-deck-wrapper"  >
       <div class="card-deck" v-for="item in foodList" :key="item.Id"> 
       
          <!-- <el-card :body-style="{ padding: '5px' }" class="food-card">
            <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
            <div style="padding: 14px;">
              <span><button type="button" class="btn btn-default" style=""  v-on:click="reverseMessage">+</button></span>
              <span><span class="name-span">菜品名称：</span>{{item.Name}}</span>
              <span><span class="name-span">菜品价格：</span><strong style="color: red">{{item.Price}}元</strong></span>
              <div class="bottom clearfix">
                <span><span class="name-span">菜品分类：</span>{{item.Category}}</span>
                <br>
                <span><span class="name-span">菜品描述：</span>{{item.Description}}</span>
              </div>
            </div>
          </el-card> -->
             <div class="card">
              <!-- Image & price content -->
              <div class="pos-relative width:200px; height:auto;">
                <img class="img-responsive" src="assets/img/product/boathouse06.png" alt="Card image cap">
              </div>
              <!-- Content -->
              <div class="card-body">
                <h5 class="card-title">
                  {{item.Name}}
                </h5>
              </div>
              <div class="card-footer">
                <a id="f"  name="vote" class="btn btn-primary text-light">{{item.Price}}元</a>
                <button type="button" class="btn btn-default" style=""  v-on:click="AddFoodToCart(item)">+</button>
              </div>         
            </div> 
    </div>
  </div>

</template>

<script>
export default {
  name: 'Food',
  data () {
    return {
      foodList: [],
      foodCategory: []
    }
  },
  mounted: function () {
    let _this = this
    this.axios.get('api/foodcategories').then(function (result) {
      if (result.status === 200) {
        _this.foodCategory = result.data
        _this.GetFoodList()
      }
    })
  },
  methods: {
    GetFoodList: function () {  
     let _this = this
      this.axios.get('api/foods').then(function (result) {
        if (result.status === 200) {
          _this.foodList = result.data.data
          _this.foodList.map(item => {
            let categoryItem = _this.foodCategory.find(i => i.Id === item.CategoryId)
            item.Category = categoryItem.Name
          })
          console.log(_this.foodList)
        }
      })
    }, 
    AddFoodToCart: function (e) {
      let _this = this 
      var userId = 1;// _this.getCookie("userId");   
      let postData = {
            userid: 1,
            foodid: parseInt(e.Id),
            num: 1,
            comment: ""
         };

       // http://127.0.0.1:8081/api/v1.0/BoatHouse/ShopCart 
      let url = 'api/food/shopcart'; 
      this.axios.post(url, postData).then(function (result) {
        console.log(result)
        if (result.status === 200) {
          alert("添加购物车成功");  
        }
      },
       function(res){          
             console.log(res.status); 
             alert("添加购物车失败");
       })
    }

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.food-card {
  text-align: center;
  font-size: 12px;
}
ul li {
  list-style-type: none;
}
.name-span {
  font-size: 10px;
  color: gray;
}
</style>
