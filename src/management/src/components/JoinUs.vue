<template>
  <div class="app-content content">
    <div class="content-wrapper">
      <div class="content-header row">
        <div class="content-header-left col-md-6 col-12 mb-2 breadcrumb-new">
          <h3 class="content-header-title mb-0 d-inline-block">加盟申请查询</h3>
          <div class="row breadcrumbs-top d-inline-block">
            <div class="breadcrumb-wrapper col-12">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <a href="#">后台管理</a>
                </li>
                <li class="breadcrumb-item">
                  <a href="#">加盟申请管理</a>
                </li>
              </ol>
            </div>
          </div>
        </div>
      </div>
      <div class="content-body">
        <!-- Column Chart -->
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
                <h4 class="card-title">加盟申请列表</h4>
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
                    <el-table :data="tableData" style="width: 100%" class="table-striped">
                      <el-table-column v-for="items in tableDataType" :prop="items.nameProp" :label="items.nameLable" width="auto"></el-table-column>
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
  name: 'JoinUs',
  data () {
    return {
      tableDataType: [],
      tableData: []
    }
  },
  mounted () {
    let _this = this
    _this.tableDataType = [
      {
        nameLable: 'ID',
        nameProp: 'id'
      },
      {
        nameLable: '联系人姓名',
        nameProp: 'name'
      },
      {
        nameLable: '联系电话',
        nameProp: 'telephone'
      },
      {
        nameLable: '备注',
        nameProp: 'comment'
      }
    ]
    this.axios.get('join/list').then(function (result) {
      if (result.status === 200) {
        _this.tableData = result.data
      }
    })
  },
  methods: {

  }
}
</script>
