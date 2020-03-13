function token() {
  var value = document.cookie.match('(^|;) ?' + 'jwt-auth-token' + '=([^;]*)(;|$)');
  return value = value? value[2] : null
}

function cookieInfo() {
  var value = document.cookie.match('(^|;) ?' + 'login_user' + '=([^;]*)(;|$)');
  return value = value? value[2] : null
}

function cookieSeq() {
  var value = document.cookie.match('(^|;) ?' + 'login_seq' + '=([^;]*)(;|$)');
  return value = value? value[2] : null
}

function cookieNickname() {
  var value = document.cookie.match('(^|;) ?' + 'login_nickname' + '=([^;]*)(;|$)');
  return value = value? value[2] : null
}

function cookieAuth() {
  var value = document.cookie.match('(^|;) ?' + 'login_auth' + '=([^;]*)(;|$)');
  return value = value? value[2] : null
}



function updateCookie() {
  var value = token()
  if (value !== null) {
      var day = new Date();
      // ms단위기에 1초로 변환->60초->60분->24시간->최종적으로 day
      day.setTime(day.getTime() + (60*60*1000));
      var expires = "expires=" + day.toUTCString();
      document.cookie = 'jwt-auth-token' + "=" + value + ";" + expires + ";path=/" + ";";
      document.cookie = 'login_user' + "=" + cookieInfo() + ";" + expires + ";path=/" + ";";
      document.cookie = 'login_seq' + "=" + cookieSeq() + ";" + expires + ";path=/" + ";";
      document.cookie = 'login_nickname' + "=" + cookieNickname() + ";" + expires + ";path=/" + ";";
      document.cookie = 'login_auth' + "=" + cookieAuth() + ";" + expires + ";path=/" + ";";
      return true
  } else {
    return false
  }
}

function cookieCreate(data) {
  let d = new Date();                
  d.setTime(d.getTime() + (60*60*1000));
  let expires = "expires="+ d.toUTCString();
  document.cookie = "login_user=" + data.userEmail + ";" + expires + ";path=/";
  document.cookie = "jwt-auth-token=" + data.token + ";" + expires + ";path=/";
  document.cookie = "login_seq=" + data.userSeq + ";" + expires + ";path=/";
  document.cookie = 'login_nickname' + "=" + data.userNickname + ";" + expires + ";path=/" + ";";
  document.cookie = 'login_auth' + "=" + data.userAuth + ";" + expires + ";path=/" + ";";
}

const cookie = {
  token:()=>token(),
  updateCookie:()=>updateCookie(),
  cookieInfo:()=>cookieInfo(),
  cookieSeq:()=>cookieSeq(),
  cookieNickname:()=>cookieNickname(),
  cookieCreate:(email, headers)=>cookieCreate(email, headers),
  cookieAuth:(email, headers)=>cookieAuth(email, headers)
}
export default cookie