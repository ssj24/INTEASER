<template>
  <div class="app">
    <div class="app_title">
      <input type="text" v-model="projectName" placeholder="프로젝트 제목">
    </div>
    
    <textarea name="" id="pjt_intro" v-model="projectIntro" placeholder="프로젝트 소개"></textarea>
    <button @click="save" class="nextbtn">다음</button>
  </div>
</template>

<script>
  import baseURL from '@/base-url.js'
  import PortfolioApi from '@/apis/PortfolioApi.js'
  import cookie from '@/cookie.js'
// user_seq ,user_nickname ,project_name,project_intro
  export default {
    data: () => {
      return {
        userSeq: '',
        userNickname: '',
        projectName: '',
        projectIntro: '',
      }
    },
    methods: {
      checkSave() {
        if( this.projectName == '' || this.projectName == null || this.projectIntro == '' || this.projectIntro == null ){
          return false;
        }
        var blank_pattern = /^\s+|\s+$/g;
        if( this.projectName.replace( blank_pattern, '' ) == "" || this.projectIntro.replace( blank_pattern, '' ) == "" ){
          return false;
        }
        return true
      },
      save() {
        var isSave = this.checkSave()
        if (isSave === true) {
          var data = {
            user_seq: cookie.cookieSeq(),
            user_nickname: cookie.cookieNickname(),
            project_name: this.projectName,
            project_intro: this.projectIntro
          }
          
          PortfolioApi.projectStep1(data, res =>{
          this.$router.push({
            name:'ProjectSecond',
            params: {
              project_seq: res.data.object.project_seq,
              flag: 'step1',
            }
          })
          }, err=> { alert('에러가 발생하였습니다.')})
          
          
        } else {
          alert('빈 값이 존재합니다.')
        }
      }
    }
  }
</script>

<style lang="scss" scoped>
.app {
  min-width: 700px;
  min-height: 300px;
}
.app_title {
  margin: 0;
  text-align: left;
  width: 100%;
}
.app_title input {
  width: 90%;
  display: block;
  margin: 10px auto;
  border: none;
  border-bottom:rgb(5, 59, 11) 2px solid;
  color: #3a3a3a;
}
#pjt_intro {
  margin-top: 20px;
  width: 100%;
  height: 150px;
  background: transparent;
  border: none;
  color: #3a3a3a;
  outline-color: rgb(5, 59, 11);
}
.nextbtn {
  cursor: pointer;
  border: 1px solid transparent;
  display: block;
  padding: 10px;
  font-weight: 700;
  box-shadow: 5px 5px 10px 5px rgba(119, 119, 119, .1), -5px -5px 10px 5px rgba(255, 255, 255, 1);
  width: 12%;
  text-align: center;
  margin: 10px 0 0px auto;
  border-radius: 50px;
  color:#013f01;
  position: relative;
}
.nextbtn:hover {
  box-shadow: 3px 3px 5px 3px rgba(119, 119, 119, .1), -3px -3px 5px 3px rgba(255, 255, 255, 1);
  font-size: 1.01em;
}
.nextbtn:active {
  box-shadow: -5px -5px 10px 5px rgba(119, 119, 119, .1), 5px 5px 10px 5px rgba(255, 255, 255, 1);
  color:#013f01;
  border-color: #f7f5ec;
}
</style>