FROM openjdk:11

ENV DATA_BASE_URL khoaluantotnghiep.cxwi99ogtkms.us-east-2.rds.amazonaws.com
ENV DATA_BASE_PORT 3306

ENV DATA_BASE_USERNAME admin
ENV DATA_BASE_PASSWORD admin123
ENV DATA_BASE_NAME khoaluantotnghiep

ADD target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]