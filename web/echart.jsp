<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'echart.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript"
			src="http://echarts.baidu.com/build/dist/echarts.js"></script>
		<script type="text/javascript" src="jquery.min.js"></script>
		<script type="text/javascript">
	// 路径配置
	require.config({
		paths : {
			echarts : 'http://echarts.baidu.com/build/dist'
		}
	});
	// 使用
	require([ 'echarts', 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
	], 
	drewEcharts
);
	function drewEcharts(ec) {
		// 基于准备好的dom，初始化echarts图表
		var myChart = ec.init(document.getElementById('main'));

		var option = {
			tooltip : {
				show : true
			},
			legend : {
				data : [ '销量' ]
			},
			xAxis : [ {
				type : 'category',
				data :(function(){
                                    var arr=[];
                                        $.ajax({
                                        type : "post",
                                        async : false, //同步执行
                                        url : "bar.do",
                                        data : {},
                                        dataType : "json", //返回数据形式为json
                                        success : function(result) {
                                        if (result) {
                                               for(var i=0;i<result.length;i++){
                                                  console.log(result[i].name);
                                                  arr.push(result[i].name);
                                                }    
                                        }
                                        
                                    },
                                    error : function(errorMsg) {
                                        alert("不好意思，图表请求数据失败啦!");
                                        myChart.hideLoading();
                                    }
                                   })
                                   return arr;
                                })() 
			
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [ {
				"name" : "销量",
				"type" : "bar",
				"data" : (function(){
                                        var arr=[];
                                        $.ajax({
                                        type : "post",
                                        async : false, //同步执行
                                        url : "bar.do",
                                        data : {},
                                        dataType : "json", //返回数据形式为json
                                        success : function(result) {
                                        if (result) {
                                               for(var i=0;i<result.length;i++){
                                                  console.log(result[i].num);
                                                  arr.push(result[i].num);
                                                }  
                                        }
                                    },
                                    error : function(errorMsg) {
                                        alert("不好意思，大爷，图表请求数据失败啦!");
                                        myChart.hideLoading();
                                    }
                                   })
                                  return arr;
                            })()
                            
			} ]
		};

		// 为echarts对象加载数据 
		myChart.setOption(option);
	}
</script>
	</head>

	<body>
		<div id="main" style="height: 800px; width: 1000Px"></div>
	</body>
</html>
