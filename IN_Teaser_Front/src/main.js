import Vue from 'vue'
import App from './App.vue'
import Router from 'vue-router'
import routes from './routes'
import store from './vuex/store'
import VModal from 'vue-js-modal'
import VueSession from 'vue-session'
import cookie from '@/cookie.js'
import VueTableDynamic from 'vue-table-dynamic'
import io from 'socket.io-client';
//const socket = io('http://localhost:3000');
//const socket = io('https://i02b204.p.ssafy.io');
const socket = io('');
import VoerroTagsInput from '@voerro/vue-tagsinput';
import VueCtkDateTimePicker from 'vue-ctk-date-time-picker';
import 'vue-ctk-date-time-picker/dist/vue-ctk-date-time-picker.css';
import NotiApi from '@/apis/NotiApi.js'

Vue.component('VueCtkDateTimePicker', VueCtkDateTimePicker);
Vue.component('tags-input', VoerroTagsInput);
Vue.prototype.$socket = socket;

Vue.config.productionTip = false

Vue.use(VueTableDynamic)
Vue.use(Router)
Vue.use(VModal)
Vue.use(VueSession)
Vue.use(store)



const router = new Router({
  mode: 'history',
  routes,
  scrollBehavior (to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { x: 0, y: 0 }
    }
  }
});


router.beforeEach((to, from, next) => {
  if (cookie.cookieAuth() == 0 && to.name != 'Admin' && to.name != 'Login') {
    return next('admin')
  }
  if (from.name === 'Noti') {
    NotiApi.leaveReq({user_seq:cookie.cookieSeq()}, res=>{}, err=>{})
  }
  if (cookie.updateCookie()) {
    if (to.meta.loginRequire === false ) {
      if (from.name === null) {
        return next('Main')
      } else {
        return next(false);
      }
    }
    next();
  } else {
    if (to.meta.loginRequire === true) {
      alert('로그인 해주세요');
      return next('/')
    } 
    return next();
  }
})

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');


