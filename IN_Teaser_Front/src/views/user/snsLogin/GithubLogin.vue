<template>
  <div id="social-login">
    <a @click="signin" class="github-login">
      <svg id="Bold" enable-background="new 0 0 24 24" height="55" viewBox="0 0 24 24" width="55" xmlns="http://www.w3.org/2000/svg">
        <path d="m12 .5c-6.63 0-12 5.28-12 11.792 0 5.211 3.438 9.63 8.205 11.188.6.111.82-.254.82-.567 0-.28-.01-1.022-.015-2.005-3.338.711-4.042-1.582-4.042-1.582-.546-1.361-1.335-1.725-1.335-1.725-1.087-.731.084-.716.084-.716 1.205.082 1.838 1.215 1.838 1.215 1.07 1.803 2.809 1.282 3.495.981.108-.763.417-1.282.76-1.577-2.665-.295-5.466-1.309-5.466-5.827 0-1.287.465-2.339 1.235-3.164-.135-.298-.54-1.497.105-3.121 0 0 1.005-.316 3.3 1.209.96-.262 1.98-.392 3-.398 1.02.006 2.04.136 3 .398 2.28-1.525 3.285-1.209 3.285-1.209.645 1.624.24 2.823.12 3.121.765.825 1.23 1.877 1.23 3.164 0 4.53-2.805 5.527-5.475 5.817.42.354.81 1.077.81 2.182 0 1.578-.015 2.846-.015 3.229 0 .309.21.678.825.56 4.801-1.548 8.236-5.97 8.236-11.173 0-6.512-5.373-11.792-12-11.792z"/></svg>
      
      <!-- <img src="../../../assets/images/github-logo.svg" alt="" width="55"> -->
      <p>Login with <span style="font-weight: 900;">{{name}}</span></p>
      
    </a>
  </div>
</template>

<script>
import cookie from '@/cookie.js'
import UserApi from '@/apis/UserApi'
export default {
  props: {
    name: String
  },
  methods: {
    snsLogin(data, flag) {
      UserApi.snsLogin(data, respo=> {
        let loginData = {
          userNickname: respo.data.object.user_nickname,
          userEmail: respo.data.object.user_email,
          token: respo.headers["jwt-auth-token"], 
          userSeq: respo.data.object.user_seq
        }
        this.$store.commit('startLogin', loginData)
        this.$store.commit('isLogin')
        cookie.cookieCreate(loginData)

        if (flag) {
          this.$router.push({name:'Main'})
        } else {
          this.$router.push({name:'Account', params:{user_email:data.user_email}})
        }
        }, err => {
          this.$router.push('error')
      })
    },
      // var auth = confirm('채용자 담당자 이신가요')

    signin() {
      // Initializes OAuth.io with API key
      // Sign-up an account to get one
      window.OAuth.initialize('3dWrCx6f7H9g4Bq_WMW2n5_Axto');

      // Popup facebook and ask for authorization
      window.OAuth.popup('github')
        .done(result => {
          result.me()
          .done(response => {
           
            let data = {
              login_type: 'github',
              user_email: response.email,
            }
            UserApi.snsUserCheck(data, res => {
              var flag = true
              if (res.data.status === false) {
                
                if (confirm('채용자 담당자 이신가요')) {
                  data.user_auth = 2
                  UserApi.snsSignup(data, 
                    respon => {
                      flag = false
                      this.snsLogin(data, flag)
                    }, err => {
                      this.$router.push('error')
                    })
                } else {
                  data.user_auth = 1
                  UserApi.snsSignup(data, respon => {
                    flag = false
                    this.snsLogin(data, flag)
                    }, err => {
                    this.$router.push('error')
                  })
                }
                
              } else {
                this.snsLogin(data, flag)
              }
            }, err =>{
            })   
          })
          .fail(function(err) {
           
          })
        })
        .fail(function(err) {
        
        })
      
    }
  }
}
</script>
<style scoped>
  #social-login {
      display: block;
      width: 100%;
  }
  a.github-login {
    color: #7f7f7c;
    width: 100%;
    text-align: center;
    display: block;
    margin: auto;
    border-radius: 100px;
    border: 3px solid transparent;
    transition: 0.2s ease;
    cursor: pointer;
    transform: rotate(0.1deg);
    text-decoration: none;
    position: relative;
    box-shadow: 5px 5px 10px 5px rgba(119, 119, 119, .1), -5px -5px 10px 5px rgba(255, 255, 255, 1);

  }
  
  a.github-login:link {
    color: ;
  }
  a.github-login:hover {
    box-shadow: 3px 3px 5px 3px rgba(119, 119, 119, .1), -3px -3px 5px 3px rgba(255, 255, 255, 1);
    text-decoration: none;
    color:#013f01;
    text-shadow: 1px 1px 1px rgb(128, 128, 128);

  }
  a.github-login:visited {
    color: ;
  }
  a.github-login:active {
    box-shadow: -5px -5px 5px 10px rgba(119, 119, 119, .1), 5px 5px 10px 5px rgba(255, 255, 255, 1);
    border-color: #f7f5ec;
  }

  .github-login svg {

    display: inline-block;
    vertical-align: middle;
    margin: 5px;
    border-radius: 50px;
    box-shadow: inset 5px 5px 10px rgba(119, 119, 119, .1), inset -5px -5px 10px rgba(255, 255, 255, 1);

  }
  .github-login p {
    font-weight: 400;
    display: inline-block;
    margin-left: 5px;
    }

</style>
