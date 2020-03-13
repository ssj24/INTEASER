import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'

Vue.use(Vuex)

const state = {
    isUser: false,
    isLogin: getters.isLogin(),
    userEmail: getters.userInfo(),
    preURL: '',
    profile: '',
    userSeq: getters.userSeq(),
    userAuth: getters.userAuth(),
}

export default new Vuex.Store({
    state,
    mutations,
    getters,
    actions
})