FROM openjdk:23-slim

# Instala utilidades básicas y Gradle
RUN apt-get update \
    && apt-get install -y wget unzip gradle git\
    && rm -rf /var/lib/apt/lists/*

# Establece el directorio de trabajo (opcional)
WORKDIR /workspace