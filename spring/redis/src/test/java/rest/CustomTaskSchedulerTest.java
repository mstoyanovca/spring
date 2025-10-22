package rest;

import org.awaitility.Durations;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import rest.service.CustomTaskScheduler;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class CustomTaskSchedulerTest {
    @MockitoSpyBean
    CustomTaskScheduler taskScheduler;

    @Test
    public void reportCurrentTime() {
        await().atMost(Durations.TEN_SECONDS).untilAsserted(() -> verify(taskScheduler, atLeast(2)).reportCurrentTime());
    }
}
