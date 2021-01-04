function execute(){
    var data = new FormData();
    data.append('query', $('#query').val());

    ajaxRequest('GET', 'http://localhost:8080/tep/query', data, function(o){
        var res = JSON.parse(o.responseText);

        console.log(res);
        for(let i = 0; i < res.length; i++){
            var item = '<li class="list-group-item"><div class="row">';

            for(let j = 0; j < res[i].length; j++){
                var sitem = '<div class="col-sm">';
                sitem += res[i][j];
                sitem += '</div>';
                item += sitem;
            }

            item += '</div></li>';
            $('#queryList').append(item);;
        }
    });
}

function covid(){
    $('#query').val('SELECT patients.amka, patients.first_name, patients.last_name, patients.addrss, patients.institution FROM ');
}