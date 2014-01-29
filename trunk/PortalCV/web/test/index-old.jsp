<%@page import="radiuscoa.RadiusCoaResult"%>
<%@page import="radiuscoa.RadiusResult"%>
<%@page import="radiuscoa.RadiusSender"%>
<%
//Esto lo agrege para que te redireccione si existe la session abierta
    if (session.getAttribute("email") != null) {
        try {
            request.getRequestDispatcher("/WEB-INF/view/logged.jsp").forward(request, response);
        } catch (Exception ex) {
        }
    }

%>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript">

    $(document).ready(function(){
        $("#checkoutForm").validate({
            rules: {
                email: {
                    required: true
                    //                    ,
                    //                    email: true
                },
                password: {
                    required: true
                    //                    ,
                    //                    minlength: 7,
                    //                    maxlength: 12
                }
                //                ,
                //                client: {
                //                    requiered: true
                //                }
            }
        });
    });
</script>

<div id="singleColumn">
    <%

      //  RadiusSender sender = new RadiusSender();
      //  RadiusResult result = sender.sessionQuery("192.168.3.1", "test1", request.getRemoteAddr());
        
      //  if(result.getResultType().equals(RadiusCoaResult.VALID_SESSION)){
      //      request.setAttribute("pruebaaux2", false);
      //  }else{
            request.setAttribute("pruebaaux", false);
      //  }
    %>
    <c:if test="${!empty pruebaaux}">
    <h2>Iniciar sesi&oacute;n FiberZone:</h2>

    <form id="checkoutForm" action="<c:url value='loginUser'/>" method="post">
        <table id="checkoutTable">
            <tr>
                <td><label for="email">Correo electr&oacute;nico:</label></td>
                <td class="inputField">
                    <input type="text"
                           size="31"
                           maxlength="45"
                           id="email"
                           name="email"
                           value="${param.email}">
                </td>
            </tr>
            <tr>
                <td><label for="password">Contrase&ntilde;a:</label></td>
                <td class="inputField">
                    <input type="password"
                           size="31"
                           maxlength="19"
                           id="password"
                           name="password"
                           class="password"
                           value="${param.password}">
                </td>
            </tr>
            <% 
             //   if (!request.getRequestURL().toString().contains(getServletContext().getAttribute("url").toString())) {
             //       response.sendRedirect(""+getServletContext().getAttribute("url").toString()+"?url=" + request.getRequestURL().toString());
             //   }


             //   if (request.getParameter("url") != null) {
             //       out.println("<input type='hidden' id='url' name='url' value='" + request.getParameter("url") + "'>");
             //   }
            %>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Entrar">
                </td>
            </tr>
            <c:if test="${!empty invalidloginErrorFlag}">
                <tr>
                    <td colspan="2" style="text-align:left">
                        <span class="indent" style="color: red">Correo electr&oacute;nico o contrase&ntilde;a incorrecta.</span>
                    </td>
                </tr>
            </c:if>
            <c:if test="${!empty accountexpiredErrorFlag}">
                <tr>
                    <td colspan="2" style="text-align:left">
                        <span class="indent" style="color: red">La cuenta ha expirado.</span>
                    </td>
                </tr>
            </c:if>
            <c:if test="${!empty accountnotvalidatedErrorFlag}">
                <tr>
                    <td colspan="2" style="text-align:left">
                        <span class="indent" style="color: red">La cuenta no esta validada.</span>
                    </td>
                </tr>
            </c:if>
            <c:if test="${!empty sucessfulLoginFlag}">
                <tr>
                    <td colspan="2" style="text-align:left">
                        <span class="indent" style="color: blue">Sesi&oacute;n iniciada correctamente.</span>
                    </td>
                </tr>
            </c:if>
        </table>
    </form>
    <br/>
    <div>
        <a href="<c:url value='signup'/>" class="bubble">
            Registrarse
        </a>
        &nbsp;
        &nbsp;&nbsp;&nbsp;
        <a href="<c:url value='recoverPassword'/>" class="bubble">
            Recuperar contrase&ntilde;a
        </a>
    </div>
            </c:if>
    <c:if test="${!empty pruebaaux2}">
        Se acabo la cuota. <a href="<c:url value='/logoutUser'/>" class="bubble">
    Logout
</a>
        </c:if>
</div>
<br/>
