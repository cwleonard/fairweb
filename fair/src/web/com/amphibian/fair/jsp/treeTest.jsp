<html>

  <head>

    <link rel="stylesheet" type="text/css" media="screen" href="css/tree.css" />

    <script language="javascript">

      var currentlySelected;
      var currentlySelectedLeaf;

      function leafClick(leaf) {

        //alert("clicked on leaf \"" + leaf.innerHTML + "\"");
        markNodeUnselected(currentlySelectedLeaf);
        markNodeSelected(leaf);
        currentlySelectedLeaf = leaf;

      }

      function removeClass(obj, className) {

        if (!obj || !obj.className) {
          return;
        }

        var classes = obj.className.split(" ");
        var newClasses = "";
        for (var i = 0; i < classes.length; i++) {
          if (classes[i] != className) {
            if (newClasses.length > 0) {
              newClasses += " ";
            }
            newClasses += classes[i];
          }
        }

        obj.className = newClasses;

      }

      function branchClick(branch) {

        var node = branch.parentNode;

        if (node.className.indexOf("branchOpen") != -1) {
          node.className = "branchClosed";
        } else {
          node.className = "branchOpen";
        }
        
        if (currentlySelected != node) {
          markNodeUnselected(currentlySelected);
        }

        markNodeSelected(branch);

        currentlySelected = branch;

      }

      function markNodeUnselected(node) {

        if (!node) return;
        node.style.backgroundColor = "#FFFFFF";
        //node.style.fontWeight = "normal";

      }

      function markNodeSelected(node) {

        if (!node) return;
        node.style.backgroundColor = "#EEEEEE";
        //node.style.fontWeight = "bold";

      }

      function markNodeError(node) {

        if (!node) return;
        node.style.color = "#FF0000";

      }

      function setError() {

        if (!currentlySelected) return;

        currentlySelectedLeaf.setAttribute("state", "error");

      }


      function addNode() {

        if (!currentlySelected) return;

        var addTo = currentlySelected.parentNode;

        var nd = document.getElementById("newDesc");

        var children = addTo.childNodes;

        var found = false;
        var n;

        for (var i = 0; i < children.length; i++) {
          n = children[i];
          if (n.tagName == 'UL') {
            found = true;
            break;
          }
        }

        if (!found) {
          // need to add UL element
          n = document.createElement("ul");
          addTo.appendChild(n);
        }

        var newNode = document.createElement("li");
        newNode.className = "leaf";

        var newDesc = document.createElement("span");
        newDesc.className = "desc";
        newDesc.innerHTML = nd.value;
        newDesc.setAttribute("onclick", "leafClick(this);");

        newNode.appendChild(newDesc);
        n.appendChild(newNode);

        // stupid IE workaround...
        n.innerHTML = n.innerHTML;

        nd.value = "";
            
      }

      function addBranch() {

        if (!currentlySelected) return;

        var addTo = currentlySelected.parentNode;

        var nd = document.getElementById("newBranchDesc");

        var children = addTo.childNodes;

        var found = false;
        var n;

        for (var i = 0; i < children.length; i++) {
          n = children[i];
          if (n.tagName == 'UL') {
            found = true;
            break;
          }
        }

        if (!found) {
          // need to add UL element
          n = document.createElement("ul");
          addTo.appendChild(n);
        }

        var newNode = document.createElement("li");
        newNode.className = "branchClosed";

        var newDesc = document.createElement("span");
        newDesc.className = "desc";
        newDesc.innerHTML = nd.value;
        newDesc.setAttribute("onclick", "branchClick(this);");

        newNode.appendChild(newDesc);
        n.appendChild(newNode);

        // stupid IE workaround...
        n.innerHTML = n.innerHTML;

        nd.value = "";

      }


    </script>

  </head>

  <body>

    This is a test of a tree.
    
    <div id="treeRoot">

    <ul>
      <li class="branchClosed"><span class="desc" onclick="branchClick(this);">Item Number 1</span></li>
      <li class="branchOpen"><span class="desc" onclick="branchClick(this);">Item Number 2</span>
        <ul>
          <li class="leaf"><span class="desc" onclick="leafClick(this);">Item Number 2.1</span></li>
          <li id="addHere" class="branchClosed"><span class="desc" onclick="branchClick(this);">Item Number 2.2</span>
            <ul>
              <li class="leaf"><span class="desc" onclick="leafClick(this);">Item Number 2.2.1</span></li>
              <li class="leaf"><span class="desc" onclick="leafClick(this);">Item Number 2.2.2</span></li>
            </ul>
          </li>
          <li class="leaf"><span class="desc" onclick="leafClick(this);">Item Number 2.3</span></li>
        </ul>
      </li>
      <li class="branchClosed"><span class="desc" onclick="branchClick(this);">Item Number 3</span></li>
    </ul>

    </div>

    <div style="margin: 5px; padding: 10px; border: solid 1px">
      <form>
        <a href="javascript:addNode();">Click to add a leaf</a>
        <p>Description: <input type="text" id="newDesc"></p>
      </form>
    </div>

    <div style="margin: 5px; padding: 10px; border: solid 1px">
      <form>
        <a href="javascript:addBranch();">Click to add a branch</a>
        <p>Description: <input type="text" id="newBranchDesc"></p>
      </form>
    </div>

    <div style="margin: 5px; padding: 10px; border: solid 1px">
      <form>
        <a href="javascript:setError();">Click to set error state</a>
      </form>
    </div>

  </body>

</html>