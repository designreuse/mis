//Initialize the charts

$("#medicalVisitorGeo").change(function () {
    byGeolocation();
});

$("#medicalVisitorInd").change(function () {
    individualStatistics();
});


function byGeolocation() {
    $.ajax({
        url: $("body").attr('data-url') + "/reports/byGeolocation",
        method: 'GET',
        data: {
            medicalVisitorId: $("#medicalVisitorGeo option:selected").val()
        },
        success: function (result) {
            var data = [];
            console.log(result);
            if ($.isEmptyObject(result)) {
                $(".byGeolocation .noData").show();
                $("#byGeolocation").hide();
            } else {
                $.each(result, function (key, value) {
                    colorPair = Colors.random();
                    data.push({
                        value: value,
                        color: Colors.pairs[colorPair][0],
                        highlight: Colors.pairs[colorPair][1],
                        label: key
                    });
                });

                initGeolocationChart(data);
                $("#byGeolocation").show();
            }
        }
    });
}


function individualStatistics() {
    $.ajax({
        url: $("body").attr('data-url') + "/reports/individualStatistics",
        method: 'GET',
        data: {
            medicalVisitorId: $("#medicalVisitorInd option:selected").val()
        },
        success: function (result) {

            if ($.isEmptyObject(result)) {
                $(".individualStatistics.noData").show();
                $("#individualStatistics").hide();
            } else {
                $(".individualStatistics.noData").hide();
                var html = '';
                $.each(result, function (key, visit) {
                    html += '<tr>';
                    html += '<td>' + visit.doctor.id + '</td>';
                    html += '<td>' + visit.doctor.firstName + ' ' + visit.doctor.lastName + '</td>';
                    html += '<td>' + visit.doctor.address + ', ' + visit.doctor.cityName + ', ' + visit.doctor.geolocationAreaName + '</td>';
                    html += '<td>' + visit.doctor.position + ' at ' + visit.doctor.institutionName + '</td>';
                    html += '<td>' + visit.doctor.specialtyName + '</td>';
                    html += '<td>' + visit.cycle.startDate + '-' + visit.cycle.endDate + '</td>';

                    if (visit.status == 'Pending') {
                        html += '<td><p><span class="label label-warning">Pending visit</span><p>';
                    } else {
                        html += '<td class="col-md-4">';
                        $.each(visit.paidVisits, function (k, paidVisit) {
                            html += '<p><span class="label label-info">Paid visit</span> <i class="fa fa-calendar"></i> ' + paidVisit.date + ', Week ' + paidVisit.week + ',' + paidVisit.hour + '<br/><small>Comments: ' + paidVisit.comments + '</small></p>';
                        });
                    }

                    if (visit.extraVisits.length > 0) {
                        $.each(visit.extraVisits, function (k, extraVisit) {
                            html += '<p><span class="label label-danger">Extra visit</span> <i class="fa fa-calendar"></i> ' + extraVisit.date + ', ' + extraVisit.time + '<br/><small>Comments: ' + extraVisit.comments + '</small></p>';
                        });
                    }
                    html += '</td>';

                    html += '</tr>';
                });
                $("#individualStatistics tbody").html(html);
                $("#individualStatistics").show();

            }
        }
    });

}



function initGeolocationChart(dataset) {

    var ctx = document.getElementById("byGeolocation").getContext("2d");
    var byGeolocation = new Chart(ctx).Pie(dataset, {
        segmentShowStroke: true,
        segmentStrokeColor: "#fff",
        segmentStrokeWidth: 2,
        animationSteps: 100,
        animationEasing: "linear",
        animateRotate: true,
        animateScale: false,
        responsive: true
    });
}



/*
 * display some random colors
 * */
Colors = {};
Colors.pairs = {
    turqoise: ["#12AFCB", "#30E0FF"],
    yellow: ["#E26E27", "#FFE04E"],
    green: ["#22BAA0", "#48FFE0"],
    purple: ["#7a6fbe", "#BAAEFF"],
    red: ["#f25656", "#FF7474"],
    orange: ["#D95232", "#E0745B"],
    lightblue: ["#4BF", "#69C8FF"],
    pink: ["#D97AA5", "#E094B7"],
    grey: ["#666", "#848484"],
    darkred: ["#AE181F", "#BE464B"],
    orange2: ["#EE802F", "#F19958"],
    moodygrey: ["#5D83AA", "#7D9BBB"]
};
Colors.random = function () {
    var result;
    var count = 0;
    for (var prop in this.pairs)
        if (Math.random() < 1 / ++count)
            result = prop;
    return result;
};


byGeolocation();
individualStatistics();