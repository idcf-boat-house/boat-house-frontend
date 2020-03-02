<template>
  
    <div class="container-fluid">
         <!-- 欢迎页面 -->
        <template v-if="state === 'welcome'">
              <div class="row justify-content-center bg-light" style="height: 200px">
                  <div class="align-self-center">
                      <h1 >加盟船屋餐厅, 做美食的老板</h1>
                      <div class="col-12" style="text-align:center">
                        <button type="button" class="btn btn-info" @click="joinIn">立即加盟</button>
                      </div>
                  </div>
              </div>
        </template>
         <!-- 加盟页面 -->

        <template v-if="state === 'join'">
           <div class="row justify-content-center bg-light" style="height: 300px">
            <form action="#">
            <div class="modal-header bg-light">
              <h4 class="modal-title">
                请填写联系方式
              </h4>
            </div>
            <div class="modal-body">
              <div class="form-group mb-1">
                <label class="sr-only" for="name">姓名</label>
                <input type="text" id="name" class="form-control email" placeholder="姓名" v-model="username">
              </div>
              <div class="form-group mb-1">
                <label class="sr-only" for="telphone">联系电话</label>
                <input type="text" id="telphone" class="form-control password mb-1" placeholder="联系电话" v-model="password">
              </div>
              <div class="form-group mb-1">
                <label class="sr-only" for="address">所在地址</label>
                <input type="text" id="address" class="form-control password mb-1" placeholder="所在地址" v-model="password">
              </div>
            </div>
            <div class="modal-footer bg-light py-3">
             <div class="col-12" style="text-align:center">
                <button type="button" class="btn btn-primary" @click="submitJoin">提 交</button>
              </div>
            </div>
          </form>
           </div>
        </template>

        <!-- feedback-->
        <template v-if="state === 'feedback' ">
            <div class="row justify-content-center bg-light" style="height: 200px">
                  <div class="align-self-center">
                      <h3 >我们已收到您的加盟信息，将尽快和您联系。(3s后返回首页）</h3>
                  </div>
              </div>
        </template>  
    </div>
</template>


<script>
export default {
  name: "Franchisee",
  data () {
    return {
      state: 'welcome'
    }

  },
  created : function() {

  },
  methods: {
    joinIn : function() {
        this.state ='join';
    },
    submitJoin: function() {
        this.state = "feedback"
        // 3秒后回首岩浆
        setTimeout( () => {
           this.$router.push('/');
        },3000 )
    }

  }

}
</script>

<style scoped>

</style>
