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
                      <el-table-column v-for="items in orderDataType" :key="items.nameLable" :prop="items.nameProp" :label="items.nameLable" width="auto"></el-table-column>
                      <el-table-column type="expand" fixed="right" align="center" label="查看详情" min-width="280">
                        <template slot-scope="scope">
                            <el-table :data="scope.row.orderItems" class="table-striped">
                              <el-table-column label="菜品图片">
                                <template slot-scope="scope">
                                  <el-image :src="scope.row.picture" :fit="fit" style="width: 50px; height: 50px">
                                    <div slot="error" class="image-slot">
                                      <span>无图</span>
                                    </div>
                                  </el-image>
                                </template>
                              </el-table-column>
                              <el-table-column v-for="items in itemDataType" :key="items.nameLable" :prop="items.nameProp" :label="items.nameLable" width="auto"></el-table-column>
                            </el-table>
                            <div style="width:100%;margin-top: 10px;text-align:right;">
                              <el-button class="btn grey btn-outline-secondary" v-on:click="refuse(scope.row,scope.$index)">拒单</el-button>
                              <el-button class="btn btn-outline-primary" v-on:click="confirm(scope.row,scope.$index)">接单</el-button>
                            </div>
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
        nameLable: '支付类型',
        nameProp: 'payType'
      },
      {
        nameLable: '订单总额',
        nameProp: 'totalAmount'
      },
      {
        nameLable: '订单状态',
        nameProp: 'orderStatus'
      },
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
      }
    ]
    // _this.orders = [
    //   {
    //     orderId: "xxxxxx1",
    //     _orderId: "#xxxxxx1",
    //     orderTime: "2019-09-10 21:09:11",
    //     payType: "微信支付",
    //     totalAmount: "90.29",
    //     orderStatus: "待接单",
    //     orderItems: [
    //       {
    //         foodName: "菜品1111",
    //         foodPrice: "29.22",
    //         foodNum: 3,
    //         picture: ""
    //       },
    //       {
    //         foodName: "菜品1111",
    //         foodPrice: "29.22",
    //         foodNum: 3,
    //         picture: ""
    //       },
    //       {
    //         foodName: "菜品1111",
    //         foodPrice: "29.22",
    //         foodNum: 3,
    //         picture: ""
    //       }
    //     ]
    //   },
    //   {
    //     orderId: "xxxxxx2",
    //     _orderId: "#xxxxxx2",
    //     orderTime: "2019-09-10 21:09:11",
    //     payType: "微信支付",
    //     totalAmount: "90.29",
    //     orderStatus: "已支付",
    //     orderItems: [
    //       {
    //         foodName: "菜品1111",
    //         foodPrice: "29.22",
    //         foodNum: 3,
    //         picture: ""
    //       },
    //       {
    //         foodName: "菜品1111",
    //         foodPrice: "29.22",
    //         foodNum: 3,
    //         picture: ""
    //       },
    //       {
    //         foodName: "菜品1111",
    //         foodPrice: "29.22",
    //         foodNum: 3,
    //         picture: ""
    //       }
    //     ]
    //   },
    //   {
    //     orderId: "xxxxxx3",
    //     _orderId: "#xxxxxx3",
    //     orderTime: "2019-09-10 21:09:11",
    //     payType: "微信支付",
    //     totalAmount: "90.29",
    //     orderStatus: "已完成",
    //     orderItems: [
    //       {
    //         foodName: "菜品1111",
    //         foodPrice: "29.22",
    //         foodNum: 3,
    //         foodPicture: ""
    //       },
    //       {
    //         foodName: "菜品1111",
    //         foodPrice: "29.22",
    //         foodNum: 3,
    //         picture: ""
    //       },
    //       {
    //         foodName: "菜品1111",
    //         foodPrice: "29.22",
    //         foodNum: 3,
    //         picture: ""
    //       }
    //     ]
    //   },
    // ]
  },
  methods: {
    refuse: function(row, index){

    },
    confirm: function(row, index){

    }
  }
}
</script>