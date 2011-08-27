<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Map of Port Royal</title>
    <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAKLj9ROU3S3SQpMsSFDnAORQBHgtmRlCRAKKN6T59Vo_arR9WoxTy8Jhaef-cB4RW8xtVzwI4XwYZLQ"
      type="text/javascript"></script>
    <script type="text/javascript">

    //<![CDATA[

function load() {

  if (GBrowserIsCompatible()) {
  
      var map = new GMap2(document.getElementById("map"));

      map.addControl(new GLargeMapControl());
      map.addControl(new GMapTypeControl());

      GEvent.addListener(map, "moveend", function() {
         var center = map.getCenter();
         document.getElementById("message").innerHTML = center.toString();
         });

      map.setCenter(new GLatLng(40.5332, -77.38915), 13);
 
     var geocoder = new GClientGeocoder();
     geocoder.getLatLng("17082", testMethod);

  }
  
}


function testMethod(point) {

   GLog.write(point);

}

function createMarker(point) {

  var opts = new Object();
  opts.title = "Juniata County Fair";
  opts.draggable = true;

  var marker = new GMarker(point, opts);
  GEvent.addListener(marker, "click", function() {
    marker.openInfoWindowHtml("Juniata County Fair");
  });

  document.getElementById("map").addOverlay(marker);
}

    //]]>
    </script>
  </head>
  <body onload="load()" onunload="GUnload()">
    <div id="map" style="width: 600px; height: 500px"></div>

<p id="message"></p>

  </body>
</html>