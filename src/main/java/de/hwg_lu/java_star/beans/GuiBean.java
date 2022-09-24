package de.hwg_lu.java_star.beans;

import java.sql.SQLException;
import java.util.ArrayList;

import de.hwg_lu.java_star.jdbc.ExcerciseDB;
import de.hwg_lu.java_star.jdbc.StatisticsDB;

public class GuiBean {

	public static String[] lessonList = { "JavaBasic.html", "VariableAndDataType.html", "OperatorsInJava.html", "ArraysInJava.html"};

	public static String getSideNavigation(boolean isLoggedIn, String userName) throws SQLException {
		String html = "";
		html += "<span class='closedSideNav' onclick='openNav()'>&#9776; JAVA STAR</span>\n"
				+ "    <div class='sidenav' id='mySidenav' style='width: 0'>\n"
				+ "    <a href='javascript:void(0)' class='closebtn' onclick='closeNav()'>&times;</a>";
		html += "    <a href='./HomePageView.jsp'>Home</a>";
		html += "    <a onclick=\"toggleExcericeSideBar('courseSideBar')\" href='#'>Course</a>";
		html += "    <div id='courseSideBar' style='display: none'>";
		for (String page : lessonList) {
			html += "        <form style='margin-left:20px;' action='./Lesson.jsp' method='get'>";
			html += "            <input type='hidden' name='currentPage' value='" + page +"'> <input";
			html += "                type='submit' value='" + page.replace(".html", "") + "' />";
			html += "        </form>";
		}
		html += "    </div>";
		html += "    <a onclick=\"toggleExcericeSideBar('exSideBar')\" href='#'>Exercises</a>";
		html += "    <div id='exSideBar' style='display: none'>";
		if (isLoggedIn) {

			ExcerciseDB ex = new ExcerciseDB();
			StatisticsDB stat = new StatisticsDB();
			try {
				int numTot = ex.getNumberExcerice();
				ArrayList<String> colors = stat.getColorForAllExcercise(userName, numTot);
				for (int num = 1; num <= numTot; ++num) {
					html += "<form style='margin-left:22px;' action='./ExerciseView.jsp' method='get'>";
					html += "    <input type='hidden' name='exerciseNum' value=" + num + " />";
					html += "    <input type='submit' value='Exercise " + num + "' style=\"border:3px solid "
							+ colors.get(num) + "\"/>";
					html += "</form>";
				}
			} catch (SQLException e) {
				html += "<p class='default_error_text'>Ops! something went wrong: excerice are temporarly non available!</p>";
			}
		} else {
			html += "<p style='margin-left:22px; color:#FFF'>Login to see the exercises</p>";

		}
		html += "</div>";
		html += "<a href='./ForumView.jsp'>Comments</a> <a href='./Contacts.jsp'>Contact</a></div>";

		return html;
	}

	public static String getTopNavigation(boolean isLoggedIn, String userName) {
		String html = "";
		html += "<div class='topnav'>";
		html += "<div class='login-container'>";
		if (isLoggedIn) {
			html += "<div style='display: flex;'>";
			html += "  <img src='../images/user_0.png' width='48' height='48'/>";
			html += "  <p class='hallo_user'>Hello, " + userName + "!!</p>";
			html += "  <form class='logout' action='./LogoutAppl.jsp' method='get'>";
			html += "    <input type='image' alt='Submit' name='logout' value='logout' src='../images/logout.jpg'  width='48' height='48'/>";
			html += "    </form>";
			html += "</div>";
		} else {
			html += "<form action='LoginAppl.jsp'>";
			html += "   <input type='text' placeholder='Username' name='userid'>";
			html += "   <input type='password' placeholder='Password' name='password'>";
//			html += "<button type='submit' >Login</button>";
			html += "    <input type='image' alt='Submit' name='login' value='logout' src='../images/login.png'  width='48' height='48'/>";

			html += "</form>";
		}
		html += "</div></div>";
		return html;
	}

	public static String getNavigationElements(boolean isLoggedIn, String userName) throws SQLException {
		return getSideNavigation(isLoggedIn, userName) + "<br>" + getTopNavigation(isLoggedIn, userName);
	}

	public static String getNavigationCourse(String currentPage) {
		int position = 0;
		for (; position < lessonList.length; ++position) {
			if (lessonList[position].equals(currentPage)) {
				break;
			}
		}

		String previous = (position - 1 < 0) ? null : lessonList[position - 1];
		String next = (position + 1 >= lessonList.length) ? null : lessonList[position + 1];

		String html = "   <div style=\"display: flex;\">\n";
		if (previous != null) {
			html += "            <form action='../jsp/Lesson.jsp' method='get'>\n"
					+ "                <input type='hidden' name='currentPage' value='" + previous + "'>"
					+ "                <input type='image' alt='Submit' value='home'\n"
					+ "                    src='../images/previous.png' width='48' height='48' />\n"
					+ "            </form>\n";
		}
		html += "            <form action='../jsp/HomePageView.jsp' method='get'>\n"
				+ "                <input type='image' alt='Submit' value='home'\n"
				+ "                    src='../images/home.png' width='48' height='48' />\n" + "            </form>\n";
		if (next != null) {
			html += "            <form action='../jsp/Lesson.jsp' method='get'>\n"
					+ "                <input type='hidden' name='currentPage' value='" + next + "'>"
					+ "                <input type='image' alt='Submit' value='home'\n"
					+ "                    src='../images/next.png' width='48' height='48' />\n"
					+ "            </form>\n";
		}
		html += "        </div>";
		return html;
	}

	public static String getExerciseResult(boolean compilationError, boolean testError) {
		String html = "";
		html += "<div style='display: flex;'>";
		html += "  <p class='hallo_user'>Compilation: </p>";
		if (compilationError) {
			html += "  <img src='../images/error.png' width='48' height='48'/>";
		} else {
			html += "  <img src='../images/ok.png' width='48' height='48'/>";
		}
		html += "</div><br>";

		html += "<div style='display: flex;'>";
		html += "  <p class='hallo_user'>Test: </p>";
		if (testError) {
			html += "  <img src='../images/error.png' width='48' height='48'/>";
		} else {
			html += "  <img src='../images/ok.png' width='48' height='48'/>";
		}
		html += "</div><br>";

		return html;
	}

}
