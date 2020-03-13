<template>
<header>
    <h1>Sticky Header Pow!</h1>
    <nav>
      <a href="">Home</a>
      <a href="">About</a>
      <a href="">Gallery</a>
      <a href="">Contact</a>
    </nav>
  </header>
</template>

<script>
    export default {
        watch: {
            header: function(v) {
                window.scroll(function() {
                    const winTop = window.scrollTop();
                    if (winTop >= 30) {
                        window.body.addClass("sticky-header");
                    } else {
                        window.body.removeClass("sticky-header")
                    }
                })
            }
        }
    }
</script>
<style lang="scss" scoped>
    body{
	background-color: #ecf0f1;
	font-family: helvetica, arial, sans-serif;
	font-size: 16px;
	padding-top: 330px;
	transition: padding-top .5s ease;
}
header{
	width: 100%;
	height: 300px;
	background-color: #3498db;
	text-align: center;
	position: relative;
	position: fixed;
	top: 0;
	overflow: hidden;
	transition: all .5s ease;
	h1{
		font-size: 42px;
		color: #fff;
		line-height: 230px;
		text-transform: uppercase;
		font-weight: 100;
		transition: all .3s ease;
	}
	nav{
		position: absolute;
		bottom: 0;
		height: 60px;
		line-height: 60px;
		width: 100%;
		background-color: rgba(0,0,0,.1);
		a{
			color: #fff;
			display: inline-block;
			padding: 10px 15px;
			line-height: 1;
			text-decoration: none;
            border-radius: 2px;
        &:hover{
          box-shadow: 0 0 0 1px #fff;
        }
		}
	}
}
h2{
	font-size: 34px;
	text-transform: uppercase;
	font-weight: 100;
	line-height: 2;
	color: #2c3e50;
}
p{
	margin-bottom: 2rem;
	line-height: 2;
	color: #7f8c8d;
}
</style>
