<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>weixin_nav_form</title>
<link rel="stylesheet" href="bs/css/bootstrap.css" />
<link rel="stylesheet" href="public/css/components.css" />
<link rel="stylesheet" href="public/css/inframe.css" />
<style>
#cpu_container, #mem_container, #disk_container, #net_container {
	width: 50%;
	float: left;
}
</style>
<script src="jquery/jquery-2.1.0.min.js"></script>
<script src="json/json2.js"></script>
<script src="bs/js/bootstrap.js"></script>
<script src="js-highcharts/highcharts.js"></script>
<script>
	$(document)
			.ready(
					function() {
						Highcharts.setOptions({
							global : {
								useUTC : false
							}
						});

						/////////////////////////////////////////////////////////////////////////
						$('#cpu_container')
								.highcharts(
										{

											chart : {
												type : 'spline', // 选择图表类型，类型包括：http://api.highcharts.com/highcharts#plotOptions
												animation : Highcharts.svg, // don't animate in old IE
												backgroundColor : '#F1F1F1', // 设置背景颜色
												borderColor : '#BFBFBF',
												borderRadius : '0',
												borderWidth : '1',
												className : 'cpu_chart', // 给生成的最外层元素（.highcharts-container）添加class
												marginRight : 10,
												// height: 800, // 实际背景部分所占高度为height-10；
												// width: 300, // 默认是占满
												reflow : true, // 改变窗口大小的时候是否改编图表大小
												// 网格线区域的设置
												// plotBackgroundColor: 'blue',
												// plotBackgroundImage: 'xxx.jpg',
												// plotBorderColor: '#000',
												// plotBorderWidth: 0, // 网格线区域的边框，默认为0
												// showAxes: true,
												style : {
													fontFamily : '"微软雅黑", serif'
												// fontSize: '12px'
												}
											},
											title : {
												text : 'CPU使用率'
											},
											// subtitle: {
											// 	text: '副标题'
											// },
											xAxis : {
												type : 'datetime', // 类型有： linear, logarithmic, datetime, categories.
												tickPixelInterval : 150,
												title : {
													text : '时间'
												}
											// tickInterval: 1 // 无效？
											},
											yAxis : { // 可以用[]添加多个y轴
												gridLineWidth : 1, // 设置水平网格线粗细
												gridLineColor : '#C0C0C0', // 水平网格线颜色
												// alternateGridColor: '#C1DEEA', // 斑马线
												// lineColor: '#FF0000', // y轴坐标轴颜色
												// lineWidth: 1, // y轴坐标轴粗细
												// type: 'categories',
												// categories: ['低', '中', '高'], // 设置类别
												floor : 0, // The lowest allowed value for automatically computed axis extremes.
												ceiling : 100, // The highest allowed value for automatically computed axis extremes.
												max : 100, // The maximum value of the axis.
												min : 0, // The minimum value of the axis.
												// minRange: 5,
												minorGridLineWidth : 1,
												minorGridLineDashStyle : 'longdash',
												minorGridLineColor : '#e1e1e1',
												minorTickInterval : 'auto', // 默认为null
												// minorTickWidth: 10, // 给小网格线在坐标轴上加个端点线？
												minorTickInterval : 5, // 每隔几个单位画一条小网格线
												// opposite: true, // 在反方向显示坐标轴？
												title : {
													text : 'CPU使用率'
												},
												labels : {
													formatter : function() {
														return this.value
																+ ' %'; // 给值加百分号
													}
												},
												plotLines : [ { // 标示线、辅助参考线
													value : 0,
													width : 2,
													color : '#808080',
												// dashStyle:'longdashdot'
												// 标示线的样式，默认是solid（实线），这里定义为长虚线
												} ]
											// PlotBands 标示区域
											},
											tooltip : { //鼠标悬停时候的数据提示
												formatter : function() {
													return '<b>'
															+ this.series.name
															+ '</b><br/>'
															+ Highcharts
																	.dateFormat(
																			'%Y-%m-%d %H:%M:%S',
																			this.x)
															+ '<br/>'
															+ Highcharts
																	.numberFormat(
																			this.y,
																			2);
												}
											},
											legend : {
												enabled : false
											// 图例（数据标识）
											},
											exporting : {
												enabled : false
											// 禁用导出功能
											},
											colors : [ '#2f7ed8', '#0d233a',
													'#8bbc21', '#910000',
													'#1aadce', '#492970',
													'#f28f43', '#77a1e5',
													'#c42525', '#a6c96a' ], // 设置数据线的颜色；
											series : [ 
<c:forEach var="i" begin="1" end="${cpuCount}" step="1"> 
{
	name : 'CPU使用率${i}',
	data : (function() {
		// 生成随机数据
		var data = [], time = (new Date())
				.getTime(), i;

		for (i = -9; i <= 0; i++) {
			data
					.push({
						x : time
								+ i
								* 1000,
						y : 0
					});
		}
		return data;
	})()
},
</c:forEach>
											],
											credits : {
												enabled : false
											// 禁用版权信息
											}
										});
						
						////////////////////mem chart////////////////////////
						$('#mem_container').highcharts({
							chart: {
								type: 'spline', // 选择图表类型，类型包括：http://api.highcharts.com/highcharts#plotOptions
								animation: Highcharts.svg, // don't animate in old IE
								backgroundColor: '#F1F1F1', // 设置背景颜色
								borderColor: '#BFBFBF',
								borderRadius: '0',
								borderWidth: '1',
								className: 'cpu_chart', // 给生成的最外层元素（.highcharts-container）添加class
								marginRight: 10,
								style: {
									fontFamily: '"微软雅黑", serif'
									// fontSize: '12px'
								}
							},
							title: {
								text: '内存占用率'
							},
							xAxis: {
								type: 'datetime', // 类型有： linear, logarithmic, datetime, categories.
								tickPixelInterval: 150,
								title: {
									text: '时间'
								}
							},
							yAxis: {  // 可以用[]添加多个y轴
								floor: 0, // The lowest allowed value for automatically computed axis extremes.
								ceiling: 100, // The highest allowed value for automatically computed axis extremes.
								max: 100, // The maximum value of the axis.
								min: 0, // The minimum value of the axis.
								minorGridLineWidth: 1,
								minorGridLineDashStyle: 'longdash',
								minorGridLineColor: '#e1e1e1',
								minorTickInterval: 'auto', // 默认为null
								minorTickInterval: 5, // 每隔几个单位画一条小网格线
								title: {
									text: '内存占用率'
								},
								labels: {
									formatter: function() {
										return this.value + ' %'; // 给值加百分号
									}
								}
							},
							tooltip: { //鼠标悬停时候的数据提示
								formatter: function() {
									return '<b>'+ this.series.name +'</b><br/>'+
									Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
									Highcharts.numberFormat(this.y, 2);
								}
							},
							legend: {
								enabled: false // 图例（数据标识）
							},
							exporting: {
								enabled: false // 禁用导出功能
							},
							colors: ['#2f7ed8'], // 设置数据线的颜色；
							series: [{ // 可以用[]添加多组数据
								name: '内存占用率',
								data: (function() {
									// 生成随机数据
									var data = [],
									time = (new Date()).getTime(),
									i;

									for (i = -9; i <= 0; i++) {
										data.push({
											x: time + i * 1000,
											
											y:0
										});
									}
									return data;
								})()
							}],
							credits:{
								enabled:false // 禁用版权信息
							}
						});
						///////////////////////////////disk chart/////////////////////////////////////////////////////////
						$('#disk_container').highcharts({
				chart: {
					plotBackgroundColor: null,
					plotBorderWidth: null,
					plotShadow: false,
					backgroundColor: '#F1F1F1', // 设置背景颜色
					borderColor: '#BFBFBF',
					borderRadius: '0',
					borderWidth: '1',
					marginRight: 10,
					style: {
						fontFamily: '"微软雅黑", serif'
						// fontSize: '12px'
					}
					
				},
				title: {	
					text: '硬盘占用空间'
				},
				tooltip: {
					pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
				},
				plotOptions: {
					pie: {
						allowPointSelect: true,
						cursor: 'pointer',
						dataLabels: {
							enabled: true,
							format: '<b>{point.name}</b>: {point.percentage:.1f} %',
							style: {
								color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
							}
						},
						colors: ['#1BA1E2', '#ccc'],
						startAngle: 30, //起始角度
						innerSize: '50%',
						states: {
							hover: {
								brightness: 0.1, // 鼠标hvoer时候变亮
								halo: {
									size: 0,
									// attributes: {
									// 	fill: '#B4E0F5' //设置鼠标hover时候外圈的颜色
									// },
									opacity: 0.5
								}
							}
						}
					}
				},
				series: [{
					type: 'pie',
					name: '百分比',
					data: [
						{
							name: '已占用',                                                                    //这里设置后台传来的值
							y: ${diskPerc},
							sliced: false // 新载入的时候是否从主体分离
						},
						{
							name: '剩余',
							y: 100-${diskPerc},
							sliced: false // 新载入的时候是否从主体分离
						}
					]
				}],
				credits:{
					enabled:false // 禁用版权信息
				}
			});
						////////////////////////////////////////////////net chart///////////////////////////////////////////////////
						$('#net_container')
								.highcharts(
										{

											chart : {
												type : 'spline', // 选择图表类型，类型包括：http://api.highcharts.com/highcharts#plotOptions
												animation : Highcharts.svg, // don't animate in old IE
												backgroundColor : '#F1F1F1', // 设置背景颜色
												borderColor : '#BFBFBF',
												borderRadius : '0',
												borderWidth : '1',
												className : 'cpu_chart', // 给生成的最外层元素（.highcharts-container）添加class
												marginRight : 10,
												// height: 800, // 实际背景部分所占高度为height-10；
												// width: 300, // 默认是占满
												reflow : true, // 改变窗口大小的时候是否改编图表大小
												// 网格线区域的设置
												// plotBackgroundColor: 'blue',
												// plotBackgroundImage: 'xxx.jpg',
												// plotBorderColor: '#000',
												// plotBorderWidth: 0, // 网格线区域的边框，默认为0
												// showAxes: true,
												style : {
													fontFamily : '"微软雅黑", serif'
												// fontSize: '12px'
												}
											},
											title : {
												text : '网络速率'
											},
											// subtitle: {
											// 	text: '副标题'
											// },
											xAxis : {
												type : 'datetime', // 类型有： linear, logarithmic, datetime, categories.
												tickPixelInterval : 150,
												title : {
													text : '时间'
												}
											// tickInterval: 1 // 无效？
											},
											yAxis : { // 可以用[]添加多个y轴
												gridLineWidth : 1, // 设置水平网格线粗细
												gridLineColor : '#C0C0C0', // 水平网格线颜色
												// alternateGridColor: '#C1DEEA', // 斑马线
												// lineColor: '#FF0000', // y轴坐标轴颜色
												// lineWidth: 1, // y轴坐标轴粗细
												// type: 'categories',
												// categories: ['低', '中', '高'], // 设置类别
												floor : 0, // The lowest allowed value for automatically computed axis extremes.
												ceiling : 1000, // The highest allowed value for automatically computed axis extremes.
												max : 100000000, // The maximum value of the axis.
												min : 0, // The minimum value of the axis.
												// minRange: 5,
												minorGridLineWidth : 1,
												minorGridLineDashStyle : 'longdash',
												minorGridLineColor : '#e1e1e1',
												minorTickInterval : 'auto', // 默认为null
												// minorTickWidth: 10, // 给小网格线在坐标轴上加个端点线？
												minorTickInterval : 5, // 每隔几个单位画一条小网格线
												// opposite: true, // 在反方向显示坐标轴？
												title : {
													text : '网络速率'
												},
												labels : {
													formatter : function() {
														return this.value
																+ 'M'; // 给值加百分号
													}
												},
												plotLines : [ { // 标示线、辅助参考线
													value : 0,
													width : 2,
													color : '#808080',
												// dashStyle:'longdashdot'
												// 标示线的样式，默认是solid（实线），这里定义为长虚线
												} ]
											// PlotBands 标示区域
											},
											tooltip : { //鼠标悬停时候的数据提示
												formatter : function() {
													return '<b>'
															+ this.series.name
															+ '</b><br/>'
															+ Highcharts
																	.dateFormat(
																			'%Y-%m-%d %H:%M:%S',
																			this.x)
															+ '<br/>'
															+ Highcharts
																	.numberFormat(
																			this.y,
																			2);
												}
											},
											legend : {
												enabled : false
											// 图例（数据标识）
											},
											exporting : {
												enabled : false
											// 禁用导出功能
											},
											colors : [ '#2f7ed8', '#0d233a',
													'#8bbc21', '#910000',
													'#1aadce', '#492970',
													'#f28f43', '#77a1e5',
													'#c42525', '#a6c96a' ], // 设置数据线的颜色；
											series : [ 
<c:forEach var="i" begin="1" end="${netCount}" step="1"> 
{
	name : '网络${i}',
	data : (function() {
		// 生成随机数据
		var data = [], time = (new Date())
				.getTime(), i;

		for (i = -9; i <= 0; i++) {
			data
					.push({
						x : time
								+ i
								* 1000,
						y : 0
					});
		}
		return data;
	})()
},
</c:forEach>
											],
											credits : {
												enabled : false
											// 禁用版权信息
											}
										});
						///////////////////////////////get data from server///////////////////////////////////////////////////////
						var cpuChart = $('#cpu_container').highcharts();
						var memChart = $('#mem_container').highcharts();
						var diskChart = $('#disk_container').highcharts();
						var netChart = $('#net_container').highcharts();
						setInterval(function() {
							$.ajax({
								async : false,
								type : "POST",
								url : "appwebmin/systeminfo/admin/usedPerc",
								dataType : "json",
								success : function(data) {
									var time = (new Date()).getTime();
									//update cpu
									var cpuPercs = data.cpuPercs;
									for(var i=0;i<cpuPercs.length;i++){
										cpuChart.series[i].addPoint([time, cpuPercs[i]], true, true);
									}
									//update mem
									memChart.series[0].addPoint([time, data.memPerc], true, true);
									//update net
									var netSpeeds = data.netSpeeds;
									for(var i=0;i<netSpeeds.length;i++){
										netChart.series[i].addPoint([time, netSpeeds[i]], true, true);
									}
									
								}
							});
						
						}, 2000);
	});
</script>

</head>
<body class="inFrame">

	<div class="container-fliud welcomeWrap">
		<div class="row">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li>当前位置：</li>
					<li class="active">系统状态</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div id="cpu_container"></div>
				<div id="mem_container"></div>
				<div id="disk_container"></div>
				<div id="net_container"></div>
			</div>
		</div>
	</div>


</body>
</html>