<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Object[][] team = new Object[][] {
		{"Иванов Антон",  "Москва", 1500},
        {"Петрова Алла",  "Санкт-Петербург", 3000},
        {"Антонов Алексей",  "Красноярск", 2500},
        {"Смирнова Валерия", "Краснодар", 1900}
	};
%>
<table border='1'>
	<tr>
		<td><b><%=res.getString("name") %></b></td>
		<td><b><%=res.getString("city") %></b></td>
		<td><b><%=res.getString("salary") %></b></td>
	</tr>
	<%
	for (Object[] temp : team)
		if (salary == null || (int)temp[2] >= Integer.parseInt(salary))
			out.println("<tr><td>" + temp[0] + "</td><td>" + temp[1] + "</td><td>"
			 + Integer.toString((int)temp[2]) + "</td></tr>");
	%>
</table>
