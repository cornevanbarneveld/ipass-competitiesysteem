function laadCompetities() {
    document.querySelector("#buttonTeamZoekc").hidden = true
    document.querySelector("#buttonwedzoekc").hidden = true

    if(window.sessionStorage.getItem("role") === "scheidsrechter") {
        document.querySelector("#alleenScheids").removeAttribute("hidden")
    }



    document.querySelector("#table").hidden = true

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

        var selectComp = document.querySelector( "#selectcomp")

        competities.forEach((element) => {

            var option = document.createElement("option");
            option.text = element;
            selectComp.add(option);

        })
    }))
}



function verwerkWedstrijden() {


    var competitie = document.querySelector("#selectcomp").value;

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

    fetch(`/restservices/competitie/${club}/${team}` , fetchOptions).then( (response) => {

        console.log(response)
        if (response.ok) {
            document.querySelector("#table").hidden = false
            return response.json();
        } else {
            throw "spelers niet gevonden";
        }
    }).then((wedstrijden) => {


        if(window.sessionStorage.getItem("role") === "admin") {

            var div = document.querySelector("#selectdivcomp")
            var buttonTeam = document.querySelector("#buttonTeamZoekc")
            buttonTeam.hidden =false
            buttonTeam.innerHTML = "Voeg Team Toe"
            buttonTeam.addEventListener("click" , function () {
                window.open("voegTeamCompetitie.html")
            })
            var buttonWedstrijd = document.querySelector("#buttonwedzoekc")
            buttonWedstrijd.hidden = false
            buttonWedstrijd.innerHTML = "Nieuw Wedstrijd"
            buttonWedstrijd.addEventListener("click" , function () {
                window.open("NieuweWedstrijd.html")
            })

            div.appendChild(buttonTeam);
            div.appendChild(buttonWedstrijd)
        }

        var tbody = document.querySelector("tbody");
        tbody.innerHTML = ""

        wedstrijden.forEach((element) => {
            window.sessionStorage.setItem("nummer" , element["nummer"]);
            console.log(element["nummer"])
            console.log(window.sessionStorage.getItem("nummer"))

            var tr = document.createElement("tr");

            var td1 = document.createElement("td")
            var td2 = document.createElement("td")
            var td3 = document.createElement("td")
            var td4 = document.createElement("td")
            var td5 = document.createElement("td")
            var td6 = document.createElement("td")
            var td7 = document.createElement("td")
            var td8 = document.createElement("td")
            var td9 = document.createElement("td")

            td1.innerHTML = element["clubEnTeamnaam"];
            td2.innerHTML = element["gespeeld"];
            td3.innerHTML = element["punten"];
            td4.innerHTML = element["gewonnen"];
            td5.innerHTML = element["gelijk"];
            td6.innerHTML = element["verloren"];
            td7.innerHTML = element["doelsaldo"];
            td8.innerHTML = element["dptt"];
            td9.innerHTML = element["dptv"];

            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            tr.appendChild(td5);
            tr.appendChild(td6);
            tr.appendChild(td7);
            tr.appendChild(td8);
            tr.appendChild(td9);

            tbody.appendChild(tr);
        })

    })
}



