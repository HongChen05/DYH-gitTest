<template>
  <div class="page">
    <!-- 返回列表按钮 -->
    <div class="back-bar">
      <el-button @click="goBack" type="primary" plain>
        ← 返回列表
      </el-button>
    </div>

    <!-- 骨架屏加载 -->
    <el-skeleton :loading="loading" animated>
      <template #default>
        <div class="detail-container">
          <!-- 封面图 -->
          <div class="banner">
            <img :src="detail.banner" alt="专题封面" />
          </div>

          <!-- 标题栏 -->
          <div class="title-bar">
            <h1 class="title">{{ detail.title }}</h1>
            <div class="stats">
              <span>👥 {{ formatNumber(detail.followersCount) }} 关注</span>
              <span>👁️ {{ formatNumber(detail.viewCount) }} 浏览</span>
              <span>📅 {{ formatTime(detail.updated) }} 更新</span>
            </div>
            <el-button type="primary" class="follow-btn">关注专题</el-button>
          </div>

          <!-- 详情内容 -->
          <div class="content">
            <p class="intro">{{ detail.introduction }}</p>
          </div>
        </div>
      </template>
    </el-skeleton>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'
import { ElMessage } from 'element-plus'

// 1. 定义详情页数据类型（和后端接口完全对应）
interface SpecialDetail {
  id: string
  title: string
  banner: string
  introduction: string
  isFollowing: string
  followersCount: number
  viewCount: number
  updated: number
}

// 2. 路由相关
const route = useRoute()
const router = useRouter()
const id = computed(() => route.params.id as string) // 从url获取id

// 3. 响应式数据
const loading = ref(false)
const detail = ref<SpecialDetail>({
  id: '',
  title: '',
  banner: '',
  introduction: '',
  isFollowing: 'false',
  followersCount: 0,
  viewCount: 0,
  updated: 0
})

// 4. 工具函数（和列表页保持一致）
// 时间格式化
const formatTime = (ts: number) => {
  const d = new Date(ts * 1000)
  return `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, '0')}-${String(d.getDate()).padStart(2, '0')}`
}
// 数字格式化（万为单位）
const formatNumber = (num: number) => {
  if (num >= 10000) return (num / 10000).toFixed(1) + '万'
  return num.toString()
}
// 返回列表
const goBack = () => {
  router.back()
}

// 5. 加载详情数据（对应后端 /api/v1/special/detail 接口）
async function loadDetail() {
  if (!id.value) {
    ElMessage.error('参数错误')
    goBack()
    return
  }

  loading.value = true
  try {
    // 完全匹配你文档里的接口：/api/v1/special/detail?id=xxx
    const res = await axios.get('/api/v1/special/detail', {
      params: { id: id.value }
    })

    if (res.data.code === 200) {
      detail.value = res.data.data
    } else {
      ElMessage.error(res.data.message || '加载失败')
      goBack()
    }
  } catch (e) {
    console.error(e)
    ElMessage.error('网络错误，请检查后端服务')
    goBack()
  } finally {
    loading.value = false
  }
}

// 6. 页面挂载时加载数据
onMounted(() => {
  loadDetail()
})
</script>

<style scoped>
/* 页面容器 */
.page {
  max-width: 1000px;
  margin: 0 auto;
  padding: 24px 20px 48px;
  box-sizing: border-box;
  background-color: #f7f8fa;
  min-height: 100vh;
}

/* 返回按钮栏 */
.back-bar {
  margin-bottom: 20px;
}

/* 详情容器 */
.detail-container {
  background: #fff;
  border-radius: 8px;
  padding: 24px;
  box-shadow: 0 1px 3px rgba(0,0,0,0.05);
}

/* 封面图 */
.banner {
  width: 100%;
  height: 300px;
  border-radius: 8px;
  overflow: hidden;
  margin-bottom: 24px;
}
.banner img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

/* 标题栏 */
.title-bar {
  margin-bottom: 24px;
  padding-bottom: 16px;
  border-bottom: 1px solid #f0f0f0;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  gap: 16px;
}
.title {
  margin: 0;
  font-size: 28px;
  font-weight: 600;
  color: #1a1a1a;
  flex: 1;
  min-width: 200px;
}
.stats {
  font-size: 14px;
  color: #8590a6;
  display: flex;
  gap: 20px;
}
.follow-btn {
  border-radius: 20px;
  padding: 6px 20px;
}

/* 详情内容 */
.content {
  line-height: 1.8;
  font-size: 16px;
  color: #333;
}
.intro {
  white-space: pre-wrap;
  word-break: break-word;
}
</style>