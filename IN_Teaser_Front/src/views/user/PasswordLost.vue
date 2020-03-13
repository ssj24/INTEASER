<template>
    <div class="app" id="">
        <div class="login">
            <br><br><br>
            <h1 class="app_title">비밀번호를 찾고자 하는 <span class="app_title-span">이메일</span>을 입력해주세요</h1>
            <div class="position-relative">
                <div class="input-wrap">
                    <input v-model="user_email" v-bind:class="{error : error.user_email, complete:!error.user_email&&user_email.length!==0}"
                        id="email" placeholder="이메일을 입력하세요." type="text"/>
                     <div class="error-text" v-if="error.user_email">
                        {{error.user_email}}
                    </div>
                </div>
            </div>
            
            <button class="" v-on:click="findingPassword" :disabled="!isSubmit"
                        :class="{disabled : !isSubmit}">
                <p class="pWBtn">다음</p>
            </button>
        </div>

    </div>
</template>

<script>
    import '@/assets/css/login.css'
    import baseURL from '@/base-url.js'
    import * as EmailValidator from 'email-validator';
    import UserApi from '@/apis/UserApi'

    
    export default {
        created(){
            this.component = this;
        },
        watch: {
            user_email: function(v) {
                this.checkForm();
            }
        },
        methods: {
            checkForm() {
                if (this.user_email.length >= 0 && !EmailValidator.validate(this.user_email))
                    this.error.user_email = "이메일 형식이 아닙니다."
                else this.error.user_email = false;

                let isSubmit = true;
                Object.values(this.error).map(v => {
                    if (v) isSubmit = false;
                })
                this.isSubmit = isSubmit;
            }, 
            findingPassword() {
                if (this.isSubmit) {
                    let {user_email}  = this;
                    let data = {
                        user_email
                    }
                    baseURL.post('/account/'+data.user_email+'/pwd-link')
                    .then(res => {
                        this.$router.push({
                            name: "PasswordCompleted",
                            params: {
                                user_email: this.user_email
                            }
                        })
                    })
                    
                    this.isSubmit = false;
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
                isLoading: false,
                error: {
                    user_email: false,
                },
                isSubmit: false,
            }
        }
    }
</script>
<style scoped>
.app {
    min-height: 200px !important;
    padding: 25px !important;
}
.login {
    margin: 0 0 25px auto;
}
.app_title {
    margin-top: 0;
    padding: 0;
    text-align: center;
    font-size: 2.4em;
    margin-bottom: 50px;
}
.input-wrap {
    margin-bottom: 30px;
}
.input-wrap:hover {
    box-shadow: -2px -2px 2px rgba(119, 119, 119, .1), 2px 2px 2px rgba(255, 255, 255, 1);
}
.pWBtn {
    margin: 0;    
}
</style>

