package com.example.springboot.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.Date;

@RestController
@RequestMapping("/epen")
public class Status {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    Job fileJob;

    @Autowired
    private JobOperator jobOperator;

    @GetMapping("/jobsummary")
    public String getJobSummary() throws NoSuchJobExecutionException {
        BigInteger job_execution_id = new BigInteger("8");
        String summary = jobOperator.getSummary(job_execution_id.longValue());
        System.out.println("summary: "+summary);
        return "all good";
    }

    @GetMapping("/startbatch")
    public String startBatch(){
        System.out.println("starting the batch by api");
        try {
            Date date = new Date();
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("launchDate", date)
                    .addString("id","koibheid")
                    .toJobParameters();
            JobExecution jobExecution = jobLauncher.run(fileJob, jobParameters);

        }
        catch (Exception e){
            System.out.println("exception aa gya: "+e.getMessage());
        }
        System.out.println("batch has been finished");
        return "all ok";
    }
}
