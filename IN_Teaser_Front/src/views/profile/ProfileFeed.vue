<template>
  <div>
    <div class="warpper">
        <input class="radio" id="one" name="group" type="radio" checked>
        <input class="radio" id="two" name="group" type="radio">
        <input class="radio" id="three" name="group" type="radio">
        <input class="radio" id="four" name="group" type="radio">
        <div class="tabs">
          <span v-if="isSameUser">
            <label class="tab" id="one-tab" for="one" @click="resetId(); uploads();">내가 쓴 글</label>
            <label class="tab" id="two-tab" for="two" @click="resetId(); likes();">좋아하는 글</label>
            <label class="tab" id="three-tab" for="three" @click="resetId(); scraps();">스크랩한 글</label>
            <label class="tab" id="four-tab" for="four" @click="resetId(); hides();">숨긴 글</label>
          </span>
          <span v-else-if="!isSameUser && user_nickname.length >= 1">
            <label class="tab" id="one-tab" for="one">{{user_nickname}}의 글</label>
          </span>
        </div>
        <div class="panels">
          <div class="panel" id="one-panel">
            <span v-if="uploadFeeds.length >= 1">
              <div class="postit" v-for="(uploadFeed, idx) in uploadFeeds" :key="idx">
                <span class="clip"></span>
                <div class="logo" >
                  <span v-if="uploadFeed.feed_hashtag" class="feeds_hashtag">{{ uploadFeed.feed_hashtag }}</span>
                </div>
                <br>
                  <viewer
                  :value="uploadFeed.feed_contents"
                  style="text-align: left; margin-left: 20px;"
                />
                
                <p class="feeds_bottom">
                  <router-link 
                    :to="{name:'Detail',params:{feedSeq:uploadFeed.feed_seq}}"
                    class="toDetail">
                    해당 글로 가기</router-link>
                  <span class="feeds_date">{{ uploadFeed.post_date }}</span>
                  
                </p> 
              </div>
            </span>
            <span v-else style="color: #666;">
              아직 작성한 글이 없습니다.
            </span>
          </div>
          <div class="panel" id="two-panel">
            <span class="new-article" style="width: 100%; margin:-20px 0 30px 0; ">
              <select name="likes" id="likes" @change="likesFeed()">
                <option value="likesArticle">좋아하는 글</option>
                <option value="likesProject">좋아하는 프로젝트</option>
              </select>

            </span>
            <span id="Articles">
              <div class="postit" v-for="(likeFeed, idx) in likeFeeds" :key="idx">
                <span class="clip"></span>
                <div class="logo"><span v-if="likeFeed.feed_hashtag" class="feeds_hashtag">{{ likeFeed.feed_hashtag }}</span> </div>
                <br>
                <viewer
                  :value="likeFeed.feed_contents"
                  style="text-align: left; margin-left: 20px;"
                />
                <p class="feeds_bottom">
                    <router-link 
                    :to="{name:'Detail',params:{feedSeq:likeFeed.feed_seq}}"
                    class="toDetail">
                    해당 글로 가기</router-link>
                  
                  <span class="feeds_nickname">{{ likeFeed.user_nickname }}</span>
                  <span class="feeds_date">{{ likeFeed.post_date }}</span>
                  
                </p>
              </div>
            </span>
            <span id="Projects">
              <div v-for="(project, idx) in projects" :key="idx" class="projects">
      
                  <div class="project postit">
                    <span class="clip"></span>
                    <span class="projectContent">
                      <p style="margin-left: 20px;"><b>제목:</b> {{ project.project_name }}</p>
                      <p style="margin-left: 20px;"><b>소개:</b> {{ project.project_intro }}</p>
                      <p style="margin-left: 20px;" v-if="project.project_dev_setting"><b>사용된 기술:</b> {{ project.project_dev_setting }}</p>
                    </span>
                      
                    <p class="feeds_bottom">
                      <router-link v-bind:to="{name:'ProjectDetail', params:{projectSeq:project.project_seq}}" class="toDetail">
                        해당 프로젝트로 가기
                      </router-link>
                      <span class="feeds_nickname">{{ project.user_nickname }}</span>
                      
                    </p>
                  </div>
              </div>
            </span>
            
          </div>
          <div class="panel" id="three-panel">
            <div class="postit" v-for="(scrapFeed, idx) in scrapFeeds" :key="idx">
              <span class="clip"></span>
              <div class="logo"><span v-if="scrapFeed.feed_hashtag" class="feeds_hashtag">{{ scrapFeed.feed_hashtag }}</span> </div>
              <br>
              <viewer
                :value="scrapFeed.feed_contents"
                style="text-align: left; margin-left: 20px;"
              />
              <p class="feeds_bottom">
                <router-link 
                  :to="{name:'Detail',params:{feedSeq:scrapFeed.feed_seq}}"
                  class="toDetail">
                  해당 글로 가기</router-link>
                <span v-if="scrapFeed.user_nickname" class="feeds_nickname">{{ scrapFeed.user_nickname }}</span>
                <span class="feeds_date">{{ scrapFeed.post_date }}</span>
                
              </p>
            </div>
          </div>
          <div class="panel" id="four-panel">
            <div class="postit" v-for="(hideFeed, idx) in hideFeeds" :key="idx">
              <span class="clip"></span>
              <div class="logo"><span v-if="hideFeed.feed_hashtag" class="feeds_hashtag">{{ hideFeed.feed_hashtag }}</span> </div>
              <br>
              <viewer
                :value="hideFeed.feed_contents"
                style="text-align: left; margin-left: 20px;"
              />
              <p class="feeds_bottom">
                <router-link 
                  :to="{name:'Detail',params:{feedSeq:hideFeed.feed_seq}}"
                  class="toDetail">
                  해당 글로 가기</router-link>
                <span class="feeds_nickname">{{ hideFeed.user_nickname }}</span>
                <span class="feeds_date">{{ hideFeed.post_date }}</span>
                
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  
</template>

<script>
  import 'tui-editor/dist/tui-editor-extColorSyntax.js'
  import 'tui-editor/dist/tui-editor-extScrollSync.js'
  import UserApi from '@/apis/UserApi.js'
  import FeedApi from '@/apis/FeedApi.js'
  import cookie from '@/cookie.js'
  require('tui-editor/dist/tui-editor.css');
  require('tui-editor/dist/tui-editor-contents.css');
  require('codemirror/lib/codemirror.css');
  var toastui = require('@toast-ui/vue-editor');
  var Viewer = toastui.Viewer;
  import baseURL from '@/base-url.js'
  import '@/assets/css/tab.css'
  import '@/assets/css/basic.css'
  export default {
    components: {
      'viewer': Viewer,
    },
    props: {
      name: String,
    },
    data:() => {
      return {
        isSameUser: false,
        since_id: 0,
        max_id: 0,
        final_id: 0,
        uploadFeeds: [],
        scrapFeeds: [],
        likeFeeds: [],
        hideFeeds: [],
        projects: [],
        user_nickname: '',
      }
    },
    methods: {
      scroll () {
        window.onscroll = () => {
          let bottomOfWindow = Math.max(window.pageYOffset, document.documentElement.scrollTop, document.body.scrollTop) + window.innerHeight === document.documentElement.offsetHeight
          
          if (bottomOfWindow && document.querySelector('#one').checked) {
            this.uploads()
          } else if (bottomOfWindow && document.querySelector('#two').checked && document.querySelector('#likes').options.selectedIndex == 0) {
            this.likes()
          } else if (bottomOfWindow && document.querySelector('#three').checked) {
            this.scraps()
          } else if (bottomOfWindow && document.querySelector('#four').checked) {
            this.hides()
          }
        }
      },
      resetId() {
        document.documentElement.scrollTop = document.body.scrollTop = 0;
        this.since_id = 0
        this.max_id = 0
        this.final_id = 0
        this.uploadFeeds= []
        this.scrapFeeds= []
        this.likeFeeds= []
        this.hideFeeds= []
      },
      uploads() {
        document.querySelector('#one').checked = true
        baseURL('/users/'+cookie.cookieSeq()+'/'+this.$route.params.userSeq+'/uploadfeed?since_id='+this.since_id+'&max_id='+this.max_id+'&final_id='+this.final_id)
        .then(res=> {
          if (res.data.object != null) {
            res.data.object.feed.forEach(element => {
              this.uploadFeeds.push(element)
            });
            this.since_id = res.data.object.since_id
            this.max_id = res.data.object.max_id
            this.final_id = res.data.object.final_id
          }
        })

      },
      likes() {
        document.querySelector('#two').checked = true
        document.querySelector('#likes').options.selectedIndex = 0
        this.projects = []
        baseURL('/users/'+cookie.cookieSeq()+'/likefeed?since_id='+this.since_id+'&max_id='+this.max_id+'&final_id='+this.final_id)
          .then(res=> {
            if (res.data.object != null) {
            res.data.object.feed.forEach(element => {
              this.likeFeeds.push(element)
            });
            this.since_id = res.data.object.since_id
            this.max_id = res.data.object.max_id
            this.final_id = res.data.object.final_id
          }
          })
      },
      scraps() {
        document.querySelector('#three').checked = true
        baseURL('/users/'+cookie.cookieSeq()+'/scrapfeed?since_id='+this.since_id+'&max_id='+this.max_id+'&final_id='+this.final_id)
          .then(res=> {
            if (res.data.object != null) {
            res.data.object.feed.forEach(element => {
              this.scrapFeeds.push(element)
            });
            this.since_id = res.data.object.since_id
            this.max_id = res.data.object.max_id
            this.final_id = res.data.object.final_id
          }
          })
      },
      hides() {
        document.querySelector('#four').checked = true
        baseURL('/users/'+cookie.cookieSeq()+'/hidefeed?since_id='+this.since_id+'&max_id='+this.max_id+'&final_id='+this.final_id)
          .then(res=> {
            if (res.data.object != null) {
            res.data.object.feed.forEach(element => {
              this.hideFeeds.push(element)
            });
            this.since_id = res.data.object.since_id
            this.max_id = res.data.object.max_id
            this.final_id = res.data.object.final_id
          }
          })
      },
      likesFeed() {
        document.documentElement.scrollTop = document.body.scrollTop = 0;
        if (document.querySelector('#likes').options.selectedIndex) {
          this.likeFeeds = []
          baseURL('/users/'+cookie.cookieSeq()+'/likeproject')
          .then(res=> {
            if (res.data.object) {
              this.projects = res.data.object
          }
          })
        }
        else{
          this.resetId();
          this.likes();
        }
      },
    },
    mounted() {
      this.uploads()
      this.scroll()
      if (this.$route.params.userSeq == cookie.cookieSeq()) {
        this.isSameUser = true
      }
      baseURL.get('/users/'+this.$route.params.userSeq+'/profile')
        .then(res => {
          this.user_nickname = res.data.object.user_nickname
      })
    },
    
  }
</script>

<style lang="scss" scoped>
.tabs {
  // border-bottom: 5px solid rgb(4, 49, 11);
  // border-radius: 5px;
}
.tab:checked {
  border-right: 3px solid rgb(4, 49, 11);
}
.panel {
  margin: 20px;
  padding: 0;
  border: 0;
}
.tui-editor-contents pre {
  background-color: transparent;
}
.panelFront {
  position: relative;
  z-index: 10;
  box-shadow: 2px 2px 5px 2px rgba(219, 219, 219, 0.774);
  background-color: #f6f5f0;
}
.panelBack {
  position:absolute;
  top: 0;
  box-shadow: 1px 1px 3px 1px rgba(204, 204, 204, 0.514);
  transform: rotate(3deg);
  width: 100%;
  height: 100%;
  z-index: 0;
}
.logo {
  font-size: 1.5em;
  background-color: #000;
}
.feeds_bottom {
  font-size: 0.8em; text-align: end;
}
.toDetail {

}
.toDetail:hover {
  color: #074709;
}
.feeds_bottom span {
  color: rgb(116, 116, 116);
  border-radius: 50px;
  padding: 8px;
  margin: 5px;
}
.feeds_nickname{

}
@mixin paper-clip($height: 70px, $color: #000, $angle: 0deg) {
  $width: round($height * 43 / 100);
  $hwidth: round($width / 2);
  border: 2px solid $color;
  border-bottom: 0;
  height: $height;
  width: $width;
  position: relative;
  border-radius: $hwidth $hwidth 0 0;
  display: inline-block;
  z-index: 2;
  @if $angle == '0deg' {
  } @else {
      transform: rotate($angle);
      outline: 1px solid transparent; 
      -webkit-backface-visibility: hidden;
      backface-visibility: hidden;
  }
  &::before, &::after {
    position: absolute;
    content: '';
    border: 2px solid $color;
  }
  &::before {
    border-bottom: 0;
    width: $width - 8;
    left: 2px;
    top: $width;
    height: round($height * 0.64);
    border-radius: $hwidth $hwidth 0 0;
  }
  &::after {
    border-top: 0;
    width: $width - 4;
    height: round($height * 43 / 100);
    top: $height;
    left: -2px;
    border-radius: 0 0 $hwidth $hwidth;  
  }
}

@mixin sticky($width) {
  box-sizing: border-box;
  width: $width;
  height: $width;
  background: #e7e4d2; /* Old browsers */
  background: -moz-linear-gradient(-45deg, #e7e4d2 81%, #e7e4d2 82%, #e7e4d2 82%, #faf7e9 100%); /* FF3.6+ */
  background: -webkit-gradient(linear, left top, right bottom, color-stop(81%,#e7e4d2), color-stop(82%,#e7e4d2), color-stop(82%,#e7e4d2), color-stop(100%,#faf7e9)); /* Chrome,Safari4+ */
  background: -webkit-linear-gradient(-45deg, #e7e4d2 81%,#e7e4d2 82%,#e7e4d2 82%,#faf7e9 100%); /* Chrome10+,Safari5.1+ */
  background: -o-linear-gradient(-45deg, #e7e4d2 81%,#e7e4d2 82%,#e7e4d2 82%,#faf7e9 100%); /* Opera 11.10+ */
  background: -ms-linear-gradient(-45deg, #e7e4d2 81%,#e7e4d2 82%,#e7e4d2 82%,#faf7e9 100%); /* IE10+ */
  background: linear-gradient(135deg, #e7e4d2 81%,#e7e4d2 82%,#e7e4d2 82%,#faf7e9 100%); /* W3C */
  filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e7e4d2', endColorstr='#faf7e9',GradientType=1 ); /* IE6-9 fallback on horizontal gradient */
  border:1px solid #E8E8E8;  
  //border-top: 60px solid #fdfd86;   
  padding-top:35px;   
  position: relative;
  margin: 25px 0px;
  display: inline-block;
  border-bottom-right-radius: 60px 5px;
  &::before {
    position: absolute;
    top: 0;
    left: 0px;
    height: 30px;
    width: 100%;
    border-radius: 5px 5px 0 0;
    background-color: #206322;
    background: -moz-linear-gradient(45deg,#557451 0%, #206322 5%,#0a470d 90%,#052904 100%); /* FF3.6+ */
    background: -webkit-gradient(linear, left bottom, right top, color-stop(0%,#63422ab7), color-stop(5%,#206322), color-stop(90%,#0a470d), color-stop(100%,#052904)); /* Chrome,Safari4+ */
    background: -webkit-linear-gradient(45deg,#557451 0%, #206322 5%,#0a470d 90%,#052904 100%); /* Chrome10+,Safari5.1+ */
    background: -o-linear-gradient(45deg,#557451 0%, #206322 5%,#0a470d 90%,#052904 100%); /* Opera 11.10+ */
    background: -ms-linear-gradient(45deg,#557451 0%, #206322 5%,#0a470d 90%,#052904 100%); /* IE10+ */
    background: linear-gradient(-45deg,#557451 0%, #206322 5%,#0a470d 90%, #052904 100%);
    box-shadow: 0px 2px 3px 2px rgb(104, 103, 96);
    content: '';
  }
  &::after {
    z-index: -1;
    position: absolute;
    content: "";
    bottom: 15px;
    right: 3px;
    left: auto;
    width: 50%;
    top: 80%;
    max-width: $width;
    background: #777;
    -webkit-box-shadow: 0 15px 10px #777;
    -moz-box-shadow: 0 15px 10px #777;
    box-shadow: 0 15px 10px #777;
    -webkit-transform: rotate(3deg);
    -moz-transform: rotate(3deg);
    -o-transform: rotate(3deg);
    -ms-transform: rotate(3deg);
    transform: rotate(3deg);
  }
  
}

.postit {
  @include sticky(100%); 
  padding: 20px;
}

.clip {
  position: absolute;
  top:-50px;
  left: 0px;
  @include paper-clip(60px, rgb(24, 22, 1), 45deg);
  transition: 3s;
}

@keyframes changedeg {
  0% {
    transform: rotate(45deg);
  }
  50% {
    transform: rotate(55deg);
  }
  100% {
    transform: rotate(45deg);
  }
}

.postit:hover .clip {
  animation-duration: 2s;
  animation-name: changedeg;
  animation-iteration-count: infinite;
  transform-origin: 50% 50%;
}

.projectContent {
  text-align: left;
  padding: 30px;
}


</style>