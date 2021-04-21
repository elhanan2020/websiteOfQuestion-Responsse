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

                document.getElementById("button1").innerHTML = "<button  style=\"margin: 3px\" type=\"submit\" class=\"btn btn-secondary\" name=\"questionNumber\" value=\" "+ resp.number + " \"> Submit </button>" ;
                document.getElementById("button2").innerHTML = " <a href=\"/MainPage\"><button style=\"margin: 3px\"  class=\"btn btn-secondary\" name=\"questionNumber\" value=\" "+ resp.number +" \">Reset </button></a>";
                ;
            })
            .catch(e => {
                document.getElementById("showQuestion").innerHTML = "Some error occured!";
            });
    });

})();