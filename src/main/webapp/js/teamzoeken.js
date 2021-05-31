


function zoeken() {


    var formData = new FormData(document.querySelector("#teamzoekForm"));
    console.log(formData)
    var encData = new URLSearchParams(formData.entries());



    fetch("/restservices/teamzoek/clob", {method: "POST", body: encData})

        .then(function (response) {
            if (response.ok) {
                return response.json()
            }
            else {
                throw "Wrong username / password";
            }

        }).then((clubs) => {



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
                    optionv.text = key;
                    vrouwens.add(optionv);
                }

                if (eersteTwee === "ma") {
                    var mannens = document.querySelector( "#mannenSelect")
                    var optionm = document.createElement("option");
                    optionm.text = key;
                    mannens.add(optionm);
                }
            })

    })

}


var teamnaam = "";

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
        console.log("aaaaaaaa")
        document.querySelector("#ttt").innerHTML = "werkt!"
        spelers.forEach((element) => {
            var node = document.createElement("li");
            var textnode = document.createTextNode(element);
            node.appendChild(textnode);
            document.getElementById("ulspelers").appendChild(node);
        })




        window.open("teamscherm.html")







    })

}
