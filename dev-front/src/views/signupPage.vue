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
    <div class="signup-box">
      <div class="input-group">
        <label for="name">이름</label>
        <input class="input-field" type="text" v-model="name" />
      </div>
      <div class="input-group">
        <label for="id">아이디</label>
        <input class="input-field" type="text" v-model="id" />
      </div>
      <div class="input-group">
        <label for="password">비밀번호</label>
        <input class="input-field" type="password" v-model="password" />
      </div>
      <button class="signup-button" @click="signUp">회원가입</button>
      <router-link class="cancel-button" to="/">
        <button class="cancel-button">취소</button>
      </router-link>
    </div>
  </div>
</template>

<script setup>
import axiosInstance from "@/apis/config";
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const name = ref("");
const id = ref("");
const password = ref("");

const signUp = async () => {
  try {
    if (!name.value.trim() || !id.value.trim() || !password.value.trim()) {
      alert("이름, 아이디, 비밀번호를 모두 입력해주세요!");
      return;
    }

    const response = await axiosInstance.post("/sign-up", {
      userName: name.value,
      userId: id.value,
      userPassword: password.value,
    });

    if (response.data.status == "success") {
      alert("회원가입 성공! 로그인 페이지로 이동합니다.");
    } else {
      alert("회원가입에 실패하였습니다. 다시 시도해주세요.");
    }

    console.log("회원가입 성공", response.data);
    await router.push("/");
  } catch (error) {
    console.error(error);
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

input[type="id"],
input[type="password"] {
  background-color: #d9d9d9;
  border: none;
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

.signup-box {
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
}

.input-group {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 10px 0;
}

.signup-box input {
  background-color: #d9d9d9;
  border: none;
  width: 200px;
  height: 50px;
  margin: 10px 0;
  padding: 10px;
  width: 80%;
}

.signup-button {
  background: none;
  border: none;
  cursor: pointer;
  font-size: 20px;
  margin-top: 40px;
  display: block;
  margin: 0 auto;
  margin-top: 40px;
}

.cancel-button {
  background: none;
  border: none;
  cursor: pointer;
  font-size: 20px;
  display: block;
  margin: 0 auto;
  margin-top: 10px;
  text-decoration: none;
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
