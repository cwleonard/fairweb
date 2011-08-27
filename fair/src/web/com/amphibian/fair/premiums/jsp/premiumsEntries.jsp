<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fair-tags.tld" prefix="fair" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
  <title>Juniata County Fair - Your Premiums Entries</title>
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

<fair:header navTab="Premiums" />

<div id="page">

  <div id="sidebar">

    <fair:personal />
    
  </div> <!-- end sidebar -->

  <div id="content">

    <h4>Your Entries</h4>

<table border="0" cellspacing="1" cellpadding="0">
  <tr>
    <th>Class Code</th>
    <th>Description</th>
    <th>DOB</th>
    <th>Animal Name</th>
    <th>Sire</th>
    <th>Dam</th>
    <th>Breeder</th>
  </tr>
<logic:iterate indexId="i" id="e" name="entries">
  <tr class="bg<%= (i % 2) %>">
    <td><a href="/classDetail.do?code=<bean:write name="e" property="entryId"/>"><bean:write name="e" property="entryId"/></a></td>
    <td><bean:write name="e" property="description"/></td>
    <td><bean:write name="e" property="DOB"/></td>
    <td><bean:write name="e" property="animalName"/></td>
    <td><bean:write name="e" property="sire"/></td>
    <td><bean:write name="e" property="dam"/></td>
    <td><bean:write name="e" property="breeder"/></td>
  </tr>
</logic:iterate>
</table>

  </div> <!-- end content -->

</div> <!-- end page -->

<div id="footer">

  <p>This page has been accessed <%= request.getAttribute("hits") %> times.</p>
  
  <p>All content on this site is Copyright &#169; 2007 Juniata County Agricultural Society.</p>
  
</div> <!-- end footer -->

</body>

</html>