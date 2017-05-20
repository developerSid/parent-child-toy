package com.example.parentchildtoy.service.jpa;

import com.example.parentchildtoy.entity.GrandParent;
import com.example.parentchildtoy.repository.GrandParentRepository;
import com.example.parentchildtoy.service.GrandParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaGrandParentService implements GrandParentService {
    private final GrandParentRepository grandParentRepository;

    @Autowired
    public JpaGrandParentService(GrandParentRepository grandParentRepository) {
        this.grandParentRepository = grandParentRepository;
    }

    @Override
    public GrandParent save(GrandParent grandParent) {
        return grandParentRepository.save(grandParent);
    }

    @Override
    public GrandParent update(GrandParent grandParent) {
        return grandParentRepository.save(grandParent);
    }
}
