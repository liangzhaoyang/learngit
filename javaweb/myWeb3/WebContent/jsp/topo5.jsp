<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>网络拓扑图3</title>
<!-- Copyright 1998-2015 by Northwoods Software Corporation. -->
<style type="text/css">

#myOverview {
  position: absolute;
  top: 10px;
  left: 10px;
  background-color: aliceblue;
  z-index: 300; /* make sure its in front */
  border: solid 1px blue;

  width:240px;
  height:100px
}

</style>
<script src="${pageContext.request.contextPath }/js/go.js"></script>
<link href="../assets/css/goSamples.css" rel="stylesheet" type="text/css" />  <!-- you don't need to use this -->
<script src="goSamples.js"></script>  <!-- this is only for the GoJS Samples framework -->
<script id="code">
  function init() {
    if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this
    var $ = go.GraphObject.make;  // for conciseness in defining templates

    myDiagram =
      $(go.Diagram, "myDiagram",  // the DIV HTML element
        {
          initialDocumentSpot: go.Spot.TopCenter,
          initialViewportSpot: go.Spot.TopCenter,
          layout:  // create a TreeLayout
            $(go.TreeLayout,
              {
                treeStyle: go.TreeLayout.StyleLastParents,
                angle: 90,
                layerSpacing: 80,
                alternateAngle: 0,
                alternateAlignment: go.TreeLayout.AlignmentStart,
                alternateNodeIndent: 20,
                alternateNodeIndentPastParent: 1,
                alternateNodeSpacing: 20,
                alternateLayerSpacing: 40,
                alternateLayerSpacingParentOverlap: 1,
                alternatePortSpot: new go.Spot(0, 0.999, 20, 0),
                alternateChildPortSpot: go.Spot.Left
              })
        });

    // define Converters to be used for Bindings
    function theNationFlagConverter(nation) {
      return null;
    }

    function theInfoTextConverter(info) {
      var str = "";
      if (info.title) str += "Title: " + info.title;
      if (info.headOf) str += "\n\nHead of: " + info.headOf;
      if (typeof info.boss === "number") {
        var bossinfo = myDiagram.model.findNodeDataForKey(info.boss);
        if (bossinfo !== null) {
          str += "\n\nfrom to: " + bossinfo.name;
        }
      }
      return str;
    }

    // define the Node template
    myDiagram.nodeTemplate =
      $(go.Node, "Auto",
        { isShadowed: true },
        // the outer shape for the node, surrounding the Table
        $(go.Shape, "Cloud",
          { fill: "aliceblue" }),
        // a table to contain the different parts of the node
        $(go.Panel, "Table",
          { margin: 4, maxSize: new go.Size(120, NaN) },
          // the two TextBlocks in column 0 both stretch in width
          // but align on the left side
          $(go.RowColumnDefinition,
            {
              column: 0,
              stretch: go.GraphObject.Horizontal,
              alignment: go.Spot.Left
            }),
          // the name
          $(go.TextBlock,
            {
              row: 0, column: 0,
              maxSize: new go.Size(120, NaN),
              margin: 2,
              
              font: "bold 9pt sans-serif",
              alignment: go.Spot.Center
            },
            new go.Binding("text", "name")),
          // the country flag
          $(go.Picture,
            {
              row: 0, column: 1, margin: 2,
              desiredSize: new go.Size(24, 16),
              imageStretch: go.GraphObject.Uniform,
              alignment: go.Spot.TopRight
            },
            new go.Binding("source", "nation", theNationFlagConverter)),
          // the additional textual information
          $(go.TextBlock,
            {
              row: 1, column: 0, columnSpan: 2,
              font: "9pt sans-serif"
             
            },
            new go.Binding("text", "", theInfoTextConverter))
        )  // end Table Panel
      );  // end Node

    // define the Link template, a simple orthogonal line
    myDiagram.linkTemplate =
      $(go.Link, go.Link.Orthogonal,
        { selectable: false },
        $(go.Shape, { stroke: '#222' } ));  // the default black link shape


    // set up the nodeDataArray, describing each person/position
    var nodeDataArray = [
      { key: 0, name: "Internet" },
        { key: 2, boss: 0, name: "Router" },
          { key: 3, boss: 2, name: "131.84.1.31"},
          { key: 4, boss: 2, name: "Switch1" },
            { key: 5, boss: 4, name: "Switch2" },
            	{key:7, boss: 5, name: "172.16.112.10"},
            	{key:8, boss: 5, name: "172.16.112.50"},
            { key: 6, boss: 4, name: "172.16.115.20" },
            
        { key: 1, boss: 0, name: "202.77.162.213" }
    ];

    // create the Model with data for the tree, and assign to the Diagram
    myDiagram.model =
      $(go.TreeModel,
        { nodeParentKeyProperty: "boss",  // this property refers to the parent node data
          nodeDataArray: nodeDataArray });


    // Overview
    myOverview =
      $(go.Overview, "myOverview",  // the HTML DIV element for the Overview
        { observed: myDiagram });   // tell it which Diagram to show and pan
  }
</script>
</head>
<body onload="init()">
<p style="text-align: center; font-family: 微软雅黑"><b>Dapar topological graph</b></p>
<div id="sample" style="position: relative;">
  <div id="myDiagram" style="background-color: white; border: solid 1px green; width: 100%; height: 800px"></div>
  <div id="myOverview"></div> <!-- Styled in a <style> tag at the top of the html page -->
  
</div>
</body>
</html>