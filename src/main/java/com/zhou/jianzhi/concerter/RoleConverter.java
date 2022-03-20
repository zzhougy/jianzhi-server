package com.zhou.jianzhi.concerter;

import com.zhou.jianzhi.entity.po.SysRole;
import com.zhou.jianzhi.entity.vo.RoleTransferItemVO;
import com.zhou.jianzhi.entity.vo.SysRoleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


public class RoleConverter {
    /**
     * 转vo
     * @param roles
     * @return
     */
    public static List<SysRoleVO> converterToSysRoleVoList(List<SysRole> roles) {
        List<SysRoleVO> roleVOS=new ArrayList<>();
        if(!CollectionUtils.isEmpty(roles)){
            for (SysRole role : roles) {
                SysRoleVO roleVO = new SysRoleVO();
                BeanUtils.copyProperties(role,roleVO);
                roleVO.setStatus(role.getStatus() == 0);
                roleVOS.add(roleVO);
            }
        }
        return roleVOS;
    }

    /**
     * 转成前端需要的角色Item
     * @param list
     * @return
     */
    public static List<RoleTransferItemVO> converterToRoleTransferItem(List<SysRole> list) {
        List<RoleTransferItemVO> itemVOList=new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            list.stream().forEach((m)->{
                RoleTransferItemVO item = new RoleTransferItemVO();
                item.setDisabled(m.getStatus()==0);
                item.setLabel(m.getRoleName());
                item.setDisabled(m.getStatus()==0);
                item.setKey(m.getId());
                itemVOList.add(item);
            });
        }
        return itemVOList;
    }
}
