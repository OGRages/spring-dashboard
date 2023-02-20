function submitForm(data, url, successUrl, response) {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 201) {
                window.location.href = successUrl;
            }
            response(xhr.status);
        }
    };
    xhr.send(JSON.stringify(data));
}

function registerUser() {
    let email = document.getElementById("email").value;
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    submitForm({
            email: email,
            username: username,
            password: password
        },
        "/api/v1/auth/register",
        "/login",
        (response) => {
            if (response === 400) {
                const unacceptable = '<div class="alert alert-danger" role="alert">Username or email is unacceptable.</div>';
                document.getElementById("register-container").innerHTML = unacceptable;
            } else if (response === 409) {
                const exists = '<div class="alert alert-danger" role="alert">User already exists with that username or email!</div>';
                document.getElementById("register-container").innerHTML = exists;
            }
        }
    );
}

function createTicket() {
    let subject = document.getElementById("subject").value;
    let information = document.getElementById("ticket-details").value;
    submitForm({
            subject: subject,
            information: information
        },
        "/api/v1/ticket/create",
        "/tickets",
        (response) => {
            console.log(response);
        }
    );
}