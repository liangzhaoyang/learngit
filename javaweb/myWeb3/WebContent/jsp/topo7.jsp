<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Copyright 1998-2015 by Northwoods Software Corporation. -->
<script src="${pageContext.request.contextPath }/js/go.js"></script>
<link href="../assets/css/goSamples.css" rel="stylesheet" type="text/css" />  <!-- you don't need to use this -->
<script src="goSamples.js"></script>  <!-- this is only for the GoJS Samples framework -->
<script id="code">
  function init() {
    if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this
    var $ = go.GraphObject.make;

    myDiagram =
      $(go.Diagram, "myDiagram",
        {
          allowDrop: true,
          initialContentAlignment: go.Spot.Center,
          "undoManager.isEnabled": true
        });

    // when the document is modified, add a "*" to the title and enable the "Save" button
    myDiagram.addDiagramListener("Modified", function(e) {
      var button = document.getElementById("saveModel");
      if (button) button.disabled = !myDiagram.isModified;
      var idx = document.title.indexOf("*");
      if (myDiagram.isModified) {
        if (idx < 0) document.title += "*";
      } else {
        if (idx >= 0) document.title = document.title.substr(0, idx); 
      }
    });

    myDiagram.nodeTemplateMap.add("Generator",
      $(go.Node,
        { locationSpot: go.Spot.Center},
        new go.Binding("location", "location", go.Point.parse).makeTwoWay(go.Point.stringify),
        $(go.Picture, "${pageContext.request.contextPath }/images/network.png",
          { name: "BODY", width: 200, height: 90, margin: 2,
            portId: "", fromLinkable: true, cursor: "pointer" })
      ));

    myDiagram.nodeTemplateMap.add("Connector",
      $(go.Node,
        { locationSpot: go.Spot.Center,locationObjectName: "BODY",
          selectionObjectName: "BODY" },
        new go.Binding("location", "location", go.Point.parse).makeTwoWay(go.Point.stringify),
         $(go.Picture, "${pageContext.request.contextPath }/images/Router.png",
          { name: "BODY", width: 50, height: 40, margin: 2,
            portId: "", fromLinkable: true, cursor: "pointer" }),
		$(go.TextBlock,
          { alignment: go.Spot.Right, alignmentFocus: go.Spot.Left, font: "bold 7pt sans-serif" },
          new go.Binding("text", "key"))
			
      ));

    myDiagram.nodeTemplateMap.add("Consumer",
      $(go.Node, "Spot",
        { locationSpot: go.Spot.Center, locationObjectName: "BODY",
          selectionObjectName: "BODY" },
        new go.Binding("location", "location", go.Point.parse).makeTwoWay(go.Point.stringify),
        $(go.Picture, "${pageContext.request.contextPath }/images/pc.jpg",
          { name: "BODY", width: 50, height: 40, margin: 2,
            portId: "", fromLinkable: true, cursor: "pointer" }),
        $(go.TextBlock,
          { alignment: go.Spot.Right, alignmentFocus: go.Spot.Left, font: "bold 7pt sans-serif"},
          new go.Binding("text", "key"))
      ));
	  
	  myDiagram.nodeTemplateMap.add("firewall",
      $(go.Node, "Spot",
        { locationSpot: go.Spot.Center, locationObjectName: "BODY",
          selectionObjectName: "BODY" },
        new go.Binding("location", "location", go.Point.parse).makeTwoWay(go.Point.stringify),
        $(go.Picture, "${pageContext.request.contextPath }/images/firewall.png",
          { name: "BODY", width: 50, height: 40, margin: 2,
            portId: "", fromLinkable: true, cursor: "pointer" }),
        $(go.TextBlock,
          { alignment: go.Spot.Right, alignmentFocus: go.Spot.Left,font: "bold 7pt sans-serif" },
          new go.Binding("text", "key"))
      ));
	  
	  myDiagram.nodeTemplateMap.add("switch",
      $(go.Node, "Spot",
        { locationSpot: go.Spot.Center, locationObjectName: "BODY",
          selectionObjectName: "BODY" },
        new go.Binding("location", "location", go.Point.parse).makeTwoWay(go.Point.stringify),
        $(go.Picture, "${pageContext.request.contextPath }/images/Switch1.png",
          { name: "BODY", width: 50, height: 40, margin: 2,
            portId: "", fromLinkable: true, cursor: "pointer" }),
        $(go.TextBlock,
          { alignment: go.Spot.Right, alignmentFocus: go.Spot.Left, font: "bold 7pt sans-serif" },
          new go.Binding("text", "key"))
      ));

    myDiagram.nodeTemplateMap.add("HBar",
      $(go.Node,
        new go.Binding("location", "location", go.Point.parse).makeTwoWay(go.Point.stringify),
        { layerName: "Background",
          // special resizing: just at the ends
          resizable: true, resizeObjectName: "SHAPE",
          resizeAdornmentTemplate:
            $(go.Adornment, "Spot",
              $(go.Placeholder),
              $(go.Shape,  // left resize handle
                { alignment: go.Spot.Left, cursor: "col-resize",
                desiredSize: new go.Size(4, 4), fill: "lightblue", stroke: "dodgerblue" }),
              $(go.Shape,  // right resize handle
                { alignment: go.Spot.Right, cursor: "col-resize",
                desiredSize: new go.Size(4, 4), fill: "lightblue", stroke: "dodgerblue" }))
            },
        $(go.Shape, "Rectangle",
          { name: "SHAPE",
            fill: "black", stroke: null, strokeWidth: 0,
            width: 1000, height: 1, 
            minSize: new go.Size(100, 4),
            maxSize: new go.Size(Infinity, 4) },
            new go.Binding("desiredSize", "size", go.Size.parse).makeTwoWay(go.Size.stringify),
          { portId: "", toLinkable: true })
      ));

    myDiagram.linkTemplate =
      $(BarLink,  // subclass defined below
        { routing: go.Link.Orthogonal,
          relinkableFrom: true, relinkableTo: true },
        $(go.Shape,
          { strokeWidth: 2 })
      );

    // start off with a simple diagram
    load();

    // initialize Palette
    myPalette =
      $(go.Palette, "myPalette",
        { nodeTemplateMap: myDiagram.nodeTemplateMap,
          layout:
            $(go.GridLayout,
              { cellSize: new go.Size(2, 2),
                isViewportSized: true })
        }
      );

    myPalette.model.nodeDataArray = [
      { "key": "Gen", "category": "Generator" },
      { "key": "Cons", "category": "Consumer" },
      { "key": "Dest", "category": "Connector" },
	  { "key": "firewall", "category": "firewall" },
	  { "key": "switch", "category": "switch" },
      { "key": "Bar", "category":"HBar", "size":"100 4"},
    ];

    // remove cursors on all ports in the Palette
    // make TextBlocks invisible, to reduce size of Nodes
    myPalette.nodes.each(function(node) {
        node.ports.each(function(port) {
            port.cursor = "";
          });
        node.elements.each(function(tb) {
            if (tb instanceof go.TextBlock) tb.visible = false;
          });
      });

    // initialize Overview
    myOverview =
      $(go.Overview, "myOverview",
        { observed: myDiagram,
          contentAlignment: go.Spot.Center });
  }


  function BarLink() {
    go.Link.call(this);
  }
  go.Diagram.inherit(BarLink, go.Link);

  BarLink.prototype.getLinkPoint = function(node, port, spot, from, ortho, othernode, otherport) {
    var r = new go.Rect(port.getDocumentPoint(go.Spot.TopLeft),
                        port.getDocumentPoint(go.Spot.BottomRight));
    var op = otherport.getDocumentPoint(go.Spot.Center);
    var below = op.y > r.centerY;
    var y = below ? r.bottom : r.top;
    if (node.category === "HBar") {
      if (op.x < r.left) return new go.Point(r.left, y);
      if (op.x > r.right) return new go.Point(r.right, y);
      return new go.Point(op.x, y);
    } else {
      return new go.Point(r.centerX, y);
    }
  };

  BarLink.prototype.getLinkDirection = function(node, port, linkpoint, spot, from, ortho, othernode, otherport) {
    var p = port.getDocumentPoint(go.Spot.Center);
    var op = otherport.getDocumentPoint(go.Spot.Center);
    var below = op.y > p.y;
    return below ? 90 : 270;
  };


  // save a model to and load a model from Json text, displayed below the Diagram
  function save() {
    document.getElementById("mySavedModel").value = myDiagram.model.toJson();
    myDiagram.isModified = false;
  }
  function load() {
    myDiagram.model = go.Model.fromJson(document.getElementById("mySavedModel").value);
  }
  

</script>
</head>
<body onload="init()">
<div id="sample">
  <div style="width:100%; white-space:nowrap;">
      <div id="myDiagram" style="border: solid 1px green; height: 600px"></div>
   
  </div>
  <textarea id="mySavedModel" style="width:100%;height:300px; display:none;">
{ "class": "go.GraphLinksModel",
  "linkFromPortIdProperty": "fromPort",
  "linkToPortIdProperty": "toPort",
  "nodeDataArray": [ 
{"key":"network", "category":"Generator", "location":"300 0"},
{"key":"1", "category":"HBar", "location":"100 60", "size":"400 1"},
{"key":"131.84.1.31", "category":"Consumer", "location":"103 100"},
{"key":"2", "category":"HBar", "location":"50 140", "size":"500 1"},
{"key":"router", "category":"Connector", "location":"232.5 100"},
{"key":"firewall", "category":"firewall", "location":"357.5 180"},
{"key":"3", "category":"HBar", "location":"100 220", "size":"500 1"},

{"key":"switch1", "category":"switch", "location":"453 260"},
{"key":"4", "category":"HBar", "location":"150 300", "size":"500 1"},
{"key":"switch2", "category":"switch", "location":"253 340"},
{"key":"172.16.115.20", "category":"Consumer", "location":"523 340"},
{"key":"5", "category":"HBar", "location":"100 380", "size":"400 1"},
{"key":"172.16.112.10", "category":"Consumer", "location":"183 420"},
{"key":"172.16.112.50", "category":"Consumer", "location":"399 420"},
{"key":"202.77.162.213", "category":"Consumer", "location":"384.5 100.75"}
 ],
  "linkDataArray": [ 
{"from":"network", "to":"1"},
{"from":"131.84.1.31", "to":"2"},
{"from":"router", "to":"1", "fromPort":"", "toPort":""},
{"from":"router", "to":"2", "fromPort":"", "toPort":""},
{"from":"firewall", "to":"2", "fromPort":"", "toPort":""},
{"from":"firewall", "to":"3", "fromPort":"", "toPort":""},
{"from":"switch1", "to":"3", "fromPort":"", "toPort":""},
{"from":"switch1", "to":"4", "fromPort":"", "toPort":""},
{"from":"switch2", "to":"4", "fromPort":"", "toPort":""},
{"from":"172.16.115.20", "to":"4", "fromPort":"", "toPort":""},
{"from":"switch2", "to":"5", "fromPort":"", "toPort":""},
{"from":"172.16.112.10", "to":"5", "fromPort":"", "toPort":""},
{"from":"172.16.112.50", "to":"5", "fromPort":"", "toPort":""},
{"from":"202.77.162.213", "to":"1", "fromPort":"", "toPort":""}
 ]}
  </textarea>
</div>
</body>

</html>
