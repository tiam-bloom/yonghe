<template>
  <el-dialog v-model="DialogVisible" :title="title" width="600">
    <el-form :model="door">
      <el-form-item label="店名" :label-width="50">
        <el-input v-model="door.name" autocomplete="on" placeholder="请输入店名" />
      </el-form-item>
      <el-form-item label="电话" :label-width="50">
        <el-input v-model="door.tel" autocomplete="on" placeholder="请输入电话" />
      </el-form-item>
      <el-form-item label="地址" :label-width="50">
        <el-input v-model="door.addr" autocomplete="on" placeholder="请输入地址" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="DialogVisible = false">返回</el-button>
        <el-button type="primary" @click="handleConfirm">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script lang="ts" setup>
import http from '@/utils/http'
import { ref, toRaw } from 'vue'
import { ElMessage } from 'element-plus'

interface Door {
  id?: number
  name: string
  tel: string
  addr: string
}
const prop = defineProps<{
  title: string,
  door: Door
}>()

const emit = defineEmits<{
  (e: 'getDoors'): void
}>()

const DialogVisible = ref(false)


function handleConfirm() {
  // 获取原生对象
  const door = toRaw(prop.door);
  if (prop.title == '新增门店') {
    addDoor(door)
  } else {
    updateDoor(door)
  }
  DialogVisible.value = false
}

function addDoor(door: Door) {
  http.post('/door/insertDoor', door).then(res => {
    if (res.data.code == 200) {
      emit('getDoors');
      ElMessage.success('新增成功')
    } else {
      ElMessage.error('新增失败')
    }
  })
}

function updateDoor(door: Door) {
  http.put('/door/updateDoor', door).then(res => {
    if (res.data.code == 200) {
      emit('getDoors');
      ElMessage.success('修改成功')
    } else {
      ElMessage.error('修改失败')
    }
  })
}

function showDialog() {
  DialogVisible.value = true
}
// 暴露给父组件
defineExpose({
  showDialog
})
</script>
<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
  