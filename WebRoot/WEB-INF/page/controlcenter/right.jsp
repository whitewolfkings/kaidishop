<%@ page contentType="text/html;charset=UTF-8" %>
<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<STYLE>
Body{
Scrollbar-Face-Color:#A4C6F9;
Scrollbar-Highlight-Color:#A4C6F9;
Scrollbar-Shadow-Color:#A4C6F9;
Scrollbar-3Dlight-Color:8080FF;
Scrollbar-Darkshadow-Color:8080FF;
Scrollbar-Arrow-Color:FFffff;
Scrollbar-Track-Color:E6E6FF;
}</STYLE>

<SCRIPT language=JavaScript>
<!--
/*****************************************************************************
                                   日期资料
*****************************************************************************/

var lunarInfo=new Array(
0x04bd8,0x04ae0,0x0a570,0x054d5,0x0d260,0x0d950,0x16554,0x056a0,0x09ad0,0x055d2,
0x04ae0,0x0a5b6,0x0a4d0,0x0d250,0x1d255,0x0b540,0x0d6a0,0x0ada2,0x095b0,0x14977,
0x04970,0x0a4b0,0x0b4b5,0x06a50,0x06d40,0x1ab54,0x02b60,0x09570,0x052f2,0x04970,
0x06566,0x0d4a0,0x0ea50,0x06e95,0x05ad0,0x02b60,0x186e3,0x092e0,0x1c8d7,0x0c950,
0x0d4a0,0x1d8a6,0x0b550,0x056a0,0x1a5b4,0x025d0,0x092d0,0x0d2b2,0x0a950,0x0b557,
0x06ca0,0x0b550,0x15355,0x04da0,0x0a5d0,0x14573,0x052d0,0x0a9a8,0x0e950,0x06aa0,
0x0aea6,0x0ab50,0x04b60,0x0aae4,0x0a570,0x05260,0x0f263,0x0d950,0x05b57,0x056a0,
0x096d0,0x04dd5,0x04ad0,0x0a4d0,0x0d4d4,0x0d250,0x0d558,0x0b540,0x0b5a0,0x195a6,
0x095b0,0x049b0,0x0a974,0x0a4b0,0x0b27a,0x06a50,0x06d40,0x0af46,0x0ab60,0x09570,
0x04af5,0x04970,0x064b0,0x074a3,0x0ea50,0x06b58,0x055c0,0x0ab60,0x096d5,0x092e0,
0x0c960,0x0d954,0x0d4a0,0x0da50,0x07552,0x056a0,0x0abb7,0x025d0,0x092d0,0x0cab5,
0x0a950,0x0b4a0,0x0baa4,0x0ad50,0x055d9,0x04ba0,0x0a5b0,0x15176,0x052b0,0x0a930,
0x07954,0x06aa0,0x0ad50,0x05b52,0x04b60,0x0a6e6,0x0a4e0,0x0d260,0x0ea65,0x0d530,
0x05aa0,0x076a3,0x096d0,0x04bd7,0x04ad0,0x0a4d0,0x1d0b6,0x0d250,0x0d520,0x0dd45,
0x0b5a0,0x056d0,0x055b2,0x049b0,0x0a577,0x0a4b0,0x0aa50,0x1b255,0x06d20,0x0ada0)

var solarMonth=new Array(31,28,31,30,31,30,31,31,30,31,30,31);
var Gan=new Array("甲","乙","丙","丁","戊","己","庚","辛","壬","癸");
var Zhi=new Array("子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥");
var Animals=new Array("鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪");
var solarTerm = new Array("小寒","大寒","立春","雨水","惊蛰","春分","清明","谷雨","立夏","小满","芒种","夏至","小暑","大暑","立秋","处暑","白露","秋分","寒露","霜降","立冬","小雪","大雪","冬至")
var sTermInfo = new Array(0,21208,42467,63836,85337,107014,128867,150921,173149,195551,218072,240693,263343,285989,308563,331033,353350,375494,397447,419210,440795,462224,483532,504758)
var nStr1 = new Array('日','一','二','三','四','五','六','七','八','九','十')
var nStr2 = new Array('初','十','廿','卅','　')
var monthName = new Array("JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC");

//国历节日 *表示放假日
var sFtv = new Array(
"0101*元旦",
"0214 情人节",
"0308 妇女节",
"0312 植树节",
"0315 消费者权益日",
"0317 St. Patrick's",
"0401 愚人节",
"0501 劳动节",
"0504 青年节",
"0505 vwebdoctor生日",
"0512 护士节",

"0601 儿童节",
"0613 坤生日",
"0614 Flag Day",
"0701 建党节 香港回归纪念",
"0801 建军节",
"0808 父亲节",

"0909 毛泽东逝世纪念",
"0910 教师节",
"0928 孔子诞辰",
"1001*国庆节",
"1006 老人节",

"1024 联合国日",
"1111 Veteran's / Remembrance Day",
"1112 孙中山诞辰纪念",
"1220 澳门回归纪念",
"1225 Christmas Day",
"1226 毛泽东诞辰纪念")

//农历节日 *表示放假日
var lFtv = new Array(
"0101*春节",
"0115 元宵节",
"0505 端午节",
"0707 七夕情人节",
"0715 中元节",
"0815 中秋节",
"0909 重阳节",
"1208 腊八节",
"1224 小年",
"0100*除夕")

//某月的第几个星期几
var wFtv = new Array(
"0131 Martin Luther King Day",
"0231 President's Day",
"0520 母亲节",
"0530 Armed Forces Day",
"0531 Victoria Day",
"0716 合作节",
"0730 被奴役国家周",
"0811 Civic Holiday",
"0911 Labor Holiday",
"1021 Columbus Day",
"1144 Thanksgiving")


/*****************************************************************************
                                      日期计算
*****************************************************************************/

//====================================== 传回农历 y年的总天数
function lYearDays(y) {
   var i, sum = 348
   for(i=0x8000; i>0x8; i>>=1) sum += (lunarInfo[y-1900] & i)? 1: 0
   return(sum+leapDays(y))
}

//====================================== 传回农历 y年闰月的天数
function leapDays(y) {
   if(leapMonth(y))  return((lunarInfo[y-1900] & 0x10000)? 30: 29)
   else return(0)
}

//====================================== 传回农历 y年闰哪个月 1-12 , 没闰传回 0
function leapMonth(y) {
   return(lunarInfo[y-1900] & 0xf)
}

//====================================== 传回农历 y年m月的总天数
function monthDays(y,m) {
   return( (lunarInfo[y-1900] & (0x10000>>m))? 30: 29 )
}

//====================================== 算出农历, 传入日期物件, 传回农历日期物件
//                                       该物件属性有 .year .month .day .isLeap .yearCyl .dayCyl .monCyl
function Lunar(objDate) {

   var i, leap=0, temp=0
   var baseDate = new Date(1900,0,31)
   var offset   = (objDate - baseDate)/86400000

   this.dayCyl = offset + 40
   this.monCyl = 14

   for(i=1900; i<2050 && offset>0; i++) {
      temp = lYearDays(i)
      offset -= temp
      this.monCyl += 12
   }

   if(offset<0) {
      offset += temp;
      i--;
      this.monCyl -= 12
   }

   this.year = i
   this.yearCyl = i-1864

   leap = leapMonth(i) //闰哪个月
   this.isLeap = false

   for(i=1; i<13 && offset>0; i++) {
      //闰月
      if(leap>0 && i==(leap+1) && this.isLeap==false)
         { --i; this.isLeap = true; temp = leapDays(this.year); }
      else
         { temp = monthDays(this.year, i); }

      //解除闰月
      if(this.isLeap==true && i==(leap+1)) this.isLeap = false

      offset -= temp
      if(this.isLeap == false) this.monCyl ++
   }

   if(offset==0 && leap>0 && i==leap+1)
      if(this.isLeap)
         { this.isLeap = false; }
      else
         { this.isLeap = true; --i; --this.monCyl;}

   if(offset<0){ offset += temp; --i; --this.monCyl; }

   this.month = i
   this.day = offset + 1
}

//==============================传回国历 y年某m+1月的天数
function solarDays(y,m) {
   if(m==1)
      return(((y%4 == 0) && (y%100 != 0) || (y%400 == 0))? 29: 28)
   else
      return(solarMonth[m])
}
//============================== 传入 offset 传回干支, 0=甲子
function cyclical(num) {
   return(Gan[num%10]+Zhi[num%12])
}

//============================== 月历属性
function calElement(sYear,sMonth,sDay,week,lYear,lMonth,lDay,isLeap,cYear,cMonth,cDay) {

      this.isToday    = false;
      //国历
      this.sYear      = sYear;
      this.sMonth     = sMonth;
      this.sDay       = sDay;
      this.week       = week;
      //农历
      this.lYear      = lYear;
      this.lMonth     = lMonth;
      this.lDay       = lDay;
      this.isLeap     = isLeap;
      //干支
      this.cYear      = cYear;
      this.cMonth     = cMonth;
      this.cDay       = cDay;

      this.color      = '';

      this.lunarFestival = ''; //农历节日
      this.solarFestival = ''; //国历节日
      this.solarTerms    = ''; //节气

}

//===== 某年的第n个节气为几日(从0小寒起算)
function sTerm(y,n) {
   var offDate = new Date( ( 31556925974.7*(y-1900) + sTermInfo[n]*60000  ) + Date.UTC(1900,0,6,2,5) )
   return(offDate.getUTCDate())
}

//============================== 传回月历物件 (y年,m+1月)
function calendar(y,m) {

   var sDObj, lDObj, lY, lM, lD=1, lL, lX=0, tmp1, tmp2
   var lDPOS = new Array(3)
   var n = 0
   var firstLM = 0

   sDObj = new Date(y,m,1)            //当月一日日期

   this.length    = solarDays(y,m)    //国历当月天数
   this.firstWeek = sDObj.getDay()    //国历当月1日星期几


   for(var i=0;i<this.length;i++) {

      if(lD>lX) {
         sDObj = new Date(y,m,i+1)    //当月一日日期
         lDObj = new Lunar(sDObj)     //农历
         lY    = lDObj.year           //农历年
         lM    = lDObj.month          //农历月
         lD    = lDObj.day            //农历日
         lL    = lDObj.isLeap         //农历是否闰月
         lX    = lL? leapDays(lY): monthDays(lY,lM) //农历当月最後一天

         if(n==0) firstLM = lM
         lDPOS[n++] = i-lD+1
      }

      //sYear,sMonth,sDay,week,
      //lYear,lMonth,lDay,isLeap,
      //cYear,cMonth,cDay
      this[i] = new calElement(y, m+1, i+1, nStr1[(i+this.firstWeek)%7],
                               lY, lM, lD++, lL,
                               cyclical(lDObj.yearCyl) ,cyclical(lDObj.monCyl), cyclical(lDObj.dayCyl++) )


      if((i+this.firstWeek)%7==0)   this[i].color = 'red'  //周日颜色
      if((i+this.firstWeek)%14==13) this[i].color = 'red'  //周休二日颜色
   }

   //节气
   tmp1=sTerm(y,m*2  )-1
   tmp2=sTerm(y,m*2+1)-1
   this[tmp1].solarTerms = solarTerm[m*2]
   this[tmp2].solarTerms = solarTerm[m*2+1]
   if(m==3) this[tmp1].color = 'red' //清明颜色

   //国历节日
   for(i in sFtv)
      if(sFtv[i].match(/^(\d{2})(\d{2})([\s\*])(.+)$/))
         if(Number(RegExp.$1)==(m+1)) {
            this[Number(RegExp.$2)-1].solarFestival += RegExp.$4 + ' '
            if(RegExp.$3=='*') this[Number(RegExp.$2)-1].color = 'red'
         }

   //月周节日
   for(i in wFtv)
      if(wFtv[i].match(/^(\d{2})(\d)(\d)([\s\*])(.+)$/))
         if(Number(RegExp.$1)==(m+1)) {
            tmp1=Number(RegExp.$2)
            tmp2=Number(RegExp.$3)
            this[((this.firstWeek>tmp2)?7:0) + 7*(tmp1-1) + tmp2 - this.firstWeek].solarFestival += RegExp.$5 + ' '
         }

   //农历节日
   for(i in lFtv)
      if(lFtv[i].match(/^(\d{2})(.{2})([\s\*])(.+)$/)) {
         tmp1=Number(RegExp.$1)-firstLM
         if(tmp1==-11) tmp1=1
         if(tmp1 >=0 && tmp1<n) {
            tmp2 = lDPOS[tmp1] + Number(RegExp.$2) -1
            if( tmp2 >= 0 && tmp2<this.length) {
               this[tmp2].lunarFestival += RegExp.$4 + ' '
               if(RegExp.$3=='*') this[tmp2].color = 'red'
            }
         }
      }

   //黑色星期五
   if((this.firstWeek+12)%7==5)
      this[12].solarFestival += '黑色星期五 '

   //今日
   if(y==tY && m==tM) this[tD-1].isToday = true;

}

//====================== 中文日期
function cDay(d){
   var s;

   switch (d) {
      case 10:
         s = '初十'; break;
      case 20:
         s = '二十'; break;
         break;
      case 30:
         s = '三十'; break;
         break;
      default :
         s = nStr2[Math.floor(d/10)];
         s += nStr1[d%10];
   }
   return(s);
}

///////////////////////////////////////////////////////////////////////////////

var cld;

function drawCld(SY,SM) {
   var i,sD,s,size;
   cld = new calendar(SY,SM);

   if(SY>1874 && SY<1909) yDisplay = '光绪' + (((SY-1874)==1)?'元':SY-1874)
   if(SY>1908 && SY<1912) yDisplay = '宣统' + (((SY-1908)==1)?'元':SY-1908)
   if(SY>1911 && SY<1950) yDisplay = '民国' + (((SY-1911)==1)?'元':SY-1911)
   if(SY>1949) yDisplay = '人民共和国' + (((SY-1949)==1)?'元':SY-1949)

   GZ.innerHTML = yDisplay +'年 农历' + cyclical(SY-1900+36) + '年 &nbsp;&nbsp;【'+Animals[(SY-4)%12]+'】';

   YMBG.innerHTML = "&nbsp;" + SY + "<BR>&nbsp;" + monthName[SM];


   for(i=0;i<42;i++) {

      sObj=eval('SD'+ i);
      lObj=eval('LD'+ i);

      sObj.className = '';

      sD = i - cld.firstWeek;

      if(sD>-1 && sD<cld.length) { //日期内
         sObj.innerHTML = sD+1;

         if(cld[sD].isToday) sObj.className = 'todyaColor'; //今日颜色

         sObj.style.color = cld[sD].color; //国定假日颜色

         if(cld[sD].lDay==1) //显示农历月
            lObj.innerHTML = '<b>'+(cld[sD].isLeap?'闰':'') + cld[sD].lMonth + '月' + (monthDays(cld[sD].lYear,cld[sD].lMonth)==29?'小':'大')+'</b>';
         else //显示农历日
            lObj.innerHTML = cDay(cld[sD].lDay);

         s=cld[sD].lunarFestival;
         if(s.length>0) { //农历节日
            if(s.length>6) s = s.substr(0, 4)+'…';
            s = s.fontcolor('red');
         }
         else { //国历节日
            s=cld[sD].solarFestival;
            if(s.length>0) {
               size = (s.charCodeAt(0)>0 && s.charCodeAt(0)<128)?8:4;
               if(s.length>size+2) s = s.substr(0, size)+'…';
               s = s.fontcolor('blue');
            }
            else { //廿四节气
               s=cld[sD].solarTerms;
               if(s.length>0) s = s.fontcolor('limegreen');
            }
         }
         if(s.length>0) lObj.innerHTML = s;

      }
      else { //非日期
         sObj.innerHTML = '';
         lObj.innerHTML = '';
      }
   }
}


function changeCld() {
   var y,m;
   y=CLD.SY.selectedIndex+1900;
   m=CLD.SM.selectedIndex;
   drawCld(y,m);
}

function pushBtm(K) {
   switch (K){
      case 'YU' :
         if(CLD.SY.selectedIndex>0) CLD.SY.selectedIndex--;
         break;
      case 'YD' :
         if(CLD.SY.selectedIndex<149) CLD.SY.selectedIndex++;
         break;
      case 'MU' :
         if(CLD.SM.selectedIndex>0) {
            CLD.SM.selectedIndex--;
         }
         else {
            CLD.SM.selectedIndex=11;
            if(CLD.SY.selectedIndex>0) CLD.SY.selectedIndex--;
         }
         break;
      case 'MD' :
         if(CLD.SM.selectedIndex<11) {
            CLD.SM.selectedIndex++;
         }
         else {
            CLD.SM.selectedIndex=0;
            if(CLD.SY.selectedIndex<149) CLD.SY.selectedIndex++;
         }
         break;
      default :
         CLD.SY.selectedIndex=tY-1900;
         CLD.SM.selectedIndex=tM;
   }
   changeCld();
}



var Today = new Date();
var tY = Today.getFullYear();
var tM = Today.getMonth();
var tD = Today.getDate();
//////////////////////////////////////////////////////////////////////////////

var width = "130";
var offsetx = 2;
var offsety = 16;

var x = 0;
var y = 0;
var snow = 0;
var sw = 0;
var cnt = 0;

var dStyle;
document.onmousemove = mEvn;

//显示详细日期资料
function mOvr(v) {
   var s,festival;
   var sObj=eval('SD'+ v);
   var d=sObj.innerHTML-1;

      //sYear,sMonth,sDay,week,
      //lYear,lMonth,lDay,isLeap,
      //cYear,cMonth,cDay

   if(sObj.innerHTML!='') {

      sObj.style.cursor = 's-resize';

      if(cld[d].solarTerms == '' && cld[d].solarFestival == '' && cld[d].lunarFestival == '')
         festival = '';
      else
         festival = '<TABLE WIDTH=100% BORDER=0 CELLPADDING=2 CELLSPACING=0 BGCOLOR="#CCFFCC"><TR><TD>'+
         '<FONT COLOR="#000000" STYLE="font-size:9pt;">'+cld[d].solarTerms + ' ' + cld[d].solarFestival + ' ' + cld[d].lunarFestival+'</FONT></TD>'+
         '</TR></TABLE>';

      s= '<TABLE WIDTH="130" BORDER=0 CELLPADDING="2" CELLSPACING=0 BGCOLOR="#000066"><TR><TD>' +
         '<TABLE WIDTH=100% BORDER=0 CELLPADDING=0 CELLSPACING=0><TR><TD ALIGN="right"><FONT COLOR="#ffffff" STYLE="font-size:9pt;">'+
         cld[d].sYear+' 年 '+cld[d].sMonth+' 月 '+cld[d].sDay+' 日<br>星期'+cld[d].week+'<br>'+
         '<font color="violet">农历'+(cld[d].isLeap?'闰 ':' ')+cld[d].lMonth+' 月 '+cld[d].lDay+' 日</font><br>'+
         '<font color="yellow">'+cld[d].cYear+'年 '+cld[d].cMonth+'月 '+cld[d].cDay + '日</font>'+
         '</FONT></TD></TR></TABLE>'+ festival +'</TD></TR></TABLE>';


      document.all["detail"].innerHTML = s;

   	if (snow == 0) {
         dStyle.left = x+offsetx-(width/2);
         dStyle.top = y+offsety;
   		dStyle.visibility = "visible";
   		snow = 1;
   	}
	}
}

//清除详细日期资料
function mOut() {
	if ( cnt >= 1 ) { sw = 0 }
	if ( sw == 0 ) { snow = 0;	dStyle.visibility = "hidden";}
	else cnt++;
}

//取得位置
function mEvn() {
   x=event.x;
   y=event.y;
	if (document.body.scrollLeft)
	   {x=event.x+document.body.scrollLeft; y=event.y+document.body.scrollTop;}
	if (snow){
      dStyle.left = x+offsetx-(width/2)
      dStyle.top = y+offsety
	}
}

///////////////////////////////////////////////////////////////////////////

function changeTZ() {
   CITY.innerHTML = CLD.TZ.value.substr(6)
   setCookie("TZ",CLD.TZ.selectedIndex)
}

function tick() {
   var today
   today = new Date()
   Clock.innerHTML = today.toLocaleString().replace(/(年|月)/g, "/").replace(/日/, "");
   Clock.innerHTML = TimeAdd(today.toGMTString(), CLD.TZ.value)
   window.setTimeout("tick()", 1000);
}

function setCookie(name, value) {
	var today = new Date()
	var expires = new Date()
	expires.setTime(today.getTime() + 1000*60*60*24*365)
	document.cookie = name + "=" + escape(value)	+ "; expires=" + expires.toGMTString()
}

function getCookie(Name) {
   var search = Name + "="
   if(document.cookie.length > 0) {
      offset = document.cookie.indexOf(search)
      if(offset != -1) {
         offset += search.length
         end = document.cookie.indexOf(";", offset)
         if(end == -1) end = document.cookie.length
         return unescape(document.cookie.substring(offset, end))
      }
      else return ""
   }
}

/////////////////////////////////////////////////////////

function initial() {
   dStyle = detail.style;
   CLD.SY.selectedIndex=tY-1900;
   CLD.SM.selectedIndex=tM;
   drawCld(tY,tM);

   CLD.TZ.selectedIndex=0;//getCookie("TZ");
   changeTZ();
   tick();
}



//-->
</SCRIPT>

<SCRIPT language=VBScript>
<!--
'===== 算世界时间
Function TimeAdd(UTC,T)
   Dim PlusMinus, DST, y
   If Left(T,1)="-" Then PlusMinus = -1 Else PlusMinus = 1
   UTC=Right(UTC,Len(UTC)-5)
   UTC=Left(UTC,Len(UTC)-4)
   y = Year(UTC)
   TimeAdd=DateAdd("n", (Cint(Mid(T,2,2))*60 + Cint(Mid(T,4,2))) * PlusMinus, UTC)
   '美国日光节约期间: 4月第一个星日00:00 至 10月最後一个星期日00:00
   If Mid(T,6,1)="*" And DateSerial(y,4,(9 - Weekday(DateSerial(y,4,1)) mod 7) ) <= TimeAdd And DateSerial(y,10,31 - Weekday(DateSerial(y,10,31))) >= TimeAdd Then
      TimeAdd=CStr(DateAdd("h", 1, TimeAdd))
      tSave.innerHTML = "R"
   Else
      tSave.innerHTML = ""
   End If
   TimeAdd = CStr(TimeAdd)
End Function
'-->
</SCRIPT>

<STYLE>.todyaColor {
	BACKGROUND-COLOR: aqua
}
</STYLE>

<META content="MSHTML 6.00.2600.0" name=GENERATOR></HEAD>
<BODY onload=initial() >
<SCRIPT language=JavaScript><!--
   if(navigator.appName == "Netscape" || parseInt(navigator.appVersion) < 4)
   document.write("<h1>你的浏览器无法执行此程序。</h1>此程序在 IE4 以后的版本才能执行!!")
//--></SCRIPT>
<DIV id=detail style="POSITION: absolute"></DIV>
<CENTER>
<FORM name=CLD>
<TABLE >
  <TBODY>
  <TR>
    <TD vAlign=top align=middle><BR><FONT style="FONT-SIZE: 9pt" 
      size=2>本地时间:</FONT><BR><FONT id=Clock face=Arial color=#000080 size=4 
      align="center"></FONT>
      <P><!--时区 *表示自动调整为日光节约时间--><FONT style="FONT-SIZE: 9pt" size=2>
	  <SELECT 
      style="FONT-SIZE: 9pt" onchange=changeTZ() name=TZ>
       <OPTION value="+0800 北京、重庆、黑龙江" selected>中国
              <OPTION 
        value="-1200 安尼威土克、瓜甲兰">国际换日线
              <OPTION 
        value="-1100 中途岛、萨摩亚群岛">萨摩亚
              <OPTION value="-1000 夏威夷">夏威夷
              <OPTION 
        value=-0900*阿拉斯加>阿拉斯加
              <OPTION value=-0800*太平洋时间(美加)、提亚纳>太平洋
              <OPTION 
        value="-0700 亚历桑那">美国山区
              <OPTION value=-0700*山区时间(美加)>美加山区
              <OPTION 
        value=-0600*萨克其万(加拿大)>加拿大中部
              <OPTION value=-0600*墨西哥市、塔克西卡帕>墨西哥
              <OPTION 
        value=-0600*中部时间(美加)>美加中部
              <OPTION value=-0500*波哥大、里玛>南美洲太平洋
              <OPTION 
        value=-0500*东部时间(美加)>美加东部
              <OPTION value=-0500*印第安纳(东部)>美东
              <OPTION 
        value=-0400*加拉卡斯、拉帕兹>南美洲西部
              <OPTION value="-0400*大西洋时间 加拿大)">大西洋
              <OPTION 
        value="-0330 新岛(加拿大东岸)">纽芬兰
              <OPTION value="-0300 波西尼亚">东南美洲
              <OPTION 
        value="-0300 布鲁诺斯爱丽斯、乔治城">南美洲东部
              <OPTION value=-0200*大西洋中部>大西洋中部
              <OPTION 
        value=-0100*亚速尔群岛、维德角群岛>亚速尔
              <OPTION 
        value="+0000 格林威治时间、都柏林、爱丁堡、伦敦">英国夏令
              <OPTION 
        value="+0000 莫洛维亚(赖比瑞亚)、卡萨布兰卡">格林威治标准
              <OPTION 
        value="+0100 巴黎、马德里">罗马
              <OPTION value="+0100 布拉格, 华沙, 布达佩斯">中欧
              <OPTION 
        value="+0100 柏林、斯德哥尔摩、罗马、伯恩、布鲁赛尔、维也纳">西欧
              <OPTION 
        value="+0200 以色列">以色列
              <OPTION value=+0200*东欧>东欧
              <OPTION 
        value=+0200*开罗>埃及
              <OPTION value=+0200*雅典、赫尔辛基、伊斯坦堡>GFT
              <OPTION 
        value=+0200*赫拉雷、皮托里>南非
              <OPTION 
        value=+0300*巴格达、科威特、奈洛比(肯亚)、里雅德(沙乌地)>沙乌地阿拉伯
              <OPTION 
        value=+0300*莫斯科、圣彼得堡、贺占、窝瓦格瑞德>俄罗斯
              <OPTION value=+0330*德黑兰>伊朗
              <OPTION 
        value=+0400*阿布达比(东阿拉伯)、莫斯凯、塔布理斯(乔治亚共和)>阿拉伯
              <OPTION 
        value=+0430*喀布尔>阿富汗
              <OPTION value="+0500 伊斯兰马巴德、克洛奇、伊卡特林堡、塔须肯">西亚
              <OPTION 
        value="+0530 孟买、加尔各答、马垂斯、新德里、可伦坡">印度
              <OPTION 
        value="+0600 阿马提、达卡">中亚
              <OPTION value="+0700 曼谷、亚加达、胡志明市">曼谷
              <OPTION value="+0800 台湾、香港、新加坡">台北
              <OPTION 
        value="+0900 东京、大阪、扎幌、汉城、亚库兹(东西伯利亚)">东京
              <OPTION 
        value="+0930 达尔文">澳洲中部
              <OPTION value="+1000 布里斯本、墨尔本、席德尼">席德尼
              <OPTION 
        value="+1000 霍巴特">塔斯梅尼亚
              <OPTION value="+1000 关岛、莫斯比港、海　威">西太平洋
              <OPTION 
        value=+1100*马哥大、所罗门群岛、新卡伦多尼亚>太平洋中部
              <OPTION 
        value="+1200 威灵顿、奥克兰">纽西兰
              <OPTION 
      value="+1200 斐济、肯加塔、马歇尔群岛">斐济</OPTION>
            </SELECT>时间</FONT> <FONT id=tSave 
      style="FONT-SIZE: 18pt; COLOR: red; FONT-FAMILY: Wingdings"></FONT><BR><BR><FONT 
      style="FONT-SIZE: 120pt; COLOR: green; FONT-FAMILY: Webdings">&ucirc;</FONT><BR><FONT 
      id=CITY 
      style="FONT-SIZE: 9pt; WIDTH: 150px; COLOR: blue; FONT-FAMILY: '宋体'"></FONT></P></TD>
    <TD align=middle>
      <DIV style="Z-INDEX: -1; POSITION: absolute; TOP: 30px"><FONT id=YMBG 
      style="FONT-SIZE: 100pt; COLOR: #f0f0f0; FONT-FAMILY: 'Arial Black'">&nbsp;0000<BR>&nbsp;JUN</FONT> 
      </DIV>
      <TABLE border=0>
        <TBODY>
        <TR>
          <TD bgColor=#000080 colSpan=7><FONT style="FONT-SIZE: 9pt" 
            color=#ffffff size=2>西历<SELECT style="FONT-SIZE: 9pt" 
            onchange=changeCld() name=SY> 
              <SCRIPT language=JavaScript><!--
            for(i=1900;i<2050;i++) document.write('<option>'+i)
            //--></SCRIPT>
            </SELECT>年<SELECT style="FONT-SIZE: 9pt" onchange=changeCld() 
            name=SM> 
              <SCRIPT language=JavaScript><!--
            for(i=1;i<13;i++) document.write('<option>'+i)
            //--></SCRIPT>
            </SELECT>月</FONT> <FONT id=GZ face=宋体 color=#ffffff 
            size=2></FONT><BR></TD></TR>
        <TR align=middle bgColor=#e0e0e0>
          <TD width=54>日</TD>
          <TD width=54>一</TD>
          <TD width=54>二</TD>
          <TD width=50>三</TD>
          <TD width=54>四</TD>
          <TD width=54>五</TD>
          <TD width=54>六</TD></TR>
        <SCRIPT language=JavaScript>
            var gNum
            for(i=0;i<6;i++) {
               document.write('<tr align=center>')
               for(j=0;j<7;j++) {
                  gNum = i*7+j
                  document.write('<td id="GD' + gNum +'" onMouseOver="mOvr(' + gNum +')" onMouseOut="mOut()"><font id="SD' + gNum +'" size=5 face="Arial Black"')
                  if(j == 0) document.write(' color=red')
                  if(j == 6)
                     if(i%2==1) document.write(' color=red')
                        else document.write(' color=green')
                  document.write(' TITLE=""> </font><br><font id="LD' + gNum + '" size=2 style="font-size:9pt"> </font></td>')
               }
               document.write('</tr>')
            }
			</SCRIPT>
        </TBODY></TABLE></TD>
    <TD vAlign=top align=middle width=60><BR><BR><BR><BR><BUTTON 
      style="FONT-SIZE: 9pt" onclick="pushBtm('YU')">年↑</BUTTON><BR><BUTTON 
      style="FONT-SIZE: 9pt" onclick="pushBtm('YD')">年↓</BUTTON> 
      <P><BUTTON style="FONT-SIZE: 9pt" 
      onclick="pushBtm('MU')">月↑</BUTTON><BR><BUTTON style="FONT-SIZE: 9pt" 
      onclick="pushBtm('MD')">月↓</BUTTON> 
      <P><BUTTON style="FONT-SIZE: 9pt" onclick="pushBtm('')">当月</BUTTON> 
</TD></TR></TBODY></TABLE></FORM></CENTER></BODY></HTML>
