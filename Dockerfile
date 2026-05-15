FROM ubuntu:latest
LABEL authors="josesandoval"

ENTRYPOINT ["top", "-b"]