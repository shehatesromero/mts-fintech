package ru.mts.hw7.scheduling;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.mts.hw7.repository.AnimalsRepository;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private final AnimalsRepository animalsRepository;

    private final ScheduledExecutorService executorService;

    @Autowired
    public ScheduledTasks(AnimalsRepository animalsRepository, @Qualifier("taskExecutor") ScheduledExecutorService executorService) {
        this.animalsRepository = animalsRepository;
        this.executorService = executorService;
    }

    @PostConstruct
    public void postConstruct() {
        schedulePrintDuplicate();
        scheduleFindAverageAge();
    }

    @Async
    public void schedulePrintDuplicate() {
        executorService.scheduleAtFixedRate(() -> {
            log.info("Starting thread for method printDuplicate()");
            animalsRepository.printDuplicate();
        }, 0, 10, TimeUnit.SECONDS);
    }

    @Async
    public void scheduleFindAverageAge() {
        executorService.scheduleAtFixedRate(() -> {
            log.info("Starting thread for method findAverageAge()");
            animalsRepository.findAverageAge(animalsRepository.getAllAnimals());
        }, 0, 20, TimeUnit.SECONDS);
    }
}

