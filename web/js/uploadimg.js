var a = 0;
var i = 0;
var b;
var it=document.getElementById("innerText");
it.innerText="已选择"+0+"张图片,"+"还可选择"+10+"张图片";
$(function() {
    $("#inputs").change(function() {
        var count = 0;
        var fil = this.files;
        a += fil.length;
        for(var i = 0; i < fil.length; i++) {
            var curr = fil[i].size;
            if(a > 10){
                alert("选择图片超上限10张！请重新选择图片！")
                a -= fil.length;
                break;
            }
            else if(a == 10){
                for( var j = i; j < fil.length; j++){
                    reads(fil[j]);
                }
                alert("选择图片已达上限10张！将无法继续选择图片！")
                $('.uploadDIv').hide();
                var it=document.getElementById("innerText");
                it.innerText="已选择"+10+"张图片,"+"还可选择"+0+"张图片";
                break;
            }
            else{
                reads(fil[i]);
            }
            var it=document.getElementById("innerText");
            b = 10-a;
            it.innerText="已选择"+a+"张图片,"+"还可选择"+b+"张图片";
        }
    });
})
function removethis(i){
    $("#img"+i+"").remove();
    if($("#uploadBox").children().length<10){
        a -= 1;
        b = 10-a;
        var it=document.getElementById("innerText");
        it.innerText="已选择"+a+"张图片,"+"还可选择"+b+"张图片";
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

function uploadFile(){
    var fileList = document.getElementById("inputs").files;
    alert("上传图片成功");
    for(var i = 0; i < fileList.length; i++){
        var fileObj = fileList[i]; // 获取文件对象
        var FileController = "ImgUploadServlet"; // 接收上传文件的后台地址

        if(fileObj){
            // FormData 对象
            var form = new FormData();
            form.append("file", fileObj);// 文件对象

            // XMLHttpRequest 对象
            var xhr = new XMLHttpRequest();
            xhr.open("post", FileController, true);
            xhr.send(form);

        }else{
            alert("未选择文件");
        }
    }
}