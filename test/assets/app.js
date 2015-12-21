/**
 * Created by donghao on 2015-12-21.
 */
window.onload=function(){
    var btnTest=document.getElementById("btnTest");
    var btnTestReturn=document.getElementById("btnTestReturn");
    var btnClear=document.getElementById("clear");
    btnTest.onclick=function(){
        window.control.callJs("Call Java Success");
    }
    btnTestReturn.onclick=function(){
        var res=window.control.plus(1,2);
        alert(res);
    }
    btnClear.onclick=function(){
        var dv=document.getElementById("dv");
        dv.style.backgroundColor="white";
    }
}
function changeBc(){
    var dv=document.getElementById("dv");
    dv.style.backgroundColor="red";
}

function getPlus(a,b){
    window.control.callBack(a+b);
}