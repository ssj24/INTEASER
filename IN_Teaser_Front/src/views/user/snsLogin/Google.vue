<template>
  <div id="social-login">
      <a @click="signin" class="google-login">
        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="55" height="55" viewBox="0 0 55 55">
          <defs>
              <clipPath id="clip-path">
              <path id="패스_155" data-name="패스 155" d="M21.023,10.057H11.847v3.8h5.282c-.492,2.417-2.551,3.8-5.282,3.8a5.819,5.819,0,1,1,0-11.637,5.693,5.693,0,0,1,3.625,1.3l2.865-2.865a9.841,9.841,0,1,0-6.49,17.232c4.923,0,9.4-3.581,9.4-9.847A8.169,8.169,0,0,0,21.023,10.057Z" transform="translate(-2 -2)"/>
              </clipPath>
          </defs>
          <g id="그룹_248" data-name="그룹 248" transform="translate(-300 -406)">
              <g id="타원_6" data-name="타원 6" transform="translate(300 406)" fill="#fff" stroke="#eee" stroke-width="1">
              <circle cx="27.5" cy="27.5" r="27.5" stroke="none"/>
              <circle cx="27.5" cy="27.5" r="27" fill="none"/>
              </g>
              <g id="Google__G__Logo" transform="translate(318 424)">
              <g id="그룹_114" data-name="그룹 114" clip-path="url(#clip-path)">
                  <path id="패스_154" data-name="패스 154" d="M0,22.637V11l7.609,5.819Z" transform="translate(-0.895 -6.972)" fill="#fbbc05"/>
              </g>
              <g id="그룹_115" data-name="그룹 115" clip-path="url(#clip-path)">
                  <path id="패스_156" data-name="패스 156" d="M0,4.923l7.609,5.819,3.133-2.73L21.484,6.266V0H0Z" transform="translate(-0.895 -0.895)" fill="#ea4335"/>
              </g>
              <g id="그룹_116" data-name="그룹 116" clip-path="url(#clip-path)">
                  <path id="패스_158" data-name="패스 158" d="M0,16.561,13.428,6.266l3.536.448L21.484,0V21.484H0Z" transform="translate(-0.895 -0.895)" fill="#34a853"/>
              </g>
              <g id="그룹_117" data-name="그룹 117" clip-path="url(#clip-path)">
                  <path id="패스_160" data-name="패스 160" d="M28.666,27.561,14.79,16.819,13,15.476,28.666,11Z" transform="translate(-8.077 -6.972)" fill="#4285f4"/>
              </g>
              </g>
          </g>
        </svg>
        <p>Login with <span style="font-weight: 900;">{{name}}</span></p>

      </a>
      <div>
        <transition name="modal">
            <div v-if="isOpen">
                <div class="overlay">
                <div class="modal">
                    <h1>추가 정보</h1>
                    <p>약관이 들어갑니다</p>
                </div>
                </div>
            </div>
        </transition>
      </div>
  </div>




  
</template>



<script>
  import cookie from '@/cookie.js'
  import UserApi from '@/apis/UserApi'
  export default {
    data: () => {
      return {
        isOpen: false,
      }
    },
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
          
          // alert(flag)
          this.$store.commit('startLogin', data.user_email)
          this.$store.commit('isLogin')
          if (flag) {
            this.$router.push({name:'Main'})
          } else {
            this.$router.push({name:'Account', params:{user_email:data.user_email}})
          }
          }, err => {
            this.$router.push('error')
        })
      },
      
      signin() {
        // Initializes OAuth.io with API key
        // Sign-up an account to get one
        window.OAuth.initialize('3dWrCx6f7H9g4Bq_WMW2n5_Axto');

        // Popup facebook and ask for authorization
        window.OAuth.popup('google')
          .done(result => {
            result.me()
            .done(response => {
             
              let data = {
                login_type: 'google',
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
      margin-top: 15px;
  
  }


  a.google-login {
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
  
  a.google-login:link {
    color: ;
  }
  a.google-login:hover {
    box-shadow: 3px 3px 5px 3px rgba(119, 119, 119, .1), -3px -3px 5px 3px rgba(255, 255, 255, 1);
    text-decoration: none;
    color:#013f01;
    text-shadow: 1px 1px 1px rgb(128, 128, 128);

  }
  a.google-login:visited {
    color: ;
  }
  a.google-login:active {
    box-shadow: -5px -5px 10px 5px rgba(119, 119, 119, .1), 5px 5px 10px 5px rgba(255, 255, 255, 1);
    border-color: #f7f5ec;

  }

  .google-login svg {

    display: inline-block;
    vertical-align: middle;
    margin: 5px;
    border-radius: 50px;
    box-shadow: inset 5px 5px 10px rgba(119, 119, 119, .1), inset -5px -5px 10px rgba(255, 255, 255, 1);

  }
  .google-login p {
    font-weight: 400;
    display: inline-block;
    margin-left: 5px;
    }

</style>