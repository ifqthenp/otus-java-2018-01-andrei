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
    <link rel="stylesheet" type="text/css" href="css/custom.css">
    <script type="text/javascript" src="js/ws.js"></script>
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
                <div class="row">
                    <h3>Websocket Test</h3>
                </div>
                <div class="row">
                    <form action="">
                        <input onclick="webSocket.sendUserId()" value="Get user" type="button">
                        <input id="userId" name="message" value="1" type="text"><br>
                    </form>
                </div>
                <div class="row" id="output"></div>
            </div>

            <div class="col">
                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Parameter</th>
                        <th scope="col">Value</th>
                    </tr>
                    </thead>

                    <tbody>
                          <#list stats as propName, propValue>
                          <tr>
                              <th scope="row">${propName?index+1}</th>
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
