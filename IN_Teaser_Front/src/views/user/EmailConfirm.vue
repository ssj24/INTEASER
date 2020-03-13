<template>
    <div class="app" id="">
        <div class="">
            <router-link v-bind:to="{name:'Login'}" class="btn--text svgs backarrow"><i class="fas fa-arrow-left"></i></router-link>
            <br><br><br>
            <h1>비밀번호를 찾고자 하는 이메일을 입력해주세요</h1>
            <div class="position-relative">
    

                <div class="input-with-label">
                    <input v-model="email" v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
                        id="email" placeholder="이메일을 입력하세요."
                        type="text"/>
                <label for="email" class="svgs"><i class="far fa-envelope"></i></label>
                    <div class="error-text" v-if="error.email">
                        {{error.email}}
                    </div>
                </div>
 
            </div>
            <router-link v-bind:to="{name:'PasswordFind'}" class="link-button">
                <button class="btn btn--back btn--login" v-on:click="findingPassword" :disabled="!isSubmit"
                        :class="{disabled : !isSubmit}">
                    다음
                </button>
            </router-link>
        </div>

    </div>
</template>

<script>
    import '@/assets/css/login.css'
    import * as EmailValidator from 'email-validator';
    import UserApi from '@/apis/UserApi'

    
    export default {

        created(){
            this.component = this;

        },
        watch: {

            email: function(v) {
                this.checkForm();
            }

        },
        methods: {
            checkForm() {
                
                if (this.email.length >= 0 && !EmailValidator.validate(this.email))
                    this.error.email = "이메일 형식이 아닙니다."
                else this.error.email = false;

                let isSubmit = true;
                Object.values(this.error).map(v => {
                    if (v) isSubmit = false;
                })
                this.isSubmit = isSubmit;
            }, 
            findingPassword() {
                if (this.isSubmit) {
                    let email = this;
                
                    this.isSubmit = false;

                  
                }
            },
            
            
        },
        data: () => {
            return {
                email: '',
                isTerm: false,
                isLoading: false,
                error: {
                    email: false,
                    password: false,
                    nickName: false,
                    passwordConfirm: false,
                    term: false
                },
                isSubmit: false,
                passwordType: "password",
                passwordConfirmType: "password",
                termPopup: false,
            }
        }

    }

</script>

<style scoped>
.app {
    width: 800px;
}
</style>
