<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fair-tags.tld" prefix="fair" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
  <title>Juniata County Fair - Premiums</title>
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

<% boolean allowEntry = ((Boolean)request.getAttribute("allowEntry")).booleanValue(); %>
<% boolean ended = ((Boolean)request.getAttribute("ended")).booleanValue(); %>

<fair:header navTab="Premiums" />

<div id="page">

  <div id="sidebar">

    <fair:personal />
    
  </div> <!-- end sidebar -->

  <div id="content">

    <logic:notEmpty name="depts">

      <h4><%= request.getAttribute("premiumsYear") %> Premiums</h4>

<% if (!allowEntry && ended) { %>

      <div class="alert">
        <p><b>NOTICE: Online pre-registration has ended for this year. New
        entries or changes to your entries must be made by an agent of the fair,
        and can be made at the fairgrounds the week of August 28. Please contact
        <a href="mailto:jcfair@countryilink.net">webmaster for more information.</a></b></p>
      </div>

<p><b>All items entered, <i>except for entries in Deptartment 19, Sections  1A and 1B</i>,
must be brought to the fairgrounds on Friday, September 2nd
between the hours of 10 a.m. and 8 p.m.<br><br>Items in Department 19, Sections 1A and 1B
must be at the fairgrounds on Thursday, September 1st between 3 p.m. and 8 p.m.</b></p>

<% } else if (!allowEntry && !ended) { %>

      <div class="alert">
        <p><b>NOTICE: Online pre-registration is not yet available for this year. 
        Check back often, as the new premiums registration will be online soon! Please contact
        <a href="mailto:jcfair@countryilink.net">webmaster for more information.</a></b></p>
      </div>

<% } else { %>

<p><b>All items entered, <i>except for entries in Deptartment 19, Sections  1A and 1B</i>,
must be brought to the fairgrounds on Friday, September 2nd
between the hours of 10 a.m. and 8 p.m.<br><br>Items in Department 19, Sections 1A and 1B
must be at the fairgrounds on Thursday, September 1st between 3 p.m. and 8 p.m.</b></p>

<% } %>

<p>You can browse the departments to see what classes exist and the prizes
associated with them.</p>

      <h4>Departments</h4>

      <logic:iterate id="dept" name="depts">
        <p><a href="premiums.do?dept=<bean:write format="#" name="dept" property="id"/>">Department <bean:write name="dept" property="number"/> - <bean:write name="dept" property="description"/></a></p>
      </logic:iterate>

    </logic:notEmpty>

    <logic:notEmpty name="sects">
    
      <h3>Department <bean:write name="dept" property="number"/></h3>

      <div class="breadcrumbs"><p><b><a href="premiums.do">All Departments</a></b> &gt; <b><bean:write name="dept" property="description"/></b></p></div>

      <logic:notEmpty name="dept" property="rules">

        <h4>Department Rules</h4>

        <fair:rulesWrite name="dept" property="rules"/>

      </logic:notEmpty>

      <h4>Sections</h4>

      <logic:iterate id="sect" name="sects">
        <p><a href="premiums.do?sect=<bean:write format="#" name="sect" property="id"/>">Section <bean:write name="sect" property="number"/> - <bean:write name="sect" property="description"/></a></p>
      </logic:iterate>

    </logic:notEmpty>

    <logic:notEmpty name="classes">

      <h3>Department <bean:write name="dept" property="number"/>, Section <bean:write name="sect" property="number"/></h3>

      <div class="breadcrumbs"><p><b><a href="premiums.do">All Departments</a></b> &gt;
      <b><a href="premiums.do?dept=<bean:write format="#" name="dept" property="id"/>"><bean:write name="dept" property="description"/></a></b> &gt;
      <b><bean:write name="sect" property="description"/></b></p></div>

      <logic:notEmpty name="sect" property="rules">

        <h4>Section Rules</h4>

        <fair:rulesWrite name="sect" property="rules"/>

      </logic:notEmpty>

      <h4>Classes</h4>

      <logic:iterate id="pClass" name="classes">

        <fair:classDisplay premiumsClass="<%= pClass %>" />

      </logic:iterate>

    </logic:notEmpty>

  </div> <!-- end content -->

</div> <!-- end page -->

<div id="footer">

  <p>This page has been accessed <%= request.getAttribute("hits") %> times.</p>
  
  <p>All content on this site is Copyright &#169; 2007-2010 Juniata County Agricultural Society.</p>
  
</div> <!-- end footer -->

</body></html>