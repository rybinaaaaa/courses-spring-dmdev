<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
</head>
<body>
Hello ${requestScope.user.username} with id ${requestScope.user.id}
<a href="http://localhost:8080/api/v1/bye">Bye</a>
</body>
</html>