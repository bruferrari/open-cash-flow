/**
 * Created by bruno on 3/1/16.
 */

$(document).ready(function() {
   $(document).ajaxStart(function() {
       $('#wait_daily_balance_chart').css('display', 'block');
       $('#wait_monthly_balance_chart').css('display', 'block');
       $('#wait_category_outgoing_chart').css('display', 'block');
       $('#wait_category_incoming_chart').css('display', 'block');
   });

    $(document).ajaxComplete(function() {
        $('#wait_daily_balance_chart').css('display', 'none');
        $('#wait_monthly_balance_chart').css('display', 'none');
        $('#wait_category_outgoing_chart').css('display', 'none');
        $('#wait_category_incoming_chart').css('display', 'none');
    });

    var monthlyTabCount = 0;

    $('a[data-toggle="tab"]').on('click', function (e) {
        var target = $(e.target).attr("href")
        if(target == '#monthly' && monthlyTabCount == 0) {
            renderMonthlyTab();
            monthlyTabCount++;
        }
    });
});

google.load('visualization', '1', {'packages' : ['corechart', 'table']});

renderDailyTab();

function renderMonthlyTab() {
    google.setOnLoadCallback(drawMonthlyChart, true);
    google.setOnLoadCallback(drawCategoryOutgoingChart, true);
    google.setOnLoadCallback(drawCategoryIncomingChart, true);
}

function renderDailyTab() {
    google.setOnLoadCallback(drawDailyChart, true);
}

$(window).resize(function() {
    drawDailyChart();
    drawMonthlyChart();
    drawCategoryOutgoingChart();
    drawCategoryIncomingChart();
});

function drawDailyChart() {
    $.ajax({
        url: '/reports/dailyBalance',
        dataType: 'json',
        success: function(jsonData) {
            var data = new google.visualization.DataTable();
            data.addColumn('date', 'Date');
            data.addColumn('number', 'Balance');
            data.addColumn('number', 'Incoming');
            data.addColumn('number', 'Outgoing');

            for(var i = 0; i < jsonData.dailyBalances.length; i++) {
                var b = Number(jsonData.dailyBalances[i].balance);
                var c = Number(jsonData.dailyBalances[i].credit);
                var d = Number(jsonData.dailyBalances[i].debit);

                data.addRow([new Date(jsonData.dailyBalances[i].date), b, c, d]);
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
                }
            };

            formatter.format(data, 1);
            formatter.format(data, 2);
            formatter.format(data, 3);

            var chart = new google.visualization.LineChart(document.getElementById('daily_balance_chart_div'));
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
            data.addColumn('date', 'Month');
            data.addColumn('number', 'Balance');

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
                }
            };

            formatter.format(data, 1);

            var chart = new google.visualization.AreaChart(document.getElementById('monthly_chart_div'));
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