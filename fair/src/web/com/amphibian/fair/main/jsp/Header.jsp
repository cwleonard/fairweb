<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fair-tags.tld" prefix="fair" %>

<html:xhtml/>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Juniata County Fair</title>
<link rel="stylesheet" type="text/css" media="screen" href="../../css/normal.css" />
<script type="text/javascript" src="niftycube.js"></script>
<script type="text/javascript">
window.onload=function(){
Nifty("ul#nav a,div#navcontainer","small transparent top");
}
</script>
</head>

<body>

<div align="center">

  <div align="center" style="width: 100%;">
    <div align="left" id="annual">The 153rd Annual</div>
    <div align="left" id="title">Juniata County Fair</div>
    <div align="right" id="dates">September 1 - 8 2007</div>
  </div>

  <div id="navcontainer" align="left" valign="bottom">

    <ul id="nav">
      <li class="activelink"><a href="home.do">Home</a></li>
      <li><a href="schedule.do">Schedule</a></li>
      <li><a href="premiums.do">Premiums</a></li>
    </ul>

  </div>

</div>

</body>

</html>