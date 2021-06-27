function laadCompetitieswed() {

    if(window.sessionStorage.getItem("role") === "scheidsrechter") {
        document.querySelector("#alleenScheids").removeAttribute("hidden")
    }

    var team = window.sessionStorage.getItem("selectedTeam");
    var club = window.sessionStorage.getItem("selectedClub");

    var fetchOptions = {method: "GET" , headers : { "Authorization" : "Bearer " + window.sessionStorage.getItem("myJWT")
        }}

    fetch(`/restservices/competitie/${club}/${team}` , fetchOptions).then( (response) => {
        if (response.ok) {
            return response.json();
        } else {
            throw "spelers niet gevonden";
        }
    }).then(((competities) => {
        var selectComp = document.querySelector( "#selectcompwed")
        competities.forEach((element) => {
            var option = document.createElement("option");
            option.text = element;
            selectComp.add(option);
        })
    }))
}


function zoekWedstrijden() {

    var competitie = document.querySelector("#selectcompwed").value;
    console.log(competitie)

    var team = window.sessionStorage.getItem("selectedTeam");
    var club = window.sessionStorage.getItem("selectedClub");

    var encData = new URLSearchParams();

    encData.append("competitie" , competitie)

    var fetchOptions = {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + window.sessionStorage.getItem("myJWT")
        },
        body: encData
    }


    fetch(`/restservices/wedstrijd/${club}/${team}` , fetchOptions).then( (response) => {

        console.log(response)
        if (response.ok) {
            return response.json();
        } else {
            throw "spelers niet gevonden";
        }
    }).then((wedstrijden) => {

        var ul = document.querySelector("#ulwedstrijden")

        wedstrijden.forEach((element) => {




            const li = document.createElement("li");


            var p1 = document.createElement("p");
            var p2 = document.createElement("p");
            var p3 = document.createElement("p");
            var p4 = document.createElement("p");


            var datum = String(element["datum"]);
            var nieuweDatum = datum.replace("T" , " ");


            p1.innerHTML =  nieuweDatum
            p2.innerHTML =  element["thuisclub"] + " " + element["thuisTeam"];

            if (element["gespeeld"] === true) {
                p3.innerHTML = element["scoreThuisTeam"] + "-" + element["scoreUitTeam"]
            } else {
                p3.innerHTML = "vs";
            }

            p4.innerHTML =  element["uitclub"] + " " + element["uitTeam"];



            if (window.sessionStorage.getItem("role") === "admin") {

                if (element["gespeeld"] === false) {
                    var button = document.createElement("button");
                    button.innerHTML = "Verplaats Wedstrijd";
                    li.appendChild(button);

                    button.addEventListener("click"  , function()  {
                        window.sessionStorage.setItem("thuisclub" ,element["thuisclub"] )
                        window.sessionStorage.setItem("uitclub" ,element["uitclub"] )
                        window.sessionStorage.setItem("thuisTeam" ,element["thuisTeam"] )
                        window.sessionStorage.setItem("uitTeam" ,element["uitTeam"] )
                        window.sessionStorage.setItem("datum" , element["datum"])

                        openDialog()
                    })
                }
            }
            li.appendChild(p1);
            li.appendChild(p2);
            li.appendChild(p3);
            li.appendChild(p4);

            if ( window.sessionStorage.getItem("role") === "admin" ||(element["thuisclub"] === window.sessionStorage.getItem("selectedClub") && element["thuisTeam"] === window.sessionStorage.getItem("selectedTeam")) || (element["uitclub"] === window.sessionStorage.getItem("selectedClub") && element["uitTeam"] === window.sessionStorage.getItem("selectedTeam")) ){
                ul.appendChild(li);
            }



        })

    })




}
function refresh() {
    document.querySelector("#errorp7").innerHTML = ""
    document.querySelector("#errorp8").innerHTML = ""
    if (window.sessionStorage.getItem("nummer") !== null) {
        document.querySelector("#nummer").value = window.sessionStorage.getItem("nummer");

    }
}


function voegWedstrijdToe() {



    var thuisClub = document.querySelector("#thuisclub").value;
    var thuisTeam = document.querySelector("#thuisteam").value;
    var uitClub = document.querySelector("#uitClub").value;
    var uitTeam = document.querySelector("#uitTeam").value;
    var veld = document.querySelector("#veld").value;
    var Datum = document.querySelector("#date").value;
    var nummer = document.querySelector("#nummer").value;

    var datum1 = new Date(Datum)

    if (datum1.getTime() < Date.now()) {
        document.querySelector("#errorp7").innerHTML = "Datum mag niet in het heden of verleden zijn"
        Datum = null;
    } else {
        var encData = new URLSearchParams();

        encData.append("thuisclub" , thuisClub)
        encData.append("thuisteam" , thuisTeam)
        encData.append("uitclub" , uitClub)
        encData.append("uitteam" , uitTeam)
        encData.append("veld" , veld)
        encData.append("datum" , Datum)
        encData.append("nummer" , nummer)


        var fetchOptions = {
            method: "POST",
            headers: {
                "Authorization": "Bearer " + window.sessionStorage.getItem("myJWT")
            },
            body: encData
        }

        if (thuisClub !== "" && thuisTeam !== "" && uitClub !== "" && uitTeam !== "" && nummer !== 0 && veld !== 0) {

            fetch(`/restservices/competitie/voegwedstrijdtoe/${nummer}` , fetchOptions).then( (response) => {

                if (response.ok) {
                    document.querySelector("#errorp7").innerHTML = ""

                    document.querySelector("#thuisclub").value = "";
                    document.querySelector("#thuisteam").value = "";
                    document.querySelector("#uitClub").value = "";
                    document.querySelector("#uitTeam").value = "";
                    document.querySelector("#veld").value = "";
                    document.querySelector("#date").value = "";


                } else {
                    document.querySelector("#errorp8").innerHTML = "Thuis team/club of Uit team/club is/zijn incorrect"
                    throw "spelers niet gevonden";
                }
            }).catch(error => console.log(error))
        } else {
            document.querySelector("#errorp8").innerHTML = "Thuis team/club of Uit team/club is/zijn incorrect"

        }

    }





}

function openDialog() {

    document.querySelector("#wedstrijdVerplaatsen").showModal();
}

function VerplaatsDatum() {

    var datum = window.sessionStorage.getItem("datum")
    var nieuwedatum = document.querySelector("#datumVerplaats").value
    var thuisClub = window.sessionStorage.getItem("thuisclub")
    var thuisTeam = window.sessionStorage.getItem("thuisTeam")
    var uitClub = window.sessionStorage.getItem("uitclub")
    var uitTeam = window.sessionStorage.getItem("uitTeam")




    var encData = new URLSearchParams();

    encData.append("thuisclub" , thuisClub)
    encData.append("thuisteam" , thuisTeam)
    encData.append("uitclub" , uitClub)
    encData.append("uitteam" , uitTeam)
    encData.append("datum" , datum)
    encData.append("nieuwedatum" , nieuwedatum)



    var fetchOptions = {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + window.sessionStorage.getItem("myJWT")
        },
        body: encData
    }
    if (nieuwedatum !== "") {
        fetch(`/restservices/wedstrijd/vervangDatum` , fetchOptions).then( (response) => {

            window.sessionStorage.removeItem("datum")
            if (response.ok) {

                document.querySelector("#wedstrijdVerplaatsen").close();
                return response.json()

            } else {
                throw "spelers niet gevonden";
            }
        }).then((date) => {
            window.sessionStorage.setItem("datum" ,date)
        }).catch(error => console.log(error))
    }



}

function closeDialog() {
    document.querySelector("#wedstrijdVerplaatsen").close();

}
