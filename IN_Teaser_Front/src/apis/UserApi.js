/*
 User API 예시
 */
// 토큰 및 사용자 정보를 저장하기 위해서 세션 스토리지를 사용한다.
import baseURL from '@/base-url.js'

const requestLogin = (data,callback,errorCallback) => {
    //백앤드와 로그인 통신하는 부분
    baseURL.post('/account/'+data.user_email+'/login', null,{
        params:{
            user_pwd: data.user_pwd
        }
    })
    .then(response => {
        callback(response)
    })
    .catch(error => {
        errorCallback()
    })
}

const requestEmailDuplicateCheck = (data, callback, errorCallback) => {
    baseURL('/account/'+data.user_email+'/email-duplicate')
        .then(response => {
            callback(response.data.status)
        })
        .catch(error => {
            errorCallback(error)
        })
}

const requestNicknameDuplicateCheck = (data, callback, errorCallback) => {
    baseURL('/account/'+data.user_nickname+'/nickname-duplicate')
        .then(response => {
            callback(response.data.status)
        })
        .catch(error => {
            errorCallback(error)
        })
}

const requestJoin = (data,callback,errorCallback) => {
    baseURL.post('/account/signup', data)
        .then(response => {
            callback()
        })
        .catch(error => {
            errorCallback();
        })
}

const snsLogin = (data,callback,errorCallback) => {
    baseURL('/account/'+data.user_email +'/sns-login')
        .then(response => {
            callback(response)
        })
        .catch(err => {
            errorCallback(err)
        })
}

const snsSignup = (data, callback, errorCallback) => {
    baseURL.post('/account/sns-signup', data)
        .then(response => {
            callback(response)
        })
        .catch(err => {
            errorCallback(err)
        })
}

const snsUserCheck = (data, callback, errorCallback) => {
    baseURL('/account/'+data.user_email+'/snsUserCheck',  {
        params:{
            login_type: data.login_type,
            user_auth: data.user_auth
        }
    })
        .then(response => {
            callback(response)
        })
        .catch(err => {
            errorCallback(err)
        })
}

const randomUser = (callback, errorCallback) => {
    baseURL('/account/findAll_user')
        .then(response => {
            callback(response)
        })
        .catch(err => {
            errorCallback(err)
        })
}
const UserApi = {
    requestLogin:(data,callback,errorCallback)=>requestLogin(data,callback,errorCallback),
    requestJoin:(data,callback,errorCallback)=>requestJoin(data,callback,errorCallback),
    requestEmailDuplicateCheck:(data, callback, errorCallback) => requestEmailDuplicateCheck(data,callback,errorCallback),
    requestNicknameDuplicateCheck:(data, callback, errorCallback) => requestNicknameDuplicateCheck(data,callback,errorCallback),
    snsLogin:(data, callback, errorCallback) => snsLogin(data,callback,errorCallback),
    snsSignup:(data, callback, errorCallback) => snsSignup(data,callback,errorCallback),
    snsUserCheck:(data, callback, errorCallback) => snsUserCheck(data,callback,errorCallback),
    randomUser:(callback, errorCallback) => randomUser(callback, errorCallback),
}

export default UserApi
