<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fair-tags.tld" prefix="fair" %>

<html:xhtml/>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
  <title>Juniata County Fair - Login</title>
  <link rel="stylesheet" type="text/css" media="screen" href="css/normal.css" />
  <script type="text/javascript" src="scripts/niftycube.js"></script>
  <script type="text/javascript" src="scripts/standard-functions.js"></script>
  <script type="text/javascript" language="Javascript">

  window.onload=function() {
      Nifty("ul#nav a,div#navcontainer","small transparent top");
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

      <html:form action="login">
      <html:hidden property="returnToSender"/>
      <html:hidden property="from"/>
      
      <h4>Account Login</h4>

      <logic:messagesPresent>
          <p style="color: #FF0000; font-weight: bold;"><html:errors /></p>
      </logic:messagesPresent>

      <p><html:text style="width: 200px;" property="userId" /><br/>User Id</p>
      <p><html:password style="width: 200px;" property="password" /><br/>Password</p>
      <p align="center"><html:submit /></p>

      </html:form>
      
      <a href="forgotPassword.do">Forgotten your password? Click here.</a>

  </div> <!-- end content -->

</div> <!-- end page -->

<div id="footer">

  <p>This page has been accessed <%= request.getAttribute("hits") %> times.</p>
  
  <p>All content on this site is Copyright &#169; 2007 Juniata County Agricultural Society.</p>
  
</div> <!-- end footer -->

</body>

</html>