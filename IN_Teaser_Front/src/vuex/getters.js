import cookie from '@/cookie.js'

export default {
  isLogin: state => {
    var token = cookie.token()
    if (token == null) {
      return false
    }
    if (token) {
      return true
    } else {
      return false
    }
  },

  userAuth: state => {
    return cookie.cookieAuth()
  },

  passwordChange: state => {
    return {
      user_email: state.userEmail,
      preURL: state.preURL
    }
  },

  userInfo: state => {
    return cookie.cookieInfo()
  },

  profileUserEmail: state => {
    return {
      profile: state.user_email
    }
  },

  userSeq: state => {
    return cookie.cookieSeq()
  }
}