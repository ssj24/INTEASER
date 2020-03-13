import baseURL from '@/base-url.js'

const editFeed = (data, callback, errorCallback) => {
  baseURL.put('/feed/'+data.user_seq+'/'+data.feed_seq, data)
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const hideFeed = (data, callback, errorCallback) => {
  baseURL.post('/feed/'+data.user_seq+'/feed-hide/'+data.feed_seq, data)
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

// const scrapFeed = (data, callback, errorCallback) => {
//   baseURL.post('/feed/'+data.user_seq+'/scrap/'+ data.feed_seq)
// }

const like = (data, callback, errorCallback) => {
  baseURL.post('/feed/' + data.user_seq + '/like/' + data.feed_seq, data)
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const likeCancel = (data, callback, errorCallback) => {
  baseURL.delete('/feed/' + data.user_seq + '/like-cancel/' + data.feed_seq, data)
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const newFeed = (data, callback, errorCallback) => {
  baseURL('/feed/'+data.user_seq+'/new-feed-list')
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const createFeed = (data,callback,errorCallback) => {
  baseURL.post('/feed/'+data.user_seq, data)
  .then(res => {
    callback(res)
  })
  .catch(err=>{
    errorCallback(err)
  })
}

const deleteFeed = (data, callback, errorCallback) => {
  baseURL.delete('/feed/'+data.user_seq+'/'+data.feed_seq)
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const detailFeed = (data, callback, errorCallback) => {
  baseURL('/feed/' + data.user_seq + '/detail/' + data.feed_seq)
  .then(res => {
    callback(res.data.object)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const createComment = (data, callback, errorCallback) => {
  baseURL.post('/comment/'+data.user_seq, data)
  .then(res => {
    detailFeed(data, response=>{ callback(response)}, error => {errorCallback(error)})
  })
  .catch(err => {
    errorCallback(err)
  })
}
const deleteComment = (data, callback, errorCallback) => {
  baseURL.delete('/comment/' + data.user_seq + '/' + data.comment_seq) 
  .then(res => {
    callback(res)
  })
  .catch(err => {
    errorCallback(err)
  })
}

const createRecomment = (data, callback, errorCallback) => {
  baseURL.post('/recomment/'+data.user_seq, data)
  .then(res => {
    detailFeed(data, response=>{ callback(response)}, error => {errorCallback(error)})
  })
  .catch(err => {
    errorCallback(err)
  })
}

const listComment =  (data, callback, errorCallback) => {
  baseURL('/feed/'+data.feed_seq+'/comment-list')
  .then(res => {
    callback(res.data.object)
  })
  .catch(err => {
    errorCallback(err)
  })
}


const FeedApi = {
  newFeed:(data,callback,errorCallback)=>newFeed(data,callback,errorCallback),
  hideFeed:(data,callback,errorCallback)=>hideFeed(data,callback,errorCallback),
  editFeed:(data,callback,errorCallback)=>editFeed(data,callback,errorCallback),
  like:(data,callback,errorCallback)=>like(data,callback,errorCallback),
  likeCancel:(data,callback,errorCallback)=>likeCancel(data,callback,errorCallback),
  createFeed:(data,callback,errorCallback)=>createFeed(data,callback,errorCallback),
  deleteFeed:(data,callback,errorCallback)=>deleteFeed(data,callback,errorCallback),
  createComment:(data,callback,errorCallback)=>createComment(data,callback,errorCallback), 
  createRecomment:(data,callback,errorCallback)=>createRecomment(data,callback,errorCallback), 
  detailFeed:(data,callback,errorCallback)=>detailFeed(data,callback,errorCallback), 
  deleteComment:(data,callback,errorCallback)=>deleteComment(data,callback,errorCallback), 
  listComment:(data,callback,errorCallback)=>listComment(data,callback,errorCallback), 
}

export default FeedApi