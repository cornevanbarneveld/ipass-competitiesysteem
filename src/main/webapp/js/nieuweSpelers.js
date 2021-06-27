function Initialize() {

}

function VoegSpelerToe() {

    var club = document.querySelector("#Clubzoeken").value
    var team = document.querySelector("#TeamZoeken").value
    var speler = document.querySelector("#Spelertoevoegen").value

    var encData = new URLSearchParams();

    encData.append("speler" , speler)

    var fetchOptions = {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + window.sessionStorage.getItem("myJWT")
        },
        body: encData
    }

    if(club !== "" && team !== "") {
        fetch(`/restservices/spelers/nieuwe/${club}/${team}` , fetchOptions).then( (response) => {


            if (response.ok) {
                document.querySelector("#Spelertoevoegen").value = "";
                document.querySelector("#errorp9").innerHTML = ""


            } else {
                document.querySelector("#errorp9").innerHTML = "club of team bestaat niet"
                throw "spelers niet gevonden";
            }
        })
    } else {
        document.querySelector("#errorp9").innerHTML = "club of team bestaat niet"

    }



}
