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
			console.log(typeof (question))
			var itemsToShow = parseInt(4 + Math.random()*10);
			console.log(itemsToShow);
			var items = {};
			for(var i=0; i < 4; i++){
				items[i] = 'ITEM ' + (i+1);
			}
		  $('#canvas').rouletteWheel({
		    items : items,
		    selected : function(key, value){
				var question='';
				var selectAnswerA='';
				var selectAnswerB=''; 
				var correctAnswer=''; 
				var userAnswer=''; 
				console.log(value.substr(5));
		    	switch (value.substr(5)){
		    		case '1':
		    			question = '每個人的生長發展速度都相同。';
		    			selectAnswerA = 'o';
		    			selectAnswerB = 'x';
		    			correctAnswer = 'A'
		    			break;
		    		case '2':
		    			question = '人生每個階段各有不同的身體活動特徵。';
		    			selectAnswerA = 'o';
		    			selectAnswerB = 'x';
		    			correctAnswer = 'B'
		    			break;
		    		case '3':
		    			question = '青年期與兒童期相比，青年期時身體的肌肉增加，所以力量較大。';
		    			selectAnswerA = 'o';
		    			selectAnswerB = 'x';
		    			correctAnswer = 'B'
		    			break;
		    		case '4':
		    			question = '即使河川受到汙染，只要不吃受到汙染的動、植物，就不會對我們有危害。';
		    			selectAnswerA = 'o';
		    			selectAnswerB = 'x';
		    			correctAnswer = 'B'
		    			break;
		    	}
	     		swal.fire({
	     			title: question,
	     			icon: 'question',
	     			showCancelButton: true,
	     			confirmButtonColor: '#D2691E',
	     			cancelButtonColor: '#A9A9A9',
	     			confirmButtonText: selectAnswerA,
	     			cancelButtonText: selectAnswerB,
	     		}).then((result) => {
	     				if (result.isConfirmed){
	     					userAnswer = 'A';
	     					if (userAnswer == correctAnswer){
	     		     			swal.fire({
	     		     				title: '答對',
	     		     				icon: 'success',
	     		     			})
	     					}else{
     		     				swal.fire({
     		     					title: '錯誤',
     		     					icon: 'error',
     		     				})
	     				 	}	
 	     				}else{
	     					userAnswer = 'B';
	     					if (userAnswer == correctAnswer){
	     		     			swal.fire({
	     		     				title: '答對',
	     		     				icon: 'success',
	     		     			})
	     					}else{
     		     				swal.fire({
     		     					title: '錯誤',
     		     					icon: 'error',
     		     				});	
	     				 	}
 	     				}
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