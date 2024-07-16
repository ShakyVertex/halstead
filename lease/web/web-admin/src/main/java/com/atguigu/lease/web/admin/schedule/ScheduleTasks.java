package com.atguigu.lease.web.admin.schedule;

import com.atguigu.lease.common.constant.RedisConstant;
import com.atguigu.lease.model.entity.LeaseAgreement;
import com.atguigu.lease.model.enums.LeaseStatus;
import com.atguigu.lease.web.admin.service.LeaseAgreementService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class ScheduleTasks {

//    @Scheduled(cron = "* * * * * *")
//    public void test() {
//        System.out.println(new Date());
//    }

    @Autowired
    private LeaseAgreementService service;

    @Autowired
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0 0 0 * * *")
    public void checkLeaseStatus() {
        LambdaUpdateWrapper<LeaseAgreement> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.le(LeaseAgreement::getLeaseEndDate, new Date());
        updateWrapper.in(LeaseAgreement::getStatus, LeaseStatus.SIGNED, LeaseStatus.WITHDRAWING);
        updateWrapper.set(LeaseAgreement::getStatus, LeaseStatus.EXPIRED);
        service.update(updateWrapper);
    }

    @Scheduled(fixedRate = 60000)
    public void delayDeleteRedis(String key) {
        Set<String> keys = redisTemplate.opsForSet().members(RedisConstant.ADMIN_DOUBLE_DELETE);
        if (!keys.isEmpty()) {
            redisTemplate.delete(keys);
            redisTemplate.opsForSet().remove(RedisConstant.ADMIN_DOUBLE_DELETE, keys.toArray(new String[0]));
        }
    }
}
