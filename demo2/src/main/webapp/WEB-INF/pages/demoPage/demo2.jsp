<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/js/rouletteWheel.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script type="text/javascript">
		$(function(){

			var itemsToShow = parseInt(4 + Math.random()*10);
			console.log(itemsToShow);
			var items = {};
			for(var i=0; i < 5; i++){
				items[i] = 'ITEM ' + (i+1);
			}
		  $('#canvas').rouletteWheel({
		    items : items,
		    selected : function(key, value){
	     		swal.fire({
	     			title: '題目?',
	     			width: 600,
// 	     			text: "You won't be able to revert this!",
	     			icon: 'question',
	     			showCancelButton: true,
	     			confirmButtonColor: '#D2691E',
	     			cancelButtonColor: '#A9A9A9',
	     			confirmButtonText: '選項A',
	     			cancelButtonText: '選項B',
	     			confirmButtonSize:'sm'
	     		})  
		    },
		    spinText : 'Click Me',
		  });

		});
	</script>
	<title></title>
</head>
<body>
	<H1 style="text-align: center;"> 抽獎活動</H1>
	<div align="center">
		<canvas id="canvas" width="1000" height="1000"></canvas>
	</div>

</body>
</html>