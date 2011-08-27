<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fair-tags.tld" prefix="fair" %>

<html:xhtml/>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
  <title>Juniata County Fair - Travel Info</title>
  <link rel="stylesheet" type="text/css" media="screen" href="css/normal.css">
  <link rel="stylesheet" type="text/css" media="screen" href="css/navbar.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="css/css_urls.jsp" />
</head>

<body>

<% request.setAttribute("thisPage", "travel"); %>

<jsp:include page="header.jsp" />

<jsp:include page="navigation.jsp" />

<div align="center">
  <center>
  <table border="0" cellpadding="0" cellspacing="0" width="600">
    <tbody><tr>
      <td valign="top" width="300">
      
<div align="center">
  <center>


<fair:pipebox width="300" title="directions">

<p>The Juniata County Fair is located in Port Royal, along PA Route 75.</p>

<p>How to get here:</p>

<p>...from <b>Harrisburg</b> Area: Take Rt 22-322 West to the Port Royal 
Exit.&nbsp; After Exiting you will come to a 4-way intersection. Go straight onto Rt 
75 south to town of Port Royal.&nbsp; Watch for signs directing you to the 
fairgrounds along Rt 75.&nbsp; From Harrisburg area to Port Royal takes approximately 
1 hour of travel time.</p>

<p>...from <b>State College</b> Area:&nbsp;&nbsp; Take Rt 
22-322 East to the Port Royal Exit.&nbsp; After Exiting you will come to a stop sign 
- turn right.&nbsp; Travel a short distance to 4-way intersection. Go straight onto 
Rt 75 south to town of Port Royal.&nbsp; Watch for signs directing you to the 
fairgrounds along Rt 75.&nbsp; From&nbsp;State College area to Port Royal takes 
approximately 1 hour of travel time.</p>

<p>...from <b>Chambersburg</b> Area: Take Route 81 north to Harrisburg, then take
Route 22/322 West to the Port Royal exit. After the exit, you will come to
a 4-way intersection. Go straight onto 
Rt 75 south to town of Port Royal.&nbsp; Watch for signs directing you to the 
fairgrounds. From Chambersburg to Port Royal takes about&nbsp;1.5 hours of travel time.</p>
      
</fair:pipebox>
      

  </center>
</div>
      
      
      </td>
      <td valign="top" width="300">
      
      
<div align="center">
  <center>
      

<fair:pipebox width="300" title="lodging">

<p>Need someplace to stay while visiting the Fair? Port Royal and the
surrounding areas offer a number of lodging options...</p>

<p><b>Econo Lodge</b>, Mifflintown<br>717-436-5981</p>

<p><b>Tuscarora Motor Inn</b>, Mifflintown<br>717-436-2127</p>

<p><b>Inn at McCullochs Mills</b>, Honey Grove<br>717-734-3628</p>

<p><b>Barb's B &amp; B</b>, Mifflintown<br>717-463-3207</p>

<p><b>Watchbox Hollow B &amp; B</b>, Port Royal<br>717-535-4596</p>

</fair:pipebox>      

  </center>
</div>


<div align="center">
  <center>


<fair:pipebox width="300">
      
<a href="http://maps.google.com/maps?f=q&hl=en&geocode=&q=juniata+county+fair&sll=40.529197,-77.37834&sspn=0.032358,0.058365&ie=UTF8&ll=40.53572,-77.387094&spn=0.032355,0.058365&z=14&iwloc=A&om=1">
Map Location with Google Maps</a>

</fair:pipebox>


  </center>
</div>

    
      
      </td>
    </tr>
  </tbody></table>
  </center>
</div>


<p>&nbsp;</p>

</body></html>