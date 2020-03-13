
<template>
  <div class="header">
    <router-link v-bind:to="{name:'Main'}" class="logo">
      <h1 class="app_title">
          <span class="app_title-span"> IN</span>:TEASER
      </h1>
    </router-link>
    <div class="search-bar">
      <input type="text" 
        autocomplete="off"
        id="searchInput"
        class="search-input"
        v-model="search"
        @keydown.down="searchColor"
        @keydown.up="searchColor"
        @keydown.enter="findUser(search_idx)"
        >
      <label for="searchInput" class="search-img"><i class="fas fa-lg fa-search"></i></label>
      <div class="search-result" v-if="search">
        <span>
          <p class="search-first">최근 검색어</p>
          <div v-for="(keyword, idx) in search_data.keywords" :key="idx" class="search-div search-div-first">
            <p class="search-keywords" 
              :class="'keywords-'+idx"
              @mouseover="searchMouseOver(idx, 'keywords')"
              @mouseleave="searchMouseLeave(idx, 'keywords')"
              @click="findUser(search_idx)">{{ keyword.search_keyword }}</p> 
            <p @click="deleteKeyword(keyword.keyword_seq, idx)" class="search-delete">삭제</p>
          </div>
        </span>
        
          <p class="search-second">검색 결과</p>
          <div class="scroll-div">
          <div class="search-div" 
            v-for="(result, idx) in search_data.results" 
            :key="idx"
            @click="findUser(search_idx)"
            @mouseover="searchMouseOver(idx, 'results')"
            @mouseleave="searchMouseLeave(idx, 'results')">
            <p :class="'results-'+idx" class="search-div-second">{{result.user_nickname}}</p>
          </div>
        </div>
      </div>
      
    </div>
    <nav id="page-nav">
      <!-- [MENU ITEMS] -->
      <ul>
        <li class="noti-li">
          <button class="noti-icon" @click="notiShow">
            <i class="far fa-lg fa-bell"></i>
          </button>
          <div class="noti-div" :style="{display: notiBlock}">
            <ul>
              <span v-for="(noti, idx) in allNoti" :key="idx">
                <li :class="{newNoti:noti.new_contentYN}">
                  <table>
                    <tr>
                      <td v-if="noti.new_contentYN">
                        <div class="newNotiDiv" >
                          <p>N</p>
                        </div>
                      </td>
                      <td class="notiContent">
                        <p v-html="noti.notice_content"></p>
                      </td>
                      <td class="notiDelTd">
                        <button class="noti-delete" @click="deleteNoti(idx)">삭제</button>
                      </td>
                    </tr>
                  </table>
                  <!-- <div class="newNotiDiv" >
                    <p>N</p>
                  </div>
                  <p v-html="noti.notice_content"></p>
                  <button class="noti-delete" @click="deleteNoti(idx)">삭제</button> -->
                </li>
              </span>
              <li class="li-to-center">
                <button id="notiDiv" @click="checkRequest">더 보기</button>
              </li>
            </ul>
          </div>
        

        <li>
          <button v-if="isLogin" @click="logout"><i class="fas fa-lg fa-sign-in-alt"></i></button>
          <router-link v-else v-bind:to="{name:'Login'}"><i class="fas fa-lg fa-sign-in-alt"></i></router-link>
        </li>
        <li>
          <router-link v-bind:to="{name:'Projects'}"><i class="far fa-lg fa-clipboard"></i></router-link>
        </li>
        <li class="dm-li">
          <button class="dm-icon" @click="dmShow">
          <i class="far fa-lg fa-envelope"></i>
          </button>
          <div class="direct-message" :style="{display: dmBlock}">
            <ul>
              <li class="dm-search">
                <i class="fas fa-search asLabel"></i>
                <input type="text" class="dm-input" 
                  placeholder="search" id="dmInput"
                  @keypress.enter="dmSearch"
                  v-model="searchDM">
                
              </li>
              
              <li class="DM-list" @click="startMessage(message)" v-for="(message, idx) in messages" :key="'message'+idx">
                <span class="dmNick">{{message.opponent_nickname}}</span>
                <span v-if="message.new_content_cnt" class="newDm">
                 {{message.new_content_cnt}}
                </span>
                <span v-else></span>
              </li>
              
            </ul>
          </div>
        </li>
        <li>
          <button @click="profile"><i class="far fa-lg fa-user"></i></button>
        </li>
      </ul>
    </nav>
    <dm @close="startMessage" :opponent_nickname="opponent_nickname" :server="server" id="dm" :style="{display: dmDisplay}"></dm>
    <div class="dm-result" :style="{display: dmResultBlock}">
      <ul>
        <li @click="DM(data)" v-for="data in search_DM_data" :key="data.user_seq">
          {{ data.user_nickname }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  import { mapState } from 'vuex'
  import cookie from '@/cookie.js'
  import NotiApi from '@/apis/NotiApi.js'
  // 토큰 및 사용자 정보를 저장하기 위해서 세션 스토리지를 사용한다.
  const storage = window.sessionStorage;
  import dm from '@/views/Dm.vue'
  import baseURL from '@/base-url.js'
  export default {
    components: {
      dm
    },
    props: {
      isLogin: {
        type: Boolean,
        required: true,
      },
      emitData: {
        type: Object,
      }
    },
    watch: {
      emitData: function(data) {
        this.opponent_seq = data.opponent_seq
        this.opponent_nickname = data.opponent_nickname
        this.startMessage(data)
      },
      isLogin: function(v) {
        this.user_email = cookie.cookieInfo()
        baseURL('/user/'+this.$store.state.userSeq+'/search-keyword-list')
        .then(res => {
          this.search_data.keywords = res.data.object
        })
      },
      search: function(v) {
        this.removeColor(this.search_idx, this.search_class)
        this.search_idx = -1
        this.search_class = 'keywords'        
        if (v === '') {
          this.search_data.results = []
        } else {
          baseURL('/user/'+v)
          .then(res=>{
            this.search_data.results = res.data.object
          })
        }
        document.querySelector('.search-input').addEventListener("click", () => {
          this.notiBlock = 'none'
          this.dmBlock = 'none'
        })
      },
      searchDM: function(v) {     
        if (v === '') {
          this.search_DM_data = {}
          this.dmResultBlock = 'none'
        } else {
          this.dmResultBlock = 'block'
          baseURL('/user/'+v)
          .then(res=>{
            var nickname = cookie.cookieNickname()
            this.search_DM_data = res.data.object.filter(val => { 
              if (val.user_nickname !== nickname) return true
            })
          })
        }
      },
      dmSearchResult: function() {
        const dmSearchInput = document.querySelector('#dmInput')
      }
      
    },

    mounted() {
      this.user_email = cookie.cookieInfo()
      baseURL('/user/'+cookie.cookieSeq()+'/search-keyword-list')
      .then(res => {
        this.search_data.keywords = res.data.object
      })
      document.addEventListener('click', (e) => {
        this.notiHide(e);
      })
      const notidiv = document.querySelector('#notiDiv')
      notidiv.addEventListener('click', this.notiHide2)
    },
    data: () => {
      return {
        messages: '',
        user_email: '',
        search_idx: -1,
        search_class: 'keywords',
        search: '',
        users: [] ,
        isOpen: false,
        limit: 0,
        allNoti: [],
        search_data: {
          results: {},
          keywords: {},
        },
        search_DM_data: {},
        notiBlock: 'none',
        dmBlock: 'none',
        dmResultBlock: 'none',
        server: [],
        opponent_nickname: '',
        dmDisplay: 'none',
        searchDM: '',
      }
    },
    methods: {
      DM(feed) {
        this.dmBlock = 'none';
        this.searchDM = ''
        this.startMessage({opponent_seq:feed.user_seq, opponent_nickname:feed.user_nickname})
      },
      startMessage(message) {
        if (this.dmDisplay == 'none') {this.dmDisplay = 'block'}
        else {this.dmDisplay = 'none'}
        if(this.dmBlock  !== 'none') this.dmShow()
        var data = {
          user_seq: cookie.cookieSeq(),
          opponent_seq: Number(message.opponent_seq)
        }
        NotiApi.dmStart(data, res=>{
          if (res.data.object) {
            this.server = res.data.object
          }
          this.opponent_nickname = message.opponent_nickname
        }, err=>{})        
      },
      dmList() {
        var data = {
          user_seq: cookie.cookieSeq()
        }
        NotiApi.dmList(data, res => { 
          this.messages = res.data.object
         }, err=>{})
      },
      deleteNoti(idx) {
        NotiApi.deleteNoti({user_seq:cookie.cookieSeq(), notice_seq:this.allNoti[idx].notice_seq}, res => {
          this.allNoti.splice(idx, 1)
        }, err=>{})
      },
      checkRequest() {
        NotiApi.checkRequest({user_seq:cookie.cookieSeq()}, res => {
          this.$router.push({name:'Noti'})
          }, err=> {})
      },
      profile() {
        this.$router.push({name:'Profile', params:{userSeq:cookie.cookieSeq()}})
      },
      notiModal() {
        this.isOpen = false
        this.$router.push('Noti')
      },
      
      notiHide(e) {
        if (!this.$el.contains(e.target) || e.target.id === 'searchInput')  {
          this.notiBlock = 'none';
          this.dmBlock = 'none';
          this.search = ''
          this.searchDM = ''
        } 
      },
      notiHide2() {
        this.notiBlock = 'none';
      },
      notiShow() {
        if (this.notiBlock == 'none') {
          NotiApi.noticeCenter({user_seq:cookie.cookieSeq()}, res => {
            this.allNoti = res.data.object
          }, err=> {})
          this.notiBlock = 'block'
          this.dmBlock = 'none'
          this.dmResultBlock = 'none'
          if (this.search) {
            document.querySelector('.search-result').style.display = 'none'
            this.search = ''
          }
        } else {
          this.notiBlock = 'none'
        }
      },
      dmShow() {
        if (this.dmBlock == 'none') {
          this.dmBlock = 'block'
          this.notiBlock = 'none'
          if (this.search) {
            document.querySelector('.search-result').style.display = 'none'
          }
          this.dmList()
        } else {
          this.dmBlock = 'none'
          NotiApi.dmExit({user_seq:cookie.cookieSeq()})
        }
      },
      dmSearch() {
        if (this.dmResultBlock == 'none') {
          this.dmResultBlock = 'block'
        } else {
          this.dmResultBlock = 'none'
        }
      },
      deleteKeyword(keywordSeq, idx) {
        // this.results = this.results.filter(keyword => keyword.seq !== idx)
        baseURL.delete('/user/'+keywordSeq+'/remove-search-keyword')
        .then(res => {
          this.search_data.keywords.splice(idx, 1)
        })
      },
      logout() {
        var date = new Date();
        this.$store.commit('isLogin')
        this.$store.commit('clearInfo')
        document.cookie = 'login_user' + "= " + "; expires=" + date.toUTCString() + "; path=/";
        document.cookie = 'jwt-auth-token' + "= " + "; expires=" + date.toUTCString() + "; path=/";
        document.cookie = 'login_seq' + "= " + "; expires=" + date.toUTCString() + "; path=/";
        this.$router.push('/')
      },

      findUser(idx) {
        if (this.search_idx === -1) {
          this.search_idx = 0
          this.search_class = 'results'
        }
        var search_keyword = ''
        

        if (this.search_class === 'keywords') search_keyword = this.search_data[this.search_class][this.search_idx].search_keyword
        else search_keyword = this.search_data[this.search_class][this.search_idx].user_nickname
        this.search = ''
        this.search_data.results = []
        baseURL.post('/user/'+cookie.cookieSeq()+'/add-search-keyword', null, {
        params: {
          search_keyword: search_keyword
        }
        })
        .then(res => {
          baseURL('/user/'+cookie.cookieSeq()+'/search-keyword-list')
          .then(res => {
            this.search_data.keywords = res.data.object
          })

          baseURL('/user/'+search_keyword)
          .then(res => {
            this.$router.push({name:'Profile', params:{
              userSeq: res.data.object.filter(res=> res.user_nickname === search_keyword)[0].user_seq
            }})
          })
        })
        
        
      },
      removeColor(idx, cls) {
        if (idx > -1 && idx < this.search_data[cls].length) {
          document.querySelector('.'+cls+'-'+idx).style.fontSize=""
          document.querySelector('.'+cls+'-'+idx).removeChild(document.querySelector('.'+cls+'-'+idx).children[0])
          }
      },
      changeColor(idx, cls) {
        if (idx > -1 && idx < this.search_data[cls].length) {
          document.querySelector('.'+cls+'-'+idx).style.fontSize="18px"
          document.querySelector('.'+cls+'-'+idx).innerHTML = '<i class="fas fa-caret-right" style="font-size: 1.2em; position:relative; top: 3px; margin-right: 20px; color: rgb(5, 59, 11);"></i>' + document.querySelector('.'+cls+'-'+idx).innerHTML
          }
      },
      searchMouseOver(idx, cls) {
        this.removeColor(this.search_idx, this.search_class)
        this.search_idx = idx
        this.search_class = cls
        this.changeColor(idx, cls)
      },
      searchMouseLeave(idx, cls) {
        this.removeColor(idx, cls)
        this.search_idx = -1
        this.search_class = 'keywords'
      },
      searchColor(key) {
        var first = ''
        var div = ''
        if (this.search_data.keywords === null) {
          this.search_class = 'results'
        } 
        var pre_idx = this.search_idx
        var pre_class = this.search_class
        if (key.key === "ArrowDown") 
        {
          if (this.search_idx >= this.search_data[this.search_class].length - 1) {
            this.search_idx = 0
            if (this.search_data.keywords !== null) {
              if (this.search_class === 'keywords') this.search_class = 'results'
              else if (this.search_class === 'results') this.search_class = 'keywords'
            }
          }
          else this.search_idx += 1
          this.changeColor(this.search_idx, this.search_class)
          this.removeColor(pre_idx, pre_class)

          if (this.search_class === 'results') {
            first = document.getElementsByClassName('scroll-div')[0].offsetTop
            div = document.getElementsByClassName(this.search_class+'-'+this.search_idx)
            document.getElementsByClassName('scroll-div')[0].scrollTop =  div[0].offsetTop - first
          }
         
        } 
        else if (key.key === "ArrowUp") 
        {
          if (this.search_idx <= 0){ 
            if (this.search_data.keywords !== null) {
              if (this.search_class === 'keywords') this.search_class = 'results'
              else if (this.search_class === 'results') this.search_class = 'keywords'
            }
            this.search_idx = this.search_data[this.search_class].length - 1
          }
          else this.search_idx -= 1
          this.changeColor(this.search_idx, this.search_class)
          this.removeColor(pre_idx, pre_class)
          if (this.search_class === 'results') {
            first = document.getElementsByClassName('scroll-div')[0].offsetTop
            div = document.getElementsByClassName(this.search_class+'-'+this.search_idx)
            document.getElementsByClassName('scroll-div')[0].scrollTop =  div[0].offsetTop - first
          }
        }
      },
    }
  }
</script>

<style scoped>
  @import "//netdna.bootstrapcdn.com/font-awesome/3.0/css/font-awesome.css";
  
  .DM-list {
    cursor: pointer;
    width: 100%;
  }
  #dm {
    z-index: 100;
    position: fixed;
    bottom: 0;
    right: 0;
  }
  /* dm detail */
    .app {
	padding-top: 10px !important;
	min-height: 500px !important;
	min-width: 350px !important;
	max-width: 400px !important;
	background-color: #f6f6f6;
  box-shadow: inset 7px 7px 10px rgba(119, 119, 119, .1), inset -7px -7px 10px rgba(255, 255, 255, 1);
	position: relative;
}
.page-container {
	max-height: 500px;
	position: relative;
}
.dm-close {
	margin-top: 10px;
	float: right;
	font-size: 16px;
}
.dm-close:hover {
	color: rgb(177, 27, 27);
}
.md-toolbar-row {
	border-bottom: 1px solid rgb(5, 59, 11);
}
.md-title {
	font-size: 20px;
}
.dm-ul {
	max-height: 350px;
	overflow-y: scroll;
}
.dm-bottom {
	position: absolute;
	bottom: 10px;
}
.dm-input {
	outline: none;
	height: 50px;
	width: 80%;
	border-radius: 50px;
	border: none;
	padding: 0 30px;
	box-shadow: inset 5px 5px 10px rgba(119, 119, 119, .1), inset -5px -5px 10px rgba(255, 255, 255, 1);
	margin-bottom: 10px;
}
.dm-input:hover {
    box-shadow: -2px -2px 2px rgba(119, 119, 119, .1), 2px 2px 2px rgba(255, 255, 255, 1);
}
.dm-submit {
	cursor: pointer;
	border: 1px solid transparent;
	padding: 5px 10px;
	font-weight: 700;
	box-shadow: 2px 2px 4px 2px rgba(119, 119, 119, .1), -2px -2px 4px 2px rgba(255, 255, 255, 1);
	margin-left: 15px;
	border-radius: 50px;
	color:#013f01;
	position: relative;
}
.dm-submit:hover {
	box-shadow: -5px -5px 10px 5px rgba(119, 119, 119, .1), 5px 5px 10px 5px rgba(255, 255, 255, 1);
	color:#013f01;
	border-color: #f7f5ec;
}
.md-app {

		height: 800px;
		border: 1px solid rgba(#000, .12);
}
.md-textarea {
		height: 300px;
}
.talk-bubble {
	margin: 10px 5px;
  display: block;
  position: relative;
	min-height: 50px;
	/* border: 2px dashed #ccc; */
	/* background-color: #f6f5f0; */
	z-index: 102;
	background-color: #E7EBE0FF;
}
.talk-bubble.left-in {
	background-color: rgb(199, 231, 238);
}
.border{
  border: 8px solid #666;
}
.round{
  border-radius: 30px;
	-webkit-border-radius: 30px;
	-moz-border-radius: 30px;

}

/* Right triangle, left side slightly down */

.tri-right.left-in:after{
	content: ' ';
	position: absolute;
	width: 0;
	height: 0;
  left: -5px;
	right: auto;
  top: 5px;
	bottom: auto;
	border: 10px solid;
	border-color: rgb(199, 231, 238) rgb(199, 231, 238) transparent transparent;
}
.tri-right.same {
	margin-right: 10px;
	margin-left: auto;
	display: block;
}
.talknew {
	margin-right: 10px;
	margin-left: auto;
	display: block;
}
/* Right triangle, right side slightly down*/

.tri-right.right-in:after{
	content: ' ';
	position: absolute;
  left: auto;
	right: -10px;
  top: 15px;
	bottom: auto;
	z-index: 101;
	border: 12px solid;
	border-color: #E7EBE0FF transparent transparent #E7EBE0FF;
}

/* talk bubble contents */
.talktext{
	text-align: left;
	padding: 20px 20px 10px 20px;
	line-height: 100%;
	
}
.talktext p{
  /* remove webkit p margins */
  -webkit-margin-before: 0em;
	-webkit-margin-after: 0em;
}
.mytalk {
	text-align: right !important;
}

  .history-back {
    margin-top: 15px;
    font-size: 1.5em;
  }
  a.logo:link, a.logo:hover, a.logo:active, a.logo:visited {
    text-decoration: none;
    margin-left: 20px;
  }
  .app_title {
    font-size: 24px;
    margin: 16px;
  }
  .header {
    width: 100%;
    display: flex;
    justify-items: flex-end;
  }

  /* search */
      .search-bar {
        margin-top: 7px;
        margin-right: 10px;
        width: 235px;
        display:inline-block;
        margin-left: auto;
        position: relative;
      }
      .search-bar .search-result {
        position: absolute;
        width: 250px;
        right: -10px;
      }
      .search-result {
        z-index: 102;
        background-color: #f6f5f0;
        box-shadow: 5px 5px 10px 5px rgba(119, 119, 119, 0.4);
        padding: 10px;
        border-radius: 5px;
        border-right: 3px solid rgba(119, 119, 119, 0.2);
        border-bottom: 3px solid rgb(5, 59, 11);
    }
      .search-result .search-keywords {
        display: inline-block;
      }
      .search-result .search-delete {
        display: inline-block;
        margin-left: 10px;
        cursor: pointer;
        border: 1px solid #ccc;
        padding: 5px;
        border-radius: 30px;
        box-shadow: 1px 1px 2px 1px rgba(119, 119, 119, .1), -1px -1px 2px 1px rgba(255, 255, 255, 1);

      }
      .search-result .search-delete:hover {
        font-size: 1.02em;
        color: rgb(177, 27, 27);
        box-shadow: -1px -1px 2px 1px rgba(119, 119, 119, .1), 1px 1px 2px 1px rgba(255, 255, 255, 1);
        font-weight: 900;
      }
      .search-bar .search-input {
        border: none;
        width: 200px;
        display: inline-block;
        position: relative;
        text-align: end;
        font-size: 1.2em;
        margin-right: 10px;
      }
      .search-bar .search-div {
        overflow: hidden;
        text-align: right;
        margin-right: 20%;
        display: inline-block;
        /* border: 3px groove #f6f5f0; */
        color: #000;
        height: 50px;
        line-height: 30px;
        
      }
      
      .search-first, .search-second {
        padding: 5px;
        margin: 0 10px 15px;
        font-size: 0.85em;
        font-weight: 900;
        color: rgb(5, 59, 11);
        border-bottom: 1px solid rgb(5, 59, 11);
        
      }
      .search-div {
        padding: 5px;
        width: 100%;
        border-radius: 50x;
      }
      
      .search-div p {
        position: relative;
      }

      .scroll-div {
        max-height: 150px;
        overflow-y: scroll;
        overflow-x: hidden;
      }
  

  #page-nav {
    margin-right: 30px;
    margin-top: 10px;
    /* clear: both; */
    display: inline-block;
    border: none;
    /* If you want the navigation bar to stick on top */
    text-align: end;

  }
  /* Menu Items */
  ul {
    list-style-type: none;
    margin: 0;
    padding: 0; 
    z-index: 999;
  }
  ul li {
    display: inline-block;
    padding: 10px;
    box-sizing: border-box;
  }
  ul li:hover {
  }


  ul li:hover a{
    font-size: 1.01em;
  }
  ul li:hover button{
    font-weight: 1.01em;
  }
  ul li a {
    font-weight: 100;
    color: #000;
    text-decoration: none;
  }

  ul li button {
    font-weight: 200;
  }

  ul li button {
    color: #000;
    text-decoration: none;
  }

  .noti-li {
    position: relative;
  }
  .noti-div {
    /* display: none; */
    position: absolute;
    top: 49px;
    left: -225px;
    width: 400px;
    z-index: 102;
    background-color: #f6f5f0;
    box-shadow: 5px 5px 10px 5px rgba(119, 119, 119, 0.4);
    padding: 10px;
    border-radius: 5px;
    border-right: 3px solid rgba(119, 119, 119, 0.2);
    border-bottom: 3px solid rgb(5, 59, 11);
    max-height: 400px;
    overflow-y: scroll;
  }
  

  .noti-div ul li:not(.li-to-center) {
    display: inline-block;
    width: 100%;
    height: 50px;
    text-align: left;
    line-height: 25px;
  }
  .noti-div ul li p {
    width: 100%;
    display: inline-block;

  }
  .notiContent {
    padding-left: 10px;
  }
  .notiDelTd {
    width: 8%;
  }
  .noti-div ul li .noti-delete {
    display: absolute;
    font-weight: 500;
    border: 1px solid #ccc;
    border-radius: 50px;
    padding: 5px;
    box-shadow: 1px 1px 2px 1px rgba(119, 119, 119, .1), -1px -1px 2px 1px rgba(255, 255, 255, 1);
  }

  .noti-div ul li .noti-delete:hover {
    font-size: 1.02em;
    color: rgb(177, 27, 27);
    box-shadow: -1px -1px 2px 1px rgba(119, 119, 119, .1), 1px 1px 2px 1px rgba(255, 255, 255, 1);
    font-weight: 900;
  }

  .li-to-center {
    padding: 0;
    margin-top: 20px;
    margin-bottom: 10px;
    display: block;
    text-align: center;
  }

  .newNotiDiv {
    display: inline-block;
    text-align: center;
    color: rgb(255, 255, 255);
    font-weight: 900;
    font-style: italic;
    
  }
  .newNotiDiv p {
    padding: 3px 5px 3px 4px;
    margin-right: 10px;
    border-radius: 100px;
    background: rgb(23, 63, 0);
  }

  #notiDiv {
    font-size: 1.1em;
    font-weight: bolder;
    color: transparent;      
    text-shadow: 2px 2px 3px rgba(255, 255, 255, 0.5);
    background-clip: text;
    -webkit-background-clip: text;
    -moz-background-clip: text;
    background-color: #83827f;
  }

  #notiDiv:hover {
    font-size: 1.12em;
    text-shadow: 2px 2px 3px rgba(255, 255, 255, 0.3);
    background-color: rgb(5, 59, 11);
  }

  .dm-li {
    position: relative;
  }

  .direct-message {
    position: absolute;
    top: 48px;
    left: -350px;
    width: 400px;
    z-index: 102;
    background-color: #f6f5f0;
    box-shadow: 5px 5px 10px 5px rgba(119, 119, 119, .4);
    padding: 10px;
    border-radius: 5px;
    border-right: 3px solid rgba(119, 119, 119, 0.2);
    border-bottom: 3px solid rgb(5, 59, 11);
    min-height: 100px;
    max-height: 400px;
    overflow-y: scroll;
  }

  .direct-message li {
    text-align: right;
    display: block;
    /* border: 3px groove #f6f5f0; */
    height: 50px;
    line-height: 30px;
  }
  .direct-message li:not(:first-child):hover {
    background-color: #013f01;
    border-radius: 10px;
    color: #f6f5f0;
    font-weight: 900;
  }
  .direct-message li:nth-child(2) {
    margin-top: 10px;
  }
  .dmNick {
    font-size: 1.05em;
    margin-right: 8px;
  }
  .newDm {
    background:rgb(5, 59, 11);
    border-radius: 50px;
    color: #f6f5f0;
    padding: 5px 8px 5px 7px;
    text-align: center;
    font-size: 0.8em;
  }
  input.dm-input {
    height: 45px;
    border: none;
    display: inline-block;
    width: 90%;
  }
  .asLabel {
    display: inline-block;
    margin-right: 10px;
  }
  .dm-result {
    background-color: #ebe9e3;
    border-radius: 0 0 10px 10px;
    position: absolute;
    right: 95px;
    top: 125px;
    width: 300px;
    max-height: 300px;
    overflow-y: scroll;
    z-index: 110;
    border-bottom: 3px solid rgb(5, 59, 11);
  }
  .dm-result>ul>li {
    display: block;
  }
  .dm-result>ul>li:hover{
    display: block;
    cursor: pointer;
    background-color: rgb(5, 59, 11);
    color: #f6f5f0;
    font-size: 1.06em;
    font-weight: 900;
  }

</style>