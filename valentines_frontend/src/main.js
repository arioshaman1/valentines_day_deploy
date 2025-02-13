import './assets/main.css'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

// Создание приложения Vue с роутером
createApp(App)
    .use(router) // Подключаем роутер
    .mount('#app')