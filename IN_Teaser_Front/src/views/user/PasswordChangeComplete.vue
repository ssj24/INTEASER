<template>
  <div class="app login">
    <h1 class="app_title">비밀번호가 성공적으로 변경되었습니다.</h1>
    <img src="@/assets/images/key (1).svg" alt="">
    <button><router-link class="btn--text" v-bind:to="{name:'Login'}"><p> 로그인하러 가기</p></router-link></button>
  </div>
</template>

<script>
  export default {
    
  }
</script>

<style scoped> 
.app {
  margin-top: 40px;
}
.app h1 {
  font-size: 2.4em;
}
.app img {
  text-align: center;
  display: block;
  width: 240px;
  margin: 50px auto 50px;
}
.app button a {
  text-decoration: none;
  color: #989898;
}
.app button a:hover {
  color: rgb(5, 59, 11);
}
.app button a:active {
  color: rgb(5, 59, 11);
}
.app button p {
  margin: 0;
}
</style>