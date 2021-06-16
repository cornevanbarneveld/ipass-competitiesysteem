function login() {
    var formData = new FormData(document.querySelector("#loginForm"));
    var encData = new URLSearchParams(formData.entries());
    console.log("a")

    fetch("/restservices/authentication", {method: "POST", body: encData})
        .then(function (response) {

            if (response.ok) {
                return response.json();
            }
            else throw "error";
        })
        .then(myJson => {
            window.sessionStorage.setItem("myJWT", myJson.JWT)
            window.open("homescreen.html")
        })
        .catch(error => console.log(error))
}





