
function initialize() {

    if(window.sessionStorage.getItem("role") === "scheidsrechter") {
        document.querySelector("#alleenScheids").removeAttribute("hidden")
    }

    var team = window.sessionStorage.getItem("selectedTeam");
    var club = window.sessionStorage.getItem("selectedClub");



    document.querySelector("#teamnaamlabel").innerHTML = team;
    document.querySelector("#clubnaamlabel").innerHTML = club;


    var fetchOptions = {method: "GET" , headers : { "Authorization" : "Bearer " + window.sessionStorage.getItem("myJWT")
        }}

    fetch(`/restservices/spelers/${club}/${team}` , fetchOptions).then( (response) => {
        if (response.ok) {

            return response.json();
        } else {
            throw "spelers niet gevonden";
        }
    }).then( (spelers) => {
        console.log(spelers)


        spelers.forEach(speler => {
            const ul = document.getElementById("ulspelers");
            const li = document.createElement("li");
            const p = document.createElement("p");
            p.innerHTML = speler
            li.appendChild(p);
            ul.appendChild(li);

        })
    })
}




