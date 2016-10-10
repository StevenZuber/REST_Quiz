var restDataModule = (function() {
  var module = {};


  module.tableBuilder = function(dataArr, location) {
    var table = document.createElement("table");
    var headerArr = ['Name','View', 'Take', 'Edit', 'Delete']
    table.appendChild(createTableHeader(headerArr));
    table.appendChild(createTableBody(dataArr));

    var containerDiv = document.getElementById(location);
    containerDiv.appendChild(table);
  };

  var createTableHeader = function(headerArr) {
    var thead = document.createElement("thead");
    var headRow = document.createElement("tr");
    headerArr.forEach(function(value, index, array){
      var th = document.createElement("th");
      th.textContent = value;
      headRow.appendChild(th);
    });
    thead.appendChild(headRow);
    return thead;
  };

  var createTableBody = function(dataArr) {
    var tbody = document.createElement("tbody");
    dataArr.forEach(function(data,index,arr){
      var tr = document.createElement("tr");
        for (property in data) {
          if (property === 'name'){
          var td = document.createElement("td");
          td.textContent = data[property];
          tr.appendChild(td);
        }
      };
        tr.appendChild(makeButtonsForTableToViewQuiz(data));
        tr.appendChild(makeButtonsForTableToTakeQuiz(data));
        tr.appendChild(makeButtonsForTableToEditQuiz(data));
        tr.appendChild(makeButtonsForTableToDeleteQuiz(data));

      tbody.appendChild(tr);
    });
    return tbody;
  };

  var makeButtonsForTableToViewQuiz = function(dataObj) {
    var td = document.createElement("td");
    var viewBtn = document.createElement("button");
    viewBtn.setAttribute("quiz_id", dataObj.id);
    viewBtn.textContent = "View";

    viewBtn.addEventListener('click', function(e) {
      var quizId = e.target.getAttribute("quiz_id");
      console.log(quizId);

      module.ajax("GET", "api/quizzes/" + quizId, function() {


      })
    });

    td.appendChild(viewBtn);
    return td;
  };

    var makeButtonsForTableToTakeQuiz = function(dataObj) {
      var td = document.createElement("td");
      var takeBtn = document.createElement("button");
      takeBtn.setAttribute("quiz_id", dataObj.id);
      takeBtn.textContent = "Take";

      takeBtn.addEventListener('click', function(e) {
        var quizId = e.target.getAttribute("quiz_id");
        console.log(quizId);
        var answerInput = [];
        var counter = 0;

        module.ajax("GET", "api/quizzes/" + quizId, function(req) {
          if (req.readyState === 4 && req.status < 400) {

            var table = document.getElementById('data-table');

            table.parentElement.removeChild(table);
            createBtn.parentElement.removeChild(createBtn);
            var form = document.createElement('form');

           dataObj.questions.forEach(function(value, index, array){
              var qt = value.questionText;
              var h1 = document.createElement('h1');
              h1.textContent = qt;
              form.appendChild(h1);

             value.answers.forEach(function(answer, index, array){

               var at = answer.answerText;
               var individualAnswer = document.createElement('p');

               individualAnswer.textContent = at;
               form.appendChild(individualAnswer);
               console.log(individualAnswer)
          	    var radio = document.createElement('input');
                console.log(answer)
                radio.setAttribute('type', 'radio');
                form.appendChild(radio);

                radio.addEventListener('click', function(e){
            	    radio.value = answer.correct;
                  console.log("Before push: " + answerInput);

                  answerInput.push(answer.correct);
                  console.log("After Push: " + answerInput);


                })// end of event listener for buttons

         }); // end of answers forEach

       }); // end of questions forEach

        var submit = document.createElement('input');
        console.log(answerInput);
        submit.setAttribute('type', 'submit');
        submit.value = 'Submit';
        form.appendChild(submit);
        document.body.appendChild(form);
        submit.addEventListener('click', function(e){

          console.log("hello from after submit");
          console.log(answerInput);
          console.log(counter);
          answerInput.forEach(function(value, index, array){
            if (value === true){
            console.log("Hello from checking radio buttons if");
            counter++;
            };
          });

          var score = parseInt((counter/3) * 100);

          var json = {
              value : score,
              quizId : quizId
          };

						var jsonString = JSON.stringify(json);

						var xhr = new XMLHttpRequest();
            console.log("Hello from right before the xhr open.");
						xhr.open("POST", "api/users/"	+ 1 + "/scores/" + quizId);

						xhr.setRequestHeader('Content-type', 'application/json');

						xhr.onreadystatechange = function() {
							if (xhr.readyState === 4 && xhr.status < 400) {
								console.log(xhr.status);
								console.log(xhr.responseText);
							} // end xhr if

						}; // end xhr
						xhr.send(jsonString);

						alert("Total Score : " + score +"%");




        }); // end of submit event listener
      }; //end of 'if page loads properly'
    }); // end of module.ajax function

  }); // end of takeBtn event listener
      td.appendChild(takeBtn);
      return td;

    }; // end of button


      var makeButtonsForTableToEditQuiz = function(dataObj) {
        var td = document.createElement("td");
        var editBtn = document.createElement("button");
        editBtn.setAttribute("quiz_id", dataObj.id);
        editBtn.textContent = "Edit";
        editBtn.addEventListener('click', function(e) {
          var quizId = e.target.getAttribute("quiz_id");
          console.log(quizId);
            module.ajax("GET", "api/quizzes/" + quizId, function(req) {
              if (req.readyState === 4 && req.status < 400) {
                var table = document.getElementById('data-table');

                table.parentElement.removeChild(table);
                createBtn.parentElement.removeChild(createBtn);
                var form = document.createElement('form');
                var input = document.createElement('input');
                input.type = "text";
                input.placeholder = "New Name";
                var submit = document.createElement('input');
                submit.type = "submit";
                submit.value = "Submit";
                form.appendChild(input);
                form.appendChild(submit);
                document.body.appendChild(form);

                submit.addEventListener('click', function(e){
                  form.remove();
                  var editQuiz ={name: input.value};
                  var jsonString = JSON.stringify(editQuiz);
                  var xhr = new XMLHttpRequest();
                  xhr.open('PUT', 'api/quizzes/' + quizId);

                  xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4 && xhr.status < 400) {
                      console.log(xhr.status);
                      console.log(xhr.responseText);

                    } else {
                    console.log(xhr.status + " : " + xhr.responseText);
                    }
                  };
                  xhr.send(jsonString);
                  form.reset();
                  location.reload();

                  });
            }

          })
        });

        td.appendChild(editBtn);
        return td;
      };

    var makeButtonsForTableToDeleteQuiz = function(dataObj) {
      var td = document.createElement("td");
      var deleteBtn = document.createElement("button");
      deleteBtn.setAttribute("quiz_id", dataObj.id);
      deleteBtn.textContent = "Delete";

      deleteBtn.addEventListener('click', function(e) {
        e.preventDefault();
        var quizId = e.target.getAttribute("quiz_id");
        module.ajax("DELETE", "api/quizzes/" + quizId, function(req) {
          if (req.status < 400 && req.readyState === 4) {
            location.reload();
          } else {
            console.log("status: " + req.status + ", state: " + req.readyState);
          }
        });
      });

      td.appendChild(deleteBtn);
      return td;
    };

    var createBtn = document.getElementById('createBtn');
    createBtn.addEventListener('click', function(e){
      var quizId = e.target.getAttribute("quiz_id")
        console.log('Create Button');
        var table = document.getElementById('data-table');

        table.parentElement.removeChild(table);
        createBtn.parentElement.removeChild(createBtn);
        var form = document.createElement('form');
        var input = document.createElement('input');
        input.type = "text";
        input.placeholder = "name";
        var submit = document.createElement('input');
        submit.type = "submit";
        submit.value = "Submit";
        form.appendChild(input);
        form.appendChild(submit);
        document.body.appendChild(form);

        submit.addEventListener('click', function(e){
          form.remove();
          var newQuiz ={name: input.value};
          var jsonString = JSON.stringify(newQuiz);
          var xhr = new XMLHttpRequest();
          xhr.open('POST', 'api/quizzes');
          xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status < 400) {
              console.log(xhr.status);
              console.log(xhr.responseText);
            } else {
            console.log(xhr.status + " : " + xhr.responseText);
            }
          }
          xhr.send(jsonString);

          });

    });


  module.ajax = function(method, url, callback, requestBody) {
	    var xhr = new XMLHttpRequest();
	    xhr.open(method,url);

	    if (requestBody) {
	      xhr.setRequestHeader("Content-type","application/json");
	    } else {
	      requestBody = null;
	    }

	    xhr.onreadystatechange = function() {
	      callback(xhr);
	    };

	    xhr.send(requestBody);
	  };

  return module;
})();
