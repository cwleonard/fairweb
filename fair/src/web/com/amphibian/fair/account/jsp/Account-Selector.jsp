<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fair-tags.tld" prefix="fair" %>

<html:xhtml/>

<html>
<head>
  <title>User Selector</title>
  <link rel="stylesheet" type="text/css" media="screen" href="css/normal.css" />
  <script type="text/javascript" src="scripts/standard-functions.js"></script>
  <script language="javascript">
  
  var rowSelected;
  
  function selectRow(el) {
  
    if (rowSelected) {
    
      rowSelected.style.backgroundColor = null;
      rowSelected.style.color = null;

    }
      
    el.style.backgroundColor = "#0000FF";
    el.style.color = "#FFFFFF";
    rowSelected = el;
  
  }


function reloadUsers() {

    var httpRequest = getHttpRequest();
    if (httpRequest) {
    
        var url = "http://www.juniatacountyfair.com/manageUsers.do?command=loadall";

        httpRequest.onreadystatechange = function() {

            if (httpRequest.readyState == 4) {
                if (httpRequest.status == 200) {

                    var ulNode = document.getElementById("usersTable");
                    
                    var newHTML = "";

                    var xmldoc = httpRequest.responseXML;
                    var rootNode = xmldoc.getElementsByTagName("ItemsList")[0];

                    for (var i = 0; i < rootNode.childNodes.length; i++) {
                    
                        var siNode = rootNode.childNodes[i];

                        var dispTextNode = siNode.getElementsByTagName("DisplayName")[0].firstChild;
                        var userIdNode = siNode.getElementsByTagName("Id")[0].firstChild;
                        
                        if (dispTextNode && userIdNode) {
                            newHTML += '<tr>';
                            newHTML += '<td userid="' + userIdNode.nodeValue + '" ondblclick="editUser(this);" onclick="selectRow(this);">' + dispTextNode.nodeValue + '</td';
                            newHTML += '</tr>';
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

function editUser(element) {

    clearSelection();

    var id = element.getAttribute("userid");
    
    top.loadUser(id);

}

function clearSelection() {
   var sel ;
   if(document.selection && document.selection.empty){
     document.selection.empty() ;
   } else if(window.getSelection) {
     sel=window.getSelection();
     if(sel && sel.removeAllRanges)
       sel.removeAllRanges() ;
   }
}
  
  </script>
</head>

<body onload="reloadUsers();" style="background-color: #FFFFFF; padding: 0; margin: 0;">

<table id="usersTable" class="selector">
</table>

</body>

</html>