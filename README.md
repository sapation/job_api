//Creating a docker container
./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=<IMAGE_NAME>"
./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=iddrisu/jobappimage"

docker push <username/Image-name>
docker pull <IMAGE>
docker run -it -d -p <host-port>:<container-port>name <NAME> <IMAGE> Eg. (docker run -d -p 8080:8080 iddrisu/jobappimage
docker stop <container-id/conatiner-name>
docker start <container-id/container-name>
docker rm <container-id/container-name>
docker rmi <image-id/image-name>
docker ps
docker ps -a
docker images
docker exec -it <container-name/container-name> bash
docker build -t <username/image>
docker logs <container-name/container-id>
docker inspect <container-name/container-id>

<!-- Make sure `mvn spring-boot:build-image` uses the Java version defined in this project -->
</configuration>					
    <image>
        <env>
            <BP_JVM_VERSION>${java.version}</BP_JVM_VERSION>
        </env>
    </image>
</configuration>