/**
 * Created by bruno on 3/1/16.
 */
google.load('visualization', '1', {'packages' : ['corechart']});
google.setOnLoadCallback(drawDailyChart, true);
google.setOnLoadCallback(drawMonthlyChart, true);
google.setOnLoadCallback(drawCategoryOutgoingChart, true);

$(window).resize(function() {
    drawDailyChart();
    drawMonthlyChart();
    drawCategoryOutgoingChart();
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
            data.addColumn('string', 'month');
            data.addColumn('number', 'balance');

            for(var i = 0; i < jsonData.monthlyBalances.length; i++) {
                var n = Number(jsonData.monthlyBalances[i].balance);
                data.addRow([jsonData.monthlyBalances[i].month, n]);

            }

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
                title: 'Category Outgoing',
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