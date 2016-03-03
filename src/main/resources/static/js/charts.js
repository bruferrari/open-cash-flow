/**
 * Created by bruno on 3/1/16.
 */
google.load('visualization', '1', {'packages' : ['corechart']});
google.setOnLoadCallback(drawDailyChart, true);
google.setOnLoadCallback(drawMonthlyChart, true);
google.setOnLoadCallback(drawCategoryOutgoingChart, true);
google.setOnLoadCallback(drawCategoryIncomingChart, true);

$(window).resize(function() {
    drawDailyChart();
    drawMonthlyChart();
    drawCategoryOutgoingChart();
    drawCategoryIncomingChart();
});

//$("a[href='#monthly']").on('shown.bs.tab', function(e) {
//    drawMonthlyChart();
//});

function drawDailyChart() {
    $.ajax({
        url: '/reports/dailyBalance',
        dataType: 'json',
        success: function(jsonData) {
            var data = new google.visualization.DataTable();
            data.addColumn('date', 'date');
            data.addColumn('number', 'balance');

            for(var i = 0; i < jsonData.dailyBalances.length; i++) {
                var n = Number(jsonData.dailyBalances[i].balance);
                data.addRow([new Date(jsonData.dailyBalances[i].date), n]);

            }

            var formatter = new google.visualization.NumberFormat({
                negativeColor: 'red',
                negativeParens: true,
                pattern: 'R$ #,##0.00'
            });

            var options = {
                title: 'Daily Balance',
                is3D: true,
                width: '100%',
                hAxis: {
                  format: 'dd/MM'
                },
                animation: {
                    startup: true,
                    duration: 1500,
                    easing: 'linear'
                },
                series: {
                    0: {color: '#e2431e'}
                }
            };

            formatter.format(data, 1);

            var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    });
}

function drawMonthlyChart() {
    $.ajax({
        url: '/reports/monthlyBalance',
        dataType: 'json',
        success: function(jsonData) {
            var data = new google.visualization.DataTable();
            data.addColumn('date', 'month');
            data.addColumn('number', 'balance');

            for(var i = 0; i < jsonData.monthlyBalances.length; i++) {
                var n = Number(jsonData.monthlyBalances[i].balance);
                var month = new Date().setMonth((jsonData.monthlyBalances[i].month)-1)
                data.addRow([new Date(month), n]);
            }

            var formatter = new google.visualization.NumberFormat({
                negativeColor: 'red',
                negativeParens: true,
                pattern: 'R$ #,##0.00'
            });

            var options = {
                title: 'Monthly Balance',
                is3D: true,
                width: '100%',
                animation: {
                    startup: true,
                    duration: 1500,
                    easing: 'linear'
                },
                series: {
                    0: {color: '#e2431e'}
                }
            };

            formatter.format(data, 1);

            var chart = new google.visualization.LineChart(document.getElementById('monthly_chart_div'));
            chart.draw(data, options);
        }
    });
}

function drawCategoryOutgoingChart() {
    $.ajax({
        url: '/reports/categoryOutgoing',
        dataType: 'json',
        success: function(jsonData) {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'categoryName');
            data.addColumn('number', 'amount');

            for(var i = 0; i < jsonData.categoryOutgoings.length; i++) {
                var n = Number(jsonData.categoryOutgoings[i].amount);
                data.addRow([jsonData.categoryOutgoings[i].categoryName, n]);

            }

            var formatter = new google.visualization.NumberFormat({
                negativeColor: 'red',
                negativeParens: true,
                pattern: 'R$ #,##0.00'
            });

            var options = {
                title: 'Outgoing by Category',
                width: '100%',
                animation: {
                    startup: true,
                    duration: 1500,
                    easing: 'linear'
                },
                pieHole: 0.4,
            };

            formatter.format(data, 1);

            var chart = new google.visualization.PieChart(document.getElementById('category_outgoing_chart_div'));
            chart.draw(data, options);
        }
    });
}

function drawCategoryIncomingChart() {
    $.ajax({
        url: '/reports/categoryIncoming',
        dataType: 'json',
        success: function(jsonData) {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'categoryName');
            data.addColumn('number', 'amount');

            for(var i = 0; i < jsonData.categoryIncomings.length; i++) {
                var n = Number(jsonData.categoryIncomings[i].amount);
                data.addRow([jsonData.categoryIncomings[i].categoryName, n]);

            }

            var formatter = new google.visualization.NumberFormat({
                negativeColor: 'red',
                negativeParens: true,
                pattern: 'R$ #,##0.00'
            });

            var options = {
                title: 'Incoming by Category',
                width: '100%',
                animation: {
                    startup: true,
                    duration: 1500,
                    easing: 'linear'
                },
                pieHole: 0.4,
            };

            formatter.format(data, 1);

            var chart = new google.visualization.PieChart(document.getElementById('category_incoming_chart_div'));
            chart.draw(data, options);
        }
    });
}