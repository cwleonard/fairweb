<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fair-tags.tld" prefix="fair" %>

<html:xhtml/>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
  <title>Juniata County Fair - Schedule</title>
  <link rel="stylesheet" type="text/css" media="screen" href="css/normal.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="css/navbar.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="css/css_urls.jsp" />
</head>

<body>

<html:form action="editSchedule">

<script type="text/javascript" language="JavaScript"><!--

idCounter = 400;

<logic:notEmpty name="AdminUserObj" scope="session">

function removeItem(num, iid) {

	var l = document.getElementById("list" + num);
	var d = document.getElementById(iid);
	
	l.removeChild(d);

}

function blah(num) {

	x = document.getElementById("list" + num);
	s = document.getElementById("stuff" + num);

	if (s.value != "") {

		var newId = "li" + idCounter;
		idCounter++;
		
		var p = document.createElement("li");
		p.setAttribute("id", newId);

		var inp = document.createElement("input");
		inp.setAttribute("name", "itemDay" + num);
		inp.setAttribute("type", "text");
		inp.setAttribute("value", s.value);
		inp.setAttribute("size", "35");

		var rmv = document.createElement("input");
		rmv.setAttribute("type", "button");
		rmv.setAttribute("value", "Remove");
		rmv["onclick"] = function() {removeItem(num, newId)};
		
		p.appendChild(inp);
		p.appendChild(rmv);
		x.appendChild(p);
		s.value = "";
		s.focus();
		
	}

}

</logic:notEmpty>

// -->
</script>

<% request.setAttribute("thisPage", "schedule"); %>

<jsp:include page="header.jsp" />

<jsp:include page="navigation.jsp" />

<div align="center">
  <center>

<logic:iterate indexId="d" id="day" name="days">

<!-- day <bean:write format="#" name="day" property="day" /> -->

<fair:pipebox width="600" title="<%= day %>">

<logic:notEmpty name="AdminUserObj" scope="session">
<p>Admission <input type="text" name="day<bean:write format="#" name="day" property="day" />Admission" value="<bean:write name="day" property="admission"/>" size="10"> starting at <input name="day<bean:write format="#" name="day" property="day" />Start" value="<bean:write name="day" property="startTime"/>" size="10"></p>
</logic:notEmpty>

<logic:empty name="AdminUserObj" scope="session">
<p>Admission <bean:write name="day" property="admission"/> starting at <bean:write name="day" property="startTime"/></p>
</logic:empty>

<p><b>Sponsored By:<div style="border-width: 10px;"><fair:rulesWrite name="day" property="sponsor"/></div></b></p>

<table id="list<bean:write format="#" name="day" property="day" />">

<logic:iterate indexId="z" id="i" name="day" property="items">

<logic:notEmpty name="AdminUserObj" scope="session">
  <li id="item-<%= z %>-<%= d %>"><input type="text" size="35" name="itemDay<bean:write format="#" name="day" property="day" />" value="<%= i %>"/><input type="button" value="Remove" onclick="removeItem('<bean:write format="#" name="day" property="day" />', 'item-<%= z %>-<%= d %>');" /></li>
</logic:notEmpty>

<logic:empty name="AdminUserObj" scope="session">
  <tr>
    <td><bean:write name="i" property="startTime"/></td>
    <td><bean:write name="i" property="description"/></td>
  </tr>
</logic:empty>

</logic:iterate>

</table>

<logic:notEmpty name="AdminUserObj" scope="session">
  <p>New Item:&nbsp;
  <input type="text" size="30" id="stuff<bean:write format="#" name="day" property="day" />">
  <input type="button" value="Add" onclick="blah(<bean:write format="#" name="day" property="day" />);"></p>
</logic:notEmpty>

</fair:pipebox>

</logic:iterate>

  </center>
</div>
      
  </center>
</div>



<logic:notEmpty name="AdminUserObj" scope="session">
<p align="center"><html:submit value="Save"/>&nbsp;<html:cancel /></p>
</logic:notEmpty>
      
      </td>
    </tr>
  </tbody></table>
  </center>
</div>


<p>&nbsp;</p>

</html:form>

</body>

</html>