import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import JoinCompleted from './views/user/JoinCompleted.vue'
import EmailConfirm from './views/user/EmailConfirm.vue'
import PasswordLost from './views/user/PasswordLost.vue'
import PasswordCompleted from './views/user/PasswordCompleted.vue'
import NotFound from './views/404.vue'
import PasswordChange from './views/user/PasswordChange.vue'
import PasswordChangeComplete from './views/user/PasswordChangeComplete.vue'
import Error from './views/Error.vue'
import Main from './views/main/Main.vue'
import Article from './views/main/Article.vue'
import Detail from './views/main/ArticleDetail.vue'
import Account from './views/Account.vue'
import Noti from './views/Noti.vue'
import Profile from './views/profile/Profile.vue'
import ProjectFirst from './views/profile/ProjectFirst.vue'
import ProjectSecond from './views/profile/ProjectSecond.vue'
import ProjectDetail from './views/profile/ProjectDetail.vue'
import Recruiter from './views/profile/Recruiter.vue'
import Idconfirm from './views/mail/Idconfirm.vue'
import Admin from './views/user/Admin.vue'
import Projects from './views/Projects.vue'
import EditArticle from './views/main/EditArticle.vue'
import DM from './views/Dm.vue'



export default [
    {
        path : '/',
        name : 'Login',
        component : Login,
        meta: { hideNavigation: true, loginRequire : false }
    },
    {
        path : '/user/join',
        name : 'Join',
        component : Join
    },
    {
        path : '/user/joinCompleted',
        name : 'JoinCompleted',
        component : JoinCompleted,
        props: true
    },
    {
        path : '/user/EmailConfirm',
        name : 'EmailConfirm',
        component : EmailConfirm
    },
    {
        path : '/user/passwordLost',
        name : 'PasswordLost',
        component : PasswordLost
    },
    {
        path : '/user/passwordCompleted',
        name : 'PasswordCompleted',
        component : PasswordCompleted,
        props: true
    },
    {
        path : '/error',
        name : 'error',
        component : Error
    },
    {
        path: '/PasswordChange',
        name : 'PasswordChange',
        component : PasswordChange
    },
    {
        path: '/PasswordChangeComplete',
        name: 'PasswordChangeComplete',
        component: PasswordChangeComplete
    },
    {
        path: '/main',
        name: 'Main',
        component: Main,
        meta: { loginRequire : true }
    },
    {
        path: '/main/article',
        name: 'Article',
        component: Article,
    },
    {
        path : '/main/article/edit',
        name: 'EditArticle',
        component: EditArticle,
        props: true
    },
    {
        path: '/main/article/:feedSeq',
        name: 'Detail',
        component: Detail,
    },
    {
        path: '/account',
        name: 'Account',
        component: Account,
        meta: { loginRequire : true }
    },
    {
        path : '/noti',
        name : 'Noti',
        component : Noti
    },
    {
        path : '/mail/:confirmcode/:email/:flag',
        name : 'Idconfirm',
        component: Idconfirm,
        meta: { hideNavigation: true }
    },
    {
        path : '/profile/:userSeq',
        name : 'Profile',
        component : Profile,
        meta: { loginRequire : true },
        props: true,
    },
    {
        path: '/project1',
        name: 'ProjectFirst',
        component: ProjectFirst,
    },
    {
        path: '/project2',
        name: 'ProjectSecond',
        component: ProjectSecond,
        props: true,
    },
    {
        path: '/projectdetail/:projectSeq',
        name: 'ProjectDetail',
        component: ProjectDetail,
    },
    {
        path: '/admin',
        name: 'Admin',
        component: Admin,
        meta: { hideNavigation: true }
    },
    {
        path: '/Recruiter',
        name: 'Recruiter',
        component: Recruiter,
    },
    {
        path: '/projects',
        name: 'Projects',
        component: Projects,
    },
    {
        path : '*',
        name : 'NotFound',
        component : NotFound,
    },
    {
        path: '/dmmm',
        name: 'DM',
        component: DM,
    }

]
