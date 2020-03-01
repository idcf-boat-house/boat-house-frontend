<template>
<div style="min-height:calc(100% - 45px)">
  <div class="main-menu menu-fixed menu-dark menu-accordion menu-shadow" data-scroll-to-active="true">
    <div class="main-menu-content">
      <ul class="navigation navigation-main" id="main-menu-navigation" data-menu="menu-navigation">
        <li class=" nav-item"><a><i class="la la-dashboard"></i><span class="menu-title" data-i18n="nav.dash.main">仪表盘</span></a>
          <ul class="menu-content">
            <li class="active"><router-link to="/">数据统计分析<span class="badge badge badge-info badge-pill float-right mr-2">6</span></router-link>
            </li>
          </ul>
        </li>
        <li class=" nav-item"><a><i class="la la-cogs"></i><span class="menu-title" data-i18n="nav.dash.main">后台管理</span></a>
          <ul class="menu-content">
            <li><router-link to="/FoodCategory">菜品分类管理</router-link></li>
            <li><router-link to="/JoinUs">加盟查询</router-link></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
  <router-view></router-view>
</div>
</template>


<script>
  import Dashboard from './components/Dashboard.vue'
  export default {
    name: 'App',
    data () {
      return {
      }
    },
    components: {
      Dashboard
    }
  }

</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>
