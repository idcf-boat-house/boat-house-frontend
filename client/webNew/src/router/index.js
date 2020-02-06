import Vue from 'vue'
import Router from 'vue-router'
import Products from '@/components/Products'
import Stores from '@/components/Stores'
import Login from '@/components/Login'

Vue.use(Router)

export default new Router({
  routes: [
   {
      path: '/',
      name: 'Products',
      component: Products
    },
    {
      path: '/stores',
      name: 'Stores',
      component: Stores
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})
