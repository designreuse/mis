//set the active menu item
$(document).ready(function () {
    var path = window.location.pathname;
    path = path.replace(/\/$/, "");
    path = decodeURIComponent(path);

    $(".sidebar-menu a").each(function () {
        var href = $(this).attr('href');

        if (path === href || (path + '/') === href) {
            $(this).parent().addClass('active');
            $(this).parent().parent().parent().addClass('active');
        }
    });
    calendar();
});

//init the calendar
function calendar() {
    $.ajax({
        url: $("body").attr('data-url') + "calendar",
        method: 'GET',
        success: function (result) {
            var data = [];

            var data = [];
            $.each(result, function (key, value) {
                data.push({
                    title: key,
                    start: value
                });
            });

            $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,basicWeek,basicDay'
                },
                editable: true,
                eventLimit: true, // allow "more" link when too many events
                events: data
            });

        }
    });
}

