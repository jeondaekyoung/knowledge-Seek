<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $( "#accordion" ).accordion();
        
        $('.m_btn').click(function(){
           $('.m_nav').slideToggle('slow');
        });
    });
</script>