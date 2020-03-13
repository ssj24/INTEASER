import baseURL from '@/base-url.js'

const noticeCenter = (data,callback,errorCallback) => {
  baseURL.put('/users/'+data.user_seq+'/notice-center')
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const closeCenter = (data,callback,errorCallback) => {
  baseURL.put('/users/'+data.user_seq+'/close-notice')
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const checkNotice = (data,callback,errorCallback) => {
  baseURL.put('/users/'+data.user_seq+'/check-new-notice')
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const deleteNoti = (data,callback,errorCallback) => {
  baseURL.delete('/users/'+data.user_seq+'/notices/' + data.notice_seq )
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const checkRequest = (data,callback,errorCallback) => {
  baseURL.put('/users/'+data.user_seq+'/check-new-request')
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const notice = (data,callback,errorCallback) => {
  baseURL('/users/'+data.user_seq+'/list-of-notice')
  .then(res => {
    
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const requests = (data,callback,errorCallback) => {
  baseURL('/users/'+data.user_seq+'/list-of-request')
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const answer = (data, callback, errorCallback) => {
  baseURL.put('/users/'+data.user_seq+'/answer', data)
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const leaveReq = (data, callback, errorCallback) => {
  baseURL.put('/users/'+data.user_seq+'/leave-request-tab')
  .then(res => {
    leaveNoti(data, response => {
      callback(response)
    }, error => {
      errorCallback(error)
    })
  })
  .catch(err => {
    errorCallback(err)
  })
}

const leaveNoti = (data, callback, errorCallback) => {
  baseURL.put('/users/'+data.user_seq+'/leave-notice-tab')
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const dmList = (data, callback, errorCallback) => {
  baseURL('/users/'+data.user_seq+'/dm-list')
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}


const dmStart = (data, callback, errorCallback) => {
  baseURL('users/'+data.user_seq+'/dm', {params:{
    opponent_seq: data.opponent_seq
  }})
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const dmSend = (data, callback, errorCallback) => {
  baseURL.post('users/'+data.user_seq+'/dm', data)
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const dmExit = (data) => {
  baseURL.put('/users/'+data.user_seq+'/dm-time')
}

const NotiApi = {
  notice:(data,callback,errorCallback)=>notice(data,callback,errorCallback),
  requests:(data,callback,errorCallback)=>requests(data,callback,errorCallback),
  answer:(data,callback,errorCallback)=>answer(data,callback,errorCallback),
  leaveReq:(data,callback,errorCallback)=>leaveReq(data,callback,errorCallback),
  leaveNoti:(data,callback,errorCallback)=>leaveNoti(data,callback,errorCallback),
  noticeCenter:(data,callback,errorCallback)=>noticeCenter(data,callback,errorCallback),
  closeCenter:(data,callback,errorCallback)=>closeCenter(data,callback,errorCallback),
  deleteNoti:(data,callback,errorCallback)=>deleteNoti(data,callback,errorCallback),
  checkRequest:(data,callback,errorCallback)=>checkRequest(data,callback,errorCallback),
  checkNotice:(data,callback,errorCallback)=>checkNotice(data,callback,errorCallback),
  dmList:(data,callback,errorCallback)=>dmList(data,callback,errorCallback),
  dmStart:(data,callback,errorCallback)=>dmStart(data,callback,errorCallback),
  dmSend:(data,callback,errorCallback)=>dmSend(data,callback,errorCallback),
  dmExit:(data)=>dmExit(data),
}

export default NotiApi