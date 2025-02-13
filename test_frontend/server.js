const express = require('express');
const path = require('path');

const app = express();
const port = 3000;

// Указываем папку для статических файлов (HTML, CSS, JS)
app.use(express.static(path.join(__dirname, 'public')));

// Запуск сервера
app.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});