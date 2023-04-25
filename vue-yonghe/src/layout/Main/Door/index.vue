<template>
    <h1>门店管理</h1>
    <div class="banner">
        <el-button type="primary" @click="handleAdd">新增门店</el-button>
        <el-input v-model="keywords" placeholder="请输入关键词" class="input-with-select" @keyup.enter.native="handleSearch"
            clearable>
            <template #prepend>
                <el-select v-model="field" placeholder="筛选" style="width: 80px" name="field">
                    <el-option label="店名" value="name" />
                    <el-option label="电话" value="tel" />
                    <el-option label="地址" value="addr" />
                </el-select>
            </template>
            <template #append>
                <el-button :icon="Search" @click="handleSearch" />
            </template>
        </el-input>
    </div>
    <el-table v-loading="loading" :data="doors" style="width: 100%" stripe height="660">
        <el-table-column type="index" />
        <el-table-column prop="name" label="店名" width="300" />
        <el-table-column prop="tel" label="电话" width="200" />
        <el-table-column prop="addr" label="地址" />
        <el-table-column label="操作" width="200">
            <template #default="scope: any">
                <el-button size="small" @click=" handleEdit(scope.$index, scope.row) ">修改</el-button>
                <el-popconfirm width="220" confirm-button-text="废话" cancel-button-text="点错了" :icon=" InfoFilled "
                    icon-color="#626AEF" title="你确定要删除吗?" @confirm=" handleDelete(scope.$index, scope.row) "
                    @cancel=" hint ">
                    <template #reference>
                        <el-button size="small" type="danger">删除</el-button>
                    </template>
                </el-popconfirm>
            </template>
        </el-table-column>
    </el-table>
    <div class="page">
        <el-pagination 
        background 
        layout="prev, pager, next" 
        :total="page.total" 
        :current-page="page.currentPage"
        :page-size="page.pageSize"
        @current-change="getDoors"/>
    </div>
    <door-dialog 
    ref="dialog" 
    :title=" title " 
    :door=" door " 
    @getDoors=" getDoors " />
</template>

<script setup lang='ts'>
import { ref, reactive, onMounted } from 'vue'
import { Search, InfoFilled } from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import DoorDialog from './DoorDialog.vue';
import http from '@/utils/http';
function hint() {
    ElMessage.info('已取消删除');
}

const title = ref('');
const dialog = ref();
const page = reactive({
    currentPage: 1,
    pageSize: 15,
    total: 0
})

interface Door {
    id?: number,
    name: string
    tel: string
    addr: string
}
// Record<string,any>
let door: Door = reactive({
    name: '',
    tel: '',
    addr: ''
});
const doors = ref([])

onMounted(() => {
    getDoors();
});
const loading = ref(true);
function getDoors(curPage:number=1) {
    // http.get('/door/listDoors').then(res => {
    //     doors.value = res.data.data;
    // }).catch(err => {
    //     ElMessage.error("请检查接口" + err);
    // })
    page.currentPage = curPage;
    http.get('/door/pageDoors', {
        params: {
            currentPage: curPage,
            pageSize: page.pageSize,
        }
    }).then(res => {
        doors.value = res.data.data.list;
        page.total = res.data.data.total;
    }).catch(err => {
        ElMessage.error("请检查接口" + err);
    }).finally(() => {
        loading.value = false;
    })
}

const field = ref('');
const keywords = ref('');

function handleEdit(index: number, row: any) {
    title.value = '修改门店';
    door.id = row.id;
    door.name = row.name;
    door.tel = row.tel;
    door.addr = row.addr;
    dialog.value.showDialog();
}

const open = (index: number, id: number) => {
    ElMessageBox.confirm(
        '该门店下存在订单, 这将会删除其下所有订单,确定继续吗?',
        'Warning',
        {
            confirmButtonText: '我确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            // 先删除所有订单
            return http.delete(`/order/deleteOrdersByDoorId/${id}`)
        }).then((res: any) => {
            if (res.data.code !== 200) {
                ElMessage.error("删除门店失败");
                return new Promise(() => { })  // 返回一个永远不会resolve的promise, 中断promise链
            }
        }).then(() => {
            // 再删除门店
            deleteDoorById(index, id);
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '取消操作',
            })
        })
}
function deleteDoorById(index: number, id: number) {
    http.delete(`/door/deleteDoorById/${id}`).then(res => {
        if (res.data.code === 200) {
            doors.value.splice(index, 1);   // 删除数组中的元素(模拟即时刷新页面, 不在需要发送请求)
            ElMessage.success("删除成功");
        } else {
            ElMessage.error("删除失败");
        }
    })
}
function handleDelete(index: number, row: any) {
    // todo: 检查是否存在所属门店下的订单, 再予以提示是否 其下删除所有订单
    http.get(`/order/listOrdersByDoorId/${row.id}`).then(res => {
        if (res.data.data.length > 0) {
            open(index, row.id);
        } else {
            deleteDoorById(index, row.id);
        }
    })
}

function handleAdd() {
    title.value = '新增门店';
    // 遍历door对象，将值置为空
    for (const key in door) {
        door[key] = '';
    }
    dialog.value.showDialog();
}

function handleSearch() {
    const f = field.value.trim();
    const k = keywords.value;
    // 如果没有关键词，就重新获取所有门店
    if (!k) {
        getDoors();
        return;
    }
    // 筛选doors数组
    doors.value = doors.value.filter((item: Door) => {
        if (f) {
            return item[f].includes(k)
        } else {
            // 如果字段为空，就遍历所有字段
            for (const key in item) {
                if (key === 'id') continue;
                if (item[key].includes(k)) {
                    return true;
                }
            }
        }
    })
    if (doors.value.length === 0) {
        ElMessage.warning("没有找到相关门店");
        getDoors();
    }
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
    align-items: center;
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