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

        var counter = 0;

        wedstrijden.forEach((element) => {

            counter += 1

            var datum = new Date(element["datum"])


            datum.getTime()

            console.log(element["gespeeld"])
            if (datum.getTime() < Date.now() && element["gespeeld"] === false) {
                const li = document.createElement("li");

                var p1 = document.createElement("p");
                var p2 = document.createElement("p");
                var div = document.createElement("div");
                var p4 = document.createElement("p");

                p1.innerHTML =  element["datum"];
                p2.innerHTML =  element["thuisclub"] + " " + element["thuisTeam"];

                var verwerk = document.createElement("button");
                verwerk.innerHTML = "ok"

                var input = document.createElement("input");
                var input2 = document.createElement("input");

                input2.setAttribute("class" , "input2")
                input.setAttribute("class" , "input1")




                input.id = "input1" + counter;
                input.name = "text" + counter;
                input.type = "number";

                input2.id = "input2" + counter;
                input2.name = "input2" + counter;
                input2.type = "number";


                var label1 = document.createElement("label")
                var label2 = document.createElement("label")

                label1.htmlFor = "input1" + counter;
                label2.htmlFor = "input2" + counter;






                div.appendChild(label1)
                div.appendChild(input)
                div.appendChild(label2)
                div.appendChild(input2)
                div.appendChild(verwerk)

                p4.innerHTML =  element["uitclub"] + " " + element["uitTeam"];

                li.appendChild(p1);
                li.appendChild(p2);
                li.appendChild(div);
                li.appendChild(p4);

                ul.appendChild(li);

                verwerk.addEventListener("click" , function () {


                    var scoreThuisTeam = input.value
                    var scoreUiTeam = input2.value
                    var thuisclub = element["thuisclub"]
                    var thuisTeam = element["thuisTeam"]
                    var uitclub = element["uitclub"]
                    var uitTeam = element["uitTeam"]
                    var datum = element["datum"]
                    var encData = new URLSearchParams()

                    encData.append("thuisclub" , thuisclub)
                    encData.append("thuisteam" , thuisTeam)
                    encData.append("uitclub" , uitclub)
                    encData.append("uitteam" , uitTeam)
                    encData.append("uitTeam" , uitTeam)
                    encData.append("datum" , datum)
                    encData.append("scoreThuisteam" , scoreThuisTeam)
                    encData.append("scoreUitteam" , scoreUiTeam)

                    var fetchOptions = {
                        method: "POST",
                        headers: {
                            "Authorization": "Bearer " + window.sessionStorage.getItem("myJWT")
                        },
                        body: encData
                    }
                    if (scoreThuisTeam !== "" && scoreUiTeam !== "") {
                        fetch(`/restservices/wedstrijd/setUitSlag` , fetchOptions).then( (response) => {


                            if (response.ok) {
                                console.log("gelukt")
                            } else {
                                throw "spelers niet gevonden";
                            }
                        })
                    }





                })
            }


        })

    })




}
