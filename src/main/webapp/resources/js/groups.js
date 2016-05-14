var leaderId = null;

//init select2 for the groupMembers
$("#groupMembers").select2({
    placeholder: 'Select group members',
    allowClear: true
});

//when a leader is selected, remove from the members select
$('#leaderId').on('change', function () {
    if (leaderId != null) {
        $('#groupMembers option[value=' + leaderId + ']').removeAttr('disabled');
        $('#groupMembers').select2();
    }

    leaderId = $(this).val();
    $('#groupMembers option[value=' + leaderId + ']').attr('disabled', 'disabled');
});

//confirm before deleting a group
$(".deleteGroup").click(function (e) {
    if (confirm('Are you sure you want to delete the group?')) {

        $.ajax({
            url: $("body").attr('data-url') + 'groups/delete/' + $(this).attr('data-id'),
            type: 'DELETE'
        }).done(function (data) {
            window.location.href = $("body").attr('data-url') + "groups/";
        });
    }
});
