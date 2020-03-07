<template>
  <div class="app-content content">
    <div class="content-wrapper">
      <div class="content-header row">
        <div class="content-header-left col-md-6 col-12 mb-2 breadcrumb-new">
          <h3 class="content-header-title mb-0 d-inline-block">菜品管理</h3>
          <div class="row breadcrumbs-top d-inline-block">
            <div class="breadcrumb-wrapper col-12">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <a href="#">后台管理</a>
                </li>
                <li class="breadcrumb-item">
                  <a href="#">菜品管理</a>
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
                <h4 class="card-title">菜品列表</h4>
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
                    <button type="button" class="btn btn-primary" style="margin-bottom:20px" data-toggle="modal" data-target="#add-food-modal">添加菜品</button>
                  </div>
                  <div class="table-responsive">
                    <el-table :data="foodTableData" style="width: 100%" class="table-striped">
                      <el-table-column v-for="items in tableDataType" :key="items.nameLable" :prop="items.nameProp" :label="items.nameLable" width="auto"></el-table-column>
                      <el-table-column label="菜品图片">
                        <template slot-scope="scope">
                          <!-- <image :src="scope.row.Picture"></image> -->
                          <el-image :src="scope.row.Picture" :fit="fit" style="width: 50px; height: 50px">
                            <div slot="error" class="image-slot">
                              <span>无图</span>
                            </div>
                          </el-image>
                        </template>
                      </el-table-column>
                      <el-table-column fixed="right" align="center" label="操作" show-overflow-tooltip min-width="140">
                        <template slot-scope="scope">
                          <button type="button" class="btn btn-icon btn-pure" v-on:click="EditRow(scope.row,scope.$index);">
                            <i class="ft-edit"></i>
                          </button>
                          <button type="button" class="btn btn-icon btn-pure" v-on:click="DeleteFood(scope.row,scope.$index);">
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

      <div class="modal fade text-left" id="add-food-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title" id="myModalLabel1">添加菜品</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">×</span>
              </button>
            </div>
            <div class="modal-body">
              <select id="food-category" class="form-control" style="margin-top:20px;" placeholder="菜品分类">
                <option v-for="item in foodCategory" :key="item.Id" :value="item.Id">{{item.Name}}</option>
              </select>
              <input id="food-name" type="text" class="form-control" style="margin-top:20px;margin-bottom:20px;" placeholder="菜品名称" />
              <input id="food-price" type="number" class="form-control" style="margin-top:20px;margin-bottom:20px;" placeholder="菜品价格" />
              <input id="food-description" type="text" class="form-control" style="margin-top:20px;margin-bottom:20px;" placeholder="菜品描述" />
              <input id="food-image" type="file" class="form-control" style="margin-top:20px;margin-bottom:20px;" @change="Change($event)" placeholder="菜品图片" />
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-outline-primary" @click="AddFood">确定</button>
              <button type="button" class="btn grey btn-outline-secondary" data-dismiss="modal">关闭</button>
            </div>
          </div>
        </div>
      </div>

      <div class="modal fade text-left" id="edit-food-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" style="display: none;" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title" id="myModalLabel2">更新菜品</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">×</span>
              </button>
            </div>
            <div class="modal-body">
              <input id="food-id" type="hidden" />
              <select id="food-category-edit" class="form-control" style="margin-top:20px;" placeholder="菜品分类">
                <option v-for="item in foodCategory" :key="item.Id" :value="item.Id">{{item.Name}}</option>
              </select>
              <input id="food-name-edit" type="text" class="form-control" style="margin-top:20px;margin-bottom:20px;" placeholder="菜品名称" />
              <input id="food-price-edit" type="number" class="form-control" style="margin-top:20px;margin-bottom:20px;" placeholder="菜品价格" />
              <input id="food-description-edit" type="text" class="form-control" style="margin-top:20px;margin-bottom:20px;" placeholder="菜品描述" />
              <input id="food-image-edit" type="file" class="form-control" style="margin-top:20px;margin-bottom:20px;" @change="Change($event)" placeholder="菜品图片" />
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-outline-primary" @click="EditFood">确定</button>
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
  name: 'Food',
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
        nameProp: 'Id'
      },
      {
        nameLable: '菜品分类',
        nameProp: 'Category'
      },
      {
        nameLable: '菜品名称',
        nameProp: 'Name'
      },
      {
        nameLable: '菜品价格',
        nameProp: 'Price'
      },
      {
        nameLable: '菜品描述',
        nameProp: 'Description'
      }
    ]
    this.axios.get('api/foodcategories').then(function (result) {
      if (result.status === 200) {
        _this.foodCategory = result.data
        console.log(_this.foodCategory)
      }
    })
    this.GetFoodList()
  },
  methods: {
    AddFood: function () {
      let _this = this
      var postData = new FormData()
      postData.append('菜品图片', this.file)
      postData.append('菜品分类ID', $('#food-category').val())
      postData.append('菜品名称', $('#food-name').val())
      postData.append('菜品价格', $('#food-price').val())
      postData.append('菜品描述', $('#food-description').val())
      // let url = `api/food?菜品分类ID=${$('#food-category').val()}&菜品名称=${$('#food-name').val()}&菜品价格=${$('#food-price').val()}&菜品描述=${$('#food-description').val()}`
      let url = 'api/food'
      this.axios.post(url, postData).then(function (result) {
        console.log(result)
        if (result.status === 200) {
          _this.GetFoodList()
          $('#add-food-modal').modal('toggle')
        }
      })
    },
    EditFood: function () {
      let _this = this
      var postData = new FormData()
      postData.append('菜品图片', this.file)
      postData.append('菜品ID', $('#food-id').val())
      postData.append('菜品分类ID', $('#food-category-edit').val())
      postData.append('菜品名称', $('#food-name-edit').val())
      postData.append('菜品价格', $('#food-price-edit').val())
      postData.append('菜品描述', $('#food-description-edit').val())
      this.axios.put('api/food', postData).then(function (result) {
        if (result.status === 200) {
          _this.GetFoodList()
          $('#edit-food-modal').modal('toggle')
        }
      })
    },
    DeleteFood: function (row, index) {
      let _this = this
      this.axios
        .delete('api/food?id=' + row.Id)
        .then(function (result) {
          if (result.status === 200) {
            _this.GetFoodList()
          }
        })
    },
    EditRow: function (row, index) {
      console.log(row)
      $('#food-id').val(row.Id)
      $('#food-category-edit').val(row.CategoryId)
      $('#food-name-edit').val(row.Name)
      $('#food-price-edit').val(row.Price)
      $('#food-description-edit').val(row.Description)
      $('#edit-food-modal').modal('toggle')
    },
    GetFoodList: function () {
      let _this = this
      this.axios.get('api/foods').then(function (result) {
        if (result.status === 200) {
          _this.foodTableData = result.data.data
          _this.foodTableData.map(item => {
            let categoryItem = _this.foodCategory.find(i => i.Id === item.CategoryId)
            item.Category = categoryItem.Name
          })
        }
      })
    },
    Change: function (event) {
      this.file = event.target.files[0]
    }
  }
}
</script>
