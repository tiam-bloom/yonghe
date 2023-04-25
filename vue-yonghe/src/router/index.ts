import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";

import Login from '@/components/Login.vue'
import Layout from '@/layout/index.vue'

import { start, close } from '@/utils/nprogress'

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        redirect: { name: 'Login' }
    },
    {
        path: '/login',
        name: 'Login',
        component: Login,
    },
    {
        path: '/index',
        name: 'Index',
        component: Layout,
        redirect: { name: 'Welcome' },
        children: [
            {
                path: '',
                name: 'Welcome',
                component: () => import('@/layout/Main/Welcome.vue'),
                meta: {
                    title: '欢迎'
                }
            },
            {
                path: 'door',
                name: 'Door',
                component: () => import('@/layout/Main/Door/index.vue'),
                meta: {
                    title: '门店管理'
                }
            },
            {
                path: 'order',
                name: 'Order',
                component: () => import('@/layout/Main/Order/index.vue'),
                meta: {
                    title: '订单管理'
                }
            }
        ]
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
})
router.beforeEach((pre, next) => {
    start()
  })
  
  router.afterEach(() => {
    close()
  })
export default router;