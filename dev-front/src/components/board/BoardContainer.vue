<template>
  <div class="todo-list-container">
    <div class="insert-container">
      <form class="board-menue-container" @submit.prevent="addTodo">
        <input
          class="todo-input"
          type="text"
          v-model="todoInsert"
          placeholder="해야 할 일을 입력하세요."
        />
        <button class="add-btn" style="cursor: pointer">등록하기</button>
      </form>
    </div>
    <div class="todo-list">
      <div v-for="todo in todos" :key="todo.todoNo" style="width: 100%">
        <Todo
          :todo="todo"
          @toggleChange="toggleChange(todo.todoNo)"
          @toggleDelete="toggleDelete(todo.todoNo)"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, watchEffect } from "vue";

import Todo from "./TodoCard.vue";

import axiosInstance from "@/apis/config";

const todos = ref([]);

const todoInsert = ref("");

const todoChangeTrigger = ref(0);

let userNo;

const triggerTodoListRefresh = () => {
  todoChangeTrigger.value++; // Increment to trigger watchEffect
};

const getTodoList = async () => {
  try {
    const response = await axiosInstance.get(`/todo/list/${userNo}`);
    console.log("get todos success ! ", response);
    todos.value = response.data.data;
  } catch (error) {
    console.log("Error get todos:", error);
  }
};

onMounted(() => {
  userNo = sessionStorage.getItem("userNo");
  getTodoList();
});

watchEffect(() => {
  getTodoList();
  console.log(
    "Todo list refreshed because of change trigger:",
    todoChangeTrigger.value
  );
});

const addTodo = async () => {
  try {
    const response = await axiosInstance.post("/todo/create", {
      todoContent: todoInsert.value,
      todoCompleteYn: false,
      todoDeleteYn: false,
      userNo: userNo,
    });
    todoInsert.value = "";
    triggerTodoListRefresh();
    console.log(todoChangeTrigger.value);
    console.log("save todo success ! ", response.data.data);
  } catch (error) {
    console.log("Error save todo:", error);
  }
};

const toggleChange = async (todoNo) => {
  try {
    const response = await axiosInstance.post(`/todo/complete/${todoNo}`);
    console.log("completed todo success ! ", response.data.data);
    triggerTodoListRefresh();
    console.log(todoChangeTrigger.value);
  } catch (error) {
    console.log("Error completed todo:", error);
  }
};

const toggleDelete = async (todoNo) => {
  try {
    const response = await axiosInstance.post(`/todo/delete/${todoNo}`);
    console.log("delete todo success ! ", response.data.data);
    triggerTodoListRefresh();
    console.log(todoChangeTrigger.value);
  } catch (error) {
    console.log("Error delete todo:", error);
  }
};
</script>

<style>
.todo-list-container {
  margin: 20px 100px;
}
.checked {
  text-decoration: line-through;
  text-decoration-thickness: 2px;
}
.todo-list {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 40px;
}

.insert-container {
  display: flex;
  align-items: center;
  justify-content: flex-end;
}
.board-menue-container {
  display: flex;
  flex-direction: row;
  margin: 20px 0;
  width: 40%;
}

@media (max-width: 1080px) {
  .board-menue-container {
    width: 100%;
  }
}
select {
  font-size: 1.125rem;
  padding: 0.5rem;
  color: #707070;
  border: 3px solid #707070;
}
.todo-input {
  flex: 1;
  color: #707070;
  outline: none;
  border: 3px solid #707070;
  border-radius: 15px;
  padding: 0.5rem;
  font-size: 1.125rem;
  line-height: 1.5;
  margin: 0 10px;
  width: 50%;
  &::placeholder {
    color: #707070;
  }
}

.add-btn {
  background: white;
  color: #707070;
  outline: none;
  border: 3px solid #707070;
  font-size: 1.125rem;
  padding: 0.5rem;
  display: flex;
  align-items: center;
  border-radius: 15px;

  &:hover {
    background-color: #707070;
    color: white;
  }
}
</style>
