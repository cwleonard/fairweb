<%

    HttpServletRequest req = (HttpServletRequest)request;
    String contextRoot = req.getContextPath();

    response.setContentType("text/css");

%>

	td.top {background-image: url("<%= contextRoot %>/images/top.gif");}
	td.lmiddle {background-image: url("<%= contextRoot %>/images/middle_left.gif");}
	td.rmiddle {background-image: url("<%= contextRoot %>/images/middle_right.gif");}
	td.bottom {background-image: url("<%= contextRoot %>/images/bottom.gif");}

	

	a#home img {
		background: url(<%= contextRoot %>/images/home_bw.gif) top left no-repeat;
	}
	
	a#home:hover img {
		background: url(<%= contextRoot %>/images/home_color.gif) top left no-repeat;
	}
	
	a#schedule img {
		background: url(<%= contextRoot %>/images/schedule_bw.gif) top left no-repeat;
	}
	
	a#schedule:hover img {
		background: url(<%= contextRoot %>/images/schedule_color.gif) top left no-repeat;
	}

	a#travel img {
		background: url(<%= contextRoot %>/images/travel_bw.gif) top left no-repeat;
	}
	
	a#travel:hover img {
		background: url(<%= contextRoot %>/images/travel_color.gif) top left no-repeat;
	}
	
	a#premiums img {
		background: url(<%= contextRoot %>/images/premiums_bw.gif) top left no-repeat;
	}
	
	a#premiums:hover img {
		background: url(<%= contextRoot %>/images/premiums_color.gif) top left no-repeat;
	}
	
	a#press img {
		background: url(<%= contextRoot %>/images/press_bw.gif) top left no-repeat;
	}
	
	a#press:hover img {
		background: url(<%= contextRoot %>/images/press_color.gif) top left no-repeat;
	}
	
	a#food img {
		background: url(<%= contextRoot %>/images/food_bw.gif) top left no-repeat;
	}
	
	a#food:hover img {
		background: url(<%= contextRoot %>/images/food_color.gif) top left no-repeat;
	}
	
	a#comments img {
		background: url(<%= contextRoot %>/images/comments_bw.gif) top left no-repeat;
	}
	
	a#comments:hover img {
		background: url(<%= contextRoot %>/images/comments_color.gif) top left no-repeat;
	}
	
	a#map img {
		background: url(<%= contextRoot %>/images/map_bw.gif) top left no-repeat;
	}
	
	a#map:hover img {
		background: url(<%= contextRoot %>/images/map_color.gif) top left no-repeat;
	}
	
	
		