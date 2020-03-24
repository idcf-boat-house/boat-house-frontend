<template>

  <!-- ======== @Region: #content ======== -->
  <form id="story" name='form' method="POST" action="/">
    <div id="content" class="p-0">

      <div id="features" class="container py-4 py-lg-6">
        <hr class="hr-lg mt-0 mb-3 w-10 mx-auto hr-primary" />
        <h2 class="text-center text-uppercase font-weight-bold my-0">
          Story
        </h2>
        <h5 class="text-center font-weight-light mt-2 mb-0 text-muted">
           It's a beautful fame!
        </h5>
        <br/>
        <div class="main">
          <!-- left panel -->
          <div class="left">
            <!-- text -->
            <div class="left-text">
              18世纪90年代末的E国是一个战乱的国度，战争中，名为安德烈（Andre）的皇帝所带领的军队撤离到一个不知名的小镇，受伤的皇帝在这里遇上美丽典雅的牧场少女Aviva，Andre虽然受伤，但身为皇帝的Andre依然心系战事，希望能早日重返战场。
            </div>
            <!-- image -->
            <div class="left-img"></div>
          </div>
          <!-- right panel -->
          <div class="right">
            <!-- text -->
            <div class="right-text">
              Aviva明白他的想法，除了细心帮他料理伤口，还每天用石头加热烹制菜肴给他吃，因为当地人认为用石头加热烹制食物可以调理身体，对伤口的复原有帮助。后来安德烈（Andre）获得了胜利，为了怀念，Aviva在小镇上开起了一家名为Boat House的小餐馆，里面的菜式全部都是以石头加热烹制的各式菜肴。从此这个小镇改名为南皇后渡口（South Queensferry）。而Boat House餐馆成为了南皇后渡口小镇的标志性建筑。
            </div>
            <!-- image -->
            <div class="right-img"></div>
          </div>
        </div>
      </div>
      <!-- Call to action -->

    </div>
  </form>

</template>

<script>
export default {
  name: 'Story',
  data () {
    return {

    }
  },
  created: function () {

  },
  methods: {
    // likeProduct: function (p) {
    //   let postData = {'data':{'story': p}}
    //   this.axios.post('api/Story/vote', postData).then(function (result) {
    //     if (result.status === 200) {
    //       $('#vote-modal').modal('toggle')
    //     }
    //   })
    // }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.main{
  background:url(../assets/img/story/bg.png) no-repeat center;
  margin:auto;
  /* text-align:center; */

}

.left{
  position:relative;
  width: 450px;
  height:500px;
  display:inline-block;
  margin: 1rem 1rem 1rem 1rem;
}

.right{
  position:relative;
  width:450px;
  height:500px;
  display:inline-block;
  margin: 1rem 1rem 1rem 1rem;
}

.left-text{
  position:absolute;
  z-index:100;
  overflow-y:scroll;
  background:#fff;
  width: 350px;
  height:200px;
  display:inline-block;
  margin: 2rem 1rem 1rem 3rem;
  padding: 0.2rem;
  border: 5px solid rgb(255, 255, 255);
  border-radius: 1rem;
}

.right-text{
  position:absolute;
  z-index:100;
  overflow-y:scroll;
  background:#fff;
  width: 350px;
  height:200px;
  bottom:0;
  display:inline-block;
  margin: 1rem 1rem 2rem 6rem;
  padding: 0.2rem;
  border: 5px solid rgb(255, 255, 255);
  border-radius: 1rem;
}

.left-img{
  position:absolute;
  z-index:80;
  background:url(../assets/img/story/left.png) no-repeat center;
  background-size:100% 100%;
  width: 450px;
  height: 300px;
  display:inline-block;
  margin: 10rem 1rem 1rem 1rem;
  border: 5px solid rgb(255, 255, 255);
  border-radius: 1rem;
}
.right-img{
  position:absolute;
  z-index:80;
  background:url(../assets/img/story/right.png) no-repeat center;
  background-size:100% 100%;
  width:450px;
  height:300px;
  display:inline-block;
  margin: 1rem 1rem 10rem 1rem;
  border: 5px solid rgb(255, 255, 255);
  border-radius: 1rem;
}

/* @media screen {
  .left {background-size:100% 80%;}
} */
</style>
