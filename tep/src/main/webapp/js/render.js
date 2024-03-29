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
    $('#showExam').css('display', 'none');
    $('#showHos').css('display', 'none');
    $('#showDocMsg').css('display', 'none');

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

// HERE

function showVisit(visit){
    console.log(visit);
    var data = new FormData();
    $('#illness').html(visit.illness);
    $('#timestamp').html(visit.date);
    $('#displayvisit').html('');
    $('#displayvisit').append('<p class="mb-1">Monitored By:</p>');
    $('#displayvisit').append('<p class="mb-1" style="color: blueviolet;">' + 'Dr. ' + visit.doctor.firstName + ' ' + visit.doctor.lastName + '</p>');
    $('#giveBtn').css('display', 'none');
    $('#nurseNote').attr('disabled', 'true');

    data.append('visit_id', visit.visitID);
    ajaxRequest('GET', 'http://localhost:8080/tep/examination', data, function(o){
        var res = JSON.parse(o.responseText);

        console.log(res);
        if(JSON.stringify(res) == '{}'){

            // No examination yet
            $('#showDocMsg').css('display', 'inline');
            $('#showExam').css('display', 'none');
            $('#showHos').css('display', 'none');
        }else{

            $('#showDocMsg').css('display', 'none');
            $('#showHos').css('display', 'none');
            $('#showExam').css('display', 'inline');
            renderDiagnosisPatient(visit, res);
        }
    });
}

function renderDiagnosisPatient(visit, examination){
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
        if(examination.tests[i].completed)
            $('#test-list').append('<li class="list-group-item list-group-item-info">' + examination.tests[i].type.charAt(0).toUpperCase() + examination.tests[i].type.slice(1) + '</li>');
        else
            $('#test-list').append('<li class="list-group-item list-group-item-danger">' + examination.tests[i].type.charAt(0).toUpperCase() + examination.tests[i].type.slice(1) + '</li>');
        if(examination.tests[i].completed == true) complete = true;
    }

    // Render status
    if(!complete){
        $('#visitStatus').html('Awaiting Nurse Test');
        $('#note').css('display', 'inline');
        $('#nurseNote').val('none');
    }else{
        $('#visitStatus').html('Test Completed. Awaiting Doctor Verdict.');
        $('#note').css('display', 'inline');
        $('#nurseNote').val(examination.note);
    }

    if(examination.hospitalized){
        $('#visitStatus').html('Patient Hospitalized');
        $('#nurseNote').val(examination.note);
        $('#note').css('display', 'inline');
    }
}

// DOCTOR

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
    $('#note').css('display', 'none');

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
        var sVisit = visit;
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

            showDoctorVisit(sVisit);
        });

    });

    $('#hospitalizeBtn').off('click').on('click', function(){
        var sVisit = visit;
        var visitID = visit.visitID;

        var hData = new FormData();
        hData.append('visit_id', visitID);

        ajaxRequest('POST', 'http://localhost:8080/tep/hospitalize', hData, function(o){
           
            showDoctorVisit(sVisit);
        });
    });

    // Prescribe drugs
    data.append('visit_id', visit.visitID);
    ajaxRequest('GET', 'http://localhost:8080/tep/examination', data, function(o){
        var res = JSON.parse(o.responseText);

        if(res.hospitalized){
            $('#visitStatus').html('Patient Hospitalized');
            $('#hospitalizeBtn').css('display', 'none');
        }

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
        if(examination.tests[i].completed)
            $('#test-list').append('<li class="list-group-item list-group-item-info">' + examination.tests[i].type.charAt(0).toUpperCase() + examination.tests[i].type.slice(1) + '</li>');
        else
            $('#test-list').append('<li class="list-group-item list-group-item-danger">' + examination.tests[i].type.charAt(0).toUpperCase() + examination.tests[i].type.slice(1) + '</li>');
        if(examination.tests[i].completed == true) complete = true;
    }

    // Render status
    console.log(complete);
    if(!complete){
        $('#visitStatus').html('Awaiting Nurse Test');
        $('#hospitalizeBtn').css('display', 'none');
        $('#note').css('display', 'none');
    }else{
        $('#visitStatus').html('Nurse Test Complete. Select wether the patient needs to be hospitalized');
        $('#hospitalizeBtn').css('display', 'inline');
        $('#note').css('display', 'inline');
        $('#nurseNote').val(examination.note);
    }

    if(examination.hospitalized){
        $('#visitStatus').html('Patient Hospitalized');
        $('#hospitalizeBtn').css('display', 'none');
        $('#note').css('display', 'inline');
        $('#nurseNote').val(examination.note);
    }
}

// NURSE

function renderNurse(res){
    $('#showExam').css('display', 'none');
    $('#showHos').css('display', 'none');
    $('#showDocMsg').css('display', 'none');

    // Render data
    console.log(res);
    $('#usernameProfile').val(session.username);
    $('#passwordProfile').val(session.password);
    $('#firstName').val(res.firstName);
    $('#lastName').val(res.lastName);

    ajaxRequest('GET', 'http://localhost:8080/tep/allVisits', undefined, function(o){
        var r = JSON.parse(o.responseText);

        fetch('patientPreset.html')
            .then(r => r.text())
            .then(text => renderNurseVisits(text, r));
    });
}

function renderNurseVisits(preset, visits){
    for(let i = visits.length - 1; i >= 0; i--){
        let curVisit = preset;
        curVisit = curVisit.replace('VISITID_H', visits[i].visitID);
        curVisit = curVisit.replace('ILLNESS_H', visits[i].illness);
        curVisit = curVisit.replace('TIMESTAMP_H', visits[i].date);
        curVisit = curVisit.replace('DOCTOR_H', visits[i].firstName + ' ' + visits[i].lastName);

        $('#visit-list').append(curVisit);
        $('#visit' + visits[i].visitID).on('click', function(e){
            $('#visit-list a').removeAttr('active');
            showNurseVisit(visits[i]);
        });
    }
}

function showNurseVisit(visit){
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
    $('#showDocMsg').css('display', 'none');
    $('#showHos').css('display', 'none');
    $('#note').css('display', 'none');

    $('#giveBtn').off('click').on('click', function(){
        var sVisit = visit;
        var makeData = new FormData();
        makeData.append('visit_id', visit.visitID);
        makeData.append('note', $('#nurseNote').val());
        
        ajaxRequest('GET', 'http://localhost:8080/tep/makeTests', makeData, function(o){

            showNurseVisit(sVisit);
        });

    });

    
    data.append('visit_id', visit.visitID);
    ajaxRequest('GET', 'http://localhost:8080/tep/examination', data, function(o){
        var res = JSON.parse(o.responseText);

        console.log(res);
        if(JSON.stringify(res) == '{}'){

            // No examination yet
            $('#showDocMsg').css('display', 'inline');
            $('#showExam').css('display', 'none');
            $('#showHos').css('display', 'none');
        }else{

            $('#showDocMsg').css('display', 'none');
            $('#showHos').css('display', 'none');
            $('#showExam').css('display', 'inline');
            renderDiagnosisNurse(visit, res);
        }
    });
}

function renderDiagnosisNurse(visit, examination){
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
        if(examination.tests[i].completed)
            $('#test-list').append('<li class="list-group-item list-group-item-info">' + examination.tests[i].type.charAt(0).toUpperCase() + examination.tests[i].type.slice(1) + '</li>');
        else
            $('#test-list').append('<li class="list-group-item list-group-item-danger">' + examination.tests[i].type.charAt(0).toUpperCase() + examination.tests[i].type.slice(1) + '</li>');
        if(examination.tests[i].completed == true) complete = true;
    }

    // Render status
    if(!complete){
        $('#visitStatus').html('Awaiting Nurse Test');
        $('#giveBtn').css('display', 'inline');
        $('#nurseNote').removeAttr('disabled');
        $('#note').css('display', 'inline');
        $('#nurseNote').val('none');
    }else{
        $('#visitStatus').html('Test Completed. Awaiting Doctor Verdict.');
        $('#giveBtn').css('display', 'none');
        $('#note').css('display', 'inline');
        $('#nurseNote').attr('disabled', 'true');
        $('#nurseNote').val(examination.note);
    }

    if(examination.hospitalized){
        $('#visitStatus').html('Patient Hospitalized');
        $('#giveBtn').css('display', 'none');
        $('#nurseNote').val(examination.note);
        $('#nurseNote').attr('disabled', 'true');
        $('#note').css('display', 'inline');
    }
}

function renderEmployee(res){
    $('#showExam').css('display', 'none');
    $('#showHos').css('display', 'none');
    $('#showDocMsg').css('display', 'none');
    $('#editShiftBtn').css('display', 'inline');

    // Render data
    console.log(res);
    $('#usernameProfile').val(session.username);
    $('#passwordProfile').val(session.password);
    $('#firstName').val(res.firstName);
    $('#lastName').val(res.lastName);

    ajaxRequest('GET', 'http://localhost:8080/tep/all', undefined, function(o){
        var r = JSON.parse(o.responseText);

        fetch('employeePreset.html')
            .then(r => r.text())
            .then(text => renderEmployeeVisits(text, r));
    });
}

function renderEmployeeVisits(preset, visits){
    for(let i = visits.length - 1; i >= 0; i--){
        let curVisit = preset;
        if(visits[i].job == 'DOCTOR')
            curVisit = curVisit.replace('DOCTOR_H', 'Dr. ' + visits[i].firstName + ' ' + visits[i].lastName);
        else
            curVisit = curVisit.replace('DOCTOR_H', visits[i].firstName + ' ' + visits[i].lastName);

        curVisit = curVisit.replace('USERID_H', visits[i].userID);

        $('#visit-list').append(curVisit);
        $('#visit' + visits[i].userID).on('click', function(e){
            $('#visit-list a').removeAttr('active');
            showEmployeeVisit(visits[i]);
        });
    }
}

function showEmployeeVisit(profile){
    var data = new FormData();
    console.log(profile);
    $('#displayvisit').html('');
    $('#drugSelect').html('');
    $('#testSelect').html('<option value="none" selected>None</option>');
    $('#diagnosisSelect').html('');
    if(profile.job == 'DOCTOR')
        $('#displayvisit').append('<p class="mb-1" style="color: blueviolet;">Dr. ' + profile.firstName + ' ' + profile.lastName + '</p>');
    else
        $('#displayvisit').append('<p class="mb-1" style="color: blueviolet;">' + profile.firstName + ' ' + profile.lastName + '</p>');
    $('#showExam').css('display', 'none');
    $('#showDocMsg').css('display', 'none');
    $('#showHos').css('display', 'none');
    $('#note').css('display', 'none');
    $('#showStaff').css('display', 'none');

    if(profile.job == 'PATIENT'){
        $('#showStaff').css('display', 'none');
        $('#showExam').css('display', 'inline');
        $('#patientFirstName').val(profile.firstName);
        $('#patientLastName').val(profile.lastName);
        $('#patientAMKA').val(profile.AMKA);
        $('#patientAdress').val(profile.address);
        $('#patientInstitution').val(profile.institution);

        fetch('patientPreset.html')
            .then(r => r.text())
            .then(text => renderVisitsForPatient(text, profile.visits));
    }else{
        $('#showStaff').css('display', 'inline');
        $('#showExam').css('display', 'none');
        $('#staffFirstName').val(profile.firstName);
        $('#staffLastName').val(profile.lastName);
    }
}

function renderVisitsForPatient(preset, visits){
    $('#patient-visit-list').html('');
    for(let i = visits.length - 1; i >= 0; i--){
        let curVisit = preset;
        curVisit = curVisit.replace('VISITID_H', visits[i].visitID);
        curVisit = curVisit.replace('ILLNESS_H', visits[i].illness);
        curVisit = curVisit.replace('TIMESTAMP_H', visits[i].date);
        curVisit = curVisit.replace('DOCTOR_H', 'Dr. ' + visits[i].doctor.firstName + ' ' + visits[i].doctor.lastName);

        $('#patient-visit-list').append(curVisit);
    }
}