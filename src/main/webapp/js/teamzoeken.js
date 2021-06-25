
function zoeken() {
    var clubnaam = document.querySelector("#input-field1").value



    var fetchOptions = {method: "GET" , headers : { "Authorization" : "Bearer " + window.sessionStorage.getItem("myJWT")
    }}

    if (clubnaam !== "") {
        fetch(`/restservices/teamzoek/${clubnaam}`, fetchOptions)

            .then(function (response) {
                if (response.ok) {

                    document.querySelector("#errorp3").innerHTML = "";
                    document.querySelector("#teamOphalenF").removeAttribute("hidden")
                    return response.json()
                }
                else {
                    document.querySelector("#errorp3").innerHTML = "geen club gevonden";
                    document.querySelector("#teamOphalenF").hidden = true

                    throw "error";
                }

            }).then((clubs) => {

            document.querySelector("#JongensSelect").innerHTML = ""
            var hiddenjongens = document.createElement("option");
            hiddenjongens.hidden = true;
            document.querySelector("#JongensSelect").appendChild(hiddenjongens)


            document.querySelector("#vrouwenselect").innerHTML = ""
            var hiddenvrouwen = document.createElement("option");
            hiddenvrouwen.hidden = true;
            document.querySelector("#vrouwenselect").appendChild(hiddenvrouwen)

            document.querySelector("#mannenSelect").innerHTML = ""
            var hiddenMannen = document.createElement("option");
            hiddenMannen.hidden = true;
            document.querySelector("#mannenSelect").appendChild(hiddenMannen)

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
    } else {
        document.querySelector("#errorp3").innerHTML = "geen club gevonden";

    }


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
