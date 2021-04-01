<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>        
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title> ��� �亯 </title>
    
    <style type="text/css">
        #wrap {
            width: 550px;
            margin: 0 auto 0 auto;
            text-align :center;
        }
    
        #commentReplyForm{
            text-align :center;
        }
    </style>
    
    <script type="text/javascript">
    
        var httpRequest = null;
        
        // httpRequest ��ü ����
        function getXMLHttpRequest(){
            var httpRequest = null;
        
            if(window.ActiveXObject){
                try{
                    httpRequest = new ActiveXObject("Msxml2.XMLHTTP");    
                } catch(e) {
                    try{
                        httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
                    } catch (e2) { httpRequest = null; }
                }
            }
            else if(window.XMLHttpRequest){
                httpRequest = new window.XMLHttpRequest();
            }
            return httpRequest;    
        }
    
    
        function checkValue()
        {
            var form = document.forms[0];
            // ������ ���� ������ ��´�.    
            var commentID = "${boardComment.commentID}";
            var boardID = "${boardComment.boardID}";
            var memberID = "${sessionScope.sessionID}";
            var content = form.content.value
            
            if(!content)
            {
                alert("������ �Է��ϼ���");
                return false;
            }
            else{
                var param="commentID="+commentID+"&boardID="+boardID
                                +"&memberID="+memberID+"&content="+content;
 
                httpRequest = getXMLHttpRequest();
                httpRequest.onreadystatechange = checkFunc;
                httpRequest.open("POST", "CommentReplyAction.comm", true);    
                httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'); 
                httpRequest.send(param);
            }
        }
        
        function checkFunc(){
            if(httpRequest.readyState == 4){
                // ������� �����´�.
                var resultText = httpRequest.responseText;
                if(resultText == 1){
                    if (opener != null) {
                        // �θ�â ���ΰ�ħ
                        window.opener.document.location.reload(); 
                        opener.replyForm = null;
                        self.close();
                    }
                }
            }
        }
        
    </script>
    
</head>
<body>
<div id="wrap">
    <br>
    <b><font size="5" color="gray">��� �亯</font></b>
    <hr size="1" width="550">
    <br>
 
    <div id="commentReplyForm">
        <form name="replyInfo" target="parentForm">        
            <textarea rows="7" cols="70" name="content"></textarea>
            <br><br>
            <input type="button" value="���" onclick="checkValue()">
            <input type="button" value="â�ݱ�" onclick="window.close()">
        </form>
    </div>
</div>    
</body>
</html>