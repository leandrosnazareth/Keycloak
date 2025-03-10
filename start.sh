#!/bin/bash

# Função para iniciar uma aplicação Java com JMX habilitado
start_java_app() {
  app_path=$1
  jmx_port=$2

  # Verificar se o arquivo JAR existe
  if [ ! -f "$app_path" ]; then
    echo "Erro: Arquivo $app_path não encontrado!"
    exit 1
  fi

  # Iniciar a aplicação
  echo "Iniciando $app_path na porta JMX $jmx_port..."
  java -Dcom.sun.management.jmxremote \
       -Dcom.sun.management.jmxremote.port=$jmx_port \
       -Dcom.sun.management.jmxremote.rmi.port=$jmx_port \
       -Dcom.sun.management.jmxremote.authenticate=false \
       -Dcom.sun.management.jmxremote.ssl=false \
       -Djava.rmi.server.hostname=127.0.0.1 \
       -jar $app_path >> "/var/log/$(basename $app_path).log" 2>&1 &
}

# Iniciar todas as aplicações
start_java_app "/apps/keycloak-spring-boot/keycloak-spring-boot.jar" 8081
start_java_app "/apps/aplicacao-kc-1/aplicacao-kc-1.jar" 8082
start_java_app "/apps/aplicacao-kc-2/aplicacao-kc-2.jar" 8083

# Esperar que algum processo termine
wait -n

# Finalizar com o status do processo que terminou primeiro
exit $?
