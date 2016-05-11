//confirm before deleting a visit
$(".deleteVisit").click(function (e) {
    console.log($("body").attr('data-url') + 'scheduledVisits/delete/' + $(this).attr('data-id'))

    if (confirm('Are you sure you want to delete the scheduled visit?')) {

        $.ajax({
            url: $("body").attr('data-url') + 'scheduledVisits/delete/' + $(this).attr('data-id'),
            type: 'DELETE'
        }).done(function (data) {
            window.location.href = $("body").attr('data-url') + "scheduledVisits/";
        });
    }
});
