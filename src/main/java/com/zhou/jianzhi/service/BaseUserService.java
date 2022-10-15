package com.zhou.jianzhi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.jianzhi.entity.dto.BaseUserDTO;
import com.zhou.jianzhi.entity.po.BaseUser;
import com.zhou.jianzhi.entity.po.SysMenu;
import com.zhou.jianzhi.entity.po.SysRole;
import com.zhou.jianzhi.entity.vo.BaseUserVO;
import com.zhou.jianzhi.entity.vo.MenuNodeVO;
import com.zhou.jianzhi.entity.vo.WechatUserInfoVO;

import java.util.List;
import java.util.Map;


public interface BaseUserService extends IService<BaseUser> {
    /**
     * 根据用户名查询实体
     * @Param  username 用户名
     * @Return BaseUser 用户实体
     */
    BaseUser selectUserByName(String username);

    /**
     * 查询用户
     * @param user
     * @return
     */
    BaseUser selectOne(BaseUserDTO user);

    /**
     * 查询用户ById
     * @param id
     * @return
     */
    BaseUser selectById(Long id);

    /**
     * 查询用户角色
     *
     * @param userId 用户id
     * @return
     */
    List<SysRole> findRolesById(Long userId);

    /**
     * 根据用户角色查询用户的菜单
     * 菜单: menu+button
     * @param roles 用户的角色
     * @return
     */
    List<SysMenu> findMenuByRoles(List<SysRole> roles);

    /**
     * 加载菜单
     *
     * @return
     */
    List<MenuNodeVO> findMenu();


    /**
     * 用户列表zhou
     * @param baseUser
     * @return
     */
    IPage<BaseUser> getUserList(BaseUserDTO baseUser);

    /**
     * 用户列表
     * @param baseUser
     * @return
     */
    IPage<BaseUserVO> findUserList(BaseUserDTO baseUser);

    /**
     * 更新状态
     *
     * @param id
     * @param status
     */
    void updateStatus(Long id, Boolean status);

    /**
     * 添加用户
     * @param userDto
     */
    boolean add(BaseUserDTO userDto);

    /**
     * 更新用户
     * @param userDto
     */
    boolean update(BaseUserDTO userDto);

    /**
     * 编辑用户 （根据ID查询）
     *
     * @param id
     * @return
     */
    BaseUserVO edit(Long id);

    /**
     * 已拥有的角色ids
     *
     * @param id 用户id
     * @return
     */
    List<Long> roles(Long id);

    /**
     * 分配角色
     *
     * @param id 用户ID
     * @param rids 角色ID数组
     */
    void assignRoles(Long id, Long[] rids);

    /**
     * 所有用户
     *
     * @return
     */
    List<BaseUser> findAll();

    /**
     * 用户登入
     *
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 用户详情
     *
     * @return
     */
    BaseUserVO info() throws Exception;
    /**
     * 删除用户
     * @param id
     */
    void deleteById(Long id);
    /**
     * 更新用户
     *
     * @param id
     * @param userDto
     */
    void update(Long id, BaseUserDTO userDto);

    IPage<BaseUserVO> test();


    Map<String, Object> weChatLogin(String phone, WechatUserInfoVO wechatUserInfoVO);

    String AppRefreshToken(String empId);

    boolean register(BaseUserDTO dbUser);

    BaseUserVO getuserImag(String username) throws Exception;

}

