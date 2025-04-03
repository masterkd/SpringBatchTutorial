package mkd.learn.springbatchtutorial.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class JobConfiguration {

    @Bean
    public JobOneReader jobOneReader() {
        return new JobOneReader();
    }

    @Bean
    public JobOneProcessor jobOneProcessor() {
        return new JobOneProcessor();
    }

    @Bean
    public JobOneWriter jobOneWriter() {
        return new JobOneWriter();
    }

    @Bean
    public Job jobOne(JobRepository jobRepository, Step stepOne) {
        return new JobBuilder("jobOne", jobRepository)
                .start(stepOne)
                .build();
    }

    @Bean
    public Step stepOne(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("stepOne", jobRepository)
                .<String, String>chunk(10, transactionManager)
                .reader(jobOneReader())
                .processor(jobOneProcessor())
                .writer(jobOneWriter())
                .build();
    }

    @Bean
    public JobTwoReader jobTwoReader() {
        return new JobTwoReader();
    }

    @Bean
    public JobTwoProcessor jobTwoProcessor() {
        return new JobTwoProcessor();
    }

    @Bean
    public JobTwoWriter jobTwoWriter() {
        return new JobTwoWriter();
    }

    @Bean
    public Job jobTwo(JobRepository jobRepository, Step stepTwo) {
        return new JobBuilder("jobTwo", jobRepository)
                .start(stepTwo)
                .build();
    }

    @Bean
    public Step stepTwo(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("stepTwo", jobRepository)
                .<String, String>chunk(2, transactionManager)
                .reader(jobTwoReader())
                .processor(jobTwoProcessor())
                .writer(jobTwoWriter())
                .build();
    }

    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
                .build();
    }

//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager getTransactionManager() {
//        return new ResourcelessTransactionManager();
//    }

}
