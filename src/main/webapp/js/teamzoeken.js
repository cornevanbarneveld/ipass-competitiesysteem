
function zoeken() {
    var clubnaam = document.querySelector("#input-field1").value
    fetch(`/restservices/teamzoek/${clubnaam}` )

        .then(function (response) {
            if (response.ok) {
                return response.json()
            }
            else {
                throw "Wrong username / password";
            }

        }).then((clubs) => {
        console.log(clubs)
        clubs.forEach(element => {
            var eersteTwee = element.substring(0, 2);


            if (eersteTwee === "jo") {
                var jongenss = document.querySelector( "#JongensSelect")
                var optionjongens = document.createElement("option");
                optionjongens.text = element;
                jongenss.add(optionjongens);
            }
            if (eersteTwee === "vr") {
                var vrouwens = document.querySelector( "#vrouwenselect")
                var optionv = document.createElement("option");
                optionv.text = element;
                vrouwens.add(optionv);
            }

            if (eersteTwee === "ma") {
                var mannens = document.querySelector( "#mannenSelect")
                var optionm = document.createElement("option");
                optionm.text = element;
                mannens.add(optionm);
            }
        })

    })

}






function spelersophalen() {

    var e = document.getElementById("JongensSelect");
    var strUser = e.options[e.selectedIndex].text;





    var formData = new FormData(document.querySelector("#teamOphalenF"));
    formData.append("team" , strUser)
    var encData = new URLSearchParams(formData.entries());



    fetch("/restservices/teamzoek/team", {method: "POST", body: encData})

        .then(function (response) {
            if (response.ok) {
                return response.json()
            }
            else {
                throw "Wrong username / password";
            }

        }).then((spelers) => {

            window.open("teamscherm.html")



            document.querySelector("#aa").innerHTML = "teamnaam.."



            spelers.forEach((element) => {
                var node = document.createElement("li");
                var textnode = document.createTextNode(element);
                node.appendChild(textnode);
                document.getElementById("ulspelers").appendChild(node);
            })




    })

}
