<template>
    <el-dialog v-model="DialogVisible" :title="title" width="600">
        <el-form :model="order">
            <el-form-item label="所属门店" :label-width="100">
                <el-select v-model="order.doorId" placeholder="请选择一个门店">
                    <el-option v-for="item in doorNameOptions" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
            </el-form-item>
            <el-form-item label="订单编号" :label-width="100">
                <el-input v-model="order.orderNo" autocomplete="on" placeholder="请输入订单编号" />
            </el-form-item>
            <el-form-item label="订单类型" :label-width="100">
                <el-select v-model="order.orderType" placeholder="请选择订单类型">
                    <el-option v-for="item in orderTypeOptions" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
            </el-form-item>
            <el-form-item label="用餐人数" :label-width="100">
                <el-input-number v-model="order.pnum" :min="1" />
            </el-form-item>
            <el-form-item label="订单时间" :label-width="100" v-if="title === '修改订单'">
                <el-date-picker v-model="order.orderTime" type="datetime" placeholder="请选择订单时间"
                    :default-time="defaultTime" />
            </el-form-item>
            <el-form-item label="支付时间" :label-width="100" v-if="title === '修改订单'">
                <el-date-picker v-model="order.payTime" type="datetime" placeholder="请选择支付时间" :default-time="defaultTime" />
            </el-form-item>
            <el-form-item label="收银员" :label-width="100">
                <el-input v-model="order.cashier" autocomplete="on" placeholder="请输入收银员姓名" />
            </el-form-item>
            <el-form-item label="支付方式" :label-width="100">
                <el-select v-model="order.payType" placeholder="请输入支付方式">
                    <el-option v-for="item in payTypeOptions" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
            </el-form-item>
            <el-form-item label="支付金额" :label-width="100">
                <el-input-number v-model="order.price" :min="0" :precision="2" />
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

<script setup lang='ts'>
import http from '@/utils/http';
import { ElMessage } from 'element-plus';
import moment from 'moment';
import { ref, toRaw, Ref } from 'vue'
import { Order, Door, payTypeOptions, orderTypeOptions } from '@/assets/js/constant'
// 对话框显隐变量
const DialogVisible:Ref<boolean> = ref(false)
// 默认时间, 现在
const defaultTime = new Date();

const prop = defineProps<{
    title: string,
    order: Order,
    doorNameOptions: Door[],
}>()
const emit = defineEmits<{
    (e: 'getOrders'): void
}>()

function handleConfirm() {
    // 获取原生对象
    const order = toRaw(prop.order);
    if (prop.title == '新增订单') {
        addOrder(order)
    } else {
        updateOrder(order)
    }
    DialogVisible.value = false
}


function addOrder(order: Order) {
    delete order.id;
    delete order.door;
    delete order.orderTime;
    delete order.payTime;
    console.log('addOrder', order)
    http.post('/order/insertOrder', order).then(res => {
        if (res.data.code == 200) {
            emit('getOrders');
            ElMessage.success("添加成功")
        } else {
            ElMessage.error(res.data.msg)
        }
    })
}

function updateOrder(order: Order) {
    delete order.door;
    // 格式化时间
    order.orderTime = moment(order.orderTime).format('YYYY-MM-DD HH:mm:ss');
    order.payTime = moment(order.payTime).format('YYYY-MM-DD HH:mm:ss');
    console.log('updateOrder', order)
    http.put('/order/updateOrder', order).then(res => {
        if (res.data.code == 200) {
            emit('getOrders');
            ElMessage.success("修改成功")
        } else {
            ElMessage.error(res.data.msg)
        }
    }).catch(err => {
        ElMessage.error(err)
    })
}


function showDialog() {
    DialogVisible.value = true
}
defineExpose({
    showDialog
})
</script>

<style lang='less' scoped></style>