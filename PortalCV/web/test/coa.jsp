<%-- 
    Document   : coa
    Created on : Apr 11, 2012, 3:14:44 PM
    Author     : bruno
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/portal.css">
        <script src="js/jquery-1.4.2.js" type="text/javascript"></script>
        <script src="js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></script>
        <script src="js/jquery.validate.js" type="text/javascript"></script>
        <title>Radius CoA Simulator</title>
    </head>
    <body>
        <script type="text/javascript">

            $(document).ready(function(){
                $("#logonForm").validate({
                    rules: {
                        message: {
                            required: true
                        },
                        radiusserverip: {
                            required: true
                        },
                        radiusserverport: {
                            required: true
                        },
                        secret: {
                            required: true
                        },
                        username: {
                            required: true
                        },
                        password: {
                            required: true
                        },
                        ciscoaccountinfo: {
                            required: true
                        }
                    }
                });

            });
        </script>
        <div id="logonColumn">
            <h2>Logon:</h2>
            <form id="logonForm" action="<c:url value='coa'/>" method="post">
                <table id="logonTable">
                    <tr>
                        <td><label for="name">Radius Server IP:</label></td>
                        <td class="inputField">
                            <input type="text"
                                   size="31"
                                   maxlength="45"
                                   id="radiusserverip"
                                   name="radiusserverip"
                                   value="${param.radiusserverip}">
                        </td>
                    </tr>
                    <tr>
                        <td><label for="name">Radius Server Port:</label></td>
                        <td class="inputField">
                            <input type="text"
                                   size="31"
                                   maxlength="45"
                                   id="radiusserverport"
                                   name="radiusserverport"
                                   value="${param.radiusserverport}">
                        </td>
                    </tr>
                    <tr>
                        <td><label for="name">Secret:</label></td>
                        <td class="inputField">
                            <input type="text"
                                   size="31"
                                   maxlength="45"
                                   id="secret"
                                   name="secret"
                                   value="${param.secret}">
                        </td>
                    </tr>
                    <tr>
                        <td><label for="name">Username:</label></td>
                        <td class="inputField">
                            <input type="text"
                                   size="31"
                                   maxlength="45"
                                   id="username"
                                   name="username"
                                   value="${param.username}">
                        </td>
                    </tr>
                    <tr>
                        <td><label for="password">Password:</label></td>
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
                    <tr>
                        <td><label for="email">CiscoAccountInfo:</label></td>
                        <td class="inputField">
                            <input type="text"
                                   size="31"
                                   maxlength="45"
                                   id="ciscoaccountinfo"
                                   name="ciscoaccountinfo"
                                   value="${param.ciscoaccountinfo}">
                        </td>
                    </tr>
                    <tr>
                        <td><label for="email">Message:</label></td>
                        <td class="inputField">
                            <select id="message" name="message">
                                <option>Logon</option>
                                <option>Logoff</option>
                                <option>Session Query</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="Submit">
                        </td>
                    </tr>
                    <tr>
                        <td >
                            Result
                        </td>
                        <td >
                            <% out.println(request.getAttribute("result")); %>
                        </td>
                    </tr>
                    <tr>
                        <td >
                            Exception
                        </td>
                        <td >
                            <% out.println(request.getAttribute("exception")); %>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <br/>
    </body>
</html>
