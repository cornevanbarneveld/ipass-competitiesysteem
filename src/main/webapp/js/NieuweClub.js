function Clubaanmaken() {
    var clubnaam = document.querySelector("#input-field1").value;




    var fetchOptions = {method: "POST" , headers : { "Authorization" : "Bearer " + window.sessionStorage.getItem("myJWT")
        }}
    if (clubnaam !== "") {

        fetch(`/restservices/club/clubmaak/${clubnaam}`, fetchOptions)

            .then(function (response) {
                if (response.ok) {
                    document.querySelector("#errorp6").innerHTML = ""
                    document.querySelector("#input-field1").value = ""
                }
                else {
                    document.querySelector("#errorp6").innerHTML = "Club Bestaat al"

                    throw "team Bestaat al";
                }

            })
    } else {
        document.querySelector("#errorp6").innerHTML = "Voer een club in"
    }


}
