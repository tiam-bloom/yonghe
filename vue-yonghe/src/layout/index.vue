<template>
  <div class="common-layout">
    <el-container>
      <el-aside>
        <Aside />
      </el-aside>
      <el-container>
        <el-header>
          <Header />
        </el-header>
        <el-main>
          <Main />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup lang='ts'>
import Header from './Header.vue';
import Aside from './Aside.vue';
import Main from './Main/index.vue'

</script>

<style lang='less' scoped>
.common-layout,.el-container {
  height: 100%;
}
.el-header {
  background-color: #008F7A;
  height: auto;
}

.el-aside {
  width: auto;

}
</style>