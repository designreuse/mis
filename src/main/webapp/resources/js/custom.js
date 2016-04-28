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
});
