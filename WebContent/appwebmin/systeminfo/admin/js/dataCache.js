

$(document).ready(function() {
	
	Highcharts.setOptions({
		global: {
			useUTC: false
		}
	});

	var chart;
	
	var cpuPerc ;
	var memPerc ;
	/////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////////
	$('#cpu_container').highcharts({
		
		chart: {
			type: 'spline', // 选择图表类型，类型包括：http://api.highcharts.com/highcharts#plotOptions
			animation: Highcharts.svg, // don't animate in old IE
			backgroundColor: '#F1F1F1', // 设置背景颜色
			borderColor: '#BFBFBF',
			borderRadius: '0',
			borderWidth: '1',
			className: 'cpu_chart', // 给生成的最外层元素（.highcharts-container）添加class
			marginRight: 10,
			// height: 800, // 实际背景部分所占高度为height-10；
			// width: 300, // 默认是占满
			reflow: true, // 改变窗口大小的时候是否改编图表大小
			// 网格线区域的设置
			// plotBackgroundColor: 'blue',
			// plotBackgroundImage: 'xxx.jpg',
			// plotBorderColor: '#000',
			// plotBorderWidth: 0, // 网格线区域的边框，默认为0
			// showAxes: true,
			style: {
				fontFamily: '"微软雅黑", serif'
				// fontSize: '12px'
			},
			events: { // 事件包括addSeries、click、drilldown、load、redraw、selection
				load: function() {                                        

					// set up the updating of the chart each second
					var series = this.series[0];
					setInterval(function() {                                                                                     //这里是刷新取数据的地方
						////////////////////////////////////////////////
						$.ajax({
							url : "appgm/systemadmin/systemConfig.do" ,
							type : "POST" ,
							dataType : "json" ,
							success : function(data) {
								var test = JSON.parse(data) ;
								
								cpuPerc = test.cpuPerc ;
								
								memPerc = test.memPerc ;
								//hardpanPerc = test.hardpanPerc ;
							},
							error : function() {
								//alert("系统异常，请稍后重试！");
							}
						});
						//alert(cpuPerc) ;
						///////////////////////////////////////////////
						var x = (new Date()).getTime(), // current time
						//y = Math.random()*100;
						y = cpuPerc;
						series.addPoint([x, y], true, true);
					}, 2000);
				}
			}
		},
		title: {
			text: 'CPU使用率'
		},
		// subtitle: {
		// 	text: '副标题'
		// },
		xAxis: {
			type: 'datetime', // 类型有： linear, logarithmic, datetime, categories.
			tickPixelInterval: 150,
			title: {
				text: '时间'
			}
			// tickInterval: 1 // 无效？
		},
		yAxis: {  // 可以用[]添加多个y轴
			gridLineWidth: 1, // 设置水平网格线粗细
			gridLineColor: '#C0C0C0', // 水平网格线颜色
			// alternateGridColor: '#C1DEEA', // 斑马线
			// lineColor: '#FF0000', // y轴坐标轴颜色
			// lineWidth: 1, // y轴坐标轴粗细
			// type: 'categories',
			// categories: ['低', '中', '高'], // 设置类别
			floor: 0, // The lowest allowed value for automatically computed axis extremes.
			ceiling: 100, // The highest allowed value for automatically computed axis extremes.
			max: 100, // The maximum value of the axis.
			min: 0, // The minimum value of the axis.
			// minRange: 5,
			minorGridLineWidth: 1,
			minorGridLineDashStyle: 'longdash',
			minorGridLineColor: '#e1e1e1',
			minorTickInterval: 'auto', // 默认为null
			// minorTickWidth: 10, // 给小网格线在坐标轴上加个端点线？
			minorTickInterval: 5, // 每隔几个单位画一条小网格线
			// opposite: true, // 在反方向显示坐标轴？
			title: {
				text: 'CPU使用率'
			},
			labels: {
				formatter: function() {
					return this.value + ' %'; // 给值加百分号
				}
			},
			plotLines: [{ // 标示线、辅助参考线
				value: 0,
				width: 2,
				color: '#808080',
				// dashStyle:'longdashdot'
				// 标示线的样式，默认是solid（实线），这里定义为长虚线
			}]
			// PlotBands 标示区域
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
		colors: ['#2f7ed8', '#0d233a', '#8bbc21', '#910000', '#1aadce', '#492970', '#f28f43', '#77a1e5', '#c42525', '#a6c96a'], // 设置数据线的颜色；
		series: [{ // 可以用[]添加多组数据
			name: 'CPU使用率',
			data: (function() {
				// 生成随机数据
				var data = [],
				time = (new Date()).getTime(),
				i;

				for (i = -10; i <= 0; i++) {
					data.push({
						x: time + i * 1000,
						//y: Math.random()*100                                                    //这里设置后台传来的值
						y: Math.random()
					});
				}
				return data;
			})()
		}],
		credits:{
			enabled:false // 禁用版权信息
		}
	});

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
			},
			events: { // 事件包括addSeries、click、drilldown、load、redraw、selection
				load: function() {
					// set up the updating of the chart each second
					//////////////////////////////////////////////////////////////////////////
//					$.ajax({
//						url : "appgm/systemadmin/systemConfig!getSystemInfo" ,
//						type : "POST" ,
//						dataType : "json" ,
//						success : function(data) {
//							var test = JSON.parse(data) ;
//							memPerc = test.memPerc ;
//						},
//						error : function() {
//							//alert("系统异常，请稍后重试！");
//						}
//					});
					//////////////////////////////////////////////////////////////////////////////
					
					var series = this.series[0];
					setInterval(function() {
						var x = (new Date()).getTime(), // current time
						//y = Math.random()*100;
						y = memPerc;
						series.addPoint([x, y], true, true);
					}, 1000);
				}
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

				for (i = -10; i <= 0; i++) {
					data.push({
						x: time + i * 1000,
						//y: Math.random()*100                                                    //这里设置后台传来的值
						y:memPerc
					});
				}
				return data;
			})()
		}],
		credits:{
			enabled:false // 禁用版权信息
		}
	});
	
	//////////////////////////////////////////////////
	$.ajax({
		url : "appgm/systemadmin/systemConfig.do" ,
		type : "POST" ,
		dataType : "json" ,
		success : function(data) {
			var test = JSON.parse(data) ;
			var hardpanPerc = test.hardpanPerc ;
			hardpanPerc = parseFloat(hardpanPerc);
			//alert(hardpanPerc) ;
			
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
							name: '已占用',
							// color: 'red',
//							y: 68,                                                                       //这里设置后台传来的值
							y: hardpanPerc,
							sliced: false // 新载入的时候是否从主体分离
						},
						{
							name: '剩余',
							// color: 'red',
//							y: 32,
							y: 100-hardpanPerc,
							sliced: false // 新载入的时候是否从主体分离
						}
					]
				}],
				credits:{
					enabled:false // 禁用版权信息
				}
			});
			
		},
		error : function() {
			//alert("系统异常，请稍后重试！");
		}
	});
	/////////////////////////////////////////////////
	

});

	

