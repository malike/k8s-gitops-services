package com.malike.gitops.poc.util;

public class Enums {

    public enum ObservabilityEvents {
        CREATE_DUMMY_DATA("create.dummy.data"),
        CREATE_DATA("create.dummy.data"),
        READ_DATA("read.data");

        private final String metricName;

        public String getMetricName() {
            return metricName;
        }

        ObservabilityEvents(String metricName) {
            this.metricName = metricName;
        }
    }
}
