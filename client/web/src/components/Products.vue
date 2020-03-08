<template>

  <!-- ======== @Region: #content ======== -->
  <form id="choice" name='form' method="POST" action="/">

    <!-- ======== @Region: #highlighted ======== -->
    <div id="highlighted">
      <!-- Showshow - Slider Revolution see: plugins/slider-revolution/examples&sources for help invoke using data-toggle="slider-rev" options can be passed to the slider via HTML5 data- ie. data-startwidth="960" -->
      <div class="slider-wrapper rev_slider_wrapper bg-black" data-page-class="slider-appstrap-theme">
        <div class="rev_slider" data-toggle="slider-rev" data-settings='{"delay":10000}'>
          <ul>
            <!-- SLIDE 1 -->
            <li class="slide" data-transition="fadethroughdark" data-slotamount="7" data-hideafterloop="0" data-hideslideonmobile="off" data-easein="default" data-easeout="default" data-masterspeed="default" data-thumb="assets/img/shop/mens-1-thumb.jpg" data-rotate="0" data-saveperformance="off" data-title="Slide" >
              <img src="assets/img/slide-dummy.png" style='background-color:#151513' data-lazyload="assets/img/transparent.png" alt="Background image" data-bgposition="center top" data-bgfit="cover" data-bgparallax="10" class="rev-slidebg" data-no-retina />
              <!-- SLIDE 1 Content-->
              <div class="slide-content container pos-zindex-80">
                <!--elements within .slide-content are pushed below navbar on "behind"-->
                <!-- Layer 1 -->
                <div class="tp-caption text-grey" data-x="['left','left','left','left']" data-hoffset="['0','0','0','40']" data-y="['bottom','bottom','bottom','bottom']" data-voffset="['140','140','140','140']" data-fontsize="['52','52','52','52']" data-lineheight="['52','52','52','52']" data-width="420" data-height="none" data-whitespace="normal" data-type="text" data-basealign="slide" data-responsive_offset="on" data-frames='[{"from":"y:[100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;s:inherit;e:inherit;","speed":1200,"to":"o:1;","delay":1300,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"opacity:0;","ease":"nothing"}]' data-textAlign="left"> <span class="font-weight-bold text-uppercase text-white">最新菜品</span>
                </div>
                <!-- Layer 3 -->
                <div class="tp-caption text-grey" data-x="['left','left','left','left']" data-hoffset="['0','0','0','40']" data-y="['bottom','bottom','bottom','bottom']" data-voffset="['80','80','80','80']" data-fontsize="['20','20','20','30']" data-width="none" data-height="none" data-whitespace="nowrap" data-type="text" data-basealign="slide" data-responsive_offset="on" data-frames='[{"delay":0,"speed":1200,"frame":"0","from":"y:[100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;opacity:0;","to":"o:1;","delay":1400},{"delay":"wait","speed":300,"frame":"999","to":"auto:auto;","ease":"Power3.easeInOut"}]' data-textAlign="['center','center','center','center']"> 限时8折优惠!
                </div>
                <!-- Layer 3 -->
                <div class="tp-caption rs-parallaxlevel-8" data-frames='[{"from":"o:0;sX:3;sY:3;","speed":1500,"to":"o:0.20;sX:3;sY:3;","delay":500,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1200,"to":"x:[100%];","ease":"Power3.easeInOut"}]' data-x="left" data-y="bottom" data-hoffset="0" data-voffset="0" data-width="none" data-height="none" data-type="image" data-basealign="slide"> <i class="fa fa-bullseye icon-20x icon-bg op-1 text-white"></i>
                </div>
                <!-- Layer 4 -->
                <div class="tp-caption tp-resizeme bg-primary-bright" data-frames='[{"from":"x:[-100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;","mask":"x:0px;y:0px;s:inherit;e:inherit;","speed":1500,"to":"o:1;","delay":500,"ease":"Power3.easeInOut"},{"delay":"wait","speed":1000,"to":"x:[100%];","mask":"x:inherit;y:inherit;s:inherit;e:inherit;","ease":"Power3.easeInOut"}]' data-x="['left','left','left','left']" data-hoffset="['0','0','0','40']" data-y="bottom" data-voffset="120" data-width="380" data-height="4" data-basealign="slide"></div>
              </div>
              <div class="tp-caption tp-resizeme" data-x="['right','right','right','right']" data-hoffset="['0','0','0','0']" data-y="['top','top','top','top']" data-voffset="['0','0','0','0']" data-width="full-proportional"
                   data-height="full-proportional"
                   data-whitespace="nowrap"
                   data-type="image" data-responsive_offset="on" data-frames='[{"delay":500,"speed":3800,"frame":"0","from":"opacity:0;x:150%;","to":"o:1;x:0;","ease":"Circ.easeOut"},{"delay":"wait","speed":300,"frame":"999","to":"opacity:0;","ease":"Power3.easeInOut"}]'
                   data-textAlign="['inherit','inherit','inherit','inherit']"
                   data-paddingtop="[0,0,0,0]"
                   data-paddingright="[0,0,0,0]"
                   data-paddingbottom="[0,0,0,0]"
                   data-paddingleft="[0,0,0,0]"
                   style="z-index: 5;">
                <img src="assets/img/slide-dummy.png" alt="" data-lazyload="assets/img/product/slide01.png" data-no-retina />

              </div>

            </li>
          </ul>
          <div class="tp-bannertimer tp-bottom"></div>
        </div>
        <!--end of tp-banner-->
      </div>
    </div>
    
    <div id="content" class="p-0">
      <!-- Most Popular Products -->
      <div id="features" class="container py-4 py-lg-6">
        <hr class="hr-lg mt-0 mb-3 w-10 mx-auto hr-primary" />
        <h2 class="text-center text-uppercase font-weight-bold my-0">
          最受 欢迎 菜品
        </h2>
        <h5 class="text-center font-weight-light mt-2 mb-0 text-muted">
           Great food, great people!
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
                <a id="a"  name="vote" class="btn btn-primary text-light" value="a" v-on:click="likeProduct('a')"> ❤ 好吃</a>
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
                <a id="b"  name="vote" class="btn btn-primary text-light" value="b" v-on:click="likeProduct('b')"> ❤ 好吃</a>
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
                <a id="c"  name="vote" class="btn btn-primary text-light" value="c" v-on:click="likeProduct('c')"> ❤ 好吃</a>
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
                <a id="d"  name="vote" class="btn btn-primary text-light" value="d" v-on:click="likeProduct('d')"> ❤ 好吃</a>
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
                <a id="e"  name="vote" class="btn btn-primary text-light" value="e" v-on:click="likeProduct('e')"> ❤ 好吃</a>
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
                <a id="f"  name="vote" class="btn btn-primary text-light" value="f" v-on:click="likeProduct('f')"> ❤ 好吃</a>
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
    likeProduct: function (p) {
      let postData = {'data':{'product': p}}
      this.axios.post('api/product/vote', postData).then(function (result) {
        if (result.status === 200) {
          $('#vote-modal').modal('toggle')
        }
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
