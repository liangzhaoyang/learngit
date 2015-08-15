<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>karate club network</title>

<script src="${pageContext.request.contextPath }/js/go.js"></script>

<script id="code">
function init() {
  if (window.goSamples) goSamples();  // init for these samples -- you don't need to call this
  var $ = go.GraphObject.make;  // for conciseness in defining templates

  blueDiagram =
    $(go.Diagram, "blueDiagram",
      {
        // start everything in the middle of the viewport
        initialContentAlignment: go.Spot.Center,
        // double-click in background creates a new node there
        "clickCreatingTool.archetypeNodeData": { key: "node" }
      });

  blueDiagram.nodeTemplate =
    $(go.Node, "Auto",
      new go.Binding("location", "loc").makeTwoWay(),
      $(go.Shape, "RoundedRectangle",
        {
          fill: $(go.Brush, go.Brush.Linear, { 0: "#00ACED", 0.5: "#00ACED", 1: "#0079A6" }),
          stroke: "#0079A6",
          portId: "", cursor: "pointer",  // the node's only port is the Shape
          fromLinkable: true, fromLinkableDuplicates: true, fromLinkableSelfNode: true,
          toLinkable: true, toLinkableDuplicates: true, toLinkableSelfNode: true
        }),
      $(go.TextBlock,
        { margin: 3, font: "bold 10pt arial", stroke: "whitesmoke" },
        new go.Binding("text", "key"))
    );

  blueDiagram.linkTemplate =
    $(go.Link,
      {
        curve: go.Link.Bezier, adjusting: go.Link.Stretch,
        relinkableFrom: true, relinkableTo: true, reshapable: true
      },
      $(go.Shape,  // the link shape
        { strokeWidth: 1, stroke: "blue" }),
      $(go.Shape,  // the arrowhead
        { toArrow: "standard",
          fill: "blue", stroke: null })
    );


  greenDiagram =
    $(go.Diagram, "greenDiagram",
      {
        // start everything in the middle of the viewport
        initialContentAlignment: go.Spot.Center,
        // double-click in background creates a new node there
        "clickCreatingTool.archetypeNodeData": { key: "node" }
      });

  greenDiagram.nodeTemplate =
    $(go.Node, "Vertical",
      new go.Binding("location", "loc").makeTwoWay(),
      $(go.Shape, "Ellipse",
        { fill: "lightgreen", width: 20, height: 20, portId: "" }),
      $(go.TextBlock,
        { margin: 3, font: "bold 12px Georgia" },
        new go.Binding("text", "key"))
    );

  greenDiagram.linkTemplate =
    $(go.Link,
      $(go.Shape,  // the link shape
        { strokeWidth: 1, stroke: "#76C176" }),
      $(go.Shape,  // the arrowhead
        { toArrow: "standard",
          fill: "#76C176", stroke: null })
    );


  // create the model data that will be represented in both diagrams simultaneously
  var model = new go.GraphLinksModel(
  [
    { key: "n1", loc: new go.Point(470,340) },
    { key: "n2", loc: new go.Point(640,310) },
	{ key: "n3", loc: new go.Point(780,260) },
	{ key: "n4", loc: new go.Point(790,350) },
    { key: "n5", loc: new go.Point(530,410) },
	{ key: "n6", loc: new go.Point(230,330) },
	{ key: "n7", loc: new go.Point(380,350) },
    { key: "n8", loc: new go.Point(840,310) },
	{ key: "n9", loc: new go.Point(760,180) },
	{ key: "n10", loc: new go.Point(530,200) },
    { key: "n11", loc: new go.Point(400,390) },
	{ key: "n12", loc: new go.Point(680,350) },
	{ key: "n13", loc: new go.Point(690,380) },
    { key: "n14", loc: new go.Point(510,260) },
	{ key: "n15", loc: new go.Point(460,70) },
	{ key: "n16", loc: new go.Point(550,60) },
    { key: "n17", loc: new go.Point(250,390) },
	{ key: "n18", loc: new go.Point(600,400) },
	{ key: "n19", loc: new go.Point(730,100) },
    { key: "n20", loc: new go.Point(440,270) },
	{ key: "n21", loc: new go.Point(643,87) },
	{ key: "n22", loc: new go.Point(310,300) },
    { key: "n23", loc: new go.Point(740,140) },
	{ key: "n24", loc: new go.Point(200,140) },
	{ key: "n25", loc: new go.Point(140,230) },
	{ key: "n26", loc: new go.Point(120,170) },
	{ key: "n27", loc: new go.Point(220,100) },
    { key: "n28", loc: new go.Point(240,170) },
	{ key: "n29", loc: new go.Point(270,200) },
	{ key: "n30", loc: new go.Point(360,100) },
	{ key: "n31", loc: new go.Point(610,200) },
	{ key: "n32", loc: new go.Point(310,240) },
    { key: "n33", loc: new go.Point(580,150) },
	{ key: "n34", loc: new go.Point(380,160) },	
  ],
  [
    { from: "n1", to: "n2"},{ from: "n1", to: "n3" },{ from: "n1", to: "n4" },{ from: "n1", to: "n5" },{ from: "n1", to: "n6" },
    { from: "n1", to: "n7" },{ from: "n1", to: "n8" },{ from: "n1", to: "n9" },{ from: "n1", to: "n11" },{ from: "n1", to: "n12" },
    { from: "n1", to: "n13" },{ from: "n1", to: "n14" },{ from: "n1", to: "n18" },{ from: "n1", to: "n20" },{ from: "n1", to: "n22" },{ from: "n1", to: "n32" },
    //2与节点3 4 8 14 18 20 22 31
	{ from: "n2", to: "n3"}, { from: "n2", to: "n4" },{ from: "n2", to: "n8" },{ from: "n2", to: "n14"},{ from:"n2", to: "n18"},{ from: "n2", to: "n20" },{ from: "n2", to: "n22" },{ from: "n2", to: "n31"},
	//3与节点4 8 9 10 14 28 29 33
	{ from: "n3", to: "n4" },{ from: "n3", to: "n8" },{ from: "n3", to: "n10" },{ from: "n3", to: "n14"},{ from: "n3", to: "n28" },{ from: "n3", to: "n29" },{ from: "n3", to: "n33" },
	//4与节点 8 13 14 
	    	  { from: "n4", to: "n8" },{ from: "n4", to: "n13" },{ from: "n4", to: "n14" },
	    	  //5与节点 7 11 
	    	  { from: "n5", to: "n7" },{ from: "n5", to: "n11" },
	    	  // 6与节点7 11 17
	    	  { from: "n6", to: "n7" },{ from: "n6", to: "n11" },{ from: "n6", to: "n17" },
	    	  // 7与节点17
	    	  { from: "n7", to: "n17" },
	    	  //9与节点31 33 34
	    	  { from: "n9", to: "n31" },{ from: "n9", to: "n33" },{ from: "n9", to: "n34" },
	    	  //10与节点34
	    	  { from: "n10", to: "n34" },
	    	  //14与节点34
	    	  { from: "n14", to: "n34" },
	    	  //15与节点33 34
	    	  { from: "n15", to: "n33" },{ from: "n15", to: "n34" },
	    	  //16与节点33 34
	    	  { from: "n16", to: "n33" },{ from: "n16", to: "n34" },
	    	  //19与节点33 34
	    	  { from: "n19", to: "n33" },{ from: "n19", to: "n34" },
	    	  //20与节点34
	    	  { from: "n20", to: "n34" },
	    	  //21与节点33 34
	    	  { from: "n21", to: "n33" },{ from: "n21", to: "n34" },
	    	  //23与节点33 34
	    	  { from: "n23", to: "n33" },{ from: "n23", to: "n34" },
	    	  //24与26 28 30 33 34
	    	  { from: "n24", to: "n26" },{ from: "n24", to: "n28" },{ from: "n24", to: "n30" },{ from: "n24", to: "n33" },{ from: "n24", to: "n34" },
	    	  //25与26 28 32
	    	  { from: "n25", to: "n26" },{ from: "n25", to: "n28" },{ from: "n25", to: "n32" },
	    	  //26与32
	    	  { from: "n26", to: "n32" },
	    	  //27与30 34
	    	  { from: "n27", to: "n30" },{ from: "n27", to: "n34" },
	    	  //28与34
	    	  { from: "n28", to: "n34" },
	    	  //29与32 34
	    	  { from: "n29", to: "n33" },{ from: "n29", to: "n34" },
	    	  //30与33 34 
	    	  { from: "n30", to: "n33" },{ from: "n30", to: "n34" },
	    	  //31与33 34
	    	  { from: "n31", to: "n33" },{ from: "n31", to: "n34" },
	    	  //32与33 34 
	    	  { from: "n32", to: "n33" },{ from: "n32", to: "n34" },
	    	  //33与34 
	    	  { from: "n33", to: "n34" }
	
  ]);

  // the two Diagrams share the same model
  blueDiagram.model = model;
  greenDiagram.model = model;

  // now turn on undo/redo
  model.undoManager.isEnabled = true;


  // **********************************************************
  // A third diagram is on this page to display the undo state.
  // It functions as a tree view, showing the Transactions
  // in the UndoManager history.
  // **********************************************************

  var undoDisplay =
    $(go.Diagram, "undoDisplay",
      {
        allowMove: false,
        maxSelectionCount: 1,
        layout:
          $(go.TreeLayout,
            {
              alignment: go.TreeLayout.AlignmentStart,
              angle: 0,
              compaction: go.TreeLayout.CompactionNone,
              layerSpacing: 16,
              layerSpacingParentOverlap: 1,
              nodeIndent: 2,
              nodeIndentPastParent: 0.88,
              nodeSpacing: 0,
              setsPortSpot: false,
              setsChildPortSpot: false,
              arrangementSpacing: new go.Size(2, 2)
            }),
        "animationManager.isEnabled": false
      });

  undoDisplay.nodeTemplate =
    $(go.Node,
      $("TreeExpanderButton",
        { width: 14, "ButtonBorder.fill": "whitesmoke" }),
      $(go.Panel, "Horizontal",
        { position: new go.Point(16, 0) },
        new go.Binding("background", "color"),
        $(go.TextBlock, {margin: 2},
          new go.Binding("text", "text"))
      )
    );

  undoDisplay.linkTemplate = $(go.Link);  // not really used

  var undoModel =
    $(go.TreeModel,  // initially empty
      { isReadOnly: true });
  undoDisplay.model = undoModel;

  // ******************************************************
  // Add an undo listener to the main model
  // ******************************************************

  var changedLog = document.getElementById("modelChangedLog");
  var editToRedo = null; // a node in the undoDisplay
  var editList = [];

  model.addChangedListener(function(e) {
    // do not display some uninteresting kinds of transaction notifications
    if (e.change === go.ChangedEvent.Transaction) {
      if (e.propertyName === "CommittingTransaction" || e.modelChange === "SourceChanged") return;
      // do not display any layout transactions
      if (e.oldValue === "Layout") return;
    }  // You will probably want to use e.isTransactionFinished instead

    // Add entries into the log
    var changes = e.toString();
    if (changes[0] !== "*") changes = "&nbsp;&nbsp;" + changes;
    changedLog.innerHTML += changes + "<br/>";
    changedLog.scrollTop = changedLog.scrollHeight;

    // Modify the undoDisplay Diagram, the tree view
    if (e.propertyName === "CommittedTransaction") {
      if (editToRedo != null) {
        // remove from the undo display diagram all nodes after editToRedo
        for (var i = editToRedo.data.index+1; i < editList.length; i++) {
          undoDisplay.remove(editList[i]);
        }
        editList = editList.slice(0, editToRedo.data.index);
        editToRedo = null;
      }

      var tx = e.object;
      var txname = (tx !== null ? e.object.name : "");
      var parentData = {text: txname, tag: e.object, index: editList.length - 1};
      undoModel.addNodeData(parentData)
      var parentKey = undoModel.getKeyForNodeData(parentData);
      var parentNode = undoDisplay.findNodeForKey(parentKey);
      editList.push(parentNode);
      if (tx !== null) {
        var allChanges = tx.changes;
        var odd = true;
        allChanges.each(function(change) {
            var childData = {
              color: (odd ? "white" : "#E0FFED"),
              text: change.toString(),
              parent: parentKey
            };
            undoModel.addNodeData(childData)
            odd = !odd;
          });
        undoDisplay.commandHandler.collapseTree(parentNode);
      }
    } else if (e.propertyName === "FinishedUndo" || e.propertyName === "FinishedRedo") {
      var undoManager = model.undoManager;
      if (editToRedo !== null) {
        editToRedo.isSelected = false;
        editToRedo = null;
      }
      // Find the node that represents the undo or redo state and select it
      var nextEdit = undoManager.transactionToRedo;
      if (nextEdit !== null) {
        var itr = undoDisplay.nodes;
        while (itr.next()) {
          var node = itr.value;
          if (node.data.tag === nextEdit) {
            node.isSelected = true;
            editToRedo = node;
            break;
          }
        }
      }
    }
  }); // end model changed listener
	//改变线条
  model.addChangedListener(function(e) {
    if (e.isTransactionFinished) {
      var tx = e.object;
      if (tx instanceof go.Transaction && console) {
        console.log(tx.toString());
        tx.changes.each(function(c) {
          if (c.model) console.log("  " + c.toString());
        });
      }
    }
  });
} // end init

function resetLog() {
  var div = document.getElementById("modelChangedLog").innerHTML = "";
}

function undo() {
  blueDiagram.undoManager.undo()
}

function redo() {
  blueDiagram.undoManager.redo()
}


</script>
</head>
<body onload="init()">
<div id="sample">
  <p style="text-align: center; font-family: 微软雅黑"><b>karate club network topological graph</b></p>
  <div style="width:100%; white-space:nowrap">
    <div style="display: inline-block; vertical-align: top; width:100%">
     <div id="greenDiagram" style="border: solid 1px green; width:100%; height:600px"></div>
     <div style="width:100%; height:20px"></div>
     <div id="blueDiagram" style="border: solid 1px green; width:100%; height:600px;"></div>
    </div>
    
  </div>
 
</div>
</body>
</html>