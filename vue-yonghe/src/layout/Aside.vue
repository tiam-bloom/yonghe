<template>
  <el-menu router default-active="Index" class="el-menu-vertical-demo" :collapse="isCollapse" @open="handleOpen"
    @close="handleClose">
    <el-menu-item index="Index" :route="{ name: 'Index' }" class="logo">
      <img src="/fandian.svg" width="26" alt="">
      <template #title>永和大王</template>
    </el-menu-item>

    <el-menu-item index="door" :route="{ name: 'Door' }">
      <el-icon>
        <setting />
      </el-icon>
      <template #title>门店管理</template>
    </el-menu-item>

    <el-menu-item index="order" :route="{ name: 'Order' }">
      <el-icon>
        <document />
      </el-icon>
      <template #title>订单管理</template>
    </el-menu-item>

    <el-icon class="collapse" size="30" @click="isCollapse = !isCollapse">
      <Expand v-if="isCollapse" />
      <Fold v-else />
    </el-icon>
  </el-menu>
</template>

<script setup lang='ts'>
import { ref } from 'vue'
import {
  Document,
  Setting,
  Expand,
  Fold
} from '@element-plus/icons-vue'

const isCollapse = ref(false)
const handleOpen = (key: string, keyPath: string[]) => {
  // console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  // console.log(key, keyPath)
}
</script>

<style lang='less' scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.collapse {
  position: absolute;
  bottom: 0;
  left: 0;
  margin: 10px;
  cursor: pointer;
  color: rgba(0, 0, 0, 0.447);
}

.el-menu {
  height: 100%;
  color: white;
}

.logo {
  background-color: #027e89;
  height: 60px;
  font-family: "times new roman", times, serif;
  color: white;
  font-size: 26px;
  font-weight: bold;
  text-indent: 10px;
}


// 穿透样式
// :deep(.el-sub-menu__title) {
//   color: white;
// }
</style>