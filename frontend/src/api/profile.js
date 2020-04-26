import request from '@/utils/request'

/**
 * 功能描述: 获取个人信息
 *
 * @param username 用户名
 * @authnr 刘武祥
 * @date 2020/4/20 0020 17:42
 */
export function info(username) {
  return request({
    url: '/profile/info/' + username,
    method: 'get'
  })
}

/**
 * 功能描述: 获取个人信息
 *
 * @param data
 * @authnr 刘武祥
 * @date 2020/4/20 0020 17:42
 */
export function update(data) {
  return request({
    url: '/profile/update/',
    method: 'post',
    data
  })
}

/**
 * 功能描述: 更新密码
 *
 * @param data
 * @auther 刘武祥
 * @date 2020/4/22 0022 10:14
 */
export function modifyPassword(data) {
  return request({
    url: '/profile/modify/password',
    method: 'post',
    data
  })
}

/**
 * 功能描述: 更新头像
 *
 * @param data
 * @auther 刘武祥
 * @date 2020/4/22 0022 10:14
 */
export function modifyIcon(data) {
  return request({
    url: '/profile/modify/icon',
    method: 'post',
    data
  })
}
