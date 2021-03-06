package com.zhou.jianzhi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.common.enums.ErrorCodeEnum;
import com.zhou.jianzhi.common.enums.UserStatusEnum;
import com.zhou.jianzhi.common.enums.UserTypeEnum;
import com.zhou.jianzhi.common.util.JWTUtils;
import com.zhou.jianzhi.common.util.MenuTreeBuilder;
import com.zhou.jianzhi.common.util.ModelIdGenreator;
import com.zhou.jianzhi.common.util.SHA256Util;
import com.zhou.jianzhi.concerter.MenuConverter;
import com.zhou.jianzhi.config.jwt.JWTToken;
import com.zhou.jianzhi.entity.base.ActiveUser;
import com.zhou.jianzhi.entity.dto.*;
import com.zhou.jianzhi.entity.po.*;
import com.zhou.jianzhi.entity.vo.BaseUserVO;
import com.zhou.jianzhi.entity.vo.MenuNodeVO;
import com.zhou.jianzhi.entity.vo.WechatUserInfoVO;
import com.zhou.jianzhi.exception.MyException;
import com.zhou.jianzhi.mapper.*;
import com.zhou.jianzhi.service.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements BaseUserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BaseUserMapper baseUserMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysRoleMenuMapper roleMenuMapper;
    @Autowired
    private SysMenuMapper menuMapper;

    @Autowired
    private BaseUserService baseUserService;

    @Autowired
    private JobSeekerMapper jobSeekerMapper;

    @Autowired
    private IResumeService resumeService;

    @Autowired
    private IHrInfoService hrInfoService;

    @Autowired
    private IJobSeekerService jobSeekerService;

    @Autowired
    private IAliyunOssService aliyunOssService;

    @Autowired
    private ICommonService commonService;

    @Override
    public BaseUser selectUserByName(String username) {
        return baseUserMapper.selectOne(new QueryWrapper<BaseUser>().eq("username", username));
    }

    @Override
    public BaseUser selectOne(BaseUserDTO user) {
        return null;
    }

    @Override
    public BaseUser selectById(Long id) {
        return baseUserMapper.selectById(id);
    }

    @Override
    public List<SysRole> findRolesById(Long userId) {
        BaseUser dbUser = baseUserMapper.selectById(userId);
        if (StringUtils.isEmpty(dbUser)) {
            throw new MyException("??????????????????");
        }
        List<SysRole> roles = new ArrayList<>();
        List<SysUserRole> userRoleList = userRoleMapper.selectList(new QueryWrapper<SysUserRole>().select("role_id,user_id").eq("user_id", userId));
        List<Long> rids = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userRoleList)) {
            for (SysUserRole userRole : userRoleList) {
                rids.add(userRole.getRoleId());
            }
            if (!CollectionUtils.isEmpty(rids)) {
                roles = roleMapper.selectBatchIds(rids);
            }
        }
        return roles;
    }


    @Override
    public List<SysMenu> findMenuByRoles(List<SysRole> roles) {
        List<SysMenu> menus = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roles)) {
            Set<Long> menuIds = new HashSet<>();//?????????????????????id
            List<SysRoleMenu> roleMenus;
            for (SysRole role : roles) {
                if(role.getStatus()==0){
                    continue;
                }
                //????????????ID????????????ID
                roleMenus = roleMenuMapper.selectList(new QueryWrapper<SysRoleMenu>().select("role_id", "menu_id").eq("role_id", role.getId()));
                if (!CollectionUtils.isEmpty(roleMenus)) {
                    for (SysRoleMenu roleMenu : roleMenus) {
                        menuIds.add(roleMenu.getMenuId());
                    }
                }
            }
            if (!CollectionUtils.isEmpty(menuIds)) {
                menus = menuMapper.selectBatchIds(menuIds);
            }
        }
        return menus;
    }

    @Override
    public List<MenuNodeVO> findMenu() {
        List<SysMenu> menus = null;
        List<MenuNodeVO> menuNodeVOS = new ArrayList<>();
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        if (activeUser.getUser().getType() == UserTypeEnum.SYSTEM_ADMIN.getTypeCode()) {
            //???????????????
            menus = menuMapper.selectList(new QueryWrapper<>());
        } else {
            //??????????????????
            menus = activeUser.getMenus();
        }
        if (!CollectionUtils.isEmpty(menus)) {
            menuNodeVOS = MenuConverter.converterToMenuNodeVO(menus);
        }
        //??????????????????
        return MenuTreeBuilder.build(menuNodeVOS);
    }


    @Override
    public IPage<BaseUser> getUserList(BaseUserDTO baseUser) {
        Page<BaseUser> page = new Page<>(baseUser.getPageCurrent(),baseUser.getPageSize());
        QueryWrapper<BaseUser> wrapper = new QueryWrapper<>();
        if(baseUser.getType() != null){
            wrapper.eq(!StrUtil.isBlank(baseUser.getType().toString()),"type", baseUser.getType());
        }
        Page<BaseUser> baseUserPage = baseUserMapper.selectPage(page, wrapper);

        return baseUserPage;
    }

    @Override
    public IPage<BaseUserVO> findUserList(BaseUserDTO baseUser) {
        Page<BaseUserVO> page = new Page<BaseUserVO>(baseUser.getPageCurrent(), baseUser.getPageSize());
        IPage<BaseUserVO> userVoPage = baseUserMapper.queryList(page, baseUser);
        logger.info("????????????{}", userVoPage.getRecords().toString());
        return userVoPage;
    }

    @Override
    public void updateStatus(Long id, Boolean status) {
        BaseUser dbUser = baseUserMapper.selectById(id);
        if (StringUtils.isEmpty(dbUser)) {
            throw new MyException("?????????????????????????????????");
        }
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        if (dbUser.getId().equals(activeUser.getUser().getId())) {
            throw new MyException(ErrorCodeEnum.DoNotAllowToDisableTheCurrentUser.getCode(), ErrorCodeEnum.DoNotAllowToDisableTheCurrentUser.getMsg());
        } else {
            BaseUser t = new BaseUser();
            t.setId(id);
            t.setStatus(status ? UserStatusEnum.AVAILABLE.getStatusCode() :
                    UserStatusEnum.DISABLE.getStatusCode());
            baseUserMapper.updateById(t);
        }
    }

    @Transactional
    @Override
    public boolean add(BaseUserDTO userDto) {
        BaseUser checkUserName = baseUserMapper.selectOne(new QueryWrapper<BaseUser>().eq("username", userDto.getUsername()));
        if (!StringUtils.isEmpty(checkUserName)) {
            throw new MyException(200, "????????????????????????");
        }
        BaseUser dbUser = new BaseUser();
        BeanUtils.copyProperties(userDto, dbUser);
        Long id = ModelIdGenreator.nextId();
        String salt = RandomStringUtils.randomAlphabetic(20);
        dbUser.setId(id);
        dbUser.setSalt(salt);
        dbUser.setPassword(SHA256Util.sha256(userDto.getPassword(), salt));
        return baseUserMapper.insert(dbUser) > 0 ? true : false;
    }

    @Override
    public boolean update(BaseUserDTO userDto) {
        return false;
    }

    @Override
    public BaseUserVO edit(Long id) {
        BaseUser user = baseUserMapper.selectById(id);
        if (StringUtils.isEmpty(user)) {
            throw new MyException("???????????????????????????");
        }
        BaseUserVO baseUserVo = new BaseUserVO();
        BeanUtils.copyProperties(user, baseUserVo);

        return baseUserVo;
    }

    @Override
    public List<Long> roles(Long id) {
        BaseUser user = baseUserMapper.selectById(id);
        if (StringUtils.isEmpty(user)) {
            throw new MyException("??????????????????");
        }
        List<SysUserRole> userRoleList = userRoleMapper.selectList(new QueryWrapper<SysUserRole>().eq("user_id", id));
        List<Long> roleIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userRoleList)) {
            userRoleList.stream().forEach((m) -> {
                        SysRole sysRole = roleMapper.selectById(m.getRoleId());
                        if (!StringUtils.isEmpty(sysRole)) {
                            roleIds.add(sysRole.getId());
                        }
                    }
            );
        }
        return roleIds;
    }

    @Override
    public void assignRoles(Long id, Long[] rids) {
        //?????????????????????????????????
        BaseUser user = baseUserMapper.selectById(id);
        if (StringUtils.isEmpty(user)) {
            throw new MyException("???????????????");
        }
        //?????????????????????
        Map<String, Object> map = new HashMap<>(16);
        map.put("user_id", id);
        userRoleMapper.deleteByMap(map);
        //?????????????????????
        if (rids.length > 0) {
            for (Long rid : rids) {
                SysRole role = roleMapper.selectById(rid);
                if (StringUtils.isEmpty(role)) {
                    throw new MyException("roleId=" + rid + ",??????????????????");
                }
                //??????????????????
                if (role.getStatus() == 0) {
                    throw new MyException("roleName=" + role.getRoleName() + ",??????????????????");
                }
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(id);
                userRole.setRoleId(rid);
                userRoleMapper.insert(userRole);
            }
        }
    }

    @Override
    public List<BaseUser> findAll() {
        return null;
    }

    @Override
    public String login(String username, String password) {
        String token;
        BaseUser user = selectUserByName(username);
        if (!StringUtils.isEmpty(user)) {
            String salt = user.getSalt();
            //????????????
            String target = SHA256Util.sha256(password, salt);
            //??????Token
            token = JWTUtils.sign(username, target);
            JWTToken jwtToken = new JWTToken(token);
            try {
                SecurityUtils.getSubject().login(jwtToken);
            } catch (AuthenticationException e) {
                throw new MyException(ErrorCodeEnum.PASSWORD_DEFINED.getCode(), ErrorCodeEnum.PASSWORD_DEFINED.getMsg());
            }
        } else {
            throw new MyException(ErrorCodeEnum.USER_ACCOUNT_NOT_FOUND.getCode(), ErrorCodeEnum.USER_ACCOUNT_NOT_FOUND.getMsg());
        }
        return token;
    }

    @Override
    public BaseUserVO info()  {
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        BaseUserVO userVo = new BaseUserVO();
        userVo.setId(activeUser.getUser().getId());
        userVo.setUsername(activeUser.getUser().getUsername());
        userVo.setType(activeUser.getUser().getType());
        userVo.setUrl(activeUser.getUrls());
        List<String> roleNames = activeUser.getRoles().stream().map(SysRole::getRoleName).collect(Collectors.toList());
        List<Long> roleIds = activeUser.getRoles().stream().map(SysRole::getId).collect(Collectors.toList());
        userVo.setRoles(roleNames);
        userVo.setRoleIds(roleIds);
        userVo.setPerms(activeUser.getPermissions());
        userVo.setIsAdmin(activeUser.getUser().getType() == UserTypeEnum.SYSTEM_ADMIN.getTypeCode());

        return userVo;
    }

    @Override
    public BaseUserVO getuserImag(String username) throws Exception {
        BaseUser baseUser = baseUserService.selectUserByName(username);
        BaseUserVO userVo = new BaseUserVO();
        //???????????????????????????
        if(baseUser.getType()==2){//
            //??????????????????
            //??????userid??????????????????id
            HrInfoDTO hrInfoDTO = new HrInfoDTO();
            hrInfoDTO.setUserId(baseUser.getId());
            HrInfo hrInfo = this.hrInfoService.selectOneHrInfo(hrInfoDTO);
            if (hrInfo!=null){
                AliyunOssDTO aliyunOssDTO = new AliyunOssDTO();
                aliyunOssDTO.setRecruitUnitId(hrInfo.getRecruitUnitId());
                aliyunOssDTO.setUsed(1);
                AjaxResult ajaxResult = aliyunOssService.selectOneAliyunOss(aliyunOssDTO);
                if ((Integer)ajaxResult.get("code") == AjaxResult.Type.SUCCESS.value()){
                    AliyunOss data = (AliyunOss) ajaxResult.get("data");
                    userVo.setImgUrl("https://jianzhi-backet.oss-cn-shenzhen.aliyuncs.com/"+data.getFilePath());
                }
            }
        }else {
            JobSeekerDTO jobSeekerDTO = new JobSeekerDTO();
            jobSeekerDTO.setUserId(baseUser.getId());
            JobSeeker jobSeeker = this.jobSeekerService.selectOneJobSeeker(jobSeekerDTO);
            if (jobSeeker!=null){
                AliyunOssDTO aliyunOssDTO = new AliyunOssDTO();
                aliyunOssDTO.setJobseekerId(jobSeeker.getId());
                aliyunOssDTO.setUsed(1);
                AjaxResult ajaxResult = aliyunOssService.selectOneAliyunOss(aliyunOssDTO);
                if ((Integer)ajaxResult.get("code") == AjaxResult.Type.SUCCESS.value()){
                    AliyunOss data = (AliyunOss) ajaxResult.get("data");
                    userVo.setImgUrl("https://jianzhi-backet.oss-cn-shenzhen.aliyuncs.com/"+data.getFilePath());
                }
            }
        }
        return userVo;
    }

    @Override
    public void update(Long id, BaseUserDTO userDto) {
        BaseUser dbUser = baseUserMapper.selectById(id);
        if (dbUser==null) {
            throw new MyException("???????????????????????????");
        }
        List<BaseUser> users = baseUserMapper.selectList(new QueryWrapper<BaseUser>().eq("username", userDto.getUsername()));
        if (!CollectionUtils.isEmpty(users)) {
            if (!users.get(0).getId().equals(id)) {
                throw new MyException("????????????????????????");
            }
        }
        BaseUser user = new BaseUser();
        BeanUtils.copyProperties(userDto, user);
        user.setId(dbUser.getId());
        baseUserMapper.updateById(user);
    }
    @Transactional
    @Override
    public void deleteById(Long id) {
        BaseUser user = baseUserMapper.selectById(id);
        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        if (user == null) {
            throw new MyException("???????????????????????????");
        }

        if (user.getId().equals(activeUser.getUser().getId())) {
            throw new MyException("??????????????????????????????");
        }
        if(!StringUtils.isEmpty(user.getEmpId())){
            BaseEmployee employee=new BaseEmployee();
            LambdaUpdateWrapper<BaseEmployee> updateWrapper=new LambdaUpdateWrapper<>();
            updateWrapper.set(BaseEmployee::getUsername,null);
            updateWrapper.set(BaseEmployee::getIsUser,0);
            updateWrapper.eq(BaseEmployee::getId,user.getEmpId());
        }
        baseUserMapper.deleteById(id);
        Map<String, Object> map = new HashMap<>(16);
        map.put("user_id", id);
        userRoleMapper.deleteByMap(map);
    }

    @Override
    public IPage<BaseUserVO> test() {
        BaseUserDTO baseUserDto = new BaseUserDTO();
        Page<BaseUserVO> page = new Page<BaseUserVO>(1, 10);
        return baseUserMapper.queryList(page, baseUserDto);
    }

    @Override
    public Map<String, Object> weChatLogin(String phone, WechatUserInfoVO wechatUserInfoVO) {
        return null;
    }

    @Override
    public String AppRefreshToken(String empId) {
        return getToken(baseUserMapper.selectOne(new QueryWrapper<BaseUser>().eq("emp_id",empId)));

    }

    @Override
    public boolean register(BaseUserDTO user) {
        BaseUser dbUser = new BaseUser();
        BeanUtils.copyProperties(user, dbUser);
        Long id = ModelIdGenreator.nextId();
        String salt = RandomStringUtils.randomAlphabetic(20);
        dbUser.setId(id);
        dbUser.setSalt(salt);
        dbUser.setPassword(SHA256Util.sha256(user.getPassword(), salt));
        int insert = baseUserMapper.insert(dbUser);

        //????????????????????????????????????
        baseUserService.assignRoles(dbUser.getId(), new Long[]{16l});

        //??????????????????
        if (user.getType()==1){
            //???job_seeker???
            JobSeeker jobSeeker = new JobSeeker();
            jobSeeker.setName(user.getName());
            jobSeeker.setSno(user.getSno());
            jobSeeker.setUserId(dbUser.getId());
            jobSeeker.setSchoolId(1);//?????????
            jobSeekerMapper.insert(jobSeeker);
            //????????????????????????????????????resume???
            ResumeDTO resumeDTO = new ResumeDTO();
            resumeDTO.setJobSeekerId(jobSeeker.getId());
            resumeService.addResume(resumeDTO);
        }//?????????hr
        else if (user.getType()==2){
            //???hr_info???
            HrInfoDTO hrInfoDTO = new HrInfoDTO();
            hrInfoDTO.setUserId(dbUser.getId());
            hrInfoService.addHrInfo(hrInfoDTO);
        }

        if (insert==1){
            return true;
        }
        return false;
    }



    public String getToken(BaseUser baseUser){
        String token;
        if(!ObjectUtils.isEmpty(baseUser)){
            //String salt = baseUser.getSalt();
            //????????????
            //String target = SHA256Util.sha256(baseUser.getPassword(), salt);
            //??????Token
            token = JWTUtils.sign(baseUser.getUsername(), baseUser.getPassword());
            JWTToken jwtToken = new JWTToken(token);
            try {
                SecurityUtils.getSubject().login(jwtToken);
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }
        }else {
            throw new MyException(ErrorCodeEnum.USER_PHONE_NOT_AGGREE.getCode(), ErrorCodeEnum.USER_PHONE_NOT_AGGREE.getMsg());
        }
        return token;
    }

}

