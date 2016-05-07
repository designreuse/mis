//confirm before deleting a dotor
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
