## gitops-observability-demo


This is a simple java service to showcase observability.

Traces, metrics and logs combined are what helps us achieve perfect observability. Spring boot allows us to easily capture these. The Observability stack we would want to use to capture these are

1. Logs with [Loki](https://grafana.com/oss/loki/)
2. Metrics with [Prometheus](https://prometheus.io/)
3. Traces with [Tempo](https://grafana.com/oss/tempo/)