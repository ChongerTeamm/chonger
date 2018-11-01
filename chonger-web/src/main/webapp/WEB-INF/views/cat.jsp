<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

	<head>
		<meta charset="utf-8" />
		<!-- 优先使用 IE 最新版本和 Chrome -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<!-- 为移动设备添加 viewport -->
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
		<!--忽略页面中的数字识别为电话，忽略email识别-->
		<meta name="format-detection" content="telphone=no, email=no" />
		<title>${itemCat.name }-虫二推荐</title>
		<link rel="stylesheet" type="text/css" href="../css2/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="../css2/base.css" />
		<!--[if lt IE 9]>
			<script type="text/javascript" src="js/html5shiv.min.js"></script>
			<script type="text/javascript" src="js/respond.min.js"></script>
	    <![endif]-->
	</head>

	<body>
		<!--导航-->
		<nav class="navbar navbar-default navbar-fixed-top top-box" style="background: #fff;">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				        <span class="sr-only">Toggle navigation</span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				    </button>
					<a class="navbar-brand" href="#"><img src="http://image.chonger.com/img/logo.png" class="img-responsive" /></a>
				</div>

				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right top-nav" >
						<li>
							<a href="http://www.chonger.com/index.html">
								首页
								<span class="sr-only">(current)</span>
							</a>
						</li>
						<li  >
							<a href="#">热门景点</a>
						</li>
					    <li class="active"><a href="#">旅游胜地</a></li>
						<li>
							<a href="#">虫二精选</a>
						</li>
						<li>
							<a href="#">出行贴士</a>
						</li>	
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container -->
		</nav>
		<!--/ 导航-->

		${itemCatDesc.catDesc }
							<nav aria-label="Page navigation" class="page-box">
								<ul class="pagination">
									<li>
										<a href="#" aria-label="Previous">
											<span aria-hidden="true">首页</span>
										</a>
									</li>
									<li class="active">
										<a href="#">1</a>
									</li>
									<li>
										<a href="#">2</a>
									</li>
									<li>
										<a href="#">3</a>
									</li>
									<li>
										<a href="#">4</a>
									</li>
									<li>
										<a href="#">5</a>
									</li>
									<li>
										<a href="#" aria-label="Next">
											<span aria-hidden="true">尾页</span>
										</a>
									</li>
								</ul>
							</nav>
		<footer class="background-two">
        <div class="container">
            <div class="row">
                <div class="clean-footer-content">

                    <div class="clean-footer-logo wow bounceIn animated" data-wow-offset="0" style="visibility: visible; animation-name: bounceIn;">
                        <a href="index.html">
                            <img id="logo-footer" src="http://image.chonger.com/img/logo.png" alt="clean">
                        </a>
                    </div>
					
					<p class="copy">Copyright © 2018-2028虫二景点| 旅游推荐</p>
					<p class="copy">浙ICP备12345678号-9&nbsp;&nbsp;&nbsp; | 虫二景点：<a href=# target="_blank">虫二景点</a>
</p>

                </div>
            </div>
        </div>
    </footer>

		<script src="../js2/jquery-1.11.0.js"></script>
		<script src="../js2/bootstrap.min.js"></script>

	</body>

</html>