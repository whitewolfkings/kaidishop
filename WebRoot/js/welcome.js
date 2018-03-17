/*************************lihuoming*******************************
****                        ªÒ»°cookieƒ⁄»›                   *****
*****************************************************************/
function readCookieByName(name){
	var nameOfCookie = name + "=";
	var x = 0;
	while ( x <= document.cookie.length ){
		var y = (x+nameOfCookie.length);
		if ( document.cookie.substring( x, y ) == nameOfCookie ) {
			if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
			endOfCookie = document.cookie.length;
			return document.cookie.substring( y, endOfCookie );
		}
		x = document.cookie.indexOf( " ", x ) + 1;	
		if ( x == 0 ) break;
	}
	return "";
}
function readWelcomeMsg(){
	try{
		var msg = readCookieByName("realName");
		if(msg!=""){
			return decodeURI(msg);
		}else{
			msg = readCookieByName("userName");
			if(msg!="") return msg;
		}		
	} catch(e){
		;
	}
	return "";
}
document.write(readWelcomeMsg());