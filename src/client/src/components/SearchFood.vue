<template>
  
  <!-- ======== @Region: #content ======== -->
  <div id="content" class="p-0">
    <div id="features" class="container py-4 py-lg-6">
      <hr class="hr-lg mt-0 mb-3 w-10 mx-auto hr-primary" />
      <h2 class="text-center text-uppercase font-weight-bold my-0">
        点菜 也是 享受
      </h2>
      <h5 class="text-center font-weight-light mt-2 mb-0 text-muted">
        Great food, great people!
      </h5>
      <hr class="mb-5 w-50 mx-auto" />

      <div class="card-deck-wrapper"  >
        <div class="card-deck">
          <div class="card product-card"  v-for="item in foodList" :key="item.Id">
            <!-- Image & price content -->
            <div class="pos-relative">
              <img class="card-img-top img-fluid"  src="assets/img/product/boathouse06.png" alt="Card image cap">
            </div>
            <!-- Content -->
            <div class="card-body">
              <h5 class="card-title">
                {{item.Name}}
              </h5>
            </div>
            <div class="card-footer">
              <div class="row">
                <div class="col-md-4">{{item.Price}}元</div>
                <div class="col-md-4 col-md-offset-6">
                  <a id="a"  name="vote" class="btn btn-primary text-light"  @click="AddFoodToCart(item)">加入购物车</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: 'SearchFood',
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
        _this.GetFoodLikeList(_this.$route.params.foodName);
      }
    })
  },
  methods: {

    GetFoodLikeList: function (searchName) {
      console.log(searchName);
      let _this = this

      this.axios.get('api/searchfood',{params:{name: searchName}}).then(function (result) {
        if (result.status === 200) {
          _this.foodList = result.data
          _this.foodList.map(item => {
            let categoryItem = _this.foodCategory.find(i => i.Id === item.CategoryId)
            item.Category = categoryItem.Name
          })
          console.log(_this.foodList)
        }
      })
    },

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
