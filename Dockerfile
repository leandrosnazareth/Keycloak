# Base image with Java 17
FROM openjdk:17

# Configuração do fuso horário
ENV TZ=America/Sao_Paulo

# Crie diretórios para cada aplicação
RUN mkdir -p /apps/keycloak-spring-boot /apps/aplicacao-kc-1 /apps/aplicacao-kc-2

# Copie os JARs das suas aplicações para os diretórios respectivos
COPY apps/keycloak-spring-boot.jar /apps/keycloak-spring-boot/
COPY apps/aplicacao-kc-1.jar /apps/aplicacao-kc-1/
COPY apps/aplicacao-kc-2.jar /apps/aplicacao-kc-2/

# Script de inicialização
COPY start.sh /start.sh
RUN chmod +x /start.sh

# Exponha as portas necessárias
EXPOSE 8081 8082 8083

# Defina o script como comando padrão
CMD ["/start.sh"]
