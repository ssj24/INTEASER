<template>
  <div class="profile-wrap">
    <div class="profile-up">
      <div class="app">
        <div class="user-img">
          <img v-if="user_profile_route" :src="'https://i02b204.p.ssafy.io'+user_profile_route" alt="namecard_img" align="middle">
          <img v-else src="@/assets/images/default5.png" alt="namecard_basic_img" align="middle">
        </div>
          <div class="app_head">
            <span>
              <router-link v-if="sameUser" v-bind:to="{name:'Account'}">
                <i class="fas fa-lg fa-cog font"></i>
              </router-link>

            </span>
            <div class="app_title">
              {{user_nickname}}
            </div>
            <button class="dmBtn" @click="DM" v-if="!sameUser"><i class="far fa-paper-plane font" alt="dm보내기"></i></button>

            <div class="follow_count">
              <button @click="following_modal=true">팔로우 <b>{{ following_count }}</b> / </button>
              <button @click="follower_modal=true">팔로워 <b>{{ follower_count }}</b></button>
            </div>
            <span v-if="!sameUser" class="follow_btn">
              <button v-if="statusFollow === 0" @click="follow(statusFollow)"><i class="fas fa-plus followO"></i></button>
              <button v-else-if="statusFollow === 2" @click="follow(statusFollow)"><i class="fas fa-plus followX"></i></button>
              <button v-else><i class="fas fa-spinner fa-spin"></i></button>
            </span>
          </div>
          <div class="app_body">
          <ul>
            <li :class="{disable: !user_email_onoff}">{{user_email}}</li>
            <li :class="{disable: !user_name_onoff}">{{user_name}}</li>
            <li :class="{disable: !user_intro_onoff}">{{user_intro}}</li>
            <li>좋아요 {{ like_count }}</li>
            <li v-if="(portfolio_onoff || sameUser) && user_auth == 1"><button class="feedBtn" @click="feedChange">{{feedChangeName}}</button></li>
          </ul>
        </div>
      </div>
    </div>
    <button class="backTop" @click="backTop"><i class="fas fa-arrow-up arrowUp"></i></button>

    <div class="profile-down">
      <profile-feed name="ProfileFeed" :style="displayBlock"></profile-feed>
      <portfolio name="Portfolio" :style="displayNone"></portfolio>
    </div>
    <transition name="modal">
      <div v-if="following_modal">
          <div class="overlay" @click.self="following_modal = false;">
            <div class="modal">
                <h1>FOLLOWING</h1>
                <span v-for="(following_user, idx) in following_list" :key="idx">
                  <router-link 
                  :to="{name:'Profile', params:{userSeq:following_user.opponent_seq}}"><p class="followP">{{ following_user.opponent_nickname }}</p></router-link>
                </span>
            </div>
          </div>
      </div>
    </transition>

    <transition name="modal">
      <div v-if="follower_modal">
          <div class="overlay" @click.self="follower_modal = false;">
            <div class="modal">
                <h1>FOLLOWER</h1>
                <span  v-for="(follower_user, idx) in follower_list" :key="idx">
                  <router-link 
                  :to="{name:'Profile', params:{userSeq:follower_user.opponent_seq}}"><p class="followP">{{ follower_user.opponent_nickname }}</p></router-link>
                </span>
            </div>
          </div>
      </div>
    </transition>
    
  </div>
</template>

<script>
  import ProfileFeed from '@/views/profile/ProfileFeed.vue'
  import Portfolio from '@/views/profile/Portfolio.vue'
  import cookie from '@/cookie.js'
  import '@/assets/css/login.css'
  import FollowApi from '@/apis/FollowApi.js'
  import '@/assets/css/tab.css'
  import baseURL from '@/base-url.js'
  export default {
    data:() => {
      return {
        
        sameUser: false,
        statusFollow: false,
        user_auth: 1,
        user_email: '',
        user_email_onoff: '',
        user_nickname: '',
        user_name: '',
        user_name_onoff: '',
        user_intro: '',
        user_intro_onoff: '',
        like_count: '',
        follower_count: '',
        following_count: '',
        following_list: [],
        following_modal: false,
        follower_list: [],
        follower_modal: false,
        displayBlock: 'display: block;',
        displayNone: 'display: none;',
        userSeq: '',
        feedChangeName: '포트폴리오',
        user_profile_route: '',
        portfolio_onoff: false,
      }
    },
    components: {
      ProfileFeed,
      Portfolio
    },
    mounted() {
      this.userSeq = cookie.cookieSeq()
      this.profileStuff()
      baseURL('/portfolio-info/' + cookie.cookieSeq())
        .then(res => {
          this.portfolio_onoff = res.data.object.portfolio_onoff
        })
    },
    methods: {
      DM() {
        this.$emit('DM', {opponent_seq:this.$route.params.userSeq, opponent_nickname:this.user_nickname})
      }, 
      feedChange() {
        if (this.displayBlock == 'display: block;') {
          this.displayBlock = 'display: none;';
          this.displayNone = 'display: block;';
          this.feedChangeName = '피드';
        } else {
          this.displayBlock = 'display: block;';
          this.displayNone = 'display: none;';
          this.feedChangeName = '포트폴리오';
        }
        
      },
      follow(statusFollow) {
        var data = {
          user_seq: this.userSeq,
          opponent_nickname: this.user_nickname,
          user_nickname: cookie.cookieNickname(),
        }
        FollowApi.requetFollow(data, res=> {
          if (statusFollow === 0) alert('팔로우 신청하였습니다.')
          else alert('언팔로우 하였습니다.')
          this.statusFollow = 1
        }, err =>{
          alert('오류가 생겻습니다. 잠시후 다시 시도해주세요.')
        })
      },
      profileStuff() {
        // this.$store.commit('profileUserEmail', this.$route.params.user_email)
        baseURL.get('/users/'+this.$route.params.userSeq+'/profile')
        .then(res => {
          this.user_auth = res.data.object.user_auth
          this.user_nickname = res.data.object.user_nickname
          this.user_intro = res.data.object.user_intro
          this.user_name = res.data.object.user_name
          this.user_email_onoff = res.data.object.user_email_onoff
          this.user_intro_onoff = res.data.object.user_intro_onoff
          this.user_name_onoff = res.data.object.user_name_onoff
          this.like_count = res.data.object.like_count
          this.user_profile_route = res.data.object.user_profile_route
          this.user_email = res.data.object.user_email
          
          var data = {
            user_seq: this.userSeq,
            opponent_seq: String(this.$route.params.userSeq),
            user_nickname: cookie.cookieNickname(),
            opponent_nickname: this.user_nickname
          }
          if (data.user_seq === data.opponent_seq) this.sameUser = true
          else {
            FollowApi.tryFollow(data, res=> {
              this.statusFollow = res
            }, err=>{
              this.statusFollow = 0
            })
          }
          FollowApi.followingList(data, res=> {
            this.following_list = res.data.object
            var value = 0
            if (this.following_list.length !== undefined) value = this.following_list.length
            this.following_count = value
          }, err => {  this.following_count=0 })
      

          FollowApi.followerList(data, res=> {
            this.follower_list = res.data.object
            var value = 0
            if (this.follower_list.length !== undefined) value = this.follower_list.length
            this.follower_count = value
          }, err => {  this.follower_count=0 })
        })
        
        
      },
      backTop() {
        document.documentElement.scrollTop = document.body.scrollTop = 0;
  
      }
    }
  }
</script>

<style scoped>
  @import url('https://fonts.googleapis.com/css?family=Yeon+Sung&display=swap');
  @import url('https://fonts.googleapis.com/css?family=East+Sea+Dokdo&display=swap');
  @import url('https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap');
  @import url('https://fonts.googleapis.com/css?family=Sunflower:300&display=swap');
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
  .app {
    position: relative;
    min-height: 300px;
  }
  
  .app_title {
    text-align: start;
    font-size: 2em;
    margin: 0;
    margin-top: 70px;
    text-shadow: 2px 2px 3px rgba(255, 255, 255, 0.774);
    background-color: #252b26;
    display: inline-block;
  }
  .app_head span {
    width: 100%;
  }
  .font {
    float: right;
    filter: drop-shadow(2px 2px rgba(255, 255, 255, 0.5));
    text-align: end;
    color: rgba(66, 66, 66, 0.959);      
  }
  .app .user-img {
    position: absolute;
    width: 180px;
    height: 225px;
    left: 30%;
    top: -40%;
    background-color: #f6f5f0;
    border-radius: 50%;
    border: 7px solid #f6f5f0;
    box-shadow: 10px 15px 15px #fce1be1a, -10px -10px 10px rgba(255, 255, 255, 1), inset 7px 7px 10px rgba(248, 221, 170, 0.1), inset -7px -7px 10px rgba(255, 255, 255, 1);
    overflow: hidden;
  }

  .app img {
    width: 100%;
    height: 100%;
  }
  
  .dmBtn {
    margin: 10px;
  }

  .app_body ul li, .follow_li span {
    font-family: 'Sunflower', cursive;
    font-size: 1.3em;
    color: transparent;      
    text-shadow: 2px 2px 3px rgba(255, 255, 255, 0.774);
    background-clip: text;
    -webkit-background-clip: text;
    -moz-background-clip: text;
    background-color: #2b2a25;
  }

  .app_body ul li .feedBtn {
    font-size: 1em;
    text-align: end;
    cursor: pointer;
    border: 1px solid transparent;
    padding: 8px;
    font-weight: 700;
    box-shadow: 5px 5px 10px 5px rgba(119, 119, 119, .1), -5px -5px 10px 5px rgba(255, 255, 255, 1);
    margin: 10px auto;
    border-radius: 50px;
    position: absolute;
    right: 15px;
    bottom: 10px;color: transparent;      
    text-shadow: 2px 2px 3px rgba(255, 255, 255, 0.774);
    background-clip: text;
    -webkit-background-clip: text;
    -moz-background-clip: text;
    background-color: #2b2a25;
  }
  .app_body ul li .feedBtn:disabled {
    background-color: #fafafa;
    color: #7f7f7c;

}
.app_body ul li .feedBtn:disabled:hover {
    cursor: not-allowed;
    
}
.app_body ul li .feedBtns:not(:disabled):hover {
    box-shadow: -1px -1px 5px 3px rgba(119, 119, 119, 0.151), 2px 2px 5px 3px rgba(255, 255, 255, 1);
    font-size: 1.01em;
    color:rgba(119, 119, 119, 0.4);
}
.overlay {
  background-color: #f6f5f060;
}
.modal {
  width: 300px;
  height: 400px;
  background-color: rgba(5, 53, 10, 0.95);
}
  .modal h1 {
    text-align: center;
    font-weight: 900;
    font-size: 1em;
    color:#f6f5f096;
  }
  .followP {
    color: #f6f5f0;
    text-align: center;
    font-size: 2.3em;
    font-family: 'East Sea Dokdo';
  }
.profile-down {
  min-height: 50%;
}
  
.follow_count {
  display: inline-block;
  margin: 0 10px;
}
.follow_count button {
  font-family: 'Sunflower', cursive;
  color: #494844;
}
.follow_btn button {
  padding: 5px;
}
.followO:hover {
  color: rgb(17, 68, 23);
}
.followX {
  transform: rotate(45deg); 
}
.followX:hover {
  color: rgb(177, 27, 27);
}
.backTop {
  position: fixed;
  bottom: 30px;
  right: 30px;
}
.arrowUp {
  width: 30px;
  font-size: 2em;
  background-color: rgb(211, 0, 0);
  border-radius: 100px;
  padding: 5px;
  color: #f6f5f0;
}
</style>
