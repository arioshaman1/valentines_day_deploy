<template>
  <div class="app">
    <div class="background-image"></div> <!-- Размытие фона -->
    <router-view /> <!-- Контент страниц будет выводиться здесь -->
  </div>
</template>

<script setup>
// Этот файл служит для загрузки глобальных стилей
</script>

<style>
.app {
  position: relative;
  height: 100vh;
  width: 100%;
}

.background-image {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url('@/assets/background.jpg'); /* Путь к изображению */
  background-size: cover;
  background-position: center;
  filter: blur(10px); /* Эффект размытия */
  z-index: -1; /* Слой фона будет находиться позади контента */
}

router-view {
  position: relative;
  z-index: 1; /* Контент всех страниц будет сверху */
}
</style>