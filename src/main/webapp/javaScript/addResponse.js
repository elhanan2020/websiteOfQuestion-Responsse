(function () {
    document.addEventListener('DOMContentLoaded', function () {
        fetch("JsonQuestionServlet", {
            method:'post',
            headers: {
                'Content-Type': 'application/json',

            }})
            .then(res => res.json())
            .then(resp => {
                document.getElementById("showQuestion").innerHTML = resp.Question;

                document.getElementById("button1").innerHTML = "<p></p><button  style=\"margin: 3px\" type=\"submit\" class=\"btn btn-secondary\" name=\"questionNumber\" value=\" "+ resp.number + " \"> Submit </button>"+
                   " <a class =\"button\" href=\"/MainPage\"> <button type=\"button\" style=\"margin: 3px\" class=\"btn btn-secondary\">Cancel</button></a> ";

            })
            .catch(e => {
                document.getElementById("showQuestion").innerHTML = "Some error occured!";
            });
    });

    function validatorForm(){

        let name = document.myform.myName.value.trim();
        let text = document.myform.text.value.trim();
        document.getElementById("errorMessage1").style.display="none" ;
        document.getElementById("errorMessage2").style.display="none" ;
        if (name == null || name === ""){
            document.getElementById("errorMessage1").style.display="block" ;
            document.getElementById("errorMessage1").innerHTML = "you need to enter your name please";
            return false;
        }else if(text == null || text === ""){
            document.getElementById("errorMessage2").style.display="block" ;
            document.getElementById("errorMessage2").innerHTML = "you need to enter your response please";
            return false;
        }
    }
})();