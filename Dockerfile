FROM java:8
ENV EXECUTABLE_JAR="$APP_HOME/app.jar"
ADD app/build/libs/app-0.0.1.jar "$EXECUTABLE_JAR"
EXPOSE 8080

ENTRYPOINT exec java \
  -Djava.security.egd=file:/dev/./urandom \
  -jar "$EXECUTABLE_JAR"

