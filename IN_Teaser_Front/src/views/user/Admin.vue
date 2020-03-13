<template>
<div>
  <header>
      <h1 class="app_title">
          <span class="app_title-span"> IN</span>:TEASER
      </h1>
      <div class="header-login">
        <button @click="logout"><i class="fas fa-lg fa-sign-in-alt"></i></button>
      </div>
  </header>
  <div class="chart-div">
    <canvas id="myChart1" height="500px" class="charts"></canvas>
    <canvas id="myChart2" height="500px" class="charts" style="display: none;"></canvas>
    <button id="Chart1" @click="chart1Show">일별 게시글 작성수</button>
    <button id="Chart2" @click="chart2Show">유저 비율</button>
  </div>
  <div class="auth-warpper">
    <div class="admin-body">
      <div class="admin-tabs">
        <span class="tab-title">
          <input name="tabs" type="radio" id="tab-1" checked="checked" class="input">
          <label for="tab-1" class="label" @click="tabToggle">사용자</label>
          <input name="tabs" type="radio" id="tab-2" class="input">
          <label for="tab-2" class="label" @click="tabToggle2">게시글</label>
        </span>
      </div>
      <div class="admin-tab-body">
        <div class="panel" :style="{display: displayBlock}">
          <div>
            <select @change="change" v-model="filter" name="users" id="users" class="select-css">
              <option value="allUsers">전체({{ allUsers.length - 1 }})</option>
              <option value="normalUsers">일반({{ normalUsers.length - 1 }})</option>
              <option value="recruiterUsers">채용담당자({{ recruiterUsers.length - 1 }})</option>
            </select>
          </div>
          <!-- 신고받은 게시글 수, 총 신고 받은 수, 총 신고한 수, 계정상태,  -->
          <vue-table-dynamic @row-click="click" :params="params" ref="table"></vue-table-dynamic>
        </div>
        <div class="panel" :style="{display: displayNone}">
          <div>
            <select @change="change" v-model="filter" name="users" id="users" class="select-css">
              <option value="allFeeds">전체({{ allFeeds.length }})</option>
              <option value="reportFeeds">신고({{ reportFeeds.length }})</option>
            </select>
          </div>
          <span>
            <vue-table-dynamic v-if="filter === 'allFeeds'" @row-click="click" :params="params2" ref="table"></vue-table-dynamic>
            <vue-table-dynamic v-else-if="filter === 'reportFeeds'" @row-click="click" :params="params3" ref="table"></vue-table-dynamic>
          </span>
        </div>
      </div>
    </div>
  </div>
  <transition name="modal">
  <div v-if="isOpen">
      <div class="overlay" @click.self="isOpen = false;">
      <div class="modal">
        <button @click="hideFeed" class="hideArticle"> 이 게시글 숨기기 </button>
        <h4 style="font-weight: 900;">글 내용</h4>
        <viewer
          :value="modalData.feed_contents"
          style="margin-left: 30px;"
        />
        
        <hr>
        <button class="button-modal" @click="modalButton('comment')" style="font-weight: 900;">댓글 보기[{{ modalData.comment.length }}]</button>
        <button v-if="this.filter === 'reportFeeds'" class="button-modal" @click="modalButton('report')">신고[{{ reports.length }}]</button>
        <span>
          <ul style="margin-top: 10px; margin-left: 30px;" :class="{openComement:commentOpen, closeComement:!commentOpen}">
            <li style="margin-top: 7px;" v-for="comment in modalData.comment" :key="comment.comment_seq">
              
              <span v-if="comment.comment.length >= 1 && comment.order_num == 0" class="comment"><span class="nickname">{{ comment.user_nickname }}</span>{{ comment.comment }}</span>
              <span v-else-if="comment.comment.length >= 1 && comment.order_num != 0" class="comment" style="margin-left: 20px;"><span class="nickname">{{ comment.user_nickname }}</span>{{ comment.comment }}</span>
              <span v-else class="comment delComment"><span class="nickname">{{ comment.user_nickname }}</span>삭제된 댓글입니다</span>
            </li>
          </ul>
          <ul style="margin-top: 10px; margin-left: 30px;" :class="{openComement:reportOpen, closeComement:!reportOpen}">
            <li style="margin-top: 7px;" v-for="report in reports" :key="report.reporter_seq">
              <span class="nickname">{{ report.reporter_nickname }}</span>
              <span>{{ report.report_content }}</span>
            </li>
          </ul>
        </span>
        
      </div>
    </div>
  </div>
  </transition>
</div>
  
</template>

<script>
  import Chart from 'chart.js';
  import cookie from '@/cookie.js'
  import FeedApi from '@/apis/FeedApi.js'
  import AdminApi from '@/apis/AdminApi.js'
  import 'codemirror/lib/codemirror.css';
  import 'tui-editor/dist/tui-editor.css';
  import 'tui-editor/dist/tui-editor-contents.css';
  import 'highlight.js/styles/github.css';
  import 'tui-editor/dist/tui-editor-extColorSyntax.js'
  import 'tui-editor/dist/tui-editor-extScrollSync.js'
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
        report: [],
        isOpen: false,
        commentOpen: false,
        reportOpen: false,
        displayBlock: 'block',
        displayNone: 'none',
        allFeeds: {},
        reportFeeds: {},
        allUsers: [],
        normalUsers: {},
        recruiterUsers: {},
        datas: {},
        filter: 'allUsers',
        modalData: {},
        modalIdx: '',
        reports: '',
        params3: {
          data: [],
          header: 'row',
          border: false,
          stripe: true,
          pagination: true,
          pageSize: 10,
          enableSearch: true,
          sort: [0, 1, 2, 3, 4],
        },
        params2: {
          data: [],
          header: 'row',
          border: false,
          stripe: true,
          pagination: true,
          pageSize: 10,
          enableSearch: true,
          sort: [0, 1, 2, 3, 4, 5, 6],
          columnWidth: [{column: 0, width: 70}, {column: 1, width: 100},{column:3, width: 90}, {column:4, width: 80}]
        },
        params: {
          data: [],
          header: 'row',
          border: false,
          stripe: true,
          pagination: true,
          pageSize: 10,
          enableSearch: true,
          sort: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
          toPage: [{column:0, }],
          columnWidth: [{column: 0, width: 60}, 
                        {column: 1, width: 100},  
                        {column: 2, width: 75},  
                        {column: 3, width: 75},  
                        {column: 4, width: 75},  
                        {column: 5, width: 75}]
        }
      }
    },
    mounted() {
      AdminApi.isAdmin({user_seq:cookie.cookieSeq()}, res=>{
        if(res.data.status === false) {
          this.$router.push({name:'Main'})
        }
      }, err=> {})
      AdminApi.feedPerDay(res=> {
        var date = []
        var data = []
        for (const val in res.data.object) {
          date.push(res.data.object[val].date)
          data.push(res.data.object[val].posting_cnt)
        }
        this.createChart(data, date);
      }, err => {})
      if (cookie.cookieAuth() !== "0" ) {
        this.$router.push({
          name: 'NotFound'
        })
      } else {
        AdminApi.allFeed(res => {
          this.allFeeds = [['번호', '유저 번호', '유저 닉네임',  '좋아요 수', '신고 수', '작성일']]
          this.datas = res.data.object        
          for (const data in this.datas) {
            this.allFeeds.push([this.datas[data].feed_seq, this.datas[data].user_seq, this.datas[data].user_nickname, this.datas[data].feed_like_cnt, this.datas[data].feed_report_cnt, this.datas[data].format_date])
          }
          this.params2.data = this.allFeeds
        }, err=> {})

        AdminApi.reportFeed(res => {
          this.reportFeeds = [['번호', '유저 번호', '유저 닉네임', '신고 수', '게시글 상태']]
          this.datas = res.data.object
          for (const data in this.datas) {
            this.reportFeeds.push([this.datas[data].feed_seq, this.datas[data].user_seq, this.datas[data].user_nickname, this.datas[data].feed_report_cnt, this.datas[data].deleteYN])
            this.report.push(this.datas[data].reporter)
          }
          this.params3.data = this.reportFeeds
        }, err=> {})
        

        AdminApi.allUser(respon => {
          this.allUsers = [['번호', '닉네임', '작성 글', '좋아요', '팔로우', '신고 수', '신고 게시글', '상태']]
          this.datas = respon.data.object
          for (const data in this.datas) {
            this.allUsers.push([this.datas[data].user_seq, this.datas[data].user_nickname, this.datas[data].post_cnt, this.datas[data].like_cnt, this.datas[data].follower_cnt, this.datas[data].reported_cnt, this.datas[data].reported_post_cnt, this.datas[data].user_utilityYN])
          }
          this.params.data = this.allUsers
          AdminApi.normalUser(res => {
            this.normalUsers = [['번호', '닉네임', '작성 글', '좋아요', '팔로우', '신고 수', '신고 게시글', '상태']]
            this.datas = res.data.object
            for (const data in this.datas) {
              this.normalUsers.push([this.datas[data].user_seq, this.datas[data].user_nickname, this.datas[data].post_cnt, this.datas[data].like_cnt, this.datas[data].follower_cnt, this.datas[data].reported_cnt, this.datas[data].reported_post_cnt, this.datas[data].user_utilityYN])
            }
            AdminApi.recruiterUser(response => {
              this.recruiterUsers = [['번호', '닉네임', '작성 글', '좋아요', '팔로우', '신고 수', '신고 게시글', '상태']]
              this.datas = response.data.object
              for (const data in this.datas) {
                this.recruiterUsers.push([this.datas[data].user_seq, this.datas[data].user_nickname, this.datas[data].post_cnt, this.datas[data].like_cnt, this.datas[data].follower_cnt, this.datas[data].reported_cnt, this.datas[data].reported_post_cnt, this.datas[data].user_utilityYN])
              }
              this.createChart2()}, error=> {})
            }, err=> {}) 
          }, er=> {})       
      }
    },
    methods: {
      modalButton(show) {
        if (show === 'comment') {
          this.commentOpen = !this.commentOpen
          this.reportOpen = false
        } else {
          this.reportOpen = !this.reportOpen
          this.commentOpen = false
        }
      },
      hideFeed(feed_seq) {
        if (confirm('이 게시글을 숨기겠습니까?')) {
          AdminApi.hideFeed({feed_seq:feed_seq}, res => {
            if (this.filter === 'reportFeeds') this.params3.data.splice(this.modalIdx, 1)
            else if (this.filter === 'allFeeds') this.params2.data.splice(this.modalIdx, 1)
            this.isOpen = false
          },err => {})
        }
      },
      chart1Show() {
        document.querySelector("#myChart1").style.display = 'block'
        document.querySelector("#myChart2").style.display = 'none'
      },
      chart2Show() {
        document.querySelector("#myChart2").style.display = 'block'
        document.querySelector("#myChart1").style.display = 'none'
      },
      click(index, data) {
        if (this.displayBlock === 'block') {
          var utility = this.params.data[index][7]
          var warnText = utility
          if ( utility === 'I') {
            warnText = data[1] + '을(를) 활성화 시키겠습니까?'
          } else if ( utility === 'N' ) {
            warnText = data[1] + '을(를) 정지 시키겠습니까?'
          } else if (utility === 'Y') {
            warnText = data[1] + '은(는) 탈퇴한 회원입니다.'
          }
          
          if (utility === 'I' || utility === 'N') {
            if (confirm(warnText)) {
              AdminApi.managementUser({user_seq:this.params.data[index][0]}, res => {
              if ( utility === 'I') {
                this.params.data[index].splice(7, 1, 'N')
                }
              else if ( utility === 'N') {
                this.params.data[index].splice(7, 1, 'I')
                }
              }, err => { })
            }
          } else {
            alert(warnText)
          }
        } else {
          if (index !== 0) {
            FeedApi.detailFeed({user_seq:cookie.cookieSeq(), feed_seq:data[0]}, res=>{
              this.isOpen = true  
              if (this.filter === 'reportFeeds') this.reports = this.report[index - 1]
              this.modalData = res
              this.modalIdx = index
              this.commentOpen = false
              this.reportOpen = false
            }, err => {})
          }
        }
      },
      change() {
        if (this.filter === 'allUsers') {
          this.params.data = this.allUsers
        } else if (this.filter === 'normalUsers') {
          this.params.data = this.normalUsers
        } else if (this.filter === 'recruiterUsers') {
          this.params.data = this.recruiterUsers
        }
      },
      createChart(data, date) { 
      var ctx = document.getElementById('myChart1');
      var myLineChart = new Chart(ctx, {
        type: 'line',
        data: {
          labels: date,
          datasets: [{
            label: '일별 게시글 작성수',
            data: data,
            borderColor: "#07471d",
            backgroundColor: "rgba(5, 59, 11, 1)",
            fill: false,
            lineTension: 0
          }],
        },
        options: {
          responsive: false,
          title: {
              display: false,
              text: '일별 게시글 작성수'
          },
          tooltips: {
              mode: 'index',
              intersect: false,
          },
          hover: {
              mode: 'nearest',
              intersect: true
          },
          scales: {
            xAxes: [{
              display: true,
              scaleLabel: {
                  display: false,
                  labelString: '날짜'
              }
            }],
            yAxes: [{
              display: true,
              ticks: {
                  suggestedMin: 0,
              },
              scaleLabel: {
                  display: false,
                  labelString: '일별 게시글 작성수'
              }
            }]
          }
        }
      });
      },  
      createChart2() { 
      var ctx = document.getElementById('myChart2');
      var myLineChart = new Chart(ctx, {
        type: 'pie',
        data: {
          labels: ['일반유저', '취업담당자'],
          datasets: [{
            data: [
              this.normalUsers.length - 1,
              this.recruiterUsers.length - 1,
            ],
            backgroundColor: ["#E7EBE0FF", "#ABD6DFFF"],
            fill: false,
            lineTension: 0
          }],
        },
        options: {
          responsive: false,
          title: {
              display: true,
              text: '유저 비율'
          },
          tooltips: {
              mode: 'index',
              intersect: false,
          },
          hover: {
              mode: 'nearest',
              intersect: true
          }
        }
      });
      },  
      logout() {
        var date = new Date();
        this.$store.commit('isLogin')
        this.$store.commit('clearInfo')
        document.cookie = 'login_user' + "= " + "; expires=" + date.toUTCString() + "; path=/";
        document.cookie = 'jwt-auth-token' + "= " + "; expires=" + date.toUTCString() + "; path=/";
        this.$router.push('/')
      },
      tabToggle() {
        if (this.displayBlock === 'none') {
          this.displayBlock = 'block'
          this.displayNone = 'none'
          this.filter = 'allUsers'
          this.change()          
        } 
      },
      tabToggle2() {
        if (this.displayNone === 'none') {
          this.displayNone = 'block'
          this.displayBlock = 'none'
          this.filter = 'allFeeds'
          this.change()          
        } 
      },
    }
  }
</script>
<style lang="scss" scoped>
.nickname {
  font-weight: 600; font-size: 1em; margin-right: 10px;
}

.button-modal{
  margin-right: 10px;
}

.openComement {
  display: block;
}

.closeComement {
  display: none;
}

.admin-num {
  margin-right: 10px
}

header {
  margin-bottom: 20px;
}
.app_title {
  margin: 20px 5%;
  text-align: center;
  display: inline-block;
  
}
.header-login {
  float: right;
  margin-top: 25px;
  margin-right: 5%;
}
.chart-div {
  text-align: center;
  margin-bottom: 30px;
}
.chart-div button{
  padding: 5px 10px;
  margin: 10px;
  border: 1px solid #f1efe0;
  border-radius: 10px;
  cursor: pointer;
  font-weight: 400;
  box-shadow: 5px 5px 10px 5px rgba(11, 51, 1, 0.1), -5px -5px 10px 5px rgba(255, 255, 255, 1);
}
.chart-div button:hover {
  border: none;
  color: #f6f5f0;
  background-color: rgb(5, 59, 11);
  box-shadow: inset -4px -4px 6px 2px rgba(0, 0, 0, 0.3), 4px 4px 6px 2px #fff;
}
.charts {
  text-align: center;
  width: 700px;
  margin: 0px auto 20px;
}
.auth-warpper {
  width: 800px;
  margin: auto;
  display: flex;
  justify-content: center;
  justify-items: flex-start;
}
.users {
  display: inline-block;
  width: 50%;
  height: 100%;
}
.user, .article {
  border: 1px solid black;
}
.articles {
  display: inline-block;
  width: 50%;
  height: 100%;
}
  .admin-body {
    width: 90%;
    height: 85%;
    margin: 0 auto;
    position: relative;
  }
  .admin-tabs {
    display: flex;
    flex-wrap: wrap;
    max-width: 700px;
    box-shadow: 0 48px 80px -32px rgba(0,0,0,0.3);
    position: relative;
  }
  .tab-title {
    padding: 10px;
    width: 100%;
  }
  .input {
    position: absolute;
    opacity: 0;
  }
  .label {
  width: 100%;
  padding: 20px 30px;
  background: transparent;
  cursor: pointer;
  font-weight: bold;
  font-size: 18px;
  color: #7f7f7f;
  transition: background 0.1s, color 0.1s;
}
.label:hover {
  background: transparent;
}
.label:active {
  background: transparent;
}
.input:focus + .label {
  box-shadow: inset 0px 0px 0px 3px #07471d;
  z-index: 1;
}
.input:checked + .label {
  border-top: 3px solid #07471d;
  box-shadow: 2px 2px 2px 2px #6968631e;
  background: transparent;
  color: #000;
  z-index: 1;
}
@media (min-width: 600px) {
  .label {
    width: auto;
  }
}
.table-div {
  width: 100%;
  overflow-x:scroll;
}

.panel {
  width: 100%;
  display: none;
  padding: 20px 30px 30px;
  background: #f6f5f0;
  position: relative;
  border: none;
  z-index: 200;
  border-bottom: 7px solid #07471d;
  border-radius: 15px;
}
@media (min-width: 600px) {
  .panel {
    order: 99;
  }
}

.panel select {
  margin-bottom: 20px;
}

.input:checked + .label + .panel {
  display: block;
}
  
  .admin {
    display: inline-block;
    width: 90%;
    margin: 5px auto;
  }
  .admin-num {
    display: inline-block;
    margin-left: 5%;
  }
  .admin-content {
    display: inline-block;
    width: 70%;
    
  }
.select-css {
    display: block;
    font-size: 16px;
    font-family: sans-serif;
    font-weight: 700;
    color: #444;
    line-height: 1.3;
    padding: .6em 1.4em .5em .8em;
    max-width: 100%; 
    box-sizing: border-box;
    margin: 0;
    border: 1px solid #aaa;
    box-shadow: 0 1px 0 1px rgba(0,0,0,.04);
    border-radius: .5em;
    -moz-appearance: none;
    -webkit-appearance: none;
    appearance: none;
    background-color: #fff;
    background-image: url('data:image/svg+xml;charset=US-ASCII,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%22292.4%22%20height%3D%22292.4%22%3E%3Cpath%20fill%3D%22%23007CB2%22%20d%3D%22M287%2069.4a17.6%2017.6%200%200%200-13-5.4H18.4c-5%200-9.3%201.8-12.9%205.4A17.6%2017.6%200%200%200%200%2082.2c0%205%201.8%209.3%205.4%2012.9l128%20127.9c3.6%203.6%207.8%205.4%2012.8%205.4s9.2-1.8%2012.8-5.4L287%2095c3.5-3.5%205.4-7.8%205.4-12.8%200-5-1.9-9.2-5.5-12.8z%22%2F%3E%3C%2Fsvg%3E'),
      linear-gradient(to bottom, #ffffff 0%,#e5e5e5 100%);
    background-repeat: no-repeat, repeat;
    background-position: right .7em top 50%, 0 0;
    background-size: .65em auto, 100%;
}
.select-css::-ms-expand {
    display: none;
}
.select-css:hover {
    border-color: #888;

}
.select-css:focus {
    border-color: #aaa;
    box-shadow: 0 0 1px 3px rgba(255, 251, 249, 0.7);
    box-shadow: 0 0 0 3px -moz-mac-focusring;
    color: #222; 
    outline: none;
}
.select-css option {
    font-weight:normal;
    background-color: #eeeeee;
    outline: none;
}
.modal {
  padding: 30px;
}
hr {
  margin: 10px;
}
.hideArticle {
  font-weight: 700;
  float: right;
  &:hover {

    color: rgb(177, 27, 27);
  }
}
.delComment {
  color: rgb(158, 158, 158);
}

</style>


