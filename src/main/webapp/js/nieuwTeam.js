function zoeken() {
    var clubnaam = document.querySelector("#input-field1").value
    document.querySelector("#errorp5").innerHTML = ""

    document.querySelector("#inputTeamAanmaak").value = ""


    var fetchOptions = {method: "GET" , headers : { "Authorization" : "Bearer " + window.sessionStorage.getItem("myJWT")
        }}

    if (clubnaam !== "") {
        fetch(`/restservices/teamzoek/${clubnaam}`, fetchOptions)

            .then(function (response) {
                if (response.ok) {
                    window.sessionStorage.setItem("club" , clubnaam)
                    const div = document.querySelector(".input-container");



                    div.removeChild(div.lastChild)


                    var h3  = document.createElement("h3")
                    h3.innerHTML = clubnaam



                    div.appendChild(h3)

                    document.querySelector("#errorp4").innerHTML = ""

                    return response.json()
                }
                else {
                    document.querySelector("h3").innerHTML = ""
                    document.querySelector("#errorp4").innerHTML = "geen club gevonden"
                    throw "errorrrrr";
                }

            })
    } else {
        document.querySelector("#errorp4").innerHTML = "geen club gevonden"
    }

}

function maakTeam() {
    var clubnaam = document.querySelector("#input-field1").value;
    var teamnaam = document.querySelector("#inputTeamAanmaak").value;



    var fetchOptions = {method: "POST" , headers : { "Authorization" : "Bearer " + window.sessionStorage.getItem("myJWT")
        }}

    if (clubnaam !== "" && teamnaam !== null) {
        fetch(`/restservices/teamzoek/teammaak/${clubnaam}/${teamnaam}`, fetchOptions)

            .then(function (response) {
                if (response.ok) {
                    document.querySelector("#errorp5").innerHTML = ""
                    document.querySelector("#input-field1").value = ""
                    document.querySelector("#inputTeamAanmaak").value = ""
                }
                else {
                    document.querySelector("#errorp5").innerHTML = "team bestaat al"
                    throw "team Bestaat al";
                }

            })
    }    else {
        document.querySelector("#errorp5").innerHTML = "voer een team of club in"
    }



}
