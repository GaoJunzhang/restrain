package com.restrain.service;

import com.restrain.dao.WxUserRepository;
import com.restrain.model.WxUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class WxUserService {
    @Autowired
    private WxUserRepository wxUserRepository;

    public WxUsers saveWxusers(Long id, String wxName, String nickName, Short sex, String img, String tel, Short status, String wxno) {
        WxUsers wxUsers = null;
        if (id == null) {
            wxUsers = new WxUsers();
        } else {
            wxUsers = wxUserRepository.findOne(id);
        }
        wxUsers.setLoginTime(new Timestamp(System.currentTimeMillis()));
        wxUsers.setWxName(wxName);
        wxUsers.setNickName(nickName);
        wxUsers.setSex(sex);
        wxUsers.setImg(img);
        wxUsers.setTel(tel);
        wxUsers.setStatus(status);
        wxUsers.setWxno(wxno);
        return wxUserRepository.save(wxUsers);
    }

    public WxUsers wxUsersById(Long id) {
        return wxUserRepository.findOne(id);
    }

    public List<WxUsers> wxUsersListByIdIn(String[] wxnos) {
        return wxUserRepository.findByWxnoIn(wxnos);
    }

    public List<WxUsers> findByOpenid(String openId) {
        return wxUserRepository.findByWxno(openId);
    }

    public List<WxUsers> findByActivityId(Long activityId) {
        return wxUserRepository.findByActivityId(activityId);
    }
    public List<WxUsers> findByIdIn(Long[] ids){
        return wxUserRepository.findByIdIn(ids);
    }
}
