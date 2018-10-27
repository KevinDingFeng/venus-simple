<!DOCTYPE html>
<html class="no-js" lang="zh">
	<head lang="en">
		<meta charset="UTF-8">
		<@block name="title"></@block>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="format-detection" content="telephone=no">
		<meta name="renderer" content="webkit">
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<link rel="alternate icon" type="image/png" href="/assets/i/favicon.png" />
		<link rel="stylesheet" href="/assets/css/amazeui.min.css"/>
		<script src="/assets/js/jquery-2.1.4.min.js"></script>
		<style>
			.header {
		      text-align: center;
		    }
		    .header h1 {
		      font-size: 200%;
		      color: #333;
		      margin-top: 30px;
		    }
		    .header p {
		      font-size: 14px;
		    }
		</style>
		<@block name="head"></@block>
	</head>
	<body>
		<!--[if lte IE 9]>
		<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
		  以获得更好的体验！</p>
		<![endif]-->
		<@block name="body"></@block>
		<@block name="foot"></@block>
		<!--[if lt IE 9]>
		<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
		<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
		<script src="/assets/js/amazeui.ie8polyfill.min.js"></script>
		<![endif]-->
		
		<!--[if (gte IE 9)|!(IE)]><!-->
		  <script src="/assets/js/jquery-2.1.4.min.js"></script>
		<!--<![endif]-->
	</body>
</html>
