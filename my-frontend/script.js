document.getElementById('registration-form').addEventListener('submit', function(event) {
    event.preventDefault();

    // Собираем данные из формы
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const username = document.getElementById('username').value;

    // Проверяем, что все поля заполнены
    if (!email || !password || !username) {
        displayErrorMessage('Пожалуйста, заполните все поля!');
        return;
    }

    // Отправляем запрос на регистрацию
    fetch('http://localhost:8080/api/users/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: email,
            password: password,
            username: username
        })
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Ошибка при регистрации');
        }
    })
    .then(data => {
        alert('Регистрация успешна! Пользователь ID: ' + data.id);
        document.getElementById('registration-form').reset(); // Очистить форму
    })
    .catch(error => {
        displayErrorMessage(error.message);
    });
});

function displayErrorMessage(message) {
    const errorMessageElement = document.getElementById('error-message');
    errorMessageElement.textContent = message;
}