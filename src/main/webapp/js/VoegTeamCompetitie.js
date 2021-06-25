function initialize() {
    document.querySelector("#input-field1nummer").value = window.sessionStorage.getItem("nummer")

}


function voegTeamToe() {

    var nummer = document.querySelector("#input-field1nummer").value;
    var club = document.querySelector("#input-field2Club").value;
    var team = document.querySelector("#input-field3Team").value;

    var encData = new URLSearchParams();

    encData.append("club" , club);
    encData.append("team" , team);


    var fetchOptions = {method: "POST" ,
        headers : { "Authorization" : "Bearer " + window.sessionStorage.getItem("myJWT")} ,
        body: encData
    }
    if (club !== "" && team !== "") {
        fetch(`/restservices/competitie/voegtoe/${nummer}`, fetchOptions)

            .then(function (response) {
                if (response.ok) {
                    document.querySelector("#input-field2Club").value = "";
                    document.querySelector("#input-field3Team").value = "";
                    document.querySelector("#errorp10").innerHTML= ""

                }
                else {
                    document.querySelector("#errorp10").innerHTML= "Team is al toegevoegd"
                    throw "Team is al toegevoegd";
                }

            })
    } else {
        document.querySelector("#errorp10").innerHTML= "voer een team of club in"
    }

}
