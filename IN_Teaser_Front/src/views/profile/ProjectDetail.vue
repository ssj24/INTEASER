<template>
  <div class="project-div">
    <div class="app project-head">
      <h1 class="logo">{{ project.project_name }}</h1> 
      <div class="likeProject">
        <span class="like" :class="{isLike: project.login_user_like, noLike: !project.login_user_like}" @click="like">
          <i class="fas fa-heart"></i>
        </span>
        <span class="likeProjectSpan">{{ project.project_like_cnt}} </span>
      </div>
      <p class="project-range">{{ project.project_start }} ~ {{ project.project_end }} </p>
      <p class="project-teamNum">팀 인원: {{ project.project_personnel }} </p>
      <p class="project-position">{{ project.user_nickname }}님이 맡은 역할: {{ project.project_position }} </p>
      <p class="project-link">{{ project.project_link }} </p>
      <p class="project-stack">{{ project.project_dev_setting }} </p>
      <div class="introduction">
        {{ project.project_intro }}
      </div>
      
    </div>
    <div class="project-body">
      <viewer
        :value="project.project_content"
        class="project-content"
      />
      
    </div>
    
  </div>
</template>

<script>
  import 'codemirror/lib/codemirror.css';
  import 'tui-editor/dist/tui-editor.css';
  import 'tui-editor/dist/tui-editor-contents.css';
  import 'highlight.js/styles/github.css';
  import 'tui-editor/dist/tui-editor-extColorSyntax.js'
  import 'tui-editor/dist/tui-editor-extScrollSync.js'
  import PortfolioApi from '@/apis/PortfolioApi.js'
  import cookie from '@/cookie.js'
  import baseURL from '@/base-url.js'


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
        project: {},
        userSeq: 0,
        userNickname: '',
        projectSeq: 0,
        sameUser: false,
      }
    },
    methods: {
      like() {
        var data = {
          user_seq: cookie.cookieSeq(),
          projectlike_seq: this.project.project_like_seq,
          project_seq: this.project.project_seq,
          user_nickname: cookie.cookieNickname()
        }
        if (this.project.login_user_like) {
          PortfolioApi.projectLikeCancel(data, res=>{
            document.querySelector('.like').style.color = 'rgb(58, 57, 56)'
            this.project.login_user_like = false
            this.project.project_like_cnt -= 1
          },err=>{})
          
        } else {
          PortfolioApi.projectLike(data, res=>{
            document.querySelector('.like').style.color = 'rgb(194, 26, 26)'
            this.project.login_user_like = true
            this.project.project_like_seq = res.data.object.project_like_seq
            this.project.project_like_cnt += 1
          },err=>{})
          
        }
      },
    },
    mounted() {
      this.userSeq = cookie.cookieSeq()
      this.userNickname = cookie.cookieNickname()
      this.projectSeq = this.$route.params.projectSeq
      baseURL('/project/' + this.userSeq + '/' + this.projectSeq)
        .then(res => {
        if (res.user_seq == res.data.object.user_seq) this.sameUser = true
          this.project = res.data.object
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
.noLike {
  color: rgb(58, 57, 56);
  cursor: pointer;
}
.project-div {
  width: 900px;
  margin: 0px auto;
}
.project-head {
  min-width: 700px;
  display: block;
  min-height: 200px;
  padding: 30px;
}
.logo {
  font-size: 2.4em;
  font-weight: 900;
  text-align: center;
  margin-top: 0;
}
.project-range {
  font-size: 0.8em;
}
.project-teamNum {
  font-size: 0.8em;
}
.project-position {
}
.project-link {

}
.project-stack {
  font-weight: bold;
}
.introduction {
  border-top: 3px solid #e0dfdb;
  padding-top: 10px;
}
.project-body {
  width: 100%;
  padding: 10px;
  font-size: 2em;
}
.likeProject {
  float: right;
  position: relative;
  top: -30px;
}
</style>