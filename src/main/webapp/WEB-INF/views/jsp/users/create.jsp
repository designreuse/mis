<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<content tag="title">Create user</content>

<content tag="content">

    <section class="content-header">
        <h1>
            Create user
        </h1>
    </section>
    <section class="content">

        <div class="row">
            <div class="col-md-12">
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h3 class="box-title">Create user</h3>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <form role="form" id="createUserForm" action="<c:url value="/users/store"/>" method="POST">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>First Name:</label>
                                        <input name="firstName" class="form-control" required data-fv-notempty-message="The first name is required">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Last Name:</label>
                                        <input name="lastName" class="form-control" required data-fv-notempty-message="The last name is required">
                                    </div>
                                </div>                                    
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Email</label>
                                        <input name="email" class="form-control" required data-fv-notempty-message="The email is required">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Username</label>
                                        <input name="username" class="form-control" required data-fv-notempty-message="The username is required">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Password</label>
                                        <input type="password" name="password" class="form-control" required data-fv-notempty-message="The password is required">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Role</label>
                                        <select name="roleId" class="form-control">
                                            <c:forEach var="role" items="${roles}">
                                                <option value="${role.id}">${role.publicName}</option>
                                            </c:forEach>
                                        </select>                                       
                                    </div>
                                </div>
                            </div>                                
                            <button type="submit" class="btn btn-default">Save</button>
                        </form>
                    </div><!-- /.box-body -->
                </div>
            </div>
        </div>
    </section>
</content>                           
<content tag="footerScripts">
    <script type="text/javascript">
        $(document).ready(function() {
          $('#createUserForm').formValidation();
       });
    </script>
</content>
