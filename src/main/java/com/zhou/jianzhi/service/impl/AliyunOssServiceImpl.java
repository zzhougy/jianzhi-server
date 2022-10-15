package com.zhou.jianzhi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.jianzhi.common.AjaxResult;
import com.zhou.jianzhi.entity.dto.AliyunOssDTO;
import com.zhou.jianzhi.entity.po.AliyunOss;
import com.zhou.jianzhi.mapper.AliyunOssMapper;
import com.zhou.jianzhi.service.IAliyunOssService;
import com.zhou.jianzhi.service.ICommonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * aliyun_ossService业务层处理
 *
 * @author zhou
 * @date 2021-01-30
 */
@Service
@Transactional
public class AliyunOssServiceImpl extends ServiceImpl<AliyunOssMapper, AliyunOss> implements IAliyunOssService {

    @Autowired
    private AliyunOssMapper aliyunOssMapper;
    @Autowired
    private ICommonService commonService;

    /**
     * 根据aliyun_oss表id查询
     *
     * @param id
     * @return
     */
    @Override
    public AjaxResult selectAliyunOssById(Integer id) {
        AliyunOss aliyunOss = this.aliyunOssMapper.selectById(id);
        if (aliyunOss==null){
            return AjaxResult.error("查不到");
        }
        return AjaxResult.success(aliyunOss);
    }

	/**
     * 根据条件查询返回一条AliyunOss信息
     * @param aliyunOssDTO
     * @return
     */
    @Override
    public AjaxResult selectOneAliyunOss(AliyunOssDTO aliyunOssDTO) throws Exception {
//        //如果没有传recruitunitId查询登录用户
//        if (null==aliyunOssDTO.getRecruitUnitId() ||  StrUtil.isBlank(aliyunOssDTO.getRecruitUnitId().toString())){
//            //通过shiro框架拿到当前登录用户的信息
//            ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
//            Long userId = activeUser.getUser().getId();
//            aliyunOssDTO.setRecruitUnitId(commonService.selectLoginUserInfoByLoginUserId(userId).getRecruitUnitId());
//        }

        AliyunOss aliyunOss = new AliyunOss();
        BeanUtils.copyProperties(aliyunOssDTO,aliyunOss);
        QueryWrapper<AliyunOss> wrapper = new QueryWrapper<>();
        if(aliyunOss.getId() != null){
            wrapper.eq(!StrUtil.isBlank(aliyunOss.getId().toString()),"id", aliyunOss.getId());
        }
        wrapper.eq(!StrUtil.isBlank(aliyunOss.getFileName()),"file_name",aliyunOss.getFileName());
        wrapper.eq(!StrUtil.isBlank(aliyunOss.getFilePath()),"file_path",aliyunOss.getFilePath());
        wrapper.eq(!StrUtil.isBlank(aliyunOss.getType()),"type",aliyunOss.getType());
        wrapper.eq(!StrUtil.isBlank(aliyunOss.getSource()),"source",aliyunOss.getSource());
        if(aliyunOss.getUsed() != null){
            wrapper.eq(!StrUtil.isBlank(aliyunOss.getUsed().toString()),"used", aliyunOss.getUsed());
        }
        if(aliyunOss.getJobseekerId() != null){
            wrapper.eq(!StrUtil.isBlank(aliyunOss.getJobseekerId().toString()),"jobseeker_id", aliyunOss.getJobseekerId());
        }
        if(aliyunOss.getRecruitUnitId() != null){
            wrapper.eq(!StrUtil.isBlank(aliyunOss.getRecruitUnitId().toString()),"recruit_unit_id", aliyunOss.getRecruitUnitId());
        }
        aliyunOss = this.aliyunOssMapper.selectOne(wrapper);
        if (aliyunOss==null){
            return AjaxResult.error("查不到");
        }
        try {
            aliyunOss.setFilePath(aliyunOss.getFilePath());
        }catch (Exception e){
            System.out.println("4444444");
        }

        return AjaxResult.success(aliyunOss);
    }

    /**
     * 查询AliyunOss列表
     *
     * @param aliyunOssDTO
     * @return
     */
    @Override
    public AjaxResult selectAliyunOssList(AliyunOssDTO aliyunOssDTO) throws Exception {
        Page<AliyunOss> page = new Page<>(aliyunOssDTO.getPageCurrent(),aliyunOssDTO.getPageSize());
        AliyunOss aliyunOss = new AliyunOss();
        BeanUtils.copyProperties(aliyunOssDTO,aliyunOss);
        QueryWrapper<AliyunOss> wrapper = new QueryWrapper<>();
        if(aliyunOss.getId() != null){
            wrapper.eq(!StrUtil.isBlank(aliyunOss.getId().toString()),"id", aliyunOss.getId());
        }
        wrapper.eq(!StrUtil.isBlank(aliyunOss.getFileName()),"file_name",aliyunOss.getFileName());
        wrapper.eq(!StrUtil.isBlank(aliyunOss.getFilePath()),"file_path",aliyunOss.getFilePath());
        wrapper.eq(!StrUtil.isBlank(aliyunOss.getType()),"type",aliyunOss.getType());
        wrapper.eq(!StrUtil.isBlank(aliyunOss.getSource()),"source",aliyunOss.getSource());
        wrapper.eq(!StrUtil.isBlank(aliyunOss.getUsed().toString()),"used",aliyunOss.getUsed());
        if(aliyunOss.getJobseekerId() != null){
            wrapper.eq(!StrUtil.isBlank(aliyunOss.getJobseekerId().toString()),"jobseeker_id", aliyunOss.getJobseekerId());
        }
        if(aliyunOss.getRecruitUnitId() != null){
            wrapper.eq(!StrUtil.isBlank(aliyunOss.getRecruitUnitId().toString()),"recruit_unit_id", aliyunOss.getRecruitUnitId());
        }
        Page<AliyunOss> Ipage = this.aliyunOssMapper.selectPage(page, wrapper);
        if (Ipage==null){
            return AjaxResult.error();
        }
        return AjaxResult.success(Ipage);
    }

    /**
    * 新增单个AliyunOss
    *
    * @param aliyunOssDTO
    * @return
    */
    @Override
    public AjaxResult addAliyunOss(AliyunOssDTO aliyunOssDTO) throws Exception {


        AliyunOss aliyunOss = new AliyunOss();
        BeanUtils.copyProperties(aliyunOssDTO,aliyunOss);
        aliyunOss.setFilePath(aliyunOss.getFilePath());

        //判断是否上传过头像，有则将原来的状态改为作废
        if (aliyunOss.getUsed() == 1){
            AliyunOssDTO aliyunOssDTO1 = new AliyunOssDTO();
            //招聘单位logo
            if(aliyunOss.getRecruitUnitId()!=null){
                aliyunOssDTO1.setRecruitUnitId(aliyunOss.getRecruitUnitId());
                aliyunOssDTO1.setUsed(1);
            }
            //求职者的头像
            else if (aliyunOss.getJobseekerId()!=null){
                aliyunOssDTO1.setJobseekerId(aliyunOss.getJobseekerId());
                aliyunOssDTO1.setUsed(1);
            }
            //将原来的状态改为作废
            AjaxResult ajaxResult = selectOneAliyunOss(aliyunOssDTO1);
            if ((Integer) ajaxResult.get("code") == AjaxResult.Type.SUCCESS.value()){
                AliyunOss data = (AliyunOss) ajaxResult.get("data");
                System.out.println(data);
                AliyunOss aliyunOss1 = data;
                BeanUtils.copyProperties(aliyunOss1,aliyunOssDTO1);
                aliyunOssDTO1.setUsed(0);
                updateAliyunOssById(aliyunOssDTO1);
            }
        }
        else if (aliyunOss.getUsed() == 2){
            //求职者简历，将原来文件的状态改为作废
            if(aliyunOss.getJobseekerId()!=null){
                AliyunOssDTO aliyunOssDTO1 = new AliyunOssDTO();
                aliyunOssDTO1.setJobseekerId(aliyunOss.getJobseekerId());
                aliyunOssDTO1.setUsed(2);
                AjaxResult ajaxResult = selectOneAliyunOss(aliyunOssDTO1);
                if ((Integer) ajaxResult.get("code") == AjaxResult.Type.SUCCESS.value()){
                    AliyunOss data = (AliyunOss) ajaxResult.get("data");
                    AliyunOss aliyunOss1 = data;
                    BeanUtils.copyProperties(aliyunOss1,aliyunOssDTO1);
                    aliyunOssDTO1.setUsed(0);
                    updateAliyunOssById(aliyunOssDTO1);
                }
            }
        }

        int insertedNum = this.aliyunOssMapper.insert(aliyunOss);
        if (insertedNum > 0){
            return AjaxResult.success("新增成功");
        }
        return AjaxResult.error("新增失败");
    }

    /**
     * 批量新增AliyunOss
     *
     * @param aliyunOssDTOList
     * @return 结果
     */
    @Override
    public AjaxResult addAliyunOssList(List<AliyunOssDTO> aliyunOssDTOList) throws Exception {
        //添加操作
        for (AliyunOssDTO aliyunOssDTO : aliyunOssDTOList){
            addAliyunOss(aliyunOssDTO);
        }
        return AjaxResult.success();
    }

    /**
     * 根据id修改AliyunOss
     *
     * @param aliyunOssDTO
     * @return 结果
     */
    @Override
    public AjaxResult updateAliyunOssById(AliyunOssDTO aliyunOssDTO){
        AjaxResult ajaxResult = selectAliyunOssById(aliyunOssDTO.getId());
        if ((Integer) ajaxResult.get("code") == AjaxResult.Type.SUCCESS.value()){
            AliyunOss aliyunOss = (AliyunOss)ajaxResult.get("data");
            if(aliyunOss.getId() != null){
                aliyunOss.setId(aliyunOssDTO.getId());
            }
            if(aliyunOss.getFileName() != null && !aliyunOss.getFileName().isEmpty()){
                aliyunOss.setFileName(aliyunOssDTO.getFileName());
            }
            if(aliyunOss.getFilePath() != null && !aliyunOss.getFilePath().isEmpty()){
                aliyunOss.setFilePath(aliyunOssDTO.getFilePath());
            }
            if(aliyunOss.getType() != null && !aliyunOss.getType().isEmpty()){
                aliyunOss.setType(aliyunOssDTO.getType());
            }
            if(aliyunOss.getSource() != null && !aliyunOss.getSource().isEmpty()){
                aliyunOss.setSource(aliyunOssDTO.getSource());
            }
            if(aliyunOss.getUsed() != null && !aliyunOss.getUsed().toString().isEmpty()){
                aliyunOss.setUsed(aliyunOssDTO.getUsed());
            }
            if(aliyunOss.getJobseekerId() != null){
                aliyunOss.setJobseekerId(aliyunOssDTO.getJobseekerId());
            }
            if(aliyunOss.getRecruitUnitId() != null){
                aliyunOss.setRecruitUnitId(aliyunOssDTO.getRecruitUnitId());
            }
            int updatedNum = this.aliyunOssMapper.updateById(aliyunOss);
            if(updatedNum > 0){
                return AjaxResult.success("修改成功");
            }else{
                return AjaxResult.error("修改失败");
            }
        }else {
            return AjaxResult.error("数据不存在");
        }
    }

    /**
     * 根据id删除AliyunOss
     *
     * @param id 需要删除的aliyun_ossID
     * @return 结果
     */
    @Override
    public AjaxResult deleteAliyunOssById(Integer id)
    {
        int delete = this.aliyunOssMapper.deleteById(id);
        if(delete<0){
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }

//    /**
//     * @Description : 数据库存的路径是加密的，传回来解密
//     */
//    @Override
//    public String toFileName(String filepath) throws Exception {
//        System.out.println(toFilePath(filepath));
//        return toFilePath(filepath);
//    }


//    /**
//     * @Description : 解密
//     */
//    public String toFilePath(String path) throws Exception {
//        byte[] decode = EncryptUtils.parseHexStr2Byte(path);
//        byte[] decryptResult = EncryptUtils.decrypt(decode);
//        return new String(decryptResult, "UTF-8");
//    }

//    /**
//     * @Description : 加密
//     */
//    public static String signFilePath(String fileName) throws Exception {
//        byte[] encode = EncryptUtils.encrypt(fileName);
//        String code = EncryptUtils.parseByte2HexStr(encode);
//        return code;
//    }


}








