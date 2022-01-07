package koreanre.batch.job;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import koreanre.batch.support.TestBatchLegacyConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableBatchProcessing
@ContextConfiguration(classes={SimpleJob.class, TestBatchLegacyConfig.class}) 
class SimpleJobTest {
	@Autowired
    private JobLauncherTestUtils jobLauncherTestUtils; // (2)

	
	@Test
	public void test() {
		JobExecution jobExecution = null;
		try {
			System.out.println("Junit test !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			jobExecution = jobLauncherTestUtils.launchJob();
			System.out.println("Junit test !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // (3)

        //then
        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
	}
}
