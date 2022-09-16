
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JavaStar-Registration</title>
<link rel="stylesheet" href="../css/JavaStarBasic.css">
</head>
<body>

<form action="./RegisterAppl.jsp" method="get" onsubmit="return checkInput(this)">
	<table>
		<tr>
			<td>Nickname:</td>
			<td>
				<input type="text" name="userid" 
					value=''
					required 
					onkeyup="checkValueAndSetFehlerMsg(this.name, this.value)"
				/>
			</td>
			<td id="useridMsg"></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td>
				<input type="password" name="password" 
					value="" 
					onchange="checkValueAndSetFehlerMsg('password', this.value)"
					onkeyup="checkValueAndSetFehlerMsg('password', this.value)"
				/>
			</td>
			<td id="passwordMsg"></td>
		</tr>
		<tr>
			<td>e-mail:</td>
			<td>
				<input type="text" name="email" 
					value=''
					onkeyup="checkValueAndSetFehlerMsg(this.name, this.value)"
				/>
			</td>
			<td id="emailMsg"></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input type="submit" name="logon" value="logon" 
					onclick="setButtonClicked(this.name)"
				/>
			</td>
			<td></td>
		</tr>
	</table>


</form>
</body>
</html>