
function spawn(page)
{
	remote = window.open("http://www.juniatacountyfair.com/" + page, "Whatnot",'width=500,height=350,directories=0,location=0,menubar=0,resizable=1,scrollbars=1,status=0,titlebar=0,toolbar=0');
	if (remote.opener == null) remote.opener = window;
	remote.opener.name = "HOME";

}

function getHttpRequest() {

    var httpRequest;
    
    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
        httpRequest = new XMLHttpRequest();
        if (httpRequest.overrideMimeType) {
            httpRequest.overrideMimeType('text/xml');
            // See note below about this line
        }
    } else if (window.ActiveXObject) { // IE
        try {
            httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {}
        }
    }

    if (!httpRequest) {
        alert('Cannot create an XMLHTTP instance');
        return null;
    }

    return httpRequest;

}

/**
 * Generic HTTP request function
 */
function makeRequest(url) {

    var httpRequest = getHttpRequest();

    if (httpRequest) {
        httpRequest.onreadystatechange = function() { processResponse(httpRequest); };
        //httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        httpRequest.open('GET', url, true);
        //httpRequest.send("key=value&a=b&whatnot=blah");
        httpRequest.send(null);
    }

}

/**
 * Generic HTTP response processing function
 */
function processResponse(httpRequest) {

    if (httpRequest.readyState == 4) {
        if (httpRequest.status == 200) {
            //alert(httpRequest.responseText);
        } else {
            alert("There was a problem with the reqeust!");
        }
    }

}

function saveNewsItem(url) {

    var httpRequest = getHttpRequest();

    if (httpRequest) {
        httpRequest.onreadystatechange = function() {
            if (httpRequest.readyState == 4) {
                if (httpRequest.status == 200) {
                    reloadNews();
                } else {
                    alert("There was a problem with the reqeust!");
                }
            }
        };
        httpRequest.open('GET', url, true);
        httpRequest.send(null);
    }

}

function saveInfoItem(url, id, headline, text) {

    var httpRequest = getHttpRequest();

    if (httpRequest) {
        httpRequest.onreadystatechange = function() {
            if (httpRequest.readyState == 4) {
                if (httpRequest.status == 200) {
                    reloadInfo();
                } else {
                    alert("There was a problem with the reqeust!");
                }
            }
        };

        httpRequest.open('POST', url, true);
        httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        httpRequest.send('command=save&id=' + id + '&headline=' + escape(headline) + '&text=' + escape(text));

    }

}


function saveScheduleItem(day, url) {

    var httpRequest = getHttpRequest();

    if (httpRequest) {
        httpRequest.onreadystatechange = function() {
            if (httpRequest.readyState == 4) {
                if (httpRequest.status == 200) {
                    reloadDay(day);
                } else {
                    alert("There was a problem with the reqeust!");
                }
            }
        };
        httpRequest.open('GET', url, true);
        httpRequest.send(null);
    }

}


function reloadDay(day) {

    var httpRequest = getHttpRequest();
    if (httpRequest) {
    
        var url = "http://www.juniatacountyfair.com/loadItem.do?day=" + day;

        httpRequest.onreadystatechange = function() {

            if (httpRequest.readyState == 4) {
                if (httpRequest.status == 200) {

                    var ulNode = document.getElementById("list" + day);
                    
                    var newHTML = "";

                    var xmldoc = httpRequest.responseXML;
                    var rootNode = xmldoc.getElementsByTagName("ItemsList")[0];

                    for (var i = 0; i < rootNode.childNodes.length; i++) {
                    
                        var siNode = rootNode.childNodes[i];

                        var iid = siNode.getAttribute("id");
                        var iday = siNode.getAttribute("day");
                        
                        var dispTextNode = siNode.getElementsByTagName("DisplayText")[0].firstChild;
                        if (dispTextNode) {
                            newHTML += '<li id="si-' + iid + '">' + dispTextNode.nodeValue;
                            newHTML += '<span class="adminEditLinks">&nbsp;[&nbsp;<a href="javascript:doEdit(' + iday + ', ' + iid + ', ' + "'" + 'si-' + iid + "'" + ');">EDIT</a>&nbsp;]&nbsp;[&nbsp;<a href="javascript:doDelete(' + iday + ', ' + iid + ');">DELETE</a>&nbsp;]</span>'
                            newHTML += '</li>';
                        }
                    
                    }
                  
                    ulNode.innerHTML = newHTML;

                } else {
                    alert("There was a problem with the reqeust!");
                }

            }
            
        };
        
        httpRequest.open('GET', url, true);
        httpRequest.send(null);

    }

}

function reloadNews() {

    var httpRequest = getHttpRequest();
    if (httpRequest) {
    
        var url = "http://www.juniatacountyfair.com/manageNews.do?command=loadall";

        httpRequest.onreadystatechange = function() {

            if (httpRequest.readyState == 4) {
                if (httpRequest.status == 200) {

                    var newsNode = document.getElementById("newsArea");
                    
                    var newHTML = "";

                    var xmldoc = httpRequest.responseXML;
                    var rootNode = xmldoc.getElementsByTagName("ItemsList")[0];

                    for (var i = 0; i < rootNode.childNodes.length; i++) {
                    
                        var niNode = rootNode.childNodes[i];

                        var iid = niNode.getAttribute("id");
                        
                        var dispTextNode = niNode.getElementsByTagName("DisplayText")[0].firstChild;
                        if (dispTextNode) {
                            newHTML += '<p class="news" id="ni-' + iid + '">' + dispTextNode.nodeValue;
                            newHTML += '<span class="adminEditLinks">&nbsp;[&nbsp;<a href="javascript:doEdit(' + iid + ', ' + "'" + 'ni-' + iid + "'" + ');">EDIT</a>&nbsp;]&nbsp;[&nbsp;<a href="javascript:doDelete(' + iid + ');">DELETE</a>&nbsp;]</span>'
                            newHTML += '</p>';
                        }
                    
                    }
                  
                    newsNode.innerHTML = newHTML;

                } else {
                    alert("There was a problem with the reqeust!");
                }

            }
            
        };
        
        httpRequest.open('GET', url, true);
        httpRequest.send(null);

    }

}

function reloadInfo() {

    var httpRequest = getHttpRequest();
    if (httpRequest) {
    
        var url = "http://www.juniatacountyfair.com/manageInfo.do?command=loadall";

        httpRequest.onreadystatechange = function() {

            if (httpRequest.readyState == 4) {
                if (httpRequest.status == 200) {

                    var newsNode = document.getElementById("infoArea");
                    
                    var newHTML = "";

                    var xmldoc = httpRequest.responseXML;
                    var rootNode = xmldoc.getElementsByTagName("ItemsList")[0];

                    for (var i = 0; i < rootNode.childNodes.length; i++) {
                    
                        var niNode = rootNode.childNodes[i];

                        var iid = niNode.getAttribute("id");
                       
                        var headlineTextNode = niNode.getElementsByTagName("Headline")[0].firstChild;
                        var shortTextNode = niNode.getElementsByTagName("ShortText")[0].firstChild;
                        var completeTextNode = niNode.getElementsByTagName("Text")[0].firstChild;
                        
                        if (headlineTextNode && shortTextNode && completeTextNode) {
                            newHTML += '<div id="ii-' + iid + '">';
                            
                            newHTML += '<p><span class="important">' + headlineTextNode.nodeValue + '</span>';
                            newHTML += '<span class="adminEditLinks">&nbsp;[&nbsp;<a href="javascript:doEdit2(' + iid + ', ' + "'" + 'ii-' + iid + "'" + ');">EDIT</a>&nbsp;]&nbsp;[&nbsp;<a href="javascript:doDelete2(' + iid + ');">DELETE</a>&nbsp;]</span>';
                            newHTML += '</p>';
                            
                            newHTML += '<div class="completeInfo" style="display: block;" id="lis-' + iid + '">';
                            newHTML += '<p>' + shortTextNode.nodeValue + '</p>';
                            newHTML += '<p><a href="javascript:toggle(\'' + iid + '\');">Read Complete Text</a></p>';
                            newHTML += '</div>';

                            newHTML += '<div class="completeInfo" style="display: none;" id="lic-' + iid + '">';
                            newHTML += '<p>' + completeTextNode.nodeValue + '</p>';
                            newHTML += '<p><a href="javascript:toggle(\'' + iid + '\');">Hide Complete Text</a></p>';
                            newHTML += '</div>';
                            
                            newHTML += '</div>';
                            
                        }
                    
                    }
                  
                    newsNode.innerHTML = newHTML;

                } else {
                    alert("There was a problem with the reqeust!");
                }

            }
            
        };
        
        httpRequest.open('GET', url, true);
        httpRequest.send(null);

    }

}

function reloadFiles() {

    var httpRequest = getHttpRequest();
    if (httpRequest) {
    
        var url = "http://www.juniatacountyfair.com/manageDocuments.do?command=loadall";

        httpRequest.onreadystatechange = function() {

            if (httpRequest.readyState == 4) {
                if (httpRequest.status == 200) {

                    var filesNode = document.getElementById("downloadsArea");
                    
                    var newHTML = "";

                    var xmldoc = httpRequest.responseXML;
                    var rootNode = xmldoc.getElementsByTagName("FilesList")[0];

                    for (var i = 0; i < rootNode.childNodes.length; i++) {
                    
                        var diNode = rootNode.childNodes[i];

                        var did = diNode.getAttribute("id");
                       
                        var descriptionTextNode = diNode.getElementsByTagName("Description")[0].firstChild;
                        
                        if (descriptionTextNode) {
                        
                            newHTML += '<p class="downloads" id="di-' + did + '">';
                            newHTML += '<a href="downloadFromDatabase.do?id=' + did + '">' + descriptionTextNode.nodeValue + '</a>';
                            newHTML += '<span class="adminEditLinks">&nbsp;[&nbsp;<a href="javascript:doDeleteFile(' + did +');">DELETE</a>&nbsp;]</span>';
                            newHTML += '</p>';
                            
                        }
                    
                    }
                  
                    filesNode.innerHTML = newHTML;

                } else {
                    alert("There was a problem with the request!");
                }

            }
            
        };
        
        httpRequest.open('GET', url, true);
        httpRequest.send(null);

    }

}


function getScheduleItemById(id) {

    var httpRequest = getHttpRequest();
    if (httpRequest) {
    
        var url = "http://www.juniatacountyfair.com/loadItem.do?id=" + id;
        
        httpRequest.onreadystatechange = function() {

            if (httpRequest.readyState == 4) {
                if (httpRequest.status == 200) {

                    var iDesc = document.getElementById("modDescription");
                    var iStart = document.getElementById("modStartTime");
                    var iStop = document.getElementById("modStopTime");
                    var iAllDay = document.getElementById("modAllDay");
            
                    var xmldoc = httpRequest.responseXML;
                    var rootNode = xmldoc.getElementsByTagName("ItemsList")[0];
                    var siNode = rootNode.firstChild;
            
                    var eDescValue = "";
                    var eStartValue = "";
                    var eStopValue = "";
                    var eAllDayValue = "";

                    var eDescNode = siNode.getElementsByTagName("Description")[0].firstChild;
                    var eStartNode = siNode.getElementsByTagName("StartTime")[0].firstChild;
                    var eStopNode = siNode.getElementsByTagName("StopTime")[0].firstChild;
                    var eAllDayNode = siNode.getElementsByTagName("AllDay")[0].firstChild;
                    
                    if (eDescNode) {
                        eDescValue = eDescNode.nodeValue;
                    }

                    if (eStartNode) {
                        eStartValue = eStartNode.nodeValue;
                    }
                    
                    if (eStopNode) {
                        eStopValue = eStopNode.nodeValue;
                    }
                    
                    if (eAllDayNode) {
                        eAllDayValue = eAllDayNode.nodeValue;
                    }
                    
                    iDesc.value = eDescValue;
                    iStart.value = eStartValue;
                    iStop.value = eStopValue;
                    
                    if (eAllDayValue == "true") {
                        iAllDay.checked = true;
                    }
                    
                } else {
                    alert("There was a problem with the reqeust!");
                }

            }
            
        };
        
        httpRequest.open('GET', url, true);
        httpRequest.send(null);
        
    }

}


function getNewsItemById(id) {

    var httpRequest = getHttpRequest();
    if (httpRequest) {
    
        var url = "http://www.juniatacountyfair.com/manageNews.do?command=load&id=" + id;
        
        httpRequest.onreadystatechange = function() {

            if (httpRequest.readyState == 4) {
                if (httpRequest.status == 200) {

                    var iText = document.getElementById("modText");
                    var iDate = document.getElementById("modDate");
            
                    var xmldoc = httpRequest.responseXML;
                    var rootNode = xmldoc.getElementsByTagName("ItemsList")[0];
                    var niNode = rootNode.firstChild;
            
                    var eTextValue = "";
                    var eDateValue = "";

                    var eTextNode = niNode.getElementsByTagName("Text")[0].firstChild;
                    var eDateNode = niNode.getElementsByTagName("PostDate")[0].firstChild;
                    
                    if (eTextNode) {
                        eTextValue = eTextNode.nodeValue;
                    }

                    if (eDateNode) {
                        eDateValue = eDateNode.nodeValue;
                    }
                    
                    iText.value = eTextValue;
                    iDate.value = eDateValue;
                    
                } else {
                    alert("There was a problem with the reqeust!");
                }

            }
            
        };
        
        httpRequest.open('GET', url, true);
        httpRequest.send(null);
        
    }

}

function getInfoItemById(id) {

    var httpRequest = getHttpRequest();
    if (httpRequest) {
    
        var url = "http://www.juniatacountyfair.com/manageInfo.do?command=load&id=" + id;
        
        httpRequest.onreadystatechange = function() {

            if (httpRequest.readyState == 4) {
                if (httpRequest.status == 200) {

                    var iHeadline = document.getElementById("modHeadline");
                    var iText = document.getElementById("modText2");
            
                    var xmldoc = httpRequest.responseXML;
                    var rootNode = xmldoc.getElementsByTagName("ItemsList")[0];
                    var niNode = rootNode.firstChild;
            
                    var eHeadlineValue = "";
                    var eTextValue = "";

                    var eHeadlineNode = niNode.getElementsByTagName("Headline")[0].firstChild;
                    var eTextNode = niNode.getElementsByTagName("Text")[0].firstChild;
                    
                    if (eHeadlineNode) {
                        eHeadlineValue = eHeadlineNode.nodeValue;
                    }

                    if (eTextNode) {
                        eTextValue = eTextNode.nodeValue;
                    }
                    
                    iHeadline.value = eHeadlineValue;
                    iText.value = eTextValue;
                    
                } else {
                    alert("There was a problem with the reqeust!");
                }

            }
            
        };
        
        httpRequest.open('GET', url, true);
        httpRequest.send(null);
        
    }

}


function toggleDisplay(elId) {

    var element = document.getElementById(elId);

    if (element) {
    
        if (element.style.display == "block") {
            element.style.display = "none";
        } else {
            element.style.display = "block";
        }
    
    }

}

function serializeForm(formElement) {

    var ret = "";
    
    var elements = formElement.elements;
    
    for (var i = 0; i < elements.length; i++) {
    
        var formElement = elements[i];
        
        if (formElement.name) {
        
            var elementName = formElement.nodeName;
            var elementType = formElement.getAttribute("type");
            
            if (elementName) elementName = elementName.toLowerCase();
            if (elementType) elementType = elementType.toLowerCase();

            if (elementName == "select") {
            
                if (i > 0) ret += "&";
                ret += formElement.name + "=";
                ret += escape(formElement.options[formElement.selectedIndex].value);

            } else if (elementName == "textarea") {
            
                if (i > 0) ret += "&";
                ret += formElement.name + "=";
                ret += escape(formElement.value);
            
            } else if (elementType == "radio" || elementType == "checkbox") {
            
                if (formElement.checked) {
                    if (i > 0) ret += "&";
                    ret += formElement.name + "=";
                    ret += escape(formElement.value);
                }
            
            } else if (elementType == "hidden" || elementType == "text" || elementType == "password") {
            
                if (i > 0) ret += "&";
                ret += formElement.name + "=";
                ret += escape(formElement.value);
                
            }
        
        }

    }
    
    return ret;

}

function DL_GetElementLeft(eElement)
{
    var nLeftPos = eElement.offsetLeft;          // initialize var to store calculations
    var eParElement = eElement.offsetParent;     // identify first offset parent element  
    while (eParElement != null)
    {                                            // move up through element hierarchy
        nLeftPos += eParElement.offsetLeft;      // appending left offset of each parent
        eParElement = eParElement.offsetParent;  // until no more offset parents exist
    }
    return nLeftPos;                             // return the number calculated
}

function DL_GetElementTop(eElement)
{
    var nTopPos = eElement.offsetTop;            // initialize var to store calculations
    var eParElement = eElement.offsetParent;     // identify first offset parent element  
    while (eParElement != null)
    {                                            // move up through element hierarchy
        nTopPos += eParElement.offsetTop;        // appending top offset of each parent
        eParElement = eParElement.offsetParent;  // until no more offset parents exist
    }
    return nTopPos;                              // return the number calculated
}


