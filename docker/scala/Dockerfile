FROM debian:jessie

RUN echo 'deb http://http.debian.net/debian jessie-backports main' > /etc/apt/sources.list.d/jessie-backports.list &&\
    echo 'deb http://dl.bintray.com/sbt/debian /' > /etc/apt/sources.list.d/sbt.list &&\
    apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 642AC823

RUN apt-get clean && apt-get update && DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends \
                                      openjdk-8-jdk \
                                      wget \
                                      tmux

RUN echo 'export PATH=/src/sbt:$PATH; set -a; source /src/docker/.env; set +a' >> /root/.bashrc

VOLUME ["/src"]

WORKDIR /src