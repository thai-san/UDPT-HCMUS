FROM openjdk:21-jdk-slim
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/customer-service-0.0.1-SNAPSHOT.jar customer.jar
# COPY /builds/ktvtkpm/online_shopping/APIGateway/target/APIGateway-0.0.1-SNAPSHOT.jar apigateway.jar
EXPOSE 8761
ENTRYPOINT exec java $JAVA_OPTS -jar customer.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar onlineshopping.jar
