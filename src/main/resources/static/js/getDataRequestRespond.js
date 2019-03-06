$(document).ready(function() {
	
	var userLoggedIn = $('#userLoggedIn').val();
	var lastUpdateDate = '';	
	var updateDate = '';
	var stock = $(".stocks");
	var contextPath =  $("#contextPath").val();

	getDateAndTime();
	setInterval(getDateAndTime, 5000);

	function getDateAndTime() {
		$.ajax({
			url: '/api/stockDate',
			type: "GET",
			dataType: "json",
			contentType: 'application/json',
			
			success: function(data) {	
				updateDate = data.date;
				
				// Update DATE, TIME & STOCKS
				if(updateDate != lastUpdateDate) {
					lastUpdateDate = updateDate;
					$('#time').empty();			
					date = updateDate.substring(0, 10);
					time = updateDate.substring(11, 16);
					$('#time').append("LAST UPDATE " + date + " " + time);
					getStockPrices();
				}
			}
		})
	}
	
	function getStockPrices() {
		$.ajax({
			url: '/api/prices',
			type: "GET",
			dataType: "json",
			contentType: 'application/json',
			
			success: function(data) {
				var content = '<tr><th>Company</th><th>Price[PLN]</th><th>Amount</th>';
				if(userLoggedIn != null) { 
					content += '<th>Action</th>';
				}

				for(var i = 0; i < data.length; i++) { 
					content += '</tr><tr><td><a href="' + contextPath + "stock/" + data[i].company.code + '">' + data[i].company.name;
					content += '<div class="charts"><img src="' + contextPath + 'icons/chart.svg"></div>';
					content += '</a></td>';
					content += '<td>' + data[i].price + '</td>';
					content += '<td>' + data[i].company.amount + '</td>';
					if(userLoggedIn != null) {
						content += '<td><input id="buy-button-' + data[i].company.code + '"class="buy-buttons buttons" type="button" value="Buy"></td>';
					}
					content += '</tr><input id="multiple-unit-' + data[i].company.code + '" type="hidden" value="' + data[i].company.unit + '">';
				
					
					// Adding prices & value to wallet if exists
					var unitPriceCompany = $('#unit-price-' + data[i].company.code);
					if(userLoggedIn != null) {
						if(unitPriceCompany != null) {
							unitPriceCompany.empty();
							unitPriceCompany.append(data[i].price)
							
							var amountOfStocks = $('#amount-of-stocks-' + data[i].company.code).val();
							
							var stockValue = $('#stock-amount-' + data[i].company.code);
							stockValue.empty();
							stockValue.append(amountOfStocks);
							
							var stockValue = $('#stocks-value-' + data[i].company.code);
							stockValue.empty();
							stockValue.append(Math.round(data[i].price*amountOfStocks * 10000) / 10000);
						}
					}
				}
				
				var tableStocks = $('#table-stocks');
				tableStocks.empty();
				tableStocks.append(content);
			}
		})
	}

});
				
