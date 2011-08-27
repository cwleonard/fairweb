<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fair-tags.tld" prefix="fair" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
  <title>Juniata County Fair - Premiums Entries</title>
  <link rel="stylesheet" type="text/css" media="screen" href="css/normal.css">
</head>

<body>

<table border="1" cellspacing="2" cellpadding="3">
  <tr style="font-weight: bold;">
    <td>ID</td>
    <td>Exhibitor</td>
    <td>Entry ID</td>
    <td>Description</td>
    <td>DOB</td>
    <td>Animal Name</td>
    <td>Sire</td>
    <td>Dam</td>
    <td>Breeder</td>
    <td>Printed</td>
  </tr>
<logic:iterate id="e" name="entries">
  <tr>
    <td><bean:write name="e" property="id"/></td>
    <td><bean:write name="e" property="exhibitorName"/></td>
    <td><bean:write name="e" property="entryId"/></td>
    <td><bean:write name="e" property="description"/></td>
    <td><bean:write name="e" property="DOB"/></td>
    <td><bean:write name="e" property="animalName"/></td>
    <td><bean:write name="e" property="sire"/></td>
    <td><bean:write name="e" property="dam"/></td>
    <td><bean:write name="e" property="breeder"/></td>
    <td><bean:write name="e" property="printed"/></td>
  </tr>
</logic:iterate>
</table>

</body>

</html>