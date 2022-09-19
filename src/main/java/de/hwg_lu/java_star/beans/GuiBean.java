package de.hwg_lu.java_star.beans;

import java.sql.SQLException;

import de.hwg_lu.java_star.jdbc.ExcerciseDB;

public class GuiBean {

    public static String getSideNavigation() throws SQLException {
        String html = "";
        html += "<span class='closedSideNav' onclick='openNav()'>&#9776; JAVA STAR</span>\n"
                + "    <div class='sidenav' id='mySidenav' style='width: 0'>\n"
                + "    <a href='javascript:void(0)' class='closebtn' onclick='closeNav()'>&times;</a>";
        html += "    <a href='./HomePageView.jsp'>Home</a>";
        html += "    <a onclick=\"toggleExcericeSideBar('courseSideBar')\" href='#'>Course</a>";
        html += "    <div id='courseSideBar' style='display: none'>";
        html += "        <!-- ============================================= -->";
        html += "        <form action='../html/JavaBasic.html' method='get'>";
        html += "            <input type='hidden' name='exerciseNum' value='1'> <input";
        html += "                type='submit' value='Java Basics' />";
        html += "        </form>";
        html += "        <form method='get'>";
        html += "            <input type='hidden' name='exerciseNum' value='1'> <input";
        html += "                type='submit' value='Variables and Data Types' />;";
        html += "        </form>";
        html += "        <form method='get'>";
        html += "            <input type='hidden' name='exerciseNum' value='1'> <input";
        html += "                type='submit' value='Input and Output in Java' />";
        html += "        </form>";
        html += "                <form method='get'>";
        html += "            <input type='hidden' name='exerciseNum' value='1'> <input";
        html += "                type='submit' value='Operators in Java' />";
        html += "        </form>";
        html += "                <form method='get'>";
        html += "            <input type='hidden' name='exerciseNum' value='1'> <input";
        html += "                type='submit' value='Flow Control in Java'/>";
        html += "                </form>";
        html += "                <form method='get'>";
        html += "            <input type='hidden' name='exerciseNum' value='1'> <input";
        html += "                type='submit' value='Arrays in Java' />";
        html += "        </form>";
        html += "        <!-- ============================================= -->";
        html += "    </div>";
        html += "    <a onclick=\"toggleExcericeSideBar('exSideBar')\" href='#'>Exercises</a>";
        html += "    <div id='exSideBar' style='display: none'>";
               
        ExcerciseDB ex = new ExcerciseDB();
        int numTot = ex.getNumberExcerice();
                for (int num = 1; num <= numTot; ++num) {
                    html += "<form action='./ExerciseView.jsp' method='get'>";
                    html += "    <input type='hidden' name='exerciseNum' value=" + num + " />";
                    html += "    <input type='submit' value='Exercise " + num + "' />";
                    html += "</form>";
                }
        html += "</div>";
        html += "<a href='./ForumView.jsp'>Comments</a> <a href='#'>Contact</a></div>";
        
        
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
                html += "   <input type='text' placeholder='Password' name='password'>";
                html += "<button type='submit'>Login</button>";
                html += "</form>";
            }
           html += "</div></div>";
        return html;
    }
    
}
