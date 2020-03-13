<template>
  <div class="">
    <div class="warpper404">
      <h1>404</h1>
      <p>찾으시는 페이지가 존재하지 않습니다.</p>
      <button @click="backgo" class="back-button"><p><i class="fas fa-lg fa-arrow-left"></i>뒤로가기</p></button>
    </div>
  </div>
</template>

<script>

export default {
  methods: {
    backgo() {
      var router = this.$router;
      var route = this.$route.params.route;
      if (route == 'Join') {
          router.push({
              name: "Join",
              params: {
                  "nickName": this.$route.params.nickName,
                  "email": this.$route.params.email,
                  "password": this.$route.params.password,
                  "route": this.$route.name
              }
          })
      } else if (route == 'Login') {
          router.push({
              name: "Login",
              params: {
                  "email": this.$route.params.email,
                  "route": this.$route.name
              }
          })
      } else if (route == 'FindPassword') {
          router.push({
              name: "FindPassword",
              params: {
                  "email": this.$route.params.email,
                  "route": this.$route.name
              }
          })
      } else if (route === undefined) {
          router.push({
            name: 'Main'
          })
      } else {
          router.push({
              name: "Login",
              params: {
                  "route": this.$route.name
              }
          })
      }
    }              
}
}
</script>
<style scoped>
.warpper404 {
  width: 800px;
  margin: 50px auto 20px;
  text-align: center;
}
h1 {
  font-size: 20em;
  color: transparent;      
  text-shadow: 2px 2px 3px rgba(255, 255, 255, 0.5);
  background-clip: text;
  -webkit-background-clip: text;
  -moz-background-clip: text;
  background-color: #83827f;
}
p {
  font-size: 2em;
  color: rgb(184, 184, 184);
  font-weight: 900;
  text-shadow: 1px 1px 2px rgba(177, 177, 177, 0.5);
}
h1 + p {
  margin-bottom: 100px;
}
.back-button {
  width: 170px;
  height: 60px;
  border-radius: 50px;
  box-shadow: 5px 5px 10px rgba(119, 119, 119, .1), -5px -5px 10px rgba(255, 255, 255, 1); 
}
.back-button .fa-lg {
  font-size: 1.5em;
  margin-right: 15px;
  color:rgba(119, 119, 119, .5);
}

.back-button p {
  display: inline-block;
  vertical-align: middle;
  line-height: 3em;
  font-size: 1.3em;
  color:rgba(119, 119, 119, .5);
}
.back-button:hover {
  box-shadow: inset 5px 5px 10px rgba(119, 119, 119, .1), inset -5px -5px 10px rgba(255, 255, 255, 1);
}
.back-button:hover .fa-lg {
  color: rgb(87, 87, 87);
}
.back-button:hover p {
  color: rgb(87, 87, 87);
}
</style>