<template>
  <div class="app">
      <h1 class="app_title">글 수정</h1>
    <div class="app_head">
    </div>
    <div class="app_body">
      <div class="article_head">
        <div class="article_head_header">
          <label for="question" class="article_label">
            <div class="article_toggle">
              <input class="toggle-state" id="question" type="checkbox" name="check" v-model="feed_subject" />
              <div class="indicator"></div>
            </div>
            <div class="label-text">질문인 경우에만 클릭해주세요</div>
          </label>
        </div>
        <div class="article_head_public">
          <input type="radio" name="public" id="all" v-model="feed_scope" value="1"><label for="all">전체공개</label>
          <input type="radio" name="public" id="friend" v-model="feed_scope" value="2"><label for="friend">친구공개</label>
          <input type="radio" name="public" id="limited" v-model="feed_scope" value="3"><label for="limited">비공개</label>
        </div>
      </div>
      <tags-input element-id="eltags"
        :typeahead="true"
        :delete-on-backspace="true"
        v-model="tags"
        :add-tags-on-space="true"
        :before-adding-tag="hashTag"
        placeholder="#파이썬 #알려주세요"
        style="font-size: 1.2em;"
        ></tags-input>
      <editor 
        ref="articleEditor"
        v-model="feed_contents"
        height="500px"
        mode="wysiwyg"
        previewStyle="vertical"
        :options="editorOptions"/>

      <div class="article_footer">
        
      </div>
      <button @click="save">저장</button>
    </div>
    <!-- <viewer
    :value="viewerText"
    height="500px"
    /> -->
  </div>
  
</template>

<script>
  import '@/assets/css/basic.css'
  import FeedApi from '@/apis/FeedApi.js'
  import cookie from '@/cookie.js'
  import 'codemirror/lib/codemirror.css';
  import 'tui-editor/dist/tui-editor.css';
  import 'tui-editor/dist/tui-editor-contents.css';
  import 'highlight.js/styles/github.css';
  import 'tui-editor/dist/tui-editor-extColorSyntax.js'
  import 'tui-editor/dist/tui-editor-extScrollSync.js'
  import PortfolioApi from '@/apis/PortfolioApi.js'

  require('tui-editor/dist/tui-editor.css');
  require('tui-editor/dist/tui-editor-contents.css');
  require('codemirror/lib/codemirror.css');
  var toastui = require('@toast-ui/vue-editor');
  var Editor = toastui.Editor;
  // var Viewer = toastui.Viewer;

  
  export default {
    components: {
      'editor': Editor,
      // 'viewer': Viewer
    },
    mounted() {
      let data = {
        user_seq : cookie.cookieSeq(),
        feed_seq : this.$route.params.feed_seq
      }
      FeedApi.detailFeed(data, res=>{
        this.feed_contents = res.feed_contents
        this.feed_scope = res.feed_scope
        if (res.feed_subject === '질문') {
          this.feed_subject = true
        } else {
          this.feed_subject = false
        }
        var tags = res.feed_hashtag.split(',')
        tags.forEach(tag => {
          this.tags.push({key:'', value:tag})
        });  
        this.feed_contents = res.feed_contents
      }, err=>{})
    },
    methods: {
      checkSave(text) {
        if( text == '' || text == null || text == undefined ){
          return false;
        }
        var blank_pattern = /^\s+|\s+$/g;
        if( text.replace( blank_pattern, '' ) == "" ){
          return false;
        }
        return true
      },
      hashTag(tags) {
        let check = tags.value.charAt(0)
        if (check === ' ' || check !== '#' || (check === '#' && tags.value.length === 1)) return false
        else return true
      },
      save() {   
        var feed_subject = ''
        if (this.feed_subject) {
          feed_subject = '질문'
        } else {
          feed_subject = '자유'
        }
        var hashtag = []
        this.tags.forEach(function(tag) {
          hashtag.push(tag.value)
        })
        let data = {
          feed_seq: this.$route.params.feed_seq,
          feed_scope: this.feed_scope, 
          feed_contents: this.$refs.articleEditor.invoke('getValue'),
          feed_hashtag: hashtag.join(','), 
          user_seq: cookie.cookieSeq(),
          user_nickname: cookie.cookieNickname(),
          feed_subject: feed_subject
        }        
        var isSave = true
        for (const value in data) {
          if (value !== 'feed_hashtag') {
            if (isSave) {
              isSave = this.checkSave(String(data[value]))
            } 
          }
        }

        if (isSave) {
          FeedApi.editFeed(data, res=> {
          alert('글이 수정되었습니다.')
            this.$router.push({name:'Detail', params:{feedSeq:this.$route.params.feed_seq}})
          }, err => {
            alert('글이 수정되지 않았습니다.')
          })
        } else {
          alert('빈 값이 존재합니다.')
        }
        

      }
    },
    data() {
      return {
        tag: '',
        tags: [],
        feed_subject: '',
        editorOptions: {
            language: 'ko_KR',
            hooks: {
            'addImageBlobHook': function(blob, callback) {
              var form = new FormData()
              form.append('file', blob)
              PortfolioApi.imageUpload({user_seq:cookie.cookieSeq(), file:form}, res=> {
               // callback('http://localhost:8888/'+res.data.object)
               callback('https://i02b204.p.ssafy.io'+res.data.object)//20200227유경수정
              }, err => {})
            }
          },
          exts: [
            'colorSyntax'
          ],
        },
        user_email: '',
        feed_scope: '',
        feed_contents: '',
        feed_hashtag: '',
        // viewerText: ''
      }
    }
  }
</script>

<style scoped>
.app {
  min-width: 700px;
  position: relative;
}
.app_title {
  font-size: 2.4em;
  position: absolute;
  top: -90px;
  left: 260px;
  display: block;
  
  text-align: left;
  margin: 20px 0;
}
input[type='checkbox'] {
  -webkit-appearance: none;
}
input[type='radio'] {
  -webkit-appearance: radio;
}
.article_label {
  display: inline-flex;
  align-items: center;
  cursor: pointer;
  color: #3f3f3f;
}

.label-text {
  font-weight: 500;
  margin-left: 16px;
}

.article_toggle {
  margin: 20px 0;
  isolation: isolate;
  position: relative;
  height: 30px;
  width: 60px;
  border-radius: 15px;
  overflow: hidden;
  box-shadow:
    -4px -2px 4px 0px #dfded9,
    4px 2px 6px 0px #dfded9,
    2px 2px 2px 0px #dfded9,
    -2px -2px 2px 0px #dfded9;
}

.toggle-state {
  display: none;
}

.indicator {
  height: 100%;
  width: 200%;
  background: #f6f5f0;
  border-radius: 15px;
  transform: translate3d(-75%, 0, 0);
  transition: transform 0.4s cubic-bezier(0.85, 0.05, 0.18, 1.35);
  box-shadow:
    -4px -2px 4px 0px #dfded9,
    4px 2px 6px 0px #d1d9e6;
}

.toggle-state:checked ~ .indicator {
  background-color: rgb(5, 59, 11);
  transform: translate3d(25%, 0, 0);
  transition: background-color 0.4s
}
  /*Checkboxes styles*/
input[type="radio"] { display: none; }
input[type="radio"] + label {
  /* display: block; */
  position: relative;
  padding-left: 40px;
  margin-bottom: 20px;
  font: 14px/20px 'Open Sans', Arial, sans-serif;
  color: rgb(0, 0, 0);
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
}
input[type="radio"] + label:last-child { margin-bottom: 0; }
input[type="radio"] + label:before {
  content: '';
  /* display: block; */
  width: 20px;
  height: 20px;
  border: 3px solid #025c15;
  position: absolute;
  left: 15px;
  top: 0;
  opacity: .6;
  -webkit-transition: all .12s, border-color .08s;
  transition: all .12s, border-color .08s;
}
input[type="radio"]:checked + label:before {
  width: 10px;
  top: -5px;
  left: 20px;
  border-radius: 0;
  opacity: 1;
  border-top-color: transparent;
  border-left-color: transparent;
  -webkit-transform: rotate(45deg);
  transform: rotate(45deg);
}

.tags-input-remove::before, .tags-input-remove::after {
  background: red;
}

button {
  display: block;
  width: 100px;
  margin: 0 auto;
  padding: 10px;
  box-shadow: 5px 5px 10px 5px rgba(119, 119, 119, .1), -5px -5px 10px 5px rgba(255, 255, 255, 1);
  border-radius: 50px;
  color:#013f01;
  font-weight: 500;

}
button:hover {
  font-size: 1.02em;
  font-weight: 900;
  box-shadow: 3px 3px 5px 3px rgba(119, 119, 119, .1), -3px -3px 5px 3px rgba(255, 255, 255, 1);

}
button:active {
  box-shadow: -5px -5px 10px 5px rgba(119, 119, 119, .1), 5px 5px 10px 5px rgba(255, 255, 255, 1);
  color:#013f01;
  border-color: #f7f5ec;
}
  .editor {
    border: 1px solid black;
    height: 500px;
  }
  .article_footer {
    height: 30px;
  }
  .article_footer .tag {
    display: inline-block;
    width: 50%;
  }
  .article_footer .attached {
    display: inline-block;
    width: 50%;
  }
</style>