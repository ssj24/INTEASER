import baseURL from '@/base-url.js'

const imageUpload = (data, callback, errorCallback) => {
  baseURL.post('/feed/'+data.user_seq+'/upload', data.file, {
  })
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const profileImageUpload = (data, callback, errorCallback) => {
  baseURL.post('/users/'+data.user_seq+'/upload', data.file, {
  })
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const projectLike = (data, callback, errorCallback) => {
  baseURL.post('/project/'+data.user_seq+'/like/'+data.project_seq, null, {
    params:{user_nickname:data.user_nickname}
  })
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const projectLikeCancel = (data, callback, errorCallback) => {
  baseURL.delete('/project/'+data.user_seq+'/like-cancel/'+data.projectlike_seq, {data})
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const projectStep1 = (data, callback, errorCallback) => {
  baseURL.post('/project/'+data.user_seq+'/step1', data)
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const projectStep2 = (data, callback, errorCallback) => {
  baseURL.put('/project/'+data.user_seq+'/step2/'+ data.project_seq, data)
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const projectDevSetting = (data, callback, errorCallback) => {
  baseURL.post('project/'+data.user_seq+'/search-dev-setting')
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const addLink = (data, callback, errorCallback) => {
  baseURL.post('/link/'+ data.user_seq, data)
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}


const listLink = (data, callback, errorCallback) => {
  baseURL('/link/'+data.user_seq)
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const deleteLink = (data, callback, errorCallback) => {
  baseURL.delete('/link/'+data.user_seq+'/'+data.userlink_seq)
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const editLink = (data, callback, errorCallback) => {
  baseURL.put('/link/'+data.user_seq+'/'+data.userlink_seq, data)
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}
const projectList = (data, callback, errorCallback) => {
  baseURL('/project/'+data.user_seq)
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const projectDetail = (data, callback, errorCallback) => {
  baseURL('/project/'+data.user_seq+'/'+data.project_seq)
  .then(res => {
    callback(res.data.object)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const projectEdit = (data, callback, errorCallback) => {
  baseURL.put('/project/'+data.user_seq+'/'+ data.project_seq, data)
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const projectDelete = (data, callback, errorCallback) => {
  baseURL.delete('/project/'+data.user_seq+'/'+ data.project_seq)
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}



const PortfolioApi = {
  imageUpload:(data,callback,errorCallback)=>imageUpload(data,callback,errorCallback),
  profileImageUpload:(data,callback,errorCallback)=>profileImageUpload(data,callback,errorCallback),
  projectLike:(data,callback,errorCallback)=>projectLike(data,callback,errorCallback),
  projectLikeCancel:(data,callback,errorCallback)=>projectLikeCancel(data,callback,errorCallback),
  projectList:(data,callback,errorCallback)=>projectList(data,callback,errorCallback),
  projectDetail:(data,callback,errorCallback)=>projectDetail(data,callback,errorCallback),
  projectDevSetting:(data,callback,errorCallback)=>projectDevSetting(data,callback,errorCallback),
  projectEdit:(data,callback,errorCallback)=>projectEdit(data,callback,errorCallback),
  projectStep1:(data,callback,errorCallback)=>projectStep1(data,callback,errorCallback),
  projectStep2:(data,callback,errorCallback)=>projectStep2(data,callback,errorCallback),
  projectDelete:(data,callback,errorCallback)=>projectDelete(data,callback,errorCallback),
  addLink:(data,callback,errorCallback)=>addLink(data,callback,errorCallback),
  listLink:(data,callback,errorCallback)=>listLink(data,callback,errorCallback),
  deleteLink:(data,callback,errorCallback)=>deleteLink(data,callback,errorCallback),
  editLink:(data,callback,errorCallback)=>editLink(data,callback,errorCallback),
}

export default PortfolioApi