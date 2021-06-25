
function test() {


    var fetchOptions = {method: "GET" , headers : { "Authorization" : "Bearer " + window.sessionStorage.getItem("myJWT")

        }}


    fetch(`/restservices/Gebruiker/role` , fetchOptions).then( (response) => {
        if (response.ok) {

            return response.json();
        } else {
            throw "spelers niet gevonden";
        }
    }).then((type) => {

        console.log(type["type"])
        window.sessionStorage.setItem("role" , type["type"])

        if (type["type"] === "admin") {




            document.querySelector("#adminAlleen1").removeAttribute("hidden");
            document.querySelector("#adminAlleen2").removeAttribute("hidden");
            document.querySelector("#adminAlleen3").removeAttribute("hidden");
            document.querySelector("#adminAlleen4").removeAttribute("hidden");
            document.querySelector("#adminAlleen5").removeAttribute("hidden");
            document.querySelector("#adminAlleen6").removeAttribute("hidden");


        }
    })
}





