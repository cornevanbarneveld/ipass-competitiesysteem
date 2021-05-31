function login() {
    var formData = new FormData(document.querySelector("#loginForm"));
    var encData = new URLSearchParams(formData.entries());

    fetch("/restservices/login", {method: "POST", body: encData})
        .then(function (response) {
            window.open("homescreen.html")
            if (response.ok) return response.json(); //body will be json
            else throw "Wrong username / password"; //there is no body, just throw the error
        })


}
