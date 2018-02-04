package com.restrain.service;

import com.restrain.dao.ActivityRepository;
import com.restrain.model.Activity;
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
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018/1/23.
 */
@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public Activity saveActivity(Long id, String name, String createrWxId, String content, Short isTime, Date startDate , Date endDate, Short isSign, Short style, String limits, String img, String video, String music){
        Activity activity = null;
        if (id==null){
            activity = new Activity();
            activity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        }else {
            activity = activityRepository.findOne(id);
        }
        activity.setName(name);
        activity.setCreaterWxId(createrWxId);
        activity.setContent(content);
        activity.setIsTime(isTime);
        activity.setStartDate(startDate);
        activity.setEndDate(endDate);
        activity.setIsSign(isSign);
        activity.setIsLimit(style);
        activity.setLimits(limits);
        activity.setImg(img);
        activity.setVideo(video);
        activity.setMusic(music);
        return activityRepository.save(activity);
    }

    public Activity activity(Long id){
        return activityRepository.findOne(id);
    }

    public Page<Activity> activities(int page, int size, String sortType, String sortValue){
        String[] svs = sortValue.split(",");
        String[] sts = sortType.split(",");

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        for (int i = 0; i < svs.length; i++) {
            Sort.Order order = new Sort.Order(Sort.Direction.fromString(sts[i]), svs[i]);
            orders.add(order);
        }
        Sort sort = new Sort(orders);
        Pageable pageable = new PageRequest(page, size, sort);

        Specification<Activity> specification = new Specification<Activity>() {
            @Override
            public Predicate toPredicate(Root<Activity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
//                if (name != null && !name.trim().isEmpty()) {
//                    predicate.getExpressions().add(cb.like(root.get("name"), "%" + name + "%"));
//                }
                return predicate;
            }

        };

        return activityRepository.findAll(specification, pageable);
    }
}
