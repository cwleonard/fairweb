<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fair-tags.tld" prefix="fair" %>

<html:xhtml/>

<html>
<head>
  <title>Forgotten Password</title>
  <link rel="stylesheet" type="text/css" media="screen" href="css/normal.css">
  <link rel="stylesheet" type="text/css" media="screen" href="css/navbar.css" />
</head>

<body>

<% request.setAttribute("thisPage", "premiums"); %>

<jsp:include page="header.jsp" />

<jsp:include page="navigation.jsp" />

<html:form action="forgotPassword">

<div align="center">
  <center>

<fair:pipebox width="600" title="Forgotten Password">

<p>Please enter your email address here and your password will be sent to you.</p>

<p>Email address <input type="text" name="userId" />
<logic:messagesPresent property="userId">
<span style="color: #FF0000; font-weight: bold;"><br /><html:errors property="userId"/></span>
</logic:messagesPresent></p>

<p><html:submit />&nbsp;<html:cancel /></p>

</fair:pipebox>

  </center>
</div>

</html:form>

</body>

</html>