
<template>
    <div class="app" id="">
        <div class="login">
            <br>
            <h1 class="app_title">입력해주신 이메일로<br>인증메일이 발송되었습니다</h1>
            <div>
                <img src="@/assets/images/mailwc.svg" alt="mail_img" class="user-img">
                
                <div>
                    <div>
                        <transition name="modal">
                            <div v-if="isOpen">
                                <div class="overlay" @click.self="isOpen = false;">
                                <div class="modal">
                                    <h1>이메일을 확인해주세요</h1>
                                    <div class="input-with-label">
                                        <input v-model="user_email" v-bind:class="{error : error.user_email, complete:!error.user_email&&user_email.length!==0}"
                                            id="email" placeholder="이메일을 입력하세요."
                                            type="text"/>
                                        <label for="email" class="svgs"><i class="far fa-envelope"></i></label>
                                        <div class="error-text" v-if="error.user_email">
                                            {{error.user_email}}
                                        </div>
                                    </div>
                                    <button @click="resend" class="btn btn--back btn--login">재전송</button>
                                </div>
                                </div>
                            </div>
                        </transition>
                        <button @click="isOpen = !isOpen;" class="">
                            메일을 받지 못하셨나요?
                        </button>
                    </div>
                </div>
            </div>
            <router-link v-bind:to="{name:'Login'}" class="link-button">
                <button class="">
                    로그인하기
                </button>
            </router-link>
        </div>
    </div>
</template>

<script>
    
    
    import * as EmailValidator from 'email-validator';
    import UserApi from '@/apis/UserApi'
    import baseURL from '@/base-url.js'

    export default {
        mounted() {
            this.user_email = this.$route.params.user_email
        },
        created(){
            this.component = this;

        },
        watch: {
            
            user_email: function(v) {
                this.checkForm();
            },
            
        },
        methods: {
            resend() {
                baseURL.post('/account/'+this.user_email+'/resend-signup-link')
                .then(res => {
                    alert('재전송하였습니다.')
                })
                this.isOpen = false
            },
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
        },
        data: () => {
            return {
                user_email: '',
                isLoading: false,
                error: {
                    user_email: false,
                },
                isSubmit: false,
                isOpen: false,
                termPopup: false,
            }
        }

    }

</script>
<style scoped>
.app_title {
    font-size: 2.4em;
    margin-top: 0px;
}
.login {
    width: 100%;
    padding: 25px !important;
    margin-top: 0px;
}
.user-img {
    display: block;
    text-align: center;
    width: 300px;
    margin: 20px auto;
}

</style>

