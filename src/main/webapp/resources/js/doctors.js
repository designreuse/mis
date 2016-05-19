//confirm before deleting a doctor
$(".deleteDoctor").click(function (e) {
    if (confirm('Are you sure you want to delete the doctor?')) {
        $.ajax({
            url: $("body").attr('data-url') + 'doctors/delete/' + $(this).attr('data-id'),
            type: 'DELETE'
        }).done(function (data) {
            if (!data)
                window.location.href = $("body").attr('data-url') + "doctors/";
            else
                alert("The doctor has paid visits and cannot be deleted");
        });
    }
});

//when a geographical area is selected, fetch its cities and institutions
$('#geolocationAreaId').on('change', function () {

    if ($(this).val() != 0) {
        $.ajax({
            url: $("body").attr('data-url') + 'geolocationAreas/' + $(this).val(),
            type: 'GET',
            success: function (data) {

                fillSelect('cityId', data.cities);
                fillSelect('institutionId', data.institutions);
            }
        });
    } else {
        fillSelect('cityId', []);
        fillSelect('institutionId', []);
        $('select[name="cityId"]').prop('disabled', 'disabled');
        $('select[name="institutionId"]').prop('disabled', 'disabled');
    }

});

//fill a select based on an array
function fillSelect(selectName, array) {
    var options = "";
    //fill the cities options
    for (var i = 0; i < array.length; i++) {
        options += "<option value='" + array[i].id + "'>" + array[i].name + "</option>";
    }
    $('select[name="' + selectName + '"]').html(options);
    $('select[name="' + selectName + '"]').prop('disabled', false);
}

//when a cycle is selected, fetch the available doctors of this period
$('#cycleId').on('change', function () {

    $.ajax({
        url: $("body").attr('data-url') + 'doctors/byCycle/' + $(this).val(),
        type: 'GET',
        success: function (data) {
            var options = "";
            for (var i = 0; i < data.length; i++) {
                options += "<option value='" + data[i].id + "'>" + data[i].firstName + " " + data[i].lastName + "</option>";

            }
            $('select[name="doctorId"]').html(options);
            $('select[name="doctorId"]').prop('disabled', false);
        }
    });
});

//when a cycle is selected, fetch the available doctors of this period
$('#cycleIdGroup').on('change', function () {
    console.log($(this).val())
    $.ajax({
        url: $("body").attr('data-url') + 'doctors/byCycle/' + $(this).val(),
        type: 'GET',
        success: function (data) {
            var options = "";
            for (var i = 0; i < data.length; i++) {
                options += "<option value='" + data[i].id + "'>" + data[i].firstName + " " + data[i].lastName + "</option>";

            }
            $('select[name="doctorIdGroup"]').html(options);
            $('select[name="doctorIdGroup"]').prop('disabled', false);
        }
    });
});

//search for doctors
$("#search").click(function (e) {

    var firstName = $("#firstName").val();
    var lastName = $("#lastName").val();
    var address = $("#address").val();
    var phone = $("#phone").val();
    var email = $("#email").val();
    var position = $("#position").val();
    var geolocationAreaId = ($("#geolocationAreaId option:selected").val() == 0 ? null : $("#geolocationAreaId option:selected").val());
    var cityId = ($("#cityId option:selected").val() == 0 ? null : $("#cityId option:selected").val());
    var institutionId = ($("#institutionId option:selected").val() == 0 ? null : $("#institutionId option:selected").val());
    var specialtyId = ($("#specialtyId option:selected").val() == 0 ? null : $("#specialtyId option:selected").val());

    $.ajax({
        url: $("body").attr('data-url') + 'doctors/search/',
        type: 'GET',
        data: {
            firstName: firstName,
            lastName: lastName,
            address: address,
            email: email,
            phone: phone,
            position: position,
            geolocationAreaId: geolocationAreaId,
            cityId: cityId,
            institutionId: institutionId,
            specialtyId: specialtyId
        },
        success: function (data) {
            var html = '';
            //draw the table again with the new data
            $.each(data, function (i, value) {
                html += '<tr>';
                html += '<td>' + value.id + '</td>';
                html += '<td>' + value.firstName + ' ' + value.lastName + '</td>';
                html += '<td>' + value.email + '</td>';
                html += '<td>' + value.position + '</td>';
                html += '<td>' + value.institutionName + '</td>';
                html += '<td>' + value.specialtyName + '</td>';
                html += '<td><a href="' + $("body").attr('data-url') + 'doctors/one/' + value.id + '" class="btn btn-info btn-sm btn-30"><i class="fa fa-eye"></i></a>';

                if (value.editable)
                    html += '<a href="' + $("body").attr('data-url') + 'doctors/edit/' + value.id + '" class="btn btn-success btn-sm btn-30"><i class="fa fa-edit"></i></a>';

                if (value.deletable)
                    html += '<button type="button" class="btn btn-sm btn-danger btn-30 deleteDoctor" data-id="' + value.id + '"><i class="fa fa-trash"></i></button>';

                html += '</td></tr>';
            });
            $("#doctorsTable tbody").html(html);
        }
    });
});

//clear all selections
$("#clear").click(function () {
    $("#firstName").val("");
    $("#lastName").val("");
    $("#address").val("");
    $("#phone").val("");
    $("#email").val("");
    $("#position").val("");
    $("#geolocationAreaId").val(0);
    $("#cityId").append($('<option>', {value: 0, text: 'City'}));
    $("#cityId").val(0);
    $("#institutionId").append($('<option>', {value: 0, text: 'Institution'}));
    $("#institutionId").val(0);
    $("#specialtyId").val(0);

    $("#cityId").prop('disabled', 'disabled');
    $("#institutionId").prop('disabled', 'disabled');

});

//before saving a doctor, check that the first name, last name and address are unique
$("#saveDoctor").submit(function (e) {
    e.preventDefault();

    var id = $(this).attr('data-id');

    $.when(checkUnique(id)).then(function (data, textStatus, jqXHR) {

        if (data.length > 0) {
            $(".error.isUnique").show();
        } else {
            $(".error.isUnique").hide();
            $.ajax({
                type: "POST",
                url: $("body").attr("data-url") + 'doctors/' + $("#saveDoctor").attr('data-mode'),
                data: $('#saveDoctor').serialize(),
                success: function (response) {
                    window.location.href = $("body").attr('data-url') + "doctors/";
                },
                error: function (response) {
                    $(".error.fillFields").show();
                }
            });
        }
    });
});

//check that fields are unique
function checkUnique(id) {

    var firstName = $("#firstName").val();
    var lastName = $("#lastName").val();
    var address = $("#address").val();

    return $.ajax({
        url: $("body").attr('data-url') + 'doctors/checkUnique/',
        type: 'GET',
        data: {
            firstName: firstName,
            lastName: lastName,
            address: address,
            id: id
        }
    });
}
