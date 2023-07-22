FROM openjdk:8
VOLUME /tmp
WORKDIR /otp/spring-boot-apps
COPY target/truc_lam_library-0.0.1-SNAPSHOT.jar truc_lam_library-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/otp/spring-boot-apps/truc_lam_library-0.0.1-SNAPSHOT.jar"]