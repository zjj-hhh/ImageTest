var i = 0;
var a = 0;
$(function() {
    // var img = []; //创建一个空对象用来保存传入的图片
    var AllowImgFileSize = '1024'; //1兆
    $("#inputs").change(function() {
        var fil = this.files;
        a += fil.length;
        for(var i = 0; i < fil.length; i++) {
            var curr = fil[i].size;
            if(curr > AllowImgFileSize * 1024) { //当图片大于99兆提示
                layer.msg("图片文件大小超过限制 请上传小于1M的文件");
            }
            else {
                // reads(fil[i]);
                if(a > 10){
                    alert("选择图片超上限10张！请重新选择图片！")
                    a -= fil.length;
                    break;
                }
                else if(a == 10){
                    reads(fil[i]);
                    alert("选择图片已达上限10张！将无法继续选择图片！")
                    $('.uploadDIv').hide();
                    break;
                }
                else{
                    reads(fil[i]);
                }
                // img.push(fil[i]); // 将传入的图片push到空对象中
            }
        }
        // if(a >= 10) { //判断图片的数量，数量不能超过50张
        //     $('.uploadDIv').hide();
        //     alert("选择图片已达上限10张！将无法继续选择图片！")
        // } else {
        //     $('.uploadDIv').show();
        // }
    });
})
function removethis(i){

    $("#img"+i+"").remove()
    if($("#uploadBox").children().length<10){
        a -= 1;
        $('.uploadDIv').show();
    }
}
function reads(fil) {
    var reader = new FileReader();
    reader.readAsDataURL(fil);
    reader.onload = function() {
        document.getElementById("uploadBox").innerHTML += "<div id='img"+i+"' class='divImg' id='uploadImg'><img onclick='showpicture(this)' src='" + reader.result + "' class='imgBiMG'><img style='width: 1%; float: left;margin-top: 1%; display: block; display: block;' src='image/delete.png' onclick='removethis("+i+")'></div>";
        i++
    }
}

//添加点击图片放大
function showpicture(pic){
    $(".filterPop").css("display","block")
    var setPopsWidth=$(".setPops").width();
    $(".setPops, .filterPop").fadeIn();
    $(".setPops").css({height:setPopsWidth,marginTop: "-400px"});
    $(".setPops").children("img")[0].src=pic.src;
}
function toHide() {
    $(".setPops, .filterPop").fadeOut()
}