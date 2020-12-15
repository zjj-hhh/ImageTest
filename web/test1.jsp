<%--
  Created by IntelliJ IDEA.
  User: 周佳杰1699
  Date: 2020/12/15
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>分类结果统计图展示</title>
    <!-- 引入 echarts.js -->
    <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    var count1 = 0;
    var count2 = 0;
    var count3 = 0;
    var count4 = 0;
    var count5 = 0;
    var str="人@猫@狗@车@船";
    var a = str.split("@");
    for( var i = 0; i < a.length; i++){
        if(a[i] == "人"){
            count1 += 1;
        }
        else if(a[i] == "猫"){
            count2 += 1;
        }
        else if(a[i] == "狗"){
            count3 += 1;
        }
        else if(a[i] == "车"){
            count4 += 1;
        }
        else if(a[i] == "船"){
            count5 += 1;
        }
    }
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '分类结果柱形图'
        },
        tooltip: {},
        legend: {
            data:['图片数量']
        },
        xAxis: {
            data: ["人","猫","狗","车","船"]
        },
        yAxis: {},
        series: [{
            name: '图片数量',
            type: 'bar',
            data: [count1, count2, count3, count4, count5]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>
