//confirm before deleting a cycle
$(".deleteCycle").click(function (e) {
    if (confirm('Are you sure you want to delete the group?')) {

        $.ajax({
            url: $("body").attr('data-url') + 'cycles/delete/' + $(this).attr('data-id'),
            type: 'DELETE'
        }).done(function (data) {
            window.location.href = $("body").attr('data-url') + "cycles/";
        });
    }
});
