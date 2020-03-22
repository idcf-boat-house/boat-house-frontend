<template>
  <div class="container">
    <div class="row">
      <div class="col-md-8 orders-wrapper">
        <div class="order-title">您的订单</div>
        <div class="card" v-for="order in orders" :key="order.itemId">
          <div class="card-header" v-on:click="showOrderDetail(order.itemId)">
            <div class="row">
              <div class="col-sm-6 order-name">{{order.name}}</div>
              <div class="col-sm-4 order-time">2020-03-20 11:11:11</div>
              <div class="col-sm-2 order-status">已支付</div>
            </div>
          </div>
          <div class="order-detail collapse show" v-show="selectedOrder === order.itemId">
            <div class="card-body">
              <ul class="list-group">
                <li v-for="item in items" :key="item.itemId" class="list-group-item">
                  <div class="row">
                    <div class="col-sm-3">
                      <img :src="item.img" class="cooking-img" />
                    </div>
                    <div class="col-sm-5">
                      <div class="cooking-name">{{item.name}}</div>
                    </div>
                    <div class="quota col-sm-1">
                      x {{item.quota}}
                    </div>
                    <div class="col-sm-3 price">
                      ￥ {{formatMoney(item.price)}}
                    </div>
                  </div>
                </li>
              </ul>
              <div class="total-price">
                <span>总计：</span>
                <span class="price">￥ {{formatMoney(total)}}</span>
              </div>
            </div>
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
      orders: [{
        itemId: 1,
        name: '蜂蜜烤猪肉 等5件',
      }, {
        itemId: 2,
        name: '烤牛排 等2件',
      }],
      items: [{
        itemId: 1,
        name: '蜂蜜烤猪肉',
        img: 'assets/img/product/boathouse01.png',
        price: 8800,
        quota: 1
      }, {
        itemId: 1,
        name: '蜂蜜烤猪肉',
        img: 'assets/img/product/boathouse01.png',
        price: 8800,
        quota: 1
      }, {
        itemId: 1,
        name: '蜂蜜烤猪肉',
        img: 'assets/img/product/boathouse01.png',
        price: 8800,
        quota: 1
      }],
      selectedOrder: 1
    }
  },
  computed: {
    total () {
      return this.items.reduce((acc, item) => acc + item.price * item.quota, 0)
    }
  },
  methods: {
    formatMoney (m) {
      return Math.floor(m / 100).toFixed(2)
    },
    showOrderDetail (id) {
      if (this.selectedOrder === id) {
        this.selectedOrder = undefined
      } else {
        this.selectedOrder = id
      }
    }
  }
}
</script>
<style scoped>
.orders-wrapper {
  margin-top: 24px;
  margin-bottom: 24px;
  min-height: calc(100vh - 490px);
}

.order-title {
  font-weight: bold;
  line-height: 30px;
}

.card {
  margin-bottom: 0;
}

.card-header {
  cursor: pointer;
}

.order-status {
  text-align: right;
}

.order-item > .row {
  align-items: center;
}

.order-detail .row {
  align-items: center;
}

.order-detail .cooking-img {
  width: 108px;
  height: 70px;
}

.order-detail .price {
  color: #f00;
  font-size: 16px;
  text-align: right;
}

.total-price {
  display: flex;
  flex-flow: row;
  justify-content: flex-end;
  margin-top: 12px;
}
</style>
