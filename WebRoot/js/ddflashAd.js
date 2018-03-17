ycn=window.ycnui||{};
ycn.Common=new function(){this.lTrim=function(i){return i.replace(/^\s*/,"");};this.rTrim=function(i){return i.replace(/\s*$/,"");};this.trim=function(i){return this.rTrim(this.lTrim(i));};this.getEl=function(i){if(!document.getElementById)return false;if(typeof i==="string"){return document.getElementById(i);}else{return i;}};this.getElByClassName=function(t,n,s,el){var el=(el)?el:document;var itm=el.getElementsByTagName(t);var num=1;for(i=0;i<itm.length;i++){if(itm[i].className===n&&s===num){return itm[i];}else if(itm[i].className===n){num++;}}
return false;};
this.isIE6=function(){return navigator.userAgent.search('MSIE')>0&&navigator.userAgent.search('6')>0;}
this.isIE=function(){return navigator.userAgent.search('MSIE')>0;}
this.isOpera=function(){return navigator.userAgent.indexOf('Opera')>-1;}
this.isMoz=function(){return navigator.userAgent.indexOf('Mozilla/5.')>-1;}
this.setCookie=function(cn,cv,d,dm){var now=new Date();var expire=new Date();if(d==null||d==0)d=1;expire.setTime(now.getTime()+3600000*24*d);document.cookie=cn+"="+escape(cv)
+";expires="+expire.toGMTString()
+";domain="+dm;}
this.deleteCookie=function(cn,dm){if(getCookie(name))
{document.cookie=cn+"="+
((domain)?"; domain="+dm:"")+"; expires=Thu, 01-Jan-70 00:00:01 GMT";}}
this.getCookie=function(cn)
{var dc=document.cookie;var prefix=cn+"=";var begin=dc.indexOf("; "+prefix);if(begin==-1)
{begin=dc.indexOf(prefix);if(begin!=0)return null;}
else
{begin+=2;}
var end=document.cookie.indexOf(";",begin);if(end==-1)
{end=dc.length;}
return unescape(dc.substring(begin+prefix.length,end));}};




ycn=window.ycn||{};
ycn.Event={addEvent:function(obj,evType,fn){
						if(obj.addEventListener)
							{obj.addEventListener(evType,fn,false);return true;}
						else if(obj.attachEvent)
							{var r=obj.attachEvent("on"+evType,fn);ycn.EventCache.add(obj,evType,fn);return r;}
						else
							{return false;}
						},removeEvent:function(obj,evType,fn){if(obj.removeEventListener){obj.removeEventListener(evType,fn,false);return true;}else if(obj.detachEvent){var r=obj.detachEvent("on"+evType,fn);return r;}else{return false;}},getEvent:function(e)
{e=window.event||e;e.leftButton=false;if(e.srcElement==null&&e.target!=null)
{e.srcElement=e.target;e.leftButton=(e.button==1);}
else if(e.target==null&&e.srcElement!=null)
{e.target=e.srcElement;e.leftButton=(e.button==0);}
else if(e.srcElement!=null&&e.target!=null)
{}
else{return null}
if(document.body&&document.documentElement)
{e.mouseX=e.pageX||(e.clientX+Math.max(document.body.scrollLeft,document.documentElement.scrollLeft));e.mouseY=e.pageY||(e.clientY+Math.max(document.body.scrollTop,document.documentElement.scrollTop));}
else
{e.mouseX=-1;e.mouseY=-1;}
return e;},stopEvent:function(e)
{if(e&&e.cancelBubble!=null)
{e.cancelBubble=true;e.returnValue=false;}
if(e&&e.stopPropagation&&e.preventDefault)
{e.stopPropagation();e.preventDefault();}
return false;}};ycn.EventCache=function()
{var listEvents=[];return{listEvents:listEvents,add:function(node,sEventName,fHandler,bCapture){listEvents[listEvents.length]=arguments;},flush:function(){var i,item;for(i=listEvents.length-1;i>=0;i=i-1)
{item=listEvents[i];if(item[0].removeEventListener){item[0].removeEventListener(item[1],item[2],item[3]);};if(item[1].substring(0,2)!="on"){item[1]="on"+item[1];};if(item[0].detachEvent){item[0].detachEvent(item[1],item[2]);};item[0][item[1]]=null;};}};}();ycn.Event.addEvent(window,"unload",ycn.EventCache.flush);function error_handler(a,b,c)
{window.status=(c+"\n"+b+"\n\n"+a+"\n\n"+error_handler.caller);return true;}



slidePlayer.prototype.container=null;
slidePlayer.prototype.imageList=null;
slidePlayer.prototype.width=0;
slidePlayer.prototype.height=0;
slidePlayer.prototype.currentNum=1;
slidePlayer.prototype.playTimer=null;
slidePlayer.prototype.loopTimer;
slidePlayer.prototype.intervalTime=50;
slidePlayer.prototype.waiting=2000;
slidePlayer.prototype.isPause=false;
slidePlayer.prototype.isPlaying=false;
slidePlayer.prototype.endPlay=new Function;
slidePlayer.prototype.initial=new Function;
slidePlayer.prototype.getCurrnetNum=function(){return this.currentNum;};
slidePlayer.prototype.goToPlay=function(n){
									var o=this;
									if(o.playTimer||o.playTimer!=null)
										{window.clearInterval(o.playTimer);}
									if(o.loopTimer)
										{window.clearTimeout(o.loopTimer);}
									var d;
									for(var i=0;i<o.imageList.length;i++)
										{o.imageList[i].style.display="none";
										if(o.imageList[i].parentNode&&o.imageList[i].parentNode.tagName.toLowerCase()=='a')
											{d=o.imageList[i].parentNode;}
										else
											{d=o.imageList[i];}
											d.style.zIndex="1";
											d.style.filter="alpha(opacity=100)";
											d.style.MozOpacity=1;
											d.style.opacity=1;
									}
									o.isPlaying=false;
									o.imageList[o.currentNum-1].style.display="block";
									o.play(n);
								};
slidePlayer.prototype.play=function(num){
								var o=this;
								if(o.isPlaying)
									{return;}
								if(num)
									{var nn=num;var on=o.currentNum;}
								else
									{var nn=o.currentNum+1;var on=o.currentNum;}
								if(nn>o.imageList.length)
									{nn=1;}
								if(on==nn)
									{o.loopTimer=window.setTimeout(function(){o.play();},o.waiting);return;}
								if(o.playTimer||o.playTimer!=null)
									{window.clearInterval(o.playTimer);}
								if(o.loopTimer)
									{window.clearTimeout(o.loopTimer);}
								var n_el=(o.imageList[nn-1].parentNode&&o.imageList[nn-1].parentNode.tagName.toLowerCase()=='a')?o.imageList[nn-1].parentNode:o.imageList[nn-1];
								var o_el=(o.imageList[on-1].parentNode&&o.imageList[on-1].parentNode.tagName.toLowerCase()=='a')?o.imageList[on-1].parentNode:o.imageList[on-1];
								n_el.style.zIndex=1;
								o_el.style.zIndex=10;
								o_el.style.filter="alpha(opacity=100)";
								o_el.style.MozOpacity=1;
								o_el.style.opacity=1;
								n_el.style.filter="alpha(opacity=100)";
								n_el.style.MozOpacity=1;
								n_el.style.opacity=1;
								o.imageList[nn-1].style.display="block";
								o.isPlaying=true;
								var n=100;
								var _is_start=false;
								var anim=function(){
											if(o.isPause)
												{o_el.style.filter="alpha(opacity=100)";o_el.style.MozOpacity=1;o_el.style.opacity=1;return;}
											n-=20;
											_is_start=true;
											if(n<=0){
												o_el.style.filter="alpha(opacity=0)";
												o_el.style.MozOpacity=0;
												o_el.style.opacity=0;
												o_el.style.zIndex=1;
												o.imageList[on-1].style.display="none";
												o.isPlaying=false;
												o.currentNum=nn;
												o.loopTimer=window.setTimeout(function(){o.play();},o.waiting);
												window.clearInterval(o.playTimer);
												o.endPlay();
											}else{
												o_el.style.filter="alpha(opacity="+n+")";
												o_el.style.MozOpacity=n/100;o_el.style.opacity=n/100;
											}
										}
								o.playTimer=window.setInterval(anim,o.intervalTime);
							};
function slidePlayer(con_id){
	var o=this;
	var cont=ycn.Common.getEl(con_id);
	if(!cont)
		{return;}
	var imgs=cont.getElementsByTagName("img");
	var tmpnum=Math.round((Math.random()*(imgs.length-1)));
	if(!imgs||imgs.length<=0)
		{return;}
	o.container=cont;
	o.imageList=imgs;
	var img=new Image();
	img.src=imgs[tmpnum].src;
	o.width=img.width;
	o.height=img.height;
	o.container.style.width=o.width+"px";
	o.container.style.height=o.height+"px";
	imgs[tmpnum].style.display="block";
	o.loopTimer=window.setTimeout(function(){o.play();},o.waiting);
	o.initial();
}





function init_slide(){
if(!document.getElementById('slide-imgs')) return;
var obj = new slidePlayer('slide-imgs'),slidenum,t_num,li;
obj.waiting = 5000;
if(obj && ycn.Common.getElByClassName('div','slide_addone',1)){
 ycn.Common.getEl('slideContent').innerHTML = ycn.Common.getElByClassName('div','slide_addone',1).innerHTML;
}
slidenum = ycn.Common.getEl('slidenum');
if(obj && slidenum){
 t_num = "<ul class=\"slideNumber\"><li class=\"curSlideNumber\">1</li>";
 for(var i=1;i<obj.imageList.length; i++){
 t_num +="<li>"+(i+1)+"</li>";
 }
 t_num += "</ul>"
 slidenum.innerHTML = t_num;
 li = slidenum.getElementsByTagName("li");
 for(var j=0; j<li.length;j++){
 ycn.Event.addEvent(li[j],'mouseover',function(e){
 var el = (navigator.userAgent.toLowerCase().indexOf('msie')>0)? e.srcElement : this;
 for(var i=0; i<li.length;i++){
 li[i].className = '';
 }
 el.className = "curSlideNumber";
 obj.goToPlay(parseInt(el.innerHTML));
 });
 }
}
obj.endPlay = function(){
 if(ycn.Common.getElByClassName('div','slide_addone',obj.getCurrnetNum())){
 ycn.Common.getEl('slideContent').innerHTML = ycn.Common.getElByClassName('div','slide_addone',obj.getCurrnetNum()).innerHTML;
 }
 if(li){
 for(var i=0; i<li.length;i++){
 li[i].className = '';
 }
 li[obj.getCurrnetNum()-1].className = 'curSlideNumber';
 }
}
ycn.Event.addEvent(obj.container,'mouseover',function(){obj.isPause = true;});
ycn.Event.addEvent(obj.container,'mouseout',function(){obj.isPause = false;});
}