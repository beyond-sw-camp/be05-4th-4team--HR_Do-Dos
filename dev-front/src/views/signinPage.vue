<template>
  <div class="container">
    <div class="preview-box">
      <h1>HR Do-Dos</h1>
      <div
        v-for="item in [
          '해야 할 일을 한눈에!',
          '쉽고 간편한 사용방법',
          '로그인 후 HR Do-Dos를 사용해 보세요!',
        ]"
        :key="item"
        class="list-item"
      >
        <input checked type="checkbox" />
        <span>{{ item }}</span>
        <span class="delete-button">&times;</span>
      </div>
    </div>
    <form class="signIn-box" @submit.prevent="signIn">
      <input
        v-model="loginId"
        placeholder="아이디를 입력해 주세요."
        type="text"
      />
      <input
        v-model="loginPassword"
        placeholder="비밀번호를 입력해 주세요."
        type="password"
      />
      <button class="signIn-button">로그인</button>
      <router-link class="signup-button" to="/sign-up">
        <button class="signup-button">회원가입</button>
      </router-link>
    </form>
  </div>
</template>

<script setup>
import axiosInstance from "@/apis/config";
import { ref } from "vue";
import { useRouter } from "vue-router";

const loginId = ref("");
const loginPassword = ref("");

const router = useRouter();

let token = sessionStorage.getItem("userNo");

if (token) {
  router.push("/main");
}

const signIn = async () => {
  try {
    if (!loginId.value.trim() || !loginPassword.value.trim()) {
      alert("아이디, 비밀번호를 올바르게 입력해 주세요!");
      return;
    }

    if (sessionStorage.getItem("userNo")) {
      sessionStorage.removeItem("userNo");
    }

    console.log(loginId.value, "   ", loginPassword.value);

    const response = await axiosInstance.post("/sign-in", {
      userId: loginId.value,
      userPassword: loginPassword.value,
    });

    if (response.data.data === null) {
      alert("아이디 또는 비밀번호가 일치하지 않습니다.");
      return;
    } else {
      alert("로그인 성공!");
      sessionStorage.setItem("userNo", response.data.data.userNo);
      sessionStorage.setItem("userName", response.data.data.userName);
    }

    console.log("signIn success ! ", response);

    await router.push("/main");
  } catch (error) {
    console.log("[Error] : ", error);

    if (error.code === "ERR_BAD_REQUEST") {
      alert("아이디 또는 비밀번호가 일치하지 않습니다.");
    }
  }
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: row;
  height: 100vh;
}

h1 {
  text-align: center;
  position: relative;
  top: -70px;
  font-family: "Do Hyeon", sans-serif;
  font-size: 60px;
}

.preview-box {
  margin: auto;
  background-color: white;
  position: fixed;
  left: 180px;
  top: 100px;
  padding: 20px;
  width: 650px;
  height: 800px;
  border: black 2px solid;
  border-radius: 30px;
  display: flex;
  justify-content: center;
  flex-direction: column;
}

.list-item {
  background-color: white;
  border-radius: 15px;
  padding: 20px 10px;
  border: 3px solid #000aff;
  margin: 25px 0;
  display: flex;
  justify-content: space-between;
}

.list-item input[type="checkbox"] {
  width: 25px;
  height: 25px;
  pointer-events: none;
}

.delete-button {
  cursor: pointer;
  width: 25px;
  height: 25px;
  pointer-events: none;
  object-fit: contain;
}

.list-item span {
  font-size: 20px;
  text-align: left;
}

.signIn-box {
  margin: auto;
  background-color: white;
  right: 300px;
  top: 250px;
  padding: 20px;
  width: 430px;
  height: 500px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
}

.signIn-box input {
  background-color: #d9d9d9;
  border: none;
  width: 320px;
  height: 50px;
  padding: 10px;
  font-size: 20px;
  margin: 10px 10px 20px;
}

.signIn-button {
  background: none;
  border: none;
  cursor: pointer;
  font-size: 20px;
  margin-top: 40px;
  margin-bottom: 20px;
}

.signup-button {
  background: none;
  border: none;
  cursor: pointer;
  font-size: 20px;
}

@media (max-width: 2560px) {
  .signIn-box {
    position: static;
    right: auto;
    top: auto;
  }

  .preview-box {
    position: static;
    left: auto;
    top: auto;
  }
}
</style>
