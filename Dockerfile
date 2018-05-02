FROM chickenzord/alpine-gradle
COPY . .
EXPOSE 9000
ENTRYPOINT ["gradle"]
CMD ["runDev"]
