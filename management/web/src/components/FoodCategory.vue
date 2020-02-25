<template>
  <div class="app-content content">
    <div class="content-wrapper">
      <div class="content-header row">
        <div class="content-header-left col-md-6 col-12 mb-2 breadcrumb-new">
          <h3 class="content-header-title mb-0 d-inline-block">菜品分类</h3>
          <div class="row breadcrumbs-top d-inline-block">
            <div class="breadcrumb-wrapper col-12">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <a href="#">后台管理</a>
                </li>
                <li class="breadcrumb-item">
                  <a href="#">菜品分类管理</a>
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
                <h4 class="card-title">菜品分类列表</h4>
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
                    <button
                      type="button"
                      class="btn btn-primary"
                      style="margin-bottom:20px"
                      data-toggle="modal"
                      data-target="#add-modal"
                    >添加菜品分类</button>
                  </div>
                  <div class="table-responsive">
                    <el-table :data="tableData" style="width: 100%" class="table-striped">
                      <el-table-column
                        v-for="items in tableDataType"
                        :prop="items.nameProp"
                        :label="items.nameLable"
                        width="auto"
                      ></el-table-column>
                      <el-table-column
                        fixed="right"
                        align="center"
                        label="操作"
                        show-overflow-tooltip
                        min-width="140"
                      >
                        <template slot-scope="scope">
                          <button
                            type="button"
                            class="btn btn-icon btn-pure"
                            v-on:click="EditRow(scope.row,scope.$index);"
                          >
                            <i class="ft-edit"></i>
                          </button>
                          <button
                            type="button"
                            class="btn btn-icon btn-pure"
                            v-on:click="DeleteFoodCategory(scope.row,scope.$index);"
                          >
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

      <div
        class="modal fade text-left"
        id="add-modal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="myModalLabel1"
        style="display: none;"
        aria-hidden="true"
      >
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title" id="myModalLabel1">添加菜品分类</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">×</span>
              </button>
            </div>
            <div class="modal-body">
              <input
                id="food-category-name"
                type="text"
                class="form-control"
                style="margin-top:20px;"
                placeholder="菜品分类名称"
              />
              <input
                id="food-category-description"
                type="text"
                class="form-control"
                style="margin-top:20px;margin-bottom:20px;"
                placeholder="菜品分类描述"
              />
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-outline-primary" @click="AddFoodCategory">确定</button>
              <button type="button" class="btn grey btn-outline-secondary" data-dismiss="modal">关闭</button>
            </div>
          </div>
        </div>
      </div>

      <div
        class="modal fade text-left"
        id="edit-modal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="myModalLabel2"
        style="display: none;"
        aria-hidden="true"
      >
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title" id="myModalLabel2">更新菜品分类</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">×</span>
              </button>
            </div>
            <div class="modal-body">
              <input id="food-category-id" type="hidden" />
              <input
                id="food-category-name-edit"
                type="text"
                class="form-control"
                style="margin-top:20px;"
                placeholder="菜品分类名称"
              />
              <input
                id="food-category-description-edit"
                type="text"
                class="form-control"
                style="margin-top:20px;margin-bottom:20px;"
                placeholder="菜品分类描述"
              />
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-outline-primary" @click="EditFoodCategory">确定</button>
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
  name: "FoodCategory",
  data() {
    return {
      tableDataType: [],
      tableData: []
    };
  },
  mounted() {
    let _this = this;
    _this.tableDataType = [
      {
        nameLable: "ID",
        nameProp: "Id"
      },
      {
        nameLable: "菜品分类名称",
        nameProp: "Name"
      },
      {
        nameLable: "菜品分类描述",
        nameProp: "Description"
      }
    ];
    this.axios.get("api/foodcategories").then(function(result) {
      if (result.status === 200) {
        _this.tableData = result.data;
      }
    });
  },
  methods: {
    AddFoodCategory: function() {
      let _this = this;
      let postData = {
        name: $("#food-category-name").val(),
        description: $("#food-category-description").val()
      };
      this.axios.post("api/foodcategory", postData).then(function(result) {
        if (result.status === 200) {
          _this.axios.get("api/foodcategories").then(function(result) {
            if (result.status === 200) {
              _this.tableData = result.data;
            }
          });
          $("#add-modal").modal("toggle");
        }
      });
    },
    EditFoodCategory: function() {
      let _this = this;
      let putData = {
        id: $("#food-category-id").val(),
        name: $("#food-category-name-edit").val(),
        description: $("#food-category-description-edit").val()
      };
      this.axios.put("api/foodcategory", putData).then(function(result) {
        if (result.status === 200) {
          _this.axios.get("api/foodcategories").then(function(result) {
            if (result.status === 200) {
              _this.tableData = result.data;
            }
          });
          $("#edit-modal").modal("toggle");
        }
      });
    },
    DeleteFoodCategory: function(row, index) {
      let _this = this;
      this.axios
        .delete("api/foodcategory?id=" + row.Id)
        .then(function(result) {
          if (result.status === 200) {
            _this.axios.get("api/foodcategories").then(function(result) {
              if (result.status === 200) {
                _this.tableData = result.data;
              }
            });
          }
        });
    },
    EditRow: function(row, index) {
      $("#food-category-id").val(row.Id);
      $("#food-category-name-edit").val(row.Name);
      $("#food-category-description-edit").val(row.Description);
      $("#edit-modal").modal("toggle");
    }
  }
};
</script>