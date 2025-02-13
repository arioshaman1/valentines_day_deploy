<template>
  <div class="dashboard">
    <h2>Добро пожаловать, {{ currentUser }}</h2>

    <h3>Создать валентинку:</h3>
    <form @submit.prevent="sendValentine" class="valentine-form">
      <div class="form-group">
        <label for="recipient">Получатель:</label>
        <select v-model="selectedRecipient" id="recipient" required>
          <option v-for="user in users" :key="user.id" :value="user.username">
            {{ user.username }}
          </option>
        </select>
      </div>
      <div class="form-group">
        <label for="message">Сообщение:</label>
        <textarea v-model="message" id="message" required></textarea>
      </div>
      <button type="submit">Отправить валентинку</button>
    </form>

    <h3>Список пользователей:</h3>
    <ul class="user-list">
      <li v-for="user in users" :key="user.id">{{ user.username }}</li>
    </ul>

    <p v-if="error" class="error">{{ error }}</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const currentUser = ref('');
const users = ref([]);
const selectedRecipient = ref('');
const message = ref('');
const error = ref('');

// Загружаем данные при загрузке компонента
onMounted(async () => {
  try {
    // Получаем текущего пользователя, используя токен из localStorage
    const token = localStorage.getItem('jwt');
    if (token) {
      const userResponse = await axios.get('http://localhost:8080/api/users/current', {
        headers: {
          Authorization: `Bearer ${token}` // Добавляем токен в заголовки запроса
        }
      });
      currentUser.value = userResponse.data.username;

      // Загружаем список пользователей
      const usersResponse = await axios.get('http://localhost:8080/api/users', {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      users.value = usersResponse.data;
    } else {
      error.value = 'Необходима авторизация';
    }
  } catch (err) {
    error.value = 'Ошибка при загрузке данных пользователей';
  }
});
// Функция для отправки валентинки
const sendValentine = async () => {
  try {
    const token = localStorage.getItem('jwt');
    if (!token) {
      error.value = 'Необходима авторизация';
      return;
    }

    await axios.post('http://localhost:8080/api/valentines', {
      sender: currentUser.value,
      recipient: selectedRecipient.value,
      message: message.value
    }, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    message.value = '';
    selectedRecipient.value = '';
    alert('Валентинка отправлена!');
  } catch (err) {
    error.value = 'Ошибка при отправке валентинки';
  }
};
</script>

<style scoped>
.dashboard {
  max-width: 500px;
  margin: 30px auto;
  padding: 20px;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
  text-align: center;
}

h2, h3 {
  margin-bottom: 15px;
  color: #6d1c7e;
}

.valentine-form .form-group {
  margin-bottom: 15px;
  text-align: left;
}

label {
  display: block;
  margin-bottom: 5px;
  color: #333;
}

input, select, textarea {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 6px;
}

button {
  padding: 10px 20px;
  background-color: #d63384;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #b02168;
}

.user-list {
  list-style: none;
  padding: 0;
  margin-top: 20px;
}

.user-list li {
  margin: 5px 0;
  background: #f4e1f5;
  padding: 5px;
  border-radius: 5px;
}

.error {
  color: red;
  margin-top: 10px;
}
</style>