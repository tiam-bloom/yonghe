import { createApp } from 'vue'
import App from './App.vue'
// Element plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import 'element-plus/theme-chalk/dark/css-vars.css'
// 全局样式
import '@/assets/css/reset.css'
import '@/assets/css/style.css'
// router
import router from './router'

const app = createApp(App)
app
    .use(ElementPlus)
    .use(router)
    .mount('#app')

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}