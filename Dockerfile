FROM hub.onlinebank.kz/core-images/runners/java/distroless-runner:java-11

COPY --chown=nonroot:nonroot build/libs/easy-trip-service-0.0.1.jar ./easy-trip-service.jar


CMD ["easy-trip-service.jar"]
