<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fair-tags.tld" prefix="fair" %>

<html:xhtml/>

<html>
<head>
  <title>User Account Manager</title>
  <link rel="stylesheet" type="text/css" media="screen" href="css/normal.css" />
  <script type="text/javascript" src="scripts/niftycube.js"></script>
  <script type="text/javascript" src="scripts/standard-functions.js"></script>
  <script type="text/javascript" language="Javascript">

  window.onload=function() {
      Nifty("ul#nav a,div#navcontainer","small transparent top");
  }

  </script>
  <script type="text/javascript" language="Javascript">

function getUserById(id) {

    var httpRequest = getHttpRequest();
    if (httpRequest) {
    
        var url = "http://www.juniatacountyfair.com/manageUsers.do?command=load&id=" + id;
        
        httpRequest.onreadystatechange = function() {

            if (httpRequest.readyState == 4) {
                if (httpRequest.status == 200) {

                    var iId = document.getElementById("id");
                    var iDispId = document.getElementById("dispId");
                    var iUserId = document.getElementById("userId");
                    var iFirstName = document.getElementById("firstName");
                    var iLastName = document.getElementById("lastName");
                    var iOrgName = document.getElementById("orgName");
                    var iAddress = document.getElementById("address");
                    var iCity = document.getElementById("city");
                    var iState = document.getElementById("state");
                    var iZip = document.getElementById("zip");
                    var iPhone = document.getElementById("phone");
                    var iEmail = document.getElementById("email");
                    var iAdmin = document.getElementById("webAdmin");
            
                    var iIndividual = document.getElementById("uTypeP");
                    var iOrganization = document.getElementById("uTypeO");
            
                    var xmldoc = httpRequest.responseXML;
                    var rootNode = xmldoc.getElementsByTagName("ItemsList")[0];
                    var siNode = rootNode.firstChild;
            
                    var eIdValue = "";
                    var eUserIdValue = "";
                    var eFirstNameValue = "";
                    var eLastNameValue = "";
                    var eOrgNameValue = "";
                    var eAddressValue = "";
                    var eCityValue = "";
                    var eStateValue = "";
                    var eZipValue = "";
                    var ePhoneValue = "";
                    var eEmailValue = "";
                    var eAdminValue = "";

                    var eIdNode = siNode.getElementsByTagName("Id")[0].firstChild;
                    var eUserIdNode = siNode.getElementsByTagName("UserId")[0].firstChild;
                    var eFirstNameNode = siNode.getElementsByTagName("FirstName")[0].firstChild;
                    var eLastNameNode = siNode.getElementsByTagName("LastName")[0].firstChild;
                    var eOrgNameNode = siNode.getElementsByTagName("OrgName")[0].firstChild;
                    var eAddressNode = siNode.getElementsByTagName("Address")[0].firstChild;
                    var eCityNode = siNode.getElementsByTagName("City")[0].firstChild;
                    var eStateNode = siNode.getElementsByTagName("State")[0].firstChild;
                    var eZipNode = siNode.getElementsByTagName("Zip")[0].firstChild;
                    var ePhoneNode = siNode.getElementsByTagName("Phone")[0].firstChild;
                    var eEmailNode = siNode.getElementsByTagName("Email")[0].firstChild;
                    var eAdminNode = siNode.getElementsByTagName("WebAdmin")[0].firstChild;
                    
                    if (eIdNode) {
                        eIdValue = eIdNode.nodeValue;
                    }

                    if (eUserIdNode) {
                        eUserIdValue = eUserIdNode.nodeValue;
                    }

                    if (eFirstNameNode) {
                        eFirstNameValue = eFirstNameNode.nodeValue;
                    }
                    
                    if (eLastNameNode) {
                        eLastNameValue = eLastNameNode.nodeValue;
                    }
                    
                    if (eOrgNameNode) {
                        eOrgNameValue = eOrgNameNode.nodeValue;
                    }

                    if (eAddressNode) {
                        eAddressValue = eAddressNode.nodeValue;
                    }

                    if (eCityNode) {
                        eCityValue = eCityNode.nodeValue;
                    }

                    if (eStateNode) {
                        eStateValue = eStateNode.nodeValue;
                    }

                    if (eZipNode) {
                        eZipValue = eZipNode.nodeValue;
                    }

                    if (ePhoneNode) {
                        ePhoneValue = ePhoneNode.nodeValue;
                    }

                    if (eEmailNode) {
                        eEmailValue = eEmailNode.nodeValue;
                    }

                    if (eAdminNode) {
                        eAdminValue = eAdminNode.nodeValue;
                    }
                    
                    iId.value = eIdValue;
                    iDispId.innerHTML = "ID: " + eIdValue;
                    iUserId.value = eUserIdValue;
                    iFirstName.value = eFirstNameValue;
                    iLastName.value = eLastNameValue;
                    iOrgName.value = eOrgNameValue;
                    iAddress.value = eAddressValue;
                    iCity.value = eCityValue;
                    //iState.value = eStateValue;
                    iZip.value = eZipValue;
                    iPhone.value = ePhoneValue;
                    iEmail.value = eEmailValue;
                    //iAdmin.value = eAdminValue;
                    for (var q = 0; q < iAdmin.options.length; q++) {
                        if (iAdmin.options[q].value == eAdminValue) {
                            iAdmin.options[q].selected = true;
                        }
                    }
                    
                    
                    if (eOrgNameValue.length > 0) {
                        toggleNames('O');
                        iIndividual.checked = false;
                        iOrganization.checked = true;
                    } else {
                        toggleNames('P');
                        iIndividual.checked = true;
                        iOrganization.checked = false;
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

function loadUser(userid) {

    getUserById(userid);

}

function newUser() {

    var iId = document.getElementById("id");
    var iDispId = document.getElementById("dispId");
    var iUserId = document.getElementById("userId");
    var iFirstName = document.getElementById("firstName");
    var iLastName = document.getElementById("lastName");
    var iOrgName = document.getElementById("orgName");
    var iAddress = document.getElementById("address");
    var iCity = document.getElementById("city");
    var iState = document.getElementById("state");
    var iZip = document.getElementById("zip");
    var iPhone = document.getElementById("phone");
    var iEmail = document.getElementById("email");
    var iAdmin = document.getElementById("webAdmin");

    iId.value = "-1";
    iDispId.innerHTML = "New User";
    iUserId.value = "";
    iFirstName.value = "";
    iLastName.value = "";
    iOrgName.value = "";
    iAddress.value = "";
    iCity.value = "";
    iState.selectedIndex = 0;
    iZip.value = "";
    iPhone.value = "";
    iEmail.value = "";
    iAdmin.selectedIndex = 0;    

}

function saveUser() {

    var url = "http://www.juniatacountyfair.com/manageUsers.do";

    var httpRequest = getHttpRequest();

    if (httpRequest) {
        httpRequest.onreadystatechange = function() {
            if (httpRequest.readyState == 4) {
                if (httpRequest.status == 200) {
                    alert(httpRequest.responseText);
                } else {
                    alert("There was a problem with the reqeust!");
                }
            }
        };

        var f = document.getElementById("userEditForm");

        httpRequest.open('POST', url, true);
        httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        httpRequest.send('command=save&' + serializeForm(f));

    }

}

function toggleNames(t) {

  if (t == "P") {
    hideLayer("organizationName");
    showLayer("personName");
  } else {
    hideLayer("personName");
    showLayer("organizationName");
  }

}
 
function hideLayer(whichLayer) {

	if (document.getElementById) {
		var style2 = document.getElementById(whichLayer).style;
        style2.display = "none";
	} else if (document.all) {
		// this is the way old msie versions work
		var style2 = document.all[whichLayer].style;
		style2.display = "none";
	} else if (document.layers) {
		// this is the way nn4 works
		var style2 = document.layers[whichLayer].style;
		style2.display = "none";
	}
	
}

function showLayer(whichLayer) {

	if (document.getElementById) {
		var style2 = document.getElementById(whichLayer).style;
        style2.display = "block";
	} else if (document.all) {
		// this is the way old msie versions work
		var style2 = document.all[whichLayer].style;
		style2.display = "block";
	} else if (document.layers) {
		// this is the way nn4 works
		var style2 = document.layers[whichLayer].style;
		style2.display = "block";
	}
	
}

  </script>
</head>

<body class="app">

<fair:header navTab="Users" />

<div id="page">

  <div id="sidebar">

    <fair:personal />
    
  </div> <!-- end sidebar -->

  <div id="content">

<div style="float: left;">
<iframe style="width: 250px; height: 400px;"
        src="accountSelector.do">
</iframe>

<p><button type="button" onclick="newUser();">New User</button></p>

</div>

<div style="margin-left: 20px; float: left;">

<form id="userEditForm">

  <input type="hidden" name="id" id="id" />

<h4>Account Editor</h4>

  <p id="dispId"></p>

  <div style="float: left;">
    <p>User ID <input type="text" name="userId" id="userId" /></p>
  </div>
  <div style="margin-left: 20px; float: left;">
    <p><input type="radio" checked id="uTypeP" name="uType" value="P" onclick="toggleNames('P');">Individual<br />
    <input type="radio" id="uTypeO" name="uType" value="O" onclick="toggleNames('O');">Organization</p>
  </div>

<div id="personName" style="display: block;">
  <div style="float: left;">
    <p><input type="text" id="firstName" name="firstName"/><br />First Name</p>
  </div>
  <div style="margin-left: 20px; float: left;">
    <p><input type="text" id="lastName" name="lastName"/><br />Last Name</p>
  </div>
</div>

<div id="organizationName" style="display: none;">
<p><input type="text" id="orgName" name="orgName" size="35"/><br />
Organization Name</p>
</div>


<p><input type="text" id="address" name="address" size="35"/><br />
Street Address</p>

<p><input type="text" id="city" name="city" size="35"/><br />
City</p>

  <div style="float: left;">
    <p><select id="state" name="state"><option value="PA">PA</option></select><br />State</p>
  </div>
  <div style="margin-left: 20px; float: left;">
    <p><input id="zip" type="text" name="zip" size="10"/><br />ZIP</p>
  </div>
  <div style="margin-left: 20px; float: left;">
    <p><input type="text" id="phone" name="phone"/><br />Phone</p>
  </div>

<p></p>

  <div style="float: left;">
    <p><input type="text" id="email" name="email"/><br />Email</p>
  </div>
  <div style="margin-left: 20px; float: left;">
    <p><select id="webAdmin" name="webAdmin"><option value="false">false</option>><option value="true">true</option>></select><br />Administrator</p>
  </div>

<p></p>

<p><button type="button" onclick="saveUser();">Save</button></p>

</form>



</div>

  </div> <!-- end content -->

</div> <!-- end page -->

<div id="footer">

  <p>This page has been accessed <%= request.getAttribute("hits") %> times.</p>
  
  <p>All content on this site is Copyright &#169; 2007 Juniata County Agricultural Society.</p>
  
</div> <!-- end footer -->

</body>

</html>