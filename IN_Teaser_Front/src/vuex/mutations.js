export default {
  isLogin: state => {
    state.isLogin = true
  },

  passwordChange(state, info) {
    state.userEmail= info.user_email
    state.preURL = info.preURL
  },

  startLogin(state, data) {
    state.userEmail = data.userEmail
    state.userSeq = data.userSeq
    state.userAuth = data.userAuth
  },
  
  clearInfo: state => {
    state.userEmail= ''
  },

  profileUserEmail(state, email) {
    state.profile = email
  }
}