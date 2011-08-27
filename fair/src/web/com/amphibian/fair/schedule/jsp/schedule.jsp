<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML+RDFa 1.0//EN" "http://www.w3.org/MarkUp/DTD/xhtml-rdfa-1.dtd">

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:v="http://rdf.data-vocabulary.org/#">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fair-tags.tld" prefix="fair" %>

<head>
  <meta http-equiv="Content-type" content="text/html;charset=UTF-8" />
  <title>Juniata County Fair - Schedule</title>
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

<fair:header navTab="Schedule" />

<div id="page">

  <div id="sidebar">

    <fair:personal />
    
  </div> <!-- end sidebar -->

  <div id="content">

<script type="text/javascript" language="JavaScript"><!--

idCounter = 400;

<logic:notEmpty name="AdminUserObj" scope="session">

</logic:notEmpty>

function doEdit(day, id, locEl) {

    var e = document.getElementById(locEl);
    if (e) {
        var newTop = DL_GetElementTop(e) + "px";
        var editArea = document.getElementById("itemModBox");
        editArea.style.top = newTop;
    }

    if (id) {
        getScheduleItemById(id);
    } else {
        doCancel();
    }

    var iDay = document.getElementById("modDay");
    iDay.value = day;
    
    var iId = document.getElementById("modId");
    iId.value = id;
    
    var editArea = document.getElementById("itemModBox");
    editArea.style.display = "block";

}

function doCancel() {

    var iDay = document.getElementById("modDay");
    var iId = document.getElementById("modId");
    var iDesc = document.getElementById("modDescription");
    var iStart = document.getElementById("modStartTime");
    var iStop = document.getElementById("modStopTime");
    var iAllDay = document.getElementById("modAllDay");
    
    iDay.value = "";
    iId.value = "";
    iDesc.value = "";
    iStart.value = "";
    iStop.value = "";
    iAllDay.checked = false;

    var editArea = document.getElementById("itemModBox");
    editArea.style.display = "none";

}

function doSave() {

    var iDay = document.getElementById("modDay").value;
    var iId = document.getElementById("modId").value;
    var iDesc = document.getElementById("modDescription").value;
    var iStart = document.getElementById("modStartTime").value;
    var iStop = document.getElementById("modStopTime").value;
    var iAllDay = document.getElementById("modAllDay").checked;
    
    var url = "http://www.juniatacountyfair.com/modItem.do?id=" + iId + "&day=" + iDay + "&desc=" + escape(iDesc) + "&start=" + escape(iStart) + "&end=" + escape(iStop) + "&allday=" + iAllDay;
    saveScheduleItem(iDay, url);
    
    var editArea = document.getElementById("itemModBox");
    editArea.style.display = "none";
    
}

function doDelete(day, id) {

    var url = "http://www.juniatacountyfair.com/delItem.do?id=" + id;
    makeRequest(url);
    
    var d = document.getElementById("list" + day);
    var s = document.getElementById("si-" + id);
    d.removeChild(s);

}

function doUpdateDay(day, type, element) {

    var url = "http://www.juniatacountyfair.com/modDay.do?day=" + day;
    url += "&" + type + "=" + element.value;
    makeRequest(url);

}

// -->
</script>

<div class="modBox" id="itemModBox">
    <form>

        <input type="hidden" id="modDay">
        <input type="hidden" id="modId">    
        <p>Description <input id="modDescription" type="text" size="50"></p>
        <p>Start Time <input id="modStartTime" type="text" size="10"></p>
        <p>Stop Time <input id="modStopTime" type="text" size="10"></p>
        <p><input type="checkbox" id="modAllDay">&nbsp;All Day Event</p>

        <button type="button" onclick="doSave();">Save</button>
        <button type="button" onclick="doCancel();">Cancel</button>

    </form>
</div>

<logic:iterate indexId="d" id="day" name="days">

<!-- day <bean:write format="#" name="day" property="day" /> -->

    <h4 id="h<bean:write format="#" name="day" property="day" />"><%= day %><logic:notEmpty name="AdminUserObj" scope="session"><span class="adminEditLinks">&nbsp;[&nbsp;<a href="javascript:doEdit(<bean:write format="#" name="day" property="day" />, null, 'h<bean:write format="#" name="day" property="day" />');">+</a>&nbsp;]</span></logic:notEmpty></h4>

    <logic:iterate id="img" name="day" property="images">
        <img style="float: right; margin: 5px;" src="images/<%= img %>" />
    </logic:iterate>

    <logic:notEmpty name="AdminUserObj" scope="session">
    <p>Admission <input type="text" id="modAdmission<bean:write format="#" name="day" property="day" />" value="<bean:write name="day" property="admission"/>" size="10" onchange="doUpdateDay(<bean:write format="#" name="day" property="day" />, 'admission', this);"> starting at <input id="modTime<bean:write format="#" name="day" property="day" />Start" value="<bean:write name="day" property="startTime"/>" size="10" onchange="doUpdateDay(<bean:write format="#" name="day" property="day" />, 'time', this);"></p>
    <p>Sponsored By: <input type="text" id="modSponsor<bean:write format="#" name="day" property="day" />" size="50" value="<bean:write name="day" property="sponsor"/>" onchange="doUpdateDay(<bean:write format="#" name="day" property="day" />, 'sponsor', this);"></p>
    <p>Other Notes: <input type="text" id="modNotes<bean:write format="#" name="day" property="day" />" size="50" value="<bean:write name="day" property="title"/>" onchange="doUpdateDay(<bean:write format="#" name="day" property="day" />, 'notes', this);"></p>
    </logic:notEmpty>

    <logic:empty name="AdminUserObj" scope="session">
        <logic:notEmpty name="day" property="sponsor">
            <p><b>Sponsored By: <bean:write name="day" property="sponsor"/></b></p>
        </logic:notEmpty>
        <p>Admission <bean:write name="day" property="admission"/> starting at <bean:write name="day" property="startTime"/></p>
        <logic:notEmpty name="day" property="title">
            <p><bean:write name="day" property="title"/></p>
        </logic:notEmpty>
    </logic:empty>

    <ul id="list<bean:write format="#" name="day" property="day" />">
        <logic:iterate indexId="z" id="i" name="day" property="items">
            <li typeof="v:Event" id="si-<bean:write name="i" property="id"/>"><%= i %><logic:notEmpty name="AdminUserObj" scope="session"><span class="adminEditLinks">&nbsp;[&nbsp;<a href="javascript:doEdit(<bean:write name="i" property="day"/>, <bean:write name="i" property="id"/>, 'si-<bean:write name="i" property="id"/>');">EDIT</a>&nbsp;]&nbsp;[&nbsp;<a href="javascript:doDelete(<bean:write name="i" property="day"/>, <bean:write name="i" property="id"/>);">DELETE</a>&nbsp;]</span></logic:notEmpty></li>
        </logic:iterate>
    </ul>

</logic:iterate>

  </div> <!-- end content -->
  
</div> <!-- end page -->

<div id="footer">

  <p>This page has been accessed <%= request.getAttribute("hits") %> times.</p>
  
  <p>All content on this site is Copyright &#169; 2007 Juniata County Agricultural Society.</p>
  
</div> <!-- end footer -->

</body>

</html>