<%@ tag trimDirectiveWhitespaces="true" %>
<%@ tag description="Student Message of the day" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="reply" type="teammates.ui.template.RepliesDiv" required="true" %>

<div class="panel panel-default">
  <div class="panel-heading">  
    <b><c:out value="${reply.name}"  /></b> ${reply.dateTime}:</br>        
      <div class="panel-body">
        <c:out value="${reply.desc}"   /></br>    
     </div> 
      <c:forEach items="${reply.actions}" var="button">
        <a ${button.attributesToString}>
            ${button.content}
        </a>
      </c:forEach>  
  </div>
</div>