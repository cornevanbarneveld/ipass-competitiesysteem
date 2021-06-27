function login() {
    var formData = new FormData(document.querySelector("#loginForm"));
    var encData = new URLSearchParams(formData.entries());

    fetch("/restservices/authentication", {method: "POST", body: encData})
        .then(function (response) {

            if (response.ok) {
                return response.json();
            }
            else {
                document.querySelector("#errorp").innerHTML = "Gebruikersnaam of Wachtwoord is incorrect";
                throw "error";
            }
        })
        .then(myJson=> {



            window.sessionStorage.setItem("myJWT", myJson.JWT)

            console.log(myJson)

            window.open("homescreen.html")
        })
        .catch(error => console.log(error))
}



function OpenDialog() {
    document.querySelector("#errorp").innerHTML = "";
    document.querySelector("#registreerDialog").showModal();
}

function cancelregistreer() {
    document.querySelector("#registreerDialog").close();
}

function registreer() {

    var username = document.querySelector("#gebrRegistreer").value;
    var password = document.querySelector("#wwRegistreer").value;
    var encData = new URLSearchParams();


    encData.append("username" , username);
    encData.append("password" , password);


    fetch("/restservices/Gebruiker", {method: "POST", body: encData})
        .then(function (response) {
            if (response.ok) {
                return response.json();
            }


            else {
                document.querySelector("#errorp2").innerHTML = "Gebruikersnaam bestaat al";

                throw "error gaat fout";
            }
        })
        .then(() => {
                document.querySelector("#registreerDialog").close();
            })
        .catch(error => console.log(error))



}

function vergeetData() {
    console.log("a")
    sessionStorage.clear()
}
