var allCtx = document.getElementById("allChart").getContext('2d');
var last20Ctx = document.getElementById("last20Chart").getContext('2d');
var pathname = window.location.pathname.substring(7);
var wholeDataFromRespond;

$(document).ready(function() {
	
	wholeDataFromRespond = getPrices();

});

function getPrices() {
	return $.ajax({
		url: window.location.origin + '/api/prices/' + pathname,
		type: "GET",
		dataType: "json",
		contentType: 'application/json',
		
		success: function(data) {
			createChart(data, allCtx, data.length, 0);
			createChart(data, last20Ctx, 20, 2);
			buttonManagment(data);
		}
	})
	
}


function createChart(separatedData, ctx, howMany, pointR) {
	var dates = [];
	var prices = [];
	var separatedDataLength = separatedData.length;
	
	for(i = separatedDataLength - howMany; i < separatedDataLength; i++) {
		date = separatedData[i].stockDate.date.substring(0, 10);
		time = separatedData[i].stockDate.date.substring(11, 16);
		dates.push(date + " " + time);
		prices.push(separatedData[i].price);
	}

	var newChart = new Chart(ctx, {
		type: 'bar',
		data: {
			labels: dates,
			datasets: [{
				label: pathname + ' stock prices. Last: ' + howMany,
				data: prices,
				borderColor: "Orange",
				backgroundColor: "Orange",
				type: 'line',
				pointRadius: pointR,
				fill: false,
				lineTension: 0,
				borderWidth: 2
			}]
		},
		options: {
			responsive: true,
			maintainAspectRatio: false,
			scales: {
				yAxes: [{
					ticks: {
						beginAtZero: false
					}
				}]
			}
		}
	});

	return newChart;
}