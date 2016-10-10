window.addEventListener('load', function() {
  restDataModule.ajax("GET", "api/quizzes", function(request) {
    if (request.readyState === 4 && request.status < 400) {
      var data = JSON.parse(request.responseText);
      restDataModule.tableBuilder(data,"data-table");
    }
  });
});
