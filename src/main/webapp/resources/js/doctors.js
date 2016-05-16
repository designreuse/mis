//confirm before deleting a doctor
$(".deleteDoctor").click(function (e) {
    console.log($("body").attr('data-url') + 'doctors/delete/' + $(this).attr('data-id'))
    if (confirm('Are you sure you want to delete the doctor?')) {

        $.ajax({
            url: $("body").attr('data-url') + 'doctors/delete/' + $(this).attr('data-id'),
            type: 'DELETE'
        }).done(function (data) {
            window.location.href = $("body").attr('data-url') + "doctors/";
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

console.log($("#geolocationAreaId option:selected").val())
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
        success: function(data){
            console.log(data)
        }
    });
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
