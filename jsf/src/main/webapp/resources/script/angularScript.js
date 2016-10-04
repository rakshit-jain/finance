var app = angular.module("hcentive", []);

var constant = {
		SERVICE_URL:"http://52.22.118.16:8080/webservice/services",
	}

app.controller("addBills", function($scope, $http, $rootScope) {
	
	$scope.claim={"name":"1",
	              "userDocuments":[
	               	   {"isPicAttached":"1"}]
	};
	
	$scope.addClaim =  function() {
		$scope.restaurant.videoUrl.push('');
	}
	
	$scope.$watch("claim['userDocuments']", function (value) {//I change here
		console.log("This is changed");
		setTimeout(function(){
			initializeUpload($scope.claim.userDocuments.length-1);
		},100)
	}, true);

	$scope.addDocument =  function() {
		var document = {"isPicAttached":"false"};
		$scope.claim.userDocuments.push(document);
		
	}
	
	$scope.removeDocument =  function(id) {
		console.log('Trying to remove id:'+id);
		$scope.claim.userDocuments.splice(id,1);
	}
	
	$scope.uploadClaim = function(emailaddress){
		
		for (var i=0; i<$scope.claim.userDocuments.length; i++){
			if (pics[i]!=null)
				$scope.claim.userDocuments[i].fileName=pics[i].imageUrl;
		}
		var jsonClaim = JSON.stringify($scope.claim, null, "\t")
		console.log("Now submiting the claim to backend system."+emailaddress);
		var req = {
				method : 'POST',
				url : constant.SERVICE_URL + '/claim/addClaim?useremail='+emailaddress,
				data : jsonClaim
			}
		$http(req).then(
				function successCallback(response) {
					alert("Claim submitted successfully");
					location.reload();
				},function errorCallback(response){
					console.log("Error Caused while updloading the data to server. Try Again : "
							+ response);
				});
	
	}
});
function isEmpty( el ){
    return !$.trim(el.html())
}
var pics=[];
function initializeUpload(index) {
	if(isEmpty($("#fileuploader"+index))){
		uploadObj = $("#fileuploader"+index).uploadFile({
			url : constant.SERVICE_URL + "/upload/image",
			fileName : "myfile",
			showPreview : true,
			dragDrop : true,
			maxFileSize : 1024 * 1024,
			multiple : true,
			autoSubmit : true,
			previewHeight : "100px",
			previewWidth : "100px",
			onSuccess : function(files, data, xhr, pd) {
				console.log("files uplodaded successfully");
				var pic = {
					"imageUrl" : data.body.fileName,
				}
				pics[index]=pic;
				console.log(data);
			}
		});
	}
	
}

$(document).ready(function() {
	//initializeUpload(0);
});