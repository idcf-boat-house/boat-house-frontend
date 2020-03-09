<template>

  <!-- ======== @Region: #content ======== -->
  <div id="content" class="p-0">
    <!-- Call to action -->

    <div>
      <ul style="padding: 0px">
        <li v-for="item in foodList" :key="item.Id">
          <el-card :body-style="{ padding: '5px' }" class="food-card">
            <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
            <div style="padding: 14px;">
              <span><span class="name-span">菜品名称：</span>{{item.Name}}</span>
              <span><span class="name-span">菜品价格：</span><strong style="color: red">{{item.Price}}元</strong></span>
              <div class="bottom clearfix">
                <span><span class="name-span">菜品分类：</span>{{item.Category}}</span>
                <br>
                <span><span class="name-span">菜品描述：</span>{{item.Description}}</span>
              </div>
            </div>
          </el-card>
        </li>
      </ul>
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
