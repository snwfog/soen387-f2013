<%@ page import="com.charlescy.model.Book" %>
<%@ page import="com.charlescy.model.Library" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="java.net.URLDecoder" %>
<!doctype html>
<html lang="en-US">
<head>
  <meta charset="UTF-8">
  <title>BOOK - SOEN387</title>
  <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
  <link rel="stylesheet" href="assets/css/style.css" />
  <script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <% Book b = (Book)request.getAttribute("book"); %>

  <div class="row">
    <ol class="breadcrumb">
      <li><a href="index.html">Home</a></li>
      <li><a href="search.jsp">Search</a></li>
      <li><a class="active" href=""><%= b.getTitle() %></a></li>
    </ol>

    <h1><%= b.getTitle() %></h1>

      <% String labelClass = (b.getAvailability()) ? "success" : "danger"; %>
      <% String labelValue = (b.getAvailability()) ? "Available" : "Out of Stock"; %>

    <div class="table-responsive">
      <table class="table table-striped table-bordered table-condensed">
        <tr><td>Title</td><td><%= b.getTitle() %></td>
          <td rowspan="6"><img src="<%= b.getImageLinks().getThumbnail() %>" alt="<%= b.getTitle() %>"/></td></tr>
        <tr><td>Publisher</td><td><%= b.getPublisher() %></td></tr>
        <tr><td>Published Date</td><td><%= b.getPublishedDate() %></td></tr>
        <tr><td>Author(s)</td><td><%= StringUtils.join(b.getAuthors(), ", ")%></td></tr>
        <tr><td>Categories</td><td><%= StringUtils.join(b.getCategories(), ", ")%></td></tr>
        <tr><td>Availability</td>
          <td>
            <span class="label label-<%= labelClass %>"><%= labelValue %></span>
          </td>
        </tr>
        <tr><td colspan="2" style="font-weight: normal !important;"><%= b.getDescription() %></td></tr>
      </table>
    </div>
  </div>


  <hr />
  <footer>
    Chao Yang - 5682061 - SOEN 387 Fall 2013
  </footer>
</div>

</body>
</html>