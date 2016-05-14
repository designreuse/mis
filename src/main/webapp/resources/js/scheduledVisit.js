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

/**
//when a cycle is selected, fetch the available doctors of this period
$('#cycleId').on('change', function () {
console.log($(this).val())
        $.ajax({
            url: $("body").attr('data-url') + 'scheduledVisits/byCycle/' + $(this).val(),
            type: 'GET',
            success: function (data) {
                var td = "";
                for(var i = 0; i < data.length; i++){
                   td += "<tr>\n\
<td>"+ data[i].id+"</td>\n\
<td>"+ data[i].medicalVisitor+"</td>\n\
<td>"+ data[i].cycle+"</td>\n\
<td>"+ data[i].doctor+"</td>\n\
<td>"+ data[i].status+"</td>\n\
<td>\n\
<a href='<c:url value='/paidVisits/create/'"+data[i].id+"''/>' class='btn btn-info'>\n\
<i class='fa fa-check-square-o'></i>\n\
</a><button type='button' class='btn btn-danger deleteVisit' data-id='"+data[i].id+"'><i class='fa fa-trash'></i></button></td></tr>";                  
                }
                $('#popTable').html(td);
            }
        });
});
*/
