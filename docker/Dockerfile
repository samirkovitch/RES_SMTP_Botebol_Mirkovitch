
FROM openjdk:8

ENV MOCKMOCK_SMPT_PORT=25
ENV MOCKMOCK_HTTP_PORT=8282
ENV MOCKMOCK_MAX_QUEUE_SIZE=1000

ADD ["https://github.com/tweakers-dev/MockMock/blob/master/release/MockMock.jar?raw=true", \
     "/mockmock/"]

EXPOSE 25 8282

WORKDIR /mockmock/

ENTRYPOINT java -jar MockMock.jar -p $MOCKMOCK_SMPT_PORT -h $MOCKMOCK_HTTP_PORT -m $MOCKMOCK_MAX_QUEUE_SIZE
