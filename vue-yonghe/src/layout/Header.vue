<template>
    <!-- <h1>永和大王门店管理系统</h1> -->
    <div class="header">
        <el-breadcrumb separator="/" class="breadcrumb">
            <el-breadcrumb-item :to="{ name: 'Index' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>{{ $route.meta.title }}</el-breadcrumb-item>
        </el-breadcrumb>

        <div class="right">
            <el-switch v-model="isDark"  style="margin-right: 24px" inline-prompt :active-icon="Moon"
                :inactive-icon="Sunny" />
            <el-avatar src="src/assets/images/head.jpg" object-fit="cover" />
            <el-dropdown @command="handleCommand">
                <span class="el-dropdown-link">
                    梦中千秋<el-icon class="el-icon--right"><arrow-down /></el-icon>
                </span>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item command="personal" :icon="User">个人中心</el-dropdown-item>
                        <el-dropdown-item command="github" :icon="TopRight">Github</el-dropdown-item>
                        <el-dropdown-item command="blog" :icon="Promotion">Blog</el-dropdown-item>
                        <el-dropdown-item command="nav" :icon="Link">Navigate</el-dropdown-item>
                        <el-dropdown-item command="logout" :icon="CloseBold" divided>退出</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
    </div>
</template>

<script setup lang='ts'>
import {
    ArrowDown,
    User,
    Promotion,
    TopRight,
    Link,
    CloseBold,
    Sunny,
    Moon
} from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'
import { useDark } from '@vueuse/core'
const isDark = useDark()
const router = useRouter()
const handleCommand = (command: string | number | object) => {
    switch (command) {
        case 'personal':
            console.log('个人中心')
            break
        case 'github':
            window.open('https://github.com/tiam-bloom')
            break;
        case 'blog':
            window.open('https://yujing.fit/')
            break;
        case 'nav':
            window.open('https://go.code2022.top/')
            break;
        case 'logout':
            router.push({
                name: 'Login',
            })
            break;
    }
}
</script>

<style lang='less' scoped>
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 20px;
    color: #fff;
    height: 60px;
}

.el-breadcrumb__inner {
    color: #fff;

    .el-breadcrumb__item:last-child {
        color: #fff !important;
    }
}

@height: 60px;

.breadcrumb {
    line-height: @height;
    height: @height;
}

.right {
    display: flex;
    align-items: center;

    .el-dropdown-link {
        color: #fff;
        cursor: pointer;
        display: flex;
        align-items: center;

        .el-icon {
            margin-left: 5px;
        }
    }

    .el-dropdown {
        margin-left: 20px;
    }
}
</style>