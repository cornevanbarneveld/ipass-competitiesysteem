
function zoeken() {
    var clubnaam = document.querySelector("#input-field1").value

    var fetchOptions = {method: "GET" , headers : { "authentication" : "Bearer" + window.sessionStorage.getItem("myJWT")
    }}

    fetch(`/restservices/teamzoek/${clubnaam}`, fetchOptions)

        .then(function (response) {
            if (response.ok) {
                return response.json()
            }
            else {
                throw "error";
            }

        }).then((clubs) => {

        clubs.forEach(element => {
            var eersteTwee = element.substring(0, 2);


            if (eersteTwee === "jo") {
                var jongenss = document.querySelector( "#JongensSelect")
                var optionjongens = document.createElement("option");
                optionjongens.text = element;
                jongenss.add(optionjongens);
            }
            if (eersteTwee === "vr") {
                var vrouwens = document.querySelector( "#vrouwenselect")
                var optionv = document.createElement("option");
                optionv.text = element;
                vrouwens.add(optionv);
            }

            if (eersteTwee === "ma") {
                var mannens = document.querySelector( "#mannenSelect")
                var optionm = document.createElement("option");
                optionm.text = element;
                mannens.add(optionm);
            }
        })

    })
    window.sessionStorage.setItem("selectedClub" , clubnaam);

}






function jongensophalen() {
    console.log("j");
    window.sessionStorage.setItem("selectedTeam" , document.querySelector("#JongensSelect").value);
    window.open("teamscherm.html");
}

function mannenophalen() {
    console.log("m");
    window.sessionStorage.setItem("selectedTeam" , document.querySelector("#mannenSelect").value);
    window.open("teamscherm.html");
}

function vrouwenophalen() {
    console.log("v");
    window.sessionStorage.setItem("selectedTeam" , document.querySelector("#vrouwenselect").value);
    window.open("teamscherm.html");

}
