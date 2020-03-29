<template>
  <div class="app-content content">
    <div class="content-wrapper">
      <div class="content-header row">
        <div class="content-header-left col-md-6 col-12 mb-2 breadcrumb-new">
          <h3 class="content-header-title mb-0 d-inline-block">船屋故事</h3>
          <div class="row breadcrumbs-top d-inline-block">
            <div class="breadcrumb-wrapper col-12">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <a href="#">后台管理</a>
                </li>
                <li class="breadcrumb-item">
                  <a href="#">船屋故事</a>
                </li>
              </ol>
            </div>
          </div>
        </div>
      </div>
      <div class="content-body">
        <div class="story-container">
          <div class="story-row story-title">
            <div class="story-label">页面标题</div>
            <input class="story-title-input" v-model="pageTitle" />
          </div>
          <div class="story-row story-content">
            <div class="story-label">页面内容</div>
            <div class="story-body">
              <quill-editor
                ref="myQuillEditor"
                v-model="pageBody"
                :options="editorOption"
              />
            </div>
          </div>
          <div class="story-row">
            <button class="btn btn-primary" v-on:click="updateIntro">保存 / 更新</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import { quillEditor } from 'vue-quill-editor'

export default {
  components: {
    quillEditor
  },
  data () {
    return {
      pageId: 'intro',
      pageTitle: '',
      pageBody: '',
      editorOption: {
        scrollingContainer: '.story-body'
      }
    }
  },
  mounted () {
    let url = '/api/intro/intro_page?page_id=' + this.pageId
    this.axios.get(url).then((result) => {
      if (result.status === 200) {
        const data = result.data
        this.pageTitle = data.page_title
        this.pageBody = data.page_values.text
      }
    })
  },
  methods: {
    updateIntro () {
      let postData = {
        page_id: this.pageId,
        page_title: this.pageTitle,
        page_values: {
          text: this.pageBody
        }
      }
      this.axios.post('/api/intro/intro_page', postData).then((result) => {
        if (result.status === 200) {
          this.$message({
            message: '更新成功',
            type: 'success'
          })
        }
      })
    }
  }
}
</script>
<style>
.story-container {
  margin: 12px 32px;
}

.story-row {
  width: 100%;
  margin-top: 24px;
}

.story-label {
  font-size: 16px;
  font-weight: bold;
}

.story-title-input {
  width: 520px;
  height: 40px;
  margin-top: 12px;
  padding: 8px;
}

.story-body {
  margin-top: 12px;
}

.story-body .ql-editor {
  height: 600px;
}
</style>
