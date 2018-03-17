	function colorEvent(eventObject,productid){
		var main_image = document.getElementById('main_image');
		if(eventObject && main_image){			
			var productimg = document.getElementById('productImage_'+ eventObject.value);
			if(productimg && productimg.value.trim()!=""){
				main_image.style.background = "url(http://image.babasport.com/global/init.gif) center center no-repeat";
				main_image.style.background = "url(http://image.babasport.com/product/"+productid+"/200x/"+ productimg.value+") center center no-repeat";
			}			
		}
	}

	function historyaccess(){
		var historyaccess = document.getElementById('historyaccess');		
		if(historyaccess){
			historyaccess.innerHTML= "数据正在加载...";
			send_request(function(value){historyaccess.innerHTML=value}, "/product/history/access.go", true);
		}
	}

	function bigImageBrowse(productid){
		var color = document.forms["cart"].colorid;
		if(color){
			var colorid = color.value;
			var productimg = document.getElementById('productImage_'+ colorid);
			if(productimg){
				window.open ("/mapping/browse/product/image.htm?productid="+ productid+"&imagefile="+ productimg.value);
			}
		}
	}

	function topcommend(typeid){
		var commenddetail = document.getElementById('commenddetail');		
		if(commenddetail && typeid!=""){
			commenddetail.innerHTML= "数据正在加载...";
			send_request(function(value){commenddetail.innerHTML=value}, "/product/top/switch.go?method=getTopCommend&typeid="+ typeid, true);
		}
	}
	function pageInit(producttypeid){
		historyaccess();
		topcommend(producttypeid);
		setCookie("currentProductType", producttypeid, 60*24*60*60);//设置当前浏览的产品类型
	}