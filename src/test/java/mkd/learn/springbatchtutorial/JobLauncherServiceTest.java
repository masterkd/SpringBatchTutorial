package mkd.learn.springbatchtutorial;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;

@ExtendWith(MockitoExtension.class)
public class JobLauncherServiceTest {

  @Mock
  JobLauncher jobLauncher;

  @Mock
  Job job;

  @Mock
  JobExecution jobExecution;

  @Test
  void runJob_success() throws Exception {
    when(jobLauncher.run(eq(job), any(JobParameters.class))).thenReturn(jobExecution);
    when(jobExecution.getStatus()).thenReturn(BatchStatus.COMPLETED);

    JobLauncherService service = new JobLauncherService(jobLauncher, job);

    assertDoesNotThrow(service::runJob);
    verify(jobLauncher).run(eq(job), any(JobParameters.class));
  }

  @Test
  void runJob_failure() throws Exception {
    when(jobLauncher.run(eq(job), any(JobParameters.class))).thenThrow(new RuntimeException("boom"));

    JobLauncherService service = new JobLauncherService(jobLauncher, job);

    assertDoesNotThrow(service::runJob);
    verify(jobLauncher).run(eq(job), any(JobParameters.class));
  }

}
