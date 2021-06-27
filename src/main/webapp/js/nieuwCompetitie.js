function maakCompetitie() {

    var competitienaam = document.querySelector("#input-field1naam").value;
    var klasse = document.querySelector("#selectKlasse").value;




    var fetchOptions = {method: "POST" , headers : { "Authorization" : "Bearer " + window.sessionStorage.getItem("myJWT")
        }}

    if (competitienaam !== "" && klasse !== 0) {
        fetch(`/restservices/competitie/maak/${competitienaam}/${klasse}`, fetchOptions)

            .then(function (response) {
                if (response.ok) {
                    var button = document.createElement("button");
                    button.setAttribute("id" , "zoekTeamCompbutton")
                    button.innerHTML = "Voeg team toe";
                    button.setAttribute("onclick" , "window.location.href = 'voegTeamCompetitie.html'")
                    var contendiv = document.querySelector(".content");
                    contendiv.appendChild(button);


                    return response.json()



                }
                else {
                    throw "team Bestaat al";
                }

            }).then((nummer) => {

            window.sessionStorage.setItem("nummer" , nummer);
        })
    }


}
