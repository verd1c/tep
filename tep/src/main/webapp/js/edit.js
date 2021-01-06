function staffEditProfile(){
    $('#firstName').removeAttr('disabled');
    $('#lastName').removeAttr('disabled');
    $('#staffEditButton').val('Submit Edits');
    $('#staffEditButton').off('click').on('click', function(){
        $('#firstName').attr('disabled', 'true');
        $('#lastName').attr('disabled', 'true');
        $('#staffEditButton').val('Edit');
        $('#staffEditButton').off('click').on('click', patientEditProfile);

        var data = new FormData();
        data.append('user_id', session.userID);
        data.append('firstName', $('#firstName').val());
        data.append('lastName', $('#lastName').val());
        ajaxRequest('POST', 'http://localhost:8080/tep/staff', data);
    });
}

function patientEditProfile(){
    $('#firstName').removeAttr('disabled');
    $('#lastName').removeAttr('disabled');
    $('#address').removeAttr('disabled');
    $('#institution').removeAttr('disabled');
    $('#editButton').val('Submit Edits');
    $('#editButton').off('click').on('click', function(){
        $('#firstName').attr('disabled', 'true');
        $('#lastName').attr('disabled', 'true');
        $('#address').attr('disabled', 'true');
        $('#institution').attr('disabled', 'true');
        $('#editButton').val('Edit');
        $('#editButton').off('click').on('click', patientEditProfile);

        var data = new FormData();
        data.append('user_id', session.userID);
        data.append('firstName', $('#firstName').val());
        data.append('lastName', $('#lastName').val());
        data.append('address', $('#address').val());
        data.append('institution', $('#institution').val());
        ajaxRequest('POST', 'http://localhost:8080/tep/patient', data);
    });
}

function editShift(){
    $('#shiftDiv').css('display', 'inline');
    $('#editShiftBtn').val('Update Shift');

    $('#editShiftBtn').off('click').on('click', function(){
        $('#editShiftBtn').val('Edit Shift');
        $('#shiftDiv').css('display', 'none');
        $('#editShiftBtn').off('click').on('click', editShift);
        var data = new FormData();
        data.append('user_ids', $('#shiftIDS').val());

        ajaxRequest('POST', 'http://localhost:8080/tep/shift', data, function(o){
            var res = JSON.parse(o.responseText);
        });
    });
}