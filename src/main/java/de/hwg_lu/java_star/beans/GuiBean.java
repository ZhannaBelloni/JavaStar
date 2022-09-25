package de.hwg_lu.java_star.beans;

import java.sql.SQLException;
import java.util.ArrayList;

import de.hwg_lu.java_star.jdbc.ExcerciseDB;
import de.hwg_lu.java_star.jdbc.StatisticsDB;

/**
 * Utility class to retrieve common html code for jsp pages.
 * 
 */
public class GuiBean {

	/**
	 * Ordered list of lesson saved in html directory.
	 * NOTE: update it if you insert new lessons.
	 */
	public static String[] lessonList = { "JavaBasic.html", "VariableAndDataType.html", "OperatorsInJava.html", "ArraysInJava.html"};

	/**
	 * Generate code for the side navigation.<br>
	 * 
	 * <ul>
	 * <li>The side navigation can be toggle with a click</li>
	 * <li>Exercises list is with colored button depending on the result of the exercise. 
	 * </ul>
	 * 
	 * The code is customized for users that are logged in
	 * @param isLoggedIn user is logged in
	 * @param userName username of the user: it is unique.
	 * @return html code for the sidenavigation
	 * @throws SQLException
	 */
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

	/**
	 * Generate html code for the top navigation.<br>
	 * <ul>
	 * <li>if the user is logged in, then a welcome message and a picture is displayed</li>
	 * <li>otherwise a form to login is displayed</li>
	 * </ul>
	 * @param isLoggedIn
	 * @param userName
	 * @return html code for the Top navigation.
	 */
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

	/**
	 * Utility to get the combination code for top and side navigation.
	 * @param isLoggedIn
	 * @param userName
	 * @return html code for top and side navigation bars.
	 * @throws SQLException
	 */
	public static String getNavigationElements(boolean isLoggedIn, String userName) throws SQLException {
		return getSideNavigation(isLoggedIn, userName) + "<br>" + getTopNavigation(isLoggedIn, userName);
	}

	/**
	 * Generate the buttons previous|home|next to be displayed in a Lesson page.<br>
	 * 
	 * It search for next and previous page if exists and generate the code.
	 * 
	 * @param currentPage name of the current page, as it is saved in html directory
	 * @return html code for the navigation between lessons.
	 */
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

	/**
	 * Utility to retrieve the code to be displayed in ExerciseResultView.jsp to show the result of an exercise
	 * @param compilationError true if there was a compilation error
	 * @param testError true if there was an error runnign the tests.
	 * @return html code to show result of an exercise.
	 */
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
