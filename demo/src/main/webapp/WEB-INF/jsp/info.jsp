<html>
<head>
<title>information page for admin only</title>
</head>
<body>
	<h2>${errorMSG}</h2>

	<h2>${successMSG}</h2>

	<form method="post" action="info">
		<label for="username">name</label> <input type="text" name="username"
			id="username" required><br> <label for="password">password</label>
		<input type="password" name="password" id="password" required><br>
		<button>submit</button>

	</form>
	
</body>
</html>