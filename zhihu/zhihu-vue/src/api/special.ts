import request from '@/utils/request'

// 获取专题列表（带搜索+分页）
export function getSpecialList(params: {
  page: number
  size: number
  title?: string
}) {
  return request({
    url: '/special/page',
    method: 'get',
    params
  })
}