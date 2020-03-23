<template>
  <div class="container">
    <div class="row">
      <div class="col-md-8 cart-wrapper">
        <div class="cart-title">您的购物车</div>
        <ul class="list-group">
          <li v-for="item in items" :key="item.itemId" class="list-group-item cart-item">
            <div class="row">
              <div class="col-sm-3">
                <img :src="item.img" class="cooking-img" />
              </div>
              <div class="col-sm-6">
                <div class="cooking-name">{{item.name}}</div>
                <div class="price">￥ {{formatMoney(item.price)}}</div>
              </div>
              <div class="col-sm-3 operation">
                <button class="btn" v-on:click="decQuota(item)" :disabled="item.quota <= 1">
                  <i class="la la-minus"></i>
                </button>
                <div class="quota">
                  {{item.quota}}
                </div>
                <button class="btn" v-on:click="incQuota(item)" :disabled="item.quota > 10">
                  <i class="la la-plus"></i>
                </button>
              </div>
            </div>
          </li>
        </ul>
        <div class="total-price">
          <span>总计：</span>
          <span class="price">￥ {{formatMoney(total)}}</span>
        </div>
        <div class="remark">
          <textarea placeholder="备注: 口味、偏好" v-model="remark" />
        </div>
        <div class="order-op">
          <button class="btn" v-on:click="jumpToFoodPage">继续点单</button>
          <button class="btn" v-on:click="createNewOrder">确认下单</button>
        </div>
      </div>
    </div>
    <div
      class="modal fade"
      tabindex="-1"
      role="dialog"
      ref="successModel"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">下单成功</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" v-on:click="handleCloseSuccessModal">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div>已接收到您的订单，正等待店长确认</div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal" v-on:click="handleCloseSuccessModal">确定</button>
          </div>
        </div>
      </div>
    </div>
    <div
      class="modal fade"
      tabindex="-1"
      role="dialog"
      ref="failureModel"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">下单异常</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" v-on:click="handleCloseFailureModal">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div>Oops，我们系统开了一会儿小差，请稍后重试</div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal" v-on:click="handleCloseFailureModal">确定</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      success: false,
      items: [{
        itemId: 1,
        name: '蜂蜜烤猪肉',
        img: 'assets/img/product/boathouse01.png',
        price: 88.00,
        quota: 1
      }, {
        itemId: 2,
        name: '牛肉haggis',
        img: 'assets/img/product/boathouse02.png',
        price: 98.00,
        quota: 1
      }, {
        itemId: 3,
        name: '红酒炖羊腿',
        img: 'assets/img/product/boathouse03.png',
        price: 128.00,
        quota: 2
      }],
      remark: '',
      creating: false
    }
  },
  computed: {
    total () {
      return this.items.reduce((acc, item) => acc + item.price * item.quota, 0)
    }
  },
  methods: {
    fetchCart () {
      // TODO
    },
    formatMoney (m) {
      return m.toFixed(2)
    },
    incQuota (item) {
      if (item.quota < 10) {
        item.quota += 1
      }
    },
    decQuota (item) {
      if (item.quota > 1) {
        item.quota -= 1
      }
    },
    createNewOrder () {
      const postData = {
        itemsList: this.items.map(item => ({
            foodId: item.itemId,
            foodNum: item.quota
          })),
        note: this.remark
      }
      this.creating = true
      this.axios.post('api/orders/create', postData)
        .then((result) => {
          if (result.status === 200) {
            $(this.$refs['successModel']).modal('show')
          } else {
            $(this.$refs['failureModel']).modal('show')
          }
        })
        .catch(() => {
          $(this.$refs['failureModel']).modal('show')
        })
        .finally(() => {
          this.creating = false
        })
    },
    handleCloseSuccessModal () {
      setTimeout(() => {
        this.jumpToFoodPage()
      }, 1000)
    },
    handleCloseFailureModal () {

    },
    jumpToFoodPage () {
      this.$router.push('/food')
    }
  }
}
</script>
<style scoped>
.cart-wrapper {
  margin-top: 24px;
}

.cart-title {
  font-weight: bold;
  line-height: 30px;
}

.cart-item > .row {
  align-items: center;
}

.cart-item {
  height: 96px;
}

.cooking-img {
  width: 108px;
  height: 70px;
}

.cooking-name {
  font-size: 18px;
  margin-bottom: 6px;
}

.price {
  color: #f00;
  font-size: 16px;
}

.operation {
  display: flex;
  flex-direction: row;
  height: 38px;
}

.operation .quota {
  height: 38px;
  width: 40px;
  text-align: center;
  line-height: 20px;
  padding: 10px;
  border: 1px solid #f2f2f2;
}

.operation > .btn {
  height: 38px;
}

.operation > .input {
  height: 38px;
}

.total-price {
  display: flex;
  flex-flow: row;
  justify-content: flex-end;
  margin-top: 12px;
}

.remark {
  margin-top: 12px;
}

.remark textarea {
  width: 100%;
  border-color: #f2f2f2;
  font-size: 12px;
  line-height: 18px;
  height: 54px;
  resize: none;
  padding: 6px;
}

.remark textarea:focus {
  outline: none;
  border-color: #aaa;
}

.order-op {
  margin-top: 12px;
  margin-bottom: 24px;
  font-size: 16px;
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
}

.order-op .btn {
  width: 112px;
  height: 36px;
  margin-left: 12px;
  color: #fff;
  font-size: 14px;
  background: #f04914;
}

.order-op .btn:active {
  background: #f04914a8;
}

.modal-footer {
  padding: 0.75em;
}
</style>
