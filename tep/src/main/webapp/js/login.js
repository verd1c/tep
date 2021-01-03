'use strict';

function login(){
    var data = new FormData();
    data.append('username', $('#username').val());
    data.append('password', $('#password').val());
    session.username = $('#username').val();
    session.password = $('#password').val();

    ajaxRequest('POST', 'http://localhost:8080/tep/login', data, function(o){
        var res = JSON.parse(o.responseText);

        if(o.responseText == '{}'){
            $('#loginError').text('Wrong Info');
            $('#loginError').css('color', 'red');
            return;
        }

        $('#loginError').text('');
        $('#myModal').modal('hide');
        $('#username').val('');
        $('#password').val('');

        session.userID = res.userID;
        session.state = states.LOGGED_IN;
        session.page = pages.PROFILE;
        switch(res.job){
            case 'PATIENT':
                $('#maincontainer').load('patient.html');
                break;
            case 'DOCTOR':
                $('#maincontainer').load('doctor.html');
                break;
        }
    });
}

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

        fetch('visitPreset.html')
                .then(r => r.text())
                .then(text => renderVisits(text, response));
    });
}

function renderVisits(preset, visits){
    for(let i = 0; i < visits.length; i++){
        let curVisit = preset;
        curVisit = curVisit.replace('VISITID_H', visits[i].visitID);
        curVisit = curVisit.replace('ILLNESS_H', visits[i].illness);
        curVisit = curVisit.replace('TIMESTAMP_H', visits[i].date);

        $('#visit-list').append(curVisit);
        $('#visit' + visits[i].visitID).on('click', function(e){
            $('#visit-list a').removeAttr('active');
            showVisit(visits[i]);
        });
    }
}

function showVisit(visit){
    console.log(visit);
}