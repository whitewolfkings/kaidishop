var dragy=0,dragx=0;
var dragapproved=false;

function moveF(e)
{
    if (e.button==0)
	{
	    window.scrollBy(dragx-e.pageX,dragy-e.pageY);
	    dragy=e.pageY;
		dragx=e.pageX;
	}
}

function downF(e)
{
    dragy=e.pageY;
	dragx=e.pageX;
}


if (window.navigator.userAgent.indexOf("Firefox")>=1)
{
    document.onmousedown=function(e)
    {
	    document.onmousemove=function(e)
        {
             moveF(e);
        }  
    }
    document.onmouseup=function()
    {
        document.onmousemove=null;
    }
}
else
{
    document.onmousemove=function()
    {
        if ((event.button==0 || event.button==1)&&dragapproved)
	    {
		    window.scrollBy(dragx-event.clientX,dragy-event.clientY);
		    dragy=event.clientY;
		    dragx=event.clientX;
		    return false;
	    }
    }
    document.onmousedown=function()
    {
        dragy=event.clientY;
	    dragx=event.clientX;
	    dragapproved=true;
    }
    document.onmouseup=function()
    {
        dragapproved=false;
    }
}




function suitImage(img,w,h)
{
		var image=new Image();
        image.src=img.src;

		var iw=image.width;
		var ih=image.height;
		var iratio=iw/ih;
        
        if(iw>w)
		{
                iw=w;
				ih=w/iratio;
        }
        if(ih>h)
		{
                ih=h;
                iw=h*iratio;
        }  
		img.width=iw;
		img.height=ih;
}