<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="util.Post" %>
<%@page import="util.User" %>

<!DOCTYPE html>

<html>
    <head>
        <title>
            MissedSC | Expanded Post
        </title>
        <script src="https://kit.fontawesome.com/51b017a2ee.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/sidebar.css">
        <style>
        	@font-face {
                font-family: 'Adagio Sans';
                src: url('Adagio_Sans-Regular.eot');
                src: local('Adagio Sans'), local('Adagio_Sans-Regular'),
                    url('Adagio_Sans-Regular.eot?#iefix') format('embedded-opentype'),
                    url('Adagio_Sans-Regular.woff2') format('woff2'),
                    url('Adagio_Sans-Regular.woff') format('woff'),
                    url('Adagio_Sans-Regular.ttf') format('truetype');
                font-weight: normal;
                font-style: normal;
            }
        	body {
               margin: 0px;
               background-color: #FFF7F0;
               color: #D56262;
               font-family: 'Adagio Sans';
           }
           #header {
               padding-left: 1.5vw;
               padding-right: 1.5vw;
               line-height: 2em;
               margin: auto;
               color: #D56262;
               /* text-shadow:
                0 0 7px #ebadad,
                0 0 10px #EE8B8B,
                0 0 21px #fff; */
           }
           #main {
               width: 45.3vw;
               margin-left: 23.3vw;
               margin-right: 10px;
               height: 100%;
               padding: 2vw;
           }
           #current-post {
               margin-bottom: 40px;
           }
           .left-bubble {
               margin-left: 2.6vw;
                margin-top: 0.7vw;
                margin-bottom: 1.5vw;
                display: inline-block;
                position: relative;
                width: 40.6vw;
                height: auto;
                border-radius: 25px;
                background-color: #D56262;
            }
            .right-bubble {
                margin-left: 2.6vw;
                margin-top: 0.7vw;
                margin-bottom: 1.5vw;
                display: inline-block;
                position: relative;
                width: 40.6vw;
                height: auto;
                border-radius: 25px;
                background-color: #FDBB63;
            }
            .talktext {
                color: white;
                padding: 1em;
                text-align: left;
                line-height: 1.5em;
            }
            .talktext p {
                /* Removes webkit p margins */
                -webkit-margin-before: 0em;
                -webkit-margin-after: 0em;
            }
            .tri-right.left-in:after{
                content: ' ';
                position: absolute;
                width: 0;
                height: 0;
                left: -1.5vw;
                right: auto;
                top: 1.3vw;
                bottom: auto;
                border: 1vw solid;
                border-color: #D56262 #D56262 transparent transparent;
            }
            .tri-right.right-in:after {
                content: ' ';
                position: absolute;
                width: 0;
                height: 0;
                left: auto;
                right: -1.5vw;
                top: 1.3vw;
                bottom: auto;
                border: 1vw solid;
                border-color: #FDBB63 transparent transparent #FDBB63;
            }
            .submit-button {
                float: right;
                font-size: 1.1em;
                padding: 10px;
                padding-right: 0px;
                width: 30px;
            }
            button {
                color: white;
                font-size: 1.1em;
                border: none;
                background-color: transparent;
                cursor: pointer;
                outline: none;
            }
            #submit-button {
                color: white;
                font-size: 1.1em;
                border: none;
                background-color: transparent;
                cursor: pointer;
                outline: none;
            }
            .like-button {
                color: #D56262;
               font-size: 1.1em;
               border: none;
               background-color: transparent;
               cursor: pointer;
               outline: none;
            }
            .comment-button {
                color: #D56262;
               font-size: 1.1em;
               border: none;
               background-color: transparent;
               cursor: pointer;
               outline: none;
            }
            .stats {
                float: right;
                font-size: 1.1em;
                margin-top: -0.7vw;
                margin-right: 1.5vw;
                color: #D56262;
            }
            #comments-header {
               font-size: 1.3em;
               margin-left: 1.5vw;
               margin-bottom: 1vw;
            }
           #comment-content {
                width: 38vw;
               height: auto;
               border: none;
               outline: none;
               resize: none;
               background-color: transparent;
               font-family: 'Adagio Sans';
               font-size: 1em;
               color: white;
            }
           #comment-content::placeholder {
                color: white;
           }
        </style>
    </head>
    <body>
        <div id="leftSidebar">
            <a href="homepage.jsp"><img src = "images/logo.png"></a>
            <div class="link-current"><a href="homepage.jsp">Home</a></div>
            <div class="link" id ="signin">Account Login</div>
            <div class="link"><a href="contact_form.jsp">Contact Us</a></div>
            <%-- <%
            if(user.status=="admin") { %> --%>
            	<div class="link"><a href="admin.jsp">Admin Review</a></div>
            <%-- <% } %> --%>
            <div class = "submitPost">Submit Post</div>
        </div>
        <% int id = (int) request.getAttribute("id");
    	   String content = (String) request.getAttribute("content");
    	   int likes = (int) request.getAttribute("likes");
    	   int comments = (int) request.getAttribute("comments");
    	   ArrayList<String> mycomments = (ArrayList<String>) request.getAttribute("mycomments");
    	   boolean ifliked = (boolean) request.getAttribute("ifliked");%>
        <div id="main">
            <div id="header">
                <h1>Post #<%=id %></h1>
            </div>
            
            <div id="current-post">
                <!-- Chat bubble credits to: https://codepen.io/Founts/pen/AJyVOr?editors=1100 -->
                <div class="left-bubble tri-right left-in">
                    <div class="talktext">
                        <p><%=content %></p>
                    </div>
                </div>
                
                <form action="Like" method="POST">
                <div class="stats">
                    <table>
                        <tr>
                            <td><%=likes %>
                            <% if(ifliked==true) { %>
                           		<button type="submit" class="like-button"><i class="fa-solid fa-heart"></i></button>
                                <% } else { %>
                                <i class="fa-regular fa-heart"></i>
                                <% } %>
                                </td>
                            <td> </td>
                            <td><%=comments %><i class="fa-regular fa-comments"></i></td>
                        </tr>
                    </table>
                </div>
                </form>
                
           	</div>
           	
            <p id="comments-header">Comments</p>
            <form action="Comment" method="POST">
	           <div class="right-bubble tri-right right-in">
	               <div class="talktext">
	                   <textarea id="comment-content" name="comment" placeholder="Type your comment here"></textarea>
	                   <div class="submit-button">
	                       <button type="submit"><i class="fa-regular fa-paper-plane"></i></button>
	                   </div>
	               </div>
	           </div>
	        </form>
	        
	        <%for(int i=0; i<comments; ++i) { %>
	            <div class="right-bubble tri-right right-in">
	                <div class="talktext">
	                  <p><%= mycomments.get(i) %></p>
	            </div>
           <% } %>
        </div>
        
        <div id="rightSidebar">
            <input type="text" id="searchbar" placeholder="Search.."><button type="submit" id="search-button"><i class="fa fa-search"></i></button>
            <div class = "staticText">Trending Posts</div>
            <div class = "trendingContainer">
                <div class = "post"></div>
            </div>
        </div>
        
    </body>
</html>