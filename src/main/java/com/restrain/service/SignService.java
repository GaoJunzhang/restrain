package com.restrain.service;

import com.restrain.dao.SignRepository;
import com.restrain.model.Sign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/2/5.
 */
@Service
public class SignService {
    @Autowired
    private SignRepository signRepository;

    public Page<Sign> Signs(Long activityId, int page, int size, String sortType, String sortValue) {
        String[] svs = sortValue.split(",");
        String[] sts = sortType.split(",");

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        for (int i = 0; i < svs.length; i++) {
            Sort.Order order = new Sort.Order(Sort.Direction.fromString(sts[i]), svs[i]);
            orders.add(order);
        }
        Sort sort = new Sort(orders);
        Pageable pageable = new PageRequest(page, size, sort);

        Specification<Sign> specification = new Specification<Sign>() {
            @Override
            public Predicate toPredicate(Root<Sign> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("activityId"), activityId));
                return predicate;
            }

        };

        return signRepository.findAll(specification, pageable);
    }

    public Sign saveSign(Long id,Long userId, Long activityId, String img, String video, String music, String content, String position, String isHide) {
        Sign sign = null;
        if (id == null) {
            sign = new Sign();
        }else {
            sign = signRepository.findOne(id);
        }
        sign.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sign.setUserId(userId);
        sign.setActivityId(activityId);
        sign.setImg(img);
        sign.setVideo(video);
        sign.setMusic(music);
        sign.setContent(content);
        sign.setPosition(position);
        sign.setIsHide(isHide);
        return signRepository.save(sign);
    }
}
