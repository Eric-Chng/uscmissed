<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>
            MissedSC | Homepage
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
            /* @font-face {
                font-family: 'Adagio Sans';
                src: url('Adagio_Sans-SemiBold.eot');
                src: local('Adagio Sans SemiBold'), local('Adagio_Sans-SemiBold'),
                    url('Adagio_Sans-SemiBold.eot?#iefix') format('embedded-opentype'),
                    url('Adagio_Sans-SemiBold.woff2') format('woff2'),
                    url('Adagio_Sans-SemiBold.woff') format('woff'),
                    url('Adagio_Sans-SemiBold.ttf') format('truetype');
                font-weight: 600;
                font-style: normal;
            } */
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
            .submit-box {
                float: right;
                font-size: 1.1em;
                padding: 0.7vw;
                padding-right: 0;
                width: 2vw;
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
                color: #FDBB63;
               font-size: 1.1em;
               border: none;
               background-color: transparent;
               cursor: pointer;
               outline: none;
            }
            .comment-button {
                color: #FDBB63;
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
                color: #FDBB63;
            }
            #post-content {
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
           #post-content::placeholder {
                color: white;
           }
        </style>
    </head>
    <body>
    	<% Cookie[] cookies = request.getCookies();
   			String newname = "";
  			boolean isLoggedIn = false;
   			if(cookies!=null) { 
   	   			for(Cookie aCookie : cookies) {
   	   				if(aCookie.getName().equals("username")) {
   	   					isLoggedIn = true; 
   	   					break;
   	   				}
   	   			}
   			} 
   			User myuser = request.getAttribute("user"); 
   		%>
        <div id="leftSidebar">
            <a href="homepage.jsp"><img src = "images/logo.png"></a>
            <div class="link-current"><a href="homepage.jsp">Home</a></div>
            <div class="link" id ="signin">Account Login</div>
            <div class="link"><a href="contact_form.jsp">Contact Us</a></div>
            <%
            if(isLoggedIn == true && user.status=="admin") { %>
            	<div class="link"><a href="admin.jsp">Admin Review</a></div>
            <% } %>
            <div class="submitPost">Submit Post</div>
        </div>
        <div id="main">
            <div id="header">
                <h1>Home</h1>
            </div>
            <!-- Chat bubble credits to: https://codepen.io/Founts/pen/AJyVOr?editors=1100 -->
            <div class="left-bubble tri-right left-in">
                <div class="talktext">
                    <textarea id="post-content" name="newpost" placeholder="Type your submission here"></textarea>
                    <div class="submit-box">
                        <button type="submit" id="submit-button"><i class="fa-regular fa-paper-plane"></i></button>
                    </div>
                </div>
            </div>
            <% ArrayList<Post> myposts = (ArrayList<Post>)request.getAttribute("posts");
            	if(myposts.isEmpty() == false) {
            		for(int i=0; i<myposts.size(); ++i) { 
            			int postid = myposts.get(i).post_id;
            			String postcontent = myposts.get(i).postContent;
            			int likes = myposts.get(i).likes;
            			int comments = myposts.get(i).comments.size();
            			ArrayList<String> mycomments = (ArrayList<String)>myposts.get(i).comments;
            			boolean ifliked = myposts.get(i).likedByUser;
            		%>
		            <div class="post">
		            	<a href=<%="expand.jsp?id=" + postid + "&content=" + postcontent + "&likes=" + likes + "&comments=" + comments + "&mycomments=" + mycomments + "&iflike=" + ifliked %>><%=postid %></a>
		                <div class="right-bubble tri-right right-in">
		                    <div class="talktext">
		                      <p><%=postcontent %></p>
		                    </div>
		                </div>
		                <div class="stats">
		                    <table>
		                        <tr>
		                            <td><%=likes %>
		                            <% if(ifliked==true) { %>
		                           		<button type="like" class="like-button"><i class="fa-solid fa-heart"></i></button>
		                                <% } else { %>
		                                <button type="like" class="like-button"><i class="fa-regular fa-heart"></i></button>
		                                <% } %>
		                                </td>
		                            <td> </td>
		                            <td><%=comments %> <button type="comment" class="comment-button"><i class="fa-regular fa-comments"></i></button></td>
		                        </tr>
		                    </table>
		                </div>
		            </div>
	            	<% } %>
	            <% } else if (myposts.isEmpty == true){ %>
	            <p>No posts currently</p>
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