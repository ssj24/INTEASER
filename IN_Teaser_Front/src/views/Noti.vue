<template>
  <div class="noti-wrap">
    <div class="noti-body">
      <div class="noti-tabs">
        <span class="tab-title">
          <input name="tabs" type="radio" id="tab-1" checked="checked" class="input">
          <label for="tab-1" class="label" @click="tabToggle">요청</label>
          <input name="tabs" type="radio" id="tab-2" class="input"/>
          <label for="tab-2" class="label" @click="tabToggle2">알림</label>
        </span>
      </div>
      <div class="noti-tab-body">
        <div class="panel" :style="{display: displayBlock}">
          <table>
            <tr v-for="(request, idx) in requests" :key="idx">
              <td class="noti-num">{{ idx + 1 }}</td>
              <td class="noti-content"><p>{{request.opponent_nickname}}님에게 팔로우 신청이 왔습니다.</p></td>
              <td class="noti-YN">
                <div class="noti-Y">
                  <button @click="answer(idx, 'Y')">수락</button>
                </div>
                <div class="noti-N">
                  <button @click="answer(idx, 'N')">거절</button>
                </div></td>
            </tr>
          </table>
        </div>
        <div class="panel" :style="{display: displayNone}">
            <table>
              <tr v-for="(notice, idx) in notices" :key="idx">
                <td class="noti-num">{{ idx + 1 }}</td>
                <td class="noti-content"><p v-html="notice.notice_content"></p></td>
              </tr>
            </table>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
  import NotiApi from '@/apis/NotiApi.js'
  import cookie from '@/cookie.js'
  import router from '@/main'

  export default {
    mounted() {
      this.req()
    },
    methods: {
      noti() {
        NotiApi.checkRequest({user_seq:cookie.cookieSeq()}, 
          res => {
            NotiApi.notice({user_seq:cookie.cookieSeq()}, response=>{
              this.notices = response.data.object
          }, error => {})
        },  err=> {})
      },
      req() {
        NotiApi.checkNotice({user_seq:cookie.cookieSeq()}, res => {
          NotiApi.requests({user_seq:cookie.cookieSeq()}, respon=>{
            this.requests = respon.data.object
          }, error => { })
        }, err=> {})
      },
      tabToggle() {
        if (this.displayBlock == 'none') {
          this.displayBlock = 'block'
          this.displayNone = 'none'
          this.req()
        } 
      },
      tabToggle2() {
        if (this.displayNone == 'none') {
          this.displayNone = 'block'
          this.displayBlock = 'none'
          this.noti()
        } 
      },
      answer(idx, accept) {
        // opponent_email, acceptYN
        var data = {
          user_seq: cookie.cookieSeq(),
          user_nickname: cookie.cookieNickname(),
          opponent_nickname: this.requests[idx].opponent_nickname,
          acceptYN: accept
        }
          NotiApi.answer(data, res=>{
          this.requests.splice(idx, 1)
        }, err =>{

        })
      }
    },
    data:() => {
      return {
        notices: [],
        requests: [],
        displayBlock: 'block',
        displayNone: 'none',
      }
    }
  }
</script>

<style scoped>
  .noti-wrap {
    display: block;
    width: 800px;
    height: 80vh;
    margin: 0 auto;
  }
  
  .noti-body {
    width: 90%;
    height: 85%;
    margin: 50px auto 30px;
    position: relative;
  }
  .noti-tabs {
    display: flex;
    flex-wrap: wrap;
    max-width: 700px;
    box-shadow: 0 48px 80px -32px rgba(0,0,0,0.3);
    position: relative;
  }
  .noti-title {
    padding: 10px;
    width: 100px;
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
}
@media (min-width: 600px) {
  .label {
    width: auto;
  }
}
.panel {
  width: 100%;
  display: none;
  padding: 20px 30px 30px;
  background: #f6f5f0;
  position: absolute;
  top: 40px;
  box-shadow: 10px 10px 10px 5px #6968631e;
  border: none;
  z-index: 10;
}
@media (min-width: 600px) {
  .panel {
    order: 99;
  }
}
.input:checked + .label + .panel {
  display: block;
}
  .noti-tab {
    height: 90%;
    width: 100%;
  }
  .noti {
    display: inline-block;
    width: 90%;
    margin: 5px auto;
  }
  .panel table {
    width: 100%;
  }
  
  .noti-num {
    width: 10%;
    text-align: center;
    font-weight: 900;
    vertical-align: top;
    line-height: 40px;
    
  }
  .noti-content {
    vertical-align: middle;
    height: 50px;
  }
  .noti-YN {
    display: inline-block;
    width: 100%;
    margin: auto;
  }
  .noti-YN button {
    display: block;
    margin: 0 auto;
    border: 3px double #f6f5f0;
    border-radius: 50px;
    box-shadow: 2px 2px 2px rgba(204, 204, 204, 0.589), -2px -2px 2px rgba(255, 255, 255, 0.712);
    padding: 5px 10px;
  }
  .noti-YN button:hover {
    box-shadow: -2px -2px 2px rgba(204, 204, 204, 0.589), 2px 2px 2px rgba(255, 255, 255, 0.712);
    font-weight: 700;
    font-size: 1.03em;
  }
  .noti-Y {
    display: inline-block;
    width: 50%;
  }
  .noti-Y button:hover {
    color: rgb(5, 59, 11);
  }
  .noti-N {
    display: inline-block;
    width: 50%;
  }
  .noti-N button:hover {
    color: rgb(177, 27, 27);
  }
  </style>