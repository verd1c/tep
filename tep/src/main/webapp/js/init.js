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

        ajaxRequest('GET', 'http://localhost:8080/tep/allVisits', undefined, function(o){
            var res = JSON.parse(o.responseText);
            renderNurse(res);
        });
    }
});

$('#loginBtn').on('click', login);
$('#logoutBtn').on('click', logout);
$('#executeBtn').on('click', execute);
$('#covidBtn').on('click', covid);
$('#logoutBtn').css('display', 'none');