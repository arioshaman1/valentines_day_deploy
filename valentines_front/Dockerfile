# Шаг 1: Используем официальный образ Node.js как базовый
FROM node:16 AS build-stage

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем package.json и package-lock.json
COPY package*.json ./

# Устанавливаем зависимости
RUN npm install

# Копируем остальные файлы проекта
COPY . .

# Строим проект
RUN npm run build

# Шаг 2: Используем легковесный сервер для запуска
FROM nginx:alpine

# Копируем билд в директорию, обслуживаемую nginx
COPY --from=build-stage /app/dist /usr/share/nginx/html

# Открываем порт 80
EXPOSE 80

# Запускаем nginx
CMD ["nginx", "-g", "daemon off;"]