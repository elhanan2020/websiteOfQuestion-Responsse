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
                    for (var i in resp) {
                        response += "<li class= \"list-group-item\">" +
                            "<p class=\"alert alert-info\">" + resp[i].Question + "</p>" +
                            resp[i].numberOfAnswer + " Answer " + "<button  data-number = \"" + i + "\" type=\"submit\" class=\"btn btn-secondary\" name=\"questionNumber\"  value = " + i + " >Answer </button>" +
                            "<button id = \""+ i +"\"data-number = \"" + i + "\" type=\"button\" style=\"margin: 3px\" type=\" button \" class=\"btn btn-secondary ; btn\" name=\"questionNumbers\"  value = " + i + " >Show answers</button></li>"+
                        "<div id = \"q"+ i +"\"></div>";
                    }
                    response += "</ul>";
                    document.getElementById("question").innerHTML = response;
                    document.getElementById("0").addEventListener("click", showTheResponse)
                    console.log("hiiiiii");
                })
                .catch(e => {
                    document.getElementById("question").innerHTML = "Some error occured!";
                });
        }


    function showTheResponse( ) {
console.log("hiiiiii");
          var n = this.dataset.number;
        fetch("showResponse?"+"numberOfResponse=" + n, {
            method: 'get',
            headers: {
                'Content-Type': 'application/json',

            }
        })
                .then(res => res.json())
                .then(resp => {
                  var  response ="";
                  for (let i in resp) {
                    response+= "<p>"+resp[i].Author + ": "+resp[i].Question+"<\p>"



                }

                document.getElementById("q0").innerHTML =response;

        });
    }
})();
