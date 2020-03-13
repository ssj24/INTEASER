<template>
  <div class="user join">
    <div class="wrapCenter">
      <h1 class="center bigger">Error</h1>
      <p style="text-align: center; font-size: 1rem;">Something went wrong. Sorry for inconvinience.</p>
      <img src="../../assets/images/error.svg" alt="" class="center" style="width: 100%;">
      <button @click=back class="btn--text svgs"><i class="fas fa-arrow-left"></i></button>
    </div>
  </div>
</template>

<script>


export default {
  methods: {
    back() {
      this.$router.push({
        name: this.$route.params.route,
        params: this.$route.params
      })
    }
  }
};
</script>