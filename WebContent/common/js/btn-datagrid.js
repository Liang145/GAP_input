/**
 * 
 * @param name
 * @param btnColor
 * @param url
 * @param paramKey
 * @param paramValue
 * @returns {String}
 */
function makeEditButton(name, color, url, paramValue) {
//	var rtn = "<a href=\"#\" onclick=\"javascript:";
//	rtn += "window.location.href='";
//	rtn += url;
//	rtn += "?";
//	rtn += paramKey;
//	rtn += "=";
//	rtn += paramValue;
//	rtn += "';return false;\" ";
//	rtn += "class=\"btn btn-";
//	if (color == null) {
//		color = "info";
//	}
//	rtn += color;
//	rtn += "\" role=\"button\">";
//	rtn += name;
//	rtn += "</a>";
	
	var rtn = "<a href=\"";
	rtn += url;
	rtn += "/";
	rtn += paramValue;
	rtn += "\"";
	rtn += "class=\"btn btn-";
	if (color == null) {
		color = "info";
	}
	rtn += color;
	rtn += "\" role=\"button\">";
	rtn += name;
	rtn += "</a>";
	return rtn;
}

function makeAjaxButton(name, color, url, paramKey, paramValue, tipMsg) {
	var rtn = "<a href=\"#\" onclick=\"javascript:";
	rtn += "excuteAjax('";
	rtn += url;
	rtn += "','";
	rtn += paramKey;
	rtn += "','";
	rtn += paramValue;
	rtn += "','";
	rtn += tipMsg;
	rtn += "');return false;\" ";
	rtn += "class=\"btn btn-";
	if (color == null) {
		color = "info";
	}
	rtn += color;
	rtn += "\" role=\"button\">";
	rtn += name;
	rtn += "</a>";
	return rtn;
}

function excuteAjax(url, paramValue, tipMsg) {

	$.ajax({
		type : "POST",
		url : url+"/"+paramValue,
		success : function(data) {
			exampleTable.fnDraw();// 重载dataTable
			if (tipMsg != null && tipMsg != "null") {
				alert(tipMsg);
			}
		},
		error : function() {
			alert("系统异常，请稍后重试！");
		}
	});

}

function makeDeleteButton(url, paramValue, tipMsg) {
	var rtn = "<a href=\"#\" onclick=\"javascript:";
	rtn += ("excuteAjax('" + url + "','" + paramValue
			+ "','" + tipMsg + "');return false;\" ");
	rtn += "class=\"btn btn-danger\" role=\"button\">";
	rtn += "删除";
	rtn += "</a>";
	return rtn;
}

function makeButton(name, btnColor, url, param, value, where) {
	var rtn = "<a href=\"#\" onclick=\"javascript:";
	if (where == null) {
		where = "window";
	}
	rtn += where;
	rtn += ".location.href='";
	rtn += url;
	rtn += "?";
	rtn += param;
	rtn += "=";
	rtn += value;
	rtn += "';return false;\" ";
	rtn += "class=\"btn btn-";
	rtn += btnColor;
	rtn += "\" role=\"button\">";
	rtn += name;
	rtn += "</a>";
	return rtn;
}
