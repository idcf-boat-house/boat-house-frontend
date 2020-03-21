<template>
  <div class="app-content content">
    <div class="content-wrapper">
      <div class="content-header row">
        <div class="content-header-left col-md-6 col-12 mb-2 breadcrumb-new">
          <h3 class="content-header-title mb-0 d-inline-block">订单管理</h3>
          <div class="row breadcrumbs-top d-inline-block">
            <div class="breadcrumb-wrapper col-12">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <a href="#">后台管理</a>
                </li>
                <li class="breadcrumb-item">
                  <a href="#">订单管理</a>
                </li>
              </ol>
            </div>
          </div>
        </div>
      </div>
      <div class="content-body">
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
                <h4 class="card-title">订单列表</h4>
                <a class="heading-elements-toggle">
                  <i class="la la-ellipsis-v font-medium-3"></i>
                </a>
                <div class="heading-elements">
                  <ul class="list-inline mb-0">
                    <li>
                      <a data-action="collapse">
                        <i class="ft-minus"></i>
                      </a>
                    </li>
                    <li>
                      <a data-action="reload">
                        <i class="ft-rotate-cw"></i>
                      </a>
                    </li>
                    <li>
                      <a data-action="expand">
                        <i class="ft-maximize"></i>
                      </a>
                    </li>
                    <li>
                      <a data-action="close">
                        <i class="ft-x"></i>
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
              <div class="card-content collapse show">
                <div class="card-body">
                  <div class="table-responsive">
                    <el-table :data="orders" style="width: 100%" class="table-striped">
                      <el-table-column type="expand" align="center" label="" min-width="280">
                        <template slot-scope="scope">
                            <el-table :data="scope.row.itemsList" class="table-striped">
                              <el-table-column v-for="items in itemDataType" :key="items.nameLable" :prop="items.nameProp" :label="items.nameLable" width="auto"></el-table-column>
                              <el-table-column label="菜品图片">
                                <template slot-scope="scope">
                                  <el-image :src="scope.row.foodPicture" :fit="fit" style="width: 50px; height: 50px">
                                    <div slot="error" class="image-slot">
                                      <span>无图</span>
                                    </div>
                                  </el-image>
                                </template>
                              </el-table-column>
                            </el-table>
                            <div style="width:100%;margin-top:10px;">
                              <el-input v-if="scope.row.orderStatus=='1'" type="text" placeholder="拒单理由" :id="scope.row.orderId" :name="scope.row.orderId" v-model="scope.row.reason"  @change="change($event)"></el-input>
                              <el-input v-else-if="scope.row.orderStatus=='-1'" type="text" placeholder="拒单理由" :id="scope.row.orderId" :name="scope.row.orderId" v-model="scope.row.reason" disabled="true"></el-input>
                            </div>
                            <div style="width:100%;margin-top: 10px;text-align:right;">
                              <el-button v-if="scope.row.orderStatus=='1'" class="btn grey btn-outline-secondary" v-on:click="refuse(scope.row.orderId)">拒单</el-button>
                              <el-button v-if="scope.row.orderStatus=='1'" class="btn btn-outline-primary" v-on:click="confirm(scope.row.orderId)">接单</el-button>
                            </div>
                        </template>
                      </el-table-column>
                      <el-table-column v-for="items in orderDataType" :key="items.nameLable" :prop="items.nameProp" :label="items.nameLable" width="auto"></el-table-column>
                      <el-table-column label="支付类型" width="auto">
                          <template slot-scope="scope">
                            <span v-if="scope.row.payType=='1'">支付宝支付</span>
                            <span v-else-if="scope.row.payType=='2'">微信支付</span>
                            <span v-else-if="scope.row.payType=='3'">银联支付</span>
                          </template>
                      </el-table-column>
                    </el-table>
                  </div>
                </div>
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
  name: "Orders",
  data () {
    return {
      orderDataType: [],
      itemDataType: [],
      orders: []
    }
  },
  mounted () {
    let _this = this
    _this.orderDataType = [
      {
        nameLable: '订单号',
        nameProp: 'orderId'
      },
      {
        nameLable: '下单时间',
        nameProp: 'orderTime'
      },
      {
        nameLable: '订单总额',
        nameProp: 'totalAmount'
      },
      {
        nameLable: '订单状态',
        nameProp: 'orderStatusDesc'
      },
      {
        nameLable: '订单更新时间',
        nameProp: 'updateTimeStr'
      }
    ],
    _this.itemDataType = [
      {
        nameLable: '菜品名称',
        nameProp: 'foodName'
      },
      {
        nameLable: '菜品价格',
        nameProp: 'foodPrice'
      },
      {
        nameLable: "菜品个数",
        nameProp: "foodNum"
      },
      {
        nameLable: "菜品小计",
        nameProp: "foodSubTotal"
      }
    ]
    _this.get_orders()
  },
  methods: {
    get_orders: function(){
        let _this = this;
        this.axios.get('/orders/pending').then(function (result) {
        if (result.status === 200) {
          _this.orders = result.data;
        }else if(result.status == 400){
          console.log(result);
        }
      })
    },
    confirm: function(orderId){
      let _this = this;
      console.log("confirm order_id:" + orderId);
      let _data = [orderId]
      this.axios
        .put('/orders/confirm', _data)
        .then(function (result) {
          if (result.status === 200) {
            _this.get_orders();
          }else if(result.status == 400){
            console.log(result);
          }
        })
    },
    refuse: function(orderId){
      let _this = this;
      let _reason = $("#" + orderId).val();
      console.log("refuse order_id :" + orderId + "reason :" + _reason);

      let _data = {order_id: orderId, reason:  _reason}
      this.axios
        .put('/orders/refuse', _data)
        .then(function (result) {
          if (result.status === 200) {
            _this.get_orders()
          }else if(result.status == 400){
            console.log(result);
          }
        })
    },
    change: function(e){
      console.log("input change event:"+ e);
      this.$forceUpdate();
    }
  }
}
</script>
