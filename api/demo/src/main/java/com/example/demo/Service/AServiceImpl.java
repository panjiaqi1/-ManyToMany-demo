package com.example.demo.Service;

import com.example.demo.entity.A;
import com.example.demo.entity.B;

import java.util.List;

import com.example.demo.entity.C;
import com.example.demo.repository.ARepository;
import com.example.demo.repository.BRepository;
import com.example.demo.repository.CRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AServiceImpl implements AService {
    private static final Logger logger = LoggerFactory.getLogger(AServiceImpl.class);

    private final ARepository aRepository;
    private final BRepository bRepository;
    private final CRepository cRepository;

    public AServiceImpl(ARepository aRepository, BRepository bRepository, CRepository cRepository) {
        this.aRepository = aRepository;
        this.bRepository = bRepository;
        this.cRepository = cRepository;

    }


    @Override
    public void updateAOfB(Long id, A a) {
        logger.debug("获取原有的A");
        A oldA = aRepository.findById(id).orElseThrow(() -> (new EntityNotFoundException("找不到A")));

        logger.debug("更新");
        oldA.setName(a.getName());
        oldA.setbList(a.getbList());

        logger.debug("保存");
        aRepository.save(oldA);
        this.updateAOfB(oldA);
    }

    @Override
    public void updateAOfC(Long id, A a) {
        logger.debug("获取原有的A");
        A oldA = aRepository.findById(id).orElseThrow(() -> (new EntityNotFoundException("找不到A")));

        logger.debug("移出A和C之间的关系");
        List<C> cList = oldA.getcList();
        for (C c : cList) {
            c.getaList().remove(oldA);
        }
        cRepository.saveAll(cList);

        logger.debug("更新");
        oldA.setName(a.getName());
        oldA.setcList(a.getcList());

        logger.debug("保存");
        aRepository.save(oldA);
        this.updateAOfC(oldA);
    }


    /**
     * 因使用mappedBy,B表维护，增加A时，需要保存A与B的关系
     */
    public void updateAOfB(A a) {
        List<B> bList = a.getbList();

        logger.debug("将A保存到中间表");
        for (B b : bList) {
            if (!b.getaList().contains(a)) {
                b.getaList().add(a);
            }
        }
        bRepository.saveAll(bList);
    }


    /**
     * 因使用mappedBy,C表维护，增加A时，需要保存A与C的关系
     */
    public void updateAOfC(A a) {
        List<C> cList = a.getcList();

        logger.debug("将A保存到中间表");
        for (C c : cList) {
            if (!c.getaList().contains(a)) {
                c.getaList().add(a);
            }
        }
        cRepository.saveAll(cList);
    }
}
