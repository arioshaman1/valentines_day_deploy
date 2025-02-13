<template>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
  <div class="centered-container">
    <div class="register-container">
      <h2>Регистрация</h2>

      <form @submit.prevent="register">
        <div class="form-group">
          <label for="username">Имя пользователя</label>
          <input type="text" v-model="username" id="username" required />
        </div>

        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" v-model="email" id="email" required />
        </div>

        <div class="form-group">
          <label for="password">Пароль</label>
          <input type="password" v-model="password" id="password" required />
        </div>

        <div class="form-group">
          <label for="confirmPassword">Подтвердите пароль</label>
          <input type="password" v-model="confirmPassword" id="confirmPassword" required />
        </div>

        <button type="submit" class="btn">Зарегистрироваться</button>
      </form>

      <p v-if="error" class="error">{{ error }}</p>

      <div class="login-link">
        <button @click="goToLogin">Уже есть аккаунт? Войти</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

const username = ref('');
const email = ref('');
const password = ref('');
const confirmPassword = ref('');
const error = ref('');
const router = useRouter();

const register = async () => {
  if (password.value !== confirmPassword.value) {
    error.value = 'Пароли не совпадают!';
    return;
  }

  try {
    const response = await axios.post('http://localhost:8080/api/users/register', {
      username: username.value,
      email: email.value,
      password: password.value,
    });

    if (response.status === 201) {
      router.push('/dashboard');
    }
  } catch (err) {
    error.value = err.response?.data || 'Ошибка регистрации.';
  }
};

const goToLogin = () => {
  router.push('/login');
};
</script>

<style scoped>
.centered-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.register-container {
  width: 400px;
  padding: 20px;
  border: 1px solid #ccc;
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

h2 {
  font-family: Roboto;
}

.form-group {
  margin-bottom: 15px;
  text-align: left;
  margin-right: 20px;
  font-family: Roboto;
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
  background-color: #4caf50;
  color: white;
  border: none;
  cursor: pointer;
  font-family: Roboto;
}

button:hover {
  background-color: #45a049;
}

.error {
  color: red;
  margin-top: 10px;
}

.login-link {
  margin-top: 15px;
}

.login-link button {
  background-color: #8b4c93;
}

.login-link button:hover {
  background-color: #7a3c7f;
}
</style>