// Buy
$(document).on('click', '.buy-buttons', function() { 
	
	var buttonIdentifier = this.id.substring(11);
	var buyPopup = $('#buy-popup-');
	var multipleBuy = $('#multiple-buy');
	buyPopup.removeClass('hidden-popup');
	buyPopup.addClass('show-popup');
	buyPopup.attr('id', 'buy-popup-' + buttonIdentifier);
    
	var unitMultiple = $('#multiple-unit-' + buttonIdentifier).val();  
	multipleBuy.empty();
    multipleBuy.append(unitMultiple);
    $('.company-stock').val(buttonIdentifier);
});

// Sell
$('.sell-buttons').click(function() {
	
	var buttonIdentifier = this.id.substring(12);
	var sellPopup = $('#sell-popup-');
	var multipleSell = $('#multiple-sell');
	sellPopup.removeClass('hidden-popup');
	sellPopup.addClass('show-popup');
	sellPopup.attr('id', 'sell-popup-' + buttonIdentifier);
    	
	var unitMultiple = $('#multiple-unit-' + buttonIdentifier).val(); 
	multipleSell.empty();
    multipleSell.append(unitMultiple);
    $('.company-stock').val(buttonIdentifier);
});

$('.hide-popup-button').click(function() {
	
	$('.company-stock').val("");
	
	var buyPopup = $('[id*=buy-popup-]');
	buyPopup.val("");
	buyPopup.removeClass('show-popup');
	buyPopup.addClass('hidden-popup');
	buyPopup.attr('id', 'buy-popup-');
    
	var sellPopup =  $('[id*=sell-popup-]');
	sellPopup.val("");
	sellPopup.removeClass('show-popup');
	sellPopup.addClass('hidden-popup');
	sellPopup.attr('id', 'sell-popup-');
});

