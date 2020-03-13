
<template>
    <div class="app-join" style="min-height: 500px;">
        <div class="login">
            <h1 class="app_title" style="font-size: 2.4em; margin: 40px 0 20px">비밀번호가 변경되어<br>메일이 발송되었습니다.</h1>
            <div>
                <img src="@/assets/images/encrypted.svg" alt="mail_img" width="300px;" style="margin: 0 auto; display: block;">
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
                                    <button @click="resend" class="reSubmit">재전송</button>
                                </div>
                                </div>
                            </div>
                        </transition>
                        <button @click="isOpen = !isOpen;" class="give-margin-more smaller">
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
                
                baseURL.post('/account/'+this.user_email+'/resend-pwd-link')
                alert('재전송하였습니다.')
                this.isOpen = !this.isOpen
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
            signup() {
                if (this.isSubmit) {
                    let {user_email}  = this;
                    let data = {
                        user_email
                    }

                    this.isSubmit = false;

                    UserApi.requestLogin(data,res=>{
                        this.isSubmit = true;
                    }, error=>{
                        this.isSubmit = true;
                    })
                }
            },
            
            show () {
                this.$modal.show('hello-world');
            },
            hide () {
                this.$modal.hide('hello-world');
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
                isOpen: false,
                termPopup: false,
            }
        }

    }

</script>
<style scoped>
.modal {
    height: 300px;
    text-align: center;
}
.modal .reSubmit {
    margin-top: 50px;
}
</style>

