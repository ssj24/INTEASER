import baseURL from '@/base-url.js'

const tryFollow = (data,callback,errorCallback) => {
  baseURL.post('/users/'+data.user_seq+'/try-follow', data)
  .then(res => {
    var staus = res.data.object
    callback(staus)
  })
  .catch(err => {
    errorCallback(false)
  })
}
//  

const requetFollow = (data,callback,errorCallback) => {
  baseURL.post('/users/'+data.user_seq+'/request-follow', data) 
  .then(res => {
    callback(res.data.status)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const followingList = (data, callback, errorCallback) => {
  baseURL('/users/'+data.opponent_seq+'/list-of-following')
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const followerList = (data, callback, errorCallback) => {
  baseURL('/users/'+data.opponent_seq+'/list-of-follower')
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const FollowApi = {
  tryFollow:(data,callback,errorCallback)=>tryFollow(data,callback,errorCallback),
  requetFollow:(data,callback,errorCallback)=>requetFollow(data,callback,errorCallback),
  followingList:(data,callback,errorCallback)=>followingList(data,callback,errorCallback),
  followerList:(data,callback,errorCallback)=>followerList(data,callback,errorCallback),
}

export default FollowApi