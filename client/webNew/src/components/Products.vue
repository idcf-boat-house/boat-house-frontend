<template>



  <!-- ======== @Region: #content ======== -->
  <form id="choice" name='form' method="POST" action="/">
    <div id="content" class="p-0">

      <!-- Most Popular Products -->
      <div id="features" class="container py-4 py-lg-6">
        <hr class="hr-lg mt-0 mb-3 w-10 mx-auto hr-primary" />
        <h2 class="text-center text-uppercase font-weight-bold my-0">
          最受 欢迎 菜品
        </h2>
        <h5 class="text-center font-weight-light mt-2 mb-0 text-muted">
          Great products, great value!
        </h5>
        <hr class="mb-5 w-50 mx-auto" />
        <!-- Products cards -->
        <div class="card-deck-wrapper">
          <div class="card-deck">
            <!-- Product 1 -->
            <div class="card product-card">
              <!-- Image & price content -->
              <div class="pos-relative">
                <img class="card-img-top img-fluid" src="assets/img/product/boathouse01.png" alt="Card image cap">
              </div>
              <!-- Content -->
              <div class="card-body">
                <h5 class="card-title">
                  蜂蜜烤猪肉
                </h5>

              </div>
              <div class="card-footer">
                <a id="a"  name="vote" class="btn btn-primary btn-vote text-light" value="a"> ❤ 好吃</a>
              </div>
            </div>
            <!-- Product 2 -->
            <div class="card product-card">
              <!-- Image & price content -->
              <div class="pos-relative">
                <img class="card-img-top img-fluid" src="assets/img/product/boathouse02.png" alt="Card image cap">
              </div>
              <!-- Content -->
              <div class="card-body">
                <h5 class="card-title">
                  牛肉haggis
                </h5>
              </div>
              <div class="card-footer">
                <a id="b" name="vote" class="btn btn-primary btn-vote text-light" value="b"> ❤ 好吃</a>
              </div>
            </div>
            <!-- Product 3 -->
            <div class="card product-card">
              <!-- Image & price content -->
              <div class="pos-relative">
                <img class="card-img-top img-fluid" src="assets/img/product/boathouse03.png" alt="Card image cap">
              </div>
              <!-- Content -->
              <div class="card-body">
                <h5 class="card-title">
                  红酒炖羊腿
                </h5>
              </div>
              <div class="card-footer">
                <a id="c" name="vote" class="btn btn-primary btn-vote text-light" value="c"> ❤ 好吃</a>
              </div>
            </div>

          </div>
          <br/>
          <div class="card-deck">
            <!-- Product 4 -->
            <div class="card product-card">
              <!-- Image & price content -->
              <div class="pos-relative">
                <img class="card-img-top img-fluid" src="assets/img/product/boathouse04.png" alt="Card image cap">
              </div>
              <!-- Content -->
              <div class="card-body">
                <h5 class="card-title">
                  烟熏肠
                </h5>

              </div>
              <div class="card-footer">
                <a id="d" name="vote" class="btn btn-primary btn-vote text-light" value="d"> ❤ 好吃</a>
              </div>
            </div>
            <!-- Product 5 -->
            <div class="card product-card">
              <!-- Image & price content -->
              <div class="pos-relative">
                <img class="card-img-top img-fluid" src="assets/img/product/boathouse05.png" alt="Card image cap">
              </div>
              <!-- Content -->
              <div class="card-body">
                <h5 class="card-title">
                  蜂蜜猪肘
                </h5>
              </div>
              <div class="card-footer">
                <a id="e" name="vote" class="btn btn-primary btn-vote text-light" value="e"> ❤ 好吃</a>
              </div>
            </div>
            <!-- Product 6 -->
            <div class="card product-card">
              <!-- Image & price content -->
              <div class="pos-relative">
                <img class="card-img-top img-fluid" src="assets/img/product/boathouse06.png" alt="Card image cap">
              </div>
              <!-- Content -->
              <div class="card-body">
                <h5 class="card-title">
                  牛肉土豆泥
                </h5>
              </div>
              <div class="card-footer">
                <a id="f"  name="vote" class="btn btn-primary btn-vote text-light" value="f" btn-vote> ❤ 好吃</a>
              </div>
            </div>

          </div>

        </div>
      </div>
      <!-- Call to action -->

    </div>
  </form>


</template>

<script>
  export default {
    name: 'Products',
    data () {
      return {

      }
    },
    created: function () {



    },
    methods: {

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
