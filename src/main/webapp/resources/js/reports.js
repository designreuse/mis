//Initialize the charts

$("#medicalVisitorId").change(function () {
    var medicalVisitorId = $("#medicalVisitorId option:selected").val();
    byGeolocation();
});


function byGeolocation() {
    $.ajax({
        url: $("body").attr('data-url') + "/reports/byGeolocation",
        method: 'GET',
        data: {
            medicalVisitorId: $("#medicalVisitorId option:selected").val()
        },
        success: function (result) {
            console.log(result);
            if ($.isEmptyObject(result)) {
                $(".byGeolocation .noData").show();
                $("#byGeolocation").hide();
            } else {
                var data = [];
                $.each(result, function (key, value) {
                    colorPair = Colors.random();
                    data.push({
                        value: value,
                        color: Colors.pairs[colorPair][0],
                        highlight: Colors.pairs[colorPair][1],
                        label: key
                    });
                });

                initChart(data);
                $("#byGeolocation").show();

            }
        }
    });

}


function initChart(dataset) {

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