<template>
  <div>
    <div>
      <div class="feed">
        <div class="feed-body">
          <div class="content noMax">
            <viewer
              :value="feedContents"
            />
            <p class="article-tag" v-if="feed_hashtag">{{ feed_hashtag }}</p>
          </div>
          <div class="feed-header">
            <img v-if="user_profile_route" :src="'https://i02b204.p.ssafy.io' + user_profile_route" alt="profile_img" width="80px" height="100%" style="max-height: 90px;">
            <img v-else src="@/assets/images/user.svg" alt="profile_img" width="80px">
            <br>
            <router-link :to="{name:'Profile', params:{userSeq:writerSeq}}" style="font-size: 2em; font-family: 'East Sea Dokdo';">{{ feedUserNickname }}</router-link>
            <br>
            <p> 
            <p> 
            <span :class="{isLike: login_user_like, noLike: !login_user_like}" class="like" @click="like">
              <i class="fas fa-heart"></i>
            </span>
            <br>{{ feedPostdate }}</p>
            <span :class="{isScrap: login_user_scrap, noScrap: !login_user_scrap}" class="feedscrap" @click="scrap">
              <i class="far fa-bookmark header-bottom"></i>
            </span>
            <span v-if="!sameUser" @click="DM"> 
              <i class="far fa-paper-plane header-bottom" alt="dm보내기"></i>
            </span>
            <br>
            <span v-if="sameUser">
              <button @click="edit" class="feed-button re">수정</button>
              <button @click="deleteFeed" class="feed-button">삭제</button>
            </span>
            
          </div>
        </div>
        <input type="text" v-model="comment" 
              @keypress.enter="createComment"
              style="margin-top: 20px;"
              placeholder="댓글을 입력해주세요"      
        >
        <span v-for="com in comments" :key="com.comment_seq" class="feed-comment">
          <p v-if="com.order_num === 0" class="comment">
            <span style="font-weight: 900; font-size: 1.1em; margin-right: 10px;">{{ com.user_nickname }}</span>
            <span v-if="com.comment === ''" style="color: #ccc;">삭제된 댓글입니다.</span>
            <span v-else class="comment-content">{{ com.comment }}</span>
            <span class="create-date"> {{ com.insert_date }} </span>
            <span>
              <button class="button re" @click="recomentInput(com)">답글</button>
              <button v-if="com.user_seq == userSeq" class="button" @click="deleteComment(com)">삭제</button>
            </span>
          </p>
          <p v-else class="recoment">
            <span><i class="fas fa-angle-double-right" style="color: rgb(15, 59, 11);"></i></span>
            <span style="font-weight: 900; font-size: 1.1em; margin: 10px;">{{ com.user_nickname }}</span>
            <span v-if="com.comment === ''" style="color: #ccc;">삭제된 댓글입니다.</span>
            <span v-else class="comment-content">{{ com.comment }}</span>
            <span class="create-date"> {{ com.insert_date }}</span>
            <button v-if="com.user_seq == userSeq" class="button" @click="deleteComment(com)">삭제</button>
          </p>
          <input :id="'recomment' + com.comment_seq" 
                  class='none' type="text" 
                  v-model="recoment" 
                  @keypress.enter="recomnetCreate(com)"
                  placeholder="댓글을 입력해주세요"
          >
        </span>
      </div>
    </div>
  </div>
  
</template>

<script>
  import '@/assets/css/main.css'
  import FeedApi from '@/apis/FeedApi.js'
  import cookie from '@/cookie.js'
  import 'codemirror/lib/codemirror.css';
  import 'tui-editor/dist/tui-editor.css';
  import 'tui-editor/dist/tui-editor-contents.css';
  import 'highlight.js/styles/github.css';
  import 'tui-editor/dist/tui-editor-extColorSyntax.js'
  import 'tui-editor/dist/tui-editor-extScrollSync.js'
  import baseURL from '@/base-url.js'
  import PortfolioApi from '@/apis/PortfolioApi.js'


  require('tui-editor/dist/tui-editor.css');
  require('tui-editor/dist/tui-editor-contents.css');
  require('codemirror/lib/codemirror.css');
  var toastui = require('@toast-ui/vue-editor');
  var Viewer = toastui.Viewer;
  
  export default {
    components: {
      'viewer': Viewer,
    },
    methods: {
      DM() {
        this.$emit('DM', {opponent_seq:this.writerSeq, opponent_nickname:this.feedUserNickname})
      },
      like() {
        var data = {
          user_seq: cookie.cookieSeq(), 
          feedlike_seq: this.feedlike_seq,
          feed_seq: this.feedSeq, 
          user_nickname: cookie.cookieNickname(),
        }
        if (this.login_user_like) {
          baseURL.delete('/feed/' + cookie.cookieSeq() + '/like-cancel/' + this.feedlike_seq, {data})
          .then(res => {
            document.querySelector('.like > svg').style.color = 'rgb(58, 57, 56)'
            this.login_user_like = false
          })
          .catch(err => {
          })
        } else {
          baseURL.post('/feed/' + cookie.cookieSeq() + '/like/' + this.feedSeq, data)
          .then(res => {
            document.querySelector('.like > svg').style.color = 'rgb(194, 26, 26)'
            this.login_user_like = true
            this.feedlike_seq = res.data.object.feedlike_seq
          })
        }
      },
      scrap() {
        var data = {
          user_nickname: cookie.cookieNickname(),
          feed_seq: this.feedSeq,
          user_seq: cookie.cookieSeq(),
          feedscrap_seq: this.feedscrap_seq
        }
        if (this.login_user_scrap) {
          baseURL.delete('/feed/' + cookie.cookieSeq() + '/scrap-cancel/' + this.feedscrap_seq, {data})
          .then(res => {
            document.querySelector('.feedscrap>svg').style.color = 'rgb(58, 57, 56)'
            this.login_user_scrap = false
          }) 
        } else {
          baseURL.post('/feed/' + cookie.cookieSeq() + '/scrap/' + this.feedSeq, data)
          .then(res => {
            document.querySelector('.feedscrap>svg').style.color = 'rgb(194, 26, 26)'
            this.login_user_scrap = true
            this.feedscrap_seq = res.data.object.feedscrap_seq
          })
        }
      },
      deleteFeed() {
        let data = {
          user_seq: cookie.cookieSeq(),
          feed_seq: this.feedSeq,
        }
        FeedApi.deleteFeed(data, res => {this.$router.push({name:'Main'})}, err=>{})
      },
      edit() {
        this.$router.push({name:'EditArticle', params:{
          feed_seq:this.feedSeq, 
          user_seq:cookie.cookieSeq(), 
        }})
      },
      recomnetCreate(idx) {
        let data = {
          user_seq:this.userSeq,
          user_nickname: this.userNickname,
          feed_seq: this.feedSeq,
          comment: this.recoment,
          group_id: idx.comment_seq
        }
        this.recoment = ''
        FeedApi.createRecomment(data, res=> {
          this.comments = res.comment
          this.recomentInput(idx)
          alert('답글이 생성되었습니다.')
        }, err=>{})

        
      },  
      recomentInput(idx) {
        if (this.preidx) {
          document.querySelector('#recomment'+this.preidx.comment_seq).className = 'none'
        }
        if (this.preidx.comment_seq === idx.comment_seq) {
          document.querySelector('#recomment'+idx.comment_seq).className = 'none'
          this.preidx = ''
        } else {
          this.preidx = idx
          document.querySelector('#recomment'+idx.comment_seq).className = 'block'
        }
      },
      deleteComment(idx) {
        let data = {
          user_seq:this.userSeq,
          comment_seq: idx.comment_seq,
          feed_seq: this.feedSeq
        }
        FeedApi.deleteComment(data, res=> {
          FeedApi.detailFeed(data, response=>{
            this.comments = response.comment
            alert('삭제되었습니다.')
          }, erorr=>{})
        }, err=>{})
      },
      checkComment(comment) {
        if( comment == '' || comment == null ){
          alert('값을 입력해주세요' );
          this.comment = ''
          return false;
        }

        var blank_pattern = /^\s+|\s+$/g;
        if( comment.replace( blank_pattern, '' ) == "" ){
          alert('공백만 입력되었습니다 ');
          this.comment = ''
          return false;
        }
      },
      createComment() {
        if (this.checkComment(this.comment)) return
        let data = {
          user_seq: this.userSeq,
          user_nickname: this.userNickname,
          feed_seq: this.feedSeq,
          comment: this.comment
        }
        FeedApi.createComment(data, res=> {
          alert('댓글이 생성되었습니다.')
          this.comment = ''
          this.comments = res.comment
        }, err=>{})
    
      }
    },
    data:() => {
      return {
        login_user_like: false,
        backup: '',
        recoment: '',
        preidx: '',
        userSeq: '',
        writerSeq: '',
        feedSeq: '',
        feed_scope: '',
        feed_hashtag: '',
        comment: '',
        comments: [],
        feedContents: '',
        feedPostdate: '',
        feedUserNickname: '',
        user_profile_route: '',
        login_user_scrap:  false,
        sameUser: false,
        feedscrap_seq: null,
        feedlike_seq: null,
      }
    },
    mounted() {
      this.userSeq = cookie.cookieSeq()
      this.userNickname = cookie.cookieNickname()
      this.feedSeq = this.$route.params.feedSeq
      let data = {
        user_seq : this.userSeq,
        feed_seq : this.feedSeq
      }

      FeedApi.detailFeed(data, res=>{
        if (res.user_seq == data.user_seq) this.sameUser = true
        this.writerSeq = res.user_seq
        this.login_user_scrap = res.login_user_scrap
        this.feedContents = res.feed_contents
        this.feedPostdate = res.post_date
        this.feedUserNickname = res.user_nickname
        this.feed_scope = res.feed_scope
        this.comments = res.comment
        this.feed_hashtag = res.feed_hashtag
        this.login_user_like = res.login_user_like
        this.feedlike_seq = res.feedlike_seq
        this.feedscrap_seq = res.feedscrap_seq
        this.user_profile_route = res.user_profile_route
      }, err=>{ 
        this.$router.push({name:'NotFound'})
      })
    }
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

input {
  outline: none;
  margin: 20px;
  height: 50px;
  width: 90%;
  border-radius: 50px;
  border: none;
  padding: 0 30px;
  box-shadow: inset 5px 5px 10px rgba(119, 119, 119, .1), inset -5px -5px 10px rgba(255, 255, 255, 1);
}
.noMax {
  max-height: none;
  padding: 0px 30px 30px;
}
.feed-header {
  text-align: center;
}
input:last-child {
  margin: 5px 20px 10px;
}
.feed {
  width: 750px;
  margin: 20px auto;
}
.comment {
  width: 90%;
  margin-bottom: 0;
}
.recoment {
  margin: -20px 0px 0px 40px;
}
.comment-content {
}
.create-date {
  font-size: 0.8em;
  margin: 10px;
}
.none {
  // display: none;
  opacity: 0;
  height: 0;
  transition: all 0.5s linear;
  margin: 0;
}

.block {
  // display: block;
  opacity: 1;
  height: 50px;
  transition: all 0.5s linear;
}

.feed-button, .button {
  text-align: center;
  display: inline-block;
  margin-left: 15px;
  font-weight: 500;
  border: 1px solid #ccc;
  border-radius: 50px;
  padding: 3px 6px;
  box-shadow: 1px 1px 2px 1px rgba(119, 119, 119, .1), -1px -1px 2px 1px rgba(255, 255, 255, 1);  
}
.feed-button {
  font-size: 1.05em;
  padding: 4px 8px;
}
.feed-button:hover, .button:hover {
  color: rgb(177, 27, 27);
  box-shadow: -1px -1px 2px 1px rgba(119, 119, 119, .1), 1px 1px 2px 1px rgba(255, 255, 255, 1);
  font-weight: 900;
}
.re:hover {
  color: rgb(5, 59, 11)
}
.feed {
  max-height: 100%;
}
.feed-body {
  min-height: 300px;
}
.content {
  min-height: 290px;
}
</style>