<template>


  <form id="choice" name='form' method="POST" action="/">
    <div id="content" class="p-0">

      <!-- Most Popular Products -->
      <div id="features" class="container py-4 py-lg-6">
        <hr class="hr-lg mt-0 mb-3 w-10 mx-auto hr-primary"/>
        <h2 class="text-center text-uppercase font-weight-bold my-0">
          门店网络
        </h2>
        <h5 class="text-center font-weight-light mt-2 mb-0 text-muted">
          Great products, great value!
        </h5>
        <hr class="mb-5 w-50 mx-auto"/>
        <div class="card-group">
          <!-- Product  -->
          <div class="row">
            <div class="col-md-4" v-for="store in stores">
              <div class="card product-card mb-1">
                <!-- Image & price content -->
                <div class="pos-relative">
                </div>
                <!-- Content -->
                <div class="card-body">
                  <h5 class="card-title">
                    {{store.name}}
                  </h5>
                  <p>地址：{{store.location}}</p>
                  <p>电话：{{store.phone}}</p>

                </div>

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
    name: 'Stores',
    data () {
      return {
        stores: [],
        msg: 'Welcome to Your Vue.js App'
      }
    },
    created: function () {


      var t = this;
      $.getJSON("/api/stores/", function (result) {
        t.stores = result;

      })

    },
    methods: {}
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
