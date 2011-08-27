<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fair-tags.tld" prefix="fair" %>

<html:xhtml/>

<%

Boolean allowEditUserId = (Boolean)request.getAttribute("allowEditUserId");


%>

<html>
<head>
  <title>Edit User</title>
  <link rel="stylesheet" type="text/css" media="screen" href="css/normal.css" />
  <script type="text/javascript" src="scripts/niftycube.js"></script>
  <script type="text/javascript" src="scripts/standard-functions.js"></script>
  <script type="text/javascript" language="Javascript">

  window.onload=function() {
      Nifty("ul#nav a,div#navcontainer","small transparent top");
  }

  </script>
  <script language="javascript">

function toggleNames(t) {

  if (t == "P") {
    hideLayer("orgName");
    showLayer("personName");
  } else {
    hideLayer("personName");
    showLayer("orgName");
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

<body>

<fair:header navTab="You" />

<div id="page">

  <div id="sidebar">

    <fair:personal />

  </div> <!-- end sidebar -->

  <div id="content">

<html:form action="saveUser">
<html:hidden property="id" />

<h4>Account Editor</h4>

<% if (allowEditUserId.booleanValue()) { %>
<p>User ID <html:text property="userId"/> (Tip: you can use your email address for your User Id)
<logic:messagesPresent property="userId">
<span style="color: #FF0000; font-weight: bold;"><br /><html:errors property="userId"/></span>
</logic:messagesPresent></p>
<% } else { %>
<p>User ID <b><bean:write name="UserForm" property="userId"/></b></p>
<html:hidden property="userId" />
<% } %>

<p><input type="radio" checked name="uType" value="P" onclick="toggleNames('P');">Individual<br />
<input type="radio" name="uType" value="O" onclick="toggleNames('O');">Organization</p>

<div id="personName" style="display: block;">
<table cellpadding="0" cellspacing="0" border="0">
  <tr>
    <td><html:text property="firstName"/><br />First Name</td>
    <td><img alt="" src="images/transpixel.gif" border="0" height="1" width="10"></td>
    <td><html:text property="lastName"/><br />Last Name</td>
  </tr>
</table>
</div>

<div id="orgName" style="display: none;">
<p><html:text property="orgName" size="35"/><br />
Organization Name
<logic:messagesPresent property="orgName">
<span style="color: #FF0000; font-weight: bold;">&nbsp;-&nbsp;<html:errors property="orgName"/></span>
</logic:messagesPresent></p>
</div>

<!--<p>SSN or Tax ID <html:text name="user" property="taxId"/></p>-->

<p><html:text property="address" size="35"/><br />
Street Address
<logic:messagesPresent property="address">
<span style="color: #FF0000; font-weight: bold;">&nbsp;-&nbsp;<html:errors property="address"/></span>
</logic:messagesPresent></p>

<p><html:text property="city" size="35"/><br />
City
<logic:messagesPresent property="city">
<span style="color: #FF0000; font-weight: bold;">&nbsp;-&nbsp;<html:errors property="city"/></span>
</logic:messagesPresent></p>

<table cellpadding="0" cellspacing="0" border="0">
  <tr>
    <td><html:select property="state">
<html:option value="PA"/>
</html:select><br />State</td>
    <td><img alt="" src="images/transpixel.gif" border="0" height="1" width="10"></td>
    <td><html:text property="zip" size="10"/><br />
        ZIP
        <logic:messagesPresent property="zip">
<span style="color: #FF0000; font-weight: bold;">&nbsp;-&nbsp;<html:errors property="zip"/></span>
</logic:messagesPresent>
        </td>
  </tr>
</table>
<p></p>
<p></p>

<p>Phone <html:text property="phone"/></p>
<p>Email address <html:text property="email"/>
<logic:messagesPresent property="email">
<span style="color: #FF0000; font-weight: bold;"><br /><html:errors property="email"/></span>
</logic:messagesPresent></p>

<% if (allowEditUserId.booleanValue()) { %>
<p>Password <input type="password" name="createPassword" autocomplete="off"/>
<p>Retype Password <input type="password" name="retypePassword" autocomplete="off"/>
<logic:messagesPresent property="password">
<span style="color: #FF0000; font-weight: bold;"><br /><html:errors property="password"/></span>
</logic:messagesPresent></p>
<% } %>

<logic:notEmpty name="AdminUserObj" scope="session">
<p>web admin? <html:select property="webAdmin">
<html:option value="true"/>
<html:option value="false"/>
</html:select></p>
</logic:notEmpty>

</p>

<p><html:submit/>&nbsp;<html:cancel/></p>

</html:form>

    </div> <!-- end content -->

</div> <!-- end page -->


</body>

</html>