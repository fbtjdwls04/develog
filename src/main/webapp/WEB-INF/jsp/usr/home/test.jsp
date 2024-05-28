<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   	
   	<c:set var="pageTitle" value="develog" />
   	
   	<%@ include file="../common/head.jsp" %>

   	<div class="w-[1500px] h-[800px] relative mt-[150px] mx-auto flex flex-col">
   		<nav class="flex border-black border bg-white">
   			<button class="btn btn-sm btn-outline">DVD 튕기기</button>
   		</nav>
   		<canvas id="canvas" width='1500' height='800' class="border-black block z-index"></canvas>
   	</div>
   	
   	<script>
   		const canvas = document.getElementById('canvas');
   		const ctx = canvas.getContext('2d');
   		const img = new Image();
   		img.src = 'https://w7.pngwing.com/pngs/981/433/png-transparent-dvd-video-logo-dvd-text-sign-symbol.png';
   		const audio = new Audio('/resource/colide.mp3')
   		
		const dvd = {
			x : 0,
			y : 0,
			speed_x : 2,
			speed_y : 2,
			width : 150,
			height : 70
		}
   		
   		function clearCanvas(){
			ctx.fillStyle = 'white';
   			ctx.fillRect(0,0,canvas.width, canvas.height);
   		}
   		
   		function update(){
   			dvd.x += dvd.speed_x;
   			dvd.y += dvd.speed_y;
   			if(dvd.x <= 0 || dvd.x >= canvas.width - dvd.width){
   				audio.load();
   				audio.play();
   				dvd.speed_x = -dvd.speed_x;
   			}
   			if(dvd.y <= 0 || dvd.y >= canvas.height - dvd.height){
   				audio.load();
   				audio.play();
   				dvd.speed_y = -dvd.speed_y;
   			}
   		}
   		
   		function draw(){
   			ctx.fillStyle = 'blue';
   			ctx.drawImage(img ,dvd.x, dvd.y, dvd.width, dvd.height);
   		}
   		
   		function gameLoop(){
   			clearCanvas()
   			update();
   			draw();
   			requestAnimationFrame(gameLoop)
   		}
   		
   		gameLoop();
   	</script>
	
	<%@ include file="../common/foot.jsp" %>
	
