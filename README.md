# Manual de iniciação
## Passo a passo

- Baixar o código usando git clone;
- Após baixar TODOS os módulos, dar mvn clean install em todos;
- iniciar o serviço DISCOVER;
- iniciar o serviço GATEWAY;
- iniciar os demais serviços, somente o MODEL não deve ser iniciado, pois serve somente como biblioteca pra os demais.

## Endpoints

POST para logar um usuário
```
http://localhost:8080/auth/login

{
    "login":"ronald4",
    "password":"12345"
}
```

POST para registrar um usuário/conta
```
http://localhost:8080/auth/register

{
    "login":"ronald4",
    "password":"12345",
    "name":"ronald",
    "surname":"garcia",
    "document":"123456"
}
```

GET para testar autenticação
```
http://localhost:8080/hello
```
