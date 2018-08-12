<#-- @ftlvariable name="stats" type="java.util.Map<java.lang.String>" -->
<#-- @ftlvariable name="visitor" type="java.lang.String" -->
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <title>Admin Page</title>
</head>
<body>

<main role="main">
    <div class="jumbotron">
        <div class="container">
            <h2 class="display-3">
                <a href="home"><img src="img/logo.png"/></a>
            </h2>
            <p>
                <a class="btn btn-lg btn-primary" href="login" role="button">Login
                    Page</a>
                <a class="btn btn-lg btn-primary" href="admin" role="button">Admin
                    Page</a>
            </p>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col">
                <h2>Hello, ${visitor}</h2>
                    <#if visitor == "ANONYMOUS">
                        <p class="lead">Please login to access application cache state on admin page</p>
                    </#if>
                <br>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col">
                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">Parameter</th>
                        <th scope="col">Value</th>
                    </tr>
                    </thead>

                    <tbody>
                          <#list stats as propName, propValue>
                          <tr>
                              <td>${propName}</td>
                              <td>${propValue}</td>
                          </tr>
                          </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</main>

</body>
</html>
