'use strict';

function initVisit(){
    if(session.page !== pages.VISIT){
        console.log('Aa');
        $('#maincontainer').load('visit.html'); // Load visit page
        session.page = pages.VISIT;
    }
}

function initProfile(){
    if(session.page !== pages.PROFILE){
        $('#maincontainer').load('profile.html');
        session.page = pages.PROFILE;
    }
}

function logout(){
    session.userID = 0;
    session.amka = 0;
    session.username = 'none';
    session.password = 'none';
    session.state = states.LOGGED_OUT;
    initVisit();
}

initVisit();
session.page = pages.VISIT;

// Visit
$(document).on('DOMNodeInserted', function(e){
    if($(e.target).hasClass('s-visit')){
        $('#visitBtn').on('click', visit);
    }
});

// Patient
$(document).on('DOMNodeInserted', function(e){
    if($(e.target).hasClass('s-patient-profile')){
        var data = new FormData();
        data.append('user_id', session.userID);
        $('#editButton').off('click').on('click', patientEditProfile);
        
        ajaxRequest('GET', 'http://localhost:8080/tep/patient', data, function(o){
            var res = JSON.parse(o.responseText);
            renderPatient(res);
        });
    }
});

// Doctor
$(document).on('DOMNodeInserted', function(e){
    if($(e.target).hasClass('s-doctor-profile')){
        var data = new FormData();
        data.append('user_id', session.userID);
        $('#staffEditButton').off('click').on('click', staffEditProfile);
        
        ajaxRequest('GET', 'http://localhost:8080/tep/doctor', data, function(o){
            var res = JSON.parse(o.responseText);
            renderDoctor(res);
        });
    }
});

// Nurse
$(document).on('DOMNodeInserted', function(e){
    if($(e.target).hasClass('s-nurse-profile')){
        var data = new FormData();
        data.append('user_id', session.userID);
        $('#staffEditButton').off('click').on('click', staffEditProfile);

        ajaxRequest('GET', 'http://localhost:8080/tep/nurse', data, function(o){
            var res = JSON.parse(o.responseText);
            renderNurse(res);
        });
    }
});

// Employee
$(document).on('DOMNodeInserted', function(e){
    if($(e.target).hasClass('s-employee-profile')){
        var data = new FormData();
        data.append('user_id', session.userID);
        $('#staffEditButton').off('click').on('click', staffEditProfile);

        ajaxRequest('GET', 'http://localhost:8080/tep/employee', data, function(o){
            var res = JSON.parse(o.responseText);
            renderEmployee(res);
        });
    }
});

$('#loginBtn').on('click', login);
$('#logoutBtn').on('click', logout);
$('#editShiftBtn').off('click').on('click', editShift);
$('#executeBtn').on('click', execute);
$('#covidBtn').on('click', covid);
$('#statisticsBtn').on('click', statistics);
$('#visitStatusBtn').on('click', visitStatus);
$('#shiftsBtn').on('click', shifts);
$('#logoutBtn').css('display', 'none');
$('#queryResult').css('display', 'none');
$('#shiftDiv').css('display', 'none');
$('#queryList').css('display', 'none');
$('#editShiftBtn').css('display', 'none');
$('#shiftBtn').on('click', function(){
    ajaxRequest('GET', 'http://localhost:8080/tep/shift', undefined, function(o){
        var res = JSON.parse(o.responseText);
        $('#shiftID').html('Shift ' + res.shiftID);
        console.log(res);
        $('#shiftList').html('');
        for(let i = 0; i < res.attendees.length; i++){
            $('#shiftList').append('<li class="list-group-item">' + res.attendees[i].firstName + ' ' + res.attendees[i].lastName + '</li>');
        }
    });
});