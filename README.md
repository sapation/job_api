//Creating a docker container
./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=<IMAGE_NAME>"
./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=iddrisu/jobappimage"

-docker push <username/Image-name>
-docker pull <IMAGE>
-docker run -it -d -p <host-port>:<container-port>name <NAME> <IMAGE> Eg. (docker run -d -p 8080:8080 iddrisu/jobappimage
-docker stop <container-id/conatiner-name>
-docker start <container-id/container-name>
-docker rm <container-id/container-name>
-docker rmi <image-id/image-name>
-docker ps
-docker ps -a
-docker images
-docker exec -it <container-name/container-name> bash
-docker build -t <username/image>
-docker logs <container-name/container-id>
-docker inspect <container-name/container-id>

<!-- Make sure `mvn spring-boot:build-image` uses the Java version defined in this project -->
</configuration>					
    <image>
        <env>
            <BP_JVM_VERSION>${java.version}</BP_JVM_VERSION>
        </env>
    </image>
</configuration>

Example running
- docker run -d --name db -e POSTGRES_PASSWORD=password postgres
- docker run -d --name pgadmin -e PGADMIN_DEFAULT_EMAIL=admin@admin.com -e PGADMIN_DEFAULT_PASSWORD=password dpage/pgadmin4
- docker exec -it pgadmin ping db
- docker rm -f db pgadmin
- docker network create my-network
- docker run -d --name db --network jobapp-network -e POSTGRES_PASSWORD=password postgres
- docker run -d --name pgadmin --network jobapp-network -e PGADMIN_DEFAULT_EMAIL=admin@admin.com -e PGADMIN_DEFAULT_PASSWORD=password dpage/pgadmin4

#Start the postgresSQL service
docker run -d \
    --name postgres_container \
    -e POSTGRES_USER=webtech \
    -e POSTGRES_PASSWORD=password \
    -v postgres:/data/postgres \
    -p 5432:5432 \
    --network postgres \
    --restart unless-stopped \
    postgres

#Start the pgAdmin service
docker run -d \
    --name pgadmin_container \
    -e PGADMIN_DEFAULT_EMAIL=admin@admin.com \
    -e PGADMIN_DEFAULT_PASSWORD=password \
    -e PGADMIN_CONFIG_SERVER_MODE=false \
    -v pgadmin:/var/lib/pgadmin \
    -p 5050:80 \
    --network postgres \
    --restart unless-stopped \
    dpage/pgadmin4

services:
    postgres:
        container_name:postgres_container
        image: postgres
        environment:
            POSTGRES_USER: webtech
            POSTGRES_PASSWORD: password
            PGDATA: /data/postgres
        volumes:
            - postgres:/data/postgres
        ports:
            - "5432:5432"
        networks:
            - postgres
        restart: unless-stopped

    pgadmin:
        container_name: pgadmin_container
        image: dpage/pgadmin4
        environment:
            PGADMIN_DEFAULT_EMAIL: ${PGADMIN_DEFAULT_EMAIL:-pgadmin4@pgadmin.org}
            PGADMIN_DEFAULT_PASSWORD: ${PGADMIN_DEFAULT_PASSWORD: -admin}
            PGADMIN_CONFIG_SERVER_MODE: false
        volumes:
            - pgadmin:/var/lib/pgadmin
        ports:
            - "5050:80"
        networks:
            - postgres
        restart: unless-stopped
networks:
    postgres:
        driver:bridge
volumes:
    postgres:
    pgadmin:
            