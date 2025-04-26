FROM reg.ypfb.gob.bo/docker/java/slim:11.1

ARG JAR_FILE=build/libs/lumira.jar
ARG PUBLIC_FILE_DEV=build/resources/main/rsa/dev/jano_rsa_public.pub
# Produccion
ARG PUBLIC_FILE_PROD=build/resources/main/rsa/prod/jano_key_rsa_public.pub

WORKDIR /opt/app
RUN mkdir -p /opt/app/rsa/dev
RUN mkdir -p /opt/app/rsa/prod


COPY ${JAR_FILE} app.jar
#COPY ${PUBLIC_FILE} /opt/app/rsa/jano_rsa_public.pub
COPY ${PUBLIC_FILE_DEV} /opt/app/rsa/dev/jano_rsa_public.pub
COPY ${PUBLIC_FILE_PROD} /opt/app/rsa/prod/jano_key_rsa_public.pub

ENTRYPOINT ["java","-jar","app.jar"]
