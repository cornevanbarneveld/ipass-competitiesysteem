function registreer() {
    var username = document.querySelector("#gebruikerRegistreer").value;
    var password = document.querySelector("#wwRegistreer").value;

    var encData = new URLSearchParams();

    var fetchOptions = {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + window.sessionStorage.getItem("myJWT")
        },
        body: encData
    }

    encData.append("username" , username);
    encData.append("password" , password);

    console.log(username)
    console.log(password)
    console.log(window.sessionStorage.getItem("role"))

    if (username !== "" && password !== "") {
        fetch("/restservices/Gebruiker/scheidsrechter", fetchOptions)
            .then(function (response) {
                if (response.ok) {
                    document.querySelector("#errorp11").innerHTML = ""
                    document.querySelector("#gebruikerRegistreer").value = "";
                    document.querySelector("#wwRegistreer").value = "";

                }

                else {
                    document.querySelector("#errorp11").innerHTML = "gebruikersnaam bestaat al"
                }
            }).catch(error => console.log(error))
    } else {
        document.querySelector("#errorp11").innerHTML = "Voer een gebruikersnaam of wachtwoord in"

    }

}


