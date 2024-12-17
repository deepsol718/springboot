package com.example.springboot.batchitems;


import com.example.springboot.entity.IndianName;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

public class BatchItemProcessorNames implements ItemProcessor<IndianName, IndianName>, StepExecutionListener {



    @Override
    public IndianName process(IndianName indianName) throws Exception {

        return indianName;
    }



}
