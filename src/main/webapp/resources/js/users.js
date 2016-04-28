//confirm before deleting a user
$(".deleteUser").click(function (e) {
    console.log($("body").attr('data-url') + 'users/delete/' + $(this).attr('data-id'))

    if (confirm('Are you sure you want to delete the user?')) {

        $.ajax({
            url: $("body").attr('data-url') + 'users/delete/' + $(this).attr('data-id'),
            type: 'DELETE'
        }).done(function (data) {
            window.location.href = $("body").attr('data-url') + "users/";
        });
    }
});