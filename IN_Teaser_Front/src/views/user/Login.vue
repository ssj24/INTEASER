<template>
    <div class="app">
        <div class="app_head">
            <div class="app_menu">
            </div>
            <h1 class="app_title">
                <span class="app_title-span"> IN</span>:TEASER
            </h1>
            
        </div>
        <div class="app_body">
            <div class="login">
                <div class="input-wrap">
                    <input 
                        type="text" class="input_text" 
                        v-model="user_email"
                        v-bind:class="{error : error.user_email, complete:!error.user_email&&user_email.length!==0}"
                        @keyup.enter="login" autocapitalize="none" id="email"
                        placeholder="Email"
                    >
                </div>
                <div class="error-text" v-if="error.user_email">
                    {{error.user_email}}
                </div>
                <div class="input-wrap" tabindex="0">
                    <input class="input_text" 
                        v-model="user_pwd" :type="passwordFieldType"
                        v-bind:class="{error : error.user_pwd, complete:!error.user_pwd&&user_pwd.length!==0}"
                        id="password" @keyup.enter="login" 
                        placeholder="Password"
                    >
                    <button type="password" @click="switchVisibility" class="password-visible">
                        <span v-show="password_visible">
                            <i class="far fa-eye"></i>
                        </span>
                        <span v-show="!password_visible"> 
                            <i class="far fa-eye-slash"></i>
                        </span>
                    </button>
                </div>
                <div class="error-text" v-if="error.user_pwd">
                    {{error.user_pwd}}

                </div>
                <button class="login_submit"
                    v-on:click="login" :disabled="!isSubmit"
                    :class="{disabled : !isSubmit}"
                >
                    LOGIN
                </button>
            </div>
            <div class="category">
                <div class="category-nav">
                    <router-link v-bind:to="{name:'PasswordLost'}" class="category-nav_title">비밀번호 찾기</router-link>
                    <router-link v-bind:to="{name:'Join'}" class="category-nav_title">회원가입</router-link>
                </div>
                
                <div class="social">
                    <GithubLogin name="Github"></GithubLogin>
                    <GoogleLogin name="Google"></GoogleLogin>
                </div>
            </div>
        </div>

    </div>

    

</template>

<script>

    import '@/assets/css/basic.css'
    import '@/assets/css/login.css'
    import PV from 'password-validator'
    import * as EmailValidator from 'email-validator';
    import GoogleLogin from '@/views/user/snsLogin/Google.vue'
    import GithubLogin from '@/views/user/snsLogin/GithubLogin'
    import { mapActions } from 'vuex'
    import UserApi from '@/apis/UserApi'
    import cookie from '@/cookie.js'
    import baseURL from '@/base-url.js'
    // 토큰 및 사용자 정보를 저장하기 위해서 세션 스토리지를 사용한다.
    const storage = window.sessionStorage;
    export default {
        components: {
            GithubLogin,
            GoogleLogin
        },
        created() {
            this.component = this;
            this.user_pwdSchema
                .is().min(8)
                .is().max(100)
                .has().digits()
                .has().letters();
        },
        watch: {
            user_pwd: function (v) {
                this.checkForm();
            },
            user_email: function (v) {
                this.checkForm();
            },
        },
        methods: {
            switchVisibility() {
                this.passwordFieldType = this.passwordFieldType === 'password' ? 'text' : 'password'
                this.password_visible = !this.password_visible
            },
            checkForm() {
                if (this.user_email.length >= 0 && !EmailValidator.validate(this.user_email))
                    this.error.user_email = "이메일 형식이 아닙니다."
                else this.error.user_email = false;

                if (this.user_pwd.length >= 0 && !this.user_pwdSchema.validate(this.user_pwd))
                    this.error.user_pwd = '영문,숫자 포함 8 자리이상이어야 합니다.'
                else
                    this.error.user_pwd = false;


                let isSubmit = true;
                Object.values(this.error).map(v => {
                    if (v) isSubmit = false;
                })
                this.isSubmit = isSubmit;

            },
            setInfo(status, token, info) {
                this.status = status;
                this.token = token;
                this.info = info;
            },
            login() {
                if (this.isSubmit) {
                    let {
                        user_email,
                        user_pwd
                    } = this;
                    let data = {
                        user_email,
                        user_pwd
                    }
                    if (this.user_email.charAt(0) >= 'A' && this.user_email.charAt(0) <= 'Z') {
                        data.user_email = user_email.toLowerCase();
                    } //여기작동하는지 확인 필요

                    //요청 후에는 버튼 비활성화
                    this.isSubmit = false;
                    UserApi.requestLogin(data, res => {
                        //통신을 통해 전달받은 값 콘솔에 출력
                        //요청이 끝나면 버튼 활성화
                        if (res.data.status == false) {
                            this.user_pwd = ''
                            this.checkForm()
                            if (res.data.data === 'fail') {
                                alert('비밀번호가 다릅니다.')
                            } else if (res.data.data === '인증되지 않은 사용자 입니다.') {
                                alert(res.data.data)
                                var resend = confirm('이메일 재 인증 받으시겠습니까?')
                                if (resend) {
                                    baseURL.post('/account/'+this.user_email+'/resend-signup-link')
                                    .then(res => {
                                        alert('인증 메일을 재 전송 하였습니다.')
                                    })
                                }
                            } else {
                                alert(res.data.data)
                            }
                        } 
                        else if (res.data.object.user_utilityYN === 'Y') {
                            alert('탈퇴한 회원입니다.')
                            this.user_email = ''
                            this.user_pwd = ''
                        }
                        else {
                            let loginData = {
                                userEmail: res.data.object.user_email,
                                token: res.headers["jwt-auth-token"], 
                                userSeq: res.data.object.user_seq,
                                userNickname: res.data.object.user_nickname,
                                userAuth: res.data.object.user_auth
                            }
                            this.$store.commit('startLogin', loginData)
                            this.$store.commit('isLogin')
                            cookie.cookieCreate(loginData)
                            if (res.data.object.user_auth == 0) {
                                this.$router.push({
                                    name: 'Admin'
                                })
                            } else {
                                this.$router.push({
                                    name: 'Main'
                                })
                            }
                            
                        }
                        this.isSubmit = true;
                    }, error => {
                        //요청이 끝나면 버튼 활성화
                        this.user_email = ''
                        this.user_pwd = ''
                        this.isSubmit = true;
                        this.$router.push({
                            name: "error",
                            params: {
                                data,
                                "route": this.$route.name
                            }
                        });
                    })
                }
            },

            
        },
        mounted() {
            if (this.$route.params.data) {
                this.user_email = this.$route.params.data.user_email
            }
        },
        data: () => {
            return {
                user_email: '',
                user_pwd: '',
                user_pwdSchema: new PV(),
                passwordFieldType: 'password',
                display: 'display: none;',
                error: {
                    user_email: false,
                    user_pwd: false,
                },
                isOpen: false,
                status: '',
                token: '',
                info: '',
                isSubmit: false,
                component: this,
                password_visible: false,
            }
        }

    }
</script>
<style scoped>

</style>