<template>
  <div class="app" id="login">
    <div class="login">
      <h1 class="app_title">변경할 비밀번호를 입력해주세요.</h1>
      <div class="login-namecard">
        <div class="namecard-right">
          <div class="input-warp">
            <input v-model="password" :type="passwordFieldType"
                v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
                id="password"
                @keyup.enter="changePassword"
                placeholder="비밀번호를 적어주세요"/>
            <button type="password" @click="switchVisibility('password')" class="password-visible"><i class="far fa-eye"></i></button>
            <div class="error-text" v-if="error.password">
                {{error.password}}
            </div>
          </div>

          <div class="input-warp btnChange">
            <input v-model="passwordConfirm" :type="passwordConfirmFieldType"
                v-bind:class="{error : error.passwordConfirm, complete:!error.passwordConfirm&&passwordConfirm.length!==0}"
                id="passwordConfirm"
                @keyup.enter="changePassword"
                placeholder="비밀번호를 적어주세요"/>
            <button type="password" @click="switchVisibility('passwordConfirm')" class="password-visible"><i class="far fa-eye"></i></button>
            <div class="error-text" v-if="error.passwordConfirm">
                {{error.passwordConfirm}}
            </div>
          </div>

          <button class="" v-on:click="changePassword" :disabled="!isSubmit"
            :class="{disabled : !isSubmit}">
            비밀번호 변경하기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  
  import PV from 'password-validator'
  import UserApi from '@/apis/UserApi'
  import baseURL from '@/base-url.js'

  export default {
    mounted() {
      // if (this.$store.getters.passwordChange.preURL === true) {
      //   console.log('Something Wrong')
      // } else {
      //   alert('잘못된 접근입니다.')
      //   this.$store.commit('clearInfo')
      //   this.$route.push({name:'Login'})
      // }
    },
    created(){
      this.passwordSchema
          .is().min(8)
          .is().max(100)
          .has().digits()
          .has().letters();
    },
    watch: {
      password: function (v) {
        this.submitCheck()
        
      },
      passwordConfirm: function (v) {
        this.submitCheck()
      }
    },
    methods: {
      passwordCheck() {
        if (this.password.length >= 0 && !this.passwordSchema.validate(this.password))
          this.error.password = '영문,숫자 포함 8 자리이상이어야 합니다.'
        else
          this.error.password = false;
      },
      passwordConfirmCheck() {
        if (this.password !== this.passwordConfirm) {
          this.error.passwordConfirm = '비밀번호가 맞지 않습니다.'
        } else {
          this.error.passwordConfirm = false
        }
      },
      submitCheck() {
        this.passwordCheck()
        this.passwordConfirmCheck()
        let isSubmit = true;                
        Object.values(this.error).map(v => {
            if (v) isSubmit = false;
        })
        if (!this.password || !this.passwordConfirm) isSubmit = false
        this.isSubmit = isSubmit;
      },
      changePassword() {
        this.submitCheck()
        if (this.isSubmit) {
          let data = {
            user_email: this.$store.getters.passwordChange.user_email,
            user_pwd: this.password,
            user_certification_code: this.$route.params.data.user_certification_code
          }
          baseURL.post('/account/'+data.user_email+'/pwd', null, {
            params: {
              user_pwd: data.user_pwd,
              user_certification_code: data.user_certification_code
            }
          })
          .then(res => {
            alert('성공적으로 변경되었습니다.')
            this.$router.push({name:'PasswordChangeComplete'})
          })
          .catch(res => {
            alert('실패하였습니다.')
          })
        }
      },
      switchVisibility(pw) {
        if (pw === 'password') {
          this.passwordFieldType = this.passwordFieldType === 'password' ? 'text' : 'password'
        } else {
          this.passwordConfirmFieldType = this.passwordConfirmFieldType === 'password' ? 'text' : 'password'
        }
      }
    },
    data: () => {
      return {
          dayMessage: '',
          email: '',
          password: '',
          passwordConfirm: '',
          passwordSchema: new PV(),
          passwordFieldType: 'password',
          error: {
              passowrd: false,
              passwordConfirm: false
          },
          isSubmit: false,
          component: this,
          passwordConfirmFieldType: 'password'
      }
    }
  }
</script>

<style scoped>
.app_title {
  font-size: 2.5em;
}
  .input-warp {
    margin: 20px 0;
  }
  .btnChange {
    margin-bottom: 50px;
  }
</style>