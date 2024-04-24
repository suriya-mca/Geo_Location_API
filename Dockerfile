FROM eclipse-temurin:21-jre-alpine

WORKDIR /src

COPY target/Geo-0.0.1.jar /src/Geo-0.0.1.jar

RUN adduser -D -u 1000 appuser && \
	chown -R appuser:appuser /src && \
    chmod +x /src/Geo-0.0.1.jar

EXPOSE 5000

USER appuser

ENTRYPOINT ["java", "-jar" , "/src/Geo-0.0.1.jar"]