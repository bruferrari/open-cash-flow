/**
 * Created by bruno on 3/1/16.
 */
google.load('visualization', '1', {'packages' : ['corechart']});
google.setOnLoadCallback(drawDailyChart, true);
google.setOnLoadCallback(drawMonthlyChart, true);

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