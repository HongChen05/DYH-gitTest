<template>
  <div class="page">
    <!-- 页面头部：标题 + 搜索栏 -->
    <header class="head">
      <h1 class="title-all">全部专题</h1>
      <p v-if="totalText" class="sub">{{ totalText }}</p>
      <div class="toolbar">
        <el-input
          v-model="searchKeyword"
          class="search"
          clearable
          placeholder="关键词，如：过年"
          @keyup.enter="onSearch"
        />
        <el-button type="primary" @click="onSearch">搜索</el-button>
      </div>
    </header>

    <!-- 骨架屏 + 列表区域 -->
    <el-skeleton :loading="loading" animated :count="3">
      <template #template>
        <div class="sk">
          <el-skeleton-item variant="image" style="width: 200px; height: 120px" />
          <div style="flex: 1; padding-left: 20px">
            <el-skeleton-item variant="h3" style="width: 50%" />
            <el-skeleton-item variant="text" style="margin-top: 16px" />
          </div>
        </div>
      </template>

      <template #default>
        <ul class="list">
          <li
            v-for="item in list"
            :key="item.id"
            class="card"
            @click="goToDetail(item.id)"
          >
            <div class="card-banner">
              <img :src="item.banner" alt="封面" />
            </div>
            <div class="card-content">
              <h2 class="card-title">{{ item.title }}</h2>
              <p class="card-intro">{{ item.introduction }}</p>
              <div class="card-footer">
                <span>{{ formatTime(item.updated) }} 更新</span>
                <span>{{ formatNumber(item.viewCount) }} 浏览 · {{ item.followersCount }} 关注</span>
                <el-button size="small" type="primary" @click.stop="goToDetail(item.id)">
                  查看详情
                </el-button>
              </div>
            </div>
          </li>
        </ul>
        <el-empty v-if="!loading && list.length === 0" description="暂无数据" />
      </template>
    </el-skeleton>

    <!-- 分页 -->
    <footer v-if="total > 0" class="pager">
      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :total="total"
        :page-sizes="[10,20,50]"
        layout="total, sizes, prev, pager, next"
        background
        @current-change="loadData"
        @size-change="onSizeChange"
      />
    </footer>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'

const router = useRouter()

interface SpecialItem {
  id: string
  title: string
  banner: string
  introduction: string
  followersCount: number
  viewCount: number
  updated: number
}

const list = ref<SpecialItem[]>([])
const loading = ref(false)
const searchKeyword = ref('')
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)

const totalText = computed(() =>
  total.value > 0 ? `共有 ${total.value.toLocaleString('zh-CN')} 个专题` : ''
)

const formatTime = (ts: number) => {
  const d = new Date(ts * 1000)
  return `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, '0')}-${String(d.getDate()).padStart(2, '0')}`
}

const formatNumber = (num: number) => {
  if (num >= 10000) return (num / 10000).toFixed(1) + '万'
  return num.toString()
}

async function loadData() {
  loading.value = true
  try {
    const res = await axios.get('/api/v1/special/page', {
      params: {
        pageNum: currentPage.value,
        pageSize: pageSize.value,
        title: searchKeyword.value
      }
    })
    if (res.data.code === 200) {
      list.value = res.data.data.records
      total.value = res.data.data.total
    }
  } catch (e) {
    ElMessage.error('加载失败')
  } finally {
    loading.value = false
  }
}

function onSearch() {
  currentPage.value = 1
  loadData()
}

function onSizeChange(s: number) {
  pageSize.value = s
  currentPage.value = 1
  loadData()
}

function goToDetail(id: string) {
  router.push(`/special/detail/${id}`)
}

onMounted(loadData)
</script>

<style scoped>
.page {
  max-width: 1000px;
  margin: 0 auto;
  padding: 24px 20px 48px;
  box-sizing: border-box;
}
.head {
  margin-bottom: 28px;
}
.title-all {
  margin: 0 0 8px;
  font-size: 26px;
  font-weight: 600;
  color: #1a1a1a;
}
.sub {
  margin: 0 0 20px;
  font-size: 14px;
  color: #8590a6;
}
.toolbar {
  display: flex;
  gap: 12px;
  flex-wrap: wrap;
}
.search {
  width: min(420px, 100%);
}
.list {
  margin: 0;
  padding: 0;
  list-style: none;
}
.sk {
  display: flex;
  padding: 20px;
  margin-bottom: 12px;
  background: #fff;
  border: 1px solid #f0f0f0;
  border-radius: 4px;
}
.pager {
  display: flex;
  justify-content: center;
  margin-top: 28px;
}

.card {
  display: flex;
  gap: 16px;
  padding: 16px;
  margin-bottom: 12px;
  background: #fff;
  border: 1px solid #f0f0f0;
  border-radius: 8px;
  cursor: pointer;
  transition: box-shadow 0.2s;
}
.card:hover {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
}
.card-banner {
  width: 160px;
  height: 100px;
  flex-shrink: 0;
}
.card-banner img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 6px;
}
.card-content {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.card-title {
  margin: 0 0 8px;
  font-size: 18px;
  font-weight: 600;
  color: #1a1a1a;
}
.card-intro {
  margin: 0 0 12px;
  font-size: 14px;
  color: #666;
  line-height: 1.5;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
.card-footer {
  display: flex;
  align-items: center;
  gap: 16px;
  font-size: 13px;
  color: #999;
}
.card-footer button {
  margin-left: auto;
  border-radius: 20px;
}
</style>