package ezpay.io.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import io.opentelemetry.exporter.otlp.http.metrics.OtlpHttpMetricExporter;

@Configuration
public class OtlpConfig {
    @Bean
    OtlpHttpMetricExporter otlpHttpMetricExporter(@Value("${management.jaeger.tracing.url}") String url) {
        return OtlpHttpMetricExporter.builder().setEndpoint(url).build();
    }
}
