import Vue from "vue";
import Router from "vue-router";
import Products from "@/components/Products";
import Story from "@/components/Story";
import Login from "@/components/Login";
import Franchisee from "@/components/Franchisee";
import Food from "@/components/Food";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "Products",
      component: Products
    },
    {
      path: "/story",
      name: "Story",
      component: Story
    },
    {
      path: "/login",
      name: "Login",
      component: Login
    },
    {
      path: "/franchisee",
      name: "Franchisee",
      component: Franchisee
    },
    {
      path: "/food",
      name: "Food",
      component: Food
    }
  ]
});
