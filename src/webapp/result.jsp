<%@ page import="com.charlescy.model.Book" %>
<%@ page import="com.charlescy.model.Library" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="java.net.URLDecoder" %>
<!doctype html>
<html lang="en-US">
<head>
  <meta charset="UTF-8">
  <title>RESULT - SOEN387</title>
  <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
  <link rel="stylesheet" href="assets/css/style.css" />
  <script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <div class="row">
    <ol class="breadcrumb">
      <li><a href="index.html">Home</a></li>
      <li><a href="search.jsp">Search</a></li>
      <li><a class="active" href="">Result</a></li>
    </ol>
    <% Library lib = (Library)request.getAttribute("library"); %>
    <% List<Book> catalogue = lib.getCatalogue(); %>

    <h1>Results</h1>
    <p>
      You searched for the keyword
      "<%= URLDecoder.decode(request.getParameter("q"), "UTF-8") %>",
      and we've found <%= lib.getTotalItems() %> record(s).
      Here are the closest <em><b><%= catalogue.size() %></b></em> result(s) for you.
    </p>

    <% for (Book b : catalogue) { %>
    <nav class="navbar navbar-default" role="navigation">
      <div class="navbar-header">
        <a class="navbar-brand" href="book.do?id=<%= b.getId() %>">
          <span style="margin-right: 10px" class="glyphicon glyphicon-book"></span>
          <%= b.getTitle() %>
        </a>
      </div>

      <% String labelClass = (b.getAvailability()) ? "success" : "danger"; %>
      <% String labelValue = (b.getAvailability()) ? "Available" : "Out of Stock"; %>
      <ul class="nav navbar-nav navbar-right">
        <li>
          <% if (labelValue.equalsIgnoreCase("Available")) { %>
            <button type="button" class="btn btn-success navbar-btn">Buy</button>
          <% } else { %>
            <button type="button" class="btn btn-danger navbar-btn" disabled>Buy</button>
          <% } %>
        </li>
        <li>
          <p class="navbar-text">
            <span class="label label-<%= labelClass %>"><%= labelValue %></span>
          </p>
        </li>
      </ul>
    </nav>
    <% } %>
  </div>


  <hr />
  <footer>
    Chao Yang - 5682061 - SOEN 387 Fall 2013
  </footer>
</div>

</body>
</html>