function renderPatient(res){
    // Render data
    $('#usernameProfile').val(session.username);
    $('#passwordProfile').val(session.password);
    $('#firstName').val(res.firstName);
    $('#lastName').val(res.lastName);
    $('#amka').val(res.AMKA);
    $('#address').val(res.address);
    $('#institution').val(res.institution);
    $('#welcome').html('Welcome, ' + res.firstName);

    // Render visits
    var data = new FormData();
    data.append('id', res.AMKA);

    ajaxRequest('GET', 'http://localhost:8080/tep/visit', data, function(o){
        var response = JSON.parse(o.responseText);
        console.log(response);
        fetch('visitPreset.html')
                .then(r => r.text())
                .then(text => renderVisits(text, response));
    });
}

function renderVisits(preset, visits){
    for(let i = visits.length - 1; i >= 0; i--){
        let curVisit = preset;
        curVisit = curVisit.replace('VISITID_H', visits[i].visitID);
        curVisit = curVisit.replace('ILLNESS_H', visits[i].illness);
        curVisit = curVisit.replace('TIMESTAMP_H', visits[i].date);
        curVisit = curVisit.replace('DOCTOR_H', 'Dr. ' + visits[i].doctor.firstName + ' ' + visits[i].doctor.lastName);

        $('#visit-list').append(curVisit);
        $('#visit' + visits[i].visitID).on('click', function(e){
            $('#visit-list a').removeAttr('active');
            showVisit(visits[i]);
        });
    }
}

function showVisit(visit){
    console.log(visit);
    $('#illness').html(visit.illness);
    $('#timestamp').html(visit.date);
    $('#displayvisit').html('');
    $('#displayvisit').append('<p class="mb-1">Monitored By:</p>');
    $('#displayvisit').append('<p class="mb-1" style="color: blueviolet;">' + 'Dr. ' + visit.doctor.firstName + ' ' + visit.doctor.lastName + '</p>');
    $('#displayvisit').append('<p class="mb-1">The following drugs were prescribed:</p>');
}

function renderDoctor(res){
    $('#showExam').css('display', 'none');
    $('#selectExam').css('display', 'none');

    // Render data
    console.log(res);
    $('#usernameProfile').val(session.username);
    $('#passwordProfile').val(session.password);
    $('#firstName').val(res.firstName);
    $('#lastName').val(res.lastName);
    $('#specialization').val(res.specialization.charAt(0) + res.specialization.slice(1).toLowerCase());

    fetch('patientPreset.html')
        .then(r => r.text())
        .then(text => renderDoctorVisits(text, res.visits));
}

function renderDoctorVisits(preset, visits){
    for(let i = visits.length - 1; i >= 0; i--){
        let curVisit = preset;
        curVisit = curVisit.replace('VISITID_H', visits[i].visitID);
        curVisit = curVisit.replace('ILLNESS_H', visits[i].illness);
        curVisit = curVisit.replace('TIMESTAMP_H', visits[i].date);
        curVisit = curVisit.replace('DOCTOR_H', visits[i].firstName + ' ' + visits[i].lastName);

        $('#visit-list').append(curVisit);
        $('#visit' + visits[i].visitID).on('click', function(e){
            $('#visit-list a').removeAttr('active');
            showDoctorVisit(visits[i]);
        });
    }
}

function showDoctorVisit(visit){
    var data = new FormData();

    $('#illness').html(visit.illness);
    $('#timestamp').html(visit.date);
    $('#displayvisit').html('');
    $('#drugSelect').html('');
    $('#testSelect').html('<option value="none" selected>None</option>');
    $('#diagnosisSelect').html('');
    $('#displayvisit').append('<p class="mb-1">Monitoring:</p>');
    $('#displayvisit').append('<p class="mb-1" style="color: blueviolet;">' + visit.firstName + ' ' + visit.lastName + '</p>');
    $('#showExam').css('display', 'none');
    $('#selectExam').css('display', 'none');

    $('#prescribeBtn').off('click').on('click', function(){
        var visitID = visit.visitID;

        var pData = new FormData();
        pData.append('visit_id', visitID);
        pData.append('drug', $('#drugSelect').val());
        ajaxRequest('POST', 'http://localhost:8080/tep/drug', pData, function(o){

        });
    });

    $('#appointBtn').off('click').on('click', function(){
        var visitID = visit.visitID;

        var aData = new FormData();
        aData.append('visit_id', visitID);
        aData.append('type', $('#testSelect').val());
        if($('#drugSelect').val() == 'none') return;

        ajaxRequest('POST', 'http://localhost:8080/tep/test', aData, function(o){

        });
    });

    $('#examBtn').off('click').on('click', function(){
        var visitID = visit.visitID;
        var amka = visit.AMKA;
        var doctor_id = visit.doctorID;

        var eData = new FormData();
        eData.append('visit_id', visitID);
        eData.append('amka', amka);
        eData.append('doctor_id', doctor_id);
        eData.append('diagnosis', $('#diagnosisSelect').val());

        ajaxRequest('POST', 'http://localhost:8080/tep/examination', eData, function(o){
            var resp = JSON.parse(o.responseText);
            console.log(resp);
        });
    });

    // Prescribe drugs
    data.append('visit_id', visit.visitID);
    ajaxRequest('GET', 'http://localhost:8080/tep/examination', data, function(o){
        var res = JSON.parse(o.responseText);

        console.log(res);
        if(JSON.stringify(res) == '{}'){
            // No examination yet
            $('#selectExam').css('display', 'inline');
            $('#showExam').css('display', 'none');
            renderOptionsExamp(visit);
        }else{
            $('#selectExam').css('display', 'none');
            $('#showExam').css('display', 'inline');
            renderDiagnosis(visit, res);
        }
    });
}

function renderOptionsExamp(visit){
    var data = new FormData();

    data.append('illness', visit.illness);

    // Drugs
    ajaxRequest('GET', 'http://localhost:8080/tep/drug', data, function(o){
        var res = JSON.parse(o.responseText);
        console.log(res);
        for(let i = 0; i < res.length; i++){
            $('#drugSelect').append('<option value="' + res[i].name + '">' + res[i].name + ' (' + res[i].type + ' ' + res[i].density + 'mg)</option>');
        }
    });

    // Medical Tests
    ajaxRequest('GET', 'http://localhost:8080/tep/test', data, function(o){
        var res = JSON.parse(o.responseText);
        console.log(res);
        for(let i = 0; i < res.length; i++){
            $('#testSelect').append('<option value="' + res[i].type + '">' + res[i].type + '</option>');
        }
    });

    // Diagnosis
    ajaxRequest('GET', 'http://localhost:8080/tep/diagnosis', data, function(o){
        var res = JSON.parse(o.responseText);
        console.log(res);
        for(let i = 0; i < res.length; i++){
            $('#diagnosisSelect').append('<option value="' + res[i].name + '">' + res[i].name + '</option>');
        }
    });    
}

function renderDiagnosis(visit, examination){
    $('#drug-list').html('');
    $('#test-list').html('');
    var complete = false;

    // Render diagnosis
    $('#diagnosisResult').html('Diagnosis: ' + examination.diagnosis.charAt(0).toUpperCase() + examination.diagnosis.slice(1));

    // Render drugs
    for(let i = 0; i < examination.drugs.length; i++){
        var drug = examination.drugs[i].name + ' (' + examination.drugs[i].type + ' ' + examination.drugs[i].density + 'mg)';
        $('#drug-list').append('<li class="list-group-item list-group-item-info">' + drug + '</li>');
    }

    // Render tests
    for(let i = 0; i < examination.tests.length; i++){
        var test = examination.tests[i].type.charAt(0).toUpperCase() + examination.tests[i].type.slice(1) + ' ' + examination.tests[i].completed;
        $('#test-list').append('<li class="list-group-item list-group-item-info">' + test + '</li>');
        if(examination.tests[i].completed == true) complete = true;
    }

    // Render status
    console.log(complete);
    if(!complete){
        $('#visitStatus').html('Awaiting Nurse Test');
        $('#hospitalizeBtn').css('display', 'flex');
    }else{
        $('#visitStatus').html('Nurse Test Complete. Select wether the patient needs to be hospitalized');
        $('#hospitalizeBtn').css('display', 'none');
    }
}