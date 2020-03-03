<template>
  
    <div class="container-fluid">
         <!-- 欢迎页面 -->
        <template v-if="state === 'welcome'">
              <div class="row justify-content-center bg-light">
                  <div class="align-self-center">
                      <div class="margin-large">
                        <h1 style="text-align:center;margin:30px 0px" >Boat House 品牌加盟</h1>
                        <p style="text-indent:25px">独具特色的Boat House，源于中欧、奥地利（维也纳），享誉世界的文化名城，有着千姿百态的生活风貌，素有“音乐之都”的盛誉，又有以精妙绝伦、风格各异的建筑而赢得“建筑之都”的美称，及全新的、自然的生活形态，独特的烘焙文化和烘焙工艺，享誉世界。</p>
                        <p style="text-indent:25px">Boat House一律采用古典工艺制作出美味的烘焙产品，把新鲜、健康、绿色，第一时间提供给顾客。以自然的 郁的家庭氛围，尽情的与身旁最亲密的人享受自然、甜蜜、温馨的幸福时光，给顾客带来健康营养、时尚高端，品味幸福与甜蜜的优质生活。</p>
                        <br>
                        <p>Boat House 加盟条件</p>
                        <p>1、熟悉当地商业环境及消费形态。</p>
                        <p>2、强烈品牌和服务意识，认同Boat House理念，服从公司统一管理。  </p>       
                        <p>3、有良好的商业信誉和务实认真的事业态度，理解并接受Boat House经营理念。</p>
                        <p>4、相应的资金实力。</p>
                        <p>5、认同并积极配合加盟总部的经营方针、管理模式和经营模式。</p>
                        <p>6、有较强经营管理、供关协调和市场运作能力。</p>
                        <br>
                        <p>Boat House 加盟优势</p>
                        <p>1.品牌支持：授予Boat House加盟商合法使用品牌、商标、证书等多项殊荣证书权利。</p>
                        <p>2.营建支持：协助选址、商圈评估、装修设计、店堂规划、投资预算。</p>
                        <p>3.企划支持：Boat House招商总部提供开业策划、营销策划、广告设计，定期品牌宣传及推广、网络营销。</p>
                        <p>4.营运支持：开业筹备、市场定位、运营指导、经营追踪及分析;持续营运督导</p>
                        <p>5.培训支持：Boat House 加盟商管理人员、技术人员、财务人员接受总部免费规范培训、总部派培训专员现场指导培训，定期片区集中培训、单店适时新品推广及技术培训。</p>
                      </div>
                      <div class="col-12" style="text-align:center">
                        <button type="button" class="btn btn-black-large" @click="joinIn">立即加盟</button>
                      </div>
                  </div>
              </div>
        </template>
         <!-- 加盟页面 -->

        <template v-if="state === 'join'">
           <div class="row justify-content-center bg-light">
            <form action="#">
            <div class="modal-header bg-light">
              <h4 class="modal-title">
                Boat House 品牌加盟申请
              </h4>
            </div>
            <div class="modal-body">
              <div class="form-group">
                <label class="sr-only" for="name">姓名</label>
                <input type="text" id="name" class="form-control email" placeholder="姓名" v-model="name">
              </div>
              <div class="form-group">
                <label class="sr-only" for="telphone">联系电话</label>
                <input type="text" id="telphone" class="form-control password mb-1" placeholder="联系电话" v-model="telephone">
              </div>
              <div class="form-group">
                <label class="sr-only" for="address">所在地址</label>
                <input type="text" id="address" class="form-control password mb-1" placeholder="所在地址" v-model="comment">
              </div>
            </div>
            <div class="modal-footer bg-light py-3">
             <div class="col-12" style="text-align:center">
                <button type="button" class="btn btn-black-large" @click="submitJoin">提 交</button>
              </div>
            </div>
          </form>
           </div>
        </template>

        <!-- feedback-->
        <template v-if="state === 'feedback' ">
            <div class="row justify-content-center bg-light" style="height: 200px">
                  <div class="align-self-center">
                      <h3 >提交成功，我们会在24小时之内联系您，谢谢！</h3>
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
      state: 'welcome',
      name: '',
      telephone: '',
      comment: ''
    }

  },
  created : function() {

  },
  methods: {
    joinIn : function() {
        this.state ='join';
    },
    submitJoin: function() {
       
        // 3秒后回首岩浆
        const postData = {
          name: this.name,
          telephone: this.telephone,
          comment: this.comment,
        };
        
        this.axios.post('/api/product/join', postData)
              .then( result => {
                  this.state = "feedback";
                 setTimeout( () => {
                    this.$router.push('/');
                 },3000 )
              });
        
       
    }

  }

}
</script>

<style scoped>
.margin-large {
  margin: 0px 80px;
}
.btn-black-large {
  background-color: black;
  width: 120px;
  color: #FFF;
  margin-bottom: 40px;
  font-size: 14px;
}
.form-group{
  width: 500px;
  margin-bottom: 20;
}
</style>
