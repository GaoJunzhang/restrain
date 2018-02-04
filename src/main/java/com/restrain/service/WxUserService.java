package com.restrain.service;

import com.restrain.dao.WxUserRepository;
import com.restrain.model.WxUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class WxUserService {
    @Autowired
    private WxUserRepository wxUserRepository;

    public WxUsers saveWxusers(Long id,String wxName,String nickName, Short sex,String img,String tel, Short status){
        WxUsers wxUsers = null;
        if (id==null){
            wxUsers = new WxUsers();
        }else {
            wxUsers = wxUserRepository.findOne(id);
        }
        wxUsers.setLoginTime(new Timestamp(System.currentTimeMillis()));
        wxUsers.setWxName(wxName);
        wxUsers.setNickName(nickName);
        wxUsers.setSex(sex);
        wxUsers.setImg(img);
        wxUsers.setTel(tel);
        wxUsers.setStatus(status);
        return wxUserRepository.save(wxUsers);
    }
}
