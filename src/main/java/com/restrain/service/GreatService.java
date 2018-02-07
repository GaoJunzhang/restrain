package com.restrain.service;

import com.restrain.dao.GreatRepository;
import com.restrain.model.Great;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreatService {

    @Autowired
    private GreatRepository greatRepository;

    public int deleteByWxNoAndSignId(String wxno,Long signId){
        return greatRepository.deleteByWxNoAndSignId(wxno, signId);
    }

    public int countBySignId(Long signId){
        return greatRepository.countBySignId(signId);
    }

    public Great saveGreate(Long signId, String wxno){
        Great great = new Great();
        great.setSignId(signId);
//        great.setWxUserId(wxUserId);
        great.setWxNo(wxno);
        return greatRepository.save(great);
    }

    public int countBySignIdAndWxNo(Long signId,String wxno){
        return greatRepository.countBySignIdAndWxNo(signId,wxno);
    }

    public int updateSgin(Long signId){
        return greatRepository.updateSgin(signId);
    }
}
