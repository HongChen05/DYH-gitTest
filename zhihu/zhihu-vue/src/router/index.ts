import { createRouter, createWebHistory } from 'vue-router'
import SpecialListView from '../views/SpecialListView.vue'
import SpecialDetailView from '../views/SpecialDetailView.vue' // 新增

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', redirect: '/special' },
    { path: '/special', component: SpecialListView },
    // 新增详情页路由，带id参数
    { 
      path: '/special/detail/:id', 
      name: 'special-detail',
      component: SpecialDetailView 
    }
  ]
})

export default router