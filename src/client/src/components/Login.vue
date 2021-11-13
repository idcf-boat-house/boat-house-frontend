<template>


    <div id="content" class="p-0">

      <div style="margin: 50px">
        <form action="login.html">
          <div class="modal-header bg-light">
            <h4 class="modal-title">
              登录船屋
            </h4>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <label class="sr-only" for="login-email">用户名</label>
              <input type="email" id="login-email" class="form-control email" placeholder="用户名" v-model="username">
            </div>
            <div class="form-group mb-0">
              <label class="sr-only" for="login-password">密码</label>
              <input type="password" id="login-password" class="form-control password mb-1" placeholder="密码" v-model="password">
            </div>
          </div>
          <div class="modal-footer bg-light py-3">
            <div class="d-flex align-items-center">
              <button type="button" class="btn btn-primary" @click="login">登录</button>
            </div>

          </div>
        </form>

      </div>

    </div>



</template>

<script>
  export default {
    name: 'Login',
    data () {
      return {
        isLoging: false,
        username: '',
        password: ''
      }
    },
    created: function () {


    },
    methods: {
      setCookie: function (cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        console.info(cname + "=" + cvalue + "; " + expires);
        document.cookie = cname + "=" + cvalue + "; " + expires;
        console.info(document.cookie);
      },
      getCookie: function (cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
          var c = ca[i];
          while (c.charAt(0) == ' ') c = c.substring(1);
          if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
        }
        return "";
      },
      login:function(){
        this.isLoging = true;
        this.setCookie('session', this.username, 365);
        this.$parent.isLoging=true;
        this.$parent.username=this.username;
        this.$router.push('/');
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
