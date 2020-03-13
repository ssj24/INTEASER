<template>
  <div class="portfolio-warpper">
    <div class="introduction">
      <span v-if="links.length > 0">
        <p
          v-for="link in links" 
          :key="link.userlink_seq"
          class="links">
          <span class="linkname">
            {{ link.linkname }}
          </span>
          <span class="link">
            <a :href=link.link target="_blank">{{ link.link }}</a>
          </span>
        </p>
      </span>
      <p v-for="(key, value, index) in devStacks" 
        :key="index+'stack'"
        class="stacks">
          {{ value }}
      </p>
    </div>
    <div v-if="sameUser" class="new-project">
      <router-link v-bind:to="{name:'ProjectFirst'}">
        <i class="fas fa-plus"></i>
      </router-link>
    </div>
    <div v-for="(project, idx) in projects" :key="idx" class="projects">
      
        <div class="project postit">
          <span class="clip"></span>
          <router-link v-bind:to="{name:'ProjectDetail', params:{projectSeq:project.project_seq}}">
            <p><b>제목:</b> {{ project.project_name }}</p>
            <p><b>소개:</b> {{ project.project_intro }}</p>
            <p v-if="project.project_dev_setting"><b>사용된 기술:</b> {{ project.project_dev_setting }}</p>
          </router-link>
          <span v-if="sameUser" class="project-edit">
            <button @click="editProject(project)" class="project_update"><i class="fas fa-pencil-alt"></i></button>
            <button @click="deleteProject(project, idx)"><i class="fas fa-trash-alt trash"></i></button>
          </span>
          
        </div>
    </div>
  </div>
</template>

<script>
  import '@/assets/css/tab.css'
  import '@/assets/css/main.css'
  import PortfolioApi from '@/apis/PortfolioApi.js'
  import baseURL from '@/base-url.js'
  import cookie from '@/cookie.js'

  export default {
    data:() => {
      return {
        projects: {},
        links: {},
        devStacks: {},
        sameUser: false,
      }
    },
    methods: {
      deleteProject(project, idx) {
        var data = {
          user_seq: cookie.cookieSeq(),
          project_seq: project.project_seq
        }
        PortfolioApi.projectDelete(data, res=> {
          this.projects.splice(idx, 1)
        }, err=>{})
      },
      editProject(project) {
        this.$router.push({name:'ProjectSecond', params:{project_seq:project.project_seq, flag:'update'}})
      },
    },
    mounted() {
      PortfolioApi.projectList({user_seq:this.$route.params.userSeq}, res=>{
        this.projects = res.data.object
      }, err=>{})

      PortfolioApi.listLink({user_seq:this.$route.params.userSeq}, res=>{
        this.links = res.data.object
      }, err=>{})

      PortfolioApi.projectDevSetting({user_seq:this.$route.params.userSeq}, res=>{
        this.devStacks = res.data.object
      }, err=>{})
      if (this.$route.params.userSeq == cookie.cookieSeq()) {
        this.sameUser = true
      }
    }
  }
</script>

<style lang="scss" scoped>
.portfolio-warpper {
  width: 700px;
  min-height: 100%;
  margin: 0 auto;
  
}
.introduction {
  margin-top: 50px;
  width: 100%;
  min-height: 100px;
  display: inline-block;
}
.linkname {
  margin: 10px;
  padding: 5px 10px 5px 13px;
  border-right: 3px solid rgb(5, 59, 11);
  border-radius: 30px;
  box-shadow: 1px 1px 3px #ccc;
  font-weight: 600;
}
.stacks {
  display: inline-block;
  margin: 5px;
  padding: 5px 10px;
  color: #2e2e2ef1;
  font-weight: 600;
  border: 3px solid #ffffff;
  border-radius: 20px 0 20px 0;
  // box-shadow: 2px 2px 4px 2px #b1b1b12f, -2px -2px 4px 2px rgba(255, 255, 255, 0.521);
}
.stacks:hover {
  // background-color: rgb(158, 6, 6);
  border-bottom: 3px solid rgb(177, 27, 27);
  border-right: 3px solid rgb(177, 27, 27);
  // color: #f6f5f0;
}
.projects {
  width: 90%;
  margin: 0 auto;
}
.new-project {
  font-size: 2em;
  text-align: right;
  margin: 2%;
}
.project {
  width: 100%;
  padding: 20px 30px;
}
.project p {
  font-family: 'Gothic A1', sans-serif !important;
}
.project > a:hover {
  font-size: 1em;
}
.project-edit {
  float: right;
}
.project-edit button {
  margin: 5px 10px;
}
.project-edit button:not(.trash):hover {
  color: rgb(5, 59, 11);
  font-size: 1.05em;

}
.project-edit .trash:hover {
  color:rgb(177, 27, 27);
  font-size: 1.05em;

}
@mixin paper-clip($height: 70px, $color: #000, $angle: 0deg) {
  $width: round($height * 43 / 100);
  $hwidth: round($width / 2);
  border: 2px solid $color;
  border-bottom: 0;
  height: $height;
  width: $width;
  position: relative;
  border-radius: $hwidth $hwidth 0 0;
  display: inline-block;
  z-index: 2;
  @if $angle == '0deg' {
  } @else {
      transform: rotate($angle);
      outline: 1px solid transparent; 
      -webkit-backface-visibility: hidden;
      backface-visibility: hidden;
  }
  &::before, &::after {
    position: absolute;
    content: '';
    border: 2px solid $color;
  }
  &::before {
    border-bottom: 0;
    width: $width - 8;
    left: 2px;
    top: $width;
    height: round($height * 0.64);
    border-radius: $hwidth $hwidth 0 0;
  }
  &::after {
    border-top: 0;
    width: $width - 4;
    height: round($height * 43 / 100);
    top: $height;
    left: -2px;
    border-radius: 0 0 $hwidth $hwidth;  
  }
}

@mixin sticky($width) {
  box-sizing: border-box;
  width: $width;
  height: $width;
  background: #424242; /* Old browsers */
  background: -moz-linear-gradient(-45deg, #e7e4d2 81%, #e7e4d2 82%, #e7e4d2 82%, #faf7e9 100%); /* FF3.6+ */
  background: -webkit-gradient(linear, left top, right bottom, color-stop(81%,#e7e4d2), color-stop(82%,#e7e4d2), color-stop(82%,#e7e4d2), color-stop(100%,#faf7e9)); /* Chrome,Safari4+ */
  background: -webkit-linear-gradient(-45deg, #e7e4d2 81%,#e7e4d2 82%,#e7e4d2 82%,#faf7e9 100%); /* Chrome10+,Safari5.1+ */
  background: -o-linear-gradient(-45deg, #e7e4d2 81%,#e7e4d2 82%,#e7e4d2 82%,#faf7e9 100%); /* Opera 11.10+ */
  background: -ms-linear-gradient(-45deg, #e7e4d2 81%,#e7e4d2 82%,#e7e4d2 82%,#faf7e9 100%); /* IE10+ */
  background: linear-gradient(135deg, #e7e4d2 81%,#e7e4d2 82%,#e7e4d2 82%,#faf7e9 100%); /* W3C */
  filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e7e4d2', endColorstr='#faf7e9',GradientType=1 ); /* IE6-9 fallback on horizontal gradient */
  border:1px solid #E8E8E8;  
  //border-top: 60px solid #fdfd86;   
  padding-top:35px;   
  position: relative;
  margin: 25px 0px;
  display: inline-block;
  border-bottom-right-radius: 60px 5px;
  &::before {
    position: absolute;
    top: 0;
    left: 0px;
    height: 30px;
    width: 100%;
    border-radius: 5px 5px 0 0;
    background-color: #ddb327;
    background: -moz-linear-gradient(-45deg,#ebd280 0%, #ddb327 10%,#dfb016 90%,#ce9a0a 100%); /* FF3.6+ */
    background: -webkit-gradient(linear, left bottom, right top, color-stop(0%,#ebd280), color-stop(10%,#ddb327), color-stop(90%,#dfb016), color-stop(100%,#ce9a0a)); /* Chrome,Safari4+ */
    background: -webkit-linear-gradient(-45deg,#ebd280 0%, #ddb327 10%,#dfb016 90%,#ce9a0a 100%); /* Chrome10+,Safari5.1+ */
    background: -o-linear-gradient(-45deg,#ebd280 0%, #ddb327 10%,#dfb016 90%,#ce9a0a 100%); /* Opera 11.10+ */
    background: -ms-linear-gradient(-45deg,#ebd280 0%, #ddb327 10%,#dfb016 90%,#ce9a0a 100%); /* IE10+ */
    background: linear-gradient(-45deg,rgb(230, 84, 84) 0%, rgb(187, 17, 17) 5%,rgb(138, 9, 9) 95%,rgb(90, 7, 7) 100%);
    box-shadow: 0px 2px 3px 2px rgb(114, 102, 99);
    content: '';
  }
  &::after {
    z-index: -1;
    position: absolute;
    content: "";
    bottom: 15px;
    right: 3px;
    left: auto;
    width: 50%;
    top: 80%;
    max-width: $width;
    background: #777;
    -webkit-box-shadow: 0 15px 10px #777;
    -moz-box-shadow: 0 15px 10px #777;
    box-shadow: 0 15px 10px #777;
    -webkit-transform: rotate(3deg);
    -moz-transform: rotate(3deg);
    -o-transform: rotate(3deg);
    -ms-transform: rotate(3deg);
    transform: rotate(3deg);
  }
  
}

.postit {
  @include sticky(100%); 
}

.clip {
  position: absolute;
  top:-30px;
  left: 20px;
  @include paper-clip(60px, rgb(128, 128, 128), 210deg);
  transition: 2s;
}

@keyframes changedeg {
  0% {
    transform: rotate(210deg);
  }
  50% {
    transform: rotate(200deg);
  }
  100% {
    transform: rotate(210deg);
  }
}

.postit:hover .clip {
  animation-duration: 2s;
  animation-name: changedeg;
  animation-iteration-count: infinite;
  transform-origin: 50% 50%;
}
</style>