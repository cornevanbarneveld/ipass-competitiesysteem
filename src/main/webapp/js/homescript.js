function zoekTeam() {

    var Team = document.querySelector("#inputClub").value;

    var fetchOptions = {
        method: "GET",
        body: JSON.stringify(Team)
    }
    fetch("/restservices/countries", fetchOptions).then(function(response){
        if (response.ok) return response.json();
    }).then(myJson => console.log(myJson)).catch(error => console.log(error))




}

function popup() {
    document.querySelector("#popup").style.visibility = "visible";
}
