package com.example.springboot.batchitems;

import com.example.springboot.repository.IndianNamesRepository;
import com.example.springboot.entity.IndianName;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BatchItemWriterNames implements ItemWriter<IndianName> {

    @Autowired
    IndianNamesRepository indianNamesDAO;

    @Override
    public void write(Chunk<? extends IndianName> chunk) throws Exception {
        System.out.println("inside writer method:");
        List<IndianName> indianNames = new ArrayList<>();
        for (IndianName indianName: chunk){
            indianNames.add(indianName);
        }

        indianNamesDAO.saveAll(indianNames);
    }

}