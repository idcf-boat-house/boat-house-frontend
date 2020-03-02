import Vue from 'vue'
import Router from 'vue-router'
import Products from '@/components/Products'
import Login from '@/components/Login'
import Franchisee from '@/components/Franchisee'

Vue.use(Router)

export default new Router({
  routes: [
   {
      path: '/',
      name: 'Products',
      component: Products
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/franchisee',
      name: 'Franchisee',
      component: Franchisee
    }
  ]
})
