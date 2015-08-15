<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>katate club network</title>
<script src="${pageContext.request.contextPath }/js/go.js"></script>

<script id="code">
function WheelLayout() {
    go.CircularLayout.call(this);
  }
  go.Diagram.inherit(WheelLayout, go.CircularLayout);

  // override makeNetwork to set the diameter of each node and ignore the TextBlock label
  WheelLayout.prototype.makeNetwork = function(coll) {
    var net = go.CircularLayout.prototype.makeNetwork.call(this, coll);
    net.vertexes.each(function(cv) {
      cv.diameter = 35;  // because our desiredSize for nodes is (20, 20)
    });
    return net;
  }

  // override commitNodes to rotate nodes so the text goes away from the center,
  // and flip text if it would be upside-down
  WheelLayout.prototype.commitNodes = function() {
    go.CircularLayout.prototype.commitNodes.call(this);
    this.network.vertexes.each(function(v) {
      var node = v.node;
      if (node === null) return;
      // get the angle of the node towards the center, and rotate it accordingly 顶点的角度是朝向中心的
      var a = v.actualAngle;
      if (a > 90 && a < 270) {  // make sure the text isn't upside down
        var textBlock = node.findObject("TEXTBLOCK");
        textBlock.angle = 180;
      }
      node.angle = a;
    });
  };
  // end WheelLayout class


  var highlightColor = "red";  // color parameterization

  function init() {
    if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this
    var $ = go.GraphObject.make;  // for conciseness in defining templates

    myDiagram =
      $(go.Diagram, "myDiagram", // must be the ID or reference to div
        {
          //autoScale: go.Diagram.Uniform,
          padding: 10,
          contentAlignment: go.Spot.Center,
          layout:
            $(WheelLayout,  // set up a custom CircularLayout
              // set some properties appropriate for this sample
              {
                arrangement: go.CircularLayout.ConstantDistance,
                nodeDiameterFormula: go.CircularLayout.Circular,
                spacing: 10,
                aspectRatio: 0.7,
                sorting: go.CircularLayout.Optimized
              }),
          isReadOnly: true,
          click: function(e) {  // background click clears any remaining highlighteds
            e.diagram.startTransaction("clear");
            e.diagram.clearHighlighteds();
            e.diagram.commitTransaction("clear");
          }
        });

    // define the Node template
    myDiagram.nodeTemplate =
      $(go.Node, "Horizontal",
        {
          selectionAdorned: false,
          locationSpot: go.Spot.Center,  // Node.location is the center of the Shape
          locationObjectName: "SHAPE",
          mouseEnter: function(e, node) {
            node.diagram.clearHighlighteds();
            node.linksConnected.each(function(l) { highlightLink(l, true); });
            node.isHighlighted = true;
            var tb = node.findObject("TEXTBLOCK");
            if (tb !== null) tb.stroke = highlightColor;
          },
          mouseLeave: function(e, node) {
            node.diagram.clearHighlighteds();
            var tb = node.findObject("TEXTBLOCK");
            if (tb !== null) tb.stroke = "black";
          }
        },
        new go.Binding("text", "text"),  // for sorting the nodes
        $(go.Shape, "Ellipse",
          {
            name: "SHAPE",
            fill: "lightgray",  // default value, but also data-bound
            stroke: "transparent",  // modified by highlighting
            strokeWidth: 2,
            desiredSize: new go.Size(20, 20),
            portId: ""
          },  // so links will go to the shape, not the whole node
          new go.Binding("fill", "color"),
          new go.Binding("stroke", "isHighlighted",
                         function(h) { return h ? highlightColor : "transparent"; })
                        .ofObject()),
        $(go.TextBlock,
          { name: "TEXTBLOCK" },  // for search
          new go.Binding("text", "text"))
      );

    function highlightLink(link, show) {
      link.isHighlighted = show;
      link.fromNode.isHighlighted = show;
      link.toNode.isHighlighted = show;
    }

    // define the Link template
    myDiagram.linkTemplate =
      $(go.Link,
        {
          routing: go.Link.Normal,
          curve: go.Link.Bezier,
          selectionAdorned: false,
          mouseEnter: function(e, link) { highlightLink(link, true); },
          mouseLeave: function(e, link) { highlightLink(link, false); }
        },
        $(go.Shape,
          new go.Binding("stroke", "isHighlighted",
                         function(h, shape) { return h ? highlightColor : shape.part.data.color; })
                        .ofObject(),
          new go.Binding("strokeWidth", "isHighlighted",
                         function(h) { return h ? 2 : 1; })
                        .ofObject())
      );

    generateGraph();
  }
  function generateGraph() {
	    var names = [
	      "n1", "n2", "n3", "n4", "n5",
	      "n6", "n7", "n8", "n9", "n10",
	      "n11", "n12", "n13", "n14", "n15",
	      "n16", "n17", "n18", "n19", "n20",
	      "n21", "n22", "n23", "n24", "n25",
	      "n26", "n27", "n28", "n29", "n30",
	      "n31", "n32", "n33", "n34", 
	    ];
	    var nodeDataArray = [];
	    for (var i = 0; i < names.length; i++) {
	      nodeDataArray.push({ key: i, text: names[i], color: go.Brush.randomColor(0, 255) });
	    }
	    var linkDataArray = [];
	    //1与节点2 3 4 5 6 7 8 9 11 12 13 14 18 20 22 32 
	        linkDataArray.push({ from: 0, to: 1, color: go.Brush.randomColor(0, 126) });
	        linkDataArray.push({ from: 0, to: 2, color: go.Brush.randomColor(0, 126) });
	        linkDataArray.push({ from: 0, to: 3, color: go.Brush.randomColor(0, 126) });
	        linkDataArray.push({ from: 0, to: 4, color: go.Brush.randomColor(0, 126) });
	        linkDataArray.push({ from: 0, to: 5, color: go.Brush.randomColor(0, 126) });
	        linkDataArray.push({ from: 0, to: 6, color: go.Brush.randomColor(0, 126) });
	        linkDataArray.push({ from: 0, to: 7, color: go.Brush.randomColor(0, 126) });
	        linkDataArray.push({ from: 0, to: 8, color: go.Brush.randomColor(0, 126) });
	        linkDataArray.push({ from: 0, to: 10, color: go.Brush.randomColor(0, 126) });
	        linkDataArray.push({ from: 0, to: 11, color: go.Brush.randomColor(0, 126) });
	        linkDataArray.push({ from: 0, to: 12, color: go.Brush.randomColor(0, 126) });
	        linkDataArray.push({ from: 0, to: 13, color: go.Brush.randomColor(0, 126) });
	        linkDataArray.push({ from: 0, to: 17, color: go.Brush.randomColor(0, 126) });
	        linkDataArray.push({ from: 0, to: 19, color: go.Brush.randomColor(0, 126) });
	        linkDataArray.push({ from: 0, to: 21, color: go.Brush.randomColor(0, 126) });
	        linkDataArray.push({ from: 0, to: 31, color: go.Brush.randomColor(0, 126) });
	    //2与节点3 4 8 14 18 20 22 31
	    	linkDataArray.push({ from: 1, to: 2, color: go.Brush.randomColor(0, 139) });
	    	linkDataArray.push({ from: 1, to: 3, color: go.Brush.randomColor(0, 139) });
	    	linkDataArray.push({ from: 1, to: 7, color: go.Brush.randomColor(0, 139) });
	    	linkDataArray.push({ from: 1, to: 13, color: go.Brush.randomColor(0, 139) });
	    	linkDataArray.push({ from: 1, to: 17, color: go.Brush.randomColor(0, 139) });
	    	linkDataArray.push({ from: 1, to: 19, color: go.Brush.randomColor(0, 139) });
	    	linkDataArray.push({ from: 1, to: 21, color: go.Brush.randomColor(0, 139) });
	    	linkDataArray.push({ from: 1, to: 30, color: go.Brush.randomColor(0, 139) });
	    //3与节点4 8 9 10 14 28 29 33
	    	linkDataArray.push({ from: 2, to: 3, color: go.Brush.randomColor(0, 128) });
	    	linkDataArray.push({ from: 2, to: 7, color: go.Brush.randomColor(0, 128) });
	    	linkDataArray.push({ from: 2, to: 9, color: go.Brush.randomColor(0, 128) });
	    	linkDataArray.push({ from: 2, to: 13, color: go.Brush.randomColor(0, 128) });
	    	linkDataArray.push({ from: 2, to: 27, color: go.Brush.randomColor(0, 128) });
	    	linkDataArray.push({ from: 2, to: 28, color: go.Brush.randomColor(0, 128) });
	    	linkDataArray.push({ from: 2, to: 32, color: go.Brush.randomColor(0, 128) });
	    //4与节点 8 13 14 
	    	linkDataArray.push({ from: 3, to: 7, color: go.Brush.randomColor(0, 56) });
	    	linkDataArray.push({ from: 3, to: 12, color: go.Brush.randomColor(0, 56) });
	    	linkDataArray.push({ from: 3, to: 13, color: go.Brush.randomColor(0, 56) });
	    	//5与节点 7 11 
	    	linkDataArray.push({ from: 4, to: 6, color: go.Brush.randomColor(0, 167) });
	    	linkDataArray.push({ from: 4, to: 10, color: go.Brush.randomColor(0, 167) });
	    	// 6与节点7 11 17
	    	linkDataArray.push({ from: 5, to: 6, color: go.Brush.randomColor(0, 156) });
	    	linkDataArray.push({ from: 5, to: 10, color: go.Brush.randomColor(0, 156) });
	    	linkDataArray.push({ from: 5, to: 16, color: go.Brush.randomColor(0, 156) });
	    	// 7与节点17
	    	linkDataArray.push({ from: 6, to: 16, color: go.Brush.randomColor(0, 16) });
	    	//9与节点31 33 34
	    	linkDataArray.push({ from: 8, to: 30, color: go.Brush.randomColor(0, 66) });
	    	linkDataArray.push({ from: 8, to: 32, color: go.Brush.randomColor(0, 66) });
	    	linkDataArray.push({ from: 8, to: 33, color: go.Brush.randomColor(0, 66) });
	    	//10与节点34
	    	linkDataArray.push({ from: 9, to: 33, color: go.Brush.randomColor(0, 189) });
	    	//14与节点34
	    	linkDataArray.push({ from: 13, to: 33, color: go.Brush.randomColor(0, 89) });
	    	//15与节点33 34
	    	linkDataArray.push({ from: 14, to: 32, color: go.Brush.randomColor(0, 246) });
	    	linkDataArray.push({ from: 14, to: 33, color: go.Brush.randomColor(0, 246) });
	    	
	    	//16与节点33 34
	    	linkDataArray.push({ from: 15, to: 32, color: go.Brush.randomColor(0, 146) });
	    	linkDataArray.push({ from: 15, to: 33, color: go.Brush.randomColor(0, 146) });
	    	//19与节点33 34
	    	linkDataArray.push({ from: 18, to: 32, color: go.Brush.randomColor(0, 46) });
	    	linkDataArray.push({ from: 18, to: 33, color: go.Brush.randomColor(0, 46) });
	    	
	    	//20与节点34
	    	linkDataArray.push({ from: 19, to: 33, color: go.Brush.randomColor(0, 208) });
	    	//21与节点33 34
	    	linkDataArray.push({ from: 20, to: 32, color: go.Brush.randomColor(0, 4) });
	    	linkDataArray.push({ from: 20, to: 33, color: go.Brush.randomColor(0, 4) });
	    	
	    	//23与节点33 34
	    	linkDataArray.push({ from: 22, to: 32, color: go.Brush.randomColor(0, 19) });
	    	linkDataArray.push({ from: 22, to: 33, color: go.Brush.randomColor(0, 19) });
	    	
	    	//24与26 28 30 33 34
	    	linkDataArray.push({ from: 23, to: 25, color: go.Brush.randomColor(0, 9) });
	    	linkDataArray.push({ from: 23, to: 27, color: go.Brush.randomColor(0, 9) });
	    	linkDataArray.push({ from: 23, to: 29, color: go.Brush.randomColor(0, 9) });
	    	linkDataArray.push({ from: 23, to: 32, color: go.Brush.randomColor(0, 9) });
	    	linkDataArray.push({ from: 23, to: 33, color: go.Brush.randomColor(0, 9) });
	    	
	    	//25与26 28 32
	    	linkDataArray.push({ from: 24, to: 25, color: go.Brush.randomColor(0, 99) });
	    	linkDataArray.push({ from: 24, to: 27, color: go.Brush.randomColor(0, 99) });
	    	linkDataArray.push({ from: 24, to: 31, color: go.Brush.randomColor(0, 99) });
	    	
	    	//26与32
	    	linkDataArray.push({ from: 25, to: 31, color: go.Brush.randomColor(0, 145) });
	    	//27与30 34
	    	linkDataArray.push({ from: 26, to: 29, color: go.Brush.randomColor(0, 45) });
	    	linkDataArray.push({ from: 26, to: 33, color: go.Brush.randomColor(0, 45) });
	    	//28与34
	    	linkDataArray.push({ from: 27, to: 37, color: go.Brush.randomColor(0, 77) });
	    	//29与32 34
	    	linkDataArray.push({ from: 28, to: 31, color: go.Brush.randomColor(0, 5) });
	    	linkDataArray.push({ from: 28, to: 33, color: go.Brush.randomColor(0, 5) });
	    	//30与33 34 
	    	linkDataArray.push({ from: 29, to: 32, color: go.Brush.randomColor(0, 95) });
	    	linkDataArray.push({ from: 29, to: 33, color: go.Brush.randomColor(0, 95) });
	    	//31与33 34 
	    	linkDataArray.push({ from: 30, to: 32, color: go.Brush.randomColor(0, 195) });
	    	linkDataArray.push({ from: 30, to: 33, color: go.Brush.randomColor(0, 195) });
	    	//32与33 34 
	    	linkDataArray.push({ from: 31, to: 32, color: go.Brush.randomColor(0, 165) });
	    	linkDataArray.push({ from: 31, to: 33, color: go.Brush.randomColor(0, 165) });
	    	//33与34 
	    	linkDataArray.push({ from: 32, to: 33, color: go.Brush.randomColor(0, 25) });
	    	
	    	
	    myDiagram.model = new go.GraphLinksModel(nodeDataArray, linkDataArray);
	  }

</script>
</head>
<body onload="init()">
<div id="sample">
<p style="text-align: center; font-family: 微软雅黑"><b>Prototype topology</b></p>
  <div id="myDiagram" style="border: solid 1px green; background: white; width: 100%; height: 600px" ></div>
  
  
  
</div>
</body>
</html>