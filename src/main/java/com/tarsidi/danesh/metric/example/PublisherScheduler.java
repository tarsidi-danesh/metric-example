package com.tarsidi.danesh.metric.example;

import com.tiket.tix.flight.analytic.lib.service.impl.constructor.AnalyticMetricRequestFactory;
import com.tiket.tix.flight.analytic.metric.lib.service.api.AnalyticMetricService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class PublisherScheduler {

  private final AnalyticMetricService<Mono<Void>> analyticMetricFluxService;

  @Autowired
  public PublisherScheduler(
      AnalyticMetricService<Mono<Void>> analyticMetricFluxService) {
    this.analyticMetricFluxService = analyticMetricFluxService;
  }

  @Scheduled(fixedRate = 10000)
  public void publishMetric(){
    log.info("Start publish metric");
    Mono.fromCallable(() -> AnalyticMetricRequestFactory
            .createMinimalRequest("sample-metric", "sample-sub-metric", "SUCCESS"))
        .doOnNext(message -> log.info("Sending message: {}", message.toString()))
        .flatMap(analyticMetricFluxService::publishMetric)
        .doOnError(throwable ->
            log.error("Failed to publish metric: ", throwable)
        ).subscribe();
  }

}
