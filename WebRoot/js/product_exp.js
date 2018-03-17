var scale=0;
var original=0;
var change=0;
var iratio=0;
var objImg=null;

window.onload=function()
{
    objImg= ifm_show_prd.document.getElementById('objImg');
    objImg.src=document.getElementById('hid_largepictureurl').value;   
}
function dochange(imagesrc){
	window.location="#pictrue_show";
	objImg.src=""+ imagesrc;
}
function doZoomIn()
{
    if(original==0)
    {
        original=objImg.width;
        scale=objImg.width;
        iratio=objImg.width/objImg.height;
    }

	if(scale-original>2048) return false;
	
	scale+=5;
	change+=5;
    if(change>100) {change=0; return false;}
	objImg.width=scale;
	objImg.height=scale/iratio;
	window.setTimeout("doZoomIn()",50);
	return true;
}

function doZoomOut()
{
	if(original==0)
    {
        original=objImg.width;
        scale=objImg.width;
        iratio=objImg.width/objImg.height;
    }
    
	if(scale<=100) return false;
	scale-=5;
	change+=5;
    if(change>100) {change=0; return false;}
	objImg.width=scale;
	objImg.height=scale/iratio;
    window.setTimeout("doZoomOut()",50);
	return true;
}


function doReset()
{
    if(original==0)
    {
        original=objImg.width;
        scale=objImg.width;
        iratio=objImg.width/objImg.height;
    }
    else
    {
        objImg.width=original;
	    objImg.height=original/iratio;
	    scale=original;
    }
    
	return true;
}







var last_img_btn_chg_prd_over=null;
var last_img_btn_chg_prd_click=null;
function ImgBtnChgPrd_Mouseover(obj)
{
    if(last_img_btn_chg_prd_over!=null) 
        last_img_btn_chg_prd_over.className='img_btn_chg_prd';
    last_img_btn_chg_prd_over=obj;
    obj.className='img_btn_chg_prd_active';
}



function ImgBtnChgPrd_Click(obj,largepictureurl)
{
    if(last_img_btn_chg_prd_click!=null) 
        last_img_btn_chg_prd_click.className='img_btn_chg_prd';
    last_img_btn_chg_prd_click=obj;
    
    if(last_img_btn_chg_prd_over==last_img_btn_chg_prd_click)  
        last_img_btn_chg_prd_over=null;
    
    obj.className='img_btn_chg_prd_active';
     objImg.src=largepictureurl;
   
        original=objImg.width;
        scale=objImg.width;
        iratio=objImg.width/objImg.height;
}