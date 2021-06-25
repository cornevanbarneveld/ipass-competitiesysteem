function registreer() {
    var username = document.querySelector("#gebruikerRegistreer").value;
    var password = document.querySelector("#wwRegistreer").value;

    var encData = new URLSearchParams();


    encData.append("username" , username);
    encData.append("password" , password);

    if (username !== "" && password !== "") {
        fetch("/restservices/Gebruiker/scheidsrechter", {method: "POST", body: encData})
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


