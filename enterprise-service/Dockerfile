FROM openjdk:21-jdk-slim
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/enterprise-service-0.0.1-SNAPSHOT.jar enterprise.jar
# COPY /builds/ktvtkpm/online_shopping/APIGateway/target/APIGateway-0.0.1-SNAPSHOT.jar apigateway.jar
EXPOSE 8761
ENTRYPOINT exec java $JAVA_OPTS -jar enterprise.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar onlineshopping.jar
