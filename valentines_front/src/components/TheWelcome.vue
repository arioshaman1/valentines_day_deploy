<template>
  <div class="welcome-container">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <h1>Happy Valentine's Day</h1>
    <button @click="goToHomePage">Перейти</button>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

const goToHomePage = () => {
  router.push('/login'); // Перенаправление на страницу логина
};
</script>

<style scoped>
.welcome-container {
  display: flex;
  flex-direction: column;
  justify-content: center; /* Центрирование по вертикали */
  align-items: center; /* Центрирование по горизонтали */
  height: 100vh; /* Растягиваем на весь экран */
  text-align: center; /* Центрирование текста */
}

.welcome-image {
  width: 200px; /* Задаем фиксированную ширину для изображения */
  height: auto; /* Пропорциональное изменение высоты */
  margin: 20px 0; /* Отступы сверху и снизу для изображения */
}

button {
  margin-top: 20px;
  padding: 10px 20px;
  color: white;
  border: none;
  cursor: pointer;
  font-size: 16px;
}

button:hover {
  background-color: #8b4c93;
}
h1 {
  font-size: 80px;
  font-family: Roboto;
}
</style>