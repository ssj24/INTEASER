<template>
  <div class="profile-wrap">
    <h1>채용담당자페이지입니다!!!!!!</h1>
    <div class="profile-up">
      <div class="namecard">
        <img src="../../assets/images/user_line.svg" alt="namecard_img" align="middle">

        <div class="namecard-content">
          <ul>
            <li style="font-size: 1.5em;">{{user_nickname}}</li>
            <li :class="{disable: !user_email_onoff}">{{user_email}}</li>
            <li :class="{disable: !user_name_onoff}">{{user_name}}</li>
            <li :class="{disable: !user_intro_onoff}">{{user_intro}}</li>
            
            <li><a href="">포트폴리오</a></li>
            <li>좋아요 {{ like_count }}</li>
            <li>팔로우 {{ following_count }} / 팔로워 {{ follower_count }}</li>
          </ul>

        </div>
      </div>
    </div>
    <div class="profile-down">
      <div class="curations">
        <p>curation1</p>
        <p>curation2</p>
        <p>curation3</p>
      </div>
      <div class="warpper">
        <input class="radio" id="one" name="group" type="radio" checked>
        <input class="radio" id="two" name="group" type="radio">
        <input class="radio" id="three" name="group" type="radio">
        <div class="tabs">
          <label class="tab" id="one-tab" for="one">보관한 글</label>
          <label class="tab" id="two-tab" for="two">내가 쓴 글</label>
          <label class="tab" id="three-tab" for="three">내가 쓴 댓글</label>
        </div>
        <div class="panels">
          <div class="panel" id="one-panel">
            <div class="panel-title">chong*2 <i class="fas fa-chevron-right"></i> 신종 코로나 바이러스 예방하려면?</div>
            <br>
            <p>흐르는 물에 30초 이상, 비누로 꼼꼼하게! <br> 기침할 때는 옷소매에! <br> 마스크 착용하기 <br>감염이 의심될 때에는, 지역번호+120, 1399</p>
          </div>
          <div class="panel" id="two-panel">
            <p>불꽃놀이 보러 가고 싶다 <br> 여수밤바다 보고 싶다 <br> 그냥 떠나고 싶다</p>
            <img src="../../assets/images/fireworks.svg" alt="" width="30%">
            <p style="font-size: 0.8em; text-align: end;">2020/01/30</p>
          </div>
          <div class="panel" id="three-panel">
            <p>집에만 있어야 되는데...</p>2020/01/23
            <p style="font-size: 0.8em;"><a href="">신종 코로나 바이러스 예방하려면?</a></p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import '@/assets/css/tab.css'
  import baseURL from '@/base-url.js'
  export default {
    data:() => {
      return {
        user_email: '',
        user_email_onoff: '',
        user_nickname: '',
        user_name: '',
        user_name_onoff: '',
        user_intro: '',
        user_intro_onoff: '',
        like_count: 0,
        follower_count: 0,
        following_count: 0,
      }
    },
    mounted() {
      this.user_email = this.$route.params.user_email
      let data = {
        user_email: this.user_email
      }
      baseURL.post('/user/Profile_info', data, {
        headers: {
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token')
        }
      })
      .then(res => {
        this.user_nickname = res.data.object.user_nickname
        this.user_intro = res.data.object.user_intro
        this.user_name = res.data.object.user_name
        this.user_email_onoff = res.data.object.user_email_onoff
        this.user_intro_onoff = res.data.object.user_intro_onoff
        this.user_name_onoff = res.data.object.user_name_onoff
        this.like_count = res.data.object.like_count
        this.follower_count = res.data.object.follower_count
        this.following_count = res.data.object.following_count
      })
    }
    ,
    methods: {
      
    }
  }
</script>

<style scoped>
  .disable {
   display: none; 
  }
  .profile-wrap {
    width: 100%;
    min-height: 100%;
  }
  .profile-up {
    width: 100%;
    height: 50%;
  }
  .namecard {
    width: 40%;
    max-width: 500px;
    min-height: 100%;
    display: block;
    margin: 100px auto 30px;
    /* border-bottom: 5px groove white; */
    /* border-right: 5px groove white; */
    padding: 10px;
    background:#fffffff6;
    box-shadow: 0 1.5rem 1.5rem #2e2e2e80;
    border-radius:3px;
    position: relative;
    text-align: center;
  }
  
  .namecard img {
    width: 165px;
    max-height: 100%;
    display: block;
    margin: -80px auto 30px;
  }
  .namecard-content {
    width: 100%;
    display: inline-block;
    height: 100%;
    position: relative;
    text-align: center;
  }
  .namecard-content a:hover {
    font-size: 1em;
    font-weight: 900;
    color: #000;
  }
  .namecard-content ul {
    position: relative;
    max-height: 100%;
    margin: auto 0;
  }
  .profile-down {
  }

</style>