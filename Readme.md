## Rabbit MQ
docker run -d --hostname my-rabbit --name rabbitmq -p 15672:15672 -p5672:5672 -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password rabbitmq:3-management

rest api icin 5672 web icin 15672