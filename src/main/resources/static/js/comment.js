    $("#commentbtn").click(function (){

    var x=$("input[name=groupod]").length;
    console.log(x);
    var arr=new Array(x);

    for (let i = 0; i <x ; i++) {
    var q=$("input[name=groupod]").eq(i);
    console.log(q);
    if(q==2)
{
    alert("2000");
}
}
    console.log("==========");

});
    // 다음 버튼 클릭
    $("#next").click(function(){

    // 첫번째 체크박스가 체크되어 있는경우
    if($("#chk1").is(":checked")){
    alert("첫번째 통과");

} else { // 첫번째 체크박스가 체크 되어있지 않은 있는경우
    alert("첫번째 항목을 체크 해주세요.")
    return false;
}

    // 두번째 체크박스가 체크되어 있는경우
    if($("#chk2").is(":checked")){
    alert("두번째 통과");
} else { // 두번째 체크박스가 체크 되어있지 않은 있는경우
    alert("두번째 항목을 체크 해주세요.");
    return false;
}

    location.href="https://naver.com";
});