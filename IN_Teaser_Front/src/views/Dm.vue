
<template>
	<span>
    <div class="page-container app">
			<p class="dm-close" @click="dmWindowClose"><i class="fas fa-times-circle"></i></p>
      <div class="md-toolbar-row">
          <h2 class="md-title">{{opponent_nickname}}</h2>
      </div>
			<div class="dm-ul"> 
				<ul>
					<span v-for="(serve, idx) in server" :key="idx">
						<li v-if="serve.speaker" class="talk-bubble tri-right round right-in same"><p class="talktext mytalk">{{ serve.content }}</p></li>
						<li v-else class="talk-bubble tri-right round left-in">
							<!-- <p class="oppnentUser">{{ opponent_nickname }}</p> -->
							<p class="talktext">{{ serve.content }}</p>
						</li>
					</span>
					<li v-for="(text, idx) in textarea" :key="'text'+idx" class="talk-bubble tri-right round right-in same">
						
						<span>
							<p class="talktext mytalk" :class="{same:text.name === user}"> {{text.message}}</p>
						</span>
					</li>
				</ul>
			</div>
			<span class="dm-bottom">
				<input class="dm-input" @keypress.enter="sendMessage" v-model="message">
				<button class="md-primary md-raised dm-submit" @click="sendMessage()"><i class="far fa-paper-plane"></i></button>
			</span>
    </div>
	</span>
</template>


<script>
	import cookie from '@/cookie.js'
	import NotiApi from '@/apis/NotiApi.js'
    export default {
			watch: {
				server: {
					handler(server) {
						this.textarea = []
						this.$socket.emit('startMessage', {room:server[0].server_seq, nickname:cookie.cookieNickname()}, (data => {
							this.textarea.push(data.message)
						}))
					}
				},
			},
			updated(){   
				var scroll = document.getElementsByClassName('dm-ul')[0]
				scroll.scrollTop = scroll.scrollHeight
			},
			props: {
				server: {
					type: Array,
					required: true
				},

				opponent_nickname: {
					type: String,
					required: true
				}
			},

			created() {
				this.user = cookie.cookieNickname()
				this.$socket.on('message', (data) => {
					this.textarea.push({name:data.nickname, message:data.message})
					.then(res => {
						this.$emit('scroll')
					})
				})
			},
			data() {
				return {
					textarea: [],
					nickname: '',
					room: '',
					message: '',
					user: '',
					opponent_seq: '',
					server_seq: '',
				}
			},
			methods: {
				sendMessage () {
					var message = this.message
					this.message = ''
					var data = {
						user_seq: cookie.cookieSeq(),
						opponent_seq: this.server[0].opponent_seq,
						server_seq: this.server[0].server_seq,
						content: message
					}
					NotiApi.dmSend(data, res=>{
						this.textarea.push({name:this.user, message:message})
						this.$socket.emit('sendMessage',{ room:this.server[0].server_seq, nickname:this.user, message:message}, (data => {
							}));
						this.$emit('scroll')
					}, err=>{

					})
				},
				dmWindowClose() {
					NotiApi.dmExit({user_seq:cookie.cookieSeq()})
					this.$emit('close', {opponent_seq: this.server[0].opponent_seq, opponent_nickname:this.opponent_nickname})
				}
			}
    }
</script>

<style scoped>
.app {
	padding-top: 10px !important;
	min-height: 500px !important;
	min-width: 350px !important;
	max-width: 400px !important;
	background-color: #f6f6f6;
  box-shadow: inset 7px 7px 10px rgba(119, 119, 119, .1), inset -7px -7px 10px rgba(255, 255, 255, 1);
	position: relative;
}
.page-container {
	max-height: 500px;
	position: relative;
}
.dm-close {
	margin-top: 10px;
	float: right;
	font-size: 16px;
}
.dm-close:hover {
	color: rgb(177, 27, 27);
}
.md-toolbar-row {
	border-bottom: 1px solid rgb(5, 59, 11);
}
.md-title {
	font-size: 2.4em;
	text-align: left;
	margin-left: 20px;
	margin-top: 0;
	font-family: 'East Sea Dokdo';
}
.dm-ul {
	max-height: 350px;
	overflow-y: scroll;
	overflow-x: hidden;
}
.dm-bottom {
	position: absolute;
	bottom: 10px;
	width: 90%;
}
.dm-input {
	outline: none;
	height: 50px;
	width: 80%;
	border-radius: 50px;
	border: none;
	padding: 0 30px;
	box-shadow: inset 5px 5px 10px rgba(119, 119, 119, .1), inset -5px -5px 10px rgba(255, 255, 255, 1);
	margin-bottom: 10px;
}
.dm-input:hover {
    box-shadow: -2px -2px 2px rgba(119, 119, 119, .1), 2px 2px 2px rgba(255, 255, 255, 1);
}
.dm-submit {
	cursor: pointer;
	border: 1px solid transparent;
	padding: 5px 10px;
	font-weight: 700;
	box-shadow: 2px 2px 4px 2px rgba(119, 119, 119, .1), -2px -2px 4px 2px rgba(255, 255, 255, 1);
	margin-left: 15px;
	border-radius: 50px;
	color:#013f01;
	position: relative;
}
.dm-submit:hover {
	box-shadow: -5px -5px 10px 5px rgba(119, 119, 119, .1), 5px 5px 10px 5px rgba(255, 255, 255, 1);
	color:#013f01;
	border-color: #f7f5ec;
}
.md-app {

		height: 800px;
		border: 1px solid rgba(#000, .12);
}
.md-textarea {
		height: 300px;
}
.talk-bubble {
	margin: 10px 5px;
  display: block;
  position: relative;
	max-width: 200px;
	min-height: 50px;
	/* border: 2px dashed #ccc; */
	/* background-color: #f6f5f0; */
	z-index: 102;
	background-color: #E7EBE0FF;
}
.talk-bubble.left-in {
	background-color: rgb(199, 231, 238);
}
.border{
  border: 8px solid #666;
}
.round{
  border-radius: 30px;
	-webkit-border-radius: 30px;
	-moz-border-radius: 30px;

}

/* Right triangle, left side slightly down */

.tri-right.left-in:after{
	content: ' ';
	position: absolute;
	width: 0;
	height: 0;
  left: -5px;
	right: auto;
  top: 5px;
	bottom: auto;
	border: 10px solid;
	border-color: rgb(199, 231, 238) rgb(199, 231, 238) transparent transparent;
}
.tri-right.same {
	margin-right: 10px;
	margin-left: auto;
	display: block;
}
.talknew {
	margin-right: 10px;
	margin-left: auto;
	display: block;
}
/* Right triangle, right side slightly down*/

.tri-right.right-in:after{
	content: ' ';
	position: absolute;
  left: auto;
	right: -10px;
  top: 15px;
	bottom: auto;
	z-index: 101;
	border: 12px solid;
	border-color: #E7EBE0FF transparent transparent #E7EBE0FF;
}

/* talk bubble contents */
.talktext{
	text-align: left;
	padding: 15px 20px 10px 20px;
	overflow-wrap: break-word;
}
.talktext p{
	max-width: 200px;
  /* remove webkit p margins */
  -webkit-margin-before: 0em;
	-webkit-margin-after: 0em;
	overflow-wrap: break-word;
}
.mytalk {
	text-align: right;
}
</style>
