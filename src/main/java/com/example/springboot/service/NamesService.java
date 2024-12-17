package com.example.springboot.service;

import com.example.springboot.dao.IndianNameDao;
import com.example.springboot.entity.IndianName;
import com.example.springboot.model.IndianNameModel;
import com.example.springboot.repository.IndianNamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class NamesService {

    @Autowired
    private IndianNamesRepository repository;

    @Autowired
    private IndianNameDao indianNameDao;

    /*
    * this method is example of pagination using JpaRepository
    * sortBy = name, direction = asc
    *
    * */
    public Page<IndianName> getPaginatedIndianNames(int page, int size, String sortBy, String direction){
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?
                    Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable);
    }

    /*
    * this method is used for getting data using jdbc template
    * */
    public IndianNameModel getIndianNameById(int id){
        return indianNameDao.getNameById(id);
    }
}
