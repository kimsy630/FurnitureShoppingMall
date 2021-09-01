<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.beans.XMLEncoder"%>
<%@ page import="org.w3c.dom.*" %>
<%@ page import="org.xml.sax.*" %>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.net.*"%>
<%@ page import="javax.xml.parsers.*"%>
<%@ page import="javax.servlet.http.HttpServletResponse.*"%>
<%
	String geturl = "http://asp1.krx.co.kr/servlet/krx.asp.DisList4MainServlet?code=000040&gubun=K";
	String gettime="";
	
	String xmlstr = "";
	int disInfo_lenth = 0;

	String line="";
	String disInfo[][] = new String[10][4];
	
	String xml = "";

	try{
		URL url = new URL(geturl);
		URLConnection conn = url.openConnection();
		HttpURLConnection httpConnection = (HttpURLConnection) conn;
		InputStream is = null;
		InputStreamReader isr = null;
		
		is =  new URL(geturl).openStream();
		isr = new InputStreamReader(is, "UTF-8");
		
		
		
		BufferedReader rd = new BufferedReader(isr,400);
		
		StringBuffer strbuf = new StringBuffer();
		
		
		while ((line = rd.readLine()) != null){
			
		  	strbuf.append(line);
		}
		
		//System.out.println("공시정보");
		//System.out.println(strbuf.toString().trim());
		
		DocumentBuilderFactory docFact = DocumentBuilderFactory.newInstance();
		docFact.setNamespaceAware(true);
		DocumentBuilder docBuild = docFact.newDocumentBuilder();

		Document doc = docBuild.parse(new InputSource(new StringReader(strbuf.toString())));
		doc.getDocumentElement().normalize();
		
		
		
		Element root = doc.getDocumentElement();
		
		NodeList disclosureMain = doc.getElementsByTagName("disclosureMain");
		
		NamedNodeMap disclosureMaininfo = disclosureMain.item(0).getAttributes();
		gettime = disclosureMaininfo.getNamedItem("querytime").getNodeValue();
		
		
		NodeList disinfo = doc.getElementsByTagName("disInfo");
		
		disInfo_lenth = disinfo.getLength();
		for(int i=0;i<disInfo_lenth;i++){
			
			NamedNodeMap Disinfo =  disinfo.item(i).getAttributes();
			
			disInfo[i][0] = Disinfo.getNamedItem("distime").getNodeValue();
			disInfo[i][1] = Disinfo.getNamedItem("disTitle").getNodeValue();
			disInfo[i][2] = Disinfo.getNamedItem("disAcpt_no").getNodeValue();
			disInfo[i][3] = Disinfo.getNamedItem("submitOblgNm").getNodeValue();
			
		}
		

	
	} catch(Exception e){
		
	}


%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script src="http://asp1.krx.co.kr/inc/js/asp_chart.js"></script>
<link rel="stylesheet" type="text/css" href="css/disinfo.css"/>
<title>공시정보</title>
</head>
<body>
	<div class="header-wrap">
	공시정보<span><span class="time_img"></span><%=gettime%> 기준</span>
	</div>
	<div class="body-wrap">
	<table id="disInfo">
		<tr>
			<th>번호</th>
			<th>일자</th>
			<th>공시제목</th>
			<th>제출의무자</th>
		</tr>
		<%if(disInfo_lenth > 0){ %>
			<%for(int i = 0; i < disInfo_lenth ; i++){ %>
			<tr>
				<td><%=disInfo_lenth-i%></td>
				<td><%=disInfo[i][0].substring(0, 4)%>/<%=disInfo[i][0].substring(4, 6)%>/<%=disInfo[i][0].substring(6, 8)%></td>		
				<td><a href="#" onclick="window.open('http://kind.krx.co.kr/common/disclsviewer.do?method=search&acptno=<%=disInfo[i][2]%>','공시상세보기','width=1200,height=800,top=100,left=350');"><%=disInfo[i][1]%></a></td>
				<td><%=disInfo[i][3]%></td>
			</tr>
			<% }%>
		<% } else {%>
			<tr>
				<td colspan="4">데이터가 없습니다.</td>
			</tr>
		<% }%>
	</table>
	
	</div>
	<div class="footer-wrap"></div>
</body>
</html>