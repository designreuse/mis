//confirm before deleting a visit
$(".deletePaidVisit").click(function (e) {
    console.log($("body").attr('data-url') + 'paidVisits/delete/' + $(this).attr('data-id'))

    if (confirm('Are you sure you want to delete the paid visit?')) {

        $.ajax({
            url: $("body").attr('data-url') + 'paidVisits/delete/' + $(this).attr('data-id'),
            type: 'DELETE'
        }).done(function (data) {
            window.location.href = $("body").attr('data-url') + "paidVisits/";
        });
    }
});