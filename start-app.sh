java  -Dotel.exporter.otlp.endpoint=http://otelcollector:4317 \
      -Dotel.exporter.otlp.traces.endpoint=http://otelcollector:4317 \
      -Dotel.exporter.otlp.metrics.endpoint=otel.exporter.otel.logs.endpoint \
      -Dotel.exporter.otlp.logs.endpoint=otel.exporter.otlp.logs.endpoint \
      -Dotel.service.name=posts \
      -javaagent:opentelemetry-javaagent.jar \
      -jar \
      /app.jar