(function(){
	var app =  angular.module("store", []);
	app.controller("storeController", function(){
		this.product = gem;
	});
	
	var gem = {
		name : 'Dodecahedron',
		price : 2.5,
		description : 'This is a good product',
		canPurchase : true
	};
	
})();