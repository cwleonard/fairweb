<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fair-tags.tld" prefix="fair" %>

<html:xhtml/>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
  <title>Juniata County Fair - Home</title>
  <link rel="stylesheet" type="text/css" media="screen" href="css/normal.css" />
  <script type="text/javascript" src="scripts/niftycube.js"></script>
  <script type="text/javascript" src="scripts/standard-functions.js"></script>
  <script type="text/javascript" language="Javascript">

  // rounds corners
  window.onload=function() {
      Nifty("ul#nav a,div#navcontainer","small transparent top");
  }

  </script>
  <script type="text/javascript" language="Javascript">

function doEdit(id, locEl) {

    var e = document.getElementById(locEl);
    if (e) {
        var newTop = DL_GetElementTop(e) + "px";
        var newLeft = DL_GetElementLeft(e) + "px";
        var editArea = document.getElementById("itemModBox");
        editArea.style.top = newTop;
        editArea.style.left = newLeft;
    }

    var iDate = document.getElementById("modDate");
    if (id) {
        getNewsItemById(id);
        iDate.disabled = false;
    } else {
        doCancel();
        var dNow = new Date();
        iDate.value = (dNow.getMonth() + 1) + "/" + dNow.getDate() + "/" + dNow.getFullYear();
        iDate.disabled = true;
    }

    var iId = document.getElementById("modId");
    iId.value = id;
    
    var editArea = document.getElementById("itemModBox");
    editArea.style.display = "block";

}

function doEdit2(id, locEl) {

    var e = document.getElementById(locEl);
    if (e) {
        var newTop = DL_GetElementTop(e) + "px";
        var newLeft = DL_GetElementLeft(e) + "px";
        var editArea = document.getElementById("infoModBox");
        editArea.style.top = newTop;
        editArea.style.left = newLeft;
    }

    if (id) {
        getInfoItemById(id);
    } else {
        doCancel2();
    }

    var iId = document.getElementById("modId2");
    iId.value = id;
    
    var editArea = document.getElementById("infoModBox");
    editArea.style.display = "block";

}

function doEditFile(id, locEl) {

    var editArea = document.getElementById("fileModBox");
    var e = document.getElementById(locEl);
    if (e) {
        var newTop = DL_GetElementTop(e) + "px";
        var newLeft = DL_GetElementLeft(e) + "px";
        editArea.style.top = newTop;
        editArea.style.left = newLeft;
    }

//    if (id) {
//        getInfoItemById(id);
//    } else {
//        doCancel2();
//    }

//    var iId = document.getElementById("modId2");
//    iId.value = id;
    
    editArea.style.display = "block";

}

function doCancel() {

    var iId = document.getElementById("modId");
    var iText = document.getElementById("modText");
    var iDate = document.getElementById("modDate");
    
    iId.value = "";
    iText.value = "";
    iDate.value = "";

    var editArea = document.getElementById("itemModBox");
    editArea.style.display = "none";

}

function doCancel2() {

    var iId = document.getElementById("modId2");
    var iHeadline = document.getElementById("modHeadline");
    var iText = document.getElementById("modText2");
    
    iId.value = "";
    iHeadline.value = "";
    iText.value = "";

    var editArea = document.getElementById("infoModBox");
    editArea.style.display = "none";

}

function doCancelUpload() {

    var iTitle = document.getElementById("fileTitle");
    var iFile = document.getElementById("fileSelect");
    
    iTitle.value = "";
    iFile.value = "";

    var editArea = document.getElementById("fileModBox");
    editArea.style.display = "none";

}

function doSave() {

    var iId = document.getElementById("modId").value;
    var iText = document.getElementById("modText").value;
    var iDate = document.getElementById("modDate").value;
    
    var url = "http://www.juniatacountyfair.com/manageNews.do?command=save&id=" + iId + "&text=" + escape(iText) + "&date=" + escape(iDate);
    saveNewsItem(url);
    
    var editArea = document.getElementById("itemModBox");
    editArea.style.display = "none";
    
}

function doSave2() {

    var iId = document.getElementById("modId2").value;
    var iHeadline = document.getElementById("modHeadline").value;
    var iText = document.getElementById("modText2").value;
    
    var url = "http://www.juniatacountyfair.com/manageInfo.do";
    saveInfoItem(url, iId, iHeadline, iText);
    
    var editArea = document.getElementById("infoModBox");
    editArea.style.display = "none";
    
}

function doDelete(id) {

    var url = "http://www.juniatacountyfair.com/manageNews.do?command=delete&id=" + id;
    makeRequest(url);
    
    var d = document.getElementById("newsArea");
    var s = document.getElementById("ni-" + id);
    d.removeChild(s);

}

function doDelete2(id) {

    var url = "http://www.juniatacountyfair.com/manageInfo.do?command=delete&id=" + id;
    makeRequest(url);
    
    var d = document.getElementById("infoArea");
    var s = document.getElementById("ii-" + id);
    d.removeChild(s);

}

function doDeleteFile(id) {

    var url = "http://www.juniatacountyfair.com/manageDocuments.do?command=delete&id=" + id;
    makeRequest(url);
    
    var d = document.getElementById("downloadsArea");
    var s = document.getElementById("di-" + id);
    d.removeChild(s);

}

function toggle(id) {

    toggleDisplay('lis-' + id);
    toggleDisplay('lic-' + id);

}

function doUpload() {

    var f = document.getElementById("FileUploadForm");
    var ft = document.getElementById("fileTitle");
    var fs = document.getElementById("fileSelect");
    
    if (ft.value == "") {
        alert("Files must have titles");
        ft.focus();
        return;
    }
    
    if (fs.value == "") {
        alert("You must select a file");
    	return;
    }
    
    f.submit();

}

  </script>

</head>

<body>

<fair:header navTab="Home" />

<logic:notEmpty name="AdminUserObj" scope="session">

<div class="modBox" id="itemModBox">
    <form>

        <input type="hidden" id="modId">    
        <p>Post Date <input id="modDate" type="text" size="10"></p>
        <p>Text <input id="modText" type="text" size="50"></p>

        <button type="button" onclick="doSave();">Save</button>
        <button type="button" onclick="doCancel();">Cancel</button>

    </form>
</div>

<div class="modBox" id="infoModBox">
    <form>

        <input type="hidden" id="modId2">    
        <p>Headline <input id="modHeadline" type="text" size="50"></p>
        <p><span style="position: absolute;">Text</span><span style="margin-left: 6ex;"><textarea id="modText2" cols="50" rows="10"></textarea></span></p>

        <button type="button" onclick="doSave2();">Save</button>
        <button type="button" onclick="doCancel2();">Cancel</button>

    </form>
</div>

<div class="modBox" id="fileModBox">

    <form id="FileUploadForm" method="post" action="/manageDocuments.do" enctype="multipart/form-data" target="uploadTarget">
    
        <input type="hidden" name="command" value="save">
        <p>Title <input id="fileTitle" type="text" name="title" size="50" /></p>
        <p>File <input id="fileSelect" type="file" name="file" value="" size="50" /></p>
        <button type="button" onclick="doUpload();">Upload</button>
        <button type="button" onclick="doCancelUpload();">Cancel</button>
        
        <iframe name="uploadTarget" id="uploadTarget" style="display: none;"></iframe>

    </form>

</div>

</logic:notEmpty>

<div id="page">

  <div id="sidebar">

    <fair:personal />
    
    <div align="center">
      <p><b>Juniata County Fair</b><br>302 Sixth Street<br>Port Royal, PA 17082
      <br><br>(717) 527-4414<br><br><a href="mailto:jcfair@centurylink.net">jcfair@centurylink.net</a></p>
    </div>

  </div> <!-- end sidebar -->

  <div id="content">

    <img style="float: right;" width="300" height="220" src="images/fair_logo.jpg" />

    <logic:notEmpty name="daysLeft">

        <p class="greeting">Welcome to the home of the Juniata
        County Fair! We're only
        <%= request.getAttribute("daysLeft") %> days away from
        the start of another exciting fair!</p>

    </logic:notEmpty>

    <logic:notEmpty name="nextYear">

        <p class="greeting">Welcome to the home of the Juniata
        County Fair!</p>
        
        <p class="greeting">The fair has ended for this year, but we hope
        to see you back next year for the <%= request.getAttribute("nextYear") %>
        Juniata County Fair!</p>

    </logic:notEmpty>

    <logic:notEmpty name="fairday">
    
        <p class="greeting">Welcome to the home of the Juniata
        County Fair! This is day <bean:write name="fairday" property="day" /> of the fair.</p>

        <p class="greeting">This is what's happening today. You won't want to miss
        any of these exciting events!</p>

    <ul>
        <logic:iterate indexId="z" id="i" name="fairday" property="items">
            <li><%= i %></li>
        </logic:iterate>
    </ul>

    </logic:notEmpty>

    <h4 id="infoHeading">Items Currently of Interest<logic:notEmpty name="AdminUserObj" scope="session"><span class="adminEditLinks">&nbsp;[&nbsp;<a href="javascript:doEdit2(null, 'infoHeading');">+</a>&nbsp;]</span></logic:notEmpty></h4>

    <div id="infoArea">
    <logic:iterate id="i" name="info">
      <div id="ii-<bean:write name="i" property="id" />">
          <logic:notEmpty name="i" property="image">
              <img style="float: left; padding: 10px;" src="images/<bean:write name="i" property="image" />" />
          </logic:notEmpty>
          <p><span class="important"><bean:write name="i" property="headline"/></span><logic:notEmpty name="AdminUserObj" scope="session"><span class="adminEditLinks">&nbsp;[&nbsp;<a href="javascript:doEdit2(<bean:write name="i" property="id"/>, 'ii-<bean:write name="i" property="id"/>');">EDIT</a>&nbsp;]&nbsp;[&nbsp;<a href="javascript:doDelete2(<bean:write name="i" property="id"/>);">DELETE</a>&nbsp;]</span></logic:notEmpty></p>
          <div class="completeInfo" style="display: block;" id="lis-<bean:write name="i" property="id" />">
              <fair:rulesWrite name="i" property="shortText"/>
              <logic:equal name="i" property="textLengthEqual" value="false">
                  <p class="toggleLink"><a href="javascript:toggle('<bean:write name="i" property="id" />');">Read Complete Text</a></p>
              </logic:equal>
          </div>
          <logic:equal name="i" property="textLengthEqual" value="false">
              <div class="completeInfo" style="display: none;" id="lic-<bean:write name="i" property="id" />">
                  <fair:rulesWrite name="i" property="text"/>
                  <p class="toggleLink"><a href="javascript:toggle('<bean:write name="i" property="id" />');">Hide Complete Text</a></p>
              </div>
          </logic:equal>
      </div>
    </logic:iterate>
    </div>

    <h4 id="newsHeading">Latest Fair News<logic:notEmpty name="AdminUserObj" scope="session"><span class="adminEditLinks">&nbsp;[&nbsp;<a href="javascript:doEdit(null, 'newsHeading');">+</a>&nbsp;]</span></logic:notEmpty></h4>

    <div id="newsArea">
    <logic:iterate id="n" name="news">

      <p class="news" id="ni-<bean:write name="n" property="id" />"><bean:write name="n" property="postDate" />&nbsp;-&nbsp;<bean:write name="n" property="text" filter="false"/><logic:notEmpty name="AdminUserObj" scope="session"><span class="adminEditLinks">&nbsp;[&nbsp;<a href="javascript:doEdit(<bean:write name="n" property="id"/>, 'ni-<bean:write name="n" property="id"/>');">EDIT</a>&nbsp;]&nbsp;[&nbsp;<a href="javascript:doDelete(<bean:write name="n" property="id"/>);">DELETE</a>&nbsp;]</span></logic:notEmpty></p>

    </logic:iterate>
    </div>

    <h4 id="downloadsHeading">Fair Information and Entry Forms to Download<logic:notEmpty name="AdminUserObj" scope="session"><span class="adminEditLinks">&nbsp;[&nbsp;<a href="javascript:doEditFile(null, 'downloadsHeading');">+</a>&nbsp;]</span></logic:notEmpty></h4>

    <div id="downloadsArea">
    <logic:iterate id="d" name="downloads">

      <p class="downloads" id="di-<bean:write name="d" property="id" />"><a href="downloadFromDatabase.do?id=<bean:write name="d" property="id" />"><bean:write name="d" property="description" /></a><logic:notEmpty name="AdminUserObj" scope="session"><span class="adminEditLinks">&nbsp;[&nbsp;<a href="javascript:doDeleteFile(<bean:write name="d" property="id"/>);">DELETE</a>&nbsp;]</span></logic:notEmpty></p>
      
    </logic:iterate>
    </div>
    


    <div class="attention" style="margin-top: 30px;">The Juniata County Fairgrounds is also home to the
    <a href="http://www.portroyalspeedway.com">Port Royal
    Speedway</a>. Be sure to check out the Speedway site!</div>

    </div> <!-- end content -->

</div> <!-- end page -->

<div id="footer">

  <p>This page has been accessed <%= request.getAttribute("hits") %> times.</p>
  
  <p>All content on this site is Copyright &#169; 2007-2011 Juniata County Agricultural Society.</p>
  
</div> <!-- end footer -->

<div id="fb-root"></div>
<script>

  window.fbAsyncInit = function() {
    FB.init({appId: '107019806018618', status: true, cookie: true, xfbml: true});
    FB.Event.subscribe('auth.login', function(response) {
        //doFacebookLogin();
      });
  };
  (function() {
    var e = document.createElement('script'); e.async = true;
    e.src = document.location.protocol +
      '//connect.facebook.net/en_US/all.js';
    document.getElementById('fb-root').appendChild(e);
  }());

</script>

</body></html>