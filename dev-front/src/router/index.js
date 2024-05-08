import { createRouter, createWebHistory } from "vue-router";
import Main from "@/views/mainPage.vue";
import Signin from "@/views/signinPage.vue";
import Signup from "@/views/signupPage.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/main",
      name: "main",
      component: Main,
    },
    {
      path: "/",
      name: "signin",
      component: Signin,
    },
    {
      path: "/sign-up",
      name: "signup",
      component: Signup,
    },
  ],
});

export default router;
