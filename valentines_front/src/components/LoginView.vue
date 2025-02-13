<template>
  <div class="centered-container">
    <div class="login-container">
      <h2>Вход</h2>

      <form @submit.prevent="login">
        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" v-model="email" id="email" required />
        </div>

        <div class="form-group">
          <label for="password">Пароль</label>
          <input type="password" v-model="password" id="password" required />
        </div>

        <button type="submit" class="btn">Войти</button>
      </form>

      <p v-if="error" class="error">{{ error }}</p>

      <!-- Кнопка для перехода на страницу регистрации -->
      <div class="register-link">
        <button @click="goToRegister">Регистрация</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

const email = ref('');
const password = ref('');
const error = ref('');
const router = useRouter();

const login = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api/users/login', {
      email: email.value,
      password: password.value,
    });

    if (response.status === 200) {
      router.push('/dashboard');
    }
  } catch (err) {
    error.value = 'Login failed. Please check your email and password.';
  }
};

const goToRegister = () => {
  router.push('/reg');
};
</script>

<style scoped>
.centered-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.login-container {
  width: 400px;
  padding: 20px;
  border: 1px solid #ccc;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

h2 {
  font-family: 'Roboto', sans-serif;
  text-align: center;
  margin-right: 10px;
}

.form-group {
  margin-bottom: 15px;
  font-family: 'Roboto', sans-serif;
  margin-right: 20px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input {
  width: 100%;
  padding: 8px;
  margin: 5px 0;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #8b4c93;
  color: white;
  border: none;
  cursor: pointer;
}

button:hover {
  background-color: #7a3c7f;
}

.error {
  color: red;
  text-align: center;
}

.register-link {
  margin-top: 10px;
  text-align: center;
  margin-top: 10px;
}

.register-link button {
  background-color: #4caf50;
}

.register-link button:hover {
  background-color: #45a049;
}
</style>