package com.restrain.service;

import com.restrain.dao.SignImgRepository;
import com.restrain.model.SignImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignImgService {
    @Autowired
    private SignImgRepository signImgRepository;

    public SignImg saveSignImg(Long id, Long signId, String url) {
        SignImg signImg = null;
        if (id == null) {
            signImg = new SignImg();
        }else {
            signImg = signImgRepository.findOne(id);
        }
        signImg.setId(id);
        signImg.setImgUrl(url);
        signImg.setSignId(signId);
        return signImgRepository.save(signImg);
    }

    public List<SignImg> signImgsBySignId(Long signId) {
        return signImgRepository.findBySignId(signId);
    }

    public long maxId(){return signImgRepository.maxId();}
}
