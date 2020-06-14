//实用工具类

function cloneObject(original, target) {
    target = (!target ? {} : target);
    for (var key in original) {
        target[key] = original[key];
    }
    return target;
}

function isBasicDataType(value) {
    return (typeof (value) === "string" || typeof (value) === "number" || typeof (value) === "boolean");
}

function formatJson(jsonObject, padding, end) {
    var formatted = '';

    if (isBasicDataType(jsonObject)) {
        return jsonObject;
    } else if (jsonObject && typeof (jsonObject['length']) !== "undefined") {
        formatted += "[";
        for (var i = 0; i < jsonObject.length; i++) {
            var thisEnd = (i != jsonObject.length - 1) ? ",\r\n" : "\r\n";
            var thisBegin = (i == 0) ? "\r\n" + padding : padding;
            formatted += thisBegin + formatJson(jsonObject[i], "    " + padding, thisEnd) + '';
        }
        formatted += (isBasicDataType(jsonObject[0] || null) ? "" : padding.substr(4)) + "]" + end;
    } else {
        formatted = '{\r\n';
        var lastKey = "";
        for (var key in jsonObject) {
            lastKey = key;
        }
        for (var key in jsonObject) {
            var value = jsonObject[key];
            if (isBasicDataType(value)) {
                formatted += padding + '"' + key + '": ' + escapeJsonString(jsonObject[key]) + (key != lastKey ? "," : "") + '\r\n';
            } else {
                formatted += padding + '"' + key + '": ' + formatJson(value, "    " + padding, (key != lastKey ? "," : "") + '\r\n');
            }
        }
        formatted += padding.substr(4) + "}" + end;
    }
    return formatted;
}

function escapeJsonString(jsonString) {
    return typeof (jsonString) === "string" ? '"' + jsonString.replace(/\\/g, "\\\\").replace(/"/g, "\\\"") + '"' : jsonString;
}

function pipelineRequest(urls, index, callback) {

    if (index >= urls.length) {
        return;
    }

    request(urls[index], function (result) {
        callback(result, index);
        pipelineRequest(urls, index + 1, callback);
    }, method, data, contentType);
}

function getWindowWidth() {
    return window.innerWidth;
}

function setDomValue(domId, value) {
    document.getElementById(domId).value = value;
}

function setAllDomValue(domValueMap) {
    for (var domId in domValueMap) {
        setDomValue(domId, domValueMap[domId]);
    }
}

function hideDomByClassName(className, isShow) {
    if (!className) {
        return;
    }
    var doms = document.getElementsByClassName(className);
    for (var i = 0; i < doms.length; i++) {
        doms[i].style.display = isShow || (doms[i].style.display ? "" : "none");
    }
}

function hideDomByIds(domIds) {
    for (var i = 0; i < domIds.length; i++) {
        document.getElementById(domIds[i]).style.display = "none";
    }
}

var xmlhttp;

function postForm(url, data, callback) {
    request(url, callback, 'POST', data);
}

function request(url, callback, method, data, contentType) {
    method = (method ? method : "GET");
    var xmlhttp = null;
    if (window.XMLHttpRequest) {// code for all new browsers
        xmlhttp = new XMLHttpRequest();
    } else if (window.ActiveXObject) {// code for IE5 and IE6
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    if (xmlhttp != null) {
        xmlhttp.onreadystatechange = function state_Change() {
            if (xmlhttp.readyState == 4) {// 4 = "loaded"
                if (xmlhttp.status == 200) {// 200 = OK
                    callback(xmlhttp.responseText)
                } else {
                    alert("Problem retrieving XML data");
                }
            }
        };
        xmlhttp.open(method, url, true);
        var param = null;
        xmlhttp.setRequestHeader("Content-Type", contentType || "application/x-www-form-urlencoded");
        param = data || "";

        xmlhttp.send(param);
    } else {
        alert("Your browser does not support XMLHTTP.");
    }
}

/**
 * @function escapeHTML 转义html脚本 < > & " '
 * @param a - 字符串
 */
function escapeHTML(a, fieldName) {
    var result = "";
    if (fieldName && fieldName.indexOf('ShowHtml') > -1) {
        result = a.replace(/(^"|"$)/g, "")
    } else {
        result = a.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/(^"|"$)/g, "");
    }
    return result;
}

/**
 * @function unescapeHTML 还原html脚本 < > & " '
 * @param a - 字符串
 */
function unescapeHTML(a) {
    a = "" + a;
    return a.replace(/</g, "<").replace(/>/g, ">").replace(/&/g, "&").replace(/"/g, '"').replace(/'/g, "'");
}

function setDomsHeight(className, height) {
    var doms = document.getElementsByClassName(className);
    for (var i = 0; i < doms.length; i++) {
        doms[i].style.height = height + "px";
    }
}

function toggleDom(domId, display) {
    document.getElementById(domId).style.display = display || (document.getElementById(domId).style.display === "none" ? "block" : "none");
}
