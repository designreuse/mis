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
console.log($(this).val())
        $.ajax({
            url: $("body").attr('data-url') + 'doctors/byCycle/' + $(this).val(),
            type: 'GET',
            success: function (data) {
                var options = "";
                for(var i = 0; i < data.length; i++){
                   options += "<option value='" + data[i].id + "'>" + data[i].firstName+" "+data[i].lastName + "</option>";
                   
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
                for(var i = 0; i < data.length; i++){
                   options += "<option value='" + data[i].id + "'>" + data[i].firstName+" "+data[i].lastName + "</option>";
                   
                }
                $('select[name="doctorIdGroup"]').html(options);
                $('select[name="doctorIdGroup"]').prop('disabled', false);
            }
        });
});



