<template>
  <div class="todo-container">
    <input
      type="checkbox"
      class="form-check-input"
      :checked="todo.todoCompleteYn"
      @click.stop
      @change="toggleChange"
      style="cursor: pointer"
    />

    <div class="form-check-label" :class="{ checked: todo.todoCompleteYn }">
      {{ todo.todoContent }}
    </div>

    <img
      class="delete-btn"
      @click="toggleDelete"
      style="cursor: pointer"
      src="@/assets/circle-substract.png"
    />
  </div>
</template>

<script setup>
import { defineProps, defineEmits } from "vue";

const props = defineProps({
  todo: {
    type: Object,
  },
});

const emit = defineEmits(["toggleChange", "toggleDelete"]);

const toggleChange = (todoNo) => {
  emit("toggleChange", todoNo);
};

const toggleDelete = (todoNo) => {
  emit("toggleDelete", todoNo);
};
</script>

<style>
.todo-container {
  display: flex;
  align-items: center;
  background-color: white;
  margin-bottom: 40px;
  width: 100%;
  height: 90px;
  border-radius: 15px;
  position: relative;
}
.form-check-input {
  width: 40px;
  height: 40px;
  margin: 0 20px;
  margin-right: 40px;
}
.delete-btn {
  position: absolute;
  right: 27px;
}
.form-check-label {
  font-size: 1.125rem;
  font-weight: 600;
}
</style>
