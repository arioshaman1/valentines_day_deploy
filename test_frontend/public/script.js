document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const userData = {
        email: email,
        password: password
    };

    fetch('http://localhost:8080/api/users/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    })
    .then(response => {
        if (response.status === 200) {
            return response.text();
        } else {
            throw new Error('Invalid email or password');
        }
    })
    .then(data => {
        document.getElementById('message').style.color = 'green';
        document.getElementById('message').innerHTML = data;
    })
    .catch(error => {
        document.getElementById('message').style.color = 'red';
        document.getElementById('message').innerHTML = error.message;
    });
});