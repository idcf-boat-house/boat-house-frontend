import Vue from "vue";
import Router from "vue-router";
import Dashboard from "@/components/Dashboard";
import FoodCategory from "@/components/FoodCategory";
import Food from "@/components/Food";
import UserMgt from "@/components/UserMgt";
import JoinUs from "@/components/JoinUs";
import Orders from "@/components/Orders";
import HouseStory from "@/components/HouseStory";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "Dashboard",
      component: Dashboard
    },
    {
      path: "/FoodCategory",
      name: "FoodCategory",
      component: FoodCategory
    },
    {
      path: "/Food",
      name: "Food",
      component: Food
    },
    {
      path: "/JoinUs",
      name: "JoinUs",
      component: JoinUs
    },
    {
      path: "/UserMgt",
      name: "UserMgt",
      component: UserMgt
    },
    {
      path: "/Orders",
      name: "Orders",
      component: Orders
    },
    {
      path: "/HouseStory",
      name: "HouseStory",
      component: HouseStory
    }
  ]
});
