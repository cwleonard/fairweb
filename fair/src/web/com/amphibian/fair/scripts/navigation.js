
img1on = new Image(); img1on.src = "images/schedule_color.gif";
img2on = new Image(); img2on.src = "images/travel_color.gif";
img3on = new Image(); img3on.src = "images/premiums_color.gif";
img4on = new Image(); img4on.src = "images/press_color.gif";
img5on = new Image(); img5on.src = "images/food_color.gif";
img6on = new Image(); img6on.src = "images/comments_color.gif";
img7on = new Image(); img7on.src = "images/home_color.gif";
img8on = new Image(); img8on.src = "images/map_color.gif";

img1off = new Image(); img1off.src = "images/schedule_bw.gif";
img2off = new Image(); img2off.src = "images/travel_bw.gif";
img3off = new Image(); img3off.src = "images/premiums_bw.gif";
img4off = new Image(); img4off.src = "images/press_bw.gif";
img5off = new Image(); img5off.src = "images/food_bw.gif";
img6off = new Image(); img6off.src = "images/comments_bw.gif";
img7off = new Image(); img7off.src = "images/home_bw.gif";
img8off = new Image(); img8off.src = "images/map_bw.gif";


function rollIn(imgName) {
    if (document.images) {
        document[imgName].src = eval(imgName + "on.src");
    }
}

function rollOut(imgName) {  // the normal onmouseout function
    if (document.images){
        document[imgName].src = eval(imgName + "off.src");
    }
}
