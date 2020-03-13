
<template>
  <div class="warpper">
    <div class="search-list">
      <table class="smart-search">
        <tr>
          <th style="text-align: center; margin: 0; padding: 0;">제목</th>
          <td><input type="text" v-model="project_name"></td>
        </tr>
        <tr>
          <th style="text-align: center; padding: 0;">작성자</th>
          <td><input type="text" v-model="user_nickname"></td>
        </tr>
        <tr>
          <th style="text-align: center; padding: 0;">기술 조합</th>
          <td>
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
          </td>
        </tr>
        <tr>
          <th style="text-align: center; padding: 0;">기간</th>
          <td>
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
          </td>
        </tr>
      </table>
      <button @click="smartSearch" class="search-submit">검색</button>
    </div>
      <button class="backTop" @click="backTop"><i class="fas fa-arrow-up arrowUp"></i></button>

    <span class="feeds" v-for="feed in feeds" :key="feed.project_seq">
      <div v-if="feed" class="feed">
        <div class="feed-body">
          <div class="content hideContent">
            <router-link :to="{name:'ProjectDetail', params:{projectSeq:feed.project_seq}}">
              <p><b>{{feed.project_name}}</b></p>
              <p class="project-range">
                {{ feed.project_start }} ~ {{ feed.project_end }}
              </p>
              <p class="project-tag" v-if="feed.project_dev_setting">{{ feed.project_dev_setting }}</p>

            </router-link>
          </div>
          <!-- <div class="show-more">
            <router-link :to="{name:'Detail', params:{feedSeq:feed.feed_seq}}">
              더 보기
            </router-link>
          </div> -->
          <div class="feed-header">
            <router-link :to="{name:'Profile', params:{userSeq:feed.user_seq}}">
              <img v-if="feed.user_profile_route" :src="'https://i02b204.p.ssafy.io' + feed.user_profile_route" alt="profile_img" width="50px" height="100%" style="max-height: 70px;">
              <img v-else src="@/assets/images/user.svg" alt="profile_img" width="40px">
            </router-link>
            <br>
            <router-link :to="{name:'Profile', params:{userSeq:feed.user_seq}}" style="font-size: 2em; font-family: 'East Sea Dokdo';">{{ feed.user_nickname }}</router-link>
            <p> 
              <span :class="'feed'+feed.project_seq" @click="like(feed)">
                <i :class="{isLike: feed.login_user_like, noLike: !feed.login_user_like}" class="fas fa-heart"></i>
              </span>
            </p>
          </div>
        </div>
      </div>
    </span>
</div>
</template>
<script>
  import PortfolioApi from '@/apis/PortfolioApi.js'
  import baseURL from '@/base-url.js'
  import cookie from '@/cookie.js'
  export default {
    data:() => {
      return {
        feeds: [],
        tags: [],
        techTag: '',
        tech_stack_list: [
          'java', 'python', 'django', 'c','c++',
          'c#', 'javascript', 'vuejs', 'reactjs',
          'angularjs', 'springboot', 'sql', 'mysql',
          'sqlite', 'php', 'android', 'ios', 'jquery',
          'html', 'css', 'los', 'ruby', 'json', 'r',
          'ajax', 'xml', 'wift', 'linux', 'database'
        ],
        project_dev_setting: [],
        project_name: '',
        projectRange: {},
        user_nickname: '',
        currentPage: ''
      }
    },
    mounted() {
      this.currentPage = this.$route.query.page
      this.smartSearch()
      
    },
    methods: {
      like(feed) {
        var data = {
          user_seq: cookie.cookieSeq(),
          projectlike_seq: feed.project_like_seq,
          project_seq: feed.project_seq,
          user_nickname: cookie.cookieNickname()
        }
        if (feed.login_user_like) {
          PortfolioApi.projectLikeCancel(data, res=>{
            document.querySelector('.feed'+feed.project_seq+'>svg').style.color = 'rgb(58, 57, 56)'
            feed.login_user_like = false
          },err=>{})
          
        } else {
          PortfolioApi.projectLike(data, res=>{
            document.querySelector('.feed'+feed.project_seq+'>svg').style.color = 'rgb(194, 26, 26)'
            feed.login_user_like = true
            feed.project_like_seq = res.data.object.project_like_seq
          },err=>{})
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
      checkedBlank(techTag) {
        if( techTag == '' || techTag == null ){
          alert('값을 입력해주세요' );
          return false;
        }

        var blank_pattern = /^\s+|\s+$/g;
        if( techTag.replace( blank_pattern, '' ) == "" ){
          alert('공백만 입력되었습니다 ');
          return false;
        }

        return true
      },
      inputTag() {
        var inputString = this.techTag
        this.techTag = ''
        if (this.checkedBlank(inputString)) {
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
        } else {
          checked.backgroundColor = "#05460d"
          checked.color = "#fff"
          checked.boxShadow = "box-shadow: -5px -5px 10px 5px rgba(119, 119, 119, .1), 5px 5px 10px 5px rgba(255, 255, 255, 1)"
        }
      },  
      smartSearch() {
        var data = {
          project_dev_setting:this.project_dev_setting.join(','),
          project_name: this.project_name,
          user_nickname: this.user_nickname,
          project_start: this.projectRange.start,
          project_end: this.projectRange.end
        }
        baseURL.post('/project/' + cookie.cookieSeq() + '/search', data)
        .then(res=>{
          this.feeds = res.data.object
        })
      },
      backTop() {
        document.documentElement.scrollTop = document.body.scrollTop = 0;
  
      }
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
  .feeds {
    width: 650px;
  }
  .feed {
    height: 200px;
    min-height: 200px;
    max-height: 200px;
  }
  .feed-body {
    border-bottom: none;
    height: 100%;
  }
  .feed-header {
    min-height: 200px;
    max-height: 200px;
  }
  .content {
    margin: 0;
    min-height: 60%;

  }
  .warpper {
    margin-top: 20px;
    width: 800px;
  }
  input {
    border: 1px solid rgba(0,0,0,.2);
  }
  input::placeholder {
    color:rgb(59, 59, 59);
    font-weight: 400;
  }
  .search-list {
    margin-top: 20px;
    padding: 15px;
    min-width: 700px;
    min-height: 200px;
  }
  .smart-search tr th {
    width: 120px;
    padding-left: 10px;
    background-color: rgb(5, 59, 11);
    color: #f6f5f0;
    font-weight: 400;
    font-size: 1.05em;
    border-radius: 5px;
    border: 1px double #fff;
  }
  .smart-search tr td input {
    width: 649px;
    border-radius: 5px;
  }
  .projects-feed {
    width: 100%;
    margin-top: 20px;
  }

.project_dev_setting {
  margin: 20px 10px 0px;
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

.inputTag {
  background-color: transparent;
  width: 649px;
  border-radius: 5px;
  border: 1px solid rgba(0,0,0,.2);
}
.inputTag::placeholder {
  color:rgb(59, 59, 59);
  font-weight: 400;
}
.a-line {
  position: relative;
  margin-top: 30px;
  margin-bottom: 20px;
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
  height: 35px;
  line-height: 30px;
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
.options input {
  outline: none;
  height: 50px;
  width: 100%;
  border-radius: 50px;
  border: none;
  padding: 0 30px;
  box-shadow: inset 5px 5px 10px rgba(119, 119, 119, .1), inset -5px -5px 10px rgba(255, 255, 255, 1);
  margin-bottom: 5px;
}
.options input:hover {
  box-shadow: -2px -2px 2px rgba(119, 119, 119, .1), 2px 2px 2px rgba(255, 255, 255, 1);
}
.options input:focus {
  box-shadow: -1px -1px 1px rgba(119, 119, 119, .1), 1px 1px 1px rgba(255, 255, 255, 1);
}
.search-submit {
  padding: 10px 10px;
  margin: 20px;
  border: 1px solid rgb(5, 59, 11);
  border-radius: 20px;
  width: 60%;
}
.search-submit:hover {
  background-color: rgb(5, 59, 11);
  color: #f6f5f0;
  font-weight: 900;
  box-shadow: inset -4px -4px 8px 4px rgba(119, 119, 119, .2), 2px 2px 4px 2px rgba(255, 255, 255, 1);
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