<template>
    <div class="app">
      
    <div class="project-body">
      <span v-if="isOpen">
        <input class="inputTag2" v-model="projectName" type="text" >
        <textarea v-model="projectIntro" id="" cols="70" rows="10"></textarea>
      </span>
      <div class="options">
      <div class="options opt1">
        <input type="number" v-model="project_personnel" name="member" id="member" placeholder="프로젝트 인원">
      </div>
      <div class="options opt2">
        <input type="text" v-model="project_position" placeholder="본인의 역할을 써 주세요">
      </div>
      <div class="options opt3" style="  margin-bottom: 10px;">
        <input type="email" name="address" id="address" placeholder="프로젝트 주소">
      </div>
      
      <div class="project_dev_setting_div">
        <button @click="remove(tech_stack)" class="project_dev_setting" v-for="tech_stack in project_dev_setting" :key="tech_stack">{{tech_stack}}</button>
      </div>
      <div class="a-line">
        <div class="input-with-label tech">
          <span v-for="(tech_stack, idx) in tech_stack_list" :key="idx"> 
            <label :for="idx" :class="'tech'+idx">
              {{ tech_stack }}
              <input type="checkbox"  @click="stackChecked(idx)"  :id="idx" :value="tech_stack" v-model="project_dev_setting">
            </label>
          </span>
        </div>
        <div class="tags">
        <input 
          class="inputTag"
          type="text" 
          placeholder="상단에 없는 기술 스택은 직접 써 주세요"
          v-model="techTag"
          @keydown.enter="inputTag">
      </div>
      </div>
      <div class="schedule">
        <VueCtkDateTimePicker
        id="RangeDatePicker"
        v-model="projectRange"
        format="YYYY-MM-DD"
        formatted="ll"
        :only-date="true"
        :no-shortcuts="true"
        :no-keyboard="true"
        color="green"
        :no-header="true" 
        :range="true"
        label="작업 기간을 선택해주세요"
        class="schedule-picker"
        >
        </VueCtkDateTimePicker>
      </div>
      <div class="readme" style="margin-top: 10px;">
        <editor 
          ref="articleEditor"
          v-model="project_content"
          height="500px"
          mode="wysiwyg"
          previewStyle="vertical"
          :options="editorOptions"
          class="pjt-editor"
          />
      </div>
      <button @click="save" type="submit" class="nextbtn">저장</button>
    </div>
  </div>
    </div>
</template>

<script>
  import 'tui-editor/dist/tui-editor.css';
  import 'tui-editor/dist/tui-editor-contents.css';
  import 'highlight.js/styles/github.css';
  import 'tui-editor/dist/tui-editor-extColorSyntax.js'
  import 'tui-editor/dist/tui-editor-extScrollSync.js'
  import PortfolioApi from '@/apis/PortfolioApi.js'
  import cookie from '@/cookie.js'

  require('tui-editor/dist/tui-editor.css');
  require('tui-editor/dist/tui-editor-contents.css');
  require('codemirror/lib/codemirror.css');
  var toastui = require('@toast-ui/vue-editor');
  var Editor = toastui.Editor;
  
  export default {
    mounted() {
      if (this.$route.params.project_seq === undefined) {
        alert('잘못된 접근입니다.')
        this.$router.push({name:'Profile', params:{userSeq:cookie.cookieSeq()}})
      } 
      // api가 있다 가정 만약 값이 있다면 data값을 변경
      if (this.$route.params.flag === 'update')  {
        PortfolioApi.projectDetail({user_seq:cookie.cookieSeq(), project_seq:this.$route.params.project_seq}, res=> {
          this.project_position = res.project_position
          this.project_content = res.project_content
          this.project_personnel = res.project_personnel
          this.projectRange = {start:res.project_start, end:res.project_end}
          this.project_dev_setting = res.project_dev_setting.split(',')
          this.flag = "update"
          this.isOpen = true
          this.projectName = res.project_name
          this.projectIntro = res.project_intro
          this.project_dev_setting.forEach(stack => {
          var flag = false
          this.tech_stack_list.forEach(tech => {
            if (tech === stack) return flag = true
          })
          if (flag) {
            this.stackChecked(this.tech_stack_list.indexOf(stack))
          }  
        })
        })
      }
    },
    components: {
      'editor': Editor,
      // 'viewer': Viewer
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
      save() {
        var data = {}

        if (this.flag === 'update') {
          data = {
            project_name: this.projectName,
            project_intro: this.projectIntro,
            project_start:this.projectRange.start,
            project_end:this.projectRange.end,
            project_dev_setting:this.project_dev_setting.join(', '),
            project_personnel:this.project_personnel,
            project_position:this.project_position,
            project_content:this.project_content,
            project_seq: this.$route.params.project_seq,
            user_seq: cookie.cookieSeq()
          }
        } else {
          data = {
            project_start:this.projectRange.start,
            project_end:this.projectRange.end,
            project_dev_setting:this.project_dev_setting.join(', '),
            project_personnel:this.project_personnel,
            project_position:this.project_position,
            project_content:this.project_content,
            project_seq: this.$route.params.project_seq,
            user_seq: cookie.cookieSeq()
         }
        }
        
        var isSave = true
        for (const value in data) {
          if (isSave) {
            isSave = this.checkSave(String(data[value]))
          } 
        }
        if (isSave) {
          if (this.flag === 'save') {
            PortfolioApi.projectStep2(data, res=> {
              this.$router.push({name:'Profile', params:{userSeq:cookie.cookieSeq()}})
            }, err => {alert('에러가 발생했습니다.')})
          } else {
            alert('프로젝트가 수정되었습니다.')
            PortfolioApi.projectEdit(data, res=> {
              this.$router.push({name:'Profile', params:{userSeq:cookie.cookieSeq()}})
            }, err => {alert('에러가 발생했습니다.')})
          }
          
        } else {
          alert('빈 값이 존재합니다.')
        }
      },
      remove(stack) {
        var flag = false
        this.tech_stack_list.forEach(tech => {
          if (tech === stack) return flag = true
        });
        if (flag) {
          this.stackChecked(this.tech_stack_list.indexOf(stack))
        } 
        this.project_dev_setting = this.project_dev_setting.filter(val =>  val != stack)        
      },
      inputTag() {
        var inputString = this.techTag
        this.techTag = ''
        if (this.checkSave(inputString)) {
          var flag = true
          this.project_dev_setting.forEach(tech => {
            if (tech === inputString) return flag = false
          });
          if (flag) {
            this.project_dev_setting.push(inputString)
          } else {
            this.project_dev_setting = this.project_dev_setting.filter(val =>  val != inputString)    
          }

          flag = false
          this.tech_stack_list.forEach(tech => {
            if (tech === inputString) return flag = true
          });
          if (flag) {
            this.stackChecked(this.tech_stack_list.indexOf(inputString))
          } 
          
        }
      },
      stackChecked(idx) {
        const checked = document.querySelector('.tech' + idx).style
        if (checked.backgroundColor == "rgb(5, 70, 13)") {
          checked.backgroundColor = ""
          checked.color = ""
          checked.boxShadow = ""
          return true
        } else {
          checked.backgroundColor = "#05460d"
          checked.color = "#fff"
          checked.boxShadow = "box-shadow: -5px -5px 10px 5px rgba(119, 119, 119, .1), 5px 5px 10px 5px rgba(255, 255, 255, 1)"
          return false
        }
      },
    },
    data: () => {
      return {
        isOpen: false,
        projectName: '',
        projectIntro: '',
        flag: 'save',
        project_content: '',
        techTag: '',
        project_personnel: 1,
        project_position: '',
        user_seq: '',
        projectRange: {},
        editorOptions: {
            language: 'ko_KR',
            hooks: {
            'addImageBlobHook': function(blob, callback) {
              var form = new FormData()
              form.append('file', blob)
              PortfolioApi.imageUpload({user_seq:cookie.cookieSeq(), file:form}, res=> {
               // callback('http://localhost:8888/'+res.data.object)
                callback('https://i02b204.p.ssafy.io'+res.data.object) 
              }, err => {})
            }
          },
          exts: [
            'colorSyntax'
          ],
        },
        tech_stack_list: [
          'java', 'python', 'django', 'c','c++',
          'c#', 'javascript', 'vuejs', 'reactjs',
          'angularjs', 'springboot', 'sql', 'mysql',
          'sqlite', 'php', 'android', 'ios', 'jquery',
          'html', 'css', 'los', 'ruby', 'json', 'r',
          'ajax', 'xml', 'wift', 'linux', 'database'
        ],
        project_dev_setting: [],
      }
    }
  }
</script>

<style lang="scss" scoped>
.project_dev_setting {
  display: inline;
  margin: 5px;
  background-color: white;
  border-radius: 5px;
  padding: 5px 10px;
  // border: 1px solid rgba(0,0,0,.2);
  display: inline-block;
  border-radius: 10px;
  box-shadow: 3px 3px 3px 3px rgba(119, 119, 119, 0.1), -3px -3px 3px 3px white;
  }

.project_dev_setting:hover {
  box-shadow: 1px 1px 2px 1px rgba(119, 119, 119, 0.1), -1px -1px 2px 1px white;
}
.inputTag2 {
  width: 80%;
  background-color: transparent;
  border-radius: 5px;
  border: 1px solid rgba(0,0,0,.2);
  margin-top: 10px;
  padding: 15px;
  height: 40px;
}

.inputTag {
  width: 100%;
  background-color: transparent;
  border-radius: 5px;
  border: 1px solid rgba(0,0,0,.2);
  margin-top: 10px;
  padding: 15px;
  height: 40px;
}
.app {
  min-width: 700px;
  position: relative;
}

.project-body {
  min-height: 800px;
}
.intro {
  min-height: 400px;
}
.a-line {
  position: relative;
  margin-top: 10px;
  margin-bottom: 10px;
}
.input-with-label {
  display: inline-block;
  width: 80%;
}
.tech {
  width: 100%;
}
.tech-p {
  margin: 10px;
  color:rgb(85, 85, 85);
}
.tech input {
  display: none;
}

.tech label {
  position: relative;
  display: inline-block;
  margin: 5px;
  box-shadow: 3px 3px 3px 3px rgba(119, 119, 119, .1), -3px -3px 3px 3px rgba(255, 255, 255, 1);
  border-radius: 10px;
  padding: 5px 10px;
  color: rgb(85, 85, 85);
}
.tech label:hover {
  box-shadow: 1px 1px 2px 1px rgba(119, 119, 119, .1), -1px -1px 2px 1px rgba(255, 255, 255, 1);
  font-size: 1.01em;
}
.schedule-picker {
  background:transparent;
}
.tag-maker {
  background: transparent;
  border: none;
  margin-top: 10px;
}
.options {
  display: inline-block;
}
.options input {
  height: 40px;
  width: 100%;
  background-color: transparent;
  border-radius: 5px;
  border: 1px solid rgba(0,0,0,.2);
  margin-top: 10px;
  padding: 15px;
}
.opt1 {
  width: 30%;
}
.opt2 {
  width: 70%;
}
.opt3 {
  width: 100%;
}
.nextbtn {
  cursor: pointer;
  border: 1px solid transparent;
  display: block;
  padding: 10px;
  font-weight: 700;
  box-shadow: 5px 5px 10px 5px rgba(119, 119, 119, .1), -5px -5px 10px 5px rgba(255, 255, 255, 1);
  width: 50%;
  text-align: center;
  margin: 30px auto 10px;
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