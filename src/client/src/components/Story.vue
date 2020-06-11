<template>
  <form id="story" name='form' method="POST" action="/">
    <div id="content" class="p-0">
      <div id="features" class="container py-4 py-lg-6">
        <hr class="hr-lg mt-0 mb-3 w-10 mx-auto hr-primary" />
        <h2 class="text-center text-uppercase font-weight-bold my-0">
          {{ pageTitle }}
        </h2>
        <div class="main">
          <div v-html="pageBody"/>
        </div>
      </div>
    </div>
  </form>
</template>
<script>
export default {
  data () {
    return {
      pageTitle: '',
      pageBody: ''
    }
  },
  mounted: function () {
    this.axios.get('/api/intro/intro_page?page_id=intro').then((result) => {
      if (result.status === 200) {
        const data = result.data
        this.pageTitle = data.page_title
        this.pageBody = data.page_values.text
      }
    })
  }
}
</script>
<style scoped>
.main{
  margin:auto;
}
</style>
