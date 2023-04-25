<template>
    <h1>订单管理</h1>
    <div class="banner">
        <div>
            <el-button type="primary" :icon="CirclePlus" @click="handleAdd">新增订单</el-button>
            <el-button type="success" :icon="Refresh" @click="Refresh1">刷新</el-button>
        </div>
        <el-form :inline="true" :model="searchForm" class="demo-form-inline">
            <el-form-item >
                <el-select v-model="searchForm.door_id" clearable placeholder="请选择所属门店" style="width: 180px"
                    @change="handleSearch">
                    <el-option v-for="item in doorNameOptions" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
            </el-form-item>
            <el-form-item >
                <el-select v-model="searchForm.order_type" clearable placeholder="请选择订单类型" style="width: 180px"
                    @change="handleSearch">
                    <el-option v-for="item in orderTypeOptions" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
            </el-form-item>

            <el-form-item >
                <el-select v-model="searchForm.pay_type" clearable placeholder="请选择支付方式" style="width: 180px"
                    @change="handleSearch">
                    <el-option v-for="item in payTypeOptions" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-input v-model="searchForm.keywords" placeholder="请输入关键词" class="input-with-select"
                    @keyup.enter.native="handleSearch">
                    <template #prepend>
                        <el-select v-model="searchForm.field" placeholder="筛选" style="width: 100px" clearable>
                            <el-option label="订单号" value="order_no" />
                            <el-option label="人数" value="pnum" />
                            <el-option label="收银员" value="cashier" />
                            <el-option label="金额" value="price" />
                        </el-select>
                    </template>
                    <template #append>
                        <el-button :icon="Search" @click="handleSearch" />
                    </template>
                </el-input>
            </el-form-item>
        </el-form>
    </div>
    <el-table v-loading="loading" :data="orders" style="width: 100%" stripe table-layout="fixed" height="660">
        <el-table-column type="index" width="50" />
        <el-table-column prop="door.name" label="所属门店" width="200" />
        <el-table-column prop="orderNo" label="订单号" />
        <el-table-column prop="orderType" label="类型" width="80" />
        <el-table-column prop="pnum" label="人数(位)" width="80" />
        <el-table-column prop="cashier" label="收银员" />
        <el-table-column prop="orderTime" label="下单时间" :formatter="dateFormat" width="200" />
        <el-table-column prop="payTime" label="结账时间" :formatter="dateFormat" width="200" />
        <el-table-column prop="payType" label="支付方式" />
        <el-table-column prop="price" label="金额CNY" :formatter="priceFormat" />
        <el-table-column label="操作" width="200">
            <template #default="scope: any">
                <el-button size="small" @click=" handleEdit(scope.$index, scope.row) " :icon=" Edit ">修改</el-button>
                <el-popconfirm title="是故意的还是不小心的?" confirm-button-text="故意的" cancel-button-text="不小心的" width="200"
                    @confirm=" handleDelete(scope.$index, scope.row) " @cancel=" hint ">
                    <template #reference>
                        <el-button size="small" type="danger" :icon=" Delete ">删除</el-button>
                    </template>
                </el-popconfirm>
            </template>
        </el-table-column>
    </el-table>
    <div class="page">
        <el-pagination 
        background 
        layout="total, prev, pager, next, jumper" 
        :total="page.total" 
        :current-page="page.currentPage"
        :page-size="page.pageSize"
        @current-change="getOrders"/>
    </div>
    
    <OrderDialog 
    ref="orderDialog" 
    :title=" title " 
    :order=" order " 
    :doorNameOptions=" 
    doorNameOptions "
    @getOrders=" getOrders " />
</template>

<script setup lang='ts'>
import { ref, reactive, onMounted, toRaw } from 'vue'
import type { Ref } from 'vue'
import { Search, Delete, Edit, Refresh, CirclePlus } from '@element-plus/icons-vue'
import OrderDialog from './OrderDialog.vue';
import http from '@/utils/http'
import { ElMessage } from 'element-plus';
import { dateFormat, priceFormat } from '@/utils/format'
import { payTypeOptions, orderTypeOptions } from '@/assets/js/constant'
import type { Order, Door } from '@/assets/js/constant'

const loading = ref(true);

const orderDialog = ref();
/* 查 ******************************/
const searchForm = reactive({
    door_id: null,
    order_type: '',
    pay_type: '',
    keywords: '',
    field: ''
})
const page = reactive({
    currentPage: 1,
    pageSize: 15,
    total: 0
})
// 处理搜索逻辑 
function handleSearch() {
    // 拷贝一份数据, 避免多次搜索时, 会留存上一次的搜索条件
    const searchParams: {
        door_id: number | null,
        order_type: string,
        pay_type: string,
        keywords?: string,
        field?: string
    } = { ...toRaw(searchForm) };
    // 整理表单可用信息
    if (searchForm.field) searchParams[searchForm.field] = searchForm.keywords;
    delete searchParams.keywords;
    delete searchParams.field;
    for (const key in searchParams) {
        if (searchParams[key] === '') {
            delete searchParams[key];
        }
    }
    http({
        url: '/order/conditionQuery',
        method: 'get',
        params: searchParams
    }).then(res => {
        orders.value = res.data.data;
    })
}

const orders = ref<Order[]>()

const doorNameOptions: Ref<Door[]> = ref<Door[]>([])
function Refresh1() {
    getOrders();
    ElMessage.success("刷新成功");
}

function getOrders(curPage:number=1) {
    page.currentPage = curPage;
    http.get('/order/pageOrders',{
        params:{
            currentPage:curPage,
            pageSize:page.pageSize
        }
    }).then(res => {
        orders.value = res.data.data.list;
        page.total = res.data.data.total;
    }).catch(err => {
        ElMessage.error(`接口异常：${err}`);
    }).finally(() => {
        loading.value = false;
    })
}

function getDoors() {
    http.get('/door/listDoors').then(res => {
        doorNameOptions.value = res.data.data;
    })
}

onMounted(() => {
    getOrders();
    getDoors();
})
/* 删除 ******************************/
function handleDelete(index: number, row: Order) {
    http.delete(`/order/deleteOrderById/${row.id}`).then(res => {
        if (res.data.code === 200) {
            // 可能为空, 可选链?.  https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Operators/Optional_chaining
            orders.value?.splice(index, 1);
            ElMessage.success("删除成功");
        } else {
            ElMessage.error(res.data.msg);
        }
    }).catch(err => {
        ElMessage.error(`接口异常：${err}`);
    })
}

function hint() {
    ElMessage.info("不小心的");
}
/* 增改 ******************************/
const title = ref('');
// 创建一个Order类型的空对象 https://www.delftstack.com/zh/howto/typescript/typescript-create-object-from-interface/
const order: Ref<Order> = ref<Order>({} as Order);

function handleEdit(index: number, row: Order) {
    title.value = "修改订单";
    order.value = row;
    console.log(order);
    orderDialog.value.showDialog();
}

function handleAdd() {
    title.value = "新增订单";
    // 创建一个Order类型的空对象
    order.value = {} as Order;
    orderDialog.value.showDialog();
}
</script>

<style lang='less' scoped>
h1 {
    text-align: center;
    font-size: 26px;
    margin: 40px 0;
}

.banner {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;

    .input-with-select {
        width: 400px;
    }
}
.page {
    display: flex;
    justify-content: center;
    margin-top: 20px;
}
</style>