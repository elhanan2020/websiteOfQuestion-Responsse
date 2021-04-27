(function () {
        document.addEventListener('DOMContentLoaded', function () {
        showTheQuestion();

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
                    for (let a in resp) {
                        response += "<li class= \"list-group-item\">" +
                            "<p class=\"alert alert-info\">" + resp[a].Question + "</p>" +
                            resp[a].numberOfAnswer + " Answer " + "<button  data-number = \"" + a + "\" type=\"submit\" class=\"btn btn-secondary\" name=\"questionNumber\"  value = " + a + " >Answer </button>" +
                            "<button id = \""+ a + "\" data-number = \"" + a + "\" type=\"button\" style=\"margin: 3px\" type=\" button \" class=\"btn btn-secondary ; show\" name=\"questionNumbers\"  value = " + a + " >Show answers</button>"+
                        "<div style='display: none' class='alert alert-light' id = \"q"+ a +"\">" +
                           "<div   id =\"show"+ a +"\"></div><button data-number = \"" + a + "\" type=\"button\"  class=\"alert alert-success ; hide\" name=\"questionNumbers\"  value = " + a + " >Hide</button></div></li>";
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
                .catch(() => {
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
                    response += "<p class=\"alert alert\">[" + resp[i].Author + "] : " + resp[i].Response + "<\p>"
                }
                document.getElementById("q" + num).style.display = "block";
                document.getElementById("show" + num).innerHTML = response;
             })

            .catch(() => {
                document.getElementById("q"+num).innerHTML = "Some error occured!";
            });

    }
    function hideTheResponse(){
        let num = this.dataset.number;
        document.getElementById("show" + num).innerHTML = "";
        document.getElementById("q" + num).style.display = "none";

    }
})();
