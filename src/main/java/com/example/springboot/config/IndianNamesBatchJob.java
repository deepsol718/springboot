package com.example.springboot.config;

import com.example.springboot.batchitems.BatchItemProcessorNames;
import com.example.springboot.batchitems.BatchItemReaderNames;
import com.example.springboot.batchitems.BatchItemWriterNames;
import com.example.springboot.entity.IndianName;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableBatchProcessing
public class IndianNamesBatchJob {

    @Autowired
    private DataSource dataSource;

    @Bean(name = "transactionManager")
    public PlatformTransactionManager getTransactionManager(){
        return new JpaTransactionManager();
    }

    @Bean(name = "jobRepository")
    public JobRepository getJobRepository() throws Exception{
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(this.dataSource);
        factory.setTransactionManager(getTransactionManager());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean(name = "jobExplorer")
    public JobExplorer jobExplorer(PlatformTransactionManager transactionManager) throws Exception {
        // This bean is necessary for JobOperator to work with the JobExplorer
        JobExplorerFactoryBean factoryBean = new JobExplorerFactoryBean();
        factoryBean.setDataSource(this.dataSource);
        factoryBean.setTransactionManager(transactionManager); // Pass the transaction manager here
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean
    public ItemReader<IndianName> itemReader(){ return new BatchItemReaderNames();
    }
    @Bean
    public ItemWriter<IndianName> itemWriter(){ return new BatchItemWriterNames();
    }

    @Bean
    public ItemProcessor<IndianName, IndianName> itemProcessor(){return new BatchItemProcessorNames();
    }
    @Bean
    protected Step fileStep(final JobRepository jobRepository, final PlatformTransactionManager transactionManager){
        return new StepBuilder("stepOne", jobRepository)
                .<IndianName, IndianName>chunk(700, transactionManager)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Job fileJob(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) throws IOException {
        return new JobBuilder("jobOne", jobRepository)
                .start(fileStep(jobRepository, transactionManager))
                .build();
    }

}
