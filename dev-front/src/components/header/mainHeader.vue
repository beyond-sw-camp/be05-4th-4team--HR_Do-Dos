<template>
  <nav class="nav-container">
    <div class="logo">
      <div>{{ title }}</div>
    </div>
    <div class="menu">
      <div class="logout-btn" style="cursor: pointer" @click="signOut">
        로그아웃
      </div>
    </div>
  </nav>
</template>

<script setup>
import axiosInstance from "@/apis/config";
import { onMounted } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

let userName = sessionStorage.getItem("userName");
const title = `${userName}'s todo-list`;

onMounted(() => {
  let token = sessionStorage.getItem("userNo");
  if (token === null) {
    router.push("/");
  }
});

const signOut = async () => {
  try {
    const response = await axiosInstance.post("/sign-out");
    sessionStorage.removeItem("userNo");
    console.log("signout success ! ", response);
    await router.push("/");
  } catch (error) {
    console.log("Error signout:", error);
  }
};
</script>

<style scoped>
.nav-container {
  background-color: white;
  width: 100%;
  height: 80px;
  display: flex;
  align-items: center;
  position: relative;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.logo {
  font-family: "Do Hyeon", sans-serif;
  font-size: 40px;
  font-weight: bolder;
  margin: 0 auto;
  display: flex;
  flex-direction: row;
}
/* .logo > div {
  font-family: "Bangers", sans-serif;
} */

.menu {
  position: absolute;
  right: 5%;
  display: flex;
}

.menu div {
  font-size: 15px;
  font-weight: 600;
}
</style>
