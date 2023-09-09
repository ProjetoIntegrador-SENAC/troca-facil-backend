# Manual de iniciação
## Passo a passo

- Baixar o código usando git clone;
- Após baixar TODOS os módulos, dar mvn clean install em todos;
- iniciar o serviço DISCOVER;
- iniciar o serviço GATEWAY;
- iniciar os demais serviços, somente o MODEL não deve ser iniciado, pois serve somente como biblioteca pra os demais.

## Criar novo serviço

DEPENDÊNCIAS:
  - Para criar um novo serviço é importante ter as seguintes dependências:
  - Eureka discovery client (para que os serviços fiquem na mesma url);
  - Lombok (Evitar boilerplate);
  - MySql Driver (para criar a conexão com o bd);
  - Flyway (Para registrar alterações no banco de dados);
  - Security - (Para validação de tokens);


APPLICATION.PROPERTIES:
```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=${MYSQL_URL}/troca-facil?createDatabaseIfNotExist=true SUBSTITUIR POR SUA URL DO MYSQL
spring.datasource.username=${MYSQL_USER} SUBSTITUIR POR SEU USERNAME DO MYSQL
spring.datasource.password=${MYSQL_PASSWORD} SUBSTITUIR POR SUA SENHA DO MYSQL
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql= true


api.security.token.secret=${JWT_SECRET:trocafacil}

spring.application.name=NOME_DO_MICRO_SERVIÇO
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
server.port=0
```

