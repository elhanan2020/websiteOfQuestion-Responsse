(function () {
        document.addEventListener('DOMContentLoaded', function () {
        showTheQuestion();

        /*const buttons = document.querySelectorAll('btn')
            buttons.forEach(function(currentBtn){
                currentBtn.addEventListener('click', showTheResponse)
            })*/
    }, false);


        function showTheQuestion() {
            fetch("JsonQuestionServlet", {
                method: 'get',
                headers: {
                    'Content-Type': 'application/json',

                }
            })
                .then(res => res.json())
                .then(resp => {

                    let response = " ";
                    for (let i in resp) {
                        response += "<li class= \"list-group-item\">" +
                            "<p class=\"alert alert-info\">" + resp[i].Question + "</p>" +
                            resp[i].numberOfAnswer + " Answer " + "<button  data-number = \"" + i + "\" type=\"submit\" class=\"btn btn-secondary\" name=\"questionNumber\"  value = " + i + " >Answer </button>" +
                            "<button id = \""+ i +"\"data-number = \"" + i + "\" type=\"button\" style=\"margin: 3px\" type=\" button \" class=\"btn btn-secondary ; show\" name=\"questionNumbers\"  value = " + i + " >Show answers</button>"+
                        "<div style='display: none' class='alert alert-light' id = \"q"+ i +"\">" +
                           "<div id =\"show"+i+"\"></div><button data-number = \"" + i + "\" type=\"button\"  type=\" button \" class=\"alert alert-success ; hide\" name=\"questionNumbers\"  value = " + i + " >Hide</button></div></li>";
                   }
                    response += "</ul>";
                    document.getElementById("question").innerHTML = response;
                    document.querySelectorAll(".show").forEach(item => {
                        item.addEventListener('click',showTheResponse)
                    })
                    document.querySelectorAll(".hide").forEach(item => {
                        item.addEventListener('click',hideTheResponse)
                    })
                })
                .catch(e => {
                    document.getElementById("question").innerHTML = "Some error occured!";
                });
        }


    function showTheResponse( ) {
        let num = this.dataset.number;
        fetch("showResponse?" + "numberOfResponse=" + num, {
            method: 'get',
            headers: {
                'Content-Type': 'application/json' +
                    '',

            }
        })
            .then(res => res.json())
            .then(resp => {
                let response = "";
                for (let i in resp) {
                    response += "<p>" + resp[i].Author + ": " + resp[i].Question + "<\p>"
                }

                document.getElementById("q" + num).style.display = "block";
                document.getElementById("show" + num).innerHTML = response;
             })

            .catch(e => {
                document.getElementById("q"+num).innerHTML = "Some error occured!";
            });

    }
    function hideTheResponse(){
        let num = this.dataset.number;
        document.getElementById("show"+num).innerHTML = "";
        document.getElementById("q"+num).style.display = "none";

    }
})();
