import axios from 'axios'

const request = axios.create({
  baseURL: 'http://localhost:8080', // 后端根地址，统一管理
  timeout: 5000
})

// 请求拦截器
request.interceptors.request.use(
  config => {
    // 可以在这里加token等
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  res => {
    // 统一处理响应数据
    if (res.data.code === 200) {
      return res.data
    } else {
      // 统一报错
      ElMessage.error(res.data.message || '请求失败')
      return Promise.reject(res.data)
    }
  },
  error => {
    ElMessage.error('网络错误，请检查后端服务')
    return Promise.reject(error)
  }
)

export default request