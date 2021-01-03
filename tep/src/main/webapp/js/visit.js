'user strict';

function visit(){
    var data = new FormData();
    data.append('firstName', $('#firstName').val());
    data.append('lastName', $('#lastName').val());
    data.append('amka', $('#amka').val());
    data.append('illness', $('#illness option:selected').text());
    data.append('address', $('#address').val());
    data.append('institution', $('#institution').val());


    ajaxRequest('POST', 'http://localhost:8080/tep/visit', data, function(o){
        var res = JSON.parse(o.responseText);
    });
}