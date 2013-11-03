<!DOCTYPE html>
<html>
<head>
  <title>SEARCH - SOEN387</title>
  <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
  <link rel="stylesheet" href="assets/css/style.css" />
  <script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <div class="row">
    <ol class="breadcrumb">
      <li><a href="index.html">Home</a></li>
      <li><a class="active" href="search.jsp">Search</a></li>
    </ol>
    <form class="form-inline" role="form" action="search.do" method="get">
      <div class="form-group">
        <label class="sr-only" for="input-search">Search</label>
        <input type="text" name="q" class="form-control" id="input-search" placeholder="Title, Author..." autocomplete="false">
      </div>
      <button type="submit" class="btn btn-primary">Search</button>
    </form>
  </div>

  <hr />
  <footer>
    Chao Yang - 5682061 - SOEN 387 Fall 2013
  </footer>
</div>

</body>
</html>