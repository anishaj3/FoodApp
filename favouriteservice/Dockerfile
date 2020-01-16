
FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=foodDB
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_USER=app_root
ENV MYSQL_PASSWORD=root123
ENV MYSQL_URL=jdbc:mysql://localhost:3306/foodDB
ADD ./target/favouriteservice-1.0.jar /usr/src/favouriteservice-1.0.jar
ENTRYPOINT ["java","-jar","favouriteservice-1.0.jar"]
