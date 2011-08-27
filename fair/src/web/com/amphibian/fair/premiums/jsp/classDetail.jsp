<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fair-tags.tld" prefix="fair" %>

<html:xhtml/>

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

  function doFBPost() {
  
  FB.ui(
   {
     method: 'stream.publish',
     message: 'I entered my <bean:write name="class" property="description"/> ' +
              'in the Juniata County Fair!',
     attachment: {
       name: 'Juniata County Fair Premiums Registration',
       description: (
         'Enter your homemade items and agricultural products online ' +
         'for competition at the Juniata County Fair!'
       ),
       href: 'http://www.juniatacountyfair.com/premiums.do'
     },
     action_links: [
       { text: 'Register', href: 'http://www.juniatacountyfair.com/premiums.do' }
     ],
     user_message_prompt: 'Share your thoughts about entering <bean:write name="class" property="description"/>'
   },
   function(response) {
     if (response && response.post_id) {
       //alert('Post was published.');
     } else {
       //alert('Post was not published.');
     }
   }
 );
  
  }

  </script>
</head>

<body>

<% boolean allowEntry = ((Boolean)request.getAttribute("allowEntry")).booleanValue(); %>

<fair:header navTab="Premiums" />

<div id="page">

  <div id="sidebar">

    <fair:personal />
    
  </div> <!-- end sidebar -->

  <div id="content">

    <html:form action="saveEntry">
    <input type="hidden" name="entryId" value="<bean:write name="class" property="code" />" />

      <h3>Department <bean:write name="dept" property="number"/>, Section <bean:write name="sect" property="number"/>, Class <bean:write name="class" property="shortCode"/></h3>

      <div class="breadcrumbs">
        <p>
          <b><a href="premiums.do">All Departments</a></b> &gt;
          <b><a href="premiums.do?dept=<bean:write format="#" name="dept" property="id"/>"><bean:write name="dept" property="description"/></a></b> &gt;
          <b><a href="premiums.do?sect=<bean:write format="#" name="sect" property="id"/>"><bean:write name="sect" property="description"/></a></b> &gt;
          <logic:iterate id="s" name="stuff">
            <b><a href="premiums.do?sect=<bean:write format="#" name="sect" property="id"/>#class<bean:write name="s" property="shortCode"/>"><bean:write name="s" property="description"/></a></b> &gt;
          </logic:iterate>
          <b><bean:write name="class" property="description"/></b>
        </p>
      </div>

      <logic:notEmpty name="class" property="rules">
        <h4>Class Rules</h4>
        <fair:rulesWrite name="class" property="rules"/>
      </logic:notEmpty>

      <h4>Prizes Awarded</h4>

      <div id="prizes">
        <logic:iterate id="p" name="class" property="prizes">
          <p><%= p %></p>
        </logic:iterate>
        <p><bean:write name="class" property="honorableMention"/></p>
        <logic:iterate id="ep" name="class" property="extraPrizes">
          <p><%= ep %></p>
        </logic:iterate>
      </div>


<logic:empty name="NoDirectEntry">

<logic:empty name="UserObj" scope="session">
<% if (allowEntry) { %>
<p>Please log in to enter something in this class.</p>
<% } %>
</logic:empty>

<logic:notEmpty name="UserObj" scope="session">

<p>You currently have <%= request.getAttribute("eInfo") %> in this class...</p>

<logic:notEmpty name="yourEntries">

<a href="javascript:doFBPost();"><img title="Share on Facebook" style="border: 0;" src="/images/facebook_icon.png"/></a>

<table>
<logic:iterate indexId="ei" id="e" name="yourEntries">

<logic:empty name="livestock">

<input type="hidden" name="id" value="<bean:write name="e" property="id"/>"/>
<input type="hidden" name="DOB" value="<bean:write name="e" property="DOB"/>" />
<input type="hidden" name="animalName" value="<bean:write name="e" property="animalName"/>" />
<input type="hidden" name="sire" value="<bean:write name="e" property="sire"/>" />
<input type="hidden" name="dam" value="<bean:write name="e" property="dam"/>" />
<input type="hidden" name="breeder" value="<bean:write name="e" property="breeder"/>" />

  <tr>
    <td><%= (((Integer)ei).intValue())+1 %>.</td>
    <td><input type="text" name="description" size="30" value="<bean:write name="e" property="description"/>"/><br />Description (optional)</td>
    <td><% if (allowEntry) { %>
      <a href="deleteEntry.do?id=<bean:write name="e" property="id"/>">DELETE</a>
      <% } else { %>
      &nbsp;
      <% } %>
    </td>
  </tr>

</logic:empty>

<logic:notEmpty name="livestock">

<input type="hidden" name="id" value="<bean:write name="e" property="id"/>"/>
<input type="hidden" name="description" value="<bean:write name="e" property="description"/>"/>

  <tr>
    <td><%= (((Integer)ei).intValue())+1 %>.</td>
    <td>
    
<p><input type="text" name="DOB" value="<bean:write name="e" property="DOB"/>" /><br />Animal DOB</p>
<p><input type="text" name="animalName" value="<bean:write name="e" property="animalName"/>" /><br />Animal Name</p>
<p><input type="text" name="sire" value="<bean:write name="e" property="sire"/>" /><br />Sire</p>
<p><input type="text" name="dam" value="<bean:write name="e" property="dam"/>" /><br />Dam</p>
<p><input type="text" name="breeder" value="<bean:write name="e" property="breeder"/>" /><br />Breeder</p>
    
    </td>
    <td><% if (allowEntry) { %>
      <a href="deleteEntry.do?id=<bean:write name="e" property="id"/>">DELETE</a>
      <% } else { %>
      &nbsp;
      <% } %>
    </td>
  </tr>

</logic:notEmpty>

  <tr>
    <td colspan="3"><hr /></td>
  </tr>

</logic:iterate>
</table>

</logic:notEmpty>

<logic:notEmpty name="showBlank">

<% if (allowEntry) { %>

<p>Add an entry by filling out the following information...</p>

<logic:empty name="livestock">

<input type="hidden" name="id" />
<input type="hidden" name="DOB" />
<input type="hidden" name="animalName" />
<input type="hidden" name="sire" />
<input type="hidden" name="dam" />
<input type="hidden" name="breeder" />

<p><input type="text" name="description" size="45" /><br />Description (optional)</p>

</logic:empty>

<logic:notEmpty name="livestock">

<input type="hidden" name="id" />
<input type="hidden" name="description" />

<p><input type="text" name="DOB" /><br />Animal DOB</p>
<p><input type="text" name="animalName" /><br />Animal Name</p>
<p><input type="text" name="sire" /><br />Sire</p>
<p><input type="text" name="dam" /><br />Dam</p>
<p><input type="text" name="breeder" /><br />Breeder</p>

</logic:notEmpty>

<% } %>

</logic:notEmpty>

<% if (allowEntry) { %>

<p><html:submit value="Add/Update Entry" /></p>

<% } %>

</logic:notEmpty>

</logic:empty>

      <logic:notEmpty name="NoDirectEntry">
      <p>You cannot enter this class online.</p>
      </logic:notEmpty>

    </html:form>

  </div> <!-- end content -->
  
</div> <!-- end page -->

<div id="footer">

  <p>This page has been accessed <%= request.getAttribute("hits") %> times.</p>
  
  <p>All content on this site is Copyright &#169; 2007 Juniata County Agricultural Society.</p>
  
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

</body>
</html>