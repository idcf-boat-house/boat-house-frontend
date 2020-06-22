<template>
  <div class="app-content content">
    <div class="content-wrapper">
      <div class="content-header row">
        <div class="content-header-left col-md-6 col-12 mb-2 breadcrumb-new">
          <h3 class="content-header-title mb-0 d-inline-block">用户管理</h3>
          <div class="row breadcrumbs-top d-inline-block">
            <div class="breadcrumb-wrapper col-12">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <a href="#">后台管理</a>
                </li>
                <li class="breadcrumb-item">
                  <a href="#">用户管理</a>
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
                <h4 class="card-title">用户列表</h4>
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
                  <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-primary" style="margin-bottom:20px" data-toggle="modal" data-target="#add-user-modal">添加用户</button>
                  </div>
                  <div class="table-responsive">
                    <el-table :data="foodTableData" style="width: 100%" class="table-striped">
                      <el-table-column v-for="items in tableDataType" :key="items.nameLable" :prop="items.nameProp" :label="items.nameLable" width="auto"></el-table-column>
                      <el-table-column fixed="right" align="center" label="操作" show-overflow-tooltip min-width="140">
                        <template slot-scope="scope">
                          <button type="button" class="btn btn-icon btn-pure" v-on:click="EditRow(scope.row,scope.$index);">
                            <i class="ft-edit"></i>
                          </button>
                          <button type="button" class="btn btn-icon btn-pure" v-on:click="DeleteRow(scope.row,scope.$index);">
                            <i class="ft-trash-2"></i>
                          </button>
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

      <div class="modal fade text-left" id="add-user-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title" id="myModalLabel1">添加用户</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">×</span>
              </button>
            </div>
            <div class="modal-body">
              <input id="account" type="text" class="form-control" style="margin-top:20px;margin-bottom:20px;" placeholder="用户账号" />
              <input id="age" type="number" class="form-control" style="margin-top:20px;margin-bottom:20px;" placeholder="用户年龄" />
              <input id="email" type="text" class="form-control" style="margin-top:20px;margin-bottom:20px;" placeholder="用户邮箱" />
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-outline-primary" @click="AddFood">确定</button>
              <button type="button" class="btn grey btn-outline-secondary" data-dismiss="modal">关闭</button>
            </div>
          </div>
        </div>
      </div>

      <div class="modal fade text-left" id="edit-user-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title" id="myModalLabel2">更新用户</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">×</span>
              </button>
            </div>
            <div class="modal-body">
              <input id="id-edit" type="hidden" />
              <input id="account-edit" type="text" class="form-control" style="margin-top:20px;margin-bottom:20px;" placeholder="用户名称" />
              <input id="age-edit" type="number" class="form-control" style="margin-top:20px;margin-bottom:20px;" placeholder="年龄" />
              <input id="email-edit" type="text" class="form-control" style="margin-top:20px;margin-bottom:20px;" placeholder="邮箱" />
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-outline-primary" @click="EditUser">确定</button>
              <button type="button" class="btn grey btn-outline-secondary" data-dismiss="modal">关闭</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'UserMgt',
  data () {
    return {
      tableDataType: [],
      foodCategory: [],
      foodDataType: [],
      foodTableData: [],
      file: Object
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
        nameLable: '用户名称',
        nameProp: 'account'
      },
      {
        nameLable: '年龄',
        nameProp: 'age'
      },
      {
        nameLable: '邮箱',
        nameProp: 'email'
      }
    ]

    this.GetUserList()
  },
  methods: {
    AddFood: function () {
      let _this = this
      var postData = {
        account: $('#account').val(),
        age: $('#age').val(),
        email: $('#email').val()
      }
      let url = 'api/user'
      this.axios.post(url, postData).then(function (result) {
        console.log(result)
        if (result.status === 200) {
          _this.GetUserList()
          $('#add-user-modal').modal('toggle')
        }
      })
    },
    EditUser: function () {
      let _this = this
      var postData = {
        id: $('#id-edit').val(),
        account: $('#account-edit').val(),
        age: $('#age-edit').val(),
        email: $('#email-edit').val()
      }
      this.axios.put('api/user', postData).then(function (result) {
        if (result.status === 200) {
          _this.GetUserList()
          $('#edit-user-modal').modal('toggle')
        }
      })
    },
    DeleteRow: function (row, index) {
      let _this = this
      this.axios
        .delete('api/user?id=' + row.id)
        .then(function (result) {
          if (result.status === 200) {
            _this.GetUserList()
          }
        })
    },
    EditRow: function (row, index) {
      console.log(row)
      $('#id-edit').val(row.id)
      $('#account-edit').val(row.account)
      $('#age-edit').val(row.age)
      $('#email-edit').val(row.email)
      $('#edit-user-modal').modal('toggle')
    },
    GetUserList: function () {
      let _this = this
      this.axios.get('api/users').then(function (result) {
        if (result.status === 200) {
          _this.foodTableData = result.data.data
        }
      })
    },
    Change: function (event) {
      this.file = event.target.files[0]
    }
  }
}
</script>
