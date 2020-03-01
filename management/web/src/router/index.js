import Vue from 'vue'
import Router from 'vue-router'
import Dashboard from '@/components/Dashboard'
import FoodCategory from '@/components/FoodCategory'
import JoinUs from '@/components/JoinUs'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Dashboard',
      component: Dashboard
    },
    {
      path: '/FoodCategory',
      name: 'FoodCategory',
      component: FoodCategory
    },
    {
      path: '/JoinUs',
      name: 'JoinUs',
      component: JoinUs
    }
  ]
})
