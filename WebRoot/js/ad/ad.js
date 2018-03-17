	function getAD(adcode){
		var TurnAD = document.getElementById('TurnAD');		
		if(TurnAD && adcode!=""){
			TurnAD.innerHTML= "";
			send_request(function(value){TurnAD.innerHTML=value}, "/ad/load.go?adcode="+ adcode, true);
		}
	}