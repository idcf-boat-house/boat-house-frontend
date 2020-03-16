<template>
  <div>

    <a id="#top" href="#content" class="sr-only">Skip to content</a>
    <!-- ======== @Region: #header ======== -->
    <div id="header">
      <div data-toggle="sticky">

        <!--Header search region - hidden by default -->
        <div class="header-search collapse" id="search">
          <form class="search-form container">
            <input type="text" name="search" class="form-control search" value="" placeholder="Search">
            <button type="button" class="btn btn-link"><span class="sr-only">Search </span><i class="fa fa-search fa-flip-horizontal search-icon"></i></button>
            <button type="button" class="btn btn-link close-btn" data-toggle="search-form-close"><span class="sr-only">Close </span><i class="fa fa-times search-icon"></i></button>
          </form>
        </div>

        <!--Header & Branding region-->
        <div class="header">
          <!-- all direct children of the .header-inner element will be vertically aligned with each other you can override all the behaviours using the flexbox utilities (flexbox.html) All elements with .header-brand & .header-block-flex wrappers will automatically be aligned inline & vertically using flexbox, this can be overridden using the flexbox utilities (flexbox.htm) Use .header-block to stack elements within on small screen & "float" on larger screens use .order-first or/and .order-last classes to make an element show first or last within .header-inner or .headr-block elements -->
          <div class="header-inner container justify-content-start">
            <!--branding/logo -->
            <div class="header-brand">
              <img src="assets/img/logo/logo.png" height="50" width="50" class="pull-left mr-1">
              <a class="header-brand-text" href="#" title="Home">
                <h1 class="h2">
                  <span class="header-brand-text-alt">船屋餐厅</span>苏格兰风味
                </h1>
              </a>
            </div>
            <!-- other header content -->
            <div class="header-block order-12">
              <div class="flex-column text-right d-none d-lg-flex mr-2">
                <h6 class="my-0 mt-1">
                  <i class="fa fa-clock text-primary"></i> 营业时间：早10点 - 晚9点
                </h6>

              </div>

              <!--Search trigger -->
              <a href="#search" class="btn btn-icon btn-link header-btn float-right order-11" data-toggle="search-form" data-target=".header-search"><i class="fa fa-search fa-flip-horizontal search-icon"></i></a>

              <!-- mobile collapse menu button - data-toggle="collapse" = default BS menu - data-toggle="off-canvas" = Off-cavnas Menu - data-toggle="overlay" = Overlay Menu -->
              <a href="#top" class="btn btn-link btn-icon header-btn float-right d-lg-none" data-toggle="off-canvas" data-target=".navbar-main" data-settings='{"cloneTarget":true, "targetClassExtras": "navbar-offcanvas"}'> <i class="fa fa-bars"></i> </a>
              <div class="header-divider d-none d-lg-block"></div>

              <!--Shopping cart-->
              <div class="dropdown dropdowns-no-carets dropdown-effect-fadeup float-right">
                <a href="#" class="btn btn-icon btn-dark btn-link float-right dropdown-toggle cart-link" data-toggle="dropdown">
                  <span class="cart-link-icon"> <i class="fa fa-shopping-cart"></i> <span class="sr-only">Cart</span> <span class="cart-link-count bg-primary text-white">2</span> </span>
                </a>

                <!--Shopping cart dropdown -->
                <div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow cart-dropdown-menu" role="menu">
                  <h5 class="dropdown-header mb-0">
                    购物车列表
                  </h5>
                  <hr class="mt-0 mb-3" />
                  <!--Shopping cart items-->
                  <div class="cart-items">
                    <!--Shopping cart item 1 -->
                    <div class="cart-items-item">
                      <div class="float-left">
                        <h5 class="mb-0">
                          蜂蜜烤猪肉
                        </h5>
                        <p class="mb-0">¥90 / x2</p>
                        <a href="#" class="close cart-remove text-primary"> <i class="fa fa-times"></i> </a>
                      </div>
                    </div>
                    <!--Shopping cart item 2 -->
                    <div class="cart-items-item">
                      <div class="float-left">
                        <h5 class="mb-0">
                          牛肉haggis
                        </h5>
                        <p class="mb-0">¥69 / x1</p>
                        <a href="#" class="close cart-remove text-primary"> <i class="fa fa-times"></i> </a>
                      </div>
                    </div>

                  </div>
                  <!--End of Shopping cart items-->
                  <hr class="mt-3 mb-0" />
                  <div class="dropdown-footer text-center">
                    <h5 class="font-weight-bold">
                      合计: <span class="text-primary">¥249</span>
                    </h5>
                    <a href="#" tabindex="-1" class="btn btn-outline-primary btn-sm btn-rounded mx-2">清理购物车</a> <a href="#" tabindex="-1" class="btn btn-primary btn-sm btn-rounded mx-2">去结算</a>
                  </div>
                </div>
              </div>
              <!-- end of shopping cart -->
            </div>
          </div>
        </div>

        <div class="navbar navbar-expand-md">
          <!--everything within this div is collapsed on mobile-->
          <div class="navbar-main collapse bg-grey-dark navbar-dark">
            <div class="container clearfix">
              <!--main navigation-->
              <ul class="nav navbar-nav float-lg-left navbar-nav-flush dropdown-effect-fadeup ml-1">
                <!-- Shop Homepage -->
                <li class="nav-item">
                  <router-link to="/" class="nav-link">主页</router-link>
                </li>
                <li class="nav-item">
                  <router-link to="/food" class="nav-link">船屋菜单</router-link>
                </li>
                <li class="nav-item">
                  <router-link to="/story" class="nav-link">船屋故事</router-link>
                </li>
                <li class="nav-item">
                  <router-link to="/franchisee" class="nav-link">品牌加盟</router-link>
                </li>

              </ul>
              <!-- All Categories menu -->
              <ul class="nav navbar-nav float-lg-right navbar-nav-flush-right dropdown-effect-fadeup">
                <li class="nav-item dropdown dropdowns-no-carets dropdown-persist">
                  <!-- Dropdown Menu -->
                  <div class="dropdown-menu dropdown-menu-right"> <a href="#" class="dropdown-item">我的收藏</a> <a href="#" class="dropdown-item">订单记录</a>
                  </div>
                </li>
              </ul>
            </div>
          </div>
          <!--/.navbar-collapse -->
        </div>
      </div>
    </div>

    <router-view />

    <!-- ======== @Region: #footer ======== -->
    <footer id="footer">
      <div class="container">
        <div class="row">

          <div class="col-lg-4">
            <h3 class="text-white text-uppercase text-slab font-weight-bold">
              船屋<span class="text-primary">餐厅</span>
            </h3>
            <p class="text-sm"> 顾客至上 服务第一 </p>

            <!--social media icons-->
            <div class="mt-3 mb-4 mb-lg-0">
              <!--@todo: replace with company social media details-->
              <a href="#" class="btn btn-icon btn-dark btn-rounded btn-flat"> <i class="fab fa-twitter"></i> <span class="sr-only">Fa twitter</span> </a>
              <a href="#" class="btn btn-icon btn-dark btn-rounded btn-flat"> <i class="fab fa-facebook-f"></i> <span class="sr-only">Fa facebook f</span> </a>
              <a href="#" class="btn btn-icon btn-dark btn-rounded btn-flat"> <i class="fab fa-linkedin-in"></i> <span class="sr-only">Fa linkedin in</span> </a>
              <a href="#" class="btn btn-icon btn-dark btn-rounded btn-flat"> <i class="fab fa-google-plus-g"></i> <span class="sr-only">Fa google plus g</span> </a>
            </div>
          </div>

          <div class="col-lg-7 offset-lg-1">
            <div class="row">
            </div>
          </div>
        </div>

        <hr class="my-4 hr-white op-1" />
        <!--@todo: replace with company copyright details-->
        <div class="subfooter text-sm text-center">
          <p>Copyright 2018 &copy; 欢乐美食 尽在船屋</p>
          <ul class="list-inline footer-links">
            <li class="list-inline-item"><a href="#">服务条款</a></li>
            <li class="list-inline-item"><a href="#">隐私</a></li>
            <li class="list-inline-item"><a href="#">联系我们</a></li>
          </ul>
        </div>
      </div>
      <a href="#top" class="btn btn-icon btn-dark pos-fixed pos-b pos-r mr-3 mb-3 scroll-state-hidden" title="Back to top" data-scroll="scroll-state"><i class="fa fa-chevron-up"></i></a>
    </footer>
    <!-- Style switcher - demo only - @todo: remove in production -->
    <div class="colour-switcher">
      <a href="#" class="colour-switcher-toggle" data-toggle="class" data-target=".colour-switcher"> <i class="fa fa-paint-brush"></i> </a>
      <h5 class="text-uppercase my-0">
        Theme Colours
      </h5>
      <hr />
      <div class="theme-colours"><a href="#green" class="green active" data-toggle="tooltip" data-placement="right" data-original-title="Green (Default)">Green</a> <a href="#red" class="red" data-toggle="tooltip" data-placement="right" data-original-title="Red">Red</a> <a href="#blue" class="blue" data-toggle="tooltip" data-placement="right" data-original-title="Blue">Blue</a> <a href="#purple" class="purple" data-toggle="tooltip" data-placement="right" data-original-title="Purple">Purple</a>
        <a href="#pink" class="pink" data-toggle="tooltip" data-placement="right" data-original-title="Pink">Pink</a> <a href="#orange" class="orange" data-toggle="tooltip" data-placement="right" data-original-title="Orange">Orange</a>
        <a href="#lime" class="lime" data-toggle="tooltip" data-placement="right" data-original-title="Lime">Lime</a> <a href="#blue-dark" class="blue-dark" data-toggle="tooltip" data-placement="right" data-original-title="Blue-dark">Blue-dark</a>
        <a href="#red-dark" class="red-dark" data-toggle="tooltip" data-placement="right" data-original-title="Red-dark">Red-dark</a>
        <a href="#brown" class="brown" data-toggle="tooltip" data-placement="right" data-original-title="Brown">Brown</a> <a href="#cyan-dark" class="cyan-dark" data-toggle="tooltip" data-placement="right" data-original-title="Cyan-dark">Cyan-dark</a>
        <a href="#yellow" class="yellow" data-toggle="tooltip" data-placement="right" data-original-title="Yellow">Yellow</a> <a href="#slate" class="slate" data-toggle="tooltip" data-placement="right" data-original-title="Slate">Slate</a> <a href="#olive" class="olive" data-toggle="tooltip" data-placement="right" data-original-title="Olive">Olive</a> <a href="#teal" class="teal" data-toggle="tooltip" data-placement="right" data-original-title="Teal">Teal</a> <a href="#green-bright" class="green-bright" data-toggle="tooltip" data-placement="right" data-original-title="Green-bright">Green-bright</a></div>
      <hr />
      <p class="text-xs text-muted">Cookies are NOT enabled so colour selection is not persistent.</p>
      <p class="text-xs my-0"><a href="index.html">Back to main homepage</a></p>
      <p class="text-xs my-0"><a href="intro.html">Back to intro page</a></p>
    </div>
    <!--Hidden elements - excluded from jPanel Menu on mobile-->
    <div class="hidden-elements js-off-canvas-exclude">
      <!--@modal - signup modal-->
      <div class="modal fade" id="signup-modal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
          <form action="signup.html">
            <div class="modal-content">
              <div class="modal-header bg-light">
                <h4 class="modal-title">
                  Sign Up
                </h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
              </div>
              <div class="modal-body">
                <div class="form-group">
                  <h6 class="op-8">
                    Price Plan
                  </h6>
                  <select class="form-control">
                    <option>Basic</option>
                    <option>Pro</option>
                    <option>Pro +</option>
                  </select>
                </div>
                <hr />

                <h6 class="op-8">
                  Account Information
                </h6>
                <div class="form-group">
                  <label class="sr-only" for="signup-first-name">First Name</label>
                  <input type="text" class="form-control" id="signup-first-name" placeholder="First name">
                </div>
                <div class="form-group">
                  <label class="sr-only" for="signup-last-name">Last Name</label>
                  <input type="text" class="form-control" id="signup-last-name" placeholder="Last name">
                </div>
                <div class="form-group">
                  <label class="sr-only" for="signup-username">Userame</label>
                  <input type="text" class="form-control" id="signup-username" placeholder="Username">
                </div>
                <div class="form-group">
                  <label class="sr-only" for="signup-email">Email address</label>
                  <input type="email" class="form-control" id="signup-email" placeholder="Email address">
                </div>
                <div class="form-group">
                  <label class="sr-only" for="signup-password">Password</label>
                  <input type="password" class="form-control" id="signup-password" placeholder="Password">
                </div>
                <div class="form-check text-xs">
                  <label class="form-check-label op-8">
                    <input type="checkbox" value="term" class="form-check-input mt-1">
                    I agree with the Terms and Conditions.
                  </label>
                </div>
              </div>
              <div class="modal-footer bg-light py-3">
                <div class="d-flex align-items-center">
                  <button type="button" class="btn btn-primary">Sign Up</button>
                  <button type="button" class="btn btn-link ml-1" data-dismiss="modal" aria-hidden="true">Cancel</button>
                </div>
                <p class="text-xs text-right text-lh-tight op-8 my-0 ml-auto">Already signed up? <a href="login.html">Login
                    here</a></p>
              </div>
            </div>
            <!-- /.modal-content -->
          </form>
        </div>
        <!-- /.modal-dialog -->
      </div>
      <!-- /.modal -->

      <!--@modal - login modal-->
      <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
            <div class="modal-header bg-light">
              <h4 class="modal-title">
                Login
              </h4>
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
              <div class="form-group">
                <label class="sr-only" for="login-email">Email</label>
                <input type="email" id="login-email" class="form-control email" placeholder="Email">
              </div>
              <div class="form-group mb-0">
                <label class="sr-only" for="login-password">Password</label>
                <input type="password" id="login-password" class="form-control password mb-1" placeholder="Password">
              </div>
            </div>
            <div class="modal-footer bg-light py-3">
              <div class="d-flex align-items-center">
                <button type="button" class="btn btn-primary">Login</button>
                <button type="button" class="btn btn-link ml-1" data-dismiss="modal" aria-hidden="true">Cancel</button>
              </div>
              <p class="text-xs text-right text-lh-tight op-8 my-0 ml-auto">
                Not a member? <a href="#" class="signup">Sign up now!</a>
                <br />
                <a href="#">Forgotten password?</a>
              </p>
            </div>
            <!-- Modal -->
            <div class="modal fade modal-duration" data-modal-duration="1000" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" id="vote-modal">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h4 class="modal-title">
                      最受欢迎菜品:
                    </h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  </div>
                  <div class="modal-body"> 感谢支持!</div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- /.modal-content -->

        </div>
        <!-- /.modal-dialog -->
      </div>
      <!-- /.modal -->
    </div>

  </div>
</template>

<script>

import Products from '@/components/Products'
import Story from '@/components/Story'
import Franchisee from '@/components/Franchisee'


export default {
  name: 'App',
  data () {
    return {
      isLoging: false,
      username: ''
    }
  },
  components: {
    'app-products': Products,
    'app-Story': Story
  },
  mounted () {
    this.getUserInfo();
  },
  methods: {
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
    setCookie: function (cname, cvalue, exdays) {
      var d = new Date();
      d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
      var expires = "expires=" + d.toUTCString();
      console.info(cname + "=" + cvalue + "; " + expires);
      document.cookie = cname + "=" + cvalue + "; " + expires;
      console.info(document.cookie);
    },
    getUserInfo: function () {
      var user = this.getCookie("session");
      if (!!user) {
        this.isLoging = true;
        this.username = user;
      }
      console.log(user);
    },
    logout: function () {
      this.setCookie("session", "", 365);
      this.isLoging = false;
      this.username = '';
      this.$router.push('/login');

    }
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
