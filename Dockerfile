FROM eclipse-temium:17-jre
WORKDIR /app
copy build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"] 