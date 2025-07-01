<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>template</title>
<style>
    body { 
		font-size:12px; 
    }
	
	div#template { 
		width:600px;
		height:500px; 
		background-color:gray;
	}
	
	div#header { 
		float:left;
		width:600px;
		height:50px;
		background-color:orange;
		text-align:center;	
    } 
    
	div#left { 
		float:left;
		width:100px;
		height:400px;
		background-color:cyan;		
		text-align:center;		
	} 
	
	div#content { 
	    float:left; 
	    width:500px;
	    height:400px;
	    background-color:yellow;
	    text-align:center;
	}
	
	div#bottom {   
	    float:left;	
	    width:600px;
	    height:50px;
	    background-color:orange;
	    text-align:center;
    }				
</style>
</head>
<body>

	<div id="template">
		<div id="header">
			header
		</div>
		<div id="left">
			<br><a href ="template.do?page=content1">content1</a><br>
				<a href ="template.do?page=content2">content2</a><br>
		</div>
		<div id="content">
			<!-- content -->
			<jsp:include page="${page}" />
		</div>
		<div id="bottom">
			<%@ include file="bottom.jsp" %>
		</div>
	</div>

</body>
</html>