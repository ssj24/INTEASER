<template>
<div class="loader loader--style8" title="7">
  <svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
     width="300px" height="400px" viewBox="0 0 24 30" style="enable-background:new 0 0 50 50;" xml:space="preserve">
    <rect x="0" y="10" width="4" height="10" fill="#333" opacity="0.2">
      <animate attributeName="opacity" attributeType="XML" values="0.2; 1; .2" begin="0s" dur="1s" repeatCount="indefinite" />
      <animate attributeName="height" attributeType="XML" values="10; 20; 10" begin="0s" dur="1s" repeatCount="indefinite" />
      <animate attributeName="y" attributeType="XML" values="10; 5; 10" begin="0s" dur="1s" repeatCount="indefinite" />
    </rect>
    <rect x="8" y="10" width="4" height="10" fill="#333"  opacity="0.2">
      <animate attributeName="opacity" attributeType="XML" values="0.2; 1; .2" begin="0.25s" dur="1s" repeatCount="indefinite" />
      <animate attributeName="height" attributeType="XML" values="10; 20; 10" begin="0.25s" dur="1s" repeatCount="indefinite" />
      <animate attributeName="y" attributeType="XML" values="10; 5; 10" begin="0.25s" dur="1s" repeatCount="indefinite" />
    </rect>
    <rect x="16" y="10" width="4" height="10" fill="#333"  opacity="0.2">
      <animate attributeName="opacity" attributeType="XML" values="0.2; 1; .2" begin="0.5s" dur="1s" repeatCount="indefinite" />
      <animate attributeName="height" attributeType="XML" values="10; 20; 10" begin="0.5s" dur="1s" repeatCount="indefinite" />
      <animate attributeName="y" attributeType="XML" values="10; 5; 10" begin="0.5s" dur="1s" repeatCount="indefinite" />
    </rect>
  </svg>
</div>
</template>

<script>
  import baseURL from '@/base-url.js'

  export default {
    mounted() {
      var flag = this.$route.params.flag
      let data = {
        user_certification_code: this.$route.params.confirmcode,
        user_email: this.$route.params.email
      }
      if (flag === 'password') {
        baseURL('/account/'+data.user_email+'/pwd-certification-code-check', {
          params: {
            user_certification_code: data.user_certification_code
          }})
          .then(res=>{
            if (res.data.status === false) {
              alert('잘못된 접근입니다.')
              this.$router.push({name:'Login'})
            } else if (res.data.status === true) {
              var flag = this.$route.params.flag
              alert('페스워드 변경 페이지로 이동합니다.')
              this.$store.commit('passwordChange', {
                user_email: this.$route.params.email,
                preURL: true
              })
              this.$router.push({name:'PasswordChange', params: {
                data
              }})
            }
          })
          .catch(err => {
            alert('오류가 발생하였습니다.')
            this.$router.push({name:'Login'})
          })
        } else if (flag === 'auth') {
          baseURL('/account/'+data.user_email+'/signup-certification-code-check', {
            params: {
              user_certification_code: data.user_certification_code
            }})
            .then(res=>{
              if (res.data.status === false) {
                alert('잘못된 접근입니다.')
                this.$router.push({name:'Login'})
              } else {
                alert('IN:TEASER에 오신것을 환영합니다.')
                this.$router.push({name:'Login'})
              }
            })
            .catch(err=>{
              alert('오류가 발생하였습니다.')
              this.$router.push({name:'Login'})
            })
        }
      
    }
  }
</script>

<style scoped>
  /* Absolute Center Spinner */
.loading {
  position: fixed;
  z-index: 999;
  height: 2em;
  width: 2em;
  overflow: visible;
  margin: auto;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
}

/* Transparent Overlay */

.loader{
  margin: 0 0 2em;
  height: 400px;
  width: 300px;
  text-align: center;
  padding: 1em;
  margin: 0 auto 1em;
  display: block;
  vertical-align: top;
}

/*
  Set the color of the icon
*/
svg path,
svg rect{
  fill: rgb(5, 59, 11);
}

</style>