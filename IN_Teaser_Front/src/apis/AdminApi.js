import baseURL from '@/base-url.js'

const feedPerDay = (callback, errorCallback) => {
  baseURL('/admin/main/feed-per-day')
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const allFeed = (callback, errorCallback) => {
  baseURL('/admin/feed-management/search-all-feed')
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const reportFeed = (callback, errorCallback) => {
  baseURL('/admin/feed-management/search-all-reportfeed')
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const hideFeed = (data, callback, errorCallback) => {
  baseURL.put('/admin/feed-management/'+data.feed_seq+'/hide')
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const allUser = (callback, errorCallback) => {
  baseURL('/admin/user-management/search-all-user')
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const normalUser = (callback, errorCallback) => {
  baseURL('/admin/user-management/search-normal')
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const recruiterUser = (callback, errorCallback) => {
  baseURL('/admin/user-management/search-recruiter')
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const managementUser = (data, callback, errorCallback) => {
  baseURL.put('/admin/user-management/'+data.user_seq)
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const isAdmin = (data, callback, errorCallback) => {
  //baseURL('http://localhost:8888/api/users/'+data.user_seq+'/is-admin')
  baseURL('/users/'+data.user_seq+'/is-admin')
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}



const AdminApi = {
  isAdmin:(data,callback,errorCallback)=>isAdmin(data,callback,errorCallback),
  allFeed:(callback,errorCallback)=>allFeed(callback,errorCallback),
  feedPerDay:(callback,errorCallback)=>feedPerDay(callback,errorCallback),
  reportFeed:(callback,errorCallback)=>reportFeed(callback,errorCallback),
  hideFeed:(data,callback,errorCallback)=>hideFeed(data,callback,errorCallback),
  allUser:(callback,errorCallback)=>allUser(callback,errorCallback),
  normalUser:(callback,errorCallback)=>normalUser(callback,errorCallback),
  recruiterUser:(callback,errorCallback)=>recruiterUser(callback,errorCallback),
  managementUser:(callback,errorCallback)=>managementUser(callback,errorCallback),
  
}

export default AdminApi