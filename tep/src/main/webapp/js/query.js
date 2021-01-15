function execute(callback){
    var data = new FormData();
    $('#queryList').css('display', 'flex');
    data.append('query', $('#query').val());

    ajaxRequest('GET', 'http://localhost:8080/tep/query', data, function(o){
        var res = JSON.parse(o.responseText);

        $('#queryList').html('');
        if(callback !== undefined && typeof callback === 'function') callback(res);
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

function visitStatus(){
    $('#queryList').css('display', 'flex');
    $('#query').val("SELECT p.amka, p.first_name, p.last_name, m.completed FROM patients p INNER JOIN visits v on p.amka = v.amka INNER JOIN medicaltests m on m.visit_id = v.visit_id;");
    execute();
    $('#queryResult').css('display', 'none');
}

function statistics(){
    $('#queryList').css('display', 'flex');
    $('#query').val("SELECT * FROM visits v INNER JOIN examinations e on v.visit_id = e.visit_id INNER JOIN diagnoses d on v.visit_id = d.visit_id INNER JOIN prescriptions p on p.visit_id = v.visit_id;");
    execute(function(res){
        console.log(res);
        let diagnoses = new Map();
        let drugs = new Map();
        $('#queryResult').css('display', 'flex');
        $('#queryResult').html('');

        // Total
        $('#queryResult').append('<li class="list-group-item">There was a total of ' + res.length - 1 +' incidents</li>');

        // Diagnoses
        for(let i = 0; i < res.length; i++){
            if(!diagnoses.has(res[i][14])){
                diagnoses.set(res[i][14], 1);
            }else{
                diagnoses.set(res[i][14], diagnoses.get(res[i][14]) + 1);
            }
        }

        for(let [key, value] of diagnoses.entries()){
            if(key == 'name') continue;
            $('#queryResult').append('<li class="list-group-item">There was ' + value + ' total ' + key + ' cases</li>');
        }

        // Drugs
        for(let i = 0; i < res.length; i++){
            if(!drugs.has(res[i][17])){
                drugs.set(res[i][17], 1);
            }else{
                drugs.set(res[i][17], drugs.get(res[i][17]) + 1);
            }
        }

        for(let [key, value] of drugs.entries()){
            if(key == 'drugs') continue;
            $('#queryResult').append('<li class="list-group-item">There was a total of ' + value + ' ' + key + '(s) prescribed</li>');
        }

    });
}

function covid(){
    $('#queryList').css('display', 'flex');
    $('#query').val("SELECT p.amka, p.first_name, p.last_name FROM patients p INNER JOIN visits v on p.amka = v.amka INNER JOIN diagnoses d on d.name = 'covid' AND d.visit_id = v.visit_id;");
    execute();
    $('#queryResult').css('display', 'none');
}

function shifts(){
    $('#queryList').css('display', 'flex');
    $('#query').val("SELECT s.shift_id, d.user_id, d.first_name, d.last_name FROM doctors d INNER JOIN shift_attendee s on d.user_id = s.user_id UNION SELECT a.shift_id, n.user_id, n.first_name, n.last_name FROM nurses n INNER JOIN shift_attendee a on n.user_id = a.user_id;");
    execute(function(res){
        let shifts = new Map();
        $('#queryResult').css('display', 'flex');
        $('#queryResult').html('');

        for(let i = 0; i < res.length; i++){
            if(!shifts.has(res[i][1])){
                shifts.set(res[i][1], 1);
            }else{
                shifts.set(res[i][1], shifts.get(res[i][1]) + 1);
            }
        }

        for(let [key, value] of shifts.entries()){
            if(key == 'user_id') continue;
            $('#queryResult').append('<li class="list-group-item">' + findNameByID(res, key) + ' worked ' + value +' shifts</li>');
        }
    });
}

function findNameByID(res, id){
    var name = "";

    for(let i = 0; i < res.length; i++){
        if(res[i][1] == id){
            name += res[i][2] + ' ' + res[i][3];
            return name;
        }
    }
}