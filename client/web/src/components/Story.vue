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
              这是一个美好的传说
            </div>
            <!-- image -->
            <div class="left-img"></div>
          </div>
          <!-- right panel -->
          <div class="right">
            <!-- text -->
            <div class="right-text">
              这是一方美味
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
  background:#FF0;
  width: 300px;
  height:120px;
  display:inline-block;
  margin: 6rem 1rem 1rem 3rem;
  border: 5px solid rgb(255, 255, 255);
  border-radius: 1rem;
}

.right-text{
  position:absolute;
  z-index:100;
  background:#FF0;
  width: 300px;
  height:120px;
  bottom:0;
  display:inline-block;
  margin: 1rem 1rem 8rem 8rem;
  border: 5px solid rgb(255, 255, 255);
  border-radius: 1rem;
}

.left-img{
  position:absolute;
  z-index:80;
  background:url(../assets/img/story/left.png) no-repeat center;
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
