El proyecto es un estilo de netflix, en el cual hay 3 microservicios. Movie, Serie y Catalogo.
Movie esta hecho con la base de datos MySql, a su vez catalogo y serie estan hechos con Mongo DB.
La funcion de catalogo es que cuando le hagan una consulta con un genero, este devuelva las series y movies con ese genero. Si el microservicio de movies o series esta caido, catalogo tiene que traer los datos de movie y serie de su propia base de datos. Por lo tanto en el microservicio de movie y serie esta implementada la funcionalidad que al crearse una movie o serie, se cree tanto en la base de datos de su propio microservicio como en Catalogo.
Centralice todas las configuraciones de los microservicios a traves de la implementacion de Config Server.
Implemente eureka server para que mis microservicios pueden escalar dinámicamente según la demanda y retirarse cuando no sean necesarios y para permitir a mis microservicios registrarse y descubrirse entre sí de manera automática.
Tambien puse en practica Gateway para enrutar las solicitudes de los clientes a los microservicios, asi hay un unico un punto de entrada para todas las solicitudes externas y aparte me ofrece seguridad para proteger mis microservicios.
Lleve a cabo Rabbit MQ en el que me proporciona un modelo de cola de mensajes en el que los productores envían mensajes a una cola y los consumidores los reciben de manera asincrónica. En mi proyecto lo utilizo para crear las movies y series en el microservicio de catalogo, al crearse una movie o serie se produce un mensaje/evento el cual sera consumido por catalogo. Movie y serie son los productores y catalogo el consumidor.
Utilice Zipkin para seguir el camino de una solicitud a medida que viaja a través de diferentes servicios y microservicios, asi tambien me ayuda a identificar los puntos de congestión, cuellos de botella y si hay algun error encontrar donde.
A su vez implemente Docker para el despliegue de mi aplicacion y la orquestacion de contenedores. En mi Docker Compose hay contenedores de MySql, MongoDB, RabbitMQ y Zipkin.
Solo Back End sin Front End


ENGLISH: 
The project follows a Netflix-style approach, consisting of 3 microservices: Movie, Series, and Catalog.
The Movie microservice uses a MySQL database, while Catalog and Series are built with MongoDB.
The Catalog's function is to respond to queries with a specific genre by returning the corresponding series and movies. If either the Movie or Series microservice is down, Catalog should retrieve the data from its own database. Therefore, the Movie and Series microservices are implemented in a way that when a movie or series is created, it is stored both in their own microservice's database and in the Catalog.
All microservice configurations are centralized through the implementation of Config Server.
Eureka Server is implemented to allow the microservices to scale dynamically based on demand and to enable automatic registration and discovery among them.
Gateway is also implemented to route client requests to the microservices, providing a single entry point for external requests and offering security measures to protect the microservices.
RabbitMQ is used to create a message queue model where producers send messages to a queue and consumers receive them asynchronously. In this project, it is used to create movies and series in the Catalog microservice. When a movie or series is created, a message/event is produced, which is consumed by the Catalog. Movie and Series act as producers, while Catalog acts as the consumer.
Zipkin is used to trace the path of a request as it travels through different services and microservices. It helps identify congestion points, bottlenecks, and assists in error identification.
Docker is employed for application deployment and container orchestration. The Docker Compose file includes containers for MySQL, MongoDB, RabbitMQ, and Zipkin.
Back End only, no front end
