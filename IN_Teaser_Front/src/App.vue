<template>
    <div id="app">
        <div class="layout">
            <app-header :emitData="emitData" :isLogin="isLogin" v-if="!$route.meta.hideNavigation"></app-header>
            <router-view @DM="DM" :key="$route.fullPath"></router-view>
        </div>
        <app-footer v-if="['Idconfirm'].indexOf($route.name)"></app-footer>      
    </div>
</template>
<script>
    import Footer from '@/views/main/myFooter'
    import Header from '@/views/main/myHeader'

    export default {
        name: 'app',
        data: () => {
            return {   
                emitData: {opponent_seq:'', opponent_nickname:''}
            }
        },
        methods: {
            DM(dmData) {
                this.emitData = dmData
            }
        },
        components: {
            appHeader: Header,
            appFooter: Footer
        },
        computed: {
            isLogin() {
                return this.$store.state.isLogin
            },
        }
    }
</script>
<style>


</style>
