//confirm before deleting a visit
$(".deleteVisit").click(function (e) {
    console.log($("body").attr('data-url') + 'scheduledVisits/delete/' + $(this).attr('data-id'))

    if (confirm('Are you sure you want to delete the scheduled visit? Any paid visits to the corresponding doctor will also be deleted')) {

        $.ajax({
            url: $("body").attr('data-url') + 'scheduledVisits/delete/' + $(this).attr('data-id'),
            type: 'DELETE'
        }).done(function (data) {
            window.location.href = $("body").attr('data-url') + "scheduledVisits/";
        });
    }
});

//Show visits by cycle
$('#selectedCycle').on('change', function () {

        $.ajax({
            type: 'GET',
            url: $("body").attr('data-url') + 'scheduledVisits/one/' + $(this).val(),
            success: function (data){
                (".show").html(data);
            }
        });
   
});

