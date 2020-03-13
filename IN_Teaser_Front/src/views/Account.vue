<template>
  <div class="user join account-wrap">
    <div class="wrapCenter">
      <div class="">
        <figure>
          <div class="toggle slide">
            <input id="c" type="checkbox" />
            <label for="c" @click="slidingTab">
              <div class="card slide"></div>    
            </label>
          </div>
        </figure>
        <div :style="displayBlock">
          <div class="a-line">
            <div class="input-with-label">
              <input disabled type="text" id="user_nickname" v-model="user_nickname">
            </div>
          </div>

          <div class="a-line">
            <div class="change submit" @click="password">
              <p>비밀번호 변경을 원하시면 클릭해주세요</p>
            </div>
          </div>
          <div class="a-line">
            <div class="input-with-label moNone">
              <input disabled type="text" id="user_name" v-model="user_name">
            </div>
            <div class="vertical">
              <span>
                <input v-model="user_name_onoff" type="checkbox" id="cb3" class="tgl tgl-skewed">
                <label for="cb3" class="tgl-btn" data-tg-off="HIDE" data-tg-on="SHOW"></label>
              </span>
            </div>
          </div>
          <div class="a-line">
            <div class="input-with-label">
              <input disabled type="text" id="email" :value="user_email">
            </div>
            <div class="vertical">
              <span>
                <input v-model="user_email_onoff" type="checkbox" id="cb3-4" class="tgl tgl-skewed">
                <label for="cb3-4" class="tgl-btn" data-tg-off="HIDE" data-tg-on="SHOW"></label>
              </span>
            </div>
          </div>
          <!-- <img :src="user_profile" class="uploading-image" alt="preview"> -->
          <input type="file" accept="image/*" @change="uploadImage($event)" id="file-input" multipart-form-data>
          <span class="trash" @click="deleteImage"> 
            <i class="fas fa-trash-alt trash"></i>
          </span>
          <div class="a-line">
            <div class="input-with-label">
              <input type="text" id="introduction" v-model="user_intro">
            </div>
            <div class="vertical">
              <span>
                <input v-model="user_intro_onoff" type="checkbox" id="cb3-5" class="tgl tgl-skewed">
                <label for="cb3-5" class="tgl-btn" data-tg-off="HIDE" data-tg-on="SHOW"></label>
              </span>
            </div>
            <div class="error-text">
              <!-- {{ error.introduction }} -->
            </div>
          </div>
          <div class="a-line">
            <div class="box">
              <select name="interests" id="interests" v-model="interests">
                <option v-for="(interest, idx) in user_interests" :key="idx">{{interest}}</option>
              </select>
              
            </div>
          </div>
          <button @click="drop" class="drop">
            <i class="fas fa-ban" style="color: rgb(177, 27, 27); margin-right: 3px;}"></i>
            탈퇴
          </button>
          <button @click="update" class="submit">
            <p>저장</p>
          </button>      
        </div>

        <div :style="displayNone">
          <div class="a-line portfolio-line">
            <div class="input-with-label portfolio">
              <input type="text" disabled>
              <label for="">포트폴리오 공개</label>
            </div>
            <div class="vertical">
              <span>
                <input v-model="portfolio_onoff" type="checkbox" id="p1" class="tgl tgl-skewed">
                <label for="p1" class="tgl-btn" data-tg-off="HIDE" data-tg-on="SHOW"></label>
              </span>
            </div>
            
          </div>
          <span id="links">  
            <div class="a-line links">
              
              <div class="input-with-label link">
                <span v-for="(link, idx) in links" :key="idx" class="link-input">
                  <input @keypress.enter="edit(links[idx])" type="text" v-model="links[idx].linkname">
                  <input @keypress.enter="edit(links[idx])" type="text" v-model="links[idx].link"> 
                  <button @click="edit(links[idx])"><i class="fas fa-pencil-alt"></i></button>
                  <button @click="linkDelete(links[idx], idx)"><i class="fas fa-trash-alt trash"></i></button> 
                </span>
                <span class="link-input">
                  <input type="text" @keypress.enter="addInput" v-model="linkname" placeholder="연결할 링크 이름을 입력해주세요">
                  <input type="text" @keypress.enter="addInput" v-model="link" placeholder="연결할 링크 주소를 입력해주세요">
                  <button @click="addInput"><i class="fas fa-plus"></i></button> 
                </span>
              </div>
            </div>
          </span>

          <button @click="update" class="submit">
            <p>저장</p>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import PortfolioApi from '@/apis/PortfolioApi.js'
  import cookie from '@/cookie.js'
  import baseURL from '@/base-url.js'

  export default {
    data:() => {
      return {
        aboutme_seq: '',
        user_email: '',
        user_email_onoff: '',
        user_nickname: '',
        user_name: '',
        user_name_onoff: '',
        user_intro: '',
        user_intro_onoff: '',
        user_auth: 0,
        user_profile: '',
        linkname: '', 
        link: '',
        links: [],
        interests: '',
        file: '',
        user_interests: [
          '관심분야',
          '웹/모바일',
          '빅데이터',
          '인공지능',
          '블록체인',
          'IoT',
        ],
        portfolio_onoff: '',
        displayBlock: 'display: block;',
        displayNone: 'display: none;',
        previewImage: null,
      }
    },
    methods: {
      checkSave(link) {
        if( link.link == '' || link.link == null || link.linkname == '' || link.linkname == null ){
          return false;
        }
        var blank_pattern = /^\s+|\s+$/g;
        if( link.link.replace( blank_pattern, '' ) == "" || link.linkname.replace( blank_pattern, '' ) == "" ){
          return false;
        }
        return true
      },
      linkDelete(link, idx) {
        var data = {
          userlink_seq: link.userlink_seq,
          user_seq: cookie.cookieSeq(),
        }
        PortfolioApi.deleteLink(data, res=> {
          alert('삭제되었습니다.')
          this.links.splice(idx, 1)
        }, err=>{})
      },
      edit(link) {
        var data = {
          link : link.link,
          linkname: link.linkname,
          user_seq: cookie.cookieSeq(),
          userlink_seq: link.userlink_seq
        }
        var isSave = this.checkSave(link)
        if (isSave === false) alert('빈 값이 존재합니다.')
        else {
          PortfolioApi.editLink(data, res => {
            alert('수정되었습니다.')
          },err => {})
        }
      },
      slidingTab() {
        if (this.displayBlock == 'display: block;') {
          this.displayBlock = 'display: none;'
          this.displayNone = 'display: block;'
        } else {
          this.displayBlock = 'display: block;'
          this.displayNone = 'display: none;'
        }
        
      },
      password() {
        let {user_email}  = this;
        let data = {
            user_email
        }
        baseURL.post('/account/'+data.user_email+'/pwd-link')
        .then(res => {
          alert('전송에 성공하였습니다.')
        })
        .catch(err => {
          alert('에러가 발생하여 전송에 실패하였습니다.')
        })
      },
      uploadImage(e){
        this.file = e.target.files
      },
      deleteImage() {
        if (confirm('프로필 사진을 삭제합니다.')) {
          baseURL.put('/users/' + cookie.cookieSeq() + '/remove-photo') 
        }
      },
      update() {
        if (this.displayBlock == 'display: block;') {
          let { aboutme_seq, user_email_onoff , user_name , user_name_onoff , user_intro , user_intro_onoff, user_profile, interests } = this
          let data = { aboutme_seq, user_email_onoff, user_name , user_name_onoff , user_intro , user_intro_onoff, user_profile, interests }
          if (data.user_intro === null) {
            data.user_intro = ''
          }
          if (data.user_profile === null) {
            data.user_profile = ''
          }
          if (data.interests === null) {
            data.interests = ''
          }
          data.user_seq = cookie.cookieSeq()
          data.user_nickname = cookie.cookieNickname()

          if (typeof this.file === 'string')  {
            let params = data
            baseURL.put('/users/'+cookie.cookieSeq()+'/setting', null,{params})
            .then(res => {
              alert('수정하였습니다.')
              this.$router.push({name:'Profile', params:{userSeq: cookie.cookieSeq()}})
            })
            .catch(err => {
              alert('수정에 실패했습니다.')
            })
            } else {
            let params = data
            var form = new FormData()
            form.append('file', this.file[0])
            
            baseURL.post('/users/'+cookie.cookieSeq()+'/setting', form, {params})
            .then(res => {
              alert('수정하였습니다.')
              this.$router.push({name:'Profile', params:{userSeq: cookie.cookieSeq()}})
            })
            .catch(err => {
              alert('수정에 실패했습니다.')
            })
          }
        } else {
          baseURL.put('/portfolio/'+cookie.cookieSeq(), {portfolio_onoff:this.portfolio_onoff})
          .then(res=>{
            alert('포토폴리오 공개 설정이 성공정으로 수정되었습니다.')
            this.$router.push({name:'Profile', params:{userSeq: cookie.cookieSeq()}})
          })
          .catch(err=>{
            alert('포토폴리오 공개 설정이 수정이 실패하였습니다.')
          })

        }
        
      },
      
      addInput() {
        var data = {
          user_seq: cookie.cookieSeq(),
          user_nickname: cookie.cookieNickname(),
          link: this.link,
          linkname: this.linkname
        }
        var isSave = this.checkSave(data)
        if (isSave) {
          PortfolioApi.addLink(data, res=> {
            PortfolioApi.listLink(data, response=> {
              this.links = response.data.object
              this.link = ''
              this.linkname = ''
            }, error => {})
          }, err=>{})
        } else {
          alert('빈 값이 존재합니다.')
        }
        
      },
      drop() {
        if (confirm('정말 탈퇴하시겠습니까?')) {
          this.dropBack()
        }
      },
      dropBack() {
        baseURL.put('/users/'+cookie.cookieSeq()+'/secession')
        .then(res => {
          alert('탈퇴되었습니다.')
          this.logout()
          this.$router.push({name:'Login'})
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
    },
    mounted() {
      this.user_email = cookie.cookieInfo()
      var data = {
        user_email: this.user_email,
        user_seq: cookie.cookieSeq(),
        user_nickname: cookie.cookieNickname()
      }
      baseURL('/users/'+cookie.cookieSeq()+'/setting', {
        })
        .then(res=> {
          this.aboutme_seq = res.data.object.aboutme_seq
          this.user_nickname = res.data.object.user_nickname
          this.user_intro = res.data.object.user_intro
          this.user_name = res.data.object.user_name
          this.user_email_onoff = res.data.object.user_email_onoff
          this.user_intro_onoff = res.data.object.user_intro_onoff
          this.user_name_onoff = res.data.object.user_name_onoff
          this.user_auth = res.data.object.user_auth
          this.user_profile = res.data.object.user_profile_route
          if (res.data.object.interests === null) this.interests = '관심분야'
          else this.interests = res.data.object.interests
        })
      baseURL('/portfolio-info/' + cookie.cookieSeq(), {
        })
        .then(res => {
          this.portfolio_onoff = res.data.object.portfolio_onoff
        })
        
      
      PortfolioApi.listLink(data, response=> {
        this.links = response.data.object
      }, error => {})
    }
  }
</script>

<style lang="scss" scoped>

input[type=checkbox] {
  display: inline;
}

figure { display:flex; }

.tgl {
	display: none;
  
	// add default box-sizing for this scope
	&,
  &:after,
  &:before,
	& *,
  & *:after,
  & *:before,
	& + .tgl-btn {
		box-sizing: border-box;
		&::selection {
			background: none;
		}
	}
  
	+ .tgl-btn {
		outline: 0;
		display: block;
		width: 4em;
		height: 2em;
		position: relative;
		cursor: pointer;
    user-select: none;
		&:after,
    &:before {
			position: relative;
			display: block;
			content: "";
			width: 50%;
			height: 100%;
		}
    
		&:after {
			left: 0;
		}
    
		&:before {
			display: none;
		}
	}
  
	&:checked + .tgl-btn:after {
		left: 50%;
	}
}

.tgl-skewed {
	+ .tgl-btn {
		overflow: hidden;
		transform: skew(-10deg);
		backface-visibility: hidden;
		transition: all .2s ease;
		font-family: sans-serif;
    background: rgb(136, 136, 136);
		&:after,
    &:before {
			transform: skew(10deg);
			display: inline-block;
			transition: all .2s ease;
			width: 100%;
			text-align: center;
			position: absolute;
			line-height: 2em;
			font-weight: bold;
			color: #fff;
			text-shadow: 0 1px 0 rgba(0,0,0,.4);
		}
    
		&:after {
			left: 100%;
			content: attr(data-tg-on);
		}
    
		&:before {
			left: 0;
			content: attr(data-tg-off);
		}
    
		&:active {
			background: #888;
			&:before {
				left: -10%;
			}
		}
	}
  
	&:checked + .tgl-btn {
    background: rgb(5, 59, 11);
    &:before {
      left: -100%;
    }

    &:after {
      left: 0;
    }

    &:active:after {
      left: 10%;
    }
	}
}
h1 {
  text-align: center;
  font-size: 40px;
  font-weight: 600;
  margin-top: 60px;
  margin-bottom: 60px;
  color: transparent;      
  text-shadow: 2px 2px 3px rgba(255, 255, 255, 0.5);
  background-clip: text;
  -webkit-background-clip: text;
  -moz-background-clip: text;
  background-color: #83827f;
  cursor: pointer;
  }

.frontTab {
  font-size: 40px;
}
.backTab {
  font-size: 20px;
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

.vertical {
  display: inline-block;
  position: absolute;
  right: 5px;
  top: 10px;
}

.input-with-label input {
  font-weight: 400;
  outline: none;
    height: 50px;
    width: 100%;
    border-radius: 50px;
    border: none;
    padding: 0 30px;
    box-shadow: inset 5px 5px 10px rgba(119, 119, 119, .1), inset -5px -5px 10px rgba(255, 255, 255, 1);
    margin-bottom: 5px;
    color: rgb(65, 65, 65);
}

#file-input {
  margin: 30px;
  margin-bottom: -20px;
  font-weight: 400;
  width: 80%;
  display: inline-block;
}

.submit {
    cursor: pointer;
    border: 1px solid transparent;
    font-weight: 700;
    box-shadow: 5px 5px 10px 5px rgba(119, 119, 119, .1), -5px -5px 10px 5px rgba(255, 255, 255, 1);
    width: 100%;
    margin: 10px auto;
    border-radius: 50px;
    color:rgb(5, 59, 11);
    position: relative;
    align-self: auto;
    padding: 5px;
    padding-top: 17px;
    text-align: center;

}

.change {
  width: 80%;
  margin: 0;
  color: rgb(66, 64, 64);
}

.submit:disabled {
    background-color: #fafafa;
    color: #7f7f7c;

}
.submit:disabled:hover {
    cursor: not-allowed;
    
}
.submit:not(:disabled):hover {
    box-shadow: 3px 3px 5px 3px rgba(119, 119, 119, .1), -3px -3px 5px 3px rgba(255, 255, 255, 1);
    font-size: 1.02em;
}
.submit:active {
    box-shadow: -5px -5px 10px 5px rgba(119, 119, 119, .1), 5px 5px 10px 5px rgba(255, 255, 255, 1);
    color:#013f01;
    border-color: #f7f5ec;
}

.submit p {
  
}



figure {
  flex:1;
  flex-direction:column;
  justify-content:center;
}

.toggle {
  display:flex;
  position:relative;
  width:500px;
  height:50px;
  background:transparent;
  box-shadow: inset 3px 3px 3px #ccc;
  align-self:center;
  user-select:none;
  margin:2rem;
  color: #f7f5ec;
  background-color: rgb(5, 59, 11);
  border-radius: 50px;
  
}

  .toggle:after, .toggle:before {
  flex:1;
  text-align:center;
  font-size: 30px;
  font-weight: 400;
  line-height: 55px;
}
.toggle:after {
  content:"프로필 설정";
}
.toggle:before {
  content:"포트폴리오 설정";
}
.toggle input {
  display: none;
}
label {
  position:absolute;
  top:0; left:0; right:0; bottom:0;
  perspective:1000; 
  cursor:pointer;
}

.card {
  position:relative;
  background:#f6f5f0;
  transition:.5s;
  width:50%;
  height:115%;
  pointer-events:none;
  border-radius: 24px;
  box-shadow: -4px 4px 2px #ccc;
}

.slide input:checked + label .card { box-shadow: 4px 4px 2px #ccc;}

.slide .card { transform:translate(0); }
.slide input:checked + label .card {
  transform:translateX(100%);
}


.box {
  position: relative;
}
.box select {
  width: 40%;
  height: 50px;
  background-color: #f6f5f0;
  color:rgb(66, 64, 64);
  position: relative;
  text-align: center;
  outline:none;
  border-radius: 30px;
  padding-left: 20px;
}

// portfolio

.portfolio-line {
  margin-top: 20px;
  margin-bottom: -40px;
}
.portfolio label {
  position: relative;
  top: -50px;
  left: 30px;
  line-height: 40px;
  font-size: 1.03em;
  cursor: default;
}

.link-input input {
  width: 80%;
  display: inline-block;
}
.link-input button {
  margin: 5px;
}
.link-input button:not(.trash):hover {
  color: rgb(5, 59, 11);
  font-size: 1.05em;

}
.link-input .trash:hover, #file-input + .trash:hover {
  color:rgb(177, 27, 27);
  font-size: 1.05em;

}
.link {
  display: inline-block;
  width: 100%;
}
.link .fa-plus {
  margin-left: 5px;
}
.drop .fas {
  margin-right: 10px;
}
.drop {
  padding: 7px 10px;
  margin-bottom: 20px;
  margin-top: -20px;
  float: right;
  box-shadow: 3px 3px 6px 3px rgba(119, 119, 119, .1), -3px -3px 6px 3px rgba(255, 255, 255, 1);
  border: 1px solid transparent;
  border-radius: 50px;

}
.drop:hover {
  box-shadow: 1px 1px 2px 1px rgba(119, 119, 119, .1), -1px -1px 2px 1px rgba(255, 255, 255, 1);
  color: rgb(177, 27, 27);
  font-size: 1.04em;
  font-weight: 600;
}
.drop:active {
  box-shadow: -5px -5px 10px 5px rgba(119, 119, 119, .1), 5px 5px 10px 5px rgba(255, 255, 255, 1);

}
</style>