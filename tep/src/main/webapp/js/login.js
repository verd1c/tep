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

        // Load on job
        switch(res.job){
            case 'PATIENT':
                $('#maincontainer').load('patient.html');
                break;
            case 'DOCTOR':
                $('#maincontainer').load('doctor.html');
                break;
            case 'NURSE':
                $('#maincontainer').load('nurse.html');
                break;
            case 'EMPLOYEE':
                $('#maincontainer').load('employee.html');
                break;
        }
    });
}