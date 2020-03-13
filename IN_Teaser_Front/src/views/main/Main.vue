<template>
  <div class="body-wrap">
    <div class="main-body">
      
      <div class="body-middle">
        <div class="warpper">
          <input class="radio" v-model="teed_tab" value="NEW" id="one" name="group" type="radio" checked>
          <input class="radio" v-model="teed_tab" value="FEED" id="two" name="group" type="radio">
          <div class="tabs">
            <label class="tab" id="one-tab" for="one"  @click="feedNew">NEW</label>
            <label class="tab" id="two-tab" for="two" @click="feedFollow">FEED</label>
          </div>
          <div class="new-article">
            
            <select 
              @change="onChange($event)"
              name="feed-filter" id="feed-filter"
              >
              <option value="all">전체 게시글</option>
              <option value="que">질문 게시글</option>
            </select>
            <router-link v-bind:to="{name:'Article'}">
              <i class="fas fa-plus plus"></i>
            </router-link>
          </div>
          
          <div class="panels">
            <div class="feeds panel" id="one-panel">
              <span v-for="(feed, idx) in feeds" :key="'feed'+feed.feed_seq">
                <div v-if="feed.feed_contents" class="feed">
                  <div class="feed-body">
                    <div class="content hideContent">
                      <p class="article-subject" v-if="feed.feed_subject == '질문'">Q</p>
                      <router-link :to="{name:'Detail', params:{feedSeq:feed.feed_seq}}">
                        <viewer
                          :value="feed.feed_contents"
                          class="content-viewer"
                        />
                      </router-link>
                      <p class="article-tag" v-if="feed.feed_hashtag">{{ feed.feed_hashtag }}</p>
                    </div>
                    <!-- <div class="show-more">
                      <router-link :to="{name:'Detail', params:{feedSeq:feed.feed_seq}}">
                        더 보기
                      </router-link>
                    </div> -->
                    <div class="feed-header">
                      <router-link :to="{name:'Profile', params:{userSeq:feed.user_seq}}">
                        <img v-if="feed.user_profile_route" :src="'https://i02b204.p.ssafy.io' + feed.user_profile_route" alt="profile_img" width="80px" height="100%" style="max-height: 90px;">
                        <img v-else src="@/assets/images/user.svg" alt="profile_img" width="60px">
                      </router-link>
                      <br>
                      <router-link :to="{name:'Profile', params:{userSeq:feed.user_seq}}" style="font-size: 2em; font-family: 'East Sea Dokdo';">{{ feed.user_nickname }}</router-link>
                      <p> 
                        <span :class="'feed'+feed.feed_seq" @click="like(feed)">
                          <i :class="{isLike: feed.login_user_like, noLike: !feed.login_user_like}" class="fas fa-heart"></i>
                        </span>
                      </p>
                      <p style="font-style: italic; font-size: 0.9em;">
                        {{ feed.post_date }}
                      </p>
                      <p class="header-bottom-wrap"> 
                        <span v-if="feed.user_seq != user_seq" @click="DM(feed)" class="hide-span">
                          <i class="far fa-paper-plane header-bottom" alt="dm보내기"></i>
                        </span>
                        <span :class="'feedscrap'+feed.feed_seq" @click="scrap(feed)">
                          <i :class="{isScrap: feed.login_user_scrap, noScrap: !feed.login_user_scrap}" class="far fa-bookmark header-bottom"></i>
                        </span>
                        <span @click="report(feed)" class="hide-span">
                          <i class="fas fa-exclamation-triangle header-bottom"></i>
                        </span>
                        
                        <span @click="hide(feed, idx)" class="hide-span">
                          <i class="far fa-eye-slash header-bottom"></i>
                        </span>
                        
                      </p>
                    </div>
                  </div>
                  <div class="feed-comment">
                    <p :class="{'recoment':comment.order_num > 0}"  v-for="comment in feed.comment" :key="'comment'+comment.comment_seq">
                      <span v-if="comment.order_num !== 0"><i class="fas fa-angle-double-right"></i></span>
                      <span style="font-size: 1.8em; margin-right: 10px; font-family: 'East Sea Dokdo';">{{ comment.user_nickname }}</span>
                      <span v-if="comment.comment === ''" style="color: #ccc;">삭제된 댓글입니다.</span>
                      <span v-else>{{ comment.comment }}</span>
                      <span> {{ comment.insert_date }}</span></p>
                  </div>
                </div>
              </span>
            </div>
            <div class="feeds panel" id="two-panel">
              <span v-if="feedsFollow !== null">
                <span v-for="(feed, idx) in feedsFollow" :key="'feed2'+feed.feed_seq">
                  <div v-if="feed.feed_contents" class="feed">
                    <div class="feed-body">
                      <div class="content hideContent">
                        <p class="article-subject" v-if="feed.feed_subject == '질문'">Q</p>
                        <router-link :to="{name:'Detail', params:{feedSeq:feed.feed_seq}}">
                          <viewer
                            :value="feed.feed_contents"
                            class="content-viewer"
                          />
                        </router-link>
                        <p class="article-tag" v-if="feed.feed_hashtag">{{ feed.feed_hashtag }}</p>
                      </div>
                      <!-- <div class="show-more">
                        <router-link :to="{name:'Detail', params:{feedSeq:feed.feed_seq}}">
                          더 보기
                        </router-link>
                      </div> -->
                      <div class="feed-header">
                        <router-link :to="{name:'Profile', params:{userSeq:feed.user_seq}}">
                          <img v-if="feed.user_profile_route" :src="'https://i02b204.p.ssafy.io' + feed.user_profile_route" alt="profile_img" width="80px" height="100%" style="max-height: 90px;">
                          <img v-else src="@/assets/images/user.svg" alt="profile_img" width="60px">
                        </router-link>
                        <br>
                        <router-link :to="{name:'Profile', params:{userSeq:feed.user_seq}}" 
                                       style="font-size: 2em; font-family: 'East Sea Dokdo';">
                          {{ feed.user_nickname }}
                        </router-link>
                        <p> 
                          <span :class="'feed'+feed.feed_seq" @click="like(feed)">
                            <i :class="{isLike: feed.login_user_like, noLike: !feed.login_user_like}" class="fas fa-heart"></i>
                          </span>
                        </p>
                        <p style="font-style: italic; font-size: 0.9em;">
                        {{ feed.post_date }}
                      </p>
                      <p class="header-bottom-wrap"> 
                        <span v-if="feed.user_seq != user_seq" @click="DM(feed)" class="hide-span">
                          <i class="far fa-paper-plane header-bottom" alt="dm보내기"></i>
                        </span>
                        <span :class="'feedscrap'+feed.feed_seq" @click="scrap(feed)">
                          <i :class="{isScrap: feed.login_user_scrap, noScrap: !feed.login_user_scrap}" class="far fa-bookmark header-bottom"></i>
                        </span>
                        <span @click="report(feed)" class="hide-span">
                          <i class="fas fa-exclamation-triangle header-bottom"></i>
                        </span>
                        
                        <span @click="hide(feed, idx)" class="hide-span">
                          <i class="far fa-eye-slash header-bottom"></i>
                        </span>
                      </p>
                      </div>
                    </div>
                    <div class="feed-comment">
                      <p :class="{'recoment':comment.order_num > 0}"  v-for="comment in feed.comment" :key="'comment2'+comment.comment_seq">
                        <span v-if="comment.order_num !== 0"><i class="fas fa-angle-double-right"></i></span>
                        <span style="font-size: 1.8em; margin-right: 10px; font-family: 'East Sea Dokdo';">{{ comment.user_nickname }}</span>
                        <span v-if="comment.comment === ''" style="color: #ccc;">삭제된 댓글입니다.</span>
                        <span v-else>{{ comment.comment }}</span>
                        <span> {{ comment.insert_date }}</span></p>
                    </div>
                  </div>
                </span>
              </span>
              <span v-else style="color: #666;">
                <p>팔로우하고 있는 친구가 쓴 글이 없네요</p>
                <p><span class="logo" style="font-size: 1.5em; font-weight: 900;"><span class="logo_span">IN</span>:TEASER</span>에서 새로운 인연을 만들어보세요</p>
              </span>
            </div>
          </div>
        </div>
      </div>
      <button class="backTop" @click="backTop"><i class="fas fa-arrow-up arrowUp"></i></button>
      <div class="body-right">
        
        <div class="random-user">
          <ul v-if="randomUsers">
            <li v-for="(randomUser, idx) in randomUsers" :key="'random'+idx">
              
              <router-link :to="{name:'Profile', params:{userSeq:randomUser.user_seq}}">
                <div class="frame_body">
                  <div v-if="randomUser.user_profile_route" class="heading-frame" :style="{ 'background-image': 'url(https://i02b204.p.ssafy.io' + randomUser.user_profile_route + ')' }">
                    <h2 contenteditable="true">
                      {{ randomUser.user_nickname }}
                    </h2>
                  </div>
                  <div v-else class="heading-frame notYet">
                    <h2 contenteditable="true">
                      {{ randomUser.user_nickname }}
                    </h2>
                  </div>
                </div>
                
              </router-link>
              
            </li>
          </ul>
          <p v-else class="knockout" id="one">관심분야를 설정해주시면 해당 분야의 친구들을 추천해드립니다</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import '@/assets/css/main.css'
  import '@/assets/css/tab.css'
  import 'codemirror/lib/codemirror.css';
  import 'tui-editor/dist/tui-editor.css';
  import 'tui-editor/dist/tui-editor-contents.css';
  import 'highlight.js/styles/github.css';
  import 'tui-editor/dist/tui-editor-extColorSyntax.js'
  import 'tui-editor/dist/tui-editor-extScrollSync.js'
  import UserApi from '@/apis/UserApi.js'
  import FeedApi from '@/apis/FeedApi.js'
  import baseURL from '@/base-url.js'
  import cookie from '@/cookie.js'
  require('tui-editor/dist/tui-editor.css');
  require('tui-editor/dist/tui-editor-contents.css');
  require('codemirror/lib/codemirror.css');
  var toastui = require('@toast-ui/vue-editor');
  var Viewer = toastui.Viewer;

  export default {
    components: {
      'viewer': Viewer,
    },
    data:() => {
      return {
        feeds: [],
        teed_tab: 'NEW',
        feedsFollow: [],
        randomUsers: [],
        profiles: [],
        allfeeds: [],
        allfeedsFoolow: [],
        isFilter: true,
        since_id: 0,
        max_id: 0,
        final_id: 0,
        user_seq: '',
      }
    },    
    mounted() {
      this.feedNew()
      this.user_seq = cookie.cookieSeq()
      baseURL('/users/' + cookie.cookieSeq() + '/random')
        .then(res => {
          this.randomUsers = res.data.object
        })
      
      this.scroll()
    },
    methods: {
      DM(feed) {
        this.$emit('DM', {opponent_seq:feed.user_seq, opponent_nickname:feed.user_nickname})
      },
      scroll () {
        window.onscroll = () => {
          let bottomOfWindow = Math.max(window.pageYOffset, document.documentElement.scrollTop, document.body.scrollTop) + window.innerHeight === document.documentElement.offsetHeight

          if (bottomOfWindow && this.teed_tab == "NEW") {
            if (document.querySelector('#feed-filter').options.selectedIndex) {
              this.qNewUpdate()
            } else {
              this.feedNewUpdate()
            }
          } else if (bottomOfWindow && this.teed_tab == "FEED") {
            if (document.querySelector('#feed-filter').options.selectedIndex) {
              this.qFollowUpdate()
            } else {
              this.feedFollowUpdate()
            }
          }
      }
      },
      onChange(event) {
        if (event.target.options.selectedIndex) {
          if (this.teed_tab === 'NEW') {
            this.feeds = []
            this.qNew()
          }
           else {
            this.feedsFollow = []
            this.qFollow()
          }
        }
        else{
          if (this.teed_tab === 'NEW') { 
            this.feeds = []
            this.feedNew()
          } else {
            this.feedsFollow = []
            this.feedFollow()
          }
        }

      },
      feedNew() {
        document.querySelector('#feed-filter').options.selectedIndex = 0
        baseURL('/feed/' + cookie.cookieSeq() + '/new-feed-list?since_id=0&max_id=0&final_id=0')
          .then(res =>{
            if (res.data.object != null) {
              this.feeds = res.data.object.feed
              this.since_id = res.data.object.since_id
              this.max_id = res.data.object.max_id
              this.final_id = res.data.object.final_id
            }
          }, err=> {})

      },
      feedNewUpdate() {
        baseURL('/feed/' + cookie.cookieSeq() + '/new-feed-list?since_id='+ this.since_id + '&max_id=' + this.max_id + '&final_id=' + this.final_id)
          .then(res =>{
            if (res.data.object != null) {
              res.data.object.feed.forEach(element => {
                this.feeds.push(element)
              });
              this.since_id = res.data.object.since_id
              this.max_id = res.data.object.max_id
              this.final_id = res.data.object.final_id
              }
            }, err=> {})
      },
      qNew() {
        baseURL('/feed/' + cookie.cookieSeq() + '/question-feed-list?since_id=0&max_id=0&final_id=0')
          .then(res =>{
            if (res.data.object != null) {
              this.feeds = res.data.object.feed
              this.since_id = res.data.object.since_id
              this.max_id = res.data.object.max_id
              this.final_id = res.data.object.final_id
            }
          }, err=> {})
      },
      qNewUpdate() {
        baseURL('/feed/' + cookie.cookieSeq() + '/question-feed-list?since_id='+this.since_id+'&max_id='+this.max_id+'&final_id='+this.final_id)
          .then(res =>{
            if (res.data.object != null) {
              res.data.object.feed.forEach(element => {
                this.feeds.push(element)
              });
              this.since_id = res.data.object.since_id
              this.max_id = res.data.object.max_id
              this.final_id = res.data.object.final_id
            }
          }, err=> {})
      },
      feedFollow() {
        document.querySelector('#feed-filter').options.selectedIndex = 0
        baseURL('/feed/' + cookie.cookieSeq() + '/follow-feed-list?since_id=0&max_id=0&final_id=0')
        .then(res => {
          if (res.data.object != null) {
            res.data.object.feed.forEach(element => {
              this.feedsFollow.push(element)
            });
            this.since_id = res.data.object.since_id
            this.max_id = res.data.object.max_id
            this.final_id = res.data.object.final_id
            this.isFilter = true
          }
        })
      },
      feedFollowUpdate() {
        baseURL('/feed/' + cookie.cookieSeq() + '/follow-feed-list?since_id='+this.since_id+'&max_id='+this.max_id+'&final_id='+this.final_id)
        .then(res => {
          if (res.data.object != null) {
            res.data.object.feed.forEach(element => {
              this.feedsFollow.push(element)
            });
            this.since_id = res.data.object.since_id
            this.max_id = res.data.object.max_id
            this.final_id = res.data.object.final_id
          }
        })
      },
      qFollow() {
        baseURL('/feed/' + cookie.cookieSeq() + '/follow-question-feed-list?since_id=0&max_id=0&final_id=0')
        .then(res => {
          if (res.data.object != null) {
            this.feedsFollow = res.data.object.feed
            this.since_id = res.data.object.since_id
            this.max_id = res.data.object.max_id
            this.final_id = res.data.object.final_id
          }
        })
      },
      qFollowUpdate() {
        baseURL('/feed/' + cookie.cookieSeq() + '/follow-question-feed-list?since_id='+this.since_id+'&max_id='+this.max_id+'&final_id='+this.final_id)
        .then(res => {
          if (res.data.object != null) {
            res.data.object.feed.forEach(element => {
              this.feedsFollow.push(element)
            });
            this.since_id = res.data.object.since_id
            this.max_id = res.data.object.max_id
            this.final_id = res.data.object.final_id
          }
        })
      },
      like(feed) {
        var data = {
          user_seq: cookie.cookieSeq(), 
          feedlike_seq: feed.feedlike_seq,
          feed_seq: feed.feed_seq, 
          user_nickname: cookie.cookieNickname(),
        }
        if (feed.login_user_like) {
          baseURL.delete('/feed/' + cookie.cookieSeq() + '/like-cancel/' + feed.feedlike_seq, {data})
            .then(res => {
              document.querySelector('.feed'+feed.feed_seq+'>svg').style.color = 'rgb(58, 57, 56)'
              feed.login_user_like = false
            })
            .catch(err => { 
            })
        } else {
          baseURL.post('/feed/' + cookie.cookieSeq() + '/like/' + feed.feed_seq, data)
            .then(res => {
              document.querySelector('.feed'+feed.feed_seq+'>svg').style.color = 'rgb(194, 26, 26)'
              feed.login_user_like = true
              feed.feedlike_seq = res.data.object.feedlike_seq
            })
            .catch(err => {
            })
        }
      },
      scrap(feed) {
        var data = {
          user_nickname: cookie.cookieNickname(),
          feed_seq: feed.feed_seq,
          user_seq: cookie.cookieSeq(),
          feedscrap_seq: feed.feedscrap_seq
        }
        if (feed.login_user_scrap) {
          baseURL.delete('/feed/' + cookie.cookieSeq() + '/scrap-cancel/' + feed.feedscrap_seq, {data})
          .then(res => {
            document.querySelector('.feedscrap'+feed.feed_seq+'>svg').style.color = 'rgb(58, 57, 56)'
            feed.login_user_scrap = false
          }) 
        } else {
          
          baseURL.post('/feed/' + cookie.cookieSeq() + '/scrap/' + feed.feed_seq, data)
          .then(res => {
            document.querySelector('.feedscrap'+feed.feed_seq+'>svg').style.color = 'rgb(194, 26, 26)'
            feed.login_user_scrap = true
            feed.feedscrap_seq = res.data.object.feedscrap_seq
          })
        }
      },
      hide(feed, idx) {
        if (confirm('정말 숨기시겠습니까?')) {
          this.hideBack(feed, idx)
        }
      },
      hideBack(feed, idx) {
        var data = {
          user_nickname: cookie.cookieNickname()
        }
        baseURL.post('/feed/' + cookie.cookieSeq() + '/feed-hide/' + feed.feed_seq, data)
        .then(res => {
          this.feeds.splice(idx, 1)
        })
      },
      report(feed) {
        if (confirm('정말 신고하시겠습니까?')) {
          this.reportBack(feed)
        }
      },
      reportBack(feed) {
        var data = {
          user_nickname: cookie.cookieNickname(),
          oppnent_seq: feed.user_seq,
          oppnent_nickname: feed.user_nickname,
        }
        baseURL.post('/feed/' + cookie.cookieSeq() + '/feed-report/' + feed.feed_seq, data)
      },
      myFunction() {
        var x = document.getElementById("myTopnav");
        if (x.className === "topnav") {
          x.className = "responsive";
        } else {
          x.className = "topnav";
        }
      },
      switchClass() {
        const event = document.querySelector(".show-more a")
      },
      backTop() {
        document.documentElement.scrollTop = document.body.scrollTop = 0;
  
      }
    },
  }
</script>

<style lang="scss" scoped>

.isLike {
  color: rgb(194, 26, 26);
  cursor: pointer;
}
.isScrap {
  color: rgb(194, 26, 26);
  cursor: pointer;
}
.noLike {
  color: rgb(58, 57, 56);
  cursor: pointer;
}
.noScrap {
  color: rgb(58, 57, 56);
  cursor: pointer;
}
.recoment {
  margin-left: 20px
}
a:hover {
  font-weight: 400;
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
.frame_body {
  display: grid;
  place-items: center;
}
.heading-frame {
	display: grid;
	box-shadow: 0 3px 6px rgba(0,0,0,0.4);
	grid-template-columns: max-content auto;
	grid-template-rows: auto max-content;
	background: #fcfbf8 center;
	background-size: 100px;
  background-repeat: no-repeat;
	color: rgb(7, 51, 1);
	width: 200px;
	height: 150px;
	padding: 1rem;
  margin-bottom: 10px;
	&:before,
	&:after {
		content: "";
		display: block;
		width: 100%;
		height: 100%;
		border: 1px solid rgb(7, 51, 1);
	}
	&:before {
		grid-row: 1 / 2;
		border-right: none;
		border-bottom: none;
	}	
	&:after {
		grid-row: 1 / span 2;
		border-left: none;
	}

	h2 {
		margin: 1rem 1rem -.5rem 0;
		font-family: 'Pragati Narrow', sans-serif;
		font-size: 2rem;
    font-weight: 900;
		max-width: 100%;
    background: #f6f5f0ec;
    border-radius: 10px;
    color:rgb(7, 51, 1);
    padding: 5px;
	}
}
  
.notYet {
	background-image: url("../../assets/images/default5.png");
  background-size: 90px;
  }
</style>