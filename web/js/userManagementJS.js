function deleteUser(userID){

    jQuery.ajax({
        url:'deleteUserServlet',
        data:{
            "userID":userID,
        },
        type:'post',
        datatype:'json',
        success:function (message){
            alert(message.msg);
            window.location.href="http://localhost:8080/ideaProject_war_exploded/users_management.jsp";
        },
        error:function (){
            alert("删除失败！");
        },
    });
}
function updateUser(userID,userName,userPassword,userAuthority){
    console.log(userID);

    var username ='#update_user_name'+userID;
    var userpassword = '#update_user_password'+userID;
    var userauthority = '#update_user_authority'+userID;
    console.log(username);
    jQuery.ajax({
        url:'updateUserServlet',
        data:{
            "user_id":userID,
            "user_name":jQuery(username).val(),
            "user_password":jQuery(userpassword).val(),
            "user_authority":jQuery(userauthority).val(),
        },
        type:'post',
        datatype:'json',
        success:function (message){
            alert(message.msg);
            window.location.href="http://localhost:8080/ideaProject_war_exploded/users_management.jsp";
        },
        error:function (){
            alert("修改失败！");
        },
    });

}