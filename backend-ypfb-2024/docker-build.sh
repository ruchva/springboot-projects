#!/bin/bash
#######################################################################################################################
## Script para compilar una imagen y publicar el proyecto backend en el registry los contenedores
##
## Elaborado por Renato Apaza T.
#######################################################################################################################

# Cambiar por la ruta del JDK instalado
export JAVA_HOME=/home/rapaza/.sdkman/candidates/java/11.0.10-open/
clear
echo " ======> Limpiar Proyecto <======"
./gradlew clean checkstyleMain #assemble
echo "=====> JAR Creado <====="

docker images | grep lumira | awk '{print $3}' | xargs docker rmi

#./gradlew buildDockerImage

#read -p " pushDockerImage (Si = 1 | No = 0): " resp

#if [ -z "$resp" ]; then
#    exit 1
#fi

#docker images | grep lumira
#echo ""
#if [ $resp -eq 1 ]; then
#  ./gradlew pushDockerImage
#  docker images | grep melumirades | awk '{print $3}' | xargs docker rmi
#fi

#cd docker
#docker-compose up

# docker images | grep none | awk '{print $3}' | xargs docker rmi -f
echo ""

echo "=====> Docker Registry <====="





 #./gradlew sonarqube \
 #   -Dsonar.projectKey=lumiraBackend \
 #   -Dsonar.host.url=https://sonarq.ypfb.gob.bo/ \
 #   -Dsonar.login=19aaabff6effc80d90a67be7ca0f75f4d7561de0

#Token-lumiraBackend: 19aaabff6effc80d90a67be7ca0f75f4d7561de0