<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fair-tags.tld" prefix="fair" %>

<%

String thisPage = (String)request.getAttribute("thisPage");

%>

<div align="center">
  <center>

<fair:pipebox width="600">

<% if ("home".equalsIgnoreCase(thisPage)) { %>
<html:img alt="" page="/images/home_color.gif" border="0" height="60" width="65" />
<% } else { %>
<a class="nb" id="home" href="home.do"><html:img alt="" page="/images/transpixel.gif" /></a>
<% } %>

<% if ("schedule".equalsIgnoreCase(thisPage)) { %>
<html:img alt="" page="/images/schedule_color.gif" border="0" height="60" width="65" />
<% } else { %>
<a class="nb" id="schedule" href="schedule.do"><html:img alt="" page="/images/transpixel.gif" /></a>
<% } %>

<% if ("travel".equalsIgnoreCase(thisPage)) { %>
<html:img alt="" page="/images/travel_color.gif" border="0" height="60" width="65" />
<% } else { %>
<a class="nb" id="travel" href="travel.do"><html:img alt="" page="/images/transpixel.gif" /></a>
<% } %>

<% if ("premiums".equalsIgnoreCase(thisPage)) { %>
<html:img alt="" page="/images/premiums_color.gif" border="0" height="60" width="65" />
<% } else { %>
<a class="nb" id="premiums" href="premiums.do"><html:img alt="" page="/images/transpixel.gif" /></a>
<% } %>

<% if ("press".equalsIgnoreCase(thisPage)) { %>
<html:img alt="" page="/images/press_color.gif" border="0" height="60" width="65" />
<% } else { %>
<a class="nb" id="press" href="#"><html:img alt="" page="/images/transpixel.gif" /></a>
<% } %>

<% if ("food".equalsIgnoreCase(thisPage)) { %>
<html:img alt="" page="/images/food_color.gif" border="0" height="60" width="65" />
<% } else { %>
<a class="nb" id="food" href="#"><html:img alt="" page="/images/transpixel.gif" /></a>
<% } %>

<% if ("comments".equalsIgnoreCase(thisPage)) { %>
<html:img alt="" page="/images/comments_color.gif" border="0" height="60" width="65" />
<% } else { %>
<a class="nb" id="comments" href="#"><html:img alt="" page="/images/transpixel.gif" /></a>
<% } %>

<% if ("map".equalsIgnoreCase(thisPage)) { %>
<html:img alt="" page="/images/map_color.gif" border="0" height="60" width="65" />
<% } else { %>
<a class="nb" id="map" href="#"><html:img alt="" page="/images/transpixel.gif" /></a>
<% } %>

<br>
<html:img alt="" page="/images/home.gif" border="0" height="10" width="65" />
<html:img alt="" page="/images/schedule.gif" border="0" height="10" width="65" />
<html:img alt="" page="/images/travel.gif" border="0" height="10" width="65" />
<html:img alt="" page="/images/premiums.gif" border="0" height="10" width="65" />
<html:img alt="" page="/images/press.gif" border="0" height="10" width="65" />
<html:img alt="" page="/images/food.gif" border="0" height="10" width="65" />
<html:img alt="" page="/images/comments.gif" border="0" height="10" width="65" />
<html:img alt="" page="/images/map.gif" border="0" height="10" width="65" />
      
</fair:pipebox>      

  </center>
</div>
