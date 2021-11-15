<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="utf-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous"
            />
            <title>Lab 9</title>
        </head>
            <div class="container-fluid p-5">
                <div class="row">
                    <div>
                        <h3>Add User</h3>
                        <form action="user" method="post">
                            <div class="input-group mb-3">
                                <input
                                    type="text"
                                    name="email"
                                    value=""
                                    class="form-control"
                                    placeholder="Email"
                                    />
                            </div>
                            <div class="input-group mb-3">
                                <input
                                    type="text"
                                    name="firstName"
                                    value=""
                                    class="form-control"
                                    placeholder="First Name"
                                    />
                            </div>
                            <div class="input-group mb-3">
                                <input
                                    type="text"
                                    name="lastName"
                                    value=""
                                    class="form-control"
                                    placeholder="Last Name"
                                    />
                            </div>
                            <div class="input-group mb-3">
                                <input
                                    type="password"
                                    name="password"
                                    value=""
                                    class="form-control"
                                    placeholder="Password"
                                    />
                            </div>
                            <div class="input-group mb-3">
                                <select class="form-select" id="inputGroupSelect01" name="roleSelect">
                                    <option selected>Choose Role</option>
                                    <c:forEach items="${roleLabel}" var="r">
                                        <option value="${r.roleId}">${r.roleName}</option>
                                    </c:forEach>               
                                </select>
                            </div>
                            <div class="d-grid gap-2">
                                <input type="hidden" name="action" value="add">
                                <button class="btn btn-primary" type="submit">Save</button>
                            </div>
                        </form>
                    </div>
                    <h3>Manage Users</h3>
                    <table>
                        <tr>
                            <th>Email</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.email}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>
                                    <form action="user" method="post">
                                        <input type="hidden" name="action" value="edit">
                                        <input type="hidden" name="toEdit" value="${user.email}">
                                        <button type="submit" class="btn btn-primary">Edit</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="user" method="post">
                                        <input type="hidden" name="action" value="delete"> 
                                        <input type="hidden" name="toDelete" value="${user.email}">
                                        <button type="submit" class="btn btn-secondary">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div>
                        <h3>Edit User</h3>
                        <form action="user" method="post">
                            <div class="input-group mb-3">
                                <input
                                    type="text"
                                    class="form-control"
                                    placeholder="Email"
                                    aria-label="Recipient's email"
                                    aria-describedby="basic-addon2"
                                    name="emailEdit"
                                    value="${emailEdit}"
                                    />
                            </div>
                            <div class="input-group mb-3">
                                <input
                                    type="text"
                                    class="form-control"
                                    placeholder="First Name"
                                    name="firstNameEdit"
                                    value="${firstNameEdit}"
                                    />
                            </div>
                            <div class="input-group mb-3">
                                <input
                                    type="text"
                                    class="form-control"
                                    placeholder="Last Name"
                                    name="lastNameEdit"
                                    value="${lastNameEdit}"
                                    />
                            </div>
                            <div class="input-group mb-3">
                                <input
                                    type="text"
                                    class="form-control"
                                    placeholder="Password"
                                    name="passwordEdit"
                                    value="${passwordEdit}"
                                    />
                            </div>
                            <div class="input-group mb-3">
                                <select class="form-select" id="inputGroupSelect01" name="roleEdit">
                                    <c:forEach items="${roleLabel}" var="r">
                                        <c:choose>
                                            <c:when test="${roleEdit == r.roleId}">
                                                <option value="${r.roleId}" selected>${r.roleName}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${r.roleId}">${r.roleName}</option>
                                            </c:otherwise>    
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="d-grid gap-2">
                                <button class="btn btn-primary" type="submit" name="action" value="save">Save</button>
                        </form>
                        <button class="btn btn-secondary" action="user" method="get">Cancel</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>